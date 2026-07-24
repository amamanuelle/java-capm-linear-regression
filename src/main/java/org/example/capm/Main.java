package org.example.capm;

public class Main {

    public static void main(String[] args) {

        if (args.length != 2) {
            System.out.println("Usage: java Main <market.csv> <stock.csv>");
            return;
        }

        CapmAnalysisService service = new CapmAnalysisService();

        try {
            CapmAnalysisResult result =
                    service.analyse(args[0], args[1]);

            System.out.println("CAPM Regression Results");
            System.out.println("-----------------------");
            System.out.println("Alpha: " + result.alpha());
            System.out.println("Beta : " + result.beta());
            System.out.println("R²   : " + result.rSquared());

        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}