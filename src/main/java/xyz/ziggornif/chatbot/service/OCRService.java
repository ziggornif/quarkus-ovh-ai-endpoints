package xyz.ziggornif.chatbot.service;

import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import org.eclipse.microprofile.rest.client.annotation.ClientHeaderParam;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import xyz.ziggornif.chatbot.model.OCRQuery;

@RegisterRestClient(configKey = "ocr-service")
public interface OCRService {
    @POST
    @Path("/completions")
    @ClientHeaderParam(name = "Authorization", value = "${llava-client.bearer}")
    String extractTextFromImage(OCRQuery query);
}
