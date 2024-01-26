package com.mmajewski.experimental.creation.dependencyinjection.tictactoe.init;

import com.mmajewski.experimental.creation.dependencyinjection.tictactoe.game.Game;
import com.mmajewski.experimental.creation.dependencyinjection.tictactoe.game.internal.TicTacToeGame;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

class Main {
    public static void main(String[] args) throws InvocationTargetException, InstantiationException, IllegalAccessException {
        Game game = createObjectRecursively(TicTacToeGame.class);
        game.startGame();
    }

    static <T> T createObjectRecursively(Class<T> clazz) throws InvocationTargetException, InstantiationException, IllegalAccessException {
        Constructor<?> constructor = getFirstConstructor(clazz);

        List<Object> constructorArguments = new ArrayList<>();

        for (Class<?> argumentType : constructor.getParameterTypes()) {
            Object argumentValue = createObjectRecursively(argumentType);
            constructorArguments.add(argumentValue);
        }

        constructor.setAccessible(true);
        return (T) constructor.newInstance(constructorArguments.toArray());
    }

    /**
     * Assumption: Each class has either one explicitly declared construction or a default one (empty constructor)
     */
    private static Constructor<?> getFirstConstructor(Class<?> clazz) {
        Constructor<?>[] constructors = clazz.getDeclaredConstructors();

        if (constructors.length == 0) {
            throw new IllegalStateException(
                    String.format(
                            "No constructor has been found for class %s",
                            clazz.getName())
            );
        }

        return constructors[0];
    }
}
