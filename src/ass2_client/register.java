package ass2_client;



import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

public class register {
	
	@SuppressWarnings("resource")
	public void regi()
	{
		Logger mongoLogger = Logger.getLogger( "org.mongodb.driver" );
	    mongoLogger.setLevel(Level.SEVERE); 
		dblayer con = new dblayer("", "");
		String file_content="";
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	  try {
		    //read csv here
		  System.out.println("enter client end_point name");
		  file_content = new Scanner( new File("/home/musunuru/Desktop/register.txt"), "UTF-8" ).useDelimiter("\\A").next();
           JSONObject j = new JSONObject(file_content);
           j.put("endpoint_name", br.readLine());
           String k = con.fetch_data(j.getString("endpoint_name"));
           if(k!=null)
           {
			DefaultHttpClient httpClient = new DefaultHttpClient();
			HttpPost postRequest = new HttpPost("http://localhost:8080/register");
			
				StringEntity input = new StringEntity(j.toString());
				input.setContentType("application/json");
				postRequest.setEntity(input);
		 
				HttpResponse response = httpClient.execute(postRequest);
	 
				if (response.getStatusLine().getStatusCode() != 200) {
					throw new RuntimeException("Failed : HTTP error code : "
						+ response.getStatusLine().getStatusCode());
				}
		 
				BufferedReader br2 = new BufferedReader(
		                        new InputStreamReader((response.getEntity().getContent())));
		 
				String output;
				System.out.println("Output from Server .... \n");
				while ((output = br2.readLine()) != null) {
					System.out.println(output);
					System.out.println("done registering");
				}
		 
				httpClient.getConnectionManager().shutdown();
           }
           else
           {
        	   System.out.println("device not bootstrapped");
           }
			  } catch (MalformedURLException e) {
		 
				e.printStackTrace();
		 
			  } catch (Exception e) {
		 
				e.printStackTrace();
		 
			  }

	}
	
	public void deregi()
	{
		Logger mongoLogger = Logger.getLogger( "org.mongodb.driver" );
	    mongoLogger.setLevel(Level.SEVERE); 
		dblayer con = new dblayer("", "");
		String file_content="";
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	  try {
		    //read csv here
		  System.out.println("enter client end_point name to delete");
		  file_content = new Scanner( new File("/home/musunuru/Desktop/register.txt"), "UTF-8" ).useDelimiter("\\A").next();
           JSONObject j = new JSONObject(file_content);
           j.put("endpoint_name", br.readLine());
           String k = con.fetch_data(j.getString("endpoint_name"));
           if(k!=null)
           {
			DefaultHttpClient httpClient = new DefaultHttpClient();
			HttpPost postRequest = new HttpPost("http://localhost:8080/deregister");
			
				StringEntity input = new StringEntity(j.toString());
				input.setContentType("application/json");
				postRequest.setEntity(input);
		 
				HttpResponse response = httpClient.execute(postRequest);
	 
				if (response.getStatusLine().getStatusCode() != 200) {
					throw new RuntimeException("Failed : HTTP error code : "
						+ response.getStatusLine().getStatusCode());
				}
		 
				BufferedReader br2 = new BufferedReader(
		                        new InputStreamReader((response.getEntity().getContent())));
		 
				String output;
				System.out.println("Output from Server .... \n");
				while ((output = br2.readLine()) != null) {
					System.out.println(output);
					System.out.println("done deregistering");
				}
		 
				httpClient.getConnectionManager().shutdown();
           }
           else
           {
        	   System.out.println("device not bootstrapped");
           }
			  } catch (MalformedURLException e) {
		 
				e.printStackTrace();
		 
			  } catch (Exception e) {
		 
				e.printStackTrace();
		 
			  }

		
	}

}
