/* ==================================================================
 * JChartLib is a chart library for the Java(tm) platform
 * ==================================================================
 *
 * (C) Copyright 2011-2014 Silvio Schneider suvi.org  
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
package bitagentur.renderer;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.util.Iterator;
import java.util.List;

/**
 * Renders a Pie chart
 * 
 * @author Silvio Schneider
 */
public class JChartLibPiechartRenderer extends JChartLibRender {
    
    /** How many pieces to take out from the cake */
    private int takeout = 0;

    /**
     * Default Constructor
     */
    public JChartLibPiechartRenderer(bitagentur.chart.JChartLibBaseChart chart) {
        super(chart);
    }

    /**
     * Draws the plot within the specified area on a graphics device.
     *
     * @param g2  the graphics device.
     * @param area  the dataArea (in Java2D space).
     */
    @Override
    protected void drawPlot(Graphics2D g2, Rectangle2D dataArea) {
        //Fill data Area with single color, stripes will be added later
        g2.setPaint(plotPaint);
        g2.fill(dataArea); //Java2D Drawing method
        //Cursor is the postion of the bottom chart line
        double cursor = dataArea.getMaxY();
        drawData(g2, cursor, dataArea);
    }

    /**
     * Calculate the total amount of all values
     * @param serie
     * @return total amount
     */
    protected Number calucateTotalAmount(bitagentur.data.JChartLibSerie serie) {
        int total = 0;
        for (Number value : serie.getValues()) {
            total = total + value.intValue();
        }
        return total;
    }
    
    /**
     * Draws the data visuals, piechart
     * Pro: takes out pieces
     * Draws Pie
     *
     * @param g2        the graphics device.
     * @param cursor    the cursor.
     * @param dataArea  the data area.
     */
    protected void drawData(Graphics2D g2, double cursor, Rectangle2D dataArea) {
        //calculate grid for pies according to number of series //PRO ONLY!
        //Iterate through Data Serie //PRO ONLY!

        List<bitagentur.data.JChartLibSerie> series = chart.getDataSeries();
        //Make a pie out of the first serie
        bitagentur.data.JChartLibSerie serie = series.get(0);
        //Colors
        JChartLibColor chartcolors = new JChartLibColor();
        int colornr = 1;

        //Circle center and radius
        double radius = 1;
        if (dataArea.getHeight() > dataArea.getWidth()) {
            radius = (dataArea.getWidth() / 2) - 20;
        } else {
            radius = (dataArea.getHeight() / 2) - 20;
        }
        double centerX = dataArea.getCenterX();
        double centerY = dataArea.getCenterY();
        double startAngle = 0;

        Number totalamount = calucateTotalAmount(serie);
        double onepercent = totalamount.doubleValue() / 100;
        //Draw the pie
        Java2d_Draw_Util.drawFilledCircle(g2, centerX + 2, centerY + 2, (int) radius, Color.lightGray); //draw the shadow
        Iterator<String> it = serie.getTexts().iterator();
        int i = 1;
        for (Number value : serie.getValues()) {
            //Set slice color
            Color color = chartcolors.getColor(colornr);
            colornr++;
            g2.setPaint(color);
            //calucate size of slice
            double percentage = value.doubleValue() / onepercent / 100;
            double arcAngle = 360 * percentage;
            //calculate angle offset for label and take out
            double angleX = Math.cos(Math.toRadians(startAngle + (arcAngle / 2)));
            double angleY = Math.sin(Math.toRadians(startAngle + (arcAngle / 2)));
            double takeoutX = 0;
            double takeoutY = 0;
            //check if we have to take out the piece, PRO only
            if (takeout >= i) {
                takeoutX = (radius / 10) * angleX;
                takeoutY = (radius / 10) * angleY;
            }
            //draw slice of pie
            Java2d_Draw_Util.drawNiceFilledArc(g2, centerX + takeoutX, centerY - takeoutY, radius, startAngle, arcAngle, color);
            //draw the label
            String label = " " + value + " ";
            if (it.hasNext()) {
                label = it.next();
            }
            FontMetrics fm = g2.getFontMetrics();
            int stringOffsetX = fm.stringWidth(label);
            if (angleX < 0) {
                stringOffsetX = stringOffsetX * 2;
            }
            int stringOffsetY = fm.getHeight();
            if (angleY < 0) {
            }
            g2.drawString(label, (float) (centerX + takeoutX + ((radius + stringOffsetX) * angleX)), (float) (centerY - takeoutY - ((radius + stringOffsetY) * angleY)));

            //preparing for next slice
            startAngle = startAngle + arcAngle;
            i++;
        }
    }

    /**
     * Set how many pieces of the Pie should be taken a bit out.
     * @param takeout 
     */
    public void setTakeout(int takeout) {
        this.takeout = takeout;
    }
}
