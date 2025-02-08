package xyz.ziggornif.chatbot.model;

import xyz.ziggornif.chatbot.model.ocr.Choice;

import java.util.List;

public record OCRResponse(List<Choice> choices) {
}
