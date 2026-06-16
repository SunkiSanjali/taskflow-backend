package com.taskflow.modernjava;

import java.util.List;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {

        // Text Block
        String message = """
                Welcome to TaskFlow
                Day 2 Modern Java
                Learning Records and Streams
                """;

        System.out.println(message);

        // Lambda Expression
        List<String> names = List.of("Sanjali", "Suraj", "Ashish");

        System.out.println("Names:");
        names.forEach(name -> System.out.println(name));

        // Record Example
        List<Task> tasks = List.of(
                new Task(1L, "Learn Java", "TODO"),
                new Task(2L, "Learn Git", "DONE"),
                new Task(3L, "Learn Streams", "TODO"),
                new Task(4L, "Learn Spring", "DONE")
        );

        // Stream Filter
        System.out.println("\nCompleted Tasks:");
        tasks.stream()
                .filter(task -> task.status().equals("DONE"))
                .forEach(System.out::println);

        // Stream Count
        long count = tasks.stream()
                .filter(task -> task.status().equals("DONE"))
                .count();

        System.out.println("\nCompleted Task Count: " + count);

        // Optional Example
        Optional<Task> foundTask = tasks.stream()
                .filter(task -> task.id().equals(2L))
                .findFirst();

        System.out.println("\nOptional Example:");
        foundTask.ifPresent(System.out::println);

        // Sealed Interface + Switch Pattern Matching
        TaskStatus status = new Todo();

        String result = switch (status) {
            case Todo t -> "Task is yet to start";
            case InProgress i -> "Task is in progress";
            case Done d -> "Task is completed";
        };

        System.out.println("\nSwitch Pattern Matching:");
        System.out.println(result);
    }
}