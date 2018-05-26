package com.marta.sandbox.springSandbox;

import com.marta.sandbox.springSandbox.kitchen.cake.Cake;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Client {

    public static void main(String[] args) {

        ApplicationContext context=new AnnotationConfigApplicationContext(com.marta.sandbox.springSandbox.config.AppConfig.class);

        Cake cake = context.getBean("cake",Cake.class);
        cake.beEaten();

    }
}
