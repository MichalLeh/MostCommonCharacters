package com.gfa.exam;

import java.nio.file.Paths;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        System.out.println(getTwoMostCommonCharacters("countchar.txt"));

    }

    public static String getTwoMostCommonCharacters(String filename){
        Map<String, Integer> allChars = new HashMap<>();
        Map<String, Integer> twoMostCommonChars = new HashMap<>();

        try (Scanner scanner = new Scanner(Paths.get("J:\\exam-trial-basics-master\\" + filename))) {

            while(scanner.hasNextLine()) {
                String row = scanner.nextLine();
                String[] parts = row.split("");

                for (int i = 0; i < parts.length; i++) {
                    if (allChars.containsKey(parts[i])) {
                        allChars.put(parts[i], allChars.get(parts[i])+1);
                    } else {
                        allChars.putIfAbsent(parts[i], 1);
                    }
                }
            }
        } catch (Exception e){
            System.out.println("File does not exist.");
        }
        // Get two most common characters
        for(int i=0; i<2; i++){
            String character = Collections.max(allChars.entrySet(), (entry1, entry2) -> entry1.getValue() - entry2.getValue()).getKey();
            // put character into new Map
            twoMostCommonChars.putIfAbsent(character, allChars.get(character));
            // remove it from inspected Map to get second most common character
            allChars.remove(character);
        }
        return twoMostCommonChars.toString();
    }
}
