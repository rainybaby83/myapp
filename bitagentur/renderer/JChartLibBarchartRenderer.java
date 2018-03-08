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
package bitagentur.renderer;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import java.util.List;

/**
 * Renders a Bar chart
 * 
 * @author Silvio Schneider
 */
public class JChartLibBarchartRenderer extends JChartLibRender {

    /**
     * Default Constructor
     */
    public JChartLibBarchartRenderer(bitagentur.chart.JChartLibBaseChart chart) {
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
        drawTicksMarksAndLabels(g2, cursor, dataArea);
        drawAxisLines(g2, cursor, dataArea);
        drawData(g2, cursor, dataArea);
    }

    /**
     * Calculates the tick labels for the axis, storing the
     * results in the tick label list (ready for drawing).
     * Intelligent Ticks, depending on range 1-10, 1-100,1-1000 and so on
     *
     * @param g2  the graphics device.
     * @param dataArea  the area in which the data should be drawn.
     *
     * @return A list of ticks.
     */
    protected List<Number> generateTicks(int range) {
        List<Number> result = new java.util.ArrayList<Number>();
        //Examples Range = 4  returns 0,1,2,3,4
        //Examples Range = 32  returns 0,10,20,30
        //calculate factor
        int factor = 1;
        while (range > 10) {
            range = range / 10;
            factor = factor * 10;
        }
        for (int i = 0; i <= range; i++) {
            result.add(new Integer(i) * factor);
        }
        return result;
    }
    
        /**
     * Draws the axis line, tick marks and tick mark labels. Horizontal Ticks is one tick per group of bars
     *
     * @param g2 the graphics device.
     * @param cursor the cursor.
     * @param dataArea the data area.
     */
    protected void drawTicksMarksAndLabels(Graphics2D g2, double cursor, Rectangle2D dataArea) {
        double posX = dataArea.getX();
        double posY = cursor;
        //Draw the vertical backgroundbars
        int maxrange = chart.getYDataRangeMax();
        int minrange = chart.getYDataRangeMin();
        List<Number> ticks = generateTicks(minrange, maxrange); //handling negative values
        double tickspaceY = dataArea.getHeight() / (ticks.size() - 1);
        g2.setPaint(Color.WHITE);
        for (int i = 1; i <= ticks.size(); i++) {
            //Make every second vertical section white
            if (i % 2 != 0) {
                g2.fillRect((int) posX, (int) (posY - tickspaceY), (int) dataArea.getWidth(), (int) tickspaceY);
            }
            posY -= tickspaceY;
        }

        //draw the horizontal ticks and ticklines
        maxrange = chart.getXDataRange() + 2; //+2 to have a space on the left and right of the bars
        ticks = generateTicks(maxrange);
        //calulate space from tick to tick
        double tickspaceX = dataArea.getWidth() / (ticks.size() - 1);
        posX = dataArea.getX();
        posY = cursor;
        g2.setPaint(Color.BLACK);
        g2.setFont(font);
        //calculating offset of negativ values
        double offsetY = zerolineOffset(dataArea);
        int i = 1;
        for (Number nr : ticks) {
            //don't draw first tick and the last tick
            if ((i != ticks.size()) && (i != 1)) {
                //draw the tick
                g2.drawLine((int) posX, (int) (posY - offsetY), (int) posX, (int) (posY + 10 - offsetY));
                //draw the tick String
                g2.drawString(nr.toString(), (float) posX - 3, (float) (posY + 25 - offsetY));
            }
            posX += tickspaceX;
            //draw the tick line
            Java2d_Draw_Util.drawLine(g2, posX, posY, posX, dataArea.getMinY(), this.ticklinePaint);
            i++;
        }
        g2.setPaint(Color.BLACK);
        //draw the vertical ticks
        maxrange = chart.getYDataRangeMax();
        ticks = generateTicks(minrange, maxrange);
        posX = dataArea.getX();
        posY = cursor;
        i = 1;
        for (Number nr : ticks) {
            //don't draw the last tick
            if (i != ticks.size()) {
                //draw the tick
                g2.drawLine((int) posX, (int) posY, (int) posX - 10, (int) posY);

                //draw the tick String
                g2.drawString(nr.toString(), (float) posX - 20, (float) posY + 3);
                posY -= tickspaceY;
            }
            //draw the tick line
            Java2d_Draw_Util.drawLine(g2, posX, posY, dataArea.getMaxX(), posY, this.ticklinePaint);
            i++;
        }
    }

    /**
     * Draws X and Y axis lines at the current cursor position and edge PRO: Also deals with negative values;
     *
     * @param g2 the graphics device
     * @param cursor the cursor position
     * @param dataArea the data area
     */
    protected void drawAxisLines(Graphics2D g2, double cursor, Rectangle2D dataArea) {
        //calculating offset of negativ values
        double offsetY = zerolineOffset(dataArea);
        //Drawing the Axis Lines
        Line2D axisLineHorizontal = new Line2D.Double(dataArea.getX(), cursor - offsetY, dataArea.getMaxX(), cursor - offsetY);
        Line2D axisLineVertical = new Line2D.Double(dataArea.getX(), cursor, dataArea.getX(), dataArea.getMinY());
        g2.setPaint(Color.BLACK);
        g2.draw(axisLineHorizontal);
        g2.draw(axisLineVertical);
        //Drawing the > and ^ Arrow
        Shape arrowRight = Java2d_Draw_Util.rightArrow();
        Shape arrowUp = Java2d_Draw_Util.upArrow();
        //draw the arrow
        AffineTransform transformer = new AffineTransform();
        transformer.setToTranslation(dataArea.getMaxX(), cursor - offsetY);
        //Move the arrow to the right position
        Shape shape = transformer.createTransformedShape(arrowRight);
        g2.draw(shape);
        transformer.setToTranslation(dataArea.getX(), dataArea.getMinY());
        shape = transformer.createTransformedShape(arrowUp);
        g2.draw(shape);
    }

    /**
     * Calculate Offset for negative values
     *
     * @param dataArea
     * @return
     */
    private double zerolineOffset(Rectangle2D dataArea) {
        //calculating offset of negativ values
        int maxrange = chart.getYDataRangeMax();
        int minrange = chart.getYDataRangeMin();
        List<Number> ticks = generateTicks(minrange, maxrange);
        int offset = 0;
        for (Number tick : ticks) {
            //increasing offset for each negativ value
            if (tick.intValue() < 0) {
                offset++;
            }
        }
        double tickspaceY = dataArea.getHeight() / (ticks.size() - 1);
        double offsetY = offset * tickspaceY;
        return offsetY;
    }

    /**
     * Draws the data visuals, barchart Draw Bars PRO: Also works with float and negative values
     *
     * @param g2 the graphics device.
     * @param cursor the cursor.
     * @param dataArea the data area.
     */
    protected void drawData(Graphics2D g2, double cursor, Rectangle2D dataArea) {
        //calculate rasterspace
        int range = chart.getXDataRange() + 2; //+2 to have a space on the left and right of the bars
        List<Number> ticks = generateTicks(range);
        double rasterspaceX = dataArea.getWidth() / (ticks.size() - 1);

        int rangeMaxY = chart.getYDataRangeMax();
        int rangeMinY = chart.getYDataRangeMin();
        range = rangeMaxY - rangeMinY;
        double rasterspaceY = (cursor - dataArea.getMinY()) / (range);
        //calculating offset of negativ values
        double offsetY = zerolineOffset(dataArea);
        JChartLibColor chartcolors = new JChartLibColor();
        //Iterate through Data Serie
        List<bitagentur.data.JChartLibSerie> series = chart.getDataSeries();
        int colornr = 1;
        double barWidth = rasterspaceX / (series.size() + 0.5); //Calculate with: Maxwidth / series
        int i = 0;
        double offset = (barWidth * series.size()) / 2; //Centering the chart to the tick
        for (bitagentur.data.JChartLibSerie serie : series) {
            double x1 = dataArea.getX() + rasterspaceX + (i * barWidth) - offset; //Start with space on the left
            //Set line color
            Color color = chartcolors.getColor(colornr);
            colornr++;
            //PRO Version Takes doubleValue here and handling negative values with offsetY
            for (Number value : serie.getValues()) {
                if (value.doubleValue() >= 0) {
                    double height = value.doubleValue() * rasterspaceY;
                    Java2d_Draw_Util.drawFilledTopRoundedRectangleWithShadow(g2, x1 + 3, cursor - height - offsetY, barWidth - 6, height, color);
                } else {
                    double height = value.doubleValue() * rasterspaceY * -1;
                    drawFilledTopRoundedRectangleWithShadowNegativ(g2, x1 + 3, cursor - offsetY, barWidth - 6, height, color);
                }
                //preparing for next bar
                x1 = x1 + rasterspaceX; // + step 
            }
            i++;
        }
    }

    /**
     * Draws a line, using the current color specified, between the points
     * <code>(x1,&nbsp;y1)</code> and
     * <code>(x2,&nbsp;y2)</code> in this graphics context's coordinate system.
     *
     * @param x1 the first point's <i>x</i> coordinate.
     * @param y1 the first point's <i>y</i> coordinate.
     */
    public static void drawFilledTopRoundedRectangleWithShadowNegativ(Graphics2D g2, double x1, double y1, double width, double height, Color color) {
        Paint paint = g2.getPaint();
        //draw shadow
        g2.setPaint(Color.GRAY);
        Composite composite = g2.getComposite();
        g2.setComposite(Java2d_Draw_Util.AC1);
        g2.fill(new RoundRectangle2D.Double(x1 + 2, y1 + 2, width, height, 20, 20));
        //due to composit it looks better without this second shadow
        //g2.fill(new Rectangle2D.Double(x1 + 2, y1 + 20 + 2, width, height - 20)); 
        //draw Rounded Rectangle
        g2.setComposite(composite);
        //We use GradientPaint for styling the bar
        Paint p = new GradientPaint((float) x1, (float) y1, color, (float) x1, (float) (y1 + height), color.darker().darker().darker());
        g2.setPaint(p);
        g2.fill(new RoundRectangle2D.Double(x1, y1, width, height, 20, 20));
        g2.fill(new Rectangle2D.Double(x1, y1, width, height - 20));
        g2.setPaint(paint);
    }

    /**
     * Calculates the tick labels for the axis, storing the results in the tick label list (ready for drawing).
     * Intelligent Ticks, depending on maxrange 1-10, 1-100,1-1000 and so on PRO:Also deals with negative values
     *
     * @param g2 the graphics device.
     * @param dataArea the area in which the data should be drawn.
     *
     * @return A list of ticks.
     */
    protected List<Number> generateTicks(int minrange, int maxrange) {
        List<Number> result = new java.util.ArrayList<Number>();
        //Examples Range = 0, 4  returns 0,1,2,3,4
        //Examples Range = -2, 2  returns -2,-1,0,1,2
        //Examples Range = 0, 32  returns 0,10,20,30
        //calculate factor
        int factor = 1;
        while (maxrange > 10) {
            maxrange = maxrange / 10;
            factor = factor * 10;
        }
        minrange = minrange / factor;
        for (int i = minrange; i <= maxrange; i++) {
            result.add(new Integer(i) * factor);
        }
        return result;
    }
    
}
