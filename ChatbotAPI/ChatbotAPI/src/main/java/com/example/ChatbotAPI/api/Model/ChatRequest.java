package com.example.ChatbotAPI.api.Model;

import com.fasterxml.jackson.annotation.JsonProperty;

//frontend sends data to backend through this class.
public class ChatRequest {
    @JsonProperty("user_input")
    private String user_input;

    // Default constructor (required for JSON deserialization)
    public ChatRequest() {
    }

    public String getUserInput() {
        return user_input;
    }

    public void setUserInput(String user_input){
        this.user_input = user_input;
    }

    public ChatRequest(String user_input) {
        setUserInput(user_input);
    }
}