package xyz.ziggornif.chatbot;

import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/")
public class UIEndpointsResource {
    @Inject
    Template index;

    @Inject
    Template imgen;

    @Inject
    Template ocr;


    @GET
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance getHomePage() {
        return index.instance();
    }

    @GET
    @Path("image-creator")
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance getImageGenPage() {
        return imgen.instance();
    }

    @GET
    @Path("ocr")
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance getOCRPage() {
        return ocr.instance();
    }
}
