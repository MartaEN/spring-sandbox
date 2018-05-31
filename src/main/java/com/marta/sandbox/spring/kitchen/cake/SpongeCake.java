package com.marta.sandbox.spring.kitchen.cake;

import com.marta.sandbox.spring.kitchen.filler.Filler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class SpongeCake implements Cake {

    @Autowired
    @Qualifier("filler")
    private Filler filler;

    public void beEaten() {
        System.out.println("Here's your sponge cake - bon appetite!");
        filler.drop();
    }
}
