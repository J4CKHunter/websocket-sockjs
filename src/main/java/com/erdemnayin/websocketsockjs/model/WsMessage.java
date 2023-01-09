package com.erdemnayin.websocketsockjs.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class WsMessage {

    private String sender;
    private String content;
}
