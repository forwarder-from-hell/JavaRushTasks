package com.javarush.task.task17.task1710;

import java.text.SimpleDateFormat;
import java.text.ParseException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/* 
CRUD
*/

public class Solution
{
    public static List<Person> allPeople = new ArrayList<Person>();

    static
    {
        allPeople.add(Person.createMale("Иванов Иван", new Date()));  //сегодня родился    id=0
        allPeople.add(Person.createMale("Петров Петр", new Date()));  //сегодня родился    id=1
    }

    public static void main(String[] args)
    {
        if (args.length == 0)
            System.exit(0);
            
        String parameterFromConsole = args[0];
        int sexPositionInArray      = args.length - 2;
        int bdPositionInArray       = args.length - 1;
        SimpleDateFormat toDate     = new SimpleDateFormat("dd/MM/yyyy");
        
        Person person    = null;
        String name      = null;
        Date   birthDate = null;
        
        switch (parameterFromConsole)
        {
            case "-c":
                name = nameSearcher(args, 1);
                
                try
                {
                    birthDate = toDate.parse(args[bdPositionInArray]);
                }
                catch (ParseException e)
                {
                    e.printStackTrace();
                }
                
                person = args[sexPositionInArray].equals("м") ? 
                                 Person.createMale(name, birthDate)   :
                                 Person.createFemale(name, birthDate) ;
                allPeople.add(person);
                System.out.println(allPeople.indexOf(person));
                break;
            case "-u":
                person = allPeople.get(Integer.parseInt(args[1]));
                name   = nameSearcher(args, 2);
                
                try
                {
                    birthDate = toDate.parse(args[bdPositionInArray]);
                }
                catch (ParseException e)
                {
                    e.printStackTrace();
                }
                
                Sex sex = args[sexPositionInArray].equals("м") ? 
                          Sex.MALE                             :
                          Sex.FEMALE                           ;
                
                person.setBirthDate(birthDate);
                person.setName(name);
                person.setSex(sex);
                break;
            case "-d":
                person = allPeople.get(Integer.parseInt(args[1]));
                person.setName(null);
                person.setSex(null);
                person.setBirthDate(null);
                break;
            case "-i":
                person = allPeople.get(Integer.parseInt(args[1]));
                System.out.println(person);
                
        }
    }
    
    public static String nameSearcher(String[] arr, int indexFrom)
    {
        StringBuilder fullName = new StringBuilder();
        
        for (; indexFrom < arr.length - 2; indexFrom++)
        {
            fullName.append(arr[indexFrom]);
            if (indexFrom < arr.length - 3)
                fullName.append(" ");
        }
        return fullName.toString();
    }
}
