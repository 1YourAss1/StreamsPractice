package practice2;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class OktmoData {
    private List<Place> places = new ArrayList<>();

    public void readFile(String filename) {
        places.clear();
        Path p = Paths.get(filename);
        try {
            List<String> lines = Files.readAllLines(p, Charset.forName("cp1251"));
            for (String s: lines) {
                readLine(s);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
//"01";"512";"000";"146";"9";"2";"п Калиновка";;;"493";"3";12.08.2021;01.01.2022
    
   private static final Pattern RE=Pattern.compile("\"(\\d+)\";\"(\\d+)\";\"(\\d+)\";\"(\\d+)\";\".\";\".\";\"(.*)?\\s?([А-ЯЁ].*?)\"");
    private void readLine(String s) {
        Matcher m = RE.matcher(s);
        if (m.find()) {
            places.add(new Place(
                    Integer.parseInt(m.group(1)),
                    Integer.parseInt(m.group(2)),
                    Integer.parseInt(m.group(3)),
                    Integer.parseInt(m.group(4)),
                    m.group(5),
                    m.group(6)
                    ));
        }
    }

    public List<Place> getPlaces() {
        return places;
    }

    public Place findPlace(String name) {
        Optional<Place> optionalPlace = places.stream().filter(place -> place.getName().equals(name)).findFirst();
        return optionalPlace.orElse(null);
    }

    // rayon
    public Stream<Place> find(int code1, int code2) {
        return places.stream()
                .filter(place -> place.getCode1() == code1)
                .filter(place -> place.getCode2() == code2)
                .filter(place -> place.getCode4() != 0);
    }

    // region
    public Stream<Place> find(int code1) {
        return places.stream()
                .filter(place -> place.getCode1() == code1)
                .filter(place -> place.getCode4() != 0);
    }

    public void localityNames(Stream<Place> placeStream) {
        Pattern pattern = Pattern.compile("(ово$|-)");

        placeStream
                .map(Place::getName)
                .filter(name -> pattern.matcher(name).find())
                .forEach(System.out::println);
    }

    public void uniqueNames(Stream<Place> placeStream) {
        placeStream
                .map(Place::getName)
                .distinct()
                .forEach(System.out::println);
    }

    public String longestName(Place place) {
        int codeRegion = place.code1;

        Optional<String> stringOptional = places.stream()
                .map(Place::getName)
                .max(Comparator.comparingInt(String::length));

        return stringOptional.orElse(null);
    }

    public List<String> longestNameList(Place place) {
        int maxLength = longestName(place).length();

        return places.stream()
                .map(Place::getName)
                .filter(name -> name.length() == maxLength)
                .collect(Collectors.toList());
    }

    public Map<String, Long> ratingName(Stream<Place> placeStream) {
        // Grouping
        Map<String, Long> result = placeStream
                .collect(Collectors.groupingBy(     // group by...
                        Place::getName,             // ...name
                        Collectors.counting()));    // and count
        // Sorting
        result = result.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue((l1, l2) -> (int) (l2 - l1)))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));
        return result;
    }
}
