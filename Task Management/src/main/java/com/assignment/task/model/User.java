package com.assignment.task.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
@Data
@Document(collection = "users")
public class User {
    @Id
    private String userId;
    @Indexed(unique = true)
    private String username;
    private String fullName;
    private String email;
    private String password;
    private String phoneNumber;
}

