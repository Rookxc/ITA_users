package com.example;

import com.example.CreateUserRequest;
import com.example.GetUserRequest;
import com.example.UserResponse;
import com.example.model.User;
import com.example.repository.UserRepository;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    public void setup() {

    }

    @Test
    void testCreateUser_Success() {


    }

    @Test
    void testCreateUser_Failure() {

    }

    @Test
    void testGetUser_Success() {

    }
}
