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

import java.awt.Graphics2D;
import java.io.IOException;

/**
 * Interface for rendering a chart on a Java2D graphics device
 * 
 * @author Silvio Schneider
 */
public interface I_JChartLibRenderer {

    /**
     * Draws the chart on a Java 2D graphics device
     * <P>
     *
     * @param g2  the graphics device.
     */
    public void draw(Graphics2D g2);
    
     /**
     * save as JPG
     * @param filename ex "C:\chart.jpg"
     * @param width
     * @param height
     */
    public void createJPG(String filename, int width, int height) throws IOException;
    
    
    /**
     * save as PNG
     * @param filename ex "C:\chart.png"
     * @param width
     * @param height
     */
    public void createPNG(String filename, int width, int height) throws IOException;
    
}
