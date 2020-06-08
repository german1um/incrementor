package com.german1um.incrementor.service;

import com.german1um.incrementor.exception.MaximumValueBelowZeroException;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Incrementor implements IIncrementor {

    private final AtomicInteger number;
    private int maximumValue;
    private final ReadWriteLock maximumValueLock = new ReentrantReadWriteLock();

    public Incrementor(AtomicInteger number, int maximumValue) {
        this.number = number;
        this.maximumValue = maximumValue;
    }

    @Override
    public int getNumber() {
        return number.get();
    }

    @Override
    public void incrementNumber() {
        try {
            maximumValueLock.readLock().lock();
            number.getAndUpdate(number -> (number+1)%maximumValue);
        } finally {
            maximumValueLock.readLock().unlock();
        }
    }

    @Override
    public void setMaximumValue(int maximumValue) {
        if(maximumValue < 0) throw new MaximumValueBelowZeroException();
        try {
            maximumValueLock.writeLock().lock();
            this.maximumValue = maximumValue;
            number.getAndUpdate(number -> number>=maximumValue ? 0 : number);
        } finally {
            maximumValueLock.writeLock().unlock();
        }
    }

    public int getMaximumValue() {
        return maximumValue;
    }
}