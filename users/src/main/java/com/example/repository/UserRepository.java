package com.example.repository;

import io.quarkus.mongodb.panache.PanacheMongoRepository;
import jakarta.enterprise.context.ApplicationScoped; // Import Jakarta EE annotation
import jakarta.inject.Inject; // Import Jakarta EE annotation
import com.example.model.User;

@ApplicationScoped
public class UserRepository implements PanacheMongoRepository<User> {}
