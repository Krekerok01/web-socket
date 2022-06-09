package com.krekerok.model;

import lombok.Builder;
import lombok.Getter;

@Builder
public class ChatMessage {

    @Getter
    private  MessageType type;

    @Getter
    private String content;

    @Getter
    private String sender;

    @Getter
    private String receiver;

    @Getter
    private String title;

    @Getter
    private String time;


    @Override
    public String toString() {
        return "ChatMessage{" +
                "type=" + type +
                ", content='" + content + '\'' +
                ", sender='" + sender + '\'' +
                ", receiver='" + receiver + '\'' +
                ", title='" + title + '\'' +
                ", time='" + time + '\'' +
                '}';
    }
}
