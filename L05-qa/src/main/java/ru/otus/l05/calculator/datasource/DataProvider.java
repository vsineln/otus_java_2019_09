package ru.otus.l05.calculator.datasource;

public interface DataProvider {
    int getDataInteger();

    double getDataDouble(int seed);
}
