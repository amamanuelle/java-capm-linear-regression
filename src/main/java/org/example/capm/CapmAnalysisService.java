package org.example.capm;

public class CapmAnalysisService {

    private final CSVPriceReader priceReader = new CSVPriceReader();
    private final ReturnCalculator returnCalculator = new ReturnCalculator();
    private final LinearRegression regression = new LinearRegression();

    public CapmAnalysisResult analyse(
            String marketCsvPath,
            String stockCsvPath
    ) {
        double[] marketPrices =
                priceReader.readClosingPrices(marketCsvPath);

        double[] stockPrices =
                priceReader.readClosingPrices(stockCsvPath);

        double[] marketReturns =
                returnCalculator.calculateReturns(marketPrices);

        double[] stockReturns =
                returnCalculator.calculateReturns(stockPrices);

        if (marketReturns.length != stockReturns.length) {
            throw new IllegalArgumentException(
                    "Stock and market returns must have the same length"
            );
        }

        RegressionResult regressionResult =
                regression.fit(marketReturns, stockReturns);

        double rSquared = regression.calculateRSquared(
                marketReturns,
                stockReturns,
                regressionResult
        );

        return new CapmAnalysisResult(
                regressionResult.intercept(),
                regressionResult.slope(),
                rSquared
        );
    }
}
