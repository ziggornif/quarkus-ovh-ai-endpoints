package xyz.ziggornif.chatbot.model.ocr;

import java.util.List;

public class Message {
    public String name;
    public String role;
    public List<Content> content;

    public Message(List<Content> content) {
        this.name = "User";
        this.role = "user";
        this.content = content;
    }

    public String getName() {
        return name;
    }

    public String getRole() {
        return role;
    }

    public List<Content> getContent() {
        return content;
    }
}
