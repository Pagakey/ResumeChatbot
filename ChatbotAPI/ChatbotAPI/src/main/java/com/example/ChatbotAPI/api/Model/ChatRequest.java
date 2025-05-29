package com.example.ChatbotAPI.api.Model;


//frontend sends data to backend through this class.
public class ChatRequest {
    private String user_input;


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
