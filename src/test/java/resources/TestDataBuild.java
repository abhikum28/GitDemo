package resources;

import java.util.ArrayList;
import java.util.List;

import pojo.GetAddPlaceReq;
import pojo.Location;

public class TestDataBuild {
	
	public GetAddPlaceReq addPlacePayload(String name, String language, String address) {
		
		Location location = new Location();
		location.setLat(-38.383494);
		location.setLng(33.427362);
		
		List<String> types = new ArrayList<String>();
		types.add("shoe park"); 
		types.add("shop");
		
		GetAddPlaceReq getAddPlaceReq = new GetAddPlaceReq();
		getAddPlaceReq.setLocation(location);
		getAddPlaceReq.setAccuracy(50);
		getAddPlaceReq.setName(name);
		getAddPlaceReq.setPhone_number("(+91) 983 893 3937");
		getAddPlaceReq.setAddress(address);
		getAddPlaceReq.setTypes(types);
		getAddPlaceReq.setWebsite("http://google.com");
		getAddPlaceReq.setLanguage(language);
		
		return getAddPlaceReq;
	}

	public String delPlacePayload(String place_Id) {
		String delPlacePayloadStr = "{\"place_id\":\""+place_Id+"\"}";
		return delPlacePayloadStr;
	}
}
