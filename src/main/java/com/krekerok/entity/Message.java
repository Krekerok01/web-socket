package com.krekerok.entity;

import lombok.*;

import javax.persistence.*;


@Entity
@Table(name = "message")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "topic")
    private String title;

    @Column(name = "message_text")
    private String messageText;


    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "sender_id", referencedColumnName = "user_id")
    private User sender;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "receiver_id", referencedColumnName = "user_id")
    private User receiver;

    @Column(name = "send_date")
    private String sendDate;


}
