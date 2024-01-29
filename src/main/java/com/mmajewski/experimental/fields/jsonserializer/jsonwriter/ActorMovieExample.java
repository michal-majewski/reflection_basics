package com.mmajewski.experimental.fields.jsonserializer.jsonwriter;

import com.mmajewski.experimental.fields.jsonserializer.data.Actor;
import com.mmajewski.experimental.fields.jsonserializer.data.Movie;

import static com.mmajewski.experimental.fields.jsonserializer.jsonwriter.JsonWriter.*;

class ActorMovieExample {
    public static void main(String[] args) throws IllegalAccessException {
        Actor actor1 = new Actor("Elijah Wood", new String[]{"Lord of the Rings", "The Good Son"});
        Actor actor2 = new Actor("Ian McKellen", new String[]{"X-Men", "Hobbit"});
        Actor actor3 = new Actor("Orlando Bloom", new String[]{"Pirates of the Caribbean", "Kingdom of heaven"});

        Movie movie = new Movie("Lord of the Rings", 8.8f, new String[]{"Action", "Adventure", "Drama"},
                new Actor[]{actor1, actor2, actor3});

        String json = objectToJson(movie, 0);

        System.out.println(json);
    }
}
