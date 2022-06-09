package com.krekerok.controller;

import com.krekerok.entity.Message;
import com.krekerok.entity.User;
//import com.krekerok.model.ChatMessage;
import com.krekerok.model.ChatMessage;
import com.krekerok.model.MessageModel;
import com.krekerok.service.MessageService;
import com.krekerok.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@Controller
public class ChatController {

    @Autowired
    private UserService userService;

    @Autowired
    private MessageService messageService;

    @MessageMapping("/chat.send")
    @SendTo("/topic/public")
    public ChatMessage sendMessage(@Payload final ChatMessage chatMessage){

        System.out.println("----- " + chatMessage.toString());
        System.out.println("***** Sender - " + chatMessage.getSender() + " ***** Title - " + chatMessage.getTitle());

        String username = chatMessage.getSender();


        // добавляем в БД сообщения
        MessageModel messageModel = new MessageModel(chatMessage.getTitle(), chatMessage.getContent(), username, chatMessage.getReceiver());

        messageService.saveMessage(messageModel);


        return chatMessage;
    }


    @MessageMapping("/chat.newUser")
    @SendTo("/topic/public")
    public ChatMessage newUser(@Payload final ChatMessage chatMessage,
                               SimpMessageHeaderAccessor headerAccessor){

        String username = chatMessage.getSender();


        // в этом месте необходимо добавлять юзера в БД
        User user = userService.findByUsername(username);
        if (user == null){
            userService.saveUser(username);
        }


        headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());

        List<String> usernames = userService.getAllUsernames();


        return chatMessage;
    }

}
