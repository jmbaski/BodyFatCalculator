//
// Created by jbask on 5/11/2025.
//

#ifndef BODYFATCALCULATOR_H
#define BODYFATCALCULATOR_H

class BodyFatCalculator {
     private:
       int gender;
       double weight;
       double waist;
    public:
      BodyFatCalculator();
      void reset();
      void getFactors();
      void calculate(int gender, double weight, double waist);
};

#endif //BODYFATCALCULATOR_H
