package com.eretailservice.model;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.eretailservice.util.Util;
import com.google.maps.model.LatLng;

/**
 * An in memory store which stores all the data in an array list. Provide a
 * different implementation if you want data persistence.
 * 
 * @author yinchun
 *
 */
public class MemoryStore implements ASubPlace<APlace, LatLng> {

	/**
	 * logger
	 */
	private static final Logger LOG = Logger.getLogger(MemoryStore.class.getName());

	/**
	 * the list for storing data
	 */
	private final List<APlace> data = new ArrayList<>();

	/**
	 * get the APlace nearest to a geocode
	 */
	@Override
	public APlace get(LatLng geocode) {
		APlace nearestAPlace = findNearest(geocode);
		return nearestAPlace;
	}

	/**
	 * get all the registered APlaces
	 */
	@Override
	public List<APlace> getAll() {
		return data;
	}

	/**
	 * register a APlace
	 */
	@Override
	public APlace add(APlace item) {
		data.add(item);
		return item;
	}

	/**
	 * Find the APlace nearest to this geocode
	 * 
	 * @param geocode
	 * @return
	 */
	public APlace findNearest(LatLng geocode) {
		// customer latitude and longitude
		double lat1 = geocode.lat;
		double lon1 = geocode.lng;
		// hold the nearest distance found till now
		double nearestDist = -1;
		// hold the reference to the nearest APlace found till now
		APlace nearestAPlace = null;
		for (APlace APlace : data) {
			// latitude and longitude of the APlace to compare
			double lat2 = APlace.getPlaceLatitude();
			double lon2 = APlace.getPlaceLongitude();
			// distance to the APlace in comparison
			double dist = Util.haversine(lat1, lon1, lat2, lon2);
			// if the APlace in comparison is nearer than the previous APlace or if
			// it is the first APlace
			if (dist < nearestDist || nearestDist == -1) {
				nearestAPlace = APlace;
				nearestDist = dist;
				LOG.log(Level.INFO, " APlace " + nearestAPlace.getPlaceName() + " found at " + nearestDist + " KM");
			}
		}
		return nearestAPlace;
	}

}
