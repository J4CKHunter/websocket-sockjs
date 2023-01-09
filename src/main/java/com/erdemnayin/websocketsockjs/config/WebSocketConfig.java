package com.erdemnayin.websocketsockjs.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {


        registry.addEndpoint("/chat") // böyle bir endpoint yayinliyorum, bunu sunucu dinler
                //.setAllowedOrigins("*")      // cross-origin yemek istemiyoruz, herkesten gelebilir
                .setAllowedOriginPatterns("*")
                .withSockJS();               // sockJS enable edilir

    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {

        // bu adresten mesajları dinleriz, bunu client dinler
        registry.enableSimpleBroker("/topic");
//        registry.setApplicationDestinationPrefixes("/app");
    }
}
