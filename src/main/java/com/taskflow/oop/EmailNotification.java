package com.taskflow.oop;

public class EmailNotification extends Notification{
    @Override
    public void send(){

        System.out.println("Email Sent");
    }
}
