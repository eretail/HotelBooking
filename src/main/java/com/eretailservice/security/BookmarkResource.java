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

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceSupport;

import com.eretailservice.model.Booking;

class BookingResource extends ResourceSupport {

	private final Booking booking;

	public BookingResource(Booking booking) {
		String username = booking.getAccount().getUsername();
		this.booking = booking;
		this.add(new Link(booking.uri, "booking-uri"));
		this.add(linkTo(BookingRestController.class, username).withRel("bookings"));
		this.add(linkTo(
				methodOn(BookingRestController.class, username).readBooking(null,
						booking.getId())).withSelfRel());
	}

	public Booking getBooking() {
		return booking;
	}
}
// end::code[]
