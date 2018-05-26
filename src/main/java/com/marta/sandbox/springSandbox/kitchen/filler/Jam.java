package com.marta.sandbox.springSandbox.kitchen.filler;

import org.springframework.stereotype.Component;

@Component ("filler")
public class Jam implements Filler {

    public void drop() {
        System.out.println("Ooops... That was blueberry jam... Sorry, it's non-washable...");
    }
}
