package com.eretailservice.controller;

import java.net.URI;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.eretailservice.model.Booking;
import com.eretailservice.repository.AccountRepository;
import com.eretailservice.repository.BookingRepository;

@RestController
@RequestMapping("/{userId}/bookmarks")
@EnableJpaRepositories(
		basePackages = {"com.eretailservice.repository"}
	)
class BookingController {

	private final BookingRepository bookingRepository;

	private final AccountRepository accountRepository;

	@Autowired
	BookingController(BookingRepository bookingRepository,
						   AccountRepository accountRepository) {
		this.bookingRepository = bookingRepository;
		this.accountRepository = accountRepository;
	}

	@RequestMapping(method = RequestMethod.GET)
	Collection<Booking> readBookmarks(@PathVariable String userId) {
		this.validateUser(userId);
		return this.bookingRepository.findByAccountUsername(userId);
	}

	@RequestMapping(method = RequestMethod.POST)
	ResponseEntity<?> add(@PathVariable String userId, @RequestBody Booking input) {
		this.validateUser(userId);

		return this.accountRepository
				.findByUsername(userId)
				.map(account -> {
					Booking result = bookingRepository.save(new Booking(account,
							input.uri, input.description));

					URI location = ServletUriComponentsBuilder
						.fromCurrentRequest().path("/{id}")
						.buildAndExpand(result.getId()).toUri();

					return ResponseEntity.created(location).build();
				})
				.orElse(ResponseEntity.noContent().build());

	}

	@RequestMapping(method = RequestMethod.GET, value = "/{bookingId}")
	Booking readBooking(@PathVariable String userId, @PathVariable Long bookingId) {
		this.validateUser(userId);
		return this.bookingRepository.findOne(bookingId);
	}

	private void validateUser(String userId) {
//		this.accountRepository.findByUsername(userId).orElseThrow(
//				(Supplier<? extends X>) () -> new UserNotFoundException(userId));
	}
}