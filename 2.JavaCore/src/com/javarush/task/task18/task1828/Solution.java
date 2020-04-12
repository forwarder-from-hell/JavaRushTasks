package com.javarush.task.task18.task1828;

import java.io.*;
import java.util.LinkedHashMap;
import java.util.Map;

public class Solution
{
    public static void main(String[] args) throws Exception
    {
        if (args.length == 0) return;

        LinkedHashMap<String, String> dataFromFile = new LinkedHashMap<>();

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String s = reader.readLine();
        reader.close();

        BufferedReader inputFile = new BufferedReader(new InputStreamReader(new FileInputStream(s)));

        while (inputFile.ready())
        {
            String stringFromFile = inputFile.readLine();
            if (stringFromFile.equals(""))
                continue;

            String idInFile;
            idInFile = stringFromFile.substring(0, 8);
            idInFile = idInFile.replaceAll("\\s+", "");

            dataFromFile.put(idInFile, stringFromFile);
        }

        BufferedWriter outputFile = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(s)));

        switch (args[0])
        {
            case "-u":
                String productInfo = productInfoCreator(args);
                replaceById(dataFromFile, args[1], productInfo);

                for (Map.Entry<String, String> map : dataFromFile.entrySet()) {
                    outputFile.write(map.getValue());
                    outputFile.newLine();
                }

                inputFile.close();
                outputFile.close();
                break;
            case "-d":
                deleteById(dataFromFile, args[1]);

                for (Map.Entry<String, String> map : dataFromFile.entrySet()) {
                    outputFile.write(map.getValue());
                    outputFile.newLine();
                }

                inputFile.close();
                outputFile.close();
                break;
            default:
                inputFile.close();
                outputFile.close();
                throw new IllegalArgumentException("Неверный параметр выполнения операции. Попробуйте снова");

        }
    }

    public static String floatChecker(String s)
    {
        if(!s.contains(".")) s += ".00";

        return s;
    }

    public static String productNameSearcher(String[] arr, int from)
    {
        StringBuilder productName = new StringBuilder();
        for(; from < arr.length - 2; from++)
        {
            productName.append(arr[from]);
            if(from < arr.length - 3)
                productName.append(" ");
        }

        return productName.toString();
    }

    public static void replaceById(LinkedHashMap<String, String> data, String id, String target)
    {
        for (Map.Entry<String, String> nextString : data.entrySet()) {
            String key = nextString.getKey();

            if (key.equals(id))
                data.put(id, target);
        }
    }

    public static void deleteById(LinkedHashMap<String, String> data, String id)
    {
        LinkedHashMap<String, String> map = new LinkedHashMap<>(data);
        for (Map.Entry<String, String> nextString : map.entrySet()) {
            String key = nextString.getKey();

            if (key.equals(id))
                data.remove(id);
        }
    }

    public static String productInfoCreator(String[] args)
    {
        String id = lengthPatternBuilder(args[1], 8);
        if(args.length > 2)
        {

            String productName = lengthPatternBuilder(productNameSearcher(args, 2), 30);
            String price = lengthPatternBuilder(floatChecker(args[args.length - 2]), 8);
            String quantity = lengthPatternBuilder(args[args.length - 1], 4);

            System.out.println(id + productName + price + quantity);
            return id + productName + price + quantity;
        }

        return id;
    }

    public static String lengthPatternBuilder(String arg, int lengthTo)
    {
        StringBuilder s = new StringBuilder(arg);
        if (s.length() > lengthTo)
            return s.substring(0, lengthTo);
        else if (arg.length() == lengthTo)
            return arg;
        else
            while(s.length() < lengthTo)
                s.append(" ");

        return s.toString();
    }
}