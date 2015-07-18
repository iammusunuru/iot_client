package ass2_client;

import java.net.UnknownHostException;
import java.util.Date;

import org.bson.BasicBSONObject;
import org.json.JSONObject;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.util.JSON;

public class dblayer {
	private MongoClient conn;
	private DB db;
	DBCollection coll;
	public dblayer(String uname, String pass)
	{
		try {
			this.conn = new MongoClient( "localhost" , 27017 );
			this.db = this.conn.getDB("client");
		//	boolean auth = db.authenticate(uname, pass.toCharArray());
			this.coll = db.getCollection("boot_info");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public int insert_data(JSONObject j)
	{
		DBObject dbObject = (DBObject) JSON.parse(j.toString());
		this.coll.insert(dbObject);
		return 1;
		
	}
	public String fetch_data(String id)
	{
		BasicDBObject doc1 = new BasicDBObject("endpoint_name",id);
		DBCursor cursor = this.coll.find(doc1);
		DBObject ret=null;
		try {
		    while (cursor.hasNext()) {
		        ret = (cursor.next());
		    }
		if (ret!=null)
		{
			return (String) ret.get("bootstrap_server_uri");
		}
		else
		{
			return null;
		}
		} finally {
		    cursor.close();
		}
	
	}

}
