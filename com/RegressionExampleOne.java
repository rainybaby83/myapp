package com;
/*     Example of the use of the class Regression demonstrating
       the use of the non-linear regression method, Regression.simplexPlot
       in fitting data to the function, y = a + b.exp(-c.x)
       with b fixed and a and c unknown

       Michael Thomas Flanagan
       http://www.ee.ucl.ac.uk/~mflanaga/java/Regression.html
       April 2007

    Copyright (c) 2007 - 2014

*   PERMISSION TO COPY:
*   Permission to use, copy and modify this software and its documentation for 
*   NON-COMMERCIAL purposes is granted, without fee, provided that an acknowledgement 
*   to the author, Dr Michael Thomas Flanagan at www.ee.ucl.ac.uk/~mflanaga, 
*   appears in all copies and associated documentation or publications. 
*   
*   Public listing of the source codes on the internet is not permitted. 
*   
*   Redistribution of the source codes or of the flanagan.jar file is not permitted. 
*   
*   Redistribution in binary form of all or parts of these classes is not permitted. 
*   
*   Dr Michael Thomas Flanagan makes no representations about the suitability 
*   or fitness of the software for any or for a particular purpose. 
*   Dr Michael Thomas Flanagan shall not be liable for any damages suffered 
*   as a result of using, modifying or distributing this software or its derivatives.
*
***************************************************************************************/


import com.flanagan.analysis.Regression;
import com.flanagan.analysis.RegressionFunction;

import javax.swing.*;

// Class to evaluate the function y = a + b.exp(-c.x1)
// where b is fixed and best estimates are required for a and  c.
class FunctOne implements RegressionFunction {

         private double b = 0.0D;

         @Override
         public double function(double[ ] p, double[ ] x){
                  double y = p[0] + b*Math.exp(-p[1]*x[0]);
                  return y;
         }

         public void setB(double b){
            this.b = b;
         }
}

// Class to demonstrate non-linear regression method, Regression.simplex.
public class RegressionExampleOne{

         public static void main(String[] arg){

             // x data array
             double[] xArray = {-0.301029996,
                     0,
                     0.301029996,
                     0.602059991,
                     1.301029996,
                     2,
                     2.397940009,
                     2.698970004,
                     2.875061263,
                     3
             };

             // observed y data array
             double[] yArray = {
                     -1.210066919,
                     -0.735418271,
                     -0.607127255,
                     -0.410832609,
                     0.075765878,
                     0.772915521,
                     0.939549203,
                     1.333288038,
                     1.383134677,
                     1.624252179
             };

              // estimates of the standard deviations of y
              double[] sdArray = {0.5,0.45,0.55,0.44,0.46,0.51,0.56,0.48,0.5,0.45,0.55,0.44,0.46,0.51,0.56,0.48};


              // Create instances of the class holding the function, y = a + b.exp(-c.x), evaluation method
              FunctOne f1 = new FunctOne();

              // assign value to constant b in the function
              f1.setB(8.0D);

              // initial estimates of a and c in y = a + b.exp(-c.x)
              double[] start = new double[2];
              start[0] = 6.0D;      // initial estimate of a
              start[1] = 0.1D;      // initial estimate of c

              // initial step sizes for a and c in y = a + b.exp(-c.x)
              double[] step = new double[2];
             step[0] = 0.6D;      // initial step size for a
             step[1] = 0.05D;     // initial step size for c


                  // create an instance of Regression

             Regression reg = new Regression(xArray, yArray);

                  // call non-linear regression using default tolerance and maximum iterations and plot display option
//                  reg.simplexPlot(f1, start, step);
             reg.linearPlot();
//             JOptionPane.showMessageDialog(null, reg.getBestEstimates());
         }
}