/*
Title: Body Fat Calculator
Version: 1.0
Created By: Joseph M Baskin
Version Date: 18 January 2024

This Body Fat Calculator can be used for either men or women.  Inputs needed
in order to perform calculations are prompted after specifying gender.  Each value
is stored as a double, with the final output being a double representation as a 
percent.
 */

import java.util.Scanner;

public class BodyFatCalculator {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // Step 1:  Get user's gender with simple input scanner.
        System.out.println("Welcome to a Body Fat Calculator.");
        System.out.print("Please specify Male or Female [m or f]: ");
        String gender = input.nextLine();

        //IF statement accepts m for male, everything else becomes female
        if (gender.equals("m")) {
            //Step 2a: Ask for user's weight and waist measurement.  Store as doubles.
            System.out.print("Please enter body weight [in pounds: 235.2]: ");
            double bodyWeight = input.nextDouble();

            System.out.print("Please enter waist measurement [in inches: 41.8]: ");
            double waistMeasurement = input.nextDouble();

            //The secret sauce.  Perform calculations based on inputs and store
            //  each as a double.
            double factor1 = (bodyWeight * 1.082) + 94.42;
            double factor2 = waistMeasurement * 4.15;
            double leanBodyMass = factor1 - factor2;
            double bodyFatWeight = bodyWeight - leanBodyMass;
            double bodyFatPercent = (bodyFatWeight * 100) / bodyWeight;

            //Output the user's body fat percentage.  Force type to int, and divide by 100.0.
            System.out.print("Your overall Body Fat Percent is: " +
                (int)(bodyFatPercent * 100) / 100.0);
        } else {
            //Step 2b:  Ask for user's weight, wrist, waist, hips, and forearms in inches.
                //Store values as doubles for use in the calculations.
            System.out.print("Please enter your Bodyweight: ");
            double bodyWeight = input.nextDouble();
            System.out.print("Please enter your wrist [in inches]: ");
            double wristMeasurement = input.nextDouble();
            System.out.print("Please enter your waist [in inches]: ");
            double waistMeasurement = input.nextDouble();
            System.out.print("Please enter your hips [in inches]: ");
            double hipMeasurement = input.nextDouble();
            System.out.print("Please enter your forearms [in inches]: ");
            double forearmMeasurement = input.nextDouble();

            //The secret sauce.  Calculate each factor, stored as a double.
            double factor1 = (bodyWeight * 0.732) + 8.987;
            double factor2 = wristMeasurement / 3.140;
            double factor3 = waistMeasurement * 0.157;
            double factor4 = hipMeasurement * 0.249;
            double factor5 = forearmMeasurement * 0.434;
            double leanBodyMass = factor1 + factor2 - factor3 - factor4 + factor5;
            double bodyFatWeight = bodyWeight - leanBodyMass;
            double bodyFatPercent = (bodyFatWeight * 100) / bodyWeight;

            //Output the user's body fat percentage.
            System.out.print("Your overall Body Fat Percent is: " +
                (int)(bodyFatPercent * 100) / 100.0);
        }

        //Close input scanner.
        input.close();
    }
}
