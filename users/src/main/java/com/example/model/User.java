package com.example.model;
import io.quarkus.mongodb.panache.common.MongoEntity;
import org.bson.types.ObjectId;


@MongoEntity(collection = "users")
public class User {
    private ObjectId id;
    private String name;
    private int age;

    public User() {}

    public User(ObjectId id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
