package xyz.ziggornif.chatbot.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import xyz.ziggornif.chatbot.model.ocr.Content;
import xyz.ziggornif.chatbot.model.ocr.ImageURL;
import xyz.ziggornif.chatbot.model.ocr.Message;

import java.util.ArrayList;
import java.util.List;

public class OCRQuery {
    String model;
    double temperature;
    @JsonProperty("max_tokens")
    int maxTokens;
    List<Message> messages;

    public OCRQuery(String encodedImage) {
        this.model = "llava-next-mistral-7b";
        this.temperature = 0.2;
        this.maxTokens = 512;

        ImageURL imageURL = new ImageURL(encodedImage);
        Content text = new Content("text", "Extract the text written in the image.");
        Content image = new Content("image_url", imageURL);
        List<Content> content = new ArrayList<>();
        content.add(text);
        content.add(image);

        Message message = new Message(content);
        this.messages = new ArrayList<>();
        this.messages.add(message);
    }

    public String getModel() {
        return model;
    }

    public double getTemperature() {
        return temperature;
    }

    public int getMaxTokens() {
        return maxTokens;
    }

    public List<Message> getMessages() {
        return messages;
    }
}
