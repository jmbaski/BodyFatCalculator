namespace BodyFatCalculator;
using System;

public class BodyFatCalculator
{
    static void Main(string[] args)
    {
        Console.WriteLine(GetBodyFactors());
    }

    private static double GetBodyFactors()
    {
        Console.WriteLine("Please enter your gender:");
        int gender = (Console.ReadLine() == "male") ? 1 : 0;

        Console.WriteLine("Please enter your weight: ");
        double weight = double.Parse(Console.ReadLine());

        Console.WriteLine("Please enter your waist [inches]: ");
        double waist = double.Parse(Console.ReadLine());

        if (gender == 0)
        {
            Console.WriteLine("Please enter your wrist [inches]: ");
            double wrist = double.Parse(Console.ReadLine());

            Console.WriteLine("Please enter your hip [inches]: ");
            double hip = double.Parse(Console.ReadLine());

            Console.WriteLine("Please enter your forearm [inches]: ");
            double forearm = double.Parse(Console.ReadLine());
            
            return CalculateFemale(weight, waist, hip, wrist, forearm);
        }
        else
        {
            return CalculateMale(weight, waist);
        }
    }

    private static double CalculateMale(double weight, double waist)
    {
        double f1 = (weight * 1.082) + 94.42;
        double f2 = waist * 4.15;
        double leanBodyMass = f1 - f2;
        double bodyFatWeight = weight - leanBodyMass;
        double bodyFatPercent = (bodyFatWeight * 100) / weight;

        return bodyFatPercent;
    }

    private static double CalculateFemale(double weight, double waist, double hip,
        double wrist, double forearm)
    {
        double f1 = (weight * 0.732) + 8.987;
        double f2 = wrist / 3.140;
        double f3 = waist * 0.157;
        double f4 = hip * 0.249;
        double f5 = forearm * 0.434;
        double leanBodyMass = f1 + f2 - f3 - f4 + f5;
        double bodyFatWeight = weight - leanBodyMass;
        double bodyFatPrecent = (bodyFatWeight * 100) / weight;

        return bodyFatPrecent;
    }
}