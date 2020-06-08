package com.german1um.incrementor.service;

public interface IIncrementor {
    int getNumber();
    void incrementNumber();
    void setMaximumValue(int maximumValue);
}