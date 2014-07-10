package servidorphpuca.example.com.myapplication;


import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

//import java.net.URL;
//import android.content.Context;

public class MiThread  {
	
	String JsonResult;
	
	//M�todo que devuelve una lista de String
	public  List<String> getImagesStack(String Url)
	{
		
			List <String>stackSites = new ArrayList<String>();
	
		    HttpClient httpClient = new DefaultHttpClient();
		    
		    HttpPost httpGet = new HttpPost(Url);
		    
		    try {
			   
			    HttpResponse response = (HttpResponse)httpClient.execute(httpGet);
			    
			    JsonResult = inputStreamToString( response.getEntity().getContent()).toString();
			    
			    // Parseamos la respuesta obtenida del servidor a un objeto JSON    
			    JSONObject jsonResponse = new JSONObject(JsonResult);
			    JSONArray jsonMainNode = jsonResponse.optJSONArray("nombre_fotos");
			    
			    //Pasamos la informaci�n de cada imagen
			    for (int i = 0; i < jsonMainNode.length(); i++) {
			    	
			        JSONObject jsonChildNode = jsonMainNode.getJSONObject(i);
			        
			        String name = jsonChildNode.optString("nombre_fotos");
			        
			        stackSites.add("http://webucaphp.esy.es/"+name);
			    }
		    
			} catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JSONException e) {
				Log.i("gol","la exepcion");
				e.printStackTrace();
			}
			
			return stackSites;
	}
	 private StringBuilder inputStreamToString(InputStream is) {
	        String rLine = "";
	        StringBuilder answer = new StringBuilder();
	        BufferedReader rd = new BufferedReader(new InputStreamReader(is));
	          
	        try {
	         while ((rLine = rd.readLine()) != null) {
	          answer.append(rLine);
	           }
	        }
	          
	        catch (IOException e) {
	            e.printStackTrace();
	         }
	        return answer;
	       }
	

}
