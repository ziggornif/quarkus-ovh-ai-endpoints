package xyz.ziggornif.chatbot;

import io.smallrye.mutiny.Multi;
import jakarta.inject.Inject;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import xyz.ziggornif.chatbot.model.AskQuery;
import xyz.ziggornif.chatbot.model.GenerateImageQuery;
import xyz.ziggornif.chatbot.service.ChatBotService;
import xyz.ziggornif.chatbot.service.StableDiffusionService;

import java.nio.charset.StandardCharsets;

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
    public Multi<String> ask(AskQuery query) {
        return chatBotService.askAQuestion(query.question()).map(str -> new String(str.getBytes(StandardCharsets.UTF_8), StandardCharsets.UTF_8));
    }

    @POST
    @Path("generate-image")
    @Produces("image/png")
    public Response generateImage(GenerateImageQuery query) {
        byte[] image = stableDiffusionService.textToImage(query);

        return Response
                .ok(image)
                .type("image/png")
                .build();
    }
}