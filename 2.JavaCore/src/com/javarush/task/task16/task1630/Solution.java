package com.javarush.task.task16.task1630;

import java.io.*;

public class Solution
{
    public static String firstFileName;
    public static String secondFileName;

    static
    {
        try
        {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in)))
            {
                firstFileName = reader.readLine();
                secondFileName = reader.readLine();
            }
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException
    {
        systemOutPrintln(firstFileName);
        systemOutPrintln(secondFileName);
    }

    public static void systemOutPrintln(String fileName) throws InterruptedException
    {
        ReadFileInterface f = new ReadFileThread();
        f.setFileName(fileName);
        f.start();
        f.join();
        System.out.println(f.getFileContent());
    }

    public interface ReadFileInterface
    {

        void setFileName(String fullFileName);

        String getFileContent();

        void join() throws InterruptedException;

        void start();
    }

    public static class ReadFileThread extends Thread implements ReadFileInterface
    {
        private BufferedReader file;
        private final StringBuilder allDataFromFile = new StringBuilder();

        @Override
        public void setFileName(String fullFileName)
        {
            try
            {
                file = new BufferedReader(new InputStreamReader(new FileInputStream(fullFileName)));
            } catch (FileNotFoundException e)
            {
                e.printStackTrace();
            }
        }

        @Override
        public String getFileContent()
        {
            return allDataFromFile.toString();
        }

        @Override
        public void run()
        {
            try
            {
                while (file.ready())
                {
                    allDataFromFile.append(file.readLine());
                    if(file.ready())
                        allDataFromFile.append(" ");
                }

                file.close();

            } catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }
}
