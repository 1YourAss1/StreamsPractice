package practice2;

import jdk.nashorn.internal.runtime.regexp.joni.ast.StringNode;

import java.awt.*;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        OktmoData d = new OktmoData();
        d.readFile("oktmo.csv");
        System.out.println(d.getPlaces().get(0).name);

//        d.localityNames(d.find(1, 512));

//        d.uniqueNames(d.find(1));

//        Place place = d.findPlace("Березовка");

//        System.out.println(d.longestName(place));

//        System.out.println(d.longestNameList(place));

//        Map<String, Long> rating = d.ratingName(d.find(place.code1));

//        File ratingCSV = Paths.get("rating.csv").toFile();

//        try (PrintWriter printWriter = new PrintWriter(new FileWriter(ratingCSV))) {
//            for (Map.Entry<String, Long> entry : rating.entrySet()) {
//                printWriter.printf("%s;%d\n", entry.getKey(), entry.getValue());
//            }
//            Desktop.getDesktop().open(ratingCSV);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }

    }
}
