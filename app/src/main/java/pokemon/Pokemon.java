package pokemon;

import java.io.IOException;
import java.nio.file.*;
import java.util.stream.*;
import java.util.*;

public class Pokemon {
    private int number;
    private String name;
    private String type;

    public Pokemon(int number, String name, String type) {
        this.number = number;
        this.name = name;
        this.type = type;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Pokemon{" +
                "number=" + number +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                '}';
    }

    public static void main(String[] args) {
        List<Pokemon> pokedex = new ArrayList<>();

        try {
            Stream<String> lines = Files.lines(Paths.get("pokemon_data.csv"));
            pokedex = lines.skip(1)
                    .map(line -> {
                        String[] tokens = line.split(",");
                        int number = Integer.parseInt(tokens[0]);
                        String name = tokens[1];
                        String type = tokens[2];
                        return new Pokemon(number, name, type);
                    })
                    .collect(Collectors.toList());
            lines.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        // Print out all Gen I Pokémon
        pokedex.forEach(System.out::println);

        // Count the number of "Psychic" type Pokémon (including subtypes)
        int psychicCount = (int) pokedex.stream()
                .filter(pokemon -> pokemon.getType().contains("Psychic"))
                .count();
        System.out.println("Number of Psychic type Pokémon: " + psychicCount);

        // Create a list of all distinct type & subtype combinations
        List<String> distinctTypes = pokedex.stream()
                .map(Pokemon::getType)
                .distinct()
                .collect(Collectors.toList());
        System.out.println("Distinct type & subtype combinations: " + distinctTypes);

        // Build a hash map of every distinct type & subtype to the number of Pokémon with that typing
        Map<String, Long> typeCountMap = pokedex.stream()
                .collect(Collectors.groupingBy(Pokemon::getType, Collectors.counting()));
        System.out.println("Type & subtype counts: " + typeCountMap);
    }
}
