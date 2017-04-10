package com.eretailservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.eretailservice.model.APlace;
import com.eretailservice.model.Address;
import com.eretailservice.service.GeocodeService;
import com.google.maps.model.GeocodingResult;

@RestController
public class GeoCodeController {
	
	@Autowired
	private GeocodeService geoService;

	@GetMapping("/req")
	public String hello(){
		return "<br>Functional Requirements: </br>"
				+ "Converting a interview request to a Resouce Renting System,"
				+ "Using google Mapping to locate the resouce"
				+ "and Event to book the resources";
	}
	
	@GetMapping("/map/{la}/{lo:.+}")
	public String getMap(@PathVariable String la, @PathVariable String lo){
		APlace shop = new APlace();
		Address shopAddress = new Address();
		
		shop.setPlaceLatitude(Double.valueOf(la));
		shop.setPlaceLongitude(Double.valueOf(lo));
		
		shop.setPlaceAddress(shopAddress);
		
		GeocodingResult gr=geoService.getGeocode(shop);
		
		return gr.formattedAddress;
	}
}