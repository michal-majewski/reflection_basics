package com.mmajewski.experimental.parameters;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.Arrays.*;

class Database {
    static final int RATING_INDEX = 0;
    static final int PRICE_INDEX = 1;
    /// Game Name -> (Rating, Price)
    private static final Map<String, List<Float>> GAME_TO_PRICE = Map.of("Fortnite", asList(5f, 10f),
            "Minecraft", asList(4.3f, 100f),
            "League Of Legends", asList(4.9f, 89f),
            "Ace Combat", asList(4.8f, 50f),
            "StarCraft", asList(4.7f, 66f),
            "Burnout", asList(4.4f, 31f));


    Set<String> readAllGames() {
        return Collections.unmodifiableSet(GAME_TO_PRICE.keySet());
    }

    Map<String, Float> readGameToRatings(Set<String> games) {
        return GAME_TO_PRICE.entrySet()
                .stream()
                .filter(entry -> games.contains(entry.getKey()))
                .collect(Collectors.toUnmodifiableMap(Map.Entry::getKey, entry -> entry.getValue().get(RATING_INDEX)));
    }

    Map<String, Float> readGameToPrice(Set<String> games) {
        return GAME_TO_PRICE.entrySet()
                .stream()
                .filter(entry -> games.contains(entry.getKey()))
                .collect(Collectors.toUnmodifiableMap(Map.Entry::getKey, entry -> entry.getValue().get(PRICE_INDEX)));
    }
}
