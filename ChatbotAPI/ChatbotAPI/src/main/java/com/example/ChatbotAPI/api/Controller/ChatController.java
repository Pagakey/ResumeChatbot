package com.example.ChatbotAPI.api.Controller;

import com.example.ChatbotAPI.api.Model.ChatRequest;
import com.example.ChatbotAPI.api.Model.ChatResponse;
import com.example.ChatbotAPI.service.ChatService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class ChatController {

    private final ChatService chatService;

    // Constructor injection for ChatService
    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @PostMapping("/chat")
    public ChatResponse chat(@RequestBody ChatRequest request) {
        //debugging
        //System.out.println("Received request: " + request.getUserInput());

        String userInput = request.getUserInput();
        String botResponse = chatService.processMessage(userInput);
        //debugging
        //System.out.println("Sending response: " + botResponse); //

        return new ChatResponse(botResponse);
    }
}