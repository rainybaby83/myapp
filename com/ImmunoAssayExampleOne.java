package com;/*      ImmunoAssayExampleOne
 *
 *       An example program illustrating the use of the ImmunoAssay class
 *       This example enters the assay data via the program enter methods,
 *       fits the data to a five parameter logistic equation,
 *       requests a concentration for a given response and
 *       prints an analysis of the fit.
 *
 *       See example two for reading data from a text file and
 *       for comparison of fitting equations
 *
 *       Mick Flanagan
 *       7 February 2011
 */

import flanagan.physchem.ImmunoAssay;
import flanagan.io.Db;

public class ImmunoAssayExampleOne{
    public static void main(String[] args){

        // Title
        String title = "ImmunoAssay Example One";

        // Analyte concentrations
        double[] analyteConcentrations = {0.0, 0.5, 1.0, 1.5, 2.0, 2.5, 3.0, 3.5, 4.0, 4.5, 5.0, 5.5, 6.0, 6.5, 10};

        // assay responses
        double[] assayResponses = {0.0, 0.05, 0.1, 0.6, 1.0, 1.5, 2.0, 2.5, 3.0, 3.4, 3.5, 3.7, 3.9, 3.94, 3.95};

        // Create an instance of ImmunoAssay
        ImmunoAssay assay = new ImmunoAssay(title);

        // Enter analyte concentrations
        assay.enterAnalyteConcns(analyteConcentrations);

        // Enter assay responses
        assay.enterResponses(assayResponses);

        // Fit assay data to a five parameter logistic function
        assay.fiveParameterLogisticFit();
        assay.fourParameterLogisticFit();
        assay.bestPolynomialFit();

        // Print an analysis of the fit to a test file named ImmunoAssayOneOutput.txt
//        assay.print("ImmunoAssayOneOutput.txt");

//        // Find concentration for a given response i.e. of a sample of unknown concentration
//        double sampleResponse = Db.readDouble("Sample assay response");
//        double sampleConcn = assay.getSampleConcn(sampleResponse);
//        double sampleError = assay.getSampleConcnError();
//
//        // Display the estimated sample concentration and its estimated error
//        System.out.println("Sample assay response = " + sampleResponse);
//        System.out.println("Estimated sample analyte concentration = " + sampleConcn);
//        System.out.println("Estimated concentration error = " + sampleError);
    }
}
