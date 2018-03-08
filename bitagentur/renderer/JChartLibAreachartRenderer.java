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

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.util.List;

/**
 * Renders a chart
 * 
 * @author Silvio Schneider
 */
public class JChartLibAreachartRenderer extends JChartLibLinechartRenderer implements I_JChartLibRenderer {

    /** set to true to activate dots drawing */
    private boolean drawdots = false;
    /** set to ture to show min and max values of a line */
    private boolean showminmax = false;

    /**
     * Default Constructor for Pro Renderer, with advanced capabilities
     */
    public JChartLibAreachartRenderer(bitagentur.chart.JChartLibBaseChart chart) {
        super(chart);
        //this.note = "JChartLibPro";
    }

    /**
     * Draws the axis line, tick marks and tick mark labels.
     * Pro: Also deals with negative values;
     *
     * @param g2  the graphics device.
     * @param cursor  the cursor.
     * @param dataArea  the data area.
     */
    @Override
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

        //draw the horizontal ticks
        maxrange = chart.getXDataRange();
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
            //don't draw the last tick
            if (i != ticks.size()) {
                //draw first tick only if there are no negativ values
                if (offsetY == 0 || i != 1) {
                    //draw the tick
                    g2.drawLine((int) posX, (int) (posY - offsetY), (int) posX, (int) (posY + 10 - offsetY));
                    //draw the tick String
                    g2.drawString(nr.toString(), (float) posX - 3, (float) (posY + 25 - offsetY));
                }
                posX += tickspaceX;
            }
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
     * Draws X and Y axis lines at the current cursor position and edge
     * PRO: Also deals with negative values;
     *
     * @param g2        the graphics device
     * @param cursor    the cursor position
     * @param dataArea  the data area
     */
    @Override
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
     * Draws the data visuals, linechart
     * PRO: Also works with float and negative values
     *
     * @param g2  the graphics device.
     * @param cursor  the cursor.
     * @param dataArea  the data area.
     */
    @Override
    protected void drawData(Graphics2D g2, double cursor, Rectangle2D dataArea) {
        //calculate rasterspace
        int range = chart.getXDataRange();
        double rasterspaceX = dataArea.getWidth() / (range);
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
        //in the are chart it is the best to always have a line with stroke width 2 on top of the area.
        g2.setStroke(new BasicStroke(2));
        for (bitagentur.data.JChartLibSerie serie : series) {
            int i = 0;
            //Set line color
            Color color = chartcolors.getColor(colornr);
            colornr++;
            double x1 = dataArea.getX();
            double y1 = 0;
            double x2 = dataArea.getX();
            double y2 = 0;
            //PRO Version takes doubleValue here and handling negative values with offsetY
            for (Number value : serie.getValues()) {
                if (i == 0) {
                    //first point
                    y2 = cursor - (value.doubleValue() * rasterspaceY) - offsetY; //value * height + Offset for 0 line
                } else {
                    //moving the end point of the line to the front point
                    x1 = x2;
                    y1 = y2;
                    y2 = cursor - (value.doubleValue() * rasterspaceY) - offsetY; //value * height
                    x2 = x2 + rasterspaceX; // + step 
                    //Area instead of line
                    Java2d_Draw_Util.drawLineWithShadow(g2, x1, y1, x2, y2, color);
                    Java2d_Draw_Util.drawAreaWithShadow(g2, x1, y1, x2, y2, cursor- offsetY, color);
                                        //Pro only show dots on values
                    if (drawdots) {
                        Java2d_Draw_Util.drawCircle(g2, x1, y1, 2, color);
                    }
                }
                i++;
            }
            //PRO Only Drawing the last dot
            if (drawdots) {
                Java2d_Draw_Util.drawCircle(g2, x2, y2, 2, color);
            }

            //PRO only, mark min and max
            x1 = dataArea.getX();
            y1 = 0;
            x2 = dataArea.getX();
            y2 = 0;
            double minvalue = 0;
            double maxvalue = 0;
            double minPosX = x1;
            double minPosY = y1;
            double maxPosX = x1;
            double maxPosY = y1;
            i = 0;
            //PRO Version takes doubleValue here and handling negative values with offsetY
            for (Number value : serie.getValues()) {
                if (i == 0) {
                    //first point
                    y2 = cursor - (value.doubleValue() * rasterspaceY) - offsetY; //value * height + Offset for 0 line
                    //Pro only, mark min and max
                    minvalue = value.doubleValue();
                    minPosY = y2;
                    maxvalue = value.doubleValue();
                    maxPosY = y2;
                } else {
                    //moving the end point of the line to the front point
                    x1 = x2;
                    y1 = y2;
                    y2 = cursor - (value.doubleValue() * rasterspaceY) - offsetY; //value * height
                    x2 = x2 + rasterspaceX; // + step 
                    //Pro only, mark min and max
                    if (value.doubleValue() > maxvalue) {
                        maxvalue = value.doubleValue();
                        maxPosX = x2;
                        maxPosY = y2;
                    }
                    if (value.doubleValue() < minvalue) {
                        minvalue = value.doubleValue();
                        minPosX = x2;
                        minPosY = y2;
                    }
                }
                i++;
            }
            if (showminmax) {
                //draw circles on min and max
                Java2d_Draw_Util.drawCircle(g2, maxPosX, maxPosY, 2, color.brighter());
                Java2d_Draw_Util.drawCircle(g2, minPosX, minPosY, 2, color.darker());
                //write value of min and max
                //check if we are to far on the left and have to move a bit
                if(minPosX==70){
                    FontMetrics fm = g2.getFontMetrics();
                    minPosX = minPosX + (fm.stringWidth(""+minvalue) / 2)+5; //centering x position
                }
                if(maxPosX==70){
                    FontMetrics fm = g2.getFontMetrics();
                    maxPosX = maxPosX + (fm.stringWidth(""+maxvalue) / 2)+5; //centering x position
                }
                Java2d_Draw_Util.drawStringCentered(g2,""+maxvalue, maxPosX , maxPosY - 5);
                Java2d_Draw_Util.drawStringCentered(g2,""+minvalue, minPosX , minPosY + 15);
            }

        }
    }

    /**
     * Calculates the tick labels for the axis, storing the
     * results in the tick label list (ready for drawing).
     * Intelligent Ticks, depending on maxrange 1-10, 1-100,1-1000 and so on
     * PRO:Also deals with negative values
     *
     * @param g2  the graphics device.
     * @param dataArea  the area in which the data should be drawn.
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

    /**
     * get the bottom right note
     * @return note
     */
    public String getNote() {
        return note;
    }

    /**
     * set the bottom right note
     * @param note 
     */
    public void setNote(String note) {
        this.note = note;
    }

    /**
     * Are the dots to be drawn
     * @return true if dotes are on
     */
    public boolean isDrawdots() {
        return drawdots;
    }

    /**
     * set the dots to be drawn
     * @param drawdots true to activate dots
     */
    public void setDrawdots(boolean drawdots) {
        this.drawdots = drawdots;
    }

    /**
     * is the min and ma values shown?
     * @return true, if shown
     */
    public boolean isShowminmax() {
        return showminmax;
    }

    /**
     * set the min and max to be shown.
     * @param showminmax set to true to show min and max values of a line
     */
    public void setShowminmax(boolean showminmax) {
        this.showminmax = showminmax;
    }
}
