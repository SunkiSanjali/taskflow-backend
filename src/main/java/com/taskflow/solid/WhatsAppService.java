package com.taskflow.solid;

public class WhatsAppService implements MessageService{
    @Override
    public void send() {
        System.out.println("WhatsApp Sent");
    }
}
