package com.krekerok.service;

import com.krekerok.entity.Message;
import com.krekerok.model.MessageModel;

import java.util.List;

public interface MessageService {

    void saveMessage(MessageModel messageModel);

    List<Message> findAllMessagesByReceiver(String receiver);
}
