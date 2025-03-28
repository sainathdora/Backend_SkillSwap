package com.example.securitydemo.Entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Chat")
public class Chat {
    @Id
    private String id;

}
