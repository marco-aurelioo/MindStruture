package com.tiozao.services;

import com.tiozao.entities.FriendListEntity;
import com.tiozao.entities.MessageEntity;
import com.tiozao.entities.UserEntity;
import com.tiozao.model.FriendList;
import com.tiozao.model.UserModel;
import com.tiozao.repositories.FriendListRepository;
import com.tiozao.repositories.MessageRepository;
import com.tiozao.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;

@Service
public class FriendsService {

    @Autowired
    private FriendListRepository repository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MessageRepository msgRepository;

    @Autowired
    private JavaMailSender emailSender;

    public FriendListEntity findMyFriends(UserModel sessionUser) {
        FriendListEntity byUser = repository.findByUser(userRepository.findByEmail(sessionUser.getEmail()));


        return byUser;
    }

    public List<MessageEntity> findMyUnreadMsgs(UserModel sessionUser){
       return msgRepository.findByToAndReaded(sessionUser.getEmail(),false);
    }

    public void solicitacaoAmizade(String email, UserModel sessionUser) {
        try {
            UserEntity fromUser = userRepository.findByEmail(sessionUser.getEmail());
            MessageEntity msg = new MessageEntity();
            msg.setFromUser(fromUser);
            msg.setTo(email);
            msg.setReaded(false);
            msg = msgRepository.save(msg);
            String link = "http://localhost:8080/amigos/confirmacao?from=" + fromUser.getId()+"&msgId="+msg.getId();
            msg.setMsg(String.format("solicitação de amizade de %s acesso o link %s", sessionUser.getName(), link));
            msgRepository.save(msg);

            sendMessageExternal(msg);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    private void sendMessageExternal(MessageEntity msg) {
        UserEntity para = userRepository.findByEmail(msg.getTo());
        if(para == null){
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("nao-responda@mailinator.com");
            message.setTo(msg.getTo());
            message.setSubject("solicitacao amizade na plataforma");
            message.setText(msg.getMsg());
            emailSender.send(message);
        }
    }

    public MessageEntity verificaConfirmacaoAmizade(UUID msgId, UUID from, UserModel sessionUser) {
        MessageEntity msg = msgRepository.findById(msgId).get();
        if(msg.getTo().equals(sessionUser.getEmail()) && msg.getFromUser().getId().equals(from)){
            return msg;
        }else{
            return null;//invalido
        }
    }

    public void confirmacaoAmizade(UUID msgId, UserModel sessionUser) {
        MessageEntity msg = msgRepository.findById( msgId ).get();
        UserEntity user = userRepository.findByEmail(sessionUser.getEmail());
        UserEntity userFrom = msg.getFromUser();
        addFriendUser(user,userFrom);
        addFriendUser(userFrom,user);
        msg.setReaded(true);
        msgRepository.save(msg);
    }

    private void addFriendUser(UserEntity user, UserEntity friend){
        FriendListEntity byUser = repository.findByUser(user);
        if(byUser == null){
            byUser = new FriendListEntity();
            byUser.setUser(user);
        }
        if(byUser.getFriends() == null){
            byUser.setFriends(new HashSet<>());
        }
        byUser.getFriends().add(friend);
        repository.save(byUser);
    }
}
