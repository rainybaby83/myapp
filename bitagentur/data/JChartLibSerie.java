/* ==================================================================
 * JChartLib is a open source chart library for the Java(tm) platform
 * ==================================================================
 *
 * (C) Copyright 2011 Silvio Schneider suvi.org  
 * Licence: GPLv2
 *
 * Project Info:  https://sourceforge.net/projects/jchartlib/
 *
 * This library is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public
 * License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301,
 * USA.
 *
 * [Java is a trademark or registered trademark of Sun Microsystems, Inc.
 * in the United States and other countries.]
 *
 */
package bitagentur.data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * Holds Series of Values
 * 
 * @author Silvio Schneider
 */
public class JChartLibSerie {

    /** title of the serie */
    private String title;
    /** stores the values */
    private List<Number> values;
    /** stores the dates */
    private List<Date> dates;
    /** stores the texts */
    private List<String> texts;

    /**
     * Standard Constructor
     * @param title Title of the serie
     */
    public JChartLibSerie(String title) {
        this(title, null);
    }

    /**
     * Standard Constuctor with values
     * @param title Title of the serie
     * @param values Values
     */
    public JChartLibSerie(String title, int[] values) {
        this.title = title;
        this.values = new ArrayList<Number>();
        this.texts = new ArrayList<String>();
        this.dates = new ArrayList<Date>();
        if (values != null) {
            for (int value : values) {
                addValue(value);
            }
        }
    }

    /**
     * Add a value
     * @param value value
     */
    public final void addValue(Number value) {
        values.add(value);
    }

    /**
     * Add a values that are assigned to a date
     * @param date date for the value
     * @param value value
     */
    public final void addValue(Date date, Number value) {
        values.add(value);
        dates.add(date);
    }
    
     /**
     * Add a values that are assigned to a date
     * @param text text for the value
     * @param value value
     */
    public final void addValue(String text, Number value) {
        values.add(value);
        texts.add(text);
    }

    /**
     * Title of the serie
     * @return title to be used in the chart legend
     */
    public String getTitle() {
        return title;
    }

    /**
     * get the values of this collection
     * @return Collection
     */
    public Collection<Number> getValues() {
        return values;
    }

    /**
     * Has this serie values with dates
     * @return true if there are dates
     */
    public boolean hasDatevalues() {
        return (!dates.isEmpty());
    }

    /**
     * get the values and dates of this collection
     * @return Collection
     */
    public Collection<Date> getDates() {
        return dates;
    }
    
     /**
     * get the values and dates of this collection
     * @return Collection
     */
    public Collection<String> getTexts() {
        return texts;
    }

    /**
     * Number of all values
     * @return number of all values in serie
     */
    public int numberOfValues() {
        return values.size();
    }

    /**
     * Highest value of all values, or 1 if no higher value
     * @return highest number of all values in a serie
     */
    public double highestValue() {
        double resultVal = 1;
        for (Number value : values) {
            if (value.doubleValue() > resultVal) {
                resultVal = value.doubleValue();
            }
        }
        return resultVal;
    }

    /**
     * Lowest value of all values, or 0 if no lower value
     * @return lowest number of all values in a serie
     */
    public double lowestValue() {
        double resultVal = 0;
        for (Number value : values) {
            if (value.doubleValue() < resultVal) {
                resultVal = value.doubleValue();
            }
        }
        return resultVal;
    }
}
