package com.javarush.task.task16.task1632;

import java.util.ArrayList;
import java.util.List;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;

public class Solution {
    public static List<Thread> threads = new ArrayList<>(5);

    static {
        threads.add(new Thread(new Thread1()));
        threads.add(new Thread(new Thread2()));
        threads.add(new Thread(new Thread3()));
        threads.add(new Thread4());
        threads.add(new Thread(new Thread5()));
    }

    public static void main(String[] args) {
        threads.get(3).start();
        Thread4 s = (Thread4) threads.get(3);
        s.showWarning();
    }
    
    public static class Thread1 extends Thread {
        @Override
        public void run() {
            while(true) {
            }
        }
    }
    
    public static class Thread2 extends Thread {
        @Override
        public void run() {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                System.out.println("InterruptedException");
            }
        }
    }
    
    public static class Thread3 extends Thread {
       @Override
        public void run() {
            try {
                while(true) {
                    System.out.println("Ура");
                    Thread.sleep(500);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    
    public static class Thread4 extends Thread implements Message {
        @Override
        public void run() {
            while (true) {
                if(isInterrupted())
                    break;
            }
        }
        
        @Override
        public void showWarning() {
            if(isAlive()) {
                try {
                    interrupt();
                    join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    public static class Thread5 extends Thread {
        @Override
        public void run() {
            int sumOfNumbers = 0;
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
                String s;
                while (!(s = reader.readLine()).equals("N")) {
                    sumOfNumbers += Integer.parseInt(s);
                }
            } catch (IOException | NumberFormatException e) {
                e.printStackTrace();
            }

            System.out.println(sumOfNumbers);
        }
    }
}