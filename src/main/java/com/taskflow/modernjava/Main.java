package com.taskflow.modernjava;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {

        String message = """
                Welcome to TaskFlow
                Day 2 Modern Java
                Learning Records and Streams
                """;

        System.out.println(message);

        List<String> names = List.of("Sanjali", "Suraj", "Ashish");

        System.out.println("Names:");
        names.forEach(name -> System.out.println(name));

        List<Task> tasks = List.of(
                new Task(1L, "Learn Java", "TODO"),
                new Task(2L, "Learn Git", "DONE"),
                new Task(3L, "Learn Streams", "TODO"),
                new Task(4L, "Learn Spring", "DONE")
        );

        System.out.println("\nCompleted Tasks:");

        tasks.stream()
                .filter(task -> task.status().equals("DONE"))
                .forEach(System.out::println);

        long count = tasks.stream()
                .filter(task -> task.status().equals("DONE"))
                .count();

        System.out.println("\nCompleted Count: " + count);

        System.out.println("\nSorted Tasks:");

        tasks.stream()
                .sorted(Comparator.comparing(Task::title))
                .forEach(System.out::println);

        System.out.println("\nTask Titles:");

        tasks.stream()
                .map(Task::title)
                .forEach(System.out::println);

        Map<String, List<Task>> grouped =
                tasks.stream()
                        .collect(Collectors.groupingBy(Task::status));

        System.out.println("\nGrouped Tasks:");
        System.out.println(grouped);

        Optional<Task> foundTask = tasks.stream()
                .filter(task -> task.id().equals(2L))
                .findFirst();

        System.out.println("\nOptional Example:");
        foundTask.ifPresent(System.out::println);
    }
}