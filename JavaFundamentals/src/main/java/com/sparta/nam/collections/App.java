package com.sparta.nam.collections;

import com.sparta.nam.oop.*;
import com.sparta.nam.oop.zoo.Dog;

import java.util.*;

public class App {
    public static void main(String[] args) {
//        Member m1 = new Member("Nish", "Mandal", 1999,5,15);
//        Member m2 = new Member("Ahmed", "Hamad", 2001,4,22);
//        Dog dog = new Dog("Fido", 2021, 7, 13, "fetch");
//        List<Movable> moveables = new ArrayList<>(List.of(m1,m2,dog));
//        moveables.add(new Dog("Fido",2001,12,1, "Bones"));
//        Movable[] moveablesArr = {m1,m2,dog};
//        moveablesArr[0] = new Dog("Rex",2001,12,1, "Bones");
//        Integer[] intArr = new Integer[3];

//        HashSet<String> names = new HashSet<>(
//                List.of("Terry", "Terry", "Liam", "Ian")
//        );
//        for(String name: names){
//            System.out.println(name);
//        }


//        HashMap<Integer, String> trainees = new HashMap<>(Map.of(
//                1, "Nish",
//                2, "Fish",
//                3, "Dish"
//        ));
//
//        //Prints all kvp as a set of entries
//        System.out.println(trainees.entrySet());
//
//        // Print the entire hashmap
//        //System.out.println(trainees);
//
//        // Remove the entry with key 2 and print the removed value
//        System.out.println(trainees.remove(2));
//
//        // Get the value associated with a key
//        System.out.println(trainees.get(1));
//
//        System.out.println(trainees.put(4, "Nash"));
//
//        System.out.println(trainees);
//
//        for(Map.Entry<Integer, String> trainee: trainees.entrySet()){
//            System.out.println(trainee);
//        }


        String message = "The cat in the hate comes back".trim().toLowerCase();
        HashMap<Character, Integer> lettersCount = new HashMap<>();

        for (Character letter: message.toCharArray()) {
            if (!letter.equals(' ')) {
                if (lettersCount.containsKey(letter)) {
                    lettersCount.put(letter, lettersCount.get(letter) + 1);
                } else {
                    lettersCount.put(letter, 1);
                }
            }
        }

        for (var entry: lettersCount.entrySet()) {
            System.out.println(entry);
    }

        ArrayDeque<String> myNamesStack  = new ArrayDeque<>();
        myNamesStack.push("Carol");
        myNamesStack.push("Brenda");
        myNamesStack.push("Terry");

        System.out.println(myNamesStack.peek());
        System.out.println(myNamesStack.pop());
        System.out.println(myNamesStack.peek());


        Queue<String> myNamesQueue = new LinkedList<>();
        myNamesQueue.offer("Carol");
        myNamesQueue.offer("Brenda");
        myNamesQueue.offer("Terry");

        System.out.println(myNamesQueue.peek());
        System.out.println(myNamesQueue.poll());
        System.out.println(myNamesQueue.peek());
}
}
