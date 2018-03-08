package bitagentur.renderer;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LicChecker {

    private static String licencefileStr = "jchartlib.lic";

    /**
     * Standard Constructor
     */
    public LicChecker() {
    }

    /**
     * Check Licence file, if licence still valid
     *
     *
     * @return the number of clients that can connect. 0 if not valid
     */
    public boolean checkLicence() {
        boolean result = false;
        // read File
        BufferedReader br = null;
        FileInputStream fstream = null;
        String strLine;
        int lineNr = 1;
        try {
            File licenceFile = new File(licencefileStr);
            if (licenceFile.exists() == false) {
                licenceFile = new File("C:\\Program Files\\JChartLib\\"
                        + licencefileStr);
                if (licenceFile.exists() == false) {
                    //so no licence file, eval version runs till this fixed date: 01.03.2013
                    Date now = new Date();
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
                    Date endDate;
                    try {
                        endDate = formatter.parse("20130301");
                    } catch (ParseException e1) {
                        return false;
                    }
                    int dateresult = endDate.compareTo(now);
                    if (dateresult < 0) {
                        System.out.println("licence expired.");
                        return false;
                    } else {
                        return true;
                    }
                }
            }
            fstream = new FileInputStream(licenceFile);
            // Get the object of DataInputStream
            DataInputStream dataStream = new DataInputStream(fstream);
            br = new BufferedReader(new InputStreamReader(dataStream));
            // Read File Line by Line
            while ((strLine = br.readLine()) != null) {
                // # is a comment
                if (strLine.startsWith("#") == false) {
                    result = checkLicenseString(strLine);
                }
                lineNr++;
            }
        } catch (FileNotFoundException ex) {
            // no licence file found
            return false;
        } catch (IOException e) {
            System.out.println("ERROR: While reading licence File, linenumber:" + lineNr);
            return false;
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
                if (fstream != null) {
                    fstream.close();
                }
            } catch (IOException ex) {
                System.out.println(ex.toString());
            }
        }

        return result;
    }

    /**
     * Check if licence is valid
     *
     * LicenceString = yearmonthDateCnumberofclients Example 20121004 The licence string is decoded:
     *
     * to decode: Take each char from the string and print the int value of the chars. this generates a string that is
     * double in lenght, but only char characters. Example: 504849504948485267494848
     *
     * To encode: put to chars together Example 5+0 = 50, then make a char out of this int value. for example 50 = "2";
     *
     * @param licence String
     * @return the number of clients that can connect
     */
    private boolean checkLicenseString(String licence) {
        licence = licenceDecode(licence);
        String licdate = "";
        // Check if the date is still valid
        try {
            licdate = licence.substring(0, 8);
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("invalid licence file.");
            return false;
        }
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        Date dateStr;
        try {
            dateStr = formatter.parse(licdate);
        } catch (ParseException e1) {
            return false;
        }

        /*
         * Use compareTo method of java Date class to compare two date objects. compareTo returns value greater than 0
         * if first date is after another date, returns value less than 0 if first date is before another date and
         * returns 0 if both dates are equal.
         */
        Date now = new Date();
        int dateresult = dateStr.compareTo(now);
        if (dateresult < 0) {
            System.out.println("licence expired");
            return false;
        }

        return true;
    }

    private String licenceDecode(String licString) {
        // To encode: put to chars together Example 5+0 = 50, then make a char
        // out of this int value.
        // for example 50 = "2";
        String licence = "";
        int i = 0;
        String part = "";
        for (char ch : licString.toCharArray()) {
            part += ch;
            i++;
            if (i == 2) {
                i = 0;
                char newchar = (char) (new Integer(part).intValue());
                licence += newchar;
                part = "";
            }
        }
        return licence;
    }
}
