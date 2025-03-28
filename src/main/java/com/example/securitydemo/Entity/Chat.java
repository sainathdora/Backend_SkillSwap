package com.example.securitydemo.Entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

class Message{
    public String message;
    public User sender;

    public Message(String message, User sender) {
        this.message = message;
        this.sender = sender;
    }

    public String getMessage() {
        return message;
    }

    public User getSender() {
        return sender;
    }
}

@Document(collection = "Chat")
public class Chat {
    @Id
    private String id;
    private List<User> users;
    private List<Message>messages;

    public Chat(String id, List<User> users) {
        this.id = id;
        this.users = users;

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }
}
