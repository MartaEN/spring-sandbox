package com.marta.sandbox.spring.kitchen.filler;

import com.marta.sandbox.spring.kitchen.chef.OutOfStock;
import org.springframework.stereotype.Component;

@Component("filler")
@OutOfStock(replaceWithClass = Jam.class)
public class SourCream implements Filler {

    public void drop() {
        System.out.println("Ooops... That was sour cream... No worries, I can wash it quickly...");
    }
}
