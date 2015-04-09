
import java.net.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.*;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.*;
import org.json.simple.parser.*;

public class ExtractTest2 {
	
	private static String readAll(Reader rd) throws IOException {
	    StringBuilder sb = new StringBuilder();
		int cp;
		while ((cp = rd.read()) != -1) {
		  sb.append((char) cp);
		}
		return sb.toString();	
	  }
	
	public static String readJsonFromUrl(String url) throws IOException, JSONException {
	    InputStream is = new URL(url).openStream();
	    try {
	      BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
	      String jsonText = readAll(rd);
	      return jsonText;
	    } finally {
	      is.close();
	    }
  }
	
	public static void main(String[] args) throws JSONException, IOException {
	    String str = readJsonFromUrl("http://data.undp.org/resource/myer-egms.json");

	    JSONArray jsonarray = new JSONArray(str);

	    for(int i=0; i<jsonarray.length(); i++){
	        JSONObject obj = jsonarray.getJSONObject(i);

	        try {
	        	String country = obj.getString("country");
		        String hdi_2013 = obj.getString("_2013_hdi_value");
		        if(obj.getString("_2013_hdi_value") != "")
		        {
		        	System.out.println(country);
			        System.out.println(hdi_2013);	
		        }
	        } catch (Exception e) {}
	        
	    }   
	}   
}
