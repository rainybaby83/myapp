/* ==================================================================
 * JChartLib is a open source chart library for the Java(tm) platform
 * ==================================================================
 *
 * (C) Copyright 2011-2014 Silvio Schneider suvi.org  
 * Licence: GPLv2
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
package bitagentur.renderer;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.*;

/**
 *
 * @author Silvio Schneider
 */
public abstract class JChartLibRender implements I_JChartLibRenderer {

    /**
     * The chart with all the data
     */
    protected bitagentur.chart.JChartLibBaseChart chart;
    /**
     * default fonts
     */
    private static final Font DEFAULT_FONT = new Font("Helvetica", Font.BOLD, 12);
    private static final Font DEFAULT_FONT_AXISNAMES = new Font("Helvetica", Font.BOLD, 12);
    private static final Font DEFAULT_FONT_TITLE = new Font("Helvetica", Font.BOLD, 14);
    //public static final Font DEFAULT_FONT = new Font("Verdana", Font.BOLD, 12); //also a nice font
    /**
     * default text colour
     */
    private static final Paint DEFAULT_TEXT_PAINT = Color.black;
    /**
     * default background colour chart
     */
    private static final Paint DEFAULT_BACKGROUND_PAINT = Color.white;  //white for the chart background
    /**
     * default background colour
     */
    private static final Paint DEFAULT_PLOT_PAINT = new Color(238, 238, 238);  //light gray
    /**
     * default tickline colour
     */
    private static final Paint DEFAULT_TICKLINE_PAINT = new Color(227, 227, 227);  //light gray
    /**
     * font used to display the title
     */
    protected Font titlefont;
    /**
     * font used to display the title
     */
    protected Font axisfont;
    /**
     * Paint used to draw the background of the chart
     */
    protected Paint backgroundPaint;
    /**
     * Paint used to draw the background of data area (plot) chart
     */
    protected Paint plotPaint;
    /**
     * paint for the title text
     */
    protected Paint paintcolor;
    /**
     * font used to display the axis Names
     */
    protected Font font;
    /**
     * JChartlib Note
     */
    protected String note = "JChartLib by Silvio Schneider";
    /**
     * paint for the title text
     */
    protected Paint ticklinePaint;
    
    /**
     * The licence file
     */
    private static String licencefileStr = "jchartlib.lic";
    
    public JChartLibRender(bitagentur.chart.JChartLibBaseChart chart) {
        this.chart = chart;
        //Set Font and color
        this.font = DEFAULT_FONT;
        this.paintcolor = DEFAULT_TEXT_PAINT;
        this.backgroundPaint = DEFAULT_BACKGROUND_PAINT;
        this.plotPaint = DEFAULT_PLOT_PAINT;
        this.ticklinePaint = DEFAULT_TICKLINE_PAINT;
        this.titlefont = DEFAULT_FONT_TITLE;
        this.axisfont = DEFAULT_FONT_AXISNAMES;
        
    }

    /**
     * Draws the chart on a Java 2D graphics device <P>
     *
     * @param g2 the graphics device.
     */
    @Override
    public void draw(Graphics2D g2) {
        //查一下谁调用
        //fill whole area
        g2.setPaint(this.backgroundPaint);
        g2.fill(g2.getClip());
        //starting from lowest layer
        drawNote(g2);

        int drawWidth = g2.getClipBounds().width;
        int drawHeight = g2.getClipBounds().height;

        //生成绘图区
        Rectangle2D chartArea = new Rectangle2D.Double(0, 0, drawWidth , drawHeight);
        //Font antialising on
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        //绘制标题
//        drawTitle(chart.getTitle(), g2);
        // ensure no drawing occurs outside chart area...
        Shape savedClip = g2.getClip();
        //Fill whole Chart with one color
        g2.clip(chartArea);  //Shape intersection
        // draw the chart background
        if (this.backgroundPaint != null) {
            g2.setPaint(this.backgroundPaint);
            g2.fill(chartArea); //Java2D Drawing method
        }
        //Definining where the data chart is placed
        Rectangle2D dataArea = Java2d_Draw_Util.shrinkRectagle2D(chartArea, 30, 0, 0);
        //Check if licence has finished
        if (licCheck() == false) {            
            drawTrialEnd(g2);
        } else {
            // draw the chart (axes and data visualisation)
            drawPlot(g2, dataArea);
            g2.setClip(savedClip);
            //draw the Axis Names at the sides of the data Area
            drawAxisNames(g2, dataArea);
            //不要画图例，注释掉
//            drawLegend(g2, dataArea);
        }
    }
    
    protected abstract void drawPlot(Graphics2D g2, Rectangle2D dataArea);

    /**
     * Draws the chart title
     *
     * @param title the title
     * @param g2 the graphics device
     */
    protected void drawTitle(String title, Graphics2D g2) {
        //Checking Parameters
        if (title == null) {
            title = "";
        }
        //Creating a rectangle on top to place the title in
        int width = g2.getClipBounds().width;
        int height = 20;
        double x = (g2.getClipBounds().width - width) / 2.0;
        double y = 0;
        Rectangle2D titleArea = new Rectangle2D.Double(x, y, width, height);
        //draw title on top
        Java2d_Draw_Util.drawTextCenteredTop(g2, titleArea, title, this.titlefont, this.paintcolor);
    }

    /**
     * Draw drawAxisNames
     *
     * @param g2 the graphics device
     * @param dataArea the data area
     */
    protected void drawAxisNames(Graphics2D g2, Rectangle2D dataArea) {
        //craw X Axis
        Java2d_Draw_Util.drawTextCenteredBottom(g2, dataArea, 55, chart.getXAxis(), axisfont, Color.BLACK);
        //draw Y Axis
        Java2d_Draw_Util.drawTextCenteredVerticalLeft(g2, dataArea, 0, chart.getYAxis(), axisfont, Color.BLACK);
    }

    /**
     * 画图例
     *
     * @param g2 the graphics device
     * @param dataArea the data area
     */
    protected void drawLegend(Graphics2D g2, Rectangle2D dataArea) {
        //get the Series Names
        List<String> names = chart.getSeriesNames();
        //calculate rectangle size
        int recwidth = 0;
        int textoffset = (int) dataArea.getMinX() + 10;
        g2.setFont(font);
        FontMetrics fm = g2.getFontMetrics();
        JChartLibColor chartColors = new JChartLibColor();
        int nr = 1;
        for (String name : names) {
            //draw legend  [] seriesname
            Color color = chartColors.getColor(nr);
            //g2.setPaint(chartColors.getColor(nr));
            //g2.fill(new Rectangle2D.Double(recwidth+textoffset,(int)g2.getClipBounds().getMaxY()-30,10,10));
            Java2d_Draw_Util.drawFilledRectangleWithShadow(g2, recwidth + textoffset, (int) g2.getClipBounds().getMaxY() - 30, 10, 10, color);
            recwidth += 30;
            g2.setPaint(Color.red);
            g2.drawString("", recwidth + textoffset, (int) g2.getClipBounds().getMaxY() - 20);
            //next item
            recwidth += 10 + fm.stringWidth(name);
            nr++;
        }
        //draw a rectangle around it
        double x = dataArea.getMinX();
        g2.setStroke(new BasicStroke(1));
        RoundRectangle2D rect = new RoundRectangle2D.Double(x, g2.getClipBounds().getMaxY() - 40, recwidth + 10, 30, 10, 10);
        g2.draw(rect);
    }

    /**
     * Writing the chart to an image
     *
     * @param filename ex "C:\file.jpg"
     * @param width
     * @param height
     * @param Imagetype jpg or png
     */
    public void createImage(String filename, int width, int height, String Imagetype) throws IOException {
        //Create a Buffered Image
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = image.createGraphics();
        g2.setClip(0, 0, width, height);
        draw(g2);
        g2.dispose();
        //Save it as JPG
        File outputfile = new File(filename);
        ImageIO.write(image, Imagetype, outputfile); //throws IOException
        //System.out.println("File saved to "+outputfile.getAbsolutePath()); //Debug statement
    }
    
    @Override
    public void createJPG(String filename, int width, int height) throws IOException {
        createImage(filename, width, height, "jpg");
    }
    
    @Override
    public void createPNG(String filename, int width, int height) throws IOException {
        createImage(filename, width, height, "png");
    }

    /**
     * Draw JChartLibNote
     *
     * @param g2 the graphics device
     */
    protected void drawNote(Graphics2D g2) {
        //Set Font and font color
        g2.setPaint(Color.LIGHT_GRAY);
        g2.setFont(font);
        FontMetrics fm = g2.getFontMetrics();
        //Draw the note
        float posX = g2.getClipBounds().width - fm.stringWidth(note) - 5;
        float posY = g2.getClipBounds().height - 10;
        //Java2D Drawing method
        g2.drawString(note, posX, posY);
    }
    
    private boolean licCheck() {
        return true;
        //LicChecker licChecker = new LicChecker();
        //return licChecker.checkLicence();
    }

    /**
     * Draws the data with and out of trial statement
     *
     * @param g2 the graphics device.
     */
    private void drawTrialEnd(Graphics2D g2) {
        String text = "Thank you for using JChartlib. The evaluation or licence period has ended. "
                + "For further using it a new licence is nessecary. "
                + "No program changes are nessecary. Your program will continue to work once a new licence is installed. "
                + "To purchase a licence please email to contact@suvi.org  ";
        //Creating a rectangle on top to place the text in
        int width = g2.getClipBounds().width;
        int height = g2.getClipBounds().height;
        double x = (g2.getClipBounds().width - width) / 2.0;
        double y = 0;
        Rectangle2D textArea = new Rectangle2D.Double(x, y, width, height);
        //draw text centered
        Java2d_Draw_Util.drawTextLeftWrap(g2, textArea, text, this.titlefont, this.paintcolor);        
    }
}
