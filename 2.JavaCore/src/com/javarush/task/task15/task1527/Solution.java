package com.javarush.task.task15.task1527;

import java.io.*;

/* 
Парсер реквестов
*/

public class Solution
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String urlWithParameters = reader.readLine();

        String info = urlWithParameters.substring(urlWithParameters.indexOf("?") + 1);
        String[] parameters = info.split("&");
        int indexOfObj = -1;

        for(int i = 0; i < parameters.length; i++)
        {
            String parameterName = parameters[i].replaceAll("=.*", "");
            System.out.print(parameterName);
            if(i < parameters.length - 1)
                System.out.print(" ");
            else
                System.out.println();
            if(parameterName.contains("obj"))
                indexOfObj = i;
        }

        if(indexOfObj > -1)
        {
            String objValue = parameters[indexOfObj].replaceAll(".*=", "");
            if(isNumber(objValue))
                alert(Double.parseDouble(objValue));
            else
                alert(objValue);
        }

    }

    public static void alert(double value)
    {
        System.out.println("double: " + value);
    }

    public static void alert(String value)
    {
        System.out.println("String: " + value);
    }

    public static boolean isNumber(String s)
    {
        String justDigits = s.replaceAll("[^0-9-.]", "");

        return justDigits.length() == s.length();
    }
}
