package com.german1um.incrementor.unit;

import com.german1um.incrementor.service.Incrementor;
import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicInteger;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

class IncrementorTest {

    @Test
    void getNumber() {
        Incrementor incrementor = new Incrementor(new AtomicInteger(0), 10);
        assertThat(incrementor.getNumber(), is(0));
    }

    @Test
    void incrementNumberNoOverflow() {
        Incrementor incrementor = new Incrementor(new AtomicInteger(0), 10);
        incrementor.incrementNumber();
        assertThat(incrementor.getNumber(), is(1));
    }

    @Test
    void incrementNumberOverflow() {
        Incrementor incrementor = new Incrementor(new AtomicInteger(9), 10);
        incrementor.incrementNumber();
        assertThat(incrementor.getNumber(), is(0));
    }

    @Test
    void setMaximumValueNoOverflow() {
        Incrementor incrementor = new Incrementor(new AtomicInteger(9), 10);
        incrementor.setMaximumValue(100);
        assertThat(incrementor.getMaximumValue(), is(100));
        assertThat(incrementor.getNumber(), is(9));
    }

    @Test
    void setMaximumValueOverflow() {
        Incrementor incrementor = new Incrementor(new AtomicInteger(9), 10);
        incrementor.setMaximumValue(5);
        assertThat(incrementor.getMaximumValue(), is(5));
        assertThat(incrementor.getNumber(), is(0));
    }

    @Test
    void setMaximumValueBelowZero() {
        Incrementor incrementor = new Incrementor(new AtomicInteger(9), 10);
        incrementor.setMaximumValue(-100);
        assertThat(incrementor.getMaximumValue(), is(10));
        assertThat(incrementor.getNumber(), is(9));
    }

}