package org.example.capm;

import org.junit.jupiter.api.Test;

import java.net.URISyntaxException;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class CSVPriceReaderTest {

    private final CSVPriceReader reader = new CSVPriceReader();

    private String getResourcePath(String filename) {
        try {
            var resource = getClass().getClassLoader().getResource(filename);

            if (resource == null) {
                throw new IllegalArgumentException(
                        "Test resource not found: " + filename
                );
            }

            return Path.of(resource.toURI()).toString();

        } catch (URISyntaxException e) {
            throw new RuntimeException(
                    "Invalid resource path: " + filename,
                    e
            );
        }
    }

    @Test
    void shouldReadClosingPrices() {
        double[] prices = reader.readClosingPrices(
                getResourcePath("valid-prices.csv")
        );

        assertArrayEquals(
                new double[]{101.0, 102.0, 103.0},
                prices,
                0.0001
        );
    }

    @Test
    void shouldRejectMissingCloseColumn() {
        assertThrows(
                IllegalArgumentException.class,
                () -> reader.readClosingPrices(
                        getResourcePath("missing-close.csv")
                )
        );
    }

    @Test
    void shouldRejectEmptyCsvFile() {
        assertThrows(
                IllegalArgumentException.class,
                () -> reader.readClosingPrices(
                        getResourcePath("empty.csv")
                )
        );
    }


    @Test
    void shouldRejectMissingFile() {
        assertThrows(
                IllegalArgumentException.class,
                () -> reader.readClosingPrices(
                        "src/test/resources/does-not-exist.csv"
                )
        );
    }
}