package com.krekerok.repository;

import com.krekerok.entity.Message;
import com.krekerok.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Integer> {
    List<Message> findAllByReceiver(User receiver);
}
