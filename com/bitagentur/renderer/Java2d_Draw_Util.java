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
package com.bitagentur.renderer;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Polygon;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;

/**
 *
 * UseFull Java2D Utilities and Shapes
 *
 * @author Silvio Schneider
 */
public class Java2d_Draw_Util {

    public static AlphaComposite AC1 = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.4f);

    public static void drawStringCentered(Graphics2D g2, String text, double x, double y) {
        FontMetrics fm = g2.getFontMetrics();
        x = x - (fm.stringWidth(text) / 2); //centering x position
        g2.drawString(text, (int) x, (int) y);
    }

    /**
     * Draws a the title horizontally and in center within the specified area
     *
     * @param g2 the graphics device
     * @param area the area for the text
     * @param text text to draw
     */
    public static void drawTextCenteredTop(Graphics2D g2, Rectangle2D area, String text, Font font, Paint paintcolor) {
        //Set Font and background color
        g2.setFont(font);
        g2.setPaint(paintcolor);
        //Calculate position for title, centered in Area
        FontMetrics fm = g2.getFontMetrics();
        float posX = (float) area.getCenterX() - (fm.stringWidth(text) / 2);
        // How far above the baseline can the font go?
        float ascent = fm.getMaxAscent();
        // How far below the baseline?
        float descent = fm.getMaxDescent();
        // Use the vertical height of this font to find
        // the vertical starting coordinate
        float posY = (float) area.getHeight() / 2 - descent / 2 + ascent / 2;
        //Java2D Drawing method
        g2.drawString(text, posX, posY);
    }
    
        /**
     * Draws a the title horizontally and in center within the specified area
     *
     * @param g2 the graphics device
     * @param area the area for the text
     * @param text text to draw
     */
    public static void drawTextLeftWrap(Graphics2D g2, Rectangle2D area, String text, Font font, Paint paintcolor) {
        //Set Font and background color
        g2.setFont(font);
        g2.setPaint(paintcolor);
        //Calculate position for title, centered in Area
        FontMetrics fm = g2.getFontMetrics();
        //float posX = (float) area.getCenterX() - (fm.stringWidth(text) / 2);
        float posX = (float) area.getMinX()+10;
        // How far above the baseline can the font go?
        float ascent = fm.getMaxAscent();
        // How far below the baseline?
        float descent = fm.getMaxDescent();
        // Use the vertical height of this font to find
        // the vertical starting coordinate
        float posY = (float) area.getHeight() / 2 - descent / 2 + ascent / 2;
        //Java2D Drawing method
        drawStringWrap(g2,text, posX, posY, area.getWidth());
    }

    public static void drawStringWrap(Graphics2D g2, String s, float x, float y, double width) {
        // FontMetrics gives us information about the width,
        // height, etc. of the current Graphics object's Font.
        FontMetrics fm = g2.getFontMetrics();
        int lineHeight = fm.getHeight();
        float curX = x;
        float curY = y;
        String[] words = s.split(" ");

        for (String word : words) {
            // Find out thw width of the word.
            int wordWidth = fm.stringWidth(word + " ");

            // If text exceeds the width, then move to next line.
            if (curX + wordWidth >= x + width) {
                curY += lineHeight;
                curX = x;
            }
            g2.drawString(word, curX, curY);
            // Move over to the right for next word.
            curX += wordWidth;
        }
    }

    /**
     * Draws a the title horizontally and in center within the specified area
     *
     * @param g2 the graphics device
     * @param area area to align
     * @param offsetY offset to move the Text manually up or down.
     * @param text text to draw
     * @param font font for text
     * @param paintcolor color for text
     */
    public static void drawTextCenteredBottom(Graphics2D g2, Rectangle2D area, float offsetY, String text, Font font, Paint paintcolor) {
        //Set Font and background color
        g2.setFont(font);
        g2.setPaint(paintcolor);
        //Calculate position for title, centered in Area
        FontMetrics fm = g2.getFontMetrics();
        float posX = (float) area.getCenterX() - (fm.stringWidth(text) / 2);  //align to rectangle
        //float posX = (float) g2.getClipBounds().getCenterX() - (fm.stringWidth(text) / 2); //align to graphics area
        // Use the vertical height of this font to find
        // the vertical starting coordinate
        float posY = (float) area.getMaxY() - fm.getHeight() + offsetY;//align to rectangle
        //float posY = (float) g2.getClipBounds().height - fm.getHeight() + 2; //align to graphics area 
        //Java2D Drawing method
        g2.drawString(text, posX, posY);
    }

    /**
     * Draws a the title horizontally and in center within the specified area
     *
     * @param g2 the graphics device
     * @param area area to align
     * @param offsetX offset to move the Text manually left or right
     * @param text text to draw
     * @param font font for text
     * @param paintcolor color for text
     */
    public static void drawTextCenteredVerticalLeft(Graphics2D g2, Rectangle2D area, float offsetX, String text, Font font, Paint paintcolor) {
        //Set Font and background color
        g2.setFont(font);
        g2.setPaint(paintcolor);
        //Calculate position for title, centered in Area
        FontMetrics fm = g2.getFontMetrics();
        float posX = (float) area.getMinX() + fm.getHeight() + offsetX;
        // Use the vertical height of this font to find
        // the vertical starting coordinate
        float posY = (float) area.getCenterY() + (fm.stringWidth(text) / 2); //align to rectangle
        //float posY = (float) g2.getClipBounds().getCenterY() + (fm.stringWidth(text) / 2); //align to graphics
        //Java2D Drawing method
        Font f = font.deriveFont(AffineTransform.getRotateInstance(-Math.PI / 2.0));
        g2.setFont(f);
        g2.drawString(text, posX, posY);
        g2.setFont(font);
    }

    /**
     * Creates a right Arrow
     *
     * @return a small right arrow
     */
    public static Polygon rightArrow() {
        Polygon arrow = new Polygon();
        arrow.addPoint(0, 0);
        arrow.addPoint(-2, -2);
        arrow.addPoint(-2, 2);
        return arrow;
    }

    /**
     * Creates a up Arrow
     *
     * @return a small up arrow
     */
    public static Polygon upArrow() {
        Polygon arrow = new Polygon();
        arrow.addPoint(0, 0);
        arrow.addPoint(-2, 2);
        arrow.addPoint(2, 2);
        return arrow;
    }

    /**
     * Shrinks an area by the space attributes.
     *
     * @param area the area to shrink
     * @param frame space to remove from all sides
     * @param indentionleft space to remove from the left, set 0 for no indention
     * @param indentionbottom space to remove from the bottom, set 0 for no indention
     * @return The result.
     */
    public static Rectangle2D shrinkRectagle2D(Rectangle2D area, double frame, double indentionleft, double indentionbottom) {
        Rectangle2D result = new Rectangle2D.Double();
        result.setRect(
                area.getX() + frame + indentionleft,
                area.getY() + frame - indentionbottom,
                area.getWidth() - frame - frame,
                area.getHeight() - frame - frame);
        return result;
    }

    /**
     * Draws a line, using the current color specified, between the points
     * <code>(x1,&nbsp;y1)</code> and
     * <code>(x2,&nbsp;y2)</code> in this graphics context's coordinate system.
     *
     * @param x1 the first point's <i>x</i> coordinate.
     * @param y1 the first point's <i>y</i> coordinate.
     * @param x2 the second point's <i>x</i> coordinate.
     * @param y2 the second point's <i>y</i> coordinate.
     */
    public static void drawLine(Graphics2D g2, double x1, double y1, double x2, double y2, Paint color) {
        Paint currentpaint = g2.getPaint();
        g2.setPaint(color);
        g2.drawLine((int) x1, (int) y1, (int) x2, (int) y2);
        g2.setPaint(currentpaint);
    }

    /**
     * Draws a line, using the current color specified, between the points
     * <code>(x1,&nbsp;y1)</code> and
     * <code>(x2,&nbsp;y2)</code> in this graphics context's coordinate system.
     *
     * @param x1 the first point's <i>x</i> coordinate.
     * @param y1 the first point's <i>y</i> coordinate.
     * @param x2 the second point's <i>x</i> coordinate.
     * @param y2 the second point's <i>y</i> coordinate.
     */
    public static void drawLineWithShadow(Graphics2D g2, double x1, double y1, double x2, double y2, Paint color) {
        Paint paint = g2.getPaint();
        //Line
        g2.setPaint(color);
        g2.drawLine((int) x1, (int) y1, (int) x2, (int) y2);
        //shadow line, looks nice, wen shadow is drawn second and overlayed
        g2.setPaint(Color.GRAY);
        Composite composite = g2.getComposite();
        g2.setComposite(AC1);
        g2.drawLine((int) x1, (int) y1 + 2, (int) x2, (int) y2 + 2);
        g2.setComposite(composite);

        g2.setPaint(paint);
    }

    /**
     * Draws a line, using the current color specified, between the points
     * <code>(x1,&nbsp;y1)</code> and
     * <code>(x2,&nbsp;y2)</code> in this graphics context's coordinate system.
     *
     * @param x1 the first point's <i>x</i> coordinate.
     * @param y1 the first point's <i>y</i> coordinate.
     */
    public static void drawFilledRectangleWithShadow(Graphics2D g2, double x1, double y1, double width, double height, Paint color) {
        Paint paint = g2.getPaint();
        //draw shadow
        g2.setPaint(Color.GRAY);
        Composite composite = g2.getComposite();
        g2.setComposite(AC1);
        g2.fill(new Rectangle2D.Double(x1 + 2, y1 + 2, width, height));
        g2.setComposite(composite);
        //draw Rect
        g2.setPaint(color);
        g2.fill(new Rectangle2D.Double(x1, y1, width, height));
        g2.setPaint(paint);
    }

    /**
     * Draws a line, using the current color specified, between the points
     * <code>(x1,&nbsp;y1)</code> and
     * <code>(x2,&nbsp;y2)</code> in this graphics context's coordinate system.
     *
     * @param x1 the first point's <i>x</i> coordinate.
     * @param y1 the first point's <i>y</i> coordinate.
     */
    public static void drawFilledTopRoundedRectangleWithShadow(Graphics2D g2, double x1, double y1, double width, double height, Color color) {
        Paint paint = g2.getPaint();
        //draw shadow
        g2.setPaint(Color.GRAY);
        Composite composite = g2.getComposite();
        g2.setComposite(AC1);
        g2.fill(new RoundRectangle2D.Double(x1 + 2, y1 + 2, width, height, 20, 20));
        //due to composit it looks better without this second shadow
        //g2.fill(new Rectangle2D.Double(x1 + 2, y1 + 20 + 2, width, height - 20)); 
        //draw Rounded Rectangle
        g2.setComposite(composite);
        //We use GradientPaint for styling the bar
        Paint p = new GradientPaint((float) x1, (float) y1, color, (float) x1, (float) (y1 + height), color.darker().darker().darker());
        g2.setPaint(p);
        g2.fill(new RoundRectangle2D.Double(x1, y1, width, height, 20, 20));
        g2.fill(new Rectangle2D.Double(x1, y1 + 20, width, height - 20));
        g2.setPaint(paint);
    }

    /**
     * Draws a circle
     *
     * @param g2 Java Graphics
     * @param x X-Cordinate
     * @param y Y-Cordinate
     * @param radius
     */
    public static void drawCircle(Graphics2D g2, double x, double y, int radius, Color color) {
        g2.setColor(color);
        g2.drawOval((int) (x - radius), (int) (y - radius), radius * 2, radius * 2);
    }

    /**
     * Draws a filled circle
     *
     * @param g2 Java Graphics
     * @param x X-Cordinate
     * @param y Y-Cordinate
     * @param radius
     */
    public static void drawFilledCircle(Graphics2D g2, double x, double y, int radius, Color color) {
        g2.setColor(color);
        g2.fillOval((int) (x - radius), (int) (y - radius), radius * 2, radius * 2);
    }

    /**
     * Draws a nicely filled arc
     *
     * @param x the <i>x</i> coordinate of the upper-left corner of the arc to be filled.
     * @param y the <i>y</i> coordinate of the upper-left corner of the arc to be filled.
     * @param width the width of the arc to be filled.
     * @param height the height of the arc to be filled.
     * @param startAngle the beginning angle.
     * @param arcAngle the angular extent of the arc, relative to the start angle.
     * @see java.awt.Graphics#drawArc
     */
    public static void drawNiceFilledArc(Graphics2D g2, double x, double y, double radius,
            double startAngle, double arcAngle, Color color) {
        //We use GradientPaint for styling the arc, to simulate a light source
        Paint p = new GradientPaint((float) (x - radius), (float) (y - radius), color.brighter(), (float) x, (float) y, color.darker().darker());
        g2.setPaint(p);
        //draw the arc
        //http://download.oracle.com/javase/6/docs/api/java/awt/Graphics.html#fillArc%28int,%20int,%20int,%20int,%20int,%20int%29
        g2.fillArc((int) (x - radius), (int) (y - radius), (int) (radius * 2), (int) (radius * 2), (int) startAngle, (int) arcAngle);
    }

    /**
     * Draws a nicely filled arc
     *
     * @param x the <i>x</i> coordinate of the upper-left corner of the arc to be filled.
     * @param y the <i>y</i> coordinate of the upper-left corner of the arc to be filled.
     * @param width the width of the arc to be filled.
     * @param height the height of the arc to be filled.
     * @param startAngle the beginning angle.
     * @param arcAngle the angular extent of the arc, relative to the start angle.
     * @see java.awt.Graphics#drawArc
     */
    public static void drawNiceArc(Graphics2D g2, double x, double y, double radius,
            double startAngle, double arcAngle, Color color) {
        //We use GradientPaint for styling the arc, to simulate a light source
        Paint p = new GradientPaint((float) (x - radius), (float) (y - radius), color.brighter(), (float) x, (float) y, color.darker().darker());
        g2.setPaint(p);
        //draw the arc
        //http://download.oracle.com/javase/6/docs/api/java/awt/Graphics.html#fillArc%28int,%20int,%20int,%20int,%20int,%20int%29
        g2.drawArc((int) (x - radius), (int) (y - radius), (int) (radius * 2), (int) (radius * 2), (int) startAngle, (int) arcAngle);
    }

    /*
     * Draws an area and fills it with the paintcolor Are is drawn with a transpance factor of 0.7
     */
    public static void drawAreaWithShadow(Graphics2D g2, double x1, double y1, double x2, double y2, double baseline, Paint color) {
        Paint paint = g2.getPaint();
        Composite composite = g2.getComposite();
        //Area
        g2.setPaint(color);
        g2.setComposite(AC1);
        Polygon polygon = new Polygon();
        polygon.addPoint((int) x1, (int) y1);
        polygon.addPoint((int) x2, (int) y2);
        polygon.addPoint((int) x2, (int) baseline);
        polygon.addPoint((int) x1, (int) baseline);
        g2.fillPolygon(polygon);
        //change back to orginal draw values
        g2.setComposite(composite);
        g2.setPaint(paint);
    }
}
