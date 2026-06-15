package com.taskflow.solid;

public class SmsService implements MessageService{
    @Override
    public void send() {
        System.out.println("SMS Sent");
    }
}
