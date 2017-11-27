//Задание 3.
//        Написать программу для подсчета наиболее встречающихся слов в неком тексте произведения Алиса в стране чудес.
//        Файл с текстом прилагается.
//        При выводе резульатов привести первые 10 наиболее встречающихся слова с указанием их количества.
//        Пример вывода:
//
//        алиса: 406
//        сказала: 126
//        было: 105
//        сказал: 100
//        если: 87
//        только: 87
//        очень: 71
//        когда: 64
//        король: 61
//        подумала: 61
//
//        Подсчет слов не должен учитывать регистр и знаки препинания

package org.project05_3;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class App {
    private static final String FILE = "alice.txt";
    // метод, сортирующий мапу по значениям. генерик.
    private static <K, V extends Comparable<? super V>> SortedSet<Map.Entry<K, V>> entriesSortedByValues(Map<K, V> map) {
        SortedSet<Map.Entry<K, V>> sortedEntries = new TreeSet<>(
                (e1, e2) -> {
                    int res = e2.getValue().compareTo(e1.getValue());
                    return res != 0 ? res : 1;
                }
        );
        sortedEntries.addAll(map.entrySet());
        return sortedEntries;
    }

    public static void main(String[] args) {
        Map<String, Integer> wordsMap = new TreeMap<>();
        try (Scanner scan = new Scanner(new File(FILE))) {
            while (scan.hasNext()) {
                String word = scan.next().toLowerCase().replaceAll("[^a-zA-ZА-Яа-я]", ""); // читаем следующее слово, преобразовываем в нижний регистр и сразу убираем все, кроме букв
                if (word.length() > 3) { // считаем только слова, длина которых больше 3
                    if (!wordsMap.containsKey(word)) {   // если слова нет в мапе,
                        wordsMap.put(word, 1);           // то добавляем
                    } else {                             // иначе
                        wordsMap.put(word, wordsMap.get(word) + 1);   //увеличиваем счетчик вхождений
                    }
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        int i = 0;                                                  // выводим первые 10 записей мапы,
        for (Map.Entry entry : entriesSortedByValues(wordsMap)) {   // отсортированные по значению
            if (i++ < 10) {
                System.out.println(entry.getKey() + ":\t" + entry.getValue());
            } else {
                break;
            }
        }
    }
}
