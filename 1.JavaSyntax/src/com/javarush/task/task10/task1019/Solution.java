package com.javarush.task.task10.task1019;

import java.util.*;
import java.io.*;

/* 
Функциональности маловато!
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        HashMap<String, Integer> map = new HashMap<>();

        int id;
        String name, s;
        while(!(s = reader.readLine()).isEmpty())
        {
            id = Integer.parseInt(s);
            name = reader.readLine();

            map.put(name, id);
        }
        for(Map.Entry<String, Integer> c : map.entrySet())
            System.out.println(c.getValue() + " " + c.getKey());
    }
}
