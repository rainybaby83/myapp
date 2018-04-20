//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.flanagan.io;

import com.flanagan.circuits.Phasor;
import com.flanagan.complex.Complex;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.BigInteger;

public class KeyboardInput {
    private BufferedReader input = null;

    public KeyboardInput() {
        this.input = new BufferedReader(new InputStreamReader(System.in));
    }

    public final synchronized double readDouble(String var1) {
        String var2 = "";
        double var3 = 0.0D;
        boolean var5 = false;
        System.out.print(var1 + " ");
        System.out.flush();

        while(!var5) {
            var2 = this.enterLine();

            try {
                var3 = Double.parseDouble(var2.trim());
                var5 = true;
            } catch (NumberFormatException var7) {
                System.out.println("You did not enter a valid double\nRe-enter the number");
            }
        }

        return var3;
    }

    public final synchronized double readDouble(String var1, double var2) {
        String var4 = "";
        double var5 = 0.0D;
        boolean var7 = false;
        System.out.print(var1 + " [default value = " + var2 + "] ");
        System.out.flush();

        while(!var7) {
            var4 = this.enterLine();
            if (var4.length() == 0) {
                var5 = var2;
                var7 = true;
            } else {
                try {
                    var5 = Double.parseDouble(var4.trim());
                    var7 = true;
                } catch (NumberFormatException var9) {
                    System.out.println("You did not enter a valid double\nRe-enter the number");
                }
            }
        }

        return var5;
    }

    public final synchronized double readDouble() {
        String var1 = "";
        double var2 = 0.0D;
        boolean var4 = false;
        System.out.print(" ");
        System.out.flush();

        while(!var4) {
            var1 = this.enterLine();

            try {
                var2 = Double.parseDouble(var1.trim());
                var4 = true;
            } catch (NumberFormatException var6) {
                System.out.println("You did not enter a valid double\nRe-enter the number");
            }
        }

        return var2;
    }

    public final synchronized float readFloat(String var1) {
        String var2 = "";
        float var3 = 0.0F;
        boolean var4 = false;
        System.out.print(var1 + " ");
        System.out.flush();

        while(!var4) {
            var2 = this.enterLine();

            try {
                var3 = Float.parseFloat(var2.trim());
                var4 = true;
            } catch (NumberFormatException var6) {
                System.out.println("You did not enter a valid float\nRe-enter the number");
            }
        }

        return var3;
    }

    public final synchronized float readFloat(String var1, float var2) {
        String var3 = "";
        float var4 = 0.0F;
        boolean var5 = false;
        System.out.print(var1 + " [default value = " + var2 + "] ");
        System.out.flush();

        while(!var5) {
            var3 = this.enterLine();
            if (var3.length() == 0) {
                var4 = var2;
                var5 = true;
            } else {
                try {
                    var4 = Float.parseFloat(var3.trim());
                    var5 = true;
                } catch (NumberFormatException var7) {
                    System.out.println("You did not enter a valid float\nRe-enter the number");
                }
            }
        }

        return var4;
    }

    public final synchronized float readFloat() {
        String var1 = "";
        float var2 = 0.0F;
        boolean var3 = false;
        System.out.print(" ");
        System.out.flush();

        while(!var3) {
            var1 = this.enterLine();

            try {
                var2 = Float.parseFloat(var1.trim());
                var3 = true;
            } catch (NumberFormatException var5) {
                System.out.println("You did not enter a valid float\nRe-enter the number");
            }
        }

        return var2;
    }

    public final synchronized BigDecimal readBigDecimal(String var1) {
        String var2 = "";
        BigDecimal var3 = null;
        boolean var4 = false;
        System.out.print(var1 + " ");
        System.out.flush();

        while(!var4) {
            var2 = this.enterLine();

            try {
                var3 = new BigDecimal(var2.trim());
                var4 = true;
            } catch (NumberFormatException var6) {
                System.out.println("You did not enter a valid BigDecimal\nRe-enter the number");
            }
        }

        return var3;
    }

    public final synchronized BigDecimal readBigDecimal(String var1, BigDecimal var2) {
        String var3 = "";
        BigDecimal var4 = null;
        boolean var5 = false;
        System.out.print(var1 + " [default value = " + var2.toString() + "] ");
        System.out.flush();

        while(!var5) {
            var3 = this.enterLine();
            if (var3.length() == 0) {
                var4 = var2;
                var5 = true;
            } else {
                try {
                    var4 = new BigDecimal(var3.trim());
                    var5 = true;
                } catch (NumberFormatException var7) {
                    System.out.println("You did not enter a valid BigDecimal\nRe-enter the number");
                }
            }
        }

        return var4;
    }

    public final synchronized BigDecimal readBigDecimal(String var1, double var2) {
        String var4 = "";
        BigDecimal var5 = null;
        boolean var6 = false;
        Double var7 = new Double(var2);
        String var8 = var7.toString();
        System.out.print(var1 + " [default value = " + var8 + "] ");
        System.out.flush();

        while(!var6) {
            var4 = this.enterLine();
            if (var4.length() == 0) {
                var5 = new BigDecimal(var8);
                var6 = true;
            } else {
                try {
                    var5 = new BigDecimal(var4.trim());
                    var6 = true;
                } catch (NumberFormatException var10) {
                    System.out.println("You did not enter a valid BigDecimal\nRe-enter the number");
                }
            }
        }

        return var5;
    }

    public final synchronized BigDecimal readBigDecimal(String var1, float var2) {
        String var3 = "";
        BigDecimal var4 = null;
        boolean var5 = false;
        Float var6 = new Float(var2);
        String var7 = var6.toString();
        System.out.print(var1 + " [default value = " + var7 + "] ");
        System.out.flush();

        while(!var5) {
            var3 = this.enterLine();
            if (var3.length() == 0) {
                var4 = new BigDecimal(var7);
                var5 = true;
            } else {
                try {
                    var4 = new BigDecimal(var3.trim());
                    var5 = true;
                } catch (NumberFormatException var9) {
                    System.out.println("You did not enter a valid BigDecimal\nRe-enter the number");
                }
            }
        }

        return var4;
    }

    public final synchronized BigDecimal readBigDecimal(String var1, long var2) {
        String var4 = "";
        BigDecimal var5 = null;
        boolean var6 = false;
        Long var7 = new Long(var2);
        String var8 = var7.toString();
        System.out.print(var1 + " [default value = " + var8 + "] ");
        System.out.flush();

        while(!var6) {
            var4 = this.enterLine();
            if (var4.length() == 0) {
                var5 = new BigDecimal(var8);
                var6 = true;
            } else {
                try {
                    var5 = new BigDecimal(var4.trim());
                    var6 = true;
                } catch (NumberFormatException var10) {
                    System.out.println("You did not enter a valid BigDecimal\nRe-enter the number");
                }
            }
        }

        return var5;
    }

    public final synchronized BigDecimal readBigDecimal(String var1, int var2) {
        String var3 = "";
        BigDecimal var4 = null;
        boolean var5 = false;
        Integer var6 = new Integer(var2);
        String var7 = var6.toString();
        System.out.print(var1 + " [default value = " + var7 + "] ");
        System.out.flush();

        while(!var5) {
            var3 = this.enterLine();
            if (var3.length() == 0) {
                var4 = new BigDecimal(var7);
                var5 = true;
            } else {
                try {
                    var4 = new BigDecimal(var3.trim());
                    var5 = true;
                } catch (NumberFormatException var9) {
                    System.out.println("You did not enter a valid BigDecimal\nRe-enter the number");
                }
            }
        }

        return var4;
    }

    public final synchronized BigDecimal readBigDecimal(String var1, String var2) {
        String var3 = "";
        BigDecimal var4 = null;
        boolean var5 = false;
        System.out.print(var1 + " [default value = " + var2 + "] ");
        System.out.flush();

        while(!var5) {
            var3 = this.enterLine();
            if (var3.length() == 0) {
                var4 = new BigDecimal(var2);
                var5 = true;
            } else {
                try {
                    var4 = new BigDecimal(var3.trim());
                    var5 = true;
                } catch (NumberFormatException var7) {
                    System.out.println("You did not enter a valid BigDecimal\nRe-enter the number");
                }
            }
        }

        return var4;
    }

    public final synchronized BigDecimal readBigDecimal() {
        String var1 = "";
        BigDecimal var2 = null;
        boolean var3 = false;
        System.out.print(" ");
        System.out.flush();

        while(!var3) {
            var1 = this.enterLine();

            try {
                var2 = new BigDecimal(var1.trim());
                var3 = true;
            } catch (NumberFormatException var5) {
                System.out.println("You did not enter a valid BigDecimal\nRe-enter the number");
            }
        }

        return var2;
    }

    public final synchronized int readInt(String var1) {
        String var2 = "";
        int var3 = 0;
        boolean var4 = false;
        System.out.print(var1 + " ");
        System.out.flush();

        while(!var4) {
            var2 = this.enterLine();

            try {
                var3 = Integer.parseInt(var2.trim());
                var4 = true;
            } catch (NumberFormatException var6) {
                System.out.println("You did not enter a valid int\nRe-enter the number");
            }
        }

        return var3;
    }

    public final synchronized int readInt(String var1, int var2) {
        String var3 = "";
        int var4 = 0;
        boolean var5 = false;
        System.out.print(var1 + " [default value = " + var2 + "] ");
        System.out.flush();

        while(!var5) {
            var3 = this.enterLine();
            if (var3.length() == 0) {
                var4 = var2;
                var5 = true;
            } else {
                try {
                    var4 = Integer.parseInt(var3.trim());
                    var5 = true;
                } catch (NumberFormatException var7) {
                    System.out.println("You did not enter a valid int\nRe-enter the number");
                }
            }
        }

        return var4;
    }

    public final synchronized int readInt() {
        String var1 = "";
        int var2 = 0;
        boolean var3 = false;
        System.out.print(" ");
        System.out.flush();

        while(!var3) {
            var1 = this.enterLine();

            try {
                var2 = Integer.parseInt(var1.trim());
                var3 = true;
            } catch (NumberFormatException var5) {
                System.out.println("You did not enter a valid int\nRe-enter the number");
            }
        }

        return var2;
    }

    public final synchronized long readLong(String var1) {
        String var2 = "";
        long var3 = 0L;
        boolean var5 = false;
        System.out.print(var1 + " ");
        System.out.flush();

        while(!var5) {
            var2 = this.enterLine();

            try {
                var3 = Long.parseLong(var2.trim());
                var5 = true;
            } catch (NumberFormatException var7) {
                System.out.println("You did not enter a valid long\nRe-enter the number");
            }
        }

        return var3;
    }

    public final synchronized long readLong(String var1, long var2) {
        String var4 = "";
        long var5 = 0L;
        boolean var7 = false;
        System.out.print(var1 + " [default value = " + var2 + "] ");
        System.out.flush();

        while(!var7) {
            var4 = this.enterLine();
            if (var4.length() == 0) {
                var5 = var2;
                var7 = true;
            } else {
                try {
                    var5 = Long.parseLong(var4.trim());
                    var7 = true;
                } catch (NumberFormatException var9) {
                    System.out.println("You did not enter a valid long\nRe-enter the number");
                }
            }
        }

        return var5;
    }

    public final synchronized long readLong() {
        String var1 = "";
        long var2 = 0L;
        boolean var4 = false;
        System.out.print(" ");
        System.out.flush();

        while(!var4) {
            var1 = this.enterLine();

            try {
                var2 = Long.parseLong(var1.trim());
                var4 = true;
            } catch (NumberFormatException var6) {
                System.out.println("You did not enter a valid long\nRe-enter the number");
            }
        }

        return var2;
    }

    public final synchronized BigInteger readBigInteger(String var1) {
        String var2 = "";
        BigInteger var3 = null;
        boolean var4 = false;
        System.out.print(var1 + " ");
        System.out.flush();

        while(!var4) {
            var2 = this.enterLine();

            try {
                var3 = new BigInteger(var2.trim());
                var4 = true;
            } catch (NumberFormatException var6) {
                System.out.println("You did not enter a valid BigInteger\nRe-enter the number");
            }
        }

        return var3;
    }

    public final synchronized BigInteger readBigInteger(String var1, BigInteger var2) {
        String var3 = "";
        BigInteger var4 = null;
        boolean var5 = false;
        System.out.print(var1 + " [default value = " + var2.toString() + "] ");
        System.out.flush();

        while(!var5) {
            var3 = this.enterLine();
            if (var3.length() == 0) {
                var4 = var2;
                var5 = true;
            } else {
                try {
                    var4 = new BigInteger(var3.trim());
                    var5 = true;
                } catch (NumberFormatException var7) {
                    System.out.println("You did not enter a valid BigInteger\nRe-enter the number");
                }
            }
        }

        return var4;
    }

    public final synchronized BigInteger readBigInteger(String var1, long var2) {
        String var4 = "";
        BigInteger var5 = null;
        boolean var6 = false;
        Long var7 = new Long(var2);
        String var8 = var7.toString();
        System.out.print(var1 + " [default value = " + var8 + "] ");
        System.out.flush();

        while(!var6) {
            var4 = this.enterLine();
            if (var4.length() == 0) {
                var5 = new BigInteger(var8);
                var6 = true;
            } else {
                try {
                    var5 = new BigInteger(var4.trim());
                    var6 = true;
                } catch (NumberFormatException var10) {
                    System.out.println("You did not enter a valid BigInteger\nRe-enter the number");
                }
            }
        }

        return var5;
    }

    public final synchronized BigInteger readBigInteger(String var1, int var2) {
        String var3 = "";
        BigInteger var4 = null;
        boolean var5 = false;
        Integer var6 = new Integer(var2);
        String var7 = var6.toString();
        System.out.print(var1 + " [default value = " + var7 + "] ");
        System.out.flush();

        while(!var5) {
            var3 = this.enterLine();
            if (var3.length() == 0) {
                var4 = new BigInteger(var7);
                var5 = true;
            } else {
                try {
                    var4 = new BigInteger(var3.trim());
                    var5 = true;
                } catch (NumberFormatException var9) {
                    System.out.println("You did not enter a valid BigInteger\nRe-enter the number");
                }
            }
        }

        return var4;
    }

    public final synchronized BigInteger readBigInteger(String var1, String var2) {
        String var3 = "";
        BigInteger var4 = null;
        boolean var5 = false;
        System.out.print(var1 + " [default value = " + var2 + "] ");
        System.out.flush();

        while(!var5) {
            var3 = this.enterLine();
            if (var3.length() == 0) {
                var4 = new BigInteger(var2);
                var5 = true;
            } else {
                try {
                    var4 = new BigInteger(var3.trim());
                    var5 = true;
                } catch (NumberFormatException var7) {
                    System.out.println("You did not enter a valid BigInteger\nRe-enter the number");
                }
            }
        }

        return var4;
    }

    public final synchronized BigInteger readBigInteger() {
        String var1 = "";
        BigInteger var2 = null;
        boolean var3 = false;
        System.out.print(" ");
        System.out.flush();

        while(!var3) {
            var1 = this.enterLine();

            try {
                var2 = new BigInteger(var1.trim());
                var3 = true;
            } catch (NumberFormatException var5) {
                System.out.println("You did not enter a valid BigInteger\nRe-enter the number");
            }
        }

        return var2;
    }

    public final synchronized short readShort(String var1) {
        String var2 = "";
        short var3 = 0;
        boolean var4 = false;
        System.out.print(var1 + " ");
        System.out.flush();

        while(!var4) {
            var2 = this.enterLine();

            try {
                var3 = Short.parseShort(var2.trim());
                var4 = true;
            } catch (NumberFormatException var6) {
                System.out.println("You did not enter a valid short\nRe-enter the number");
            }
        }

        return var3;
    }

    public final synchronized short readShort(String var1, short var2) {
        String var3 = "";
        short var4 = 0;
        boolean var5 = false;
        System.out.print(var1 + " [default value = " + var2 + "] ");
        System.out.flush();

        while(!var5) {
            var3 = this.enterLine();
            if (var3.length() == 0) {
                var4 = var2;
                var5 = true;
            } else {
                try {
                    var4 = Short.parseShort(var3.trim());
                    var5 = true;
                } catch (NumberFormatException var7) {
                    System.out.println("You did not enter a valid short\nRe-enter the number");
                }
            }
        }

        return var4;
    }

    public final synchronized short readShort() {
        String var1 = "";
        short var2 = 0;
        boolean var3 = false;
        System.out.print(" ");
        System.out.flush();

        while(!var3) {
            var1 = this.enterLine();

            try {
                var2 = Short.parseShort(var1.trim());
                var3 = true;
            } catch (NumberFormatException var5) {
                System.out.println("You did not enter a valid short\nRe-enter the number");
            }
        }

        return var2;
    }

    public final synchronized byte readByte(String var1) {
        String var2 = "";
        byte var3 = 0;
        boolean var4 = false;
        System.out.print(var1 + " ");
        System.out.flush();

        while(!var4) {
            var2 = this.enterLine();

            try {
                var3 = Byte.parseByte(var2.trim());
                var4 = true;
            } catch (NumberFormatException var6) {
                System.out.println("You did not enter a valid byte\nRe-enter the number");
            }
        }

        return var3;
    }

    public final synchronized byte readByte(String var1, byte var2) {
        String var3 = "";
        byte var4 = 0;
        boolean var5 = false;
        System.out.print(var1 + " [default value = " + var2 + "] ");
        System.out.flush();

        while(!var5) {
            var3 = this.enterLine();
            if (var3.length() == 0) {
                var4 = var2;
                var5 = true;
            } else {
                try {
                    var4 = Byte.parseByte(var3.trim());
                    var5 = true;
                } catch (NumberFormatException var7) {
                    System.out.println("You did not enter a valid byte\nRe-enter the number");
                }
            }
        }

        return var4;
    }

    public final synchronized byte readByte() {
        String var1 = "";
        byte var2 = 0;
        boolean var3 = false;
        System.out.print(" ");
        System.out.flush();

        while(!var3) {
            var1 = this.enterLine();

            try {
                var2 = Byte.parseByte(var1.trim());
                var3 = true;
            } catch (NumberFormatException var5) {
                System.out.println("You did not enter a valid byte\nRe-enter the number");
            }
        }

        return var2;
    }

    public final synchronized char readChar(String var1) {
        String var2 = "";
        boolean var3 = true;
        boolean var4 = false;
        System.out.print(var1 + " ");
        System.out.flush();
        var2 = this.enterLine();
        var2 = var2.trim();
        char var5 = var2.charAt(0);
        return var5;
    }

    public final synchronized char readChar(String var1, char var2) {
        String var3 = "";
        boolean var4 = true;
        boolean var5 = false;
        System.out.print(var1 + " [default value = " + var2 + "] ");
        System.out.flush();
        var3 = this.enterLine();
        var3 = var3.trim();
        char var6 = var3.charAt(0);
        return var6;
    }

    public final synchronized char readChar() {
        String var1 = "";
        boolean var2 = true;
        boolean var3 = false;
        System.out.print(" ");
        System.out.flush();
        var1 = this.enterLine();
        var1 = var1.trim();
        char var4 = var1.charAt(0);
        return var4;
    }

    public final synchronized boolean readBoolean(String var1) {
        String var2 = "";
        boolean var3 = false;
        boolean var4 = false;
        System.out.print(var1 + " ");
        System.out.flush();

        while(true) {
            while(true) {
                while(!var4) {
                    var2 = this.enterLine();
                    if (!var2.trim().equals("true") && !var2.trim().equals("TRUE")) {
                        if (!var2.trim().equals("false") && !var2.trim().equals("FALSE")) {
                            System.out.println("You did not enter a valid boolean\nRe-enter the number");
                        } else {
                            var3 = false;
                            var4 = true;
                        }
                    } else {
                        var3 = true;
                        var4 = true;
                    }
                }

                return var3;
            }
        }
    }

    public final synchronized boolean readBoolean(String var1, boolean var2) {
        String var3 = "";
        boolean var4 = false;
        boolean var5 = false;
        System.out.print(var1 + " [default value = " + var2 + "] ");
        System.out.flush();

        while(true) {
            while(true) {
                while(!var5) {
                    var3 = this.enterLine();
                    if (!var3.trim().equals("true") && !var3.trim().equals("TRUE")) {
                        if (!var3.trim().equals("false") && !var3.trim().equals("FALSE")) {
                            System.out.println("You did not enter a valid boolean\nRe-enter the number");
                        } else {
                            var4 = false;
                            var5 = true;
                        }
                    } else {
                        var4 = true;
                        var5 = true;
                    }
                }

                return var4;
            }
        }
    }

    public final synchronized boolean readBoolean() {
        String var1 = "";
        boolean var2 = false;
        boolean var3 = false;
        System.out.print(" ");
        System.out.flush();

        while(true) {
            while(true) {
                while(!var3) {
                    var1 = this.enterLine();
                    if (!var1.trim().equals("true") && !var1.trim().equals("TRUE")) {
                        if (!var1.trim().equals("false") && !var1.trim().equals("FALSE")) {
                            System.out.println("You did not enter a valid boolean\nRe-enter the number");
                        } else {
                            var2 = false;
                            var3 = true;
                        }
                    } else {
                        var2 = true;
                        var3 = true;
                    }
                }

                return var2;
            }
        }
    }

    public final synchronized Complex readComplex(String var1) {
        return Complex.readComplex(var1);
    }

    public final synchronized Complex readComplex(String var1, String var2) {
        return Complex.readComplex(var1, var2);
    }

    public final synchronized Complex readComplex(String var1, Complex var2) {
        return Complex.readComplex(var1, var2);
    }

    public final synchronized Complex readComplex() {
        return Complex.readComplex();
    }

    public final synchronized Phasor readPhasor(String var1) {
        return Phasor.readPhasor(var1);
    }

    public final synchronized Phasor readPhasor(String var1, String var2) {
        return Phasor.readPhasor(var1, var2);
    }

    public final synchronized Phasor readPhasor(String var1, Phasor var2) {
        return Phasor.readPhasor(var1, var2);
    }

    public final synchronized Phasor readPhasor() {
        return Phasor.readPhasor();
    }

    public final synchronized String readLine(String var1) {
        System.out.print(var1 + " ");
        System.out.flush();
        return this.enterLine();
    }

    public final synchronized String readLine(String var1, String var2) {
        String var3 = "";
        System.out.print(var1 + " [default option = " + var2 + "] ");
        System.out.flush();
        var3 = this.enterLine();
        if (var3.length() == 0) {
            var3 = var2;
        }

        return var3;
    }

    private final synchronized String readLine() {
        return this.enterLine();
    }

    private final synchronized String enterLine() {
        String var1 = "";

        try {
            var1 = this.input.readLine();
        } catch (IOException var3) {
            System.out.println(var3);
        }

        return var1;
    }
}
