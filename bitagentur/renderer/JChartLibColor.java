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

import java.awt.Color;
import java.util.Random;

/**
 *
 * @author Silvio Schneider
 */
public class JChartLibColor {


    /*    
    The whole color Palete
    
     */
    private static String[] colors;

    static {
        colors = new String[26];
        colors[0] = "000000";  //black;

        colors[1] = "709FC4"; //  lightblue
        colors[2] = "9EC46F"; //	lightgreen
        colors[3] = "c47070"; //	lightred 
        colors[4] = "7870c4"; //  darkviolet
        colors[5] = "bdc470"; //	darkyellow
        colors[6] = "C46F9E"; //  lightpink

        colors[7] = "74eff7"; //	lightblue
        colors[8] = "87f774"; //	lightgreen
        colors[9] = "f77474"; //  lightred
        colors[10] = "8a74f7"; //	lightviolet
        colors[11] = "f7f274"; //  lightyellow
        colors[12] = "f774e6"; //	pink
        colors[13] = "F79E74"; //  lightorange

        colors[14] = "1276c4"; //	blue
        colors[15] = "12c448"; //	green
        colors[16] = "c41212"; //	red
        colors[17] = "6112c4"; //	violet
        colors[18] = "bac412"; //	yellow
        colors[19] = "c412ad"; //	pink

        colors[20] = "07304f"; //  darkblue
        colors[21] = "0c4f07"; //  darnkgreen
        colors[22] = "4f0707"; //  darkred
        colors[23] = "26074f"; //  darkviolet
        colors[24] = "4e4f07"; //	darkyellow
        colors[25] = "4f0744"; //	darkpink
    }

    /**
     * Returns a start color, colors are starting with 1
     * nr 0 is black
     * colors are defined from 1 - 25. Higher than 25 gives a random color
     * 
     * @param nr
     * @return A Color 
     */
    public Color getColor(int nr) {
        //nr > 25 gives random color
        if (nr > 25) {
            Random r = new Random();
            Color randomcolor = new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255));
            return randomcolor;
        }
        //get color from color array
        String colorStr = colors[nr];
        //calculate r,g,b
        String subString = colorStr.substring(0, 2);
        int r = Integer.parseInt(subString, 16);
        subString = colorStr.substring(2, 4);
        int g = Integer.parseInt(subString, 16);
        subString = colorStr.substring(4, 6);
        int b = Integer.parseInt(subString, 16);
        Color retColor = new Color(r, g, b);
        return retColor;
    }
}
