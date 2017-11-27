//Задание 2.
//        Напишите метод, с названием WordWrap, который принимает Scanner, представляющий входной файл в качестве параметра и
//        выводит каждую строка файла на консоль, при чем длинна строки не должна превышать 60 символов.
//
//        Например, если строка содержит 112 символов, метод должен заменить его на две строки: одна из которых содержит
//        первые 60 символов, а другая содержащая последние 52 символов.
//
//        Строка, содержащая 217 символов должны быть преобразована в четыре строки: три длиной 60 и последняя строка длиной 37

package org.project05_2;

import java.io.*;
import java.util.Scanner;

public class App {
    private static final String INPUT_FILE = "input.txt";

    private static void WordWrap(Scanner scanner) {
            while (scanner.hasNextLine()) {
                for (String s : scanner.nextLine().replaceAll("(.{60})", "$1|").split("\\|")) { // ставим маркер "|" через каждые 60 символов и разбиваем строку по маркеру
                    System.out.println(s);
                }
            }
    }

    public static void main(String[] args){
        try {
            WordWrap(new Scanner(new File(INPUT_FILE)));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
