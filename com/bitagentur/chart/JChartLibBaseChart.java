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
package com.bitagentur.chart;

import com.bitagentur.renderer.I_JChartLibRenderer;
import com.bitagentur.renderer.JChartLibLinechartRenderer;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Chart with title and data
 *
 * @author Silvio Schneider
 */
public class JChartLibBaseChart {

    /**
     * Title of the chart
     */
    private String title;
    /**
     * text for xAxis
     */
    private String xAxis;
    /**
     * text for yAxis
     */
    private String yAxis;
    /**
     * DataSet for values
     */
    private com.bitagentur.data.JChartLibDataSet dataSet;
    private I_JChartLibRenderer renderer;

    /**
     * Basic chart constuctor
     *
     * @param title Title of the chart
     * @param xAxis name of X Axis
     * @param yAxis name of Y Axis
     * @param dataSet data
     */
    public JChartLibBaseChart(String title, String xAxis, String yAxis, com.bitagentur.data.JChartLibDataSet dataSet) {
        this.title = title;
        this.xAxis = xAxis;
        this.yAxis = yAxis;
        this.dataSet = dataSet;
        renderer = new JChartLibLinechartRenderer(this);
    }

    public void setDataSet(com.bitagentur.data.JChartLibDataSet dataSet) {
        this.dataSet = dataSet;
    }


    /**
     * get the title for this chart
     */
    public String getTitle() {
        if (title == null) {
            return "";
        }
        return title;
    }

    /**
     * set the title for this chart
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * get the X Axis Name
     */
    public String getXAxis() {
        return xAxis;
    }

    /**
     * set the X Axis Name
     */
    public void setXAxis(String xAxis) {
        this.xAxis = xAxis;
    }

    /**
     * get the Y Axis Name
     */
    public String getYAxis() {
        return yAxis;
    }

    /**
     * set the Y Axis Name
     */
    public void setYAxis(String yAxis) {
        this.yAxis = yAxis;
    }

    /**
     * get the renderer for drawings
     *
     * @param renderer the new renderer
     */
    public void setRender(I_JChartLibRenderer renderer) {
        if (renderer != null) {
            this.renderer = renderer;
        }
    }

    /**
     * get the renderer for drawings
     */
    public I_JChartLibRenderer getRender() {
        return renderer;
    }

    /**
     * get the X Data Range, starting from 0 max X value
     */
    public int getXDataRange() {
        List<com.bitagentur.data.JChartLibSerie> series = this.dataSet.getSeries();
        int range = 1;
        //Check all series for highest amount of values
        for (com.bitagentur.data.JChartLibSerie serie : series) {
            int nrvalues = serie.numberOfValues() - 1;
            if (nrvalues > range) {
                range = nrvalues;
            }
        }
        return range;
    }

    /**
     * get the Y Data Range maximum value max Y value
     */
    public int getYDataRangeMax() {
        List<com.bitagentur.data.JChartLibSerie> series = this.dataSet.getSeries();
        int range = 1;
        //Check all series for highest value
        for (com.bitagentur.data.JChartLibSerie serie : series) {
            double highestValue = serie.highestValue();
            if (highestValue > range) {
                range = (int) Math.ceil(highestValue); //always round up
            }
        }
        return range;
    }

    /**
     * get the Y Data Range minimum value min Y value
     */
    public int getYDataRangeMin() {
        List<com.bitagentur.data.JChartLibSerie> series = this.dataSet.getSeries();
        int range = 0;
        //Check all series for highest value
        for (com.bitagentur.data.JChartLibSerie serie : series) {
            double lowestValue = serie.lowestValue();
            if (lowestValue < range) {
                range = (int) Math.floor(lowestValue); //always round down
            }
        }
        return range;
    }

    /**
     * checks if the first of the dataseries has values with dates
     *
     * @return true if it contains data values
     */
    public boolean hasDateValues() {
        com.bitagentur.data.JChartLibSerie serie = getFirstSerie();
        if (serie == null) {
            return false;
        }
        return serie.hasDatevalues();
    }

    /**
     * get the DataSeries
     *
     * @return DataSeries
     */
    public List<com.bitagentur.data.JChartLibSerie> getDataSeries() {
        return dataSet.getSeries();
    }

    /**
     * returns the first dataserie
     */
    public com.bitagentur.data.JChartLibSerie getFirstSerie() {
        return dataSet.getSeries().iterator().next();
    }

    /**
     * get the series Names
     *
     * @return List with the Series Names
     */
    public List<String> getSeriesNames() {
        ArrayList<String> result = new ArrayList<String>();
        for (com.bitagentur.data.JChartLibSerie serie : dataSet.getSeries()) {
            result.add(serie.getTitle());
        }
        return result;
    }

    /**
     * save as JPG
     *
     * @param filename ex "C:\chart.jpg"
     * @param width
     * @param height
     */
    public void saveAsJPEG(String filename, int width, int height) throws IOException {
        //use the renderer to save chart
        renderer.createJPG(filename, width, height);
    }

    /**
     * save as PNG
     *
     * @param filename ex "C:\chart.png"
     * @param width
     * @param height
     */
    public void saveAsPNG(String filename, int width, int height) throws IOException {
        //use the renderer to save chart
        renderer.createPNG(filename, width, height);
    }
}
