package ru.otus.l05.calculator.datasource;


import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

// https://static.javadoc.io/org.mockito/mockito-core/3.0.0/org/mockito/Mockito.html
class DatasourceCalculatorTest {

    @Test
    void accumulator() {
        DataProvider dataProvider = mock(DataProvider.class);
        when(dataProvider.getDataInteger()).thenReturn(1);

        int result = new DatasourceCalculator(dataProvider).accumulator(5);

        assertEquals(5, result, "accumulator result:");
        verify(dataProvider, times(5)).getDataInteger();
        verify(dataProvider, never()).getDataDouble(ArgumentMatchers.anyInt());
    }

    @Test
    void complexCalc() {
        DataProvider dataProvider = new DataProviderImpl();
        DataProvider dataProviderSpy = spy(dataProvider);
        doReturn(1.0).when(dataProviderSpy).getDataDouble(ArgumentMatchers.anyInt());
        double result = new DatasourceCalculator(dataProviderSpy).complexCalc(5);

        assertEquals(5, result, 0.1, "complex calc result:");

        verify(dataProviderSpy, times(1)).getDataDouble(0);
        verify(dataProviderSpy, times(1)).getDataDouble(1);
        verify(dataProviderSpy, times(1)).getDataDouble(2);
        verify(dataProviderSpy, times(1)).getDataDouble(3);
        verify(dataProviderSpy, times(1)).getDataDouble(4);
        verify(dataProviderSpy, times(5)).getDataDouble(ArgumentMatchers.anyInt());
        verify(dataProviderSpy, times(5)).getDataInteger();
    }
}
