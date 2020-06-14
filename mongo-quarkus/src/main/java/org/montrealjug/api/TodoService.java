package org.montrealjug.api;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import org.bson.Document;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class TodoService {

    @ConfigProperty(name = "quarkus.mongo.database")
    private String database;

    @ConfigProperty(name = "custom.quarkus.mongodb.collection")
    private String collection;


    private MongoClient mongoClient;

    public TodoService(MongoClient mongoClient) {
        this.mongoClient = mongoClient;
    }

    public Document add(Todo todo) {
        Document document = new Document()
                .append("title", todo.getTitle())
                .append("completed", todo.isCompleted());
        getCollection().insertOne(document);
        return document;
    }

    private <Document> MongoCollection<org.bson.Document> getCollection() {
        return mongoClient.getDatabase(database).getCollection(collection);
    }

    public List<Todo> list() {
        List<Todo> list = new ArrayList<>();
        MongoCursor<Document> cursor = getCollection().find().iterator();
        try {
            Document doc;
            while (cursor.hasNext()) {
                doc = cursor.next();
                list.add(new Todo(doc.getString("title"), doc.getBoolean("completed")));

            }

        } finally {
            cursor.close();
        }
        return list;
    }
}
