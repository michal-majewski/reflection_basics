package com.mmajewski.experimental.invokechain.parameters;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.mmajewski.experimental.invokechain.parameters.annotations.Annotations.*;

class Main {
    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException {
        final BestGameFinder bestGameFinder = new BestGameFinder();

        // No annotation chain
//        final Set<String> games = bestGameFinder.getAllGames();
//        final Map<String, Float> gameToRating = bestGameFinder.getGameToRating(games);
//        final Map<String, Float> gameToPrice = bestGameFinder.getGameToPrice(games);
//        final SortedMap<Double, String> scoreToGame = bestGameFinder.scoreGames(gameToPrice, gameToRating);
//        final List<String> bestGamesInDescendingOrder = bestGameFinder.getTopGames(scoreToGame);
//        System.out.println(bestGamesInDescendingOrder);

        // With annotations
        final List<String> bestGamesInDescendingOrder = execute(bestGameFinder);
        System.out.println(bestGamesInDescendingOrder);
    }

    public static <T> T execute(Object instance) throws InvocationTargetException, IllegalAccessException {
        final Class<?> clazz = instance.getClass();

        final Map<String, Method> operationToMethod = getOperationToMethod(clazz);
        final Method finalResultMethod = findFinalResultMethod(clazz);

        return (T) executeWithDependencies(instance, finalResultMethod, operationToMethod);
    }

    private static Object executeWithDependencies(final Object instance, final Method currentMethod, final Map<String, Method> operationToMethod) throws InvocationTargetException, IllegalAccessException {
        final List<Object> parametersValues = new ArrayList<>(currentMethod.getParameterCount());

        for (final Parameter parameter : currentMethod.getParameters()) {
            Object value = null;
            if (parameter.isAnnotationPresent(DependsOn.class)) {
                final String dependencyOperationName = parameter.getAnnotation(DependsOn.class).value();
                final Method dependencyMethod = operationToMethod.get(dependencyOperationName);

                value = executeWithDependencies(instance, dependencyMethod, operationToMethod);
            }
            parametersValues.add(value);
        }

        return currentMethod.invoke(instance, parametersValues.toArray());
    }

    private static Map<String, Method> getOperationToMethod(Class<?> clazz) {
        final HashMap<String, Method> operationNameToMethod = new HashMap<>();

        for (final Method method : clazz.getDeclaredMethods()) {
            if (!method.isAnnotationPresent(Operation.class)) {
                continue;
            }

            final Operation operation = method.getAnnotation(Operation.class);

            operationNameToMethod.put(operation.value(), method);
        }

        return operationNameToMethod;
    }

    private static Method findFinalResultMethod(Class<?> clazz) {
        for (final Method method : clazz.getDeclaredMethods()) {
            if (method.isAnnotationPresent(FinalResult.class)) {
                return method;
            }
        }
        throw new RuntimeException("No method found with FinalResult annotation");
    }
}
