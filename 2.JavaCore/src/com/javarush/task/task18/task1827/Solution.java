package com.javarush.task.task18.task1827;

import java.io.*;
import java.util.LinkedHashMap;
import java.util.Map;

/* 
Прайсы
*/

public class Solution
{
    public static void main(String[] args) throws Exception
    {
        if(args == null || args.length == 0) return;

        LinkedHashMap<String, String> dataFromFile = new LinkedHashMap<>();

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String s = reader.readLine(); reader.close();

        BufferedReader inputFile = new BufferedReader(new InputStreamReader(new FileInputStream(s)));

        String productName;
        String id;
        String price;
        String quantity;
        int argumentCounter = 1;

        while(inputFile.ready())
        {
            String stringFromFile = inputFile.readLine();
            if(stringFromFile.equals("")) continue;

            String idInFile       = null;
                   idInFile       = stringFromFile.substring(0, 8);
                   idInFile       = idInFile.replaceAll("\\s+", "");

            dataFromFile.put(idInFile, stringFromFile);
        }

        BufferedOutputStream outputFile = new BufferedOutputStream(new FileOutputStream(s));

        if (args[0].equals("-u"))
        {
            id          = args[argumentCounter++];
            productName = productNameSearcher(args, argumentCounter);
            price       = floatChecker(args[args.length - 2]);
            quantity    = args[args.length - 1];

            String productInfo = String.format("%-8s%-30s%-8s%-4s", id, productName, price, quantity);
            System.out.println(productInfo);
            idReplacer(dataFromFile, id, productInfo);

            for(Map.Entry<String, String> map: dataFromFile.entrySet())
            {
                outputFile.write(map.getValue().getBytes());
                outputFile.write('\n');
            }

            inputFile.close();
            outputFile.close();
        }
        else if (args[0].equals("-d"))
        {
            id = args[argumentCounter];

            idDeleter(dataFromFile, id);

            for(Map.Entry<String, String> map: dataFromFile.entrySet())
            {
                outputFile.write(map.getValue().getBytes());
                outputFile.write('\n');
            }

            inputFile.close();
            outputFile.close();
        }
        else
        {
            inputFile.close();
            outputFile.close();
            throw new IllegalArgumentException("Неверный параметр выполнения операции. Попробуйте снова");
        }
    }

    public static String floatChecker(String s)
    {
        if(!s.endsWith(".")) s += ".00";

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

    public static void idReplacer(LinkedHashMap<String, String> data, String id, String target)
    {
        for (Map.Entry<String, String> nextString : data.entrySet()) {
            String key = nextString.getKey();

            if (key.equals(id))
                data.put(id, target);
        }
    }

    public static void idDeleter(LinkedHashMap<String, String> data, String id)
    {
        LinkedHashMap<String, String> map = new LinkedHashMap<>(data);
        for (Map.Entry<String, String> nextString : map.entrySet()) {
            String key = nextString.getKey();

            if (key.equals(id))
                data.remove(id);
        }
    }
}