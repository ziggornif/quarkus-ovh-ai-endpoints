package xyz.ziggornif.chatbot;

import jakarta.inject.Inject;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import xyz.ziggornif.chatbot.model.GenerateImage;
import xyz.ziggornif.chatbot.service.ChatBotService;
import xyz.ziggornif.chatbot.service.StableDiffusionService;

@Path("ai-assistant")
public class AIEndpointsResource {
    // AI Service injection to use it later
    @Inject
    ChatBotService chatBotService;

    @Inject
    @RestClient
    StableDiffusionService stableDiffusionService;


    @POST
    @Path("ask")
    public String ask(String question) {
        return chatBotService.askAQuestion(question);
    }

    @POST
    @Path("generate-image")
    @Produces("image/png")
    public Response generateImage(GenerateImage query) {
        byte[] image = stableDiffusionService.textToImage(query);

        return Response
                .ok(image)
                .type("image/png")
                .build();
    }
}