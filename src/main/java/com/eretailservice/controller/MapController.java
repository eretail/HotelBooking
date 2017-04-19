package com.eretailservice.controller;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.eretailservice.location.ServerLocation;
import com.eretailservice.location.ServerLocationBo;
import com.eretailservice.service.GeocodeService;

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
