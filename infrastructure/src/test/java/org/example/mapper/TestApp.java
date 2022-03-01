package org.example.mapper;

import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class TestApp {
    @Test
    public void liveCodingPhoneTest(){
        List<String> phoneNumbers = List.of("01", "02", "06", "03", "06", "01");

        Map<String, Long> counting = phoneNumbers.stream().collect(
                Collectors.groupingBy(String::valueOf, Collectors.counting()));

        System.out.println(counting);
    }
    @Test
    public void liveCodingCharSizeTest(){
        // Alt Gr + e = €
        String stringValue = "test de 12€";

        int charLengthUTF8 = stringValue.getBytes(StandardCharsets.UTF_8).length;
        System.out.println(charLengthUTF8);

        int charLengthISO_8859_1 = stringValue.getBytes(StandardCharsets.ISO_8859_1).length;
        System.out.println(charLengthISO_8859_1);

        //Arrays.asList(stringValue.getBytes(StandardCharsets.UTF_8)).stream().
    }
}
