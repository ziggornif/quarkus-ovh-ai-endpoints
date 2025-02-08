package xyz.ziggornif.chatbot.model.ocr;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Content {
    public String type;
    public String text;
    @JsonProperty("image_url")
    public ImageURL imageURL;

    public Content(String type, String text) {
        this.type = type;
        this.text = text;
    }

    public Content(String type, ImageURL imageURL) {
        this.type = type;
        this.imageURL = imageURL;
    }

    public String getType() {
        return type;
    }

    public String getText() {
        return text;
    }

    public ImageURL getImageURL() {
        return imageURL;
    }
}
