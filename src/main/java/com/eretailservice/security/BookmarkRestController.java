/*
 * Copyright 2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.eretailservice.security;

import java.net.URI;
import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resources;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.eretailservice.exception.UserNotFoundException;
import com.eretailservice.model.Booking;
import com.eretailservice.repository.AccountRepository;
import com.eretailservice.repository.BookingRepository;

@RestController
@RequestMapping("/bookings")
class BookingRestController {

	private final BookingRepository bookingRepository;

	private final AccountRepository accountRepository;

	@Autowired
	BookingRestController(BookingRepository bookingRepository,
						   AccountRepository accountRepository) {
		this.bookingRepository = bookingRepository;
		this.accountRepository = accountRepository;
	}

	@RequestMapping(method = RequestMethod.GET)
	Resources<BookingResource> readBookings(Principal principal) {
		this.validateUser(principal);

		List<BookingResource> bookingResourceList = bookingRepository
			.findByAccountUsername(principal.getName()).stream()
			.map(BookingResource::new)
			.collect(Collectors.toList());

		return new Resources<>(bookingResourceList);
	}

	@RequestMapping(method = RequestMethod.POST)
	ResponseEntity<?> add(Principal principal, @RequestBody Booking input) {
		this.validateUser(principal);

		return accountRepository
				.findByUsername(principal.getName())
				.map(account -> {
					Booking booking = bookingRepository.save(
						new Booking(account, input.uri, input.description));

					Link forOneBooking = new BookingResource(booking).getLink(Link.REL_SELF);

					return ResponseEntity.created(URI
						.create(forOneBooking.getHref()))
						.build();
				})
				.orElse(ResponseEntity.noContent().build());
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{bookingId}")
	BookingResource readBooking(Principal principal, @PathVariable Long bookingId) {
		this.validateUser(principal);
		return new BookingResource(
			this.bookingRepository.findOne(bookingId));
	}

	private void validateUser(Principal principal) {
		String userId = principal.getName();
		this.accountRepository
			.findByUsername(userId)
			.orElseThrow(
				() -> new UserNotFoundException(userId));
	}
}
// end::code[]
