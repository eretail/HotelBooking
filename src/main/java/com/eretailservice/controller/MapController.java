package com.eretailservice.controller;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.eretailservice.location.ServerLocation;
import com.eretailservice.location.ServerLocationBo;
import com.eretailservice.service.GeocodeService;
import com.google.maps.model.PlacesSearchResponse;
import com.google.maps.model.PlacesSearchResult;

@Controller
public class MapController {

	static final String apikey="AIzaSyD7vnkhYbTs1oDx1mbH2QnYYBywOWdVNTM";
	static final String googleUrl="https://maps.googleapis.com/maps/api/place/nearbysearch/";
	
	@Autowired
	ServerLocationBo serverLocationBo;

	@Autowired
	GeocodeService gs;
	
	@RequestMapping(value = "/map", method = RequestMethod.GET)
	public ModelAndView getPages() {

		ModelAndView model = new ModelAndView("map");
		return model;

	}

//	https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=-33.8670522,151.1957362&radius=500&type=restaurant&keyword=cruise&key=AIzaSyD7vnkhYbTs1oDx1mbH2QnYYBywOWdVNTM
	
	@RequestMapping(value = "/map2business", method = RequestMethod.GET)
	public @ResponseBody
	ModelAndView getBusinessInMap(@RequestParam("la") String la, @RequestParam("lo") String lo) {

		gs.setApikey(apikey);
		System.out.println(la);
		System.out.println(lo);
		
		ModelAndView model = new ModelAndView("index");
		
		return model;
	}
	
	@RequestMapping(value = "/map2business2", method = RequestMethod.GET)
	public @ResponseBody
	ModelAndView getBusinessInMap2(@RequestParam("la") String la, @RequestParam("lo") String lo) {

		gs.setApikey(apikey);
		System.out.println(la);
		System.out.println(lo);
		
		ModelAndView model = new ModelAndView("index");
		
		return model;
	}
	
	@RequestMapping(value = "/getLocationByIpAddress", method = RequestMethod.GET)
	public @ResponseBody
	String getDomainInJsonFormat(@RequestParam String ipAddress) {

		ObjectMapper mapper = new ObjectMapper();

		ServerLocation location = serverLocationBo.getLocation(ipAddress);

		String result = "";

		try {
			result = mapper.writeValueAsString(location);
		} catch (Exception e) {

			e.printStackTrace();
		}

		return result;
	}

	@RequestMapping(value = "/maps/nearby", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody
	String getBusinessNearBy() {
		String url="https://maps.googleapis.com/maps/api/place/nearbysearch/json?key=AIzaSyD7vnkhYbTs1oDx1mbH2QnYYBywOWdVNTM&location=-33.8670522,151.1957362&rankby=distance&types=hardware_store";
		
		RestTemplate restTemplate = new RestTemplate();
		String place = (String) restTemplate.getForObject(url, String.class, 200);
//		PlacesSearchResponse place = (PlacesSearchResponse) restTemplate.getForObject(url, PlacesSearchResponse.class, 200);
	      
        System.out.println("ID: " + place);
    
		return place;

	}
	
	
	/*    @Autowired
    private GeolocDAO geolocDAO;

    @RequestMapping(value = "/map", method = RequestMethod.GET)
    public ModelAndView getPages(HttpServletRequest request) {
        Geolocation geoloc = geolocDAO.get();
        ModelAndView model = new ModelAndView("map");
        List<Geolocation> listGeo = geolocDAO.list();
        model.addObject("listGeo", listGeo);
        return model;
    }*/

}
