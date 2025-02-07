package xyz.ziggornif.chatbot.service;

import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import org.eclipse.microprofile.rest.client.annotation.ClientHeaderParam;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import xyz.ziggornif.chatbot.model.GenerateImage;

@RegisterRestClient
public interface StableDiffusionService {
    @POST
    @Path("/text2image")
    @ClientHeaderParam(name = "Authorization", value = "${stable-diffusion-client.bearer}")
    byte[] textToImage(GenerateImage query);
}
