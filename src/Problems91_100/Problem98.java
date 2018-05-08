package Problems91_100;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Stream;

public class Problem98 {

    private static String filename = "files/p098_words.txt";
    private static Set<String> words = new HashSet<>();

    private static Set<String> squares;
    private static Map<String, Set<String>> maskToSquares;
    private static Map<String, String> squareToMask;
    private static Map<String, Set<String>> repToSquares;
    private static Map<String, String> squareToRep;

    private static Map<String, Set<String>> maskToWord = new HashMap<>();
    private static Map<String, String> wordToMask = new HashMap<>();
    private static Map<String, Set<String>> repToWord = new HashMap<>();
    private static Map<String, String> wordToRep = new HashMap<>();

    public static void main(String[] args) {
        try (Stream<String> stream = Files.lines(Paths.get(filename))) {
            stream.forEach(
                    line ->
                            Stream.of(line.split(",")).forEach(
                                    word ->
                                            words.add((word.substring(1, word.length() - 1)))
                            )
            );
        } catch (IOException e) {
            for (StackTraceElement stackTraceElement :
                    e.getStackTrace()) {
                System.out.println(stackTraceElement.toString());
            }
            System.out.println(e.getLocalizedMessage());
        }

        generateSquareData();

        Set<String> tempWords = new HashSet<>();

        words.forEach(
                word -> {
                    String mask = generateMask(word);
                    if (maskToSquares.containsKey(mask)){
                        // there is a square that can be mapped to this word
                        if (!maskToWord.containsKey(mask)){
                            maskToWord.put(mask, new HashSet<>());
                        }
                        maskToWord.get(mask).add(word);
                        wordToMask.put(word, mask);

                        String rep = generateRepresentative(word);
                        if (!repToWord.containsKey(rep)){
                            repToWord.put(rep, new HashSet<>());
                        }
                        repToWord.get(rep).add(word);
                        wordToRep.put(word, rep);

                        tempWords.add(word);
                    }
                }
        );

        // Filter out any words which do not have a permutation in the input file
        words = new HashSet<>();


        tempWords.forEach(
                word -> {
                    if (repToWord.get(wordToRep.get(word)).size() == 1){
                        repToWord.remove(wordToRep.get(word));
                        wordToRep.remove(word);
                        maskToWord.get(wordToMask.get(word)).remove(word);
                        wordToMask.remove(word);
                    } else {
                        words.add(word);
                    }
                }
        );

        // Remove any masks that no longer have any words associated with them
        maskToWord.keySet().removeIf(key -> maskToWord.get(key).size() == 0);

        // We know, by construction, that all masks associated with a word must be a mask associated with a square.
        // But we need to remove from consideration all squares which are associated with a mask which is not associated
        // with any of our remaining words. (What a mouthful there...amiright?)
        Set<String> badSquares = new HashSet<>();
        (new HashSet<String>(maskToSquares.keySet())).stream().forEach(
                mask -> {
                    if (!maskToWord.containsKey(mask)){
                        badSquares.addAll(maskToSquares.get(mask));
                        maskToSquares.remove(mask);
                    }
                }
        );

        // The above code removes all bad squares from the maskToSquares mapping, but not the other three square-related
        // maps. Handle those now.
        badSquares.forEach(
                square -> {
                    squareToMask.remove(square);
                    repToSquares.get(squareToRep.get(square)).remove(square);
                    squareToRep.remove(square);
                }
        );

        // Remove all representatives that no longer have any squares associated with them
        repToSquares.keySet().removeIf(key -> repToSquares.get(key).size() == 0);

        squares.removeAll(badSquares);

        final AtomicReference<String> answer = new AtomicReference<>("");

        squares.stream().sorted((x, y) -> Integer.compare(Integer.parseInt(y), Integer.parseInt(x))).forEach(
                square -> {
                    Set<String> myWords = maskToWord.get(squareToMask.get(square));

                    Set<String> squarePerms = new HashSet<>(repToSquares.get(squareToRep.get(square)));
                    squarePerms.remove(square); // ignore self
                    Set<String> squarePermMasks = new HashSet<>();
                    squarePerms.forEach(perm -> squarePermMasks.add(squareToMask.get(perm)));
                    if (squarePermMasks.size() == 0){
                        return;
                    }

                    myWords.forEach(
                            mappedWord -> {
                                Set<String> wordAnagrams = new HashSet<>(repToWord.get(wordToRep.get(mappedWord)));
                                wordAnagrams.remove(mappedWord); // ignore self
                                if (wordAnagrams.size() > 0){
                                    Set<String> wordAnagramMasks = new HashSet<>();
                                    wordAnagrams.forEach(wordAnagram -> wordAnagramMasks.add(wordToMask.get(wordAnagram)));

                                    wordAnagramMasks.retainAll(squarePermMasks);
                                    if (wordAnagramMasks.size() > 0 && answer.get().equals("")){
                                        for (String word2: wordAnagrams){
                                            if (isSolution(mappedWord, word2, square)){
                                                answer.set(square);
                                                break;
                                            }
                                        }
                                    }
                                }
                            }
                    );
                }
        );

        System.out.println(answer.get());

    }

    private static boolean isSolution(String word1, String word2, String square1){
        Map<Character, Character> charMap = new HashMap<>();
        for (int i=0; i<word1.length(); i++){
            charMap.put(word1.charAt(i), square1.charAt(i));
        }

        StringBuilder builder = new StringBuilder();
        for (int i=0; i<word2.length(); i++){
            builder.append(charMap.get(word2.charAt(i)));
        }

        String square2 = builder.toString();

        return squares.contains(square2);
    }

    private static String generateMask(String s){
        Map<Integer, Integer> charMap = new HashMap<>();
        StringBuilder builder = new StringBuilder();
        AtomicInteger newDigit = new AtomicInteger(0);

        s.chars().forEach(
                c -> {
                    if (!charMap.containsKey(c)){
                        charMap.put(c, newDigit.getAndIncrement());
                    }
                    builder.append(charMap.get(c));
                }
        );

        if (newDigit.get() < 10){
            return builder.toString();
        } else {
            return null;
        }
    }

    private static String generateRepresentative(String s){
        char[] chars = s.toCharArray();
        Arrays.sort(chars);
        return String.valueOf(chars);
    }

    private static void generateSquareData(){
        int base = 1;
        int square = base*base;
        squares = new HashSet<>();

        while (true){
            String temp = "" + square;
            if (temp.length() > 9){
                break;
            }
            squares.add(temp);

            base += 1;
            square = base*base;
        }

        maskToSquares = new HashMap<>();
        squareToMask = new HashMap<>();

        repToSquares = new HashMap<>();
        squareToRep = new HashMap<>();

        squares.forEach(
                s -> {
                    String mask = generateMask(s);
                    if (!maskToSquares.containsKey(mask)){
                        maskToSquares.put(mask, new HashSet<>());
                    }
                    maskToSquares.get(mask).add(s);
                    squareToMask.put(s, mask);

                    String representative = generateRepresentative(s);
                    if (!repToSquares.containsKey(representative)){
                        repToSquares.put(representative, new HashSet<>());
                    }
                    repToSquares.get(representative).add(s);
                    squareToRep.put(s, representative);
                }
        );


        // Filter the "bad" numbers out. A number is bad if there is no other square which is a permutation of it.
        Set<String> goodSquares = new HashSet<>();
        squares.forEach(
                s -> {
                    if (repToSquares.get(squareToRep.get(s)).size() == 1){
                        // Bad number
                        repToSquares.remove(squareToRep.get(s));
                        squareToRep.remove(s);
                        maskToSquares.get(squareToMask.get(s)).remove(s);
                        squareToMask.remove(s);
                    } else {
                        goodSquares.add(s);
                    }
                }
        );

        // Remove any masks which have no strings associated with them anymore
        maskToSquares.keySet().removeIf(key -> maskToSquares.get(key).size() == 0);

        squares = goodSquares;

    }

}
