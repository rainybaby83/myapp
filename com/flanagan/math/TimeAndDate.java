//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.flanagan.math;

import com.flanagan.io.Db;
import java.util.Calendar;

public class TimeAndDate {
    private Calendar cal = Calendar.getInstance();
    private String dayOfTheWeek = null;
    private int dayOfTheMonth = 0;
    private String monthOfTheYear = null;
    private int monthAsInteger = 0;
    private int year = 0;
    private String fullDate = null;
    private String date = null;
    private String shortDateUK = null;
    private String shortDateUS = null;
    private String[] days = new String[]{"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
    private String[] months = new String[]{"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
    private int[] monthDays = new int[]{31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    private int[] monthDaysLeap = new int[]{31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    private int hour24 = -1;
    private String hour12 = null;
    private int minute = -1;
    private int second = -1;
    private int millisecond = -1;
    private String shortTime24 = null;
    private String shortTime12 = null;
    private String midTime24 = null;
    private String midTime12 = null;
    private String fullTime24 = null;
    private String fullTime12 = null;
    private long tStart = 0L;
    private boolean startCheck = false;
    private long tEnd = 0L;
    private boolean endCheck = false;
    private long totalTime = 0L;
    private String changeDate = null;
    private boolean backForw = true;
    private int easterMonth = 0;
    private int easterDay = 0;
    private String easterDayName = null;
    private int yearHold = 0;
    private int monthHold = 0;
    private int dayHold = 0;
    private String dayNameHold = null;

    public TimeAndDate() {
    }

    public void waitFor(int var1) {
        long var2 = System.currentTimeMillis();

        long var4;
        do {
            var4 = System.currentTimeMillis();
        } while(var4 - var2 < (long)(var1 * 1000));

    }

    public void waitFor(long var1) {
        if (var1 > 9223372036854775L) {
            System.out.println("Class: TimeAndDate, method: wait(long nSeconds), nSeconds is too large for this method - the value has been replaced by 9223372036854775");
            var1 = 9223372036854775L;
        }

        long var3 = System.currentTimeMillis();

        long var5;
        do {
            var5 = System.currentTimeMillis();
        } while(var5 - var3 < var1 * 1000L);

    }

    public void waitFor(double var1) {
        long var3 = 0L;
        if (var1 > Math.pow(2.0D, 63.0D) - 1.0D) {
            System.out.println("Class: TimeAndDate, method: wait(double nSeconds), nSeconds is too large for this method - the value has been replaced by 9223372036854775");
            var3 = 9223372036854775807L;
        } else {
            var3 = Conv.convert_double_to_long(var1 * 1000.0D);
        }

        long var5 = System.currentTimeMillis();

        long var7;
        do {
            var7 = System.currentTimeMillis();
        } while(var7 - var5 < var3);

    }

    public void blocStart() {
        this.tStart = System.currentTimeMillis();
        this.startCheck = true;
    }

    public long blocEnd() {
        if (this.startCheck) {
            this.tEnd = System.currentTimeMillis();
            this.totalTime = this.tEnd - this.tStart;
            this.endCheck = true;
            return this.totalTime;
        } else {
            throw new IllegalArgumentException("No start marker has been set");
        }
    }

    public long blocTime() {
        if (this.endCheck) {
            return this.totalTime;
        } else if (!this.startCheck) {
            System.out.println("Class Time: method totalTime:  No start marker has been set - -9999 rturned");
            return -9999L;
        } else {
            System.out.println("Class Time: method totalTime:  No end marker has been set - -8888 rturned");
            return -8888L;
        }
    }

    public int getHour24() {
        this.hour24 = this.cal.get(11);
        return this.hour24;
    }

    public String getHour12() {
        int var1 = this.cal.get(10);
        int var2 = this.cal.get(9);
        if (var2 == 0) {
            this.hour12 = (new Integer(var1)).toString() + " AM";
        } else {
            this.hour12 = (new Integer(var1)).toString() + " PM";
        }

        return this.hour12;
    }

    public int getMinute() {
        this.minute = this.cal.get(12);
        return this.minute;
    }

    public int getSecond() {
        this.second = this.cal.get(13);
        return this.second;
    }

    public int getMilliSecond() {
        this.millisecond = this.cal.get(14);
        return this.millisecond;
    }

    public String getShortTime24() {
        int var1 = this.getHour24();
        this.shortTime24 = (new Integer(var1)).toString();
        int var2 = this.getMinute();
        if (var2 < 10) {
            this.shortTime24 = this.shortTime24 + ".0" + var2;
        } else {
            this.shortTime24 = this.shortTime24 + "." + var2;
        }

        return this.shortTime24;
    }

    public String getShortTime12() {
        int var1 = this.cal.get(10);
        int var2 = this.cal.get(9);
        this.shortTime12 = (new Integer(var1)).toString();
        int var3 = this.getMinute();
        if (var3 < 10) {
            this.shortTime12 = this.shortTime12 + ".0" + var3;
        } else {
            this.shortTime12 = this.shortTime12 + "." + var3;
        }

        if (var2 == 0) {
            this.shortTime12 = this.shortTime12 + " AM";
        } else {
            this.shortTime12 = this.shortTime12 + " PM";
        }

        return this.shortTime12;
    }

    public String getMidTime24() {
        int var1 = this.getHour24();
        this.midTime24 = (new Integer(var1)).toString();
        int var2 = this.getMinute();
        if (var2 < 10) {
            this.midTime24 = this.midTime24 + ".0" + var2;
        } else {
            this.midTime24 = this.midTime24 + "." + var2;
        }

        int var3 = this.getSecond();
        if (var3 < 10) {
            this.midTime24 = this.midTime24 + ".0" + var3;
        } else {
            this.midTime24 = this.midTime24 + "." + var3;
        }

        return this.midTime24;
    }

    public String getMidTime12() {
        int var1 = this.cal.get(10);
        int var2 = this.cal.get(9);
        this.midTime12 = (new Integer(var1)).toString();
        int var3 = this.getMinute();
        if (var3 < 10) {
            this.midTime12 = this.midTime12 + ".0" + var3;
        } else {
            this.midTime12 = this.midTime12 + "." + var3;
        }

        int var4 = this.getSecond();
        if (var4 < 10) {
            this.midTime12 = this.midTime12 + ".0" + var4;
        } else {
            this.midTime12 = this.midTime12 + "." + var4;
        }

        if (var2 == 0) {
            this.midTime12 = this.midTime12 + " AM";
        } else {
            this.midTime12 = this.midTime12 + " PM";
        }

        return this.midTime12;
    }

    public String getFullTime24() {
        int var1 = this.getHour24();
        this.fullTime24 = (new Integer(var1)).toString();
        int var2 = this.getMinute();
        if (var2 < 10) {
            this.fullTime24 = this.fullTime24 + ".0" + var2;
        } else {
            this.fullTime24 = this.fullTime24 + "." + var2;
        }

        int var3 = this.getSecond();
        if (var3 < 10) {
            this.fullTime24 = this.fullTime24 + ".0" + var3;
        } else {
            this.fullTime24 = this.fullTime24 + "." + var3;
        }

        int var4 = this.getMilliSecond();
        if (var4 < 10) {
            this.fullTime24 = this.fullTime24 + ".00" + var4;
        } else if (var4 < 100) {
            this.fullTime24 = this.fullTime24 + ".0" + var4;
        } else {
            this.fullTime24 = this.fullTime24 + "." + var4;
        }

        return this.fullTime24;
    }

    public String getFullTime12() {
        int var1 = this.cal.get(10);
        int var2 = this.cal.get(9);
        this.fullTime12 = (new Integer(var1)).toString();
        int var3 = this.getMinute();
        if (var3 < 10) {
            this.fullTime12 = this.fullTime12 + ".0" + var3;
        } else {
            this.fullTime12 = this.fullTime12 + "." + var3;
        }

        int var4 = this.getSecond();
        if (var4 < 10) {
            this.fullTime12 = this.fullTime12 + ".0" + var4;
        } else {
            this.fullTime12 = this.fullTime12 + "." + var4;
        }

        int var5 = this.getMilliSecond();
        if (var5 < 10) {
            this.fullTime12 = this.fullTime12 + ".00" + var5;
        } else if (var5 < 100) {
            this.fullTime12 = this.fullTime12 + ".0" + var5;
        } else {
            this.fullTime12 = this.fullTime12 + "." + var5;
        }

        if (var2 == 0) {
            this.fullTime12 = this.fullTime12 + " AM";
        } else {
            this.fullTime12 = this.fullTime12 + " PM";
        }

        return this.fullTime12;
    }

    public long getComputerTime() {
        return System.currentTimeMillis();
    }

    public long dateToJavaMilliSecondsUK(int var1, int var2, int var3, String var4, int var5, int var6, int var7, int var8) {
        long var9 = 0L;
        int var11 = this.getDayOfTheWeekAsInteger(var4);
        long var12 = 0L;
        this.backForw = this.checkBST(var4, var3, var5, var2, var11);
        if (this.backForw) {
            var12 = 1L;
        }

        long var14;
        int var16;
        long var17;
        int var19;
        long var20;
        if (var1 >= 1970) {
            var14 = 0L;

            for(var16 = var1 - 1; var16 >= 1970; --var16) {
                var14 += 365L;
                if (this.leapYear(var16)) {
                    ++var14;
                }
            }

            var14 *= 86400000L;
            var17 = 0L;

            for(var19 = var2; var19 > 0; --var19) {
                var17 += (long)this.monthDays[var19 - 1];
                if (this.leapYear(var1) && var19 == 2) {
                    ++var17;
                }
            }

            var17 *= 86400000L;
            var20 = (long)(var3 - 1) * 24L * 60L * 60L * 1000L;
            var9 = var14 + var17 + var20 + ((long)var5 - var12) * 60L * 60L * 1000L + (long)var6 * 60L * 1000L + (long)var7 * 1000L + (long)var8;
        } else {
            var14 = 0L;

            for(var16 = var1 + 1; var16 < 1970; ++var16) {
                var14 += 365L;
                if (this.leapYear(var16)) {
                    ++var14;
                }
            }

            var14 *= 86400000L;
            var17 = 0L;

            for(var19 = var2 - 1; var19 > 0; --var19) {
                var17 += (long)this.monthDays[var19 - 1];
                if (this.leapYear(var1) && var19 == 2) {
                    ++var17;
                }
            }

            var17 *= 86400000L;
            var20 = (long)(var3 - 1) * 24L * 60L * 60L * 1000L;
            var17 = var17 + var20 + ((long)var5 - var12) * 60L * 60L * 1000L + (long)var6 * 60L * 1000L + (long)var7 * 1000L + (long)var8;
            long var22 = 365L;
            if (this.leapYear(var1)) {
                ++var22;
            }

            var22 *= 86400000L;
            var9 = var22 - var17;
            var9 += var14;
            var9 = -var9;
        }

        return var9;
    }

    public boolean checkBST() {
        String var1 = this.getDayOfTheWeek();
        int var2 = this.getDayOfTheMonth();
        int var3 = this.getMonthAsInteger();
        int var4 = this.getMonthAsInteger();
        int var5 = this.getDayOfTheWeekAsInteger(var1);
        return this.checkBST(var1, var2, var3, var4, var5);
    }

    private boolean checkBST(String var1, int var2, int var3, int var4, int var5) {
        if (var4 > 3 && var4 < 10) {
            this.backForw = true;
        } else if (var4 == 3 && var2 > 24) {
            if (var5 == 0) {
                if (var3 >= 1) {
                    this.backForw = true;
                }
            } else if (var5 > 0 && var5 < var2 - 24) {
                this.backForw = true;
            }
        } else if (var4 == 10 && var2 > 24) {
            if (var5 == 0) {
                if (var3 <= 2) {
                    this.backForw = true;
                }
            } else {
                this.backForw = true;
                if (var5 > 0 && var5 < var2 - 24) {
                    this.backForw = false;
                }
            }
        }

        return this.backForw;
    }

    public int getDayOfTheWeekAsInteger() {
        String var1 = this.getDayOfTheWeek();
        return this.getDayOfTheWeekAsInteger(var1) + 1;
    }

    private int getDayOfTheWeekAsInteger(String var1) {
        int var2 = 0;
        int var3 = 0;
        boolean var4 = true;

        while(var4) {
            if (var1.equals(this.days[var2])) {
                var3 = var2;
                var4 = false;
            } else {
                ++var2;
                if (var2 > 6) {
                    throw new IllegalArgumentException(var1 + " is not recognised as a day of the week");
                }
            }
        }

        return var3;
    }

    public String nextBstClockChange() {
        this.backForw = true;
        String var1 = this.getDayOfTheWeek();
        int var2 = this.getDayOfTheMonth();
        int var3 = this.getMonthAsInteger();
        int var4 = this.getMonthAsInteger();
        int var5 = this.getDayOfTheWeekAsInteger(var1);
        this.backForw = this.checkBST(var1, var2, var3, var4, var5);
        int var6 = 0;
        int var8 = var4;
        int var9 = this.year;
        if (var5 != 0) {
            var6 = 7 - var5;
        }

        int var7 = var2 + var6;
        int var13 = this.monthDays[var4 - 1];
        if (var4 == 2 && this.leapYear(var9)) {
            ++var13;
        }

        if (var7 > var13) {
            var7 -= var13;
            var8 = var4 + 1;
            if (var8 == 13) {
                var8 = 1;
                ++var9;
            }
        }

        boolean var14;
        if (!this.backForw) {
            var14 = true;

            while(true) {
                while(var14) {
                    if (var8 == 3 && var7 > 24) {
                        this.changeDate = "Sunday, " + var7 + " March " + this.year + ", one hour forward";
                        var14 = false;
                    } else {
                        var7 += 7;
                        var13 = this.monthDays[var8 - 1];
                        if (var8 == 2 && this.leapYear(var9)) {
                            ++var13;
                        }

                        if (var7 > var13) {
                            var7 -= var13;
                            ++var8;
                            if (var8 == 13) {
                                var8 = 1;
                                ++var9;
                            }
                        }
                    }
                }

                return this.changeDate;
            }
        } else {
            var14 = true;

            while(true) {
                while(var14) {
                    if (var8 == 10 && var7 > 24) {
                        this.changeDate = "Sunday, " + var7 + " October " + this.year + ", one hour back";
                        var14 = false;
                    } else {
                        var7 += 7;
                        var13 = this.monthDays[var8 - 1];
                        if (var8 == 2 && this.leapYear(var9)) {
                            ++var13;
                        }

                        if (var7 > var13) {
                            var7 -= var13;
                            ++var8;
                            if (var8 == 13) {
                                var8 = 1;
                                ++var9;
                            }
                        }
                    }
                }

                return this.changeDate;
            }
        }
    }

    public String getDayOfTheWeek() {
        int var1 = this.cal.get(7);
        this.dayOfTheWeek = this.days[var1 - 1];
        return this.dayOfTheWeek;
    }

    public int getDayOfTheMonth() {
        this.dayOfTheMonth = this.cal.get(5);
        return this.dayOfTheMonth;
    }

    public String getMonth() {
        int var1 = this.cal.get(2);
        this.monthOfTheYear = this.months[var1];
        return this.monthOfTheYear;
    }

    public int getMonthAsInteger() {
        this.monthAsInteger = this.cal.get(2) + 1;
        return this.monthAsInteger;
    }

    public int getMonthAsInteger(String var1) {
        int var2 = 0;
        boolean var3 = true;
        int var4 = 0;

        while(var3) {
            if (var1.equals(this.months[var4])) {
                var2 = var4 + 1;
                var3 = false;
            } else {
                ++var4;
                if (var4 == 12) {
                    throw new IllegalArgumentException(var1 + " is not recognised as a valid month name");
                }
            }
        }

        return var2;
    }

    public int getYear() {
        this.year = this.cal.get(1);
        return this.year;
    }

    public String getDate() {
        this.date = (new Integer(this.getDayOfTheMonth())).toString();
        this.date = this.date + " " + this.getMonth();
        this.date = this.date + " " + this.getYear();
        return this.date;
    }

    public String getFullDate() {
        this.fullDate = this.getDayOfTheWeek();
        this.fullDate = this.fullDate + ", " + this.getDayOfTheMonth();
        this.fullDate = this.fullDate + " " + this.getMonth();
        this.fullDate = this.fullDate + " " + this.getYear();
        return this.fullDate;
    }

    public String getShortDateUK() {
        this.shortDateUK = (new Integer(this.getDayOfTheMonth())).toString();
        if (this.shortDateUK.length() < 2) {
            this.shortDateUK = "0" + this.shortDateUK;
        }

        int var1 = this.getMonthAsInteger();
        if (var1 < 10) {
            this.shortDateUK = this.shortDateUK + ".0" + var1;
        } else {
            this.shortDateUK = this.shortDateUK + "." + var1;
        }

        String var2 = (new Integer(this.getYear())).toString();
        this.shortDateUK = this.shortDateUK + "." + var2.substring(2);
        return this.shortDateUK;
    }

    public String getShortDateUS() {
        this.shortDateUS = (new Integer(this.getMonthAsInteger())).toString();
        if (this.shortDateUS.length() < 2) {
            this.shortDateUS = "0" + this.shortDateUS;
        }

        int var1 = this.getDayOfTheMonth();
        if (var1 < 10) {
            this.shortDateUS = this.shortDateUS + "/0" + var1;
        } else {
            this.shortDateUS = this.shortDateUS + "/" + var1;
        }

        String var2 = (new Integer(this.getYear())).toString();
        this.shortDateUS = this.shortDateUS + "/" + var2.substring(2);
        return this.shortDateUS;
    }

    private boolean direction(int var1, int var2, int var3, int var4, int var5, int var6) {
        boolean var7 = true;
        boolean var8 = false;
        if (var6 > var3) {
            var8 = true;
        } else if (var6 < var3) {
            var8 = false;
        } else if (var5 > var2) {
            var8 = true;
        } else if (var5 < var2) {
            var8 = false;
        } else if (var4 >= var1) {
            var8 = true;
        } else {
            var8 = false;
        }

        return var8;
    }

    public String getDayOfDate(int var1, String var2, int var3) {
        int var4 = this.getMonthAsInteger(var2);
        return this.getDayOfDate(var1, var4, var3);
    }

    public String getDayOfDate(int var1, int var2, int var3) {
        String var4 = null;
        int var5 = this.getYear();
        int var6 = this.getMonthAsInteger();
        int var7 = this.getDayOfTheMonth();
        int var8 = this.getDayOfTheWeekAsInteger();
        boolean var9 = false;
        boolean var10 = this.direction(var7, var6, var5, var1, var2, var3);
        boolean var11;
        int var13;
        if (var10) {
            var11 = true;

            while(true) {
                while(var11) {
                    if (var5 == var3 && var6 == var2 && var7 == var1) {
                        var4 = this.days[var8 - 1];
                        var11 = false;
                    } else {
                        ++var7;
                        var13 = this.monthDays[var6 - 1];
                        if (this.leapYear(var5) && var6 == 2) {
                            ++var13;
                        }

                        if (var7 > var13) {
                            var7 -= var13;
                            ++var6;
                        }

                        if (var6 == 13) {
                            var6 = 1;
                            ++var5;
                        }

                        ++var8;
                        if (var8 == 8) {
                            var8 = 1;
                        }
                    }
                }

                return var4;
            }
        } else {
            var11 = true;

            while(true) {
                while(var11) {
                    if (var5 == var3 && var6 == var2 && var7 == var1) {
                        var4 = this.days[var8 - 1];
                        var11 = false;
                    } else {
                        --var7;
                        int var12 = var6 - 2;
                        if (var12 < 0) {
                            var12 = 11;
                        }

                        var13 = this.monthDays[var12];
                        if (this.leapYear(var5) && var6 == 3) {
                            ++var13;
                        }

                        if (var7 == 0) {
                            var7 = var13;
                            --var6;
                        }

                        if (var6 == 0) {
                            var6 = 12;
                            --var5;
                        }

                        --var8;
                        if (var8 == 0) {
                            var8 = 7;
                        }
                    }
                }

                return var4;
            }
        }
    }

    public String easterSunday() {
        int var1 = this.getYear();
        if (var1 > 2299) {
            System.out.println(var1 + " is outside the range for which this algorithm has been checked, 1700 - 2299");
        }

        int var2 = this.getMonthAsInteger();
        int var3 = this.getDayOfTheMonth();
        int var4 = var1 % 19;
        int var5 = var1 / 100;
        int var6 = var1 % 100;
        int var7 = var5 / 4;
        int var8 = var5 % 4;
        int var9 = var6 / 4;
        int var10 = var6 % 4;
        int var11 = (var5 + 8) / 25;
        int var12 = (var5 - var11 + 1) / 3;
        int var13 = (19 * var4 + var5 - var7 - var12 + 15) % 30;
        int var14 = (32 + 2 * var8 + 2 * var9 - var13 - var10) % 7;
        int var15 = (var4 + 11 * var13 + 22 * var14) / 451;
        int var16 = var13 + var14 - 7 * var15 + 114;
        this.easterMonth = var16 / 31;
        this.easterDay = var16 % 31 + 1;
        boolean var17 = this.direction(var3, var2, var1, this.easterDay, this.easterMonth, var1);
        if (var17) {
            this.dayHold = this.easterDay;
            this.monthHold = this.easterMonth;
            this.yearHold = var1;
            this.dayNameHold = "Sunday";
            this.easterDayName = this.getDayOfDate(this.easterDay, this.easterMonth, var1);
            return this.easterDayName + ", " + this.easterDay + " " + this.months[this.easterMonth - 1] + " " + var1;
        } else {
            ++var1;
            return this.easterSunday(var1);
        }
    }

    public String easterSunday(int var1) {
        if (var1 < 1700 || var1 > 2299) {
            System.out.println(var1 + " is outside the range for which this algorithm has been checked, 1700 - 2299");
        }

        int var2 = var1 % 19;
        int var3 = var1 / 100;
        int var4 = var1 % 100;
        int var5 = var3 / 4;
        int var6 = var3 % 4;
        int var7 = var4 / 4;
        int var8 = var4 % 4;
        int var9 = (var3 + 8) / 25;
        int var10 = (var3 - var9 + 1) / 3;
        int var11 = (19 * var2 + var3 - var5 - var10 + 15) % 30;
        int var12 = (32 + 2 * var6 + 2 * var7 - var11 - var8) % 7;
        int var13 = (var2 + 11 * var11 + 22 * var12) / 451;
        int var14 = var11 + var12 - 7 * var13 + 114;
        this.easterMonth = var14 / 31;
        this.easterDay = var14 % 31 + 1;
        this.dayHold = this.easterDay;
        this.monthHold = this.easterMonth;
        this.yearHold = var1;
        this.dayNameHold = "Sunday";
        this.easterDayName = this.getDayOfDate(this.easterDay, this.easterMonth, var1);
        return this.easterDayName + ", " + this.easterDay + " " + this.months[this.easterMonth - 1] + " " + var1;
    }

    public String goodFriday() {
        int var1 = this.getYear();
        int var2 = this.getMonthAsInteger();
        int var3 = this.getDayOfTheMonth();
        this.easterSunday(var1);
        int var4 = this.easterMonth;
        int var5 = this.easterDay - 2;
        if (var5 < 1) {
            int var6 = this.monthDays[var4 - 2];
            if (this.leapYear(var1) && var4 == 3) {
                ++var6;
            }

            var5 += var6;
            --var4;
        }

        boolean var7 = this.direction(var3, var2, var1, var5, var4, var1);
        if (!var7) {
            ++var1;
        }

        return this.goodFriday(var1);
    }

    public String goodFriday(int var1) {
        this.easterSunday(var1);
        int var2 = this.easterMonth;
        int var3 = this.easterDay - 2;
        if (var3 < 1) {
            int var4 = this.monthDays[var2 - 2];
            if (this.leapYear(var1) && var2 == 3) {
                ++var4;
            }

            var3 += var4;
            --var2;
        }

        this.dayHold = var3;
        this.monthHold = var2;
        this.yearHold = var1;
        this.dayNameHold = "Friday";
        return "Friday, " + var3 + " " + this.months[var2 - 1] + " " + var1;
    }

    public String maundyThursday() {
        int var1 = this.getYear();
        int var2 = this.getMonthAsInteger();
        int var3 = this.getDayOfTheMonth();
        this.maundyThursday(var1);
        int var4 = this.monthHold;
        int var5 = this.dayHold;
        boolean var6 = this.direction(var3, var2, var1, var5, var4, var1);
        if (!var6) {
            ++var1;
        }

        return this.maundyThursday(var1);
    }

    public String maundyThursday(int var1) {
        this.goodFriday(var1);
        int var2 = this.monthHold;
        int var3 = this.dayHold - 1;
        if (var3 < 1) {
            int var4 = this.monthDays[var2 - 2];
            if (this.leapYear(var1) && var2 == 3) {
                ++var4;
            }

            var3 += var4;
            --var2;
        }

        this.dayHold = var3;
        this.monthHold = var2;
        this.yearHold = var1;
        this.dayNameHold = "Friday";
        return "Thursday, " + var3 + " " + this.months[var2 - 1] + " " + var1;
    }

    public String ashWednesday() {
        int var1 = this.getYear();
        int var2 = this.getMonthAsInteger();
        int var3 = this.getDayOfTheMonth();
        this.ashWednesday(var1);
        int var4 = this.monthHold;
        int var5 = this.dayHold;
        boolean var6 = this.direction(var3, var2, var1, var5, var4, var1);
        if (!var6) {
            ++var1;
        }

        return this.ashWednesday(var1);
    }

    public String ashWednesday(int var1) {
        this.easterSunday(var1);
        int var2 = this.easterMonth;
        int var3 = this.easterDay;
        int var4 = 1;

        while(var4 <= 40) {
            --var3;
            if (var3 < 1) {
                int var5 = this.monthDays[var2 - 2];
                if (this.leapYear(var1) && var2 == 3) {
                    ++var5;
                }

                var3 += var5;
                --var2;
            }

            if (!this.getDayOfDate(var3, var2, var1).equals("Sunday")) {
                ++var4;
            }
        }

        this.dayHold = var3;
        this.monthHold = var2;
        this.yearHold = var1;
        this.dayNameHold = "Wednesday";
        return "Wednesday, " + var3 + " " + this.months[var2 - 1] + " " + var1;
    }

    public String shroveTuesday() {
        int var1 = this.getYear();
        int var2 = this.getMonthAsInteger();
        int var3 = this.getDayOfTheMonth();
        this.shroveTuesday(var1);
        int var4 = this.monthHold;
        int var5 = this.dayHold;
        boolean var6 = this.direction(var3, var2, var1, var5, var4, var1);
        if (!var6) {
            ++var1;
        }

        return this.shroveTuesday(var1);
    }

    public String shroveTuesday(int var1) {
        this.ashWednesday(var1);
        int var2 = this.monthHold;
        int var3 = this.dayHold - 1;
        if (var3 < 1) {
            int var4 = this.monthDays[var2 - 2];
            if (this.leapYear(var1) && var2 == 3) {
                ++var4;
            }

            var3 += var4;
            --var2;
        }

        this.dayHold = var3;
        this.monthHold = var2;
        this.yearHold = var1;
        this.dayNameHold = "Tuesday";
        return "Tuesday, " + var3 + " " + this.months[var2 - 1] + " " + var1;
    }

    public String palmSunday() {
        int var1 = this.getYear();
        int var2 = this.getMonthAsInteger();
        int var3 = this.getDayOfTheMonth();
        this.palmSunday(var1);
        int var4 = this.monthHold;
        int var5 = this.dayHold;
        boolean var6 = this.direction(var3, var2, var1, var5, var4, var1);
        if (!var6) {
            ++var1;
        }

        return this.palmSunday(var1);
    }

    public String palmSunday(int var1) {
        this.easterSunday(var1);
        int var2 = this.easterMonth;
        int var3 = this.easterDay - 7;
        if (var3 < 1) {
            int var4 = this.monthDays[var2 - 2];
            if (this.leapYear(var1) && var2 == 3) {
                ++var4;
            }

            var3 += var4;
            --var2;
        }

        this.dayHold = var3;
        this.monthHold = var2;
        this.yearHold = var1;
        this.dayNameHold = "Sunday";
        return "Sunday, " + var3 + " " + this.months[var2 - 1] + " " + var1;
    }

    public String adventSunday() {
        int var1 = this.getYear();
        int var2 = this.getMonthAsInteger();
        int var3 = this.getDayOfTheMonth();
        this.adventSunday(var1);
        int var4 = this.monthHold;
        int var5 = this.dayHold;
        boolean var6 = this.direction(var3, var2, var1, var5, var4, var1);
        if (!var6) {
            ++var1;
        }

        return this.adventSunday(var1);
    }

    public String adventSunday(int var1) {
        this.saintAndrewsDay(var1);
        int var2 = this.monthHold;
        int var3 = this.dayHold;
        String var4 = this.dayNameHold;
        int var5 = this.getDayOfTheWeekAsInteger(var4);
        int var6;
        if (var5 < 4) {
            var3 -= var5;
            if (var3 < 1) {
                var6 = this.monthDays[var2 - 2];
                if (this.leapYear(var1) && var2 == 3) {
                    ++var6;
                }

                var3 += var6;
                --var2;
            }
        } else {
            var3 += 7 - var5;
            var6 = this.monthDays[var2 - 1];
            if (this.leapYear(var1) && var2 == 2) {
                ++var6;
            }

            if (var3 > var6) {
                var3 -= var6;
                ++var2;
            }
        }

        this.dayHold = var3;
        this.monthHold = var2;
        this.yearHold = var1;
        this.dayNameHold = "Sunday";
        return "Sunday, " + var3 + " " + this.months[var2 - 1] + " " + var1;
    }

    public String trinitySunday() {
        int var1 = this.getYear();
        int var2 = this.getMonthAsInteger();
        int var3 = this.getDayOfTheMonth();
        this.trinitySunday(var1);
        int var4 = this.monthHold;
        int var5 = this.dayHold;
        boolean var6 = this.direction(var3, var2, var1, var5, var4, var1);
        if (!var6) {
            ++var1;
        }

        return this.trinitySunday(var1);
    }

    public String trinitySunday(int var1) {
        this.whitSunday(var1);
        int var2 = this.monthHold;
        int var3 = this.dayHold + 7;
        int var4 = this.monthDays[var2 - 1];
        if (this.leapYear(var1) && var2 == 2) {
            ++var4;
        }

        if (var3 > var4) {
            var3 -= var4;
            ++var2;
        }

        this.dayHold = var3;
        this.monthHold = var2;
        this.yearHold = var1;
        this.dayNameHold = "Sunday";
        return "Sunday, " + var3 + " " + this.months[var2 - 1] + " " + var1;
    }

    public String corpusChristi() {
        int var1 = this.getYear();
        int var2 = this.getMonthAsInteger();
        int var3 = this.getDayOfTheMonth();
        this.corpusChristi(var1);
        int var4 = this.monthHold;
        int var5 = this.dayHold;
        boolean var6 = this.direction(var3, var2, var1, var5, var4, var1);
        if (!var6) {
            ++var1;
        }

        return this.corpusChristi(var1);
    }

    public String corpusChristi(int var1) {
        this.trinitySunday(var1);
        int var2 = this.monthHold;
        int var3 = this.dayHold + 4;
        int var4 = this.monthDays[var2 - 1];
        if (this.leapYear(var1) && var2 == 2) {
            ++var4;
        }

        if (var3 > var4) {
            var3 -= var4;
            ++var2;
        }

        this.dayHold = var3;
        this.monthHold = var2;
        this.yearHold = var1;
        this.dayNameHold = "Thursday";
        return "Thursday, " + var3 + " " + this.months[var2 - 1] + " " + var1;
    }

    public String sundayAfterCorpusChristi() {
        int var1 = this.getYear();
        int var2 = this.getMonthAsInteger();
        int var3 = this.getDayOfTheMonth();
        this.sundayAfterCorpusChristi(var1);
        int var4 = this.monthHold;
        int var5 = this.dayHold;
        boolean var6 = this.direction(var3, var2, var1, var5, var4, var1);
        if (!var6) {
            ++var1;
        }

        return this.sundayAfterCorpusChristi(var1);
    }

    public String sundayAfterCorpusChristi(int var1) {
        this.corpusChristi(var1);
        int var2 = this.monthHold;
        int var3 = this.dayHold + 3;
        int var4 = this.monthDays[var2 - 1];
        if (this.leapYear(var1) && var2 == 2) {
            ++var4;
        }

        if (var3 > var4) {
            var3 -= var4;
            ++var2;
        }

        this.dayHold = var3;
        this.monthHold = var2;
        this.yearHold = var1;
        this.dayNameHold = "Sunday";
        return "Sunday, " + var3 + " " + this.months[var2 - 1] + " " + var1;
    }

    public String ascensionThursday() {
        int var1 = this.getYear();
        int var2 = this.getMonthAsInteger();
        int var3 = this.getDayOfTheMonth();
        this.ascensionThursday(var1);
        int var4 = this.monthHold;
        int var5 = this.dayHold;
        boolean var6 = this.direction(var3, var2, var1, var5, var4, var1);
        if (!var6) {
            ++var1;
        }

        return this.ascensionThursday(var1);
    }

    public String ascensionThursday(int var1) {
        this.easterSunday(var1);
        int var2 = this.easterMonth;
        int var3 = this.easterDay + 39;
        int var4 = this.monthDays[var2 - 1];
        if (this.leapYear(var1) && var2 == 2) {
            ++var4;
        }

        int var5 = this.monthDays[var2];
        if (this.leapYear(var1) && var2 == 1) {
            ++var5;
        }

        if (var3 > var4 + var5) {
            var3 -= var4 + var5;
            var2 += 2;
        } else if (var3 > var4) {
            var3 -= var4;
            ++var2;
        }

        this.dayHold = var3;
        this.monthHold = var2;
        this.yearHold = var1;
        this.dayNameHold = "Thursday";
        return "Thursday, " + var3 + " " + this.months[var2 - 1] + " " + var1;
    }

    public String sundayAfterAscension() {
        int var1 = this.getYear();
        int var2 = this.getMonthAsInteger();
        int var3 = this.getDayOfTheMonth();
        this.sundayAfterAscension(var1);
        int var4 = this.monthHold;
        int var5 = this.dayHold;
        boolean var6 = this.direction(var3, var2, var1, var5, var4, var1);
        if (!var6) {
            ++var1;
        }

        return this.sundayAfterAscension(var1);
    }

    public String sundayAfterAscension(int var1) {
        this.ascensionThursday(var1);
        int var2 = this.monthHold;
        int var3 = this.dayHold + 3;
        int var4 = this.monthDays[var2 - 1];
        if (this.leapYear(var1) && var2 == 2) {
            ++var4;
        }

        if (var3 > var4) {
            var3 -= var4;
            ++var2;
        }

        this.dayHold = var3;
        this.monthHold = var2;
        this.yearHold = var1;
        this.dayNameHold = "Sunday";
        return "Sunday, " + var3 + " " + this.months[var2 - 1] + " " + var1;
    }

    public String whitSunday() {
        int var1 = this.getYear();
        int var2 = this.getMonthAsInteger();
        int var3 = this.getDayOfTheMonth();
        this.whitSunday(var1);
        int var4 = this.monthHold;
        int var5 = this.dayHold;
        boolean var6 = this.direction(var3, var2, var1, var5, var4, var1);
        if (!var6) {
            ++var1;
        }

        return this.whitSunday(var1);
    }

    public String whitSunday(int var1) {
        this.easterSunday(var1);
        int var2 = this.easterDay + 49;
        int var3 = this.easterMonth;
        int var4 = this.monthDays[var3 - 1];
        if (this.leapYear(var1) && var3 == 2) {
            ++var4;
        }

        int var5 = this.monthDays[var3];
        if (this.leapYear(var1) && var3 == 1) {
            ++var5;
        }

        if (var2 > var4 + var5) {
            var2 -= var4 + var5;
            var3 += 2;
        } else if (var2 > var4) {
            var2 -= var4;
            ++var3;
        }

        this.dayHold = var2;
        this.monthHold = var3;
        this.yearHold = var1;
        this.dayNameHold = "Sunday";
        return "Sunday, " + var2 + " " + this.months[this.monthHold - 1] + " " + var1;
    }

    public String mothersDayUK() {
        int var1 = this.getYear();
        int var2 = this.getMonthAsInteger();
        int var3 = this.getDayOfTheMonth();
        this.mothersDayUK(var1);
        int var4 = this.monthHold;
        int var5 = this.dayHold;
        boolean var6 = this.direction(var3, var2, var1, var5, var4, var1);
        if (!var6) {
            ++var1;
        }

        return this.mothersDayUK(var1);
    }

    public String mothersDayUK(int var1) {
        this.ashWednesday(var1);
        int var2 = this.dayHold + 25;
        int var3 = this.monthHold;
        int var4 = this.monthDays[var3 - 1];
        if (this.leapYear(var1) && var3 == 2) {
            ++var4;
        }

        if (var2 > var4) {
            var2 -= var4;
            ++var3;
        }

        this.dayHold = var2;
        this.monthHold = var3;
        this.yearHold = var1;
        this.dayNameHold = "Sunday";
        return "Sunday, " + var2 + " " + this.months[this.monthHold - 1] + " " + var1;
    }

    public String mothersDayUS() {
        int var1 = this.getYear();
        int var2 = this.getMonthAsInteger();
        int var3 = this.getDayOfTheMonth();
        this.mothersDayUS(var1);
        boolean var4 = this.direction(var3, var2, var1, this.dayHold, this.monthHold, var1);
        if (!var4) {
            ++var1;
        }

        return this.mothersDayUS(var1);
    }

    public String mothersDayUS(int var1) {
        String var2 = this.getDayOfDate(1, "May", var1);
        byte var3 = 5;
        int var4 = this.getDayOfTheWeekAsInteger(var2) + 1;
        boolean var5 = false;
        int var6;
        if (var4 == 1) {
            var6 = var4 + 7;
        } else {
            var6 = 16 - var4;
        }

        this.dayHold = var6;
        this.monthHold = var3;
        this.yearHold = var1;
        this.dayNameHold = "Sunday";
        return "Sunday, " + var6 + " May " + var1;
    }

    public String fathersDay() {
        int var1 = this.getYear();
        int var2 = this.getMonthAsInteger();
        int var3 = this.getDayOfTheMonth();
        this.fathersDay(var1);
        boolean var4 = this.direction(var3, var2, var1, this.dayHold, this.monthHold, var1);
        if (!var4) {
            ++var1;
        }

        return this.fathersDay(var1);
    }

    public String fathersDay(int var1) {
        String var2 = this.getDayOfDate(1, "June", var1);
        byte var3 = 6;
        int var4 = this.getDayOfTheWeekAsInteger(var2) + 1;
        boolean var5 = false;
        int var6;
        if (var4 == 1) {
            var6 = var4 + 14;
        } else {
            var6 = 23 - var4;
        }

        this.dayHold = var6;
        this.monthHold = var3;
        this.yearHold = var1;
        this.dayNameHold = "Sunday";
        return "Sunday, " + var6 + " June " + var1;
    }

    public String christmasDay() {
        int var1 = this.getYear();
        int var2 = this.getMonthAsInteger();
        int var3 = this.getDayOfTheMonth();
        boolean var4 = this.direction(var3, var2, var1, 25, 12, var1);
        if (!var4) {
            ++var1;
        }

        return this.christmasDay(var1);
    }

    public String christmasDay(int var1) {
        String var2 = this.getDayOfDate(25, 12, var1);
        this.dayHold = 25;
        this.monthHold = 12;
        this.yearHold = var1;
        this.dayNameHold = var2;
        return var2 + ", 25 December " + var1;
    }

    public String newYearsDay() {
        int var1 = this.getYear();
        int var2 = this.getMonthAsInteger();
        int var3 = this.getDayOfTheMonth();
        boolean var4 = this.direction(var3, var2, var1, 1, 1, var1);
        if (!var4) {
            ++var1;
        }

        return this.newYearsDay(var1);
    }

    public String newYearsDay(int var1) {
        String var2 = this.getDayOfDate(1, 1, var1);
        this.dayHold = 1;
        this.monthHold = 1;
        this.yearHold = var1;
        this.dayNameHold = var2;
        return var2 + ", 1 January " + var1;
    }

    public String epiphany() {
        int var1 = this.getYear();
        int var2 = this.getMonthAsInteger();
        int var3 = this.getDayOfTheMonth();
        boolean var4 = this.direction(var3, var2, var1, 6, 1, var1);
        if (!var4) {
            ++var1;
        }

        return this.epiphany(var1);
    }

    public String epiphany(int var1) {
        String var2 = this.getDayOfDate(6, 1, var1);
        this.dayHold = 6;
        this.monthHold = 1;
        this.yearHold = var1;
        this.dayNameHold = var2;
        return var2 + ", 6 January " + var1;
    }

    public String sundayAfterEpiphany() {
        int var1 = this.getYear();
        int var2 = this.getMonthAsInteger();
        int var3 = this.getDayOfTheMonth();
        String var4 = this.getDayOfDate(6, 1, var1);
        int var5 = this.getDayOfTheWeekAsInteger(var4);
        int var6 = 6;
        if (var5 > 0) {
            var6 += 7 - var5;
        }

        boolean var7 = this.direction(var3, var2, var1, var6, 1, var1);
        if (!var7) {
            ++var1;
        }

        return this.sundayAfterEpiphany(var1);
    }

    public String sundayAfterEpiphany(int var1) {
        String var2 = this.getDayOfDate(6, 1, var1);
        int var3 = this.getDayOfTheWeekAsInteger(var2);
        int var4 = 6;
        if (var3 > 0) {
            var4 += 7 - var3;
        }

        this.dayHold = var4;
        this.monthHold = 1;
        this.yearHold = var1;
        this.dayNameHold = "Sunday";
        return "Sunday, " + var4 + " January " + var1;
    }

    public String annunciation() {
        int var1 = this.getYear();
        int var2 = this.getMonthAsInteger();
        int var3 = this.getDayOfTheMonth();
        boolean var4 = this.direction(var3, var2, var1, 25, 3, var1);
        if (!var4) {
            ++var1;
        }

        return this.annunciation(var1);
    }

    public String annunciation(int var1) {
        String var2 = this.getDayOfDate(25, 3, var1);
        this.dayHold = 25;
        this.monthHold = 3;
        this.yearHold = var1;
        this.dayNameHold = var2;
        return var2 + ", 25 March " + var1;
    }

    public String assumption() {
        int var1 = this.getYear();
        int var2 = this.getMonthAsInteger();
        int var3 = this.getDayOfTheMonth();
        boolean var4 = this.direction(var3, var2, var1, 15, 8, var1);
        if (!var4) {
            ++var1;
        }

        return this.assumption(var1);
    }

    public String assumption(int var1) {
        String var2 = this.getDayOfDate(15, 8, var1);
        this.dayHold = 15;
        this.monthHold = 8;
        this.yearHold = var1;
        this.dayNameHold = var2;
        return var2 + ", 15 August " + var1;
    }

    public String nativityBlessedVirgin() {
        int var1 = this.getYear();
        int var2 = this.getMonthAsInteger();
        int var3 = this.getDayOfTheMonth();
        boolean var4 = this.direction(var3, var2, var1, 8, 9, var1);
        if (!var4) {
            ++var1;
        }

        return this.nativityBlessedVirgin(var1);
    }

    public String nativityBlessedVirgin(int var1) {
        String var2 = this.getDayOfDate(8, 9, var1);
        this.dayHold = 8;
        this.monthHold = 9;
        this.yearHold = var1;
        this.dayNameHold = var2;
        return var2 + ", 8 September " + var1;
    }

    public String immaculateConception() {
        int var1 = this.getYear();
        int var2 = this.getMonthAsInteger();
        int var3 = this.getDayOfTheMonth();
        boolean var4 = this.direction(var3, var2, var1, 8, 12, var1);
        if (!var4) {
            ++var1;
        }

        return this.immaculateConception(var1);
    }

    public String immaculateConception(int var1) {
        String var2 = this.getDayOfDate(8, 12, var1);
        this.dayHold = 8;
        this.monthHold = 12;
        this.yearHold = var1;
        this.dayNameHold = var2;
        return var2 + ", 8 December " + var1;
    }

    public String purification() {
        int var1 = this.getYear();
        int var2 = this.getMonthAsInteger();
        int var3 = this.getDayOfTheMonth();
        boolean var4 = this.direction(var3, var2, var1, 2, 2, var1);
        if (!var4) {
            ++var1;
        }

        return this.purification(var1);
    }

    public String presentation() {
        return this.purification();
    }

    public String candlemas() {
        return this.purification();
    }

    public String purification(int var1) {
        String var2 = this.getDayOfDate(2, 2, var1);
        this.dayHold = 2;
        this.monthHold = 2;
        this.yearHold = var1;
        this.dayNameHold = var2;
        return var2 + ", 2 February " + var1;
    }

    public String presentation(int var1) {
        return this.purification(var1);
    }

    public String candlemas(int var1) {
        return this.purification(var1);
    }

    public String transfiguration() {
        int var1 = this.getYear();
        int var2 = this.getMonthAsInteger();
        int var3 = this.getDayOfTheMonth();
        boolean var4 = this.direction(var3, var2, var1, 6, 8, var1);
        if (!var4) {
            ++var1;
        }

        return this.transfiguration(var1);
    }

    public String transfiguration(int var1) {
        String var2 = this.getDayOfDate(6, 8, var1);
        this.dayHold = 6;
        this.monthHold = 8;
        this.yearHold = var1;
        this.dayNameHold = var2;
        return var2 + ", 6 August " + var1;
    }

    public String remembranceSunday() {
        int var1 = this.getYear();
        int var2 = this.getMonthAsInteger();
        int var3 = this.getDayOfTheMonth();
        this.remembranceSunday(var1);
        int var4 = this.monthHold;
        int var5 = this.dayHold;
        boolean var6 = this.direction(var3, var2, var1, var5, var4, var1);
        if (!var6) {
            ++var1;
        }

        return this.remembranceSunday(var1);
    }

    public String remembranceSunday(int var1) {
        int var2 = 11;
        byte var3 = 11;
        String var4 = this.getDayOfDate(11, 11, var1);
        int var5 = this.getDayOfTheWeekAsInteger(var4);
        int var6;
        int var7;
        if (var5 < 4) {
            var7 = var3 - var5;
            if (var7 < 1) {
                var6 = this.monthDays[var2 - 2];
                if (this.leapYear(var1) && var2 == 3) {
                    ++var6;
                }

                var7 += var6;
                --var2;
            }
        } else {
            var7 = var3 + (7 - var5);
            var6 = this.monthDays[var2 - 1];
            if (this.leapYear(var1) && var2 == 2) {
                ++var6;
            }

            if (var7 > var6) {
                var7 -= var6;
                ++var2;
            }
        }

        this.dayHold = var7;
        this.monthHold = var2;
        this.yearHold = var1;
        this.dayNameHold = "Sunday";
        return "Sunday, " + var7 + " " + this.months[var2 - 1] + " " + var1;
    }

    public String holocaustMemorialDay() {
        int var1 = this.getYear();
        int var2 = this.getMonthAsInteger();
        int var3 = this.getDayOfTheMonth();
        boolean var4 = this.direction(var3, var2, var1, 27, 1, var1);
        if (!var4) {
            ++var1;
        }

        return this.holocaustMemorialDay(var1);
    }

    public String holocaustMemorialDay(int var1) {
        String var2 = this.getDayOfDate(27, 1, var1);
        this.dayHold = 25;
        this.monthHold = 12;
        this.yearHold = var1;
        this.dayNameHold = var2;
        return var2 + ", 27 January " + var1;
    }

    public String saintPatricksDay() {
        int var1 = this.getYear();
        int var2 = this.getMonthAsInteger();
        int var3 = this.getDayOfTheMonth();
        boolean var4 = this.direction(var3, var2, var1, 17, 3, var1);
        if (!var4) {
            ++var1;
        }

        return this.saintPatricksDay(var1);
    }

    public String saintPatricksDay(int var1) {
        String var2 = this.getDayOfDate(17, 3, var1);
        this.dayHold = 17;
        this.monthHold = 3;
        this.yearHold = var1;
        this.dayNameHold = var2;
        return var2 + ", 17 March " + var1;
    }

    public String saintBrigidsDay() {
        int var1 = this.getYear();
        int var2 = this.getMonthAsInteger();
        int var3 = this.getDayOfTheMonth();
        boolean var4 = this.direction(var3, var2, var1, 1, 2, var1);
        if (!var4) {
            ++var1;
        }

        return this.saintBrigidsDay(var1);
    }

    public String saintBrigidsDay(int var1) {
        String var2 = this.getDayOfDate(1, 2, var1);
        this.dayHold = 1;
        this.monthHold = 2;
        this.yearHold = var1;
        this.dayNameHold = var2;
        return var2 + ", 1 February " + var1;
    }

    public String saintColmCillesDay() {
        int var1 = this.getYear();
        int var2 = this.getMonthAsInteger();
        int var3 = this.getDayOfTheMonth();
        boolean var4 = this.direction(var3, var2, var1, 9, 6, var1);
        if (!var4) {
            ++var1;
        }

        return this.saintColmCillesDay(var1);
    }

    public String saintColumbasDay() {
        return this.saintColmCillesDay();
    }

    public String saintColmcillesDay() {
        return this.saintColmCillesDay();
    }

    public String saintColmCillesDay(int var1) {
        String var2 = this.getDayOfDate(9, 6, var1);
        this.dayHold = 9;
        this.monthHold = 6;
        this.yearHold = var1;
        this.dayNameHold = var2;
        return var2 + ", 9 June " + var1;
    }

    public String saintColumbasDay(int var1) {
        return this.saintColmCillesDay(var1);
    }

    public String saintColmcillesDay(int var1) {
        return this.saintColmCillesDay(var1);
    }

    public String saintGeorgesDay() {
        int var1 = this.getYear();
        int var2 = this.getMonthAsInteger();
        int var3 = this.getDayOfTheMonth();
        boolean var4 = this.direction(var3, var2, var1, 23, 4, var1);
        if (!var4) {
            ++var1;
        }

        return this.saintGeorgesDay(var1);
    }

    public String saintGeorgesDay(int var1) {
        String var2 = this.getDayOfDate(23, 4, var1);
        this.dayHold = 23;
        this.monthHold = 4;
        this.yearHold = var1;
        this.dayNameHold = var2;
        return var2 + ", 23 April " + var1;
    }

    public String saintAndrewsDay() {
        int var1 = this.getYear();
        int var2 = this.getMonthAsInteger();
        int var3 = this.getDayOfTheMonth();
        boolean var4 = this.direction(var3, var2, var1, 30, 11, var1);
        if (!var4) {
            ++var1;
        }

        return this.saintAndrewsDay(var1);
    }

    public String saintAndrewsDay(int var1) {
        String var2 = this.getDayOfDate(30, 11, var1);
        this.dayHold = 30;
        this.monthHold = 11;
        this.yearHold = var1;
        this.dayNameHold = var2;
        return var2 + ", 30 November " + var1;
    }

    public String saintDavidsDay() {
        int var1 = this.getYear();
        int var2 = this.getMonthAsInteger();
        int var3 = this.getDayOfTheMonth();
        boolean var4 = this.direction(var3, var2, var1, 1, 3, var1);
        if (!var4) {
            ++var1;
        }

        return this.saintDavidsDay(var1);
    }

    public String saintDavidsDay(int var1) {
        String var2 = this.getDayOfDate(1, 3, var1);
        this.dayHold = 1;
        this.monthHold = 3;
        this.yearHold = var1;
        this.dayNameHold = var2;
        return var2 + ", 1 March " + var1;
    }

    public String saintStephensDay() {
        int var1 = this.getYear();
        int var2 = this.getMonthAsInteger();
        int var3 = this.getDayOfTheMonth();
        boolean var4 = this.direction(var3, var2, var1, 26, 12, var1);
        if (!var4) {
            ++var1;
        }

        return this.saintStephensDay(var1);
    }

    public String saintStephensDay(int var1) {
        String var2 = this.getDayOfDate(26, 12, var1);
        this.dayHold = 26;
        this.monthHold = 12;
        this.yearHold = var1;
        this.dayNameHold = var2;
        return var2 + ", 26 December " + var1;
    }

    public String saintValentinesDay() {
        int var1 = this.getYear();
        int var2 = this.getMonthAsInteger();
        int var3 = this.getDayOfTheMonth();
        boolean var4 = this.direction(var3, var2, var1, 14, 2, var1);
        if (!var4) {
            ++var1;
        }

        return this.saintValentinesDay(var1);
    }

    public String saintValentinesDay(int var1) {
        String var2 = this.getDayOfDate(14, 2, var1);
        this.dayHold = 14;
        this.monthHold = 2;
        this.yearHold = var1;
        this.dayNameHold = var2;
        return var2 + ", 14 February " + var1;
    }

    public String burnsNight() {
        int var1 = this.getYear();
        int var2 = this.getMonthAsInteger();
        int var3 = this.getDayOfTheMonth();
        boolean var4 = this.direction(var3, var2, var1, 25, 1, var1);
        if (!var4) {
            ++var1;
        }

        return this.burnsNight(var1);
    }

    public String burnsNight(int var1) {
        String var2 = this.getDayOfDate(25, 1, var1);
        this.dayHold = 25;
        this.monthHold = 1;
        this.yearHold = var1;
        this.dayNameHold = var2;
        return var2 + ", 25 January " + var1;
    }

    public String twelfthJuly() {
        int var1 = this.getYear();
        int var2 = this.getMonthAsInteger();
        int var3 = this.getDayOfTheMonth();
        boolean var4 = this.direction(var3, var2, var1, 12, 7, var1);
        if (!var4) {
            ++var1;
        }

        return this.twelfthJuly(var1);
    }

    public String twelfthJuly(int var1) {
        String var2 = this.getDayOfDate(12, 7, var1);
        this.dayHold = 12;
        this.monthHold = 7;
        this.yearHold = var1;
        this.dayNameHold = var2;
        return var2 + ", 12 July " + var1;
    }

    public String fourthJuly() {
        int var1 = this.getYear();
        int var2 = this.getMonthAsInteger();
        int var3 = this.getDayOfTheMonth();
        boolean var4 = this.direction(var3, var2, var1, 12, 7, var1);
        if (!var4) {
            ++var1;
        }

        return this.fourthJuly(var1);
    }

    public String fourthJuly(int var1) {
        String var2 = this.getDayOfDate(4, 7, var1);
        this.dayHold = 4;
        this.monthHold = 7;
        this.yearHold = var1;
        this.dayNameHold = var2;
        return var2 + ", 4 July " + var1;
    }

    public String thanksgivingDay() {
        int var1 = this.getYear();
        int var2 = this.getMonthAsInteger();
        int var3 = this.getDayOfTheMonth();
        String var4 = this.getDayOfDate(1, "November", var1);
        int var5 = this.getDayOfTheWeekAsInteger(var4) + 1;
        int var6 = 6 - var5;
        if (var6 <= 0) {
            var6 += 7;
        }

        var6 += 14;
        boolean var7 = this.direction(var3, var2, var1, var6, 11, var1);
        if (var7) {
            return "Thursday, " + var6 + " November " + var1;
        } else {
            ++var1;
            return this.thanksgivingDay(var1);
        }
    }

    public String thanksgivingDay(int var1) {
        String var2 = this.getDayOfDate(1, "November", var1);
        int var3 = this.getDayOfTheWeekAsInteger(var2) + 1;
        int var4 = 6 - var3;
        if (var4 <= 0) {
            var4 += 7;
        }

        var4 += 14;
        this.dayHold = var4;
        this.monthHold = 11;
        this.yearHold = var1;
        this.dayNameHold = "Thursday";
        return "Thursday, " + var4 + " November " + var1;
    }

    public String commonwealthDay() {
        int var1 = this.getYear();
        int var2 = this.getMonthAsInteger();
        int var3 = this.getDayOfTheMonth();
        String var4 = this.getDayOfDate(1, "March", var1);
        int var5 = this.getDayOfTheWeekAsInteger(var4);
        boolean var6 = false;
        int var8;
        if (var5 > 1) {
            var8 = 15 - var5;
        } else if (var5 == 0) {
            var8 = 8;
        } else {
            var8 = 9;
        }

        boolean var7 = this.direction(var3, var2, var1, var8, 3, var1);
        if (var7) {
            this.dayHold = var8;
            this.monthHold = 3;
            this.yearHold = var1;
            this.dayNameHold = "Monday";
            return "Monday, " + var8 + " November " + var1;
        } else {
            ++var1;
            return this.commonwealthDay(var1);
        }
    }

    public String commonwealthDay(int var1) {
        String var2 = this.getDayOfDate(1, "March", var1);
        int var3 = this.getDayOfTheWeekAsInteger(var2);
        boolean var4 = false;
        int var5;
        if (var3 > 1) {
            var5 = 16 - var3;
        } else if (var3 == 0) {
            var5 = 9;
        } else {
            var5 = 8;
        }

        this.dayHold = var5;
        this.monthHold = 3;
        this.yearHold = var1;
        this.dayNameHold = "Monday";
        return "Monday, " + var5 + " March " + var1;
    }

    public String armedForcesDay() {
        int var1 = this.getYear();
        int var2 = this.getMonthAsInteger();
        int var3 = this.getDayOfTheMonth();
        boolean var4 = this.direction(var3, var2, var1, 27, 6, var1);
        if (!var4) {
            ++var1;
        }

        return this.armedForcesDay(var1);
    }

    public String veteransDayUK() {
        return this.armedForcesDay();
    }

    public String armedForcesDay(int var1) {
        String var2 = this.getDayOfDate(27, 6, var1);
        this.dayHold = 27;
        this.monthHold = 6;
        this.yearHold = var1;
        this.dayNameHold = var2;
        return var2 + ", 27 June " + var1;
    }

    public String veteransDayUK(int var1) {
        return this.armedForcesDay(var1);
    }

    public boolean leapYear(int var1) {
        boolean var2 = false;
        if (var1 % 4 != 0) {
            var2 = false;
        } else if (var1 % 400 == 0) {
            var2 = true;
        } else if (var1 % 100 == 0) {
            var2 = false;
        } else {
            var2 = true;
        }

        return var2;
    }

    public int monthAsNumber(String var1) {
        boolean var2 = true;
        int var3 = 0;

        while(var2) {
            if (var1.equalsIgnoreCase(this.months[var3])) {
                var2 = false;
            } else {
                ++var3;
                if (var3 >= 12) {
                    throw new IllegalArgumentException("Entered month, " + var1 + ", not recognised");
                }
            }
        }

        return var3 + 1;
    }

    public String monthAsName(int var1) {
        if (var1 >= 1 && var1 <= 12) {
            return this.months[var1 - 1];
        } else {
            throw new IllegalArgumentException("The month number, " + var1 + ", must be > 0 and < 13");
        }
    }

    public int getDaysInMonth(String var1, int var2) {
        int var3 = this.getDaysInNonLeapMonth(var1);
        if (var1.equalsIgnoreCase("February") && this.leapYear(var2)) {
            ++var3;
        }

        return var3;
    }

    public int getDaysInMonth(int var1, int var2) {
        String var3 = this.months[var1 - 1];
        int var4 = this.getDaysInNonLeapMonth(var3);
        if (var3.equalsIgnoreCase("February") && this.leapYear(var2)) {
            ++var4;
        }

        return var4;
    }

    public int getDaysInMonth(String var1) {
        int var2 = this.getDaysInNonLeapMonth(var1);
        if (var1.equalsIgnoreCase("February")) {
            int var3 = Db.readInt("Enter the year");
            if (this.leapYear(var3)) {
                ++var2;
            }
        }

        return var2;
    }

    public int getDaysInMonth(int var1) {
        String var2 = this.months[var1 - 1];
        return this.getDaysInMonth(var2);
    }

    private int getDaysInNonLeapMonth(String var1) {
        int var2 = 0;
        boolean var3 = true;
        int var4 = 0;

        while(var3) {
            if (var1.equalsIgnoreCase(this.months[var4])) {
                var2 = this.monthDays[var4];
                var3 = false;
            } else {
                ++var4;
                if (var4 >= 12) {
                    throw new IllegalArgumentException("Entered month, " + var1 + ", not recognised");
                }
            }
        }

        return var2;
    }

    public boolean isLessThan(int[] var1, int[] var2) {
        boolean var3 = false;
        return this.isLessThan(var1[0], var1[1], var1[2], var2[0], var2[1], var2[2]);
    }

    public boolean isLessThan(int var1, int var2, int var3, int var4, int var5, int var6) {
        boolean var7 = false;
        if (var3 == var6) {
            if (var2 == var5) {
                if (var1 < var4) {
                    var7 = true;
                } else {
                    var7 = false;
                }
            } else if (var2 < var5) {
                var7 = true;
            } else {
                var7 = false;
            }
        } else if (var3 < var6) {
            var7 = true;
        } else {
            var7 = false;
        }

        return var7;
    }
}
