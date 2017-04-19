package com.eretailservice.service;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.stereotype.Service;

import com.eretailservice.geo.auth.AuthenticatedOkHttpRequestHandler;
import com.eretailservice.geo.auth.Authenticator;
import com.eretailservice.model.APlace;
import com.eretailservice.model.Address;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;

/**
 * Service to locate the latitude and longitude of a shop. Uses Google's
 * Geocoding service.
 * 
 * @author yinchun li
 *
 */
@Service
public class GeocodeService {

	private static final Logger LOG = Logger.getLogger(GeocodeService.class.getName());
	private GeoApiContext context;

	private boolean proxy;
	private String proxyaddress;
	private String proxyport;
	private String proxyuser;
	private String proxypassword;

	private String apikey ="AIzaSyD7vnkhYbTs1oDx1mbH2QnYYBywOWdVNTM";

	/**
	 * initialize the Geo API Context with API key and request handler etc
	 */
	private void initializeGeoApiContext() {
		if (proxy) {
			LOG.log(Level.INFO, "Proxy settings on :", proxyaddress + ":" + proxyport + "@" + proxyuser);
			AuthenticatedOkHttpRequestHandler requestHandler = new AuthenticatedOkHttpRequestHandler();
			Authenticator authenticator = new Authenticator(proxyuser, proxypassword);
			requestHandler.setAuthenticator(authenticator);
			InetSocketAddress address = new InetSocketAddress(proxyaddress, Integer.valueOf(proxyport));
			
			Proxy proxy = new Proxy(Proxy.Type.HTTP, address);
			requestHandler.setProxy(proxy);
			
			context = new GeoApiContext(requestHandler);
		} else {
			context = new GeoApiContext();
		}
		context.setApiKey(apikey);
	}

	/**
	 * return the geocode of the shop
	 * 
	 * @param shop
	 * @return
	 */
	public GeocodingResult getGeocode(APlace shop) {
		initializeGeoApiContext();
		GeocodingResult[] results = null;
		try {
				results = GeocodingApi.newRequest(context)
				        .latlng(new LatLng(shop.getPlaceLatitude(),shop.getPlaceLongitude())).await();
		} catch (Exception e) {
			LOG.log(Level.SEVERE, "Exception in invoking Google geocoding API :", e.getCause());
		}
		return results[0];
	}

//	https://maps.googleapis.com/maps/api/distancematrix/json?units=imperial&
//	origins=40.6655101,-73.89188969999998&destinations=40.6905615%2C-73.9976592%7C40.6905615%2C-73.9976592%7C40.6905615%2C-73.9976592%7C40.6905615%2C-73.9976592%7C40.6905615%2C-73.9976592%7C40.6905615%2C-73.9976592%7C40.659569%2C-73.933783%7C40.729029%2C-73.851524%7C40.6860072%2C-73.6334271%7C40.598566%2C-73.7527626%7C40.659569%2C-73.933783%7C40.729029%2C-73.851524%7C40.6860072%2C-73.6334271%7C40.598566%2C-73.7527626
//	&key=AIzaSyD7vnkhYbTs1oDx1mbH2QnYYBywOWdVNTM
	
	/**
	 * comma separated string formated address
	 * 
	 * @param shop
	 * @return
	 */
	private String getFormattedAddress(APlace shop) {
		Address address = shop.getPlaceAddress();
		StringBuilder formattedAddress = new StringBuilder();

		if (Objects.nonNull(shop.getPlaceName())) {
			formattedAddress.append(shop.getPlaceName()).append(",");
		}
		if (Objects.nonNull(address.getNumber())) {
			formattedAddress.append(address.getNumber()).append(",");
		}
		if (Objects.nonNull(address.getAddressLine1())) {
			formattedAddress.append(address.getAddressLine1()).append(",");
		}
		if (Objects.nonNull(address.getAddressLine2())) {
			formattedAddress.append(address.getAddressLine2()).append(",");
		}
		if (Objects.nonNull(address.getPostCode())) {
			formattedAddress.append(address.getPostCode());
		}
		LOG.log(Level.INFO, "Evaluating geocode for the address :", formattedAddress.toString());
		return formattedAddress.toString();
	}

	public void setContext(GeoApiContext context) {
		this.context = context;
	}

	public void setProxyaddress(String proxyaddress) {
		this.proxyaddress = proxyaddress;
	}

	public void setProxyport(String proxyport) {
		this.proxyport = proxyport;
	}

	public void setProxyuser(String proxyuser) {
		this.proxyuser = proxyuser;
	}

	public void setProxypassword(String proxypassword) {
		this.proxypassword = proxypassword;
	}

	public void setApikey(String apikey) {
		this.apikey = apikey;
	}

	public void setProxy(boolean proxy) {
		this.proxy = proxy;
	}
}