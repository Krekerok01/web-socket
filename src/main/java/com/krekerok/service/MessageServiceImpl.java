package com.krekerok.service;

import com.krekerok.entity.Message;
import com.krekerok.entity.User;
import com.krekerok.model.MessageModel;
import com.krekerok.repository.MessageRepository;
import com.krekerok.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;


@Service
public class MessageServiceImpl implements MessageService {

    private final MessageRepository messageRepository;
    private final UserRepository userRepository;
    private final UserServiceImpl userService;

    @Autowired
    public MessageServiceImpl(MessageRepository messageRepository, UserRepository userRepository, UserServiceImpl userService) {
        this.messageRepository = messageRepository;
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @Override
    public void saveMessage(MessageModel messageModel) {

        User sender = userRepository.findByUsername(messageModel.getSender()).get();

        Optional<User> optReceiver = userRepository.findByUsername(messageModel.getReceiver());
        User receiver = optReceiver.orElseGet(() -> userService.saveUser(messageModel.getReceiver()));

        Message message = new Message();
        message.setTitle(messageModel.getTitle());
        message.setMessageText(messageModel.getBody());
        message.setSender(sender);
        message.setReceiver(receiver);
        message.setSendDate(new Date().toString());

        messageRepository.save(message);
    }

    @Override
    public List<Message> findAllMessagesByReceiver(String receiver) {
        Optional<User> optReceiver = userRepository.findByUsername(receiver);
        return !(optReceiver.isPresent()) ? new LinkedList<>() : messageRepository.findAllByReceiver(optReceiver.get());
    }

}
