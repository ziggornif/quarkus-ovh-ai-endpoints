package xyz.ziggornif.chatbot.service;

import dev.langchain4j.service.MemoryId;
import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import io.quarkiverse.langchain4j.RegisterAiService;
import io.smallrye.mutiny.Multi;
import jakarta.inject.Singleton;

import java.util.UUID;

@Singleton
@RegisterAiService
public interface ChatBotService {
    // Scope / context passed to the LLM
    @SystemMessage("You are a virtual, an AI assistant.")
    // Prompt (with detailed instructions and variable section) passed to the LLM
    @UserMessage("Answer as best possible to the following question: {question}. The answer must be in a style of a virtual assistant and add some emojis to make the answer more fun.")
    Multi<String> askAQuestion(@MemoryId UUID memoryId, String question);
}