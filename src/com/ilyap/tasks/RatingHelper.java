package com.ilyap.tasks;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static java.nio.charset.StandardCharsets.UTF_8;
import static java.util.stream.Collectors.toMap;

public class RatingHelper {
    private static Map<String, Integer> getCurrentRate(Path path) throws IOException {
        if (Files.exists(path)) {
            try (Stream<String> lines = Files.lines(path, UTF_8)) {
                return lines
                        .map(String::valueOf)
                        .filter(s -> s.matches("^[а-яА-Яa-zA-Z0-9]+ — \\d+"))
                        .collect(toMap(
                                k -> k.split(" — ")[0],
                                v -> Integer.valueOf(v.split(" — ")[1]))
                        );
            }
        }
        return new HashMap<>();
    }

    public static List<String> getNewRate(Path path, Player... players) throws IOException {
        Map<String, Integer> currentRate = getCurrentRate(path);
        for (Player player : players) {
            if (currentRate.containsKey(player.getName())) {
                for (Map.Entry<String, Integer> entry : currentRate.entrySet()) {
                    if (entry.getKey().equals(player.getName())) {
                        currentRate.put(entry.getKey(), entry.getValue() + player.getCountWins());
                    }
                }
            } else {
                currentRate.put(player.getName(), player.getCountWins());
            }
        }
        return toListRepresentation(currentRate);
    }

    private static List<String> toListRepresentation(Map<String, Integer> playersRating) {
        return playersRating.entrySet().stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .map(entry -> entry.getKey() + " — " + entry.getValue())
                .toList();
    }
}
