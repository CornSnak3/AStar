package ru.suai.ukpo.lab2.geographics;

import ru.suai.ukpo.lab2.GraphNode;

public class City implements GraphNode {
    private final String id;
    private final String name;
    private final double latitude;
    private final double longitude;

    public City(String id, String name, double latitude, double longitude) {
        this.id = id;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    @Override
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    @Override
    public String toString() {
        return name;
    }
}