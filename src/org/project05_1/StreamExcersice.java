package org.project05_1;

import java.util.*;
import java.util.stream.Collectors;

public class StreamExcersice {
    private enum Sex {
        MAN,
        WOMEN
    }

    private static class Student {
        private final String name;
        private final Integer age;
        private final Sex sex;

        public Student(String name, Integer age, Sex sex) {
            this.name = name;
            this.age = age;
            this.sex = sex;
        }

        public String getName() {
            return name;
        }

        public Integer getAge() {
            return age;
        }

        public Sex getSex() {
            return sex;
        }

        @Override
        public String toString() {
            return "{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    ", sex=" + sex +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Student)) return false;
            Student people = (Student) o;
            return Objects.equals(name, people.name) &&
                    Objects.equals(age, people.age) &&
                    Objects.equals(sex, people.sex);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name, age, sex);
        }
    }

    static Collection<Student> students = Arrays.asList(
            new Student("Вася", 16, Sex.MAN),
            new Student("Петя", 23, Sex.MAN),
            new Student("Соня", 18, Sex.WOMEN),
            new Student("Виктор Петрович", 65, Sex.MAN),
            new Student("Дима", 25, Sex.MAN),
            new Student("Катя", 21, Sex.WOMEN),
            new Student("Семен", 33, Sex.MAN),
            new Student("Елена", 42, Sex.WOMEN),
            new Student("Иван Иванович", 69, Sex.MAN)
    );

    static List<Student> ex01() {
        // TODO: Задание 1
        // Выбрать всех мужчин-военнообязанных (возраст от 18 до 27 лет)

        return students.stream()
                .filter(s -> s.getSex().equals(Sex.MAN))
                .filter(s -> s.getAge() >= 18 & s.getAge() <= 27)
                .collect(Collectors.toList());
    }

    static Double ex02() {
        // TODO: Задание 2
        // Найти средний возраст среди мужчин

        return students.stream()
                .filter(s -> s.getSex().equals(Sex.MAN))
                .mapToInt(Student::getAge)
                .average().getAsDouble();
    }

    static Long ex03() {
        // TODO: Задание 3
        // Найти кол-во потенциально работоспособных
        // студентов в выборке (т.е. от 18 лет и учитывая
        // что женщины выходят в 55 лет, а мужчина в 60)

        return students.stream()
                .filter(s -> s.getAge() >= 18 & (s.getAge() < 60 & s.getSex().equals(Sex.MAN) || s.getAge() < 55 & s.getSex().equals(Sex.WOMEN)))
                .count();
    }

    static List<Student> ex04() {
        // TODO: Задание 4
        // Отсортировать студентов по имени в обратном алфавитном порядке

        return students.stream()
                .sorted(Comparator.comparing(Student::getName).reversed())
                .collect(Collectors.toList());
    }

    static Student ex05() {
        //  TODO: Задание 5
        //  найти студента с максимальным возрастом

        return students.stream()
                .filter(s -> s.getAge().equals(students.stream()
                        .mapToInt(Student::getAge)
                        .max()
                        .getAsInt()))
                .findAny()
                .orElse(null);
    }

    static Student ex06() {
        // TODO: Задание 6
        // Найти студента с минимальным возрастом

        return students.stream()
                .filter(s -> s.getAge().equals(students.stream()
                        .mapToInt(Student::getAge)
                        .min()
                        .getAsInt()))
                .findAny()
                .orElse(null);
    }

    public static void main(String[] args) {
        // TODO: тестировать здесь

        //ex01
        System.out.println(ex01());

        //ex02
        System.out.println(ex02());

        //ex03
        System.out.println(ex03());

        //ex04
        System.out.println(ex04());

        //ex05
        System.out.println(ex05());

        //ex06
        System.out.println(ex06());
    }
}
