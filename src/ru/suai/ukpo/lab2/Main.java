package ru.suai.ukpo.lab2;

import ru.suai.ukpo.lab2.geographics.City;
import ru.suai.ukpo.lab2.geographics.HaversineScorer;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {

        Set<City> nodes = new HashSet<>();
        Map<String, Set<String>> connections = new HashMap<>();

        nodes.add(new City("1", "Санкт-Петербург", 59.5615, 30.1831));
        nodes.add(new City("2", "Москва", 55.4500, 37.3700));
        nodes.add(new City("3", "Норильск", 69.2000, 88.1300));
        nodes.add(new City("4", "Владивосток", 43.0800, 131.5400));
        nodes.add(new City("5", "Лос-Анджелес", 34.0200, -118.1600));
        nodes.add(new City("6", "Лондон", 51.3026, 0.0739));
        nodes.add(new City("7", "Кардифф", 51.2848, 3.1048));


        connections.put("1", Stream.of("2", "4").collect(Collectors.toSet()));
        connections.put("2", Stream.of("1", "3", "4", "5", "6").collect(Collectors.toSet()));
        connections.put("3", Stream.of("2").collect(Collectors.toSet()));
        connections.put("4", Stream.of("1", "2").collect(Collectors.toSet()));
        connections.put("5", Stream.of("1").collect(Collectors.toSet()));
        connections.put("6", Stream.of("1", "2", "7").collect(Collectors.toSet()));
        connections.put("7", Stream.of("6").collect(Collectors.toSet()));

        Graph<City> cityGraph = new Graph<>(nodes, connections);
        RouteFinder<City> routeFinder = new RouteFinder<>(
                cityGraph, new HaversineScorer(), new HaversineScorer()
        );

        /* Тестирование методом черного ящика */
        System.out.println("Тестирование методом черного ящика");

        System.out.println("\nТестирование HaversineScorer.computeCost(City, City)");
        HaversineScorer testScorer = new HaversineScorer();
        System.out.println(testScorer.computeCost(new City("1", "1", 0, 0), new City("2", "2", 0, 0)));
        System.out.println(testScorer.computeCost(new City("1", "1", 0, 0), new City("2", "2", 1, 1)));
        System.out.println(testScorer.computeCost(new City("1", "1", 0, 0), new City("2", "2", 59, 30)));

        System.out.println("\nТестирование City.getID(), City.getName(), City.getLatitude(), City.getLongtitude()");
        System.out.println(cityGraph.getNode("1").getId());
        System.out.println(cityGraph.getNode("1").getName());
        System.out.println(cityGraph.getNode("1").getLatitude());
        System.out.println(cityGraph.getNode("1").getLongitude());

        System.out.println("\nТестирование Graph.getNode(String)");
        try {
            System.out.println(cityGraph.getNode("1"));
            System.out.println(cityGraph.getNode("69"));
        } catch (IllegalArgumentException e) {
            System.out.println(e);
        }

        System.out.println("\nТестирование Graph.getConnections(String)");
        System.out.println(cityGraph.getConnections(cityGraph.getNode("1")) + "\n");

        List<City> route = routeFinder.findRoute(cityGraph.getNode("1"), cityGraph.getNode("7"));

    }
}
