package ru.suai.ukpo.lab2;

public interface Scorer<T extends GraphNode> {
    double computeCost(T from, T to);
}
