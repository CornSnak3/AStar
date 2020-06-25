package ru.suai.ukpo.lab2;

import ru.suai.ukpo.lab2.geographics.City;
import ru.suai.ukpo.lab2.geographics.HaversineScorer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Frame extends JFrame implements ActionListener {

    private JTextField textFieldId1;
    private JTextField textFieldId2;
    private JTextField textFieldId3;
    private JTextField textFieldResult;

    private JButton execute;

    private JTextArea citiesList;
    ListModel listModel;


    public Frame() {

        Set<City> nodes = new HashSet<>();
        Map<String, Set<String>> connections = new HashMap<>();

        nodes.add(new City("1", "Санкт-Петербург", 59.5615, 30.1831));
        nodes.add(new City("2", "Москва", 55.4500, 37.3700));
        nodes.add(new City("3", "Норильск", 69.2000, 88.1300));
        nodes.add(new City("4", "Владивосток", 43.0800, 131.5400));
        nodes.add(new City("5", "Лос-Анджелес", 34.0200, -118.1600));
        nodes.add(new City("6", "Лондон", 51.3026, 0.0739));
        nodes.add(new City("7", "Кардифф", 51.2848, 3.1048));
        nodes.add(new City("8", "Северный полюс", 90.0000, 0.0000));


        connections.put("1", Stream.of("2", "4").collect(Collectors.toSet()));
        connections.put("2", Stream.of("1", "3", "4", "5", "6").collect(Collectors.toSet()));
        connections.put("3", Stream.of("2").collect(Collectors.toSet()));
        connections.put("4", Stream.of("1", "2").collect(Collectors.toSet()));
        connections.put("5", Stream.of("1").collect(Collectors.toSet()));
        connections.put("6", Stream.of("1", "2", "7").collect(Collectors.toSet()));
        connections.put("7", Stream.of("6").collect(Collectors.toSet()));
        connections.put("8", null);

        Graph<City> cityGraph = new Graph<>(nodes, connections);
        RouteFinder<City> routeFinder = new RouteFinder<>(
                cityGraph, new HaversineScorer(), new HaversineScorer()
        );

        listModel = new DefaultListModel();

        for (City city : nodes) {
//            citiesList.append("ID:" + city.getId() + " Город:" + city.getName() + "\n");
           ((DefaultListModel) listModel).addElement(city.getName());
        }







        execute.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<City> route = routeFinder.findRoute(cityGraph.getNode(textFieldId1.getText()), cityGraph.getNode(textFieldId2.getText()));
                String res = "";
                for (City city : route) {
                    res += city.getName() + " ";
                }
                textFieldResult.setText(res);
            }
        });

        this.pack();
        this.setLocationRelativeTo(null);
    }

    public Frame(String name) {
        this();
        setName(name);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
