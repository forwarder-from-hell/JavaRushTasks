package com.javarush.task.task15.task1529;

import java.io.*;

/* 
Осваивание статического блока
*/

public class Solution {
    public static void main(String[] args) {

    }

    static {
        reset();
    }

    public static CanFly result;

    public static void reset() {
        //BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String typeOfPlane = null;
        
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            typeOfPlane = reader.readLine();

            if ("helicopter".equals(typeOfPlane)) {
                result = new Helicopter();
            } else if ("plane".equals(typeOfPlane)) {
                try {
                    int passangersNumber = Integer.parseInt(reader.readLine());
                    result = new Plane(passangersNumber);
                } catch (IOException | NumberFormatException e) {
                    e.printStackTrace();
                }
            } else
                throw new IllegalArgumentException("Неверный тип аргумента!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
