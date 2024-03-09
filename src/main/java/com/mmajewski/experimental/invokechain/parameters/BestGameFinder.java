package com.mmajewski.experimental.invokechain.parameters;

import java.util.*;

import static com.mmajewski.experimental.invokechain.parameters.annotations.Annotations.*;

class BestGameFinder {
    private final Database database = new Database();

    @Operation("All-Games")
    public Set<String> getAllGames() {
        return database.readAllGames();
    }

    @Operation("Game-To-Price")
    public Map<String, Float> getGameToPrice(@DependsOn("All-Games") Set<String> allGames) {
        return database.readGameToPrice(allGames);
    }

    @Operation("Game-To-Rating")
    public Map<String, Float> getGameToRating(@DependsOn("All-Games") Set<String> allGames) {
        return database.readGameToRatings(allGames);
    }

    @Operation("Score-To-Game")
    public SortedMap<Double, String> scoreGames(
            @DependsOn("Game-To-Price") Map<String, Float> gameToPrice,
            @DependsOn("Game-To-Rating") Map<String, Float> gameToRating) {
        final SortedMap<Double, String> gameToScore = new TreeMap<>(Collections.reverseOrder());
        for (final String gameName : gameToPrice.keySet()) {
            final double score = (double) gameToRating.get(gameName) / gameToPrice.get(gameName);
            gameToScore.put(score, gameName);
        }
        return gameToScore;
    }

    @FinalResult
    public List<String> getTopGames(@DependsOn("Score-To-Game") SortedMap<Double, String> gameToScore) {
        return new ArrayList<>(gameToScore.values());
    }
}
