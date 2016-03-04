package net.mv;

import java.net.UnknownHostException;
import java.util.List;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;

import net.mv.shortenURL.URLShorten;
import net.mv.shortenURL.URLShortenImpl;

public class QueryDriver {

	public static void main(String[] args) throws UnknownHostException {

		System.out.println("Get database name");
		List<String> dbs = (new MongoClient("localhost", 27017)).getDatabaseNames();
		for (String db : dbs) {
			System.out.println(db);
		}
		System.out.println("**************");
		String res1=URLTrans("http://stackoverflow.com/questions/5961145/changing-mongodb-data-store-directory");
		String res2=URLTrans("http://www.tutorialspoint.com/mongodb/mongodb_environment.htm");
		System.out.println("Syso from main for res1"+res1);
		System.out.println("Syso from main for res2"+ res2);
		displayURLs();
//		isExist("http://stackoverflow.com/questions/5961145/changing-mongodb-data-store-directory");

	}

	public static String URLTrans(String inputURL) throws UnknownHostException {
		DB dB = (new MongoClient("localhost", 27017)).getDB("zaneacademydatabse");
		// DBCollection dBCollection = dB.getCollection("Channel");
		// BasicDBObject basicDBObject = new BasicDBObject();
		// basicDBObject.put("name", "zaneacademydatabse");
		// DBCursor dbCursor = dBCollection.find(basicDBObject);
		// System.out.println("Find name==zaneacademydatabse");
		// while (dbCursor.hasNext()){
		// System.out.println(dbCursor.next());
		// }
		// System.out.println("Find everything in the Channel connection");
		// DBCursor dbCursor2 = dBCollection.find();
		// while(dbCursor2.hasNext()){
		// System.out.println(dbCursor2.next());
		// }
		DBCollection dBCollection = dB.getCollection("CounterCollection");
		DBCollection URLtable = dB.getCollection("URL");

		DBCursor cursor = dBCollection.find();

		while (cursor.hasNext()) {

			BasicDBObject obj = (BasicDBObject) cursor.next();
			String URLFromDB=isExist(inputURL);
			if(URLFromDB!=null){
				System.out.println("URL exists and it is" + URLFromDB);
				return URLFromDB;
			}
			else
			{
				System.out.println("ULR first time");
			}
			// System.out.println(obj.getInt("counter"));
			int ret = obj.getInt("counter");
			System.out.println(ret);

			URLShorten shortenURL = new URLShortenImpl();
			String newURL = shortenURL.shortenURL(ret + 1);
			System.out.println("new ULR is " + newURL);
			BasicDBObject urls = new BasicDBObject();
			urls.put("OldURL", inputURL);
			urls.put("NewURL", newURL);
			URLtable.insert(urls);

			// updating
			BasicDBObject query = new BasicDBObject();
			query.put("counter", ret);

			BasicDBObject newDocument = new BasicDBObject();
			newDocument.put("counter", (ret + 1));

			BasicDBObject updateObj = new BasicDBObject();
			updateObj.put("$set", newDocument);

			dBCollection.update(query, updateObj);
			System.out.println("url saving success!!");
			
			return newURL;

		}
		return "NULL";

	}

	public static void displayURLs() throws UnknownHostException{
		DB dB = (new MongoClient("localhost", 27017)).getDB("zaneacademydatabse");
		DBCollection dBCollection = dB.getCollection("CounterCollection");
		DBCollection URLtable = dB.getCollection("URL");

		DBCursor cursor = URLtable.find();

		while (cursor.hasNext()) {

//			BasicDBObject obj = (BasicDBObject) cursor.next();
			System.out.println(cursor.next());
		}
	}
	
	public static String isExist(String url) throws UnknownHostException{
		DB dB = (new MongoClient("localhost", 27017)).getDB("zaneacademydatabse");
//		DBCollection dBCollection = dB.getCollection("CounterCollection");
		DBCollection URLtable = dB.getCollection("URL");
		
		BasicDBObject searchQuery = new BasicDBObject();
		searchQuery.put("OldURL", url);
		DBCursor cursor = URLtable.find(searchQuery);
		if(cursor.hasNext()){
//			System.out.println("url exists!!");
			BasicDBObject obj = (BasicDBObject)cursor.next();
			String res = obj.getString("NewURL");
//			System.out.println("And the short URL is" +res);
			return res;
		}
		else{
//			System.out.println("Url doesn't exist");
			return null;
		}
	}
}
