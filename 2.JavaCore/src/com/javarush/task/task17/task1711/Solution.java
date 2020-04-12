package com.javarush.task.task17.task1711;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/* 
CRUD 2
*/

public class Solution
{
    public static volatile List<Person> allPeople = new ArrayList<>();

    static
    {
        allPeople.add(Person.createMale("Иванов Иван", new Date()));  //сегодня родился    id=0
        allPeople.add(Person.createMale("Петров Петр", new Date()));  //сегодня родился    id=1
    }

    public static void main(String[] args)
    {
        if (args.length == 0)
            System.exit(0);

        String actionParameter   = args[0];
        Date birthDate           = null;
        StringBuilder fullName   = new StringBuilder();
        int indexFrom            = 1;
        SimpleDateFormat dFormat = new SimpleDateFormat("dd/MM/yyyy");

        Person person;

        switch (args[0])
        {
            case "-c":
                synchronized (allPeople)
                {
                    do 
                    {
                        indexFrom = nameSearcher(args, indexFrom, fullName);
                        try
                        {
                            birthDate = dFormat.parse(args[indexFrom]);
                        } catch (ParseException e)
                        {
                            e.printStackTrace();
                        }
                        person = args[indexFrom - 1].equals("м") ?
                                Person.createMale(fullName.toString(), birthDate) :
                                Person.createFemale(fullName.toString(), birthDate);

                        allPeople.add(person);
                        System.out.println(allPeople.size() - 1);

                        indexFrom++;
                    }
                    while (indexFrom < args.length - 1);
                }
                break;
            case "-u":
                synchronized (allPeople)
                {
                    do
                    {
                        person = allPeople.get(Integer.parseInt(args[indexFrom++]));
                        indexFrom = nameSearcher(args, indexFrom, fullName);
                        try
                        {
                            birthDate = dFormat.parse(args[indexFrom]);
                        } catch (ParseException e)
                        {
                            e.printStackTrace();
                        }
                        Sex sex = args[indexFrom - 1].equals("м") ?
                                Sex.MALE :
                                Sex.FEMALE;

                        person.setBirthDate(birthDate);
                        person.setName(fullName.toString());
                        person.setSex(sex);

                        indexFrom++;
                    }
                    while (indexFrom < args.length - 1);
                }
                break;
            case "-d":
                synchronized (allPeople)
                {
                    do
                    {
                        person = allPeople.get(Integer.parseInt(args[indexFrom]));

                        person.setBirthDate(null);
                        person.setName(null);
                        person.setSex(null);

                        indexFrom++;
                    }
                    while (indexFrom < args.length);
                }
                break;
            case "-i":
                synchronized (allPeople)
                {
                    for (int i = 1; i < args.length; i++)
                        System.out.println(allPeople.
                                get(Integer.parseInt(args[i])));
                }
                break;
        }
    }

    public static int nameSearcher(String[] arr, int indexFrom, StringBuilder fullName)
    {
        fullName.setLength(0);

        while(indexFrom < arr.length - 1)
        {
            fullName.append(arr[indexFrom]);
            indexFrom++;

            //если следующий элемент не пол, добавить пробел
            if (!arr[indexFrom].equals("м") &&
                    !arr[indexFrom].equals("ж"))
            {
                fullName.append(" ");
            }
            else
                break;
        }
        //возврат позиции для вычисления остаточных данных этого человека и начальных следующего
        return ++indexFrom;
    }
}