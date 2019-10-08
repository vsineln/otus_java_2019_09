package ru.otus.custom;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;


/**
 * @author sergey
 * created on 23.07.18.
 */
public class MyArrayIntTest {

    @Test
    public void setGetValue() throws Exception {
        MyArrayInt myArr = new MyArrayInt(1);
        int val = 45;
        int idx = 0;
        myArr.setValue(idx, val);
        assertEquals(val, myArr.getValue(idx), "value from array");
    }

    @Test
    public void setGetValueSeq() throws Exception {
        int size = 100;
        MyArrayInt myArr = new MyArrayInt(size);
        for (int idx = 0; idx < size; idx++) {
            myArr.setValue(idx, idx);
        }

        for (int idx = 0; idx < size; idx++) {
            assertEquals(idx, myArr.getValue(idx), "value from array for idx:" + idx);
        }
    }


    @Test
    public void incSize() throws Exception {
        int sizeInit = 1;
        int sizeMax = 100;
        MyArrayInt myArr = new MyArrayInt(sizeInit);
        for (int idx = 0; idx < sizeMax; idx++) {
            myArr.setValue(idx, idx);
        }

        for (int idx = 0; idx < sizeMax; idx++) {
            assertEquals(idx, myArr.getValue(idx), "value from array for idx:" + idx);
        }
    }


}