//
// Created by jbask on 5/11/2025.
//

#include <iostream>
#include <cstdlib>
#include "BodyFatCalculator.h"

BodyFatCalculator::BodyFatCalculator() {
	reset();
};

void BodyFatCalculator::reset() {
	gender = 0;
    weight = 0;
    waist = 0;
};

void BodyFatCalculator::getFactors() {
  	int gender;
    std::cout << "Enter your gender [0 - Female, 1 - Male]: ";
    std::cin >> gender;

    if (gender == 0) {
        std::cout << "You selected : Female" << std::endl;
    } else if (gender == 1) {
        std::cout << "You selected : Male" << std::endl;
    }

    double weight;
    std::cout << "Enter your weight [e.g., 241.3] ";
    std::cin >> weight;

    double waist;
    std::cout << "Enter your waist [e.g., 44.5] ";
    std::cin >> waist;

    calculate(gender, weight, waist);
};

void BodyFatCalculator::calculate(int gender, double weight, double waist) {
    if ( gender == 1) {
         double factor1 = (weight * 1.082) + 94.42;
         double factor2 = (waist * 4.15);
         double leanBodyMass = factor1 - factor2;
         double bodyFatWeight = weight - leanBodyMass;
         double bodyFatPercent = (bodyFatWeight * 100) / weight;

         std::cout << "Your body fat percentage is : " << (int)(bodyFatPercent * 100) / 100.0 << "%" << std::endl;
    } else if (gender == 0) {
        std::cout << "We will need a few more measurements. " << std::endl;

        double wrist;
        std::cout << "Enter your wrist [e.g., 5.5] ";
        std::cin >> wrist;

        double hip;
        std::cout << "Enter your hip [e.g., 25] ";
        std::cin >> hip;

        double forearm;
        std::cout << "Enter your forearm [e.g., 9.0] ";
        std::cin >> forearm;

        double factor1 = (weight * 0.732) + 8.987;
        double factor2 = wrist / 3.140;
        double factor3 = waist * 0.157;
        double factor4 = hip * 0.249;
        double factor5 = forearm * 0.434;
        double leanBodyMass = factor1 + factor2 - factor3 - factor4 + factor5;
        double bodyFatWeight = weight - leanBodyMass;
        double bodyFatPercent = (bodyFatWeight * 100) / weight;

        std::cout << "Your body fat percentage is : " << (int)(bodyFatPercent * 100) / 100.0 << "%" << std::endl;
    }
};