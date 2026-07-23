package org.example.capm;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ReturnCalculatorTest {

    private final ReturnCalculator calculator = new ReturnCalculator();

    @Test
    void shouldCalculateReturns() {
        double[] prices = {100.0, 102.0, 101.0, 105.0};

        double[] returns = calculator.calculateReturns(prices);

        assertArrayEquals(
                new double[]{
                        0.02,
                        -0.00980392156862745,
                        0.039603960396039604
                },
                returns,
                0.0001
        );
    }

    @Test
    void shouldCalculateZeroReturnWhenPriceDoesNotChange() {
        double[] prices = {100.0, 100.0};

        double[] returns = calculator.calculateReturns(prices);

        assertArrayEquals(
                new double[]{0.0},
                returns,
                0.0001
        );
    }

    @Test
    void shouldRejectNullPrices() {
        assertThrows(
                IllegalArgumentException.class,
                () -> calculator.calculateReturns(null)
        );
    }

    @Test
    void shouldRejectEmptyPrices() {
        assertThrows(
                IllegalArgumentException.class,
                () -> calculator.calculateReturns(new double[]{})
        );
    }

    @Test
    void shouldRejectSinglePrice() {
        assertThrows(
                IllegalArgumentException.class,
                () -> calculator.calculateReturns(new double[]{100.0})
        );
    }

    @Test
    void shouldRejectZeroPrice() {
        double[] prices = {100.0, 0.0, 105.0};

        assertThrows(
                IllegalArgumentException.class,
                () -> calculator.calculateReturns(prices)
        );
    }

    @Test
    void shouldRejectNegativePrice() {
        double[] prices = {100.0, -50.0, 105.0};

        assertThrows(
                IllegalArgumentException.class,
                () -> calculator.calculateReturns(prices)
        );
    }
}