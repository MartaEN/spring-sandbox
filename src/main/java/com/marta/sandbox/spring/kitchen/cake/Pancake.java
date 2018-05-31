package com.marta.sandbox.spring.kitchen.cake;

import com.marta.sandbox.spring.kitchen.chef.OutOfStock;
import com.marta.sandbox.spring.kitchen.filler.Filler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component ("cake")
@OutOfStock(replaceWithClass = SpongeCake.class)
public class Pancake implements Cake {

    @Autowired
    @Qualifier("filler")
    private Filler filler;

    public void beEaten () {
        System.out.println("Here's your pancake - bon appetite!");
        filler.drop();
    }
}
