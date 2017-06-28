
package com.sp.controller.rest;


import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.sp.services.spservice.SPService;

@Path("/status/")
public class StatusDebug {
	/**
	 * @author dan
	 */
private static final String api_version = "SmartPoint Beta 1.0.0"; //version of the api
	
	/**
	 * This method sits at the root of the api.  It will return the name
	 * of this api.
	 * 
	 * @return String - Title of the api
	 */
	@Path("/cacerts")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String returnTitle() {
		 String retorno= "";
		 String currentLocation = "-22.7042212,-46.9855012";
		   String dest = "-22.9099312,-47.0626312";
		   String API_KEY = "AIzaSyBK1FNUH9AnosNUQPOPrjbZpk5M7HPP32M";
	      String https_url = "https://maps.googleapis.com/maps/api/distancematrix/json?origins=" + currentLocation +"&destinations=" +dest+ "&key=" +  API_KEY;
	      URL url;
		
		 try {
			url = new URL(https_url);
		     HttpsURLConnection con = (HttpsURLConnection)url.openConnection();

		     SPService spSer = new SPService();
				
				return spSer.get_https_cert(con).toString();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return https_url;

	     //dumpl all cert info
	   
		
	}
	
	/**
	 * This method will return the version number of the api
	 * Note: this is nested one down from the root.  You will need to add version
	 * into the URL path.
	 * 
	 * Example:
	 * http://localhost:7001/com.youtube.rest/api/v1/status/version
	 * 
	 * @return String - version number of the api
	 */
	@Path("/version/")
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String returnVersion() {
		return "<p>Version:</p>" + api_version;
	}
	
	@Path("/bd/")	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String bdTest() {
		SPService spSer = new SPService();
		return "teste";
	}
	
	@Path("/bdcheck/")	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String returnDB() {
		SPService spSer = new SPService();
		return spSer.dbCheck().toString();
	}
	
	/**
	 * This method will connect to the oracle database and return the date/time stamp.
	 * It will then return the date/time to the user in String format
	 * 
	 * This was explained in Part 3 of the Java Rest Tutorial Series on YouTube
	 * 
	 * Pre-episode 6, updated because Oracle308tube.java no longer accessible.
	 * 
	 * @return String -  returns the database date/time stamp
	 * @throws Exception
	 */
	/*@Path("/database")
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String returnDatabaseStatus() throws Exception {
		
		String myString = null;
		String returnString = null;
		JSONArray json = new JSONArray();
		
		try {
			
			Schema308tube dao = new Schema308tube();
			
			json = dao.queryCheckDbConnection();
			myString = json.toString();
			
			returnString = "<p>Database Status</p> " +
				"<p>Database Date/Time return: " + myString + "</p>";
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return returnString; 
	}*/
	
}