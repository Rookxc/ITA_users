package com.example.service;

import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import io.quarkus.grpc.GrpcService;
import com.example.repository.UserRepository;
import com.example.UserServiceGrpc;

import jakarta.inject.Inject;

import org.bson.types.ObjectId;

@GrpcService
public class UserService extends UserServiceGrpc.UserServiceImplBase {

    @Inject
    UserRepository userRepository;

    @Override
    public void createUser(com.example.CreateUserRequest request, StreamObserver<com.example.UserResponse> responseObserver) {
        com.example.UserResponse.Builder responseBuilder = com.example.UserResponse.newBuilder();
        try {
            com.example.model.User user = new com.example.model.User(null, request.getName(), request.getAge());
            userRepository.persist(user);

            // Assuming the ID is generated and assigned by MongoDB upon persisting
            String idString = user.getId() != null ? user.getId().toHexString() : "unknown";
            com.example.UserResponse response = com.example.UserResponse.newBuilder()
                    .setId(idString)
                    .setName(user.getName())
                    .setAge(user.getAge())
                    .build();

            responseObserver.onNext(response);
            responseObserver.onCompleted();
        } catch (Exception e) {
            responseObserver.onError(Status.INTERNAL.withDescription("Error creating user").asRuntimeException());
        }
    }

    @Override
    public void getUser(com.example.GetUserRequest request, StreamObserver<com.example.UserResponse> responseObserver) {
        com.example.UserResponse.Builder responseBuilder = com.example.UserResponse.newBuilder();
        try {
            ObjectId objectId = new ObjectId(request.getId());
            com.example.model.User user = userRepository.findById(objectId);
            if (user != null) {
                com.example.UserResponse response = com.example.UserResponse.newBuilder()
                        .setId(user.getId() != null ? user.getId().toHexString() : "unknown")
                        .setName(user.getName())
                        .setAge(user.getAge())
                        .build();
                responseObserver.onNext(response);
            } else {
                responseObserver.onError(Status.NOT_FOUND.withDescription("User not found").asRuntimeException());
            }
        } catch (IllegalArgumentException e) {
            responseObserver.onError(Status.INVALID_ARGUMENT.withDescription("Invalid ID format").asRuntimeException());
        } catch (Exception e) {
            responseObserver.onError(Status.INTERNAL.withDescription("Error retrieving user").asRuntimeException());
        }
        responseObserver.onCompleted();
    }
}
