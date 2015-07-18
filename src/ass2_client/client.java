package ass2_client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

public class client {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		 while(true){
			 System.out.println("\n#############################IOT################################\n");
			 System.out.println("\n1. Bootstrapping\n");
			 System.out.println("\n2. Register\n");
			 System.out.println("\n3. DeRegister");
			 System.out.println("\n4. Exit\n");
			 System.out.println("enter your choice: ");
			 String choice="";
			try {
				choice = br.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 switch(choice)
			 {
			 case "1":
				 new bootstrap().boot();
				 break;
			 case "2":
				 //register
				 register r =new register();
				 r.regi();
				 break;
			 case "3":
				 register r1 =new register();
				 r1.deregi();
				 
			 case "4":
				 System.exit(0);
			 }
			 
		 }
	}

}
