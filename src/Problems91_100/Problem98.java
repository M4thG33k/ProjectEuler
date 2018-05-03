package Problems91_100;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

public class Problem98 {

    private static String filename = "files/p098_words.txt";
    private static Set<String> words = new HashSet<>();

    public static void main(String[] args) {
        try (Stream<String> stream = Files.lines(Paths.get(filename))){
            stream.forEach(
                    line ->
                           Stream.of(line.split(",")).forEach(
                                   word ->
                                           words.add((word.substring(1, word.length()-1)))
                           )
            );

            words.forEach(System.out::println);
            System.out.println("TEST");

            // WIP
        } catch (IOException e){
            for (StackTraceElement stackTraceElement:
                 e.getStackTrace()) {
                System.out.println(stackTraceElement.toString());
            }
            System.out.println(e.getLocalizedMessage());
        }
    }
}
