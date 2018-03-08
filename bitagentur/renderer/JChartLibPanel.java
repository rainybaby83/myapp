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


import bitagentur.chart.JChartLibBaseChart;
import bitagentur.renderer.I_JChartLibRenderer;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import javax.swing.JPanel;

public class JChartLibPanel extends JPanel {
    public static final int DEFAULT_WIDTH = 640;
    public static final int DEFAULT_HEIGHT = 480;
    private JChartLibBaseChart chart;

    public JChartLibPanel() {
        super();
    }

    public JChartLibPanel(JChartLibBaseChart chart) {
        this(chart, 640, 480);
    }

    public JChartLibPanel(JChartLibBaseChart chart, int width, int height) {
        this.setBackground(Color.white);
        this.setChart(chart);
        this.setPreferredSize(new Dimension(width, height));
        this.enableEvents(16L);
        this.enableEvents(32L);
    }

    public void setChart(JChartLibBaseChart chart) {
        this.chart = chart;
        this.repaint();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (this.chart != null) {
            Graphics2D g2 = (Graphics2D)g.create();
            AffineTransform saved = g2.getTransform();
            I_JChartLibRenderer renderer = this.chart.getRender();
//            g2.setClip(0,0,this.getWidth(),this.getHeight());
            renderer.draw(g2);
            g2.setTransform(saved);
            g2.dispose();
        }
    }
}

