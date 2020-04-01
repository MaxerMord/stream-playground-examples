import countries.*;
var countries = new Countries().getAll();

// 1. Returns whether there is at least one country with the word island in its name ignoring case.

countries.stream().
    anyMatch(country -> country.getName().toLowerCase().contains("island"));

// 2. Returns the first country name that contains the word island ignoring case.

countries.stream().
    filter(country -> country.getName().toLowerCase().contains("island")).findFirst().
    map(Country::getName).get();

// 3. Prints each country name in which the first and the last letters are the same ignoring case.

countries.stream().
    filter(country -> country.getName().toLowerCase().charAt(0) ==
    country.getName().charAt(country.getName().toLowerCase().length()-1)).
    map(Country::getName).forEach(System.out::println);



// 4. Prints the populations of the first ten least populous countries (required intermediate operation: stream, limit)).

countries.stream().
    map(Country::getPopulation).sorted().limit(10).
    forEach(System.out::println);

// 5. Prints the names of the first ten least populous countries (required intermediate operation: sorted, limit)).

// find the least 10 populus countries and the amount of most in them.
//countries.stream().map(Country::getPopulation).sorted().limit(10).forEach(System.out::println);

// use the amount from last steps to print out the country names.
//countries.stream().
    //filter(country -> country.getPopulation() <= 1411).
    //map(Country::getName).forEach(System.out::println);

//countries.stream().sorted().limit(10).forEach(System.out::println);

countries.stream().
    sorted(Comparator.comparingLong(Country::getPopulation)).limit(10).
    map(Country::getName).forEach(System.out::println);


// *6. Returns summary statistics about the number of country name translations associated with each country
//    (required intermediate operation: mapToInt, summaryStatistics).




// 7.Prints the names of countries in the ascending order of the number of timezones

countries.stream().
    sorted(Comparator.comparingLong(country -> country.getTimezones().size())).
    map(Country::getName).forEach(System.out::println);


// *8. Prints the number of timezones for each country in the form
//    name: population, in the ascending order of the number of timezones.

//countries.stream().
//  sorted(Comparator.comparing(country -> country.getTimezones().size())).
//  map(Country::getName,Country::getTimezones).forEach(System.out::println);

// 9.Returns the number of countries with no Spanish country name translation
// (the Spanish language is identified by the language code es).

long escounter = countries.stream().
    filter(country -> !country.getTranslations().containsKey("es")).count();

// 10. Prints the names of countries with null area.

countries.stream().filter(country -> country.getArea() == null).map(Country::getName).forEach(System.out::println);

// *11. Prints all distinct language tags of country name translations sorted in alphabetical order
// (required intermediate operation: flatMap).



// 12. Returns the average length of country names.

long avg = (countries.stream().mapToInt(country -> country.getName().length()).sum()) /
           (countries.stream().mapToInt(country -> country.getName().length()).count());

// 13.Prints all distinct regions of the countries with null area.

countries.stream().
    filter(country -> country.getArea() == null).
    map(Country::getRegion).distinct().forEach(System.out::println);

// 14. Returns the largest country with non-null area.

countries.stream().
    filter(country -> country.getArea() != null).
    max(Comparator.comparing(Country::getArea)).get();

// 15. Prints the names of countries with a non-null area below 10 (requires the use of BigDecimal.TEN).

countries.stream().
    filter(country -> country.getArea() != null && country.getArea().compareTo(BigDecimal.TEN) != 1).
    map(Country::getName).forEach(System.out::println);

// 16. Prints all distinct timezones of European and Asian countries.
    countries.stream().
        filter(country -> country.getRegion() == Region.EUROPE || country.getRegion() == Region.ASIA).
        flatMap(country -> country.getTimezones().stream()).
        distinct().
        forEach(System.out::println);