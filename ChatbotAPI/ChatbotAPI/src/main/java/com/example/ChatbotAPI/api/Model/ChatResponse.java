package com.example.ChatbotAPI.api.Model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ChatResponse {
    // Tells Spring Boot to use exactly chat_response as the JSON filed name
    // instead of converting getChatResponse() to chatResponse.
    @JsonProperty("chat_response")
    private String chat_response;

    // Default constructor (good practice)
    public ChatResponse() {

    }

    public String getChatResponse() {
        return chat_response;
    }

    public void setChatResponse(String chat_response){
        this.chat_response = chat_response;
    }

    public ChatResponse(String chat_response) {
        setChatResponse(chat_response);
    }
}