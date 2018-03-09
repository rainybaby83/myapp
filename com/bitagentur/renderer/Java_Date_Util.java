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

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Utility myClass for Dates
 *
 * @author Silvio Schneider
 */
public class Java_Date_Util {
    
     /**
     * Compares to dates if they are on the same day
     * @param date1
     * @param date2
     * @return true if same day
     */
    public static boolean datesOnSameDay(Date date1, Date date2) {
        SimpleDateFormat dateformated = new SimpleDateFormat("yyyyMMdd");
        String dateStr1 = dateformated.format(date1);
        String dateStr2 = dateformated.format(date2);
        return (dateStr1.equals(dateStr2));
    }
    
}
