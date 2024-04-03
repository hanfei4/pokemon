# Pokémon Ϟ(๑⚈ ․̫ ⚈๑)⋆

Objectives:

In this lab you will practice using Streams and Lambdas.

## Instructions

You are given a dataset of Pokémon which are fictional characters in a large Japanese franchise. The data include the first 151 Pokémon (also known as Generation I) with their `number`, `name` in english, and `type` which can consist of a single type or a type with a subtype. You're tasked with opening and analyzing the content in this file with streams.

1. Create a Java Project.
2. Make a `Pokemon.java` Class with appropriate setters and getters **as well as** a `toString` method.
3. In your project's main method, have it open and read the file with the following code:

    ```java
    try{
        Stream<String> lines = Files.lines(Paths.get("pokemon_data.csv"));
        lines.forEach(System.out::println);
        lines.close();
    }
    catch(Exeption e){
        System.out.println(e.getMessage());
    }
    ```

    Don't forget to import `java.util.*`, `java.util.stream.*`, and `java.nio.file.*` which is Java's New I/O package for high performance file handling.

4. Try running this code. If you get an error message, double check the name of the data file as well as the path you specified. The IDE may be expecting the data to be in a specific directory, so read any error message closely to see what is causing the issue so you can address it.
5. Before the try statement, create a list using the statement `List<Pokemon> pokedex = new ArrayList<>();`.
6. Replace the `lines.forEach` stream with the following code:

    ```java
    pokedex = lines.map(line -> {
                    String[] tokens = line.split(",");
                    int number = Integer.parseInt(tokens[0]);
                    String name = tokens[1];
                    String type = tokens[2];
                    return new Pokemon(number, name, type);
                })
                .collect(Collectors.toList());
    ```

    This will go through every line in the file and create a Pokemon object for you, finally storing the data in our ArrayList.
7. When you run this code, you'll encounter an error. This is because the first line in the program is a header that we shouldn't try to split and thereby cast the word `number` as an integer. Have your program skip the first file by adding `.skip(1)` before the `.map(` stream.
8. Add a `.forEach` stream at the end of the program to print out all of the Gen I Pokémon using the statement `pokedex.forEach(System.out::println);`
9. Complete the following tasks on your own. Using streams with lambdas when appropriate:

* Count the number of `"Psychic"` type Pokémon (including subtypes). Remember that the `count` stream returns a `long` so you might want to cast it as an `int` before saving it somewhere. Don't forget that the `filter()` stream is available.
* Create a list of all of the distinct type & subtype combinations (you might want to consider using the `distinct()` stream).
* Build a hash map of every distinct type & subtype to the number of Pokémon in Gen I with that typing. For this task, do **not** group a single type with subtypes.

## Part 2 - Reflection

It is always best to think about what you just learned. Write a short retrospective (3-5 complete sentences) on what you've learned regarding lambdas and streams in this lab and the activity from last class below:


In this lab, I learned how to use streams to read data from a CSV file, map each line to a custom object (Pokemon), and collect the objects into a list. I also learned how to apply filters to streams to count specific types of objects (e.g., Psychic type Pokémon), use distinct() to obtain unique values, and leverage collectors to group and count objects based on certain criteria.
