package com.cisco.aws;

import org.json.JSONObject;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class FingerBank {

	public String postDeviceName(EndPoint ep) {
		//Initialize URL
		Client client = Client.create();
		String url = "https://api.fingerbank.org/api/v2/combinations/interrogate?key=83dfe2be31701d8f3e3fd27eb160e84a698e1cb5";
		WebResource webResource = client.resource(url);

		//convert EP data to JSON
		JSONObject json = new JSONObject(ep);
		JSONObject attributeJson = json.getJSONObject("attributes");
		
		//create FingerBank JSON payload for significant attributes
		JSONObject fbjson = FingerBank.createFingerbankPayload(attributeJson);
		
		//Call Fingerbank API
		ClientResponse response = webResource.type("application/json").post(ClientResponse.class,fbjson.toString());

		//Check response and return Unknown if status 404
		if (response.getStatus() != 200) {
			if (response.getStatus() == 404) {
				return "Unknown";
			} else {
				throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
			}
		} else {
			String output = response.getEntity(String.class);
			JSONObject responsejson = new JSONObject(output);
			String device_name = responsejson.getJSONObject("device").getString("name");

			return device_name;
		}
	}
	
	private static JSONObject createFingerbankPayload(JSONObject jsonep) {
		//Creates new JSON payload converting ISE attributes to Fingerbank attributes.
		//If no significant attributes, just send original EP json attributes
		JSONObject fingerbankjson = new JSONObject();
		if (jsonep.has("dhcp-parameter-request-list") || 
				jsonep.has("dhcp-class-identifier") || 
				jsonep.has("User-Agent") ||
				jsonep.has("host-name")) {
			
			if (jsonep.has("dhcp-parameter-request-list")) {
				System.out.println("Converting dhcp-parameter-request-list to dhcp_fingerprint");
				String value = jsonep.getString("dhcp-parameter-request-list");
				fingerbankjson.put("dhcp_fingerprint",value);
			} 
			if (jsonep.has("dhcp-class-identifier")) {
				System.out.println("Converting dhcp-class-identifier to dhcp_vendor");
				String value = jsonep.getString("dhcp-class-identifier");
				fingerbankjson.put("dhcp_vendor",value);
			}
			if (jsonep.has("User-Agent")) {
				System.out.println("Converting User-Agent to user_agents");
				String value = jsonep.getString("User-Agent");
				fingerbankjson.put("user_agents",value);
			}
			if (jsonep.has("host-name")) {
				System.out.println("Converting host-name to hostname");
				String value = jsonep.getString("host-name");
				fingerbankjson.put("hostname",value);
			}
		} else {
			System.out.println("No significant attributes used by Fingerbank");
			if (jsonep.has("MACAddress")) {
				String value = jsonep.getString("MACAddress");
				fingerbankjson.put("mac", value);
			} else {
				return jsonep;
			}
		}
		//System.out.println("Fingerbank JSON Payload = \n");
		//System.out.println(fingerbankjson);
		return fingerbankjson;
	}
}
