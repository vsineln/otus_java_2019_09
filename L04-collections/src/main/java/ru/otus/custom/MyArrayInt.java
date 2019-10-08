package ru.otus.custom;


import sun.misc.Unsafe;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;


/**
 * @author sergey
 * created on 22.07.18.
 */
public class MyArrayInt implements AutoCloseable {

    private final Unsafe unsafe;
    private final int elementSizeBytes;
    private int size;
    private long arrayBeginIdx;

    MyArrayInt(int size) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        this.size = size;

        Constructor<Unsafe> unsafeConstructor = Unsafe.class.getDeclaredConstructor();
        unsafeConstructor.setAccessible(true);
        unsafe = unsafeConstructor.newInstance();

        elementSizeBytes = Integer.SIZE / 8;
        arrayBeginIdx = unsafe.allocateMemory(this.size * elementSizeBytes);
    }

    void setValue(long index, int value) {
        if (index == size) {
            this.size *= 2;
            arrayBeginIdx = unsafe.reallocateMemory(arrayBeginIdx, this.size * elementSizeBytes);
        }
        unsafe.putInt(index(index), value);
    }

    int getValue(long index) {
        return unsafe.getInt(index(index));
    }

    private long index(long offset) {
        return arrayBeginIdx + offset * this.elementSizeBytes;
    }

    @Override
    public void close() {
        unsafe.freeMemory(arrayBeginIdx);
    }
}
