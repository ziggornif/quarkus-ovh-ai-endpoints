package xyz.ziggornif.chatbot;

import jakarta.inject.Inject;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import xyz.ziggornif.chatbot.services.ChatBotService;

// Endpoint root path
@Path("chatbot")
public class AIEndpointsResource {
    // AI Service injection to use it later
    @Inject
    ChatBotService chatBotService;

    // ask resource exposition with POST method
    @Path("ask")
    @POST
    public String ask(String question) {
        // Call the Mistral AI chat model
        return  chatBotService.askAQuestion(question);
    }

}