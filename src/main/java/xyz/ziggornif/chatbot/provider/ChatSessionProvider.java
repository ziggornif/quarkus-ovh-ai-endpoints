package xyz.ziggornif.chatbot.provider;

import dev.langchain4j.memory.ChatMemory;
import dev.langchain4j.memory.chat.ChatMemoryProvider;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.store.memory.chat.InMemoryChatMemoryStore;

import java.util.function.Supplier;

public class ChatSessionProvider implements Supplier<ChatMemoryProvider> {

    private final InMemoryChatMemoryStore store = new InMemoryChatMemoryStore();

    @Override
    public ChatMemoryProvider get() {
        return new ChatMemoryProvider() {

            @Override
            public ChatMemory get(Object memoryId) {
                return MessageWindowChatMemory.builder()
                        .maxMessages(20)
                        .id(memoryId)
                        .chatMemoryStore(store)
                        .build();
            }
        };
    }
}
