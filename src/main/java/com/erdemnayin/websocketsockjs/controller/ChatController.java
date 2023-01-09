package com.erdemnayin.websocketsockjs.controller;


import com.erdemnayin.websocketsockjs.model.WsMessage;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.NestedExceptionUtils;
import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

@Controller
@CrossOrigin
@Log4j2
public class ChatController {

    // @SentTo'lara 3. alternatif, bununla da mesajı özelleştirip gönderebiliyoruz
    private SimpMessagingTemplate messagingTemplate;

    public ChatController(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @MessageMapping("/chat")
//    @SendTo("/topic")  // herkese gönderir mesajları, parametre olarak kanalı veririz
//    @SendToUser()  // belli bir channel'daki belli bir user'a göndeririz. broadcast ise herkese gönderiyor
    public void chatEndpoint(@Payload WsMessage wsMessage){

        log.info(wsMessage);
        messagingTemplate.convertAndSend("/topic", wsMessage);
    }

    @MessageExceptionHandler
    @SendTo("/topic/errors")
    public String handleException(Exception e){
        var message = "Something went wrong processing the request" + NestedExceptionUtils.getMostSpecificCause(e);
        log.info(message);
        return message;
    }
}
