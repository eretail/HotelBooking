package com.eretailservice.model;

public class APlace {

	private String placeName;

	private Address placeAddress;

	private double placeLatitude;

	private double placeLongitude;

	public double getPlaceLatitude() {
		return placeLatitude;
	}

	public void setPlaceLatitude(double placeLatitude) {
		this.placeLatitude = placeLatitude;
	}

	public double getPlaceLongitude() {
		return placeLongitude;
	}

	public void setPlaceLongitude(double placeLongitude) {
		this.placeLongitude = placeLongitude;
	}

	public String getPlaceName() {
		return placeName;
	}

	public void setPlaceName(String placeName) {
		this.placeName = placeName;
	}

	public Address getPlaceAddress() {
		return placeAddress;
	}

	public void setPlaceAddress(Address placeAddress) {
		this.placeAddress = placeAddress;
	}

}
