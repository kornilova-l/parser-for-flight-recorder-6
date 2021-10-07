package com.github.kornilova_l.flight_parser;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FlightParserTest {
    @Test
    public void javaNine() {
        FlightParser flightParser = new FlightParser(new File("src/test/resources/recording_java9.jfr"));
        String expected = readFile(new File("src/test/resources/expected_java9.txt"));
        assertEquals(removeLineSeparator(Objects.requireNonNull(expected)), removeLineSeparator(flightParser.getStacks()));
    }

    @Test
    public void javaEight() {
        FlightParser flightParser = new FlightParser(new File("src/test/resources/recording_java8.jfr"));
        String expected = readFile(new File("src/test/resources/expected_java8.txt"));
        assertEquals(removeLineSeparator(Objects.requireNonNull(expected)), removeLineSeparator(flightParser.getStacks()));
    }

    private String readFile(File file) {
        try (Scanner scanner = new Scanner(new FileInputStream(file))) {
            StringBuilder stringBuilder = new StringBuilder();
            while (scanner.hasNextLine()) {
                stringBuilder.append(scanner.nextLine()).append("\n");
            }
            return stringBuilder.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String removeLineSeparator(String original) {
        return original.replaceAll("\n", "").replaceAll("\r", "");
    }
}