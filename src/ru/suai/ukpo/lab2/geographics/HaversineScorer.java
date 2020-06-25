package ru.suai.ukpo.lab2.geographics;

import ru.suai.ukpo.lab2.Scorer;

// Ортодрома - https://ru.wikipedia.org/wiki/Ортодромия
public class HaversineScorer implements Scorer<City> {

    @Override
    public double computeCost(City from, City to) {
        double R = 6372.8; // км

        double dLat = Math.toRadians(to.getLatitude() - from.getLatitude());
        double dLon = Math.toRadians(to.getLongitude() - from.getLongitude());
        double lat1 = Math.toRadians(from.getLatitude());
        double lat2 = Math.toRadians(to.getLatitude());

        double a = Math.pow(Math.sin(dLat / 2),2) + Math.pow(Math.sin(dLon / 2),2) * Math.cos(lat1) * Math.cos(lat2);
        double c = 2 * Math.asin(Math.sqrt(a));
        return R * c;
    }
}