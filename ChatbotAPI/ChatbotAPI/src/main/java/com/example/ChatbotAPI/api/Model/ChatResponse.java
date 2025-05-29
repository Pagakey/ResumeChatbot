package com.example.ChatbotAPI.api.Model;


//will be sending data back to frontend
public class ChatResponse {
    private String chat_response;

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
