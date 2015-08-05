/**
 * Copyright © 2014, Oracle and/or its affiliates. All rights reserved.
 *
 * JDK 8 MOOC Lesson 3 homework
 */
package lesson3;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * Class to generate a list of random words
 *
 * @author Simon Ritter (@speakjava)
 */
public class RandomWords {

    private final List<String> sourceWords;

    /**
     * Constructor
     *
     * @throws IOException If the source words file cannot be read
     */
    public RandomWords() throws IOException {

        URI wordsURI;
        try {
            wordsURI = this.getClass().getResource("words").toURI();
        } catch (URISyntaxException ex) {
            Logger.getLogger(RandomWords.class.getName()).log(Level.SEVERE, null, ex);
            throw new IOException(ex);
        }

        try (BufferedReader reader = Files.newBufferedReader(Paths.get(wordsURI))) {
            // YOUR CODE HERE
            sourceWords = reader.lines().collect(Collectors.toList());
            System.out.println("Loaded " + sourceWords.size() + " words");
        }
    }

    /**
     * Create a list of a given size containing random words
     *
     * @param listSize The size of the list to create
     * @return The created list
     */
    public List<String> createList(int listSize) {
        Random rand = new Random();
        // YOUR CODE HERE
        List<String> wordList
                = rand.ints(listSize, 0, listSize - 1).
                peek(System.out::println).
                mapToObj(i -> sourceWords.get(i)).
                collect(Collectors.toList());
        return wordList;
    }

    /**
     * Return the list of all source words, which cannot be modified
     *
     * @return The unmodifiable list of all source words
     */
    public List<String> allWords() {
        return Collections.unmodifiableList(sourceWords);
    }
}
