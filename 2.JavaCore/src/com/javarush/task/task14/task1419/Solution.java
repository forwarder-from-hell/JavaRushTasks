package com.javarush.task.task14.task1419;

import java.util.*;
import java.text.*;
import java.io.*;

/* 
Нашествие исключений
*/

public class Solution {
    public static List<Exception> exceptions = new ArrayList<Exception>();

    public static void main(String[] args) {
        initExceptions();

        for (Exception exception : exceptions) {
            System.out.println(exception);
        }
    }

    private static void initExceptions() {   //the first exception
        try
        {
            float i = 1 / 0;

        } catch (ArithmeticException e)
        {
            exceptions.add(e);
        }

        try
        {
            new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH).parse("shit");
        } catch (ParseException e)
        {
            exceptions.add(e);
        }
        
        try
        {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            reader.close();
            reader.readLine();
        } catch (IOException e)
        {
            exceptions.add(e);
        }
        
        try
        {
            new FileInputStream("shit");
        } catch (FileNotFoundException e)
        {
            exceptions.add(e);
        }
        
        try
        {
            int[] a = new int[1];
            a[2] = 6;
        } catch (ArrayIndexOutOfBoundsException e)
        {
            exceptions.add(e);
        }
        
        try
        {
            String s = null;
            s.length();
        } catch (NullPointerException e)
        {
            exceptions.add(e);
        }
        
        try
        {
            Object a = new Solution();
            String s = (String) a;
        } catch (ClassCastException e)
        {
            exceptions.add(e);
        }
        
        exceptions.add(new IllegalArgumentException());
        exceptions.add(new NoSuchElementException());
        exceptions.add(new Exception());

    }
}
