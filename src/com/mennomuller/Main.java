package com.mennomuller;

import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main extends Thread {
    String myWord;

    public Main(String str) {
        myWord = str;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        while (true){
            Main main = new Main(input.nextLine().toLowerCase());
            main.start();
        }

    }

    @Override
    public void run() {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        long timeBefore = System.currentTimeMillis();
        Future<Integer> future = executor.submit(new WordGenerator(myWord));
        int attempts = 0;
        try {
            attempts = future.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        long timeAfter = System.currentTimeMillis();
        System.out.println(myWord + " generated\n" +
                attempts + " attempts\n" +
                "Time spent: " + (timeAfter - timeBefore) + " ms");
        executor.shutdown();
    }
}
