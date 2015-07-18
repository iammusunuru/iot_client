package ass2_client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

public class bootstrap {
	public void boot()
	
	{
	    Logger mongoLogger = Logger.getLogger( "org.mongodb.driver" );
	    mongoLogger.setLevel(Level.SEVERE); 
		dblayer con = new dblayer("", "");

		 try {
			    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			   System.out.println("\nenter bootstrap server uri :");
			   //expected input http://localhost:8080/
			   String addr = br.readLine();
			   System.out.println("\nenter client end_point name :");
			   String end_point = br.readLine();
				DefaultHttpClient httpClient = new DefaultHttpClient();
				HttpPost postRequest = new HttpPost(addr);				
					StringEntity input = new StringEntity("{\"endpoint_name\":\""+ end_point+"\"}");
					input.setContentType("application/json");
					postRequest.setEntity(input);
			 
					HttpResponse response = httpClient.execute(postRequest);
		 
					if (response.getStatusLine().getStatusCode() != 200) {
						throw new RuntimeException("Failed : HTTP error code : "
							+ response.getStatusLine().getStatusCode());
					}
			 
					BufferedReader br2 = new BufferedReader(
			                        new InputStreamReader((response.getEntity().getContent())));
			 
					String output,o=null;
					System.out.println("Output from Server .... \n");
					while ((output = br2.readLine()) != null) {
						System.out.println(output);
						o=output+o;
						System.out.println("done bootstrapping");
					}
			 
					httpClient.getConnectionManager().shutdown();
					JSONObject j = new JSONObject(o);
					j.put("endpoint_name",end_point);
					j.put("bootstrap_server_uri", addr);
				    con.insert_data(j);
			 
				  } catch (MalformedURLException e) {
			 
					e.printStackTrace();
			 
				  } catch (Exception e) {
			 
					e.printStackTrace();
			 
				  }
	}

}
