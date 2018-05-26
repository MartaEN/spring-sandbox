package com.marta.sandbox.springSandbox.kitchen.cake;

import com.marta.sandbox.springSandbox.kitchen.filler.Filler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component ("cake")
public class Pancake implements Cake {

    @Autowired
    @Qualifier("filler")
    private Filler filler;

    public void beEaten () {
        System.out.println("Here's your pancake - bon appetit!");
        filler.drop();
    }
}
