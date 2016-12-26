package com.ry.spidertest.mongodb;

import com.mongodb.*;
import com.mongodb.util.JSON;
import org.bson.types.ObjectId;

import java.net.UnknownHostException;
import java.util.ArrayList;

/**
 * Created by yangyang on 2016/12/26.
 */
public class MongoConnector {
    private static Mongo mongo;
    private static String user;
    private static String password;
    private String dbName;
    private String collectionName;

    static {
        String host = "127.0.0.1";
        String port = "27017";
        mongo = new Mongo(host,Integer.parseInt(port));
    }

    public static Mongo getMongo(){
        return mongo;
    }

    public MongoConnector(String dbName, String collectionName) {
        this.dbName = dbName;
        this.collectionName = collectionName;
        mongo = MongoConnector.getMongo();
    }

    public static DB getDB(String dbName) {
        if(mongo!=null){
            DB db = mongo.getDB(dbName);
            return db;
        }
        return null;
    }

    public String insertDocument(String documentJSON) {
        DB db = getDB(dbName);
        DBCollection collection = db.getCollection(collectionName);
        DBObject document = (DBObject) JSON.parse(documentJSON);
        WriteResult result = collection.insert(document);
        Object objectId = document.get("_id");
        if(objectId!=null){
            return ((ObjectId)objectId).toStringMongod();
        }
        return null;
    }

    public String insertDocumentObject(DBObject documentObject) {
        DB db = getDB(dbName);
        DBCollection collection = db.getCollection(collectionName);
        WriteResult result = collection.insert(documentObject);
        Object objectId = documentObject.get("_id");
        if(objectId!=null){
            return ((ObjectId)objectId).toStringMongod();
        }
        return null;
    }

    //获取文档对象
    public DBObject getDocument(String objectID) {
        if(objectID==null){
            throw new IllegalArgumentException("object id is null");
        }
        DB db = getDB(dbName);
        DBCollection collection = db.getCollection(collectionName);
        BasicDBObject query = new BasicDBObject();
        query.put("_id", new ObjectId(objectID));
        DBObject document = collection.findOne(query);
        return document;
    }

    //获取文档内的数据数组
    public ArrayList getDocumentData(String objectID) {
        DBObject dbObject = getDocument(objectID);
        ArrayList data = (ArrayList)dbObject.get("data");
        return data;
    }
}
