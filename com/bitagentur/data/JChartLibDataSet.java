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
package com.bitagentur.data;

import java.util.ArrayList;
import java.util.List;

/**
 * DataSet myclass for holding the Data Series
 * 
 * @author Silvio Schneider
 */
public class JChartLibDataSet {

    private List<JChartLibSerie> series;
    
    /**
     * Standard Constructor
     */
    public JChartLibDataSet() {
        series = new ArrayList<JChartLibSerie>();
    }
    
    /**
     * add values
     */
    public void addDataSerie(String serieName, int[] values) {
         JChartLibSerie serie = new JChartLibSerie(serieName,values);
         series.add(serie);
    }
    
    /**
     * add Data Serie
     */
    public void addDataSerie(JChartLibSerie serie) {
         series.add(serie);
    }
    
    /**
     * Get Series
     * @return All Data Series
     */
    public List<JChartLibSerie> getSeries() {
        return series;
    }
            
    
}
