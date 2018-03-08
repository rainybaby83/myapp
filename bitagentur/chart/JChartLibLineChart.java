/* ==================================================================
 * JChartLib is a open source chart library for the Java(tm) platform
 * ==================================================================
 *
 * (C) Copyright 2000-2008, by Object Refinery Limited and Contributors.
 *
 * Project Info:   https://sourceforge.net/projects/jchartlib/
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
 * 
 */
package bitagentur.chart;

import bitagentur.renderer.JChartLibLinechartRenderer;

/**
 *
 * @author Silvio Schneider
 */
public class JChartLibLineChart extends JChartLibBaseChart {

    public JChartLibLineChart(String title, String xAxis, String yAxis, bitagentur.data.JChartLibDataSet dataSet) {
        super(title, xAxis, yAxis, dataSet);
        setRender(new JChartLibLinechartRenderer(this));
    }
    
    
    
}
