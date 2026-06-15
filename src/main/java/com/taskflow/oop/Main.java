package com.taskflow.oop;

public class Main {
    public static void main(String[] args){
        User user = new User("Sanjali");
        Employee manager = new Manager();
        manager.work();
        Notification notification = new EmailNotification();
        notification.send();
        Payment payment = new CardPayment();
        payment.pay();
        System.out.println(user.getName());
    }
}
