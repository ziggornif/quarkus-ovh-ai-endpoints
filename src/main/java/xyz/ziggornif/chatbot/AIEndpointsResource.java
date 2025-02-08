package xyz.ziggornif.chatbot;

import io.smallrye.mutiny.Multi;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.resteasy.reactive.RestForm;
import xyz.ziggornif.chatbot.model.AskQuery;
import xyz.ziggornif.chatbot.model.GenerateImageQuery;
import xyz.ziggornif.chatbot.model.OCRQuery;
import xyz.ziggornif.chatbot.service.ChatBotService;
import xyz.ziggornif.chatbot.service.OCRService;
import xyz.ziggornif.chatbot.service.StableDiffusionService;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Base64;

@Path("ai-assistant")
public class AIEndpointsResource {
    // AI Service injection to use it later
    @Inject
    ChatBotService chatBotService;

    @Inject
    @RestClient
    StableDiffusionService stableDiffusionService;

    @Inject
    @RestClient
    OCRService ocrService;


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

    @POST
    @Path("ocr")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public String ocr(@RestForm File file) {
        try {
            byte[] imageData = Files.readAllBytes(file.toPath());
            String base64Image = Base64.getEncoder().encodeToString(imageData);
            OCRQuery query = new OCRQuery("data:;base64," + base64Image);
            return ocrService.extractTextFromImage(query);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}