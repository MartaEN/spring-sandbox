package com.marta.sandbox.spring;

import com.marta.sandbox.spring.kitchen.cake.Cake;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Client {

    public static void main(String[] args) {

        ApplicationContext context=new AnnotationConfigApplicationContext(com.marta.sandbox.spring.config.AppConfig.class);

        System.out.println("Hi there! May I have please some cake with some fancy filler? Pancake with sour cream would be great...");

        Cake cake = context.getBean("cake",Cake.class);
        cake.beEaten();

    }
}
