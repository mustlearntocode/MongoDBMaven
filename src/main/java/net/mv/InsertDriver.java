package net.mv;

import java.net.UnknownHostException;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;

public class InsertDriver {
	public static void main(String[] args) throws UnknownHostException {
		DB dB = (new MongoClient("localhost", 27017)).getDB("zaneacademydatabse");
		DBCollection dBCollection = dB.getCollection("CounterCollection");
		BasicDBObject basicDBObject = new BasicDBObject();
//		basicDBObject.put("name", "xiuyi");
//		basicDBObject.put("subscriptions", 200);
		basicDBObject.put("counter", 0);
		dBCollection.insert(basicDBObject);
	}
}
