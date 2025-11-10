package com.sparta.nam.streams;

import java.util.*;
import java.util.stream.Collectors;

public class App {

    public static void main(String[] args) {
        ArrayList<Integer> myArr = new ArrayList<>(List.of(22,3,45,18,20,21));
        int sum = 0;

        for(Integer n : myArr){
            if(isEven(n)) sum += n;
        }

        var result = myArr.stream().filter(App::isEven).mapToInt(x -> x).sum();
        System.out.println(result);
        System.out.println(sum);

        List<String> beatles = List.of("John", "Paul", "George", "Ringo");
        beatles.stream().filter(name -> name.startsWith("J")).forEach(System.out::println);

        HashMap<String, Integer> scores = new HashMap<>(Map.of(
                "Alice", 90,
                "Bob", 85,
                "Charlie", 95,
                "Diana", 80
        ));

        beatles.stream().forEach(x -> x.concat(" Lennon"));

        LinkedHashMap<String, Integer> sortedByValue = scores.entrySet()
                .stream()
                .sorted()
                .collect(Collectors.toMap(
                        entry -> entry.getKey(),
                        entry -> entry.getValue(),
                        (e1, e2) -> e1,
                        LinkedHashMap::new
                ));

        // Sort this hashmap so that it is in ascedning order of scores
    }

    public static boolean isEven(int n){
        return n % 2 == 0;
    }
}
