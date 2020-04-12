package com.javarush.task.task20.task2027;

import java.util.List;
import java.util.ArrayList;

/*
Кроссворд
*/
public class Solution {
    public static void main(String[] args) {
        int[][] crossword = new int[][]{
                {'f', 'd', 'e', 'r', 'l', 'k'},
                {'u', 's', 'a', 'm', 'e', 'o'},
                {'l', 'n', 'g', 'r', 'o', 'v'},
                {'m', 'l', 'p', 'r', 'r', 'h'},
                {'p', 'o', 'e', 'e', 'j', 'j'}
        };
        List<Word> list = detectAllWords(crossword, "home", "same");
        for (Word w : list)
            System.out.println(w);
        /*
Ожидаемый результат
home - (5, 3) - (2, 0)
same - (1, 1) - (4, 1)
         */
    }

    public static List<Word> detectAllWords(int[][] crossword, String... words) {
        ArrayList<Word> list = new ArrayList<>();

        int[][] wordChars = wordsToChar(words);

        for(int i = 0; i < words.length; i++)
            letterPositionFinder(list, crossword, wordChars[i], words[i]);

        return list;
    }

    public static class Word {
        private String text;
        private int startX;
        private int startY;
        private int endX;
        private int endY;

        public Word(String text) {
            this.text = text;
        }

        public void setStartPoint(int i, int j) {
            startX = i;
            startY = j;
        }

        public void setEndPoint(int i, int j) {
            endX = i;
            endY = j;
        }

        @Override
        public String toString() {
            return String.format("%s - (%d, %d) - (%d, %d)", text, startX, startY, endX, endY);
        }
    }

    public static int[] isCommon(int[][] crossword, int x, int y, int target) {
        int[] arr = new int[2];

        if (x + 1 < crossword.length - 1 && target == crossword[x + 1][y]) {
            arr[0] = 1;
        } else if (x - 1 >= 0 && target == crossword[x - 1][y]) {
            arr[0] = -1;
        } else if (y + 1 < crossword[x].length - 1 && target == crossword[x][y + 1]) {
            arr[1] = 1;
        } else if (x + 1 < crossword.length - 1 && y + 1 < crossword[x].length - 1 && target == crossword[x + 1][y + 1]) {
            arr[0] = 1;
            arr[1] = 1;
        } else if (x - 1 >= 0 && y + 1 < crossword[x].length - 1 && target == crossword[x - 1][y + 1]) {
            arr[0] = -1;
            arr[1] = 1;
        } else if (y - 1 >= 0 && target == crossword[x][y - 1]) {
            arr[1] = -1;
        } else if (x + 1 < crossword.length - 1 && y - 1 >= 0 && target == crossword[x + 1][y - 1]) {
            arr[0] = 1;
            arr[1] = -1;
        } else if (x - 1 >= 0 && y - 1 >= 0 && target == crossword[x - 1][y - 1]) {
            arr[0] = -1;
            arr[1] = -1;
        }

        return arr;
    }

    public static int[][] wordsToChar(String[] w) {
        int[][] wordChars = new int[w.length][];

        for (int i = 0; i < w.length; i++) {
            String s = w[i];
            int[] chars = new int[s.length()];
            for (int j = 0; j < s.length(); j++) {
                chars[j] = s.charAt(j);
            }
            wordChars[i] = chars;
        }

        return wordChars;
    }

    public static void letterPositionFinder(List<Word> list, int[][] crossword, int[] name, String sName) {
        int[] finder;

        for (int i = 0; i < crossword.length; i++) {
            int counter;
            int firstLetter = name[0];

            for (int j = 0; j < crossword[i].length; j++) {
                if (crossword[i][j] == firstLetter) {
                    counter = 0;
                    finder = isCommon(crossword, i, j, name[++counter]);
                    int x = finder[0];
                    int y = finder[1];
                    if (x == 0 && y == 0) continue;

                    Word word = new Word(sName);
                    word.setStartPoint(j, i);

                    while (counter != name.length) {
                        if (name[counter] == crossword[i += x][j += y])
                            counter++;
                        else break;
                    }
                    if (counter != name.length)
                        break;

                    word.setEndPoint(j, i);
                    list.add(word);

                    return;
                }
            }
        }
    }
}