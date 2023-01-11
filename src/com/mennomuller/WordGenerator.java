package com.mennomuller;

import java.util.Random;
import java.util.concurrent.Callable;

public class WordGenerator implements Callable<Integer> {
    private final String target;
    private int attempts;
    private final Random random = new Random();

    public WordGenerator(String string) {
        this.target = string;
    }

    @Override
    public Integer call() throws Exception {
        attempts = 0;
        String word;
        do{
            word = generateWord();
        } while (!word.equals(target));
        return attempts;
    }

    public String generateWord(){
        StringBuilder word = new StringBuilder();
        for(int i=0;i<target.length();i++){
            word.append ((char)('a' + random.nextInt(26)));
        }
        attempts++;
        return word.toString();
    }

}
