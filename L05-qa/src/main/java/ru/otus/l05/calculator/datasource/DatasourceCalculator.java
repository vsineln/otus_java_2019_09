package ru.otus.l05.calculator.datasource;

public class DatasourceCalculator {

    private final DataProvider dataProvider;

    public DatasourceCalculator(DataProvider dataProvider) {
        this.dataProvider = dataProvider;
    }

    public int accumulator(int ticksCounter) {
        int result = 0;
        for (int i = 0; i < ticksCounter; i++) {
            result += dataProvider.getDataInteger();
        }
        return result;
    }

    public double complexCalc(int ticksCounter) {
        double result = 0;
        for (int i = 0; i < ticksCounter; i++) {
            result += dataProvider.getDataDouble(dataProvider.getDataInteger());
        }
        return result;
    }
}
