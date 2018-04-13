//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package flanagan.io;

import flanagan.analysis.ErrorProp;
import flanagan.circuits.Phasor;
import flanagan.complex.Complex;
import flanagan.complex.ComplexErrorProp;
import flanagan.math.Fmath;
import java.awt.Component;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import javax.swing.Icon;
import javax.swing.JOptionPane;

public class Db {
    private static boolean inputTypeInfo = true;

    public Db() {
    }

    public static void setTypeInfoOption(int var0) {
        switch(var0) {
            case 1:
                inputTypeInfo = true;
                break;
            case 2:
                inputTypeInfo = false;
                break;
            default:
                throw new IllegalArgumentException("Option " + var0 + " not recognised");
        }

    }

    public static final synchronized double readDouble(String var0) {
        String var1 = "";
        double var2 = 0.0D;
        boolean var4 = false;
        System.out.flush();
        String var5 = "";
        if (inputTypeInfo) {
            var5 = "Input type: double\n";
        }

        while(!var4) {
            var1 = JOptionPane.showInputDialog(var5 + var0);
            if (var1 != null) {
                try {
                    var2 = Double.parseDouble(var1.trim());
                    var4 = true;
                } catch (NumberFormatException var7) {
                    ;
                }
            }
        }

        return var2;
    }

    public static final synchronized double readDouble(String var0, double var1) {
        String var3 = "";
        double var4 = 0.0D;
        boolean var6 = false;
        System.out.flush();
        String var7 = "";
        if (inputTypeInfo) {
            var7 = "Input type: double\n";
        }

        var0 = var0 + "\n";
        String var8 = var1 + "";

        while(!var6) {
            var3 = JOptionPane.showInputDialog(var7 + var0 + " [default value = " + var1 + "] ", var8);
            if (var3 != null) {
                if (var3.equals("")) {
                    var4 = var1;
                    var6 = true;
                    var3 = null;
                } else {
                    try {
                        var4 = Double.parseDouble(var3.trim());
                        var6 = true;
                    } catch (NumberFormatException var10) {
                        ;
                    }
                }
            }
        }

        return var4;
    }

    public static final synchronized double readDouble() {
        String var0 = "";
        String var1 = "Input type: double";
        double var2 = 0.0D;
        boolean var4 = false;
        System.out.flush();

        while(!var4) {
            var0 = JOptionPane.showInputDialog(var1);
            if (var0 != null) {
                try {
                    var2 = Double.parseDouble(var0.trim());
                    var4 = true;
                } catch (NumberFormatException var6) {
                    ;
                }
            }
        }

        return var2;
    }

    public static final synchronized double[] readDoubleArray(String var0) {
        String var1 = "";
        Object var2 = null;
        boolean var3 = false;
        System.out.flush();
        String var4 = "";
        if (inputTypeInfo) {
            var4 = "Input type: double[], each element separated by a comma\n";
        }

        var0 = var0 + "\n";
        String var5 = " ";
        boolean var6 = false;
        ArrayList var7 = new ArrayList();

        while(true) {
            while(true) {
                int var15;
                do {
                    if (var3) {
                        int var14 = var7.size();
                        double[] var13 = new double[var14];

                        for(var15 = 0; var15 < var14; ++var15) {
                            var13[var15] = (Double)var7.get(var15);
                        }

                        return var13;
                    }

                    var1 = JOptionPane.showInputDialog(var4 + var0, var5);
                } while(var1 == null);

                if (var1.equals("")) {
                    var1 = null;
                } else {
                    boolean var8 = true;
                    String var9 = null;
                    boolean var10 = true;

                    while(var10) {
                        var15 = var1.indexOf(44);
                        if (var15 == -1) {
                            var9 = var1.trim();
                            var10 = false;
                            var3 = true;
                        } else {
                            var9 = var1.substring(0, var15).trim();
                            var1 = var1.substring(var15 + 1);
                        }

                        try {
                            var7.add(Double.valueOf(var9));
                        } catch (NumberFormatException var12) {
                            ;
                        }
                    }
                }
            }
        }
    }

    public static final synchronized Complex readComplex(String var0) {
        String var1 = "";
        Complex var2 = new Complex();
        boolean var3 = false;
        String var4 = "";
        if (inputTypeInfo) {
            var4 = "Input type: Complex (x + jy)\n";
        }

        System.out.flush();

        while(!var3) {
            var1 = JOptionPane.showInputDialog(var4 + var0);
            if (var1 != null) {
                try {
                    var2 = Complex.parseComplex(var1);
                    var3 = true;
                } catch (NumberFormatException var6) {
                    ;
                }
            }
        }

        return var2;
    }

    public static final synchronized Complex readComplex(String var0, Complex var1) {
        String var2 = "";
        Complex var3 = new Complex();
        boolean var4 = false;
        String var5 = "";
        if (inputTypeInfo) {
            var5 = "Input type: Complex (x + jy)\n";
        }

        String var6 = var1 + "";
        var0 = var0 + "\n";
        System.out.flush();

        while(!var4) {
            var2 = JOptionPane.showInputDialog(var5 + var0 + " [default value = " + var1 + "] ", var6);
            if (var2 != null) {
                if (var2.equals("")) {
                    var3 = var1;
                    var4 = true;
                    var2 = null;
                } else {
                    try {
                        var3 = Complex.parseComplex(var2);
                        var4 = true;
                    } catch (NumberFormatException var8) {
                        ;
                    }
                }
            }
        }

        return var3;
    }

    public static final synchronized Complex readComplex(String var0, String var1) {
        String var2 = "";
        Complex var3 = new Complex();
        boolean var4 = false;
        String var5 = "";
        if (inputTypeInfo) {
            var5 = "Input type: Complex (x + jy)\n";
        }

        String var6 = var1;
        var0 = var0 + "\n";
        System.out.flush();

        while(!var4) {
            var2 = JOptionPane.showInputDialog(var5 + var0 + " [default value = " + var1 + "] ", var6);
            if (var2 != null) {
                if (var2.equals("")) {
                    var3 = Complex.parseComplex(var1);
                    var4 = true;
                    var2 = null;
                } else {
                    try {
                        var3 = Complex.parseComplex(var2);
                        var4 = true;
                    } catch (NumberFormatException var8) {
                        ;
                    }
                }
            }
        }

        return var3;
    }

    public static final synchronized Complex readComplex() {
        String var0 = "";
        String var1 = "Input type: Complex (x + jy)";
        Complex var2 = new Complex();
        boolean var3 = false;
        System.out.flush();

        while(!var3) {
            var0 = JOptionPane.showInputDialog(var1);
            if (var0 != null) {
                try {
                    var2 = Complex.parseComplex(var0.trim());
                    var3 = true;
                } catch (NumberFormatException var5) {
                    ;
                }
            }
        }

        return var2;
    }

    public static final synchronized Phasor readPhasor(String var0) {
        String var1 = "";
        Phasor var2 = new Phasor();
        boolean var3 = false;
        String var4 = "";
        if (inputTypeInfo) {
            var4 = "Input type: Phasor ('mag'<'phase'deg or 'mag'<'phase'rad)\n";
        }

        System.out.flush();

        while(!var3) {
            var1 = JOptionPane.showInputDialog(var4 + var0);
            if (var1 != null) {
                try {
                    var2 = Phasor.parsePhasor(var1);
                    var3 = true;
                } catch (NumberFormatException var6) {
                    ;
                }
            }
        }

        return var2;
    }

    public static final synchronized Phasor readPhasor(String var0, Phasor var1) {
        String var2 = "";
        Phasor var3 = new Phasor();
        boolean var4 = false;
        String var5 = "";
        if (inputTypeInfo) {
            var5 = "Input type: Phasor ('mag'<'phase'deg or 'mag'<'phase'rad)\n";
        }

        String var6 = var1 + "";
        var0 = var0 + "\n";
        System.out.flush();

        while(!var4) {
            var2 = JOptionPane.showInputDialog(var5 + var0 + " [default value = " + var1 + "] ", var6);
            if (var2 != null) {
                if (var2.equals("")) {
                    var3 = var1;
                    var4 = true;
                    var2 = null;
                } else {
                    try {
                        var3 = Phasor.parsePhasor(var2);
                        var4 = true;
                    } catch (NumberFormatException var8) {
                        ;
                    }
                }
            }
        }

        return var3;
    }

    public static final synchronized Phasor readPhasor(String var0, String var1) {
        String var2 = "";
        Phasor var3 = new Phasor();
        boolean var4 = false;
        String var5 = "";
        if (inputTypeInfo) {
            var5 = "Input type: Phasor ('mag'<'phase'deg or 'mag'<'phase'rad)\n";
        }

        String var6 = var1;
        var0 = var0 + "\n";
        System.out.flush();

        while(!var4) {
            var2 = JOptionPane.showInputDialog(var5 + var0 + " [default value = " + var1 + "] ", var6);
            if (var2 != null) {
                if (var2.equals("")) {
                    var3 = Phasor.parsePhasor(var1);
                    var4 = true;
                    var2 = null;
                } else {
                    try {
                        var3 = Phasor.parsePhasor(var2);
                        var4 = true;
                    } catch (NumberFormatException var8) {
                        ;
                    }
                }
            }
        }

        return var3;
    }

    public static final synchronized Phasor readPhasor() {
        String var0 = "";
        String var1 = "Input type: Phasor ('mag'<'phase'deg or 'mag'<'phase'rad)";
        Phasor var2 = new Phasor();
        boolean var3 = false;
        System.out.flush();

        while(!var3) {
            var0 = JOptionPane.showInputDialog(var1);
            if (var0 != null) {
                try {
                    var2 = Phasor.parsePhasor(var0.trim());
                    var3 = true;
                } catch (NumberFormatException var5) {
                    ;
                }
            }
        }

        return var2;
    }

    public static final synchronized float readFloat(String var0) {
        String var1 = "";
        float var2 = 0.0F;
        boolean var3 = false;
        System.out.flush();
        String var4 = "";
        if (inputTypeInfo) {
            var4 = "Input type: float\n";
        }

        while(!var3) {
            var1 = JOptionPane.showInputDialog(var4 + var0);
            if (var1 != null) {
                try {
                    var2 = Float.parseFloat(var1.trim());
                    var3 = true;
                } catch (NumberFormatException var6) {
                    ;
                }
            }
        }

        return var2;
    }

    public static final synchronized float readFloat(String var0, float var1) {
        String var2 = "";
        float var3 = 0.0F;
        boolean var4 = false;
        System.out.flush();
        String var5 = "";
        if (inputTypeInfo) {
            var5 = "Input type: float\n";
        }

        var0 = var0 + "\n";
        String var6 = var1 + "";

        while(!var4) {
            var2 = JOptionPane.showInputDialog(var5 + var0 + " [default value = " + var1 + "] ", var6);
            if (var2 != null) {
                if (var2.equals("")) {
                    var3 = var1;
                    var4 = true;
                    var2 = null;
                } else {
                    try {
                        var3 = Float.parseFloat(var2.trim());
                        var4 = true;
                    } catch (NumberFormatException var8) {
                        ;
                    }
                }
            }
        }

        return var3;
    }

    public static final synchronized float readFloat() {
        String var0 = "";
        String var1 = "Input type: float";
        float var2 = 0.0F;
        boolean var3 = false;
        System.out.flush();

        while(!var3) {
            var0 = JOptionPane.showInputDialog(var1);
            if (var0 != null) {
                try {
                    var2 = Float.parseFloat(var0.trim());
                    var3 = true;
                } catch (NumberFormatException var5) {
                    ;
                }
            }
        }

        return var2;
    }

    public static final synchronized int readInt(String var0) {
        String var1 = "";
        int var2 = 0;
        boolean var3 = false;
        System.out.flush();
        String var4 = "";
        if (inputTypeInfo) {
            var4 = "Input type: int\n";
        }

        while(!var3) {
            var1 = JOptionPane.showInputDialog(var4 + var0);
            if (var1 != null) {
                try {
                    var2 = Integer.parseInt(var1.trim());
                    var3 = true;
                } catch (NumberFormatException var6) {
                    ;
                }
            }
        }

        return var2;
    }

    public static final synchronized int readInt(String var0, int var1) {
        String var2 = "";
        int var3 = 0;
        boolean var4 = false;
        String var5 = "";
        if (inputTypeInfo) {
            var5 = "Input type: int\n";
        }

        var0 = var0 + "\n";
        String var6 = var1 + "";
        System.out.flush();

        while(!var4) {
            var2 = JOptionPane.showInputDialog(var5 + var0 + " [default value = " + var1 + "] ", var6);
            if (var2 != null) {
                if (var2.equals("")) {
                    var3 = var1;
                    var4 = true;
                    var2 = null;
                } else {
                    try {
                        var3 = Integer.parseInt(var2.trim());
                        var4 = true;
                    } catch (NumberFormatException var8) {
                        ;
                    }
                }
            }
        }

        return var3;
    }

    public static final synchronized int readInt() {
        String var0 = "";
        String var1 = "Input type: int";
        int var2 = 0;
        boolean var3 = false;
        System.out.flush();

        while(!var3) {
            var0 = JOptionPane.showInputDialog(var1);
            if (var0 != null) {
                try {
                    var2 = Integer.parseInt(var0.trim());
                    var3 = true;
                } catch (NumberFormatException var5) {
                    ;
                }
            }
        }

        return var2;
    }

    public static final synchronized long readLong(String var0) {
        String var1 = "";
        long var2 = 0L;
        boolean var4 = false;
        String var5 = "";
        if (inputTypeInfo) {
            var5 = "Input type: long\n";
        }

        System.out.flush();

        while(!var4) {
            var1 = JOptionPane.showInputDialog(var5 + var0);
            if (var1 != null) {
                try {
                    var2 = Long.parseLong(var1.trim());
                    var4 = true;
                } catch (NumberFormatException var7) {
                    ;
                }
            }
        }

        return var2;
    }

    public static final synchronized long readLong(String var0, long var1) {
        String var3 = "";
        long var4 = 0L;
        boolean var6 = false;
        String var7 = "";
        if (inputTypeInfo) {
            var7 = "Input type: long\n";
        }

        var0 = var0 + "\n";
        String var8 = var1 + "";
        System.out.flush();

        while(!var6) {
            var3 = JOptionPane.showInputDialog(var7 + var0 + " [default value = " + var1 + "] ", var8);
            if (var3 != null) {
                if (var3.equals("")) {
                    var4 = var1;
                    var6 = true;
                    var3 = null;
                } else {
                    try {
                        var4 = Long.parseLong(var3.trim());
                        var6 = true;
                    } catch (NumberFormatException var10) {
                        ;
                    }
                }
            }
        }

        return var4;
    }

    public static final synchronized long readLong() {
        String var0 = "";
        String var1 = "Input type: long";
        long var2 = 0L;
        boolean var4 = false;
        System.out.flush();

        while(!var4) {
            var0 = JOptionPane.showInputDialog(var1);
            if (var0 != null) {
                try {
                    var2 = Long.parseLong(var0.trim());
                    var4 = true;
                } catch (NumberFormatException var6) {
                    ;
                }
            }
        }

        return var2;
    }

    public static final synchronized long readShort(String var0) {
        String var1 = "";
        long var2 = 0L;
        boolean var4 = false;
        String var5 = "";
        if (inputTypeInfo) {
            var5 = "Input type: short\n";
        }

        System.out.flush();

        while(!var4) {
            var1 = JOptionPane.showInputDialog(var5 + var0);
            if (var1 != null) {
                try {
                    var2 = (long)Short.parseShort(var1.trim());
                    var4 = true;
                } catch (NumberFormatException var7) {
                    ;
                }
            }
        }

        return var2;
    }

    public static final synchronized short readShort(String var0, short var1) {
        String var2 = "";
        short var3 = 0;
        boolean var4 = false;
        String var5 = "";
        if (inputTypeInfo) {
            var5 = "Input type: short\n";
        }

        var0 = var0 + "\n";
        String var6 = var1 + "";
        System.out.flush();

        while(!var4) {
            var2 = JOptionPane.showInputDialog(var5 + var0 + " [default value = " + var1 + "] ", var6);
            if (var2 != null) {
                if (var2.equals("")) {
                    var3 = var1;
                    var4 = true;
                    var2 = null;
                } else {
                    try {
                        var3 = Short.parseShort(var2.trim());
                        var4 = true;
                    } catch (NumberFormatException var8) {
                        ;
                    }
                }
            }
        }

        return var3;
    }

    public static final synchronized short readShort() {
        String var0 = "";
        String var1 = "Input type: short";
        short var2 = 0;
        boolean var3 = false;
        System.out.flush();

        while(!var3) {
            var0 = JOptionPane.showInputDialog(var1);
            if (var0 != null) {
                try {
                    var2 = Short.parseShort(var0.trim());
                    var3 = true;
                } catch (NumberFormatException var5) {
                    ;
                }
            }
        }

        return var2;
    }

    public static final synchronized long readByte(String var0) {
        String var1 = "";
        long var2 = 0L;
        boolean var4 = false;
        String var5 = "";
        if (inputTypeInfo) {
            var5 = "Input type: short\n";
        }

        System.out.flush();

        while(!var4) {
            var1 = JOptionPane.showInputDialog(var5 + var0);
            if (var1 != null) {
                try {
                    var2 = (long)Byte.parseByte(var1.trim());
                    var4 = true;
                } catch (NumberFormatException var7) {
                    ;
                }
            }
        }

        return var2;
    }

    public static final synchronized byte readByte(String var0, byte var1) {
        String var2 = "";
        byte var3 = 0;
        boolean var4 = false;
        String var5 = "";
        if (inputTypeInfo) {
            var5 = "Input type: byte\n";
        }

        var0 = var0 + "\n";
        String var6 = var1 + "";
        System.out.flush();

        while(!var4) {
            var2 = JOptionPane.showInputDialog(var5 + var0 + " [default value = " + var1 + "] ", var6);
            if (var2 != null) {
                if (var2.equals("")) {
                    var3 = var1;
                    var4 = true;
                    var2 = null;
                } else {
                    try {
                        var3 = Byte.parseByte(var2.trim());
                        var4 = true;
                    } catch (NumberFormatException var8) {
                        ;
                    }
                }
            }
        }

        return var3;
    }

    public static final synchronized byte readByte() {
        String var0 = "";
        String var1 = "Input type: byte";
        byte var2 = 0;
        boolean var3 = false;
        System.out.flush();

        while(!var3) {
            var0 = JOptionPane.showInputDialog(var1);
            if (var0 != null) {
                try {
                    var2 = Byte.parseByte(var0.trim());
                    var3 = true;
                } catch (NumberFormatException var5) {
                    ;
                }
            }
        }

        return var2;
    }

    public static final synchronized char readChar(String var0) {
        String var1 = "";
        char var2 = ' ';
        boolean var3 = false;
        String var4 = "";
        if (inputTypeInfo) {
            var4 = "Input type: char\n";
        }

        System.out.flush();

        while(!var3) {
            var1 = JOptionPane.showInputDialog(var4 + var0);
            if (var1 != null && !var1.equals("")) {
                var2 = var1.charAt(0);
                var3 = true;
            }
        }

        return var2;
    }

    public static final synchronized char readChar(String var0, char var1) {
        String var2 = "";
        char var3 = ' ';
        boolean var4 = false;
        String var5 = "";
        if (inputTypeInfo) {
            var5 = "Input type: char\n";
        }

        var0 = var0 + "\n";
        String var6 = var1 + "";
        System.out.flush();

        while(!var4) {
            var2 = JOptionPane.showInputDialog(var5 + var0 + " [default value = " + var1 + "] ", var6);
            if (var2 != null) {
                if (var2.equals("")) {
                    var3 = var1;
                    var4 = true;
                    var2 = null;
                } else {
                    var3 = var2.charAt(0);
                    var4 = true;
                }
            }
        }

        return var3;
    }

    public static final synchronized char readChar() {
        String var0 = "";
        String var1 = "Input type: char";
        char var2 = ' ';
        boolean var3 = false;
        System.out.flush();

        while(!var3) {
            var0 = JOptionPane.showInputDialog(var1);
            if (var0 != null && !var0.equals("")) {
                var2 = var0.charAt(0);
                var3 = true;
            }
        }

        return var2;
    }

    public static final synchronized String readLine(String var0) {
        String var1 = "";
        boolean var2 = false;
        String var3 = "";
        if (inputTypeInfo) {
            var3 = "Input type: String [a line]\n";
        }

        System.out.flush();

        while(!var2) {
            var1 = JOptionPane.showInputDialog(var3 + var0);
            if (var1 != null) {
                var2 = true;
            }
        }

        return var1;
    }

    public static final synchronized String readLine(String var0, String var1) {
        String var2 = "";
        boolean var3 = false;
        String var4 = "";
        if (inputTypeInfo) {
            var4 = "Input type: String [a line]\n";
        }

        var0 = var0 + "\n";
        String var5 = var1 + "";
        System.out.flush();

        while(!var3) {
            var2 = JOptionPane.showInputDialog(var4 + var0 + " [default value = " + var1 + "] ", var5);
            if (var2 != null) {
                if (var2.equals("")) {
                    var2 = var1;
                    var3 = true;
                } else {
                    var3 = true;
                }
            }
        }

        return var2;
    }

    public static final synchronized String readLine() {
        String var0 = "";
        String var1 = "Input type: String [a line]";
        boolean var2 = false;
        System.out.flush();

        while(!var2) {
            var0 = JOptionPane.showInputDialog(var1);
            if (var0 != null) {
                var2 = true;
            }
        }

        return var0;
    }

    public static final synchronized boolean readBoolean(String var0, boolean var1) {
        String var2 = "";
        boolean var3 = false;
        boolean var4 = false;
        System.out.flush();
        String var5 = "";
        if (inputTypeInfo) {
            var5 = "Input boolean\n";
        }

        var0 = var0 + "\n";
        String var6 = var1 + "";

        while(true) {
            while(true) {
                do {
                    if (var4) {
                        return var3;
                    }

                    var2 = JOptionPane.showInputDialog(var5 + var0 + " [default value = " + var1 + "] ", var6);
                } while(var2 == null);

                if (var2.equals("")) {
                    var3 = var1;
                    var4 = true;
                    var2 = null;
                } else if (!var2.equals("true") && !var2.trim().equals("TRUE")) {
                    if (var2.equals("false") || var2.trim().equals("FALSE")) {
                        var3 = false;
                        var4 = true;
                    }
                } else {
                    var3 = true;
                    var4 = true;
                }
            }
        }
    }

    public static final synchronized boolean readBoolean(String var0) {
        String var1 = "";
        boolean var2 = false;
        boolean var3 = false;
        System.out.flush();
        String var4 = "";
        if (inputTypeInfo) {
            var4 = "Input boolean\n";
        }

        while(true) {
            do {
                while(true) {
                    do {
                        if (var3) {
                            return var2;
                        }

                        var1 = JOptionPane.showInputDialog(var4 + var0);
                    } while(var1 == null);

                    if (!var1.equals("true") && !var1.trim().equals("TRUE")) {
                        break;
                    }

                    var2 = true;
                    var3 = true;
                }
            } while(!var1.equals("false") && !var1.trim().equals("FALSE"));

            var2 = false;
            var3 = true;
        }
    }

    public static final synchronized boolean readBoolean() {
        String var0 = "";
        String var1 = "Input type: boolean";
        boolean var2 = false;
        boolean var3 = false;
        System.out.flush();

        while(true) {
            do {
                while(true) {
                    do {
                        if (var3) {
                            return var2;
                        }

                        var0 = JOptionPane.showInputDialog(var1);
                    } while(var0 == null);

                    if (!var0.equals("true") && !var0.trim().equals("TRUE")) {
                        break;
                    }

                    var2 = true;
                    var3 = true;
                }
            } while(!var0.equals("false") && !var0.trim().equals("FALSE"));

            var2 = false;
            var3 = true;
        }
    }

    public static final synchronized BigDecimal readBigDecimal(String var0) {
        String var1 = "";
        BigDecimal var2 = null;
        boolean var3 = false;
        String var4 = "";
        if (inputTypeInfo) {
            var4 = "Input type: BigDecimal\n";
        }

        System.out.flush();

        while(!var3) {
            var1 = JOptionPane.showInputDialog(var4 + var0);
            if (var1 != null) {
                try {
                    var2 = new BigDecimal(var1);
                    var3 = true;
                } catch (NumberFormatException var6) {
                    ;
                }
            }
        }

        return var2;
    }

    public static final synchronized BigDecimal readBigDecimal(String var0, BigDecimal var1) {
        String var2 = "";
        BigDecimal var3 = null;
        boolean var4 = false;
        String var5 = "";
        if (inputTypeInfo) {
            var5 = "Input type: BigDecimal\n";
        }

        String var6 = var1.toString() + "";
        var0 = var0 + "\n";
        System.out.flush();

        while(!var4) {
            var2 = JOptionPane.showInputDialog(var5 + var0 + " [default value = " + var1 + "] ", var6);
            if (var2 != null) {
                if (var2.equals("")) {
                    var3 = var1;
                    var4 = true;
                    var2 = null;
                } else {
                    try {
                        var3 = new BigDecimal(var2);
                        var4 = true;
                    } catch (NumberFormatException var8) {
                        ;
                    }
                }
            }
        }

        return var3;
    }

    public static final synchronized BigDecimal readBigDecimal(String var0, String var1) {
        String var2 = "";
        BigDecimal var3 = null;
        boolean var4 = false;
        String var5 = "";
        if (inputTypeInfo) {
            var5 = "Input type: BigDecimal\n";
        }

        String var6 = var1;
        var0 = var0 + "\n";
        System.out.flush();

        while(!var4) {
            var2 = JOptionPane.showInputDialog(var5 + var0 + " [default value = " + var1 + "] ", var6);
            if (var2 != null) {
                if (var2.equals("")) {
                    var3 = new BigDecimal(var1);
                    var4 = true;
                    var2 = null;
                } else {
                    try {
                        var3 = new BigDecimal(var2);
                        var4 = true;
                    } catch (NumberFormatException var8) {
                        ;
                    }
                }
            }
        }

        return var3;
    }

    public static final synchronized BigDecimal readBigDecimal(String var0, double var1) {
        String var3 = "";
        BigDecimal var4 = null;
        boolean var5 = false;
        String var6 = "";
        if (inputTypeInfo) {
            var6 = "Input type: BigDecimal\n";
        }

        Double var7 = new Double(var1);
        String var8 = var7.toString();
        var0 = var0 + "\n";
        System.out.flush();

        while(!var5) {
            var3 = JOptionPane.showInputDialog(var6 + var0 + " [default value = " + var1 + "] ", var8);
            if (var3 != null) {
                if (var3.equals("")) {
                    var4 = new BigDecimal(var8);
                    var5 = true;
                    var3 = null;
                } else {
                    try {
                        var4 = new BigDecimal(var3);
                        var5 = true;
                    } catch (NumberFormatException var10) {
                        ;
                    }
                }
            }
        }

        return var4;
    }

    public static final synchronized BigDecimal readBigDecimal(String var0, float var1) {
        String var2 = "";
        BigDecimal var3 = null;
        boolean var4 = false;
        String var5 = "";
        if (inputTypeInfo) {
            var5 = "Input type: BigDecimal\n";
        }

        Float var6 = new Float(var1);
        String var7 = var6.toString();
        var0 = var0 + "\n";
        System.out.flush();

        while(!var4) {
            var2 = JOptionPane.showInputDialog(var5 + var0 + " [default value = " + var1 + "] ", var7);
            if (var2 != null) {
                if (var2.equals("")) {
                    var3 = new BigDecimal(var7);
                    var4 = true;
                    var2 = null;
                } else {
                    try {
                        var3 = new BigDecimal(var2);
                        var4 = true;
                    } catch (NumberFormatException var9) {
                        ;
                    }
                }
            }
        }

        return var3;
    }

    public static final synchronized BigDecimal readBigDecimal(String var0, long var1) {
        String var3 = "";
        BigDecimal var4 = null;
        boolean var5 = false;
        String var6 = "";
        if (inputTypeInfo) {
            var6 = "Input type: BigDecimal\n";
        }

        Long var7 = new Long(var1);
        String var8 = var7.toString();
        var0 = var0 + "\n";
        System.out.flush();

        while(!var5) {
            var3 = JOptionPane.showInputDialog(var6 + var0 + " [default value = " + var1 + "] ", var8);
            if (var3 != null) {
                if (var3.equals("")) {
                    var4 = new BigDecimal(var8);
                    var5 = true;
                    var3 = null;
                } else {
                    try {
                        var4 = new BigDecimal(var3);
                        var5 = true;
                    } catch (NumberFormatException var10) {
                        ;
                    }
                }
            }
        }

        return var4;
    }

    public static final synchronized BigDecimal readBigDecimal(String var0, int var1) {
        String var2 = "";
        BigDecimal var3 = null;
        boolean var4 = false;
        String var5 = "";
        if (inputTypeInfo) {
            var5 = "Input type: BigDecimal\n";
        }

        Integer var6 = new Integer(var1);
        String var7 = var6.toString();
        var0 = var0 + "\n";
        System.out.flush();

        while(!var4) {
            var2 = JOptionPane.showInputDialog(var5 + var0 + " [default value = " + var1 + "] ", var7);
            if (var2 != null) {
                if (var2.equals("")) {
                    var3 = new BigDecimal(var7);
                    var4 = true;
                    var2 = null;
                } else {
                    try {
                        var3 = new BigDecimal(var2);
                        var4 = true;
                    } catch (NumberFormatException var9) {
                        ;
                    }
                }
            }
        }

        return var3;
    }

    public static final synchronized BigDecimal readBigDecimal() {
        String var0 = "";
        String var1 = "Input type: BigDecimal";
        BigDecimal var2 = null;
        boolean var3 = false;
        System.out.flush();

        while(!var3) {
            var0 = JOptionPane.showInputDialog(var1);
            if (var0 != null) {
                try {
                    var2 = new BigDecimal(var0.trim());
                    var3 = true;
                } catch (NumberFormatException var5) {
                    ;
                }
            }
        }

        return var2;
    }

    public static final synchronized BigInteger readBigInteger(String var0) {
        String var1 = "";
        BigInteger var2 = null;
        boolean var3 = false;
        String var4 = "";
        if (inputTypeInfo) {
            var4 = "Input type: BigInteger\n";
        }

        System.out.flush();

        while(!var3) {
            var1 = JOptionPane.showInputDialog(var4 + var0);
            if (var1 != null) {
                try {
                    var2 = new BigInteger(var1);
                    var3 = true;
                } catch (NumberFormatException var6) {
                    ;
                }
            }
        }

        return var2;
    }

    public static final synchronized BigInteger readBigInteger(String var0, BigInteger var1) {
        String var2 = "";
        BigInteger var3 = null;
        boolean var4 = false;
        String var5 = "";
        if (inputTypeInfo) {
            var5 = "Input type: BigInteger\n";
        }

        String var6 = var1.toString() + "";
        var0 = var0 + "\n";
        System.out.flush();

        while(!var4) {
            var2 = JOptionPane.showInputDialog(var5 + var0 + " [default value = " + var1 + "] ", var6);
            if (var2 != null) {
                if (var2.equals("")) {
                    var3 = var1;
                    var4 = true;
                    var2 = null;
                } else {
                    try {
                        var3 = new BigInteger(var2);
                        var4 = true;
                    } catch (NumberFormatException var8) {
                        ;
                    }
                }
            }
        }

        return var3;
    }

    public static final synchronized BigInteger readBigInteger(String var0, String var1) {
        String var2 = "";
        BigInteger var3 = null;
        boolean var4 = false;
        String var5 = "";
        if (inputTypeInfo) {
            var5 = "Input type: BigInteger\n";
        }

        String var6 = var1;
        var0 = var0 + "\n";
        System.out.flush();

        while(!var4) {
            var2 = JOptionPane.showInputDialog(var5 + var0 + " [default value = " + var1 + "] ", var6);
            if (var2 != null) {
                if (var2.equals("")) {
                    var3 = new BigInteger(var1);
                    var4 = true;
                    var2 = null;
                } else {
                    try {
                        var3 = new BigInteger(var2);
                        var4 = true;
                    } catch (NumberFormatException var8) {
                        ;
                    }
                }
            }
        }

        return var3;
    }

    public static final synchronized BigInteger readBigInteger(String var0, long var1) {
        String var3 = "";
        BigInteger var4 = null;
        boolean var5 = false;
        String var6 = "";
        if (inputTypeInfo) {
            var6 = "Input type: BigInteger\n";
        }

        Long var7 = new Long(var1);
        String var8 = var7.toString();
        var0 = var0 + "\n";
        System.out.flush();

        while(!var5) {
            var3 = JOptionPane.showInputDialog(var6 + var0 + " [default value = " + var1 + "] ", var8);
            if (var3 != null) {
                if (var3.equals("")) {
                    var4 = new BigInteger(var8);
                    var5 = true;
                    var3 = null;
                } else {
                    try {
                        var4 = new BigInteger(var3);
                        var5 = true;
                    } catch (NumberFormatException var10) {
                        ;
                    }
                }
            }
        }

        return var4;
    }

    public static final synchronized BigInteger readBigInteger(String var0, int var1) {
        String var2 = "";
        BigInteger var3 = null;
        boolean var4 = false;
        String var5 = "";
        if (inputTypeInfo) {
            var5 = "Input type: BigInteger\n";
        }

        Integer var6 = new Integer(var1);
        String var7 = var6.toString();
        var0 = var0 + "\n";
        System.out.flush();

        while(!var4) {
            var2 = JOptionPane.showInputDialog(var5 + var0 + " [default value = " + var1 + "] ", var7);
            if (var2 != null) {
                if (var2.equals("")) {
                    var3 = new BigInteger(var7);
                    var4 = true;
                    var2 = null;
                } else {
                    try {
                        var3 = new BigInteger(var2);
                        var4 = true;
                    } catch (NumberFormatException var9) {
                        ;
                    }
                }
            }
        }

        return var3;
    }

    public static final synchronized BigInteger readBigInteger() {
        String var0 = "";
        String var1 = "Input type: BigInteger";
        BigInteger var2 = null;
        boolean var3 = false;
        System.out.flush();

        while(!var3) {
            var0 = JOptionPane.showInputDialog(var1);
            if (var0 != null) {
                try {
                    var2 = new BigInteger(var0.trim());
                    var3 = true;
                } catch (NumberFormatException var5) {
                    ;
                }
            }
        }

        return var2;
    }

    public static final synchronized boolean yesNo(String var0) {
        int var1 = JOptionPane.showConfirmDialog((Component)null, var0, "Db Class Yes or No Box", 0, 3);
        boolean var2 = false;
        if (var1 == 0) {
            var2 = true;
        }

        return var2;
    }

    public static final synchronized boolean noYes(String var0) {
        Object[] var1 = new Object[]{"Yes", "No"};
        int var2 = JOptionPane.showOptionDialog((Component)null, var0, "Db Class Yes or No Box", 0, 3, (Icon)null, var1, var1[1]);
        boolean var3 = false;
        if (var2 == 0) {
            var3 = true;
        }

        return var3;
    }

    public static final synchronized void show(String var0, double var1) {
        JOptionPane.showMessageDialog((Component)null, var0 + " " + var1, "Db.show (double)", 1);
    }

    public static final synchronized void show(double var0) {
        JOptionPane.showMessageDialog((Component)null, " " + var0, "Db.show (double)", 1);
    }

    public static final synchronized void show(String var0, double var1, int var3) {
        JOptionPane.showMessageDialog((Component)null, var0 + " " + Fmath.truncate(var1, var3), "Db.show (double)", 1);
    }

    public static final synchronized void show(double var0, int var2) {
        JOptionPane.showMessageDialog((Component)null, " " + Fmath.truncate(var0, var2), "Db.show (double)", 1);
    }

    public static final synchronized void show(String var0, Double var1) {
        JOptionPane.showMessageDialog((Component)null, var0 + " " + var1, "Db.show (Double)", 1);
    }

    public static final synchronized void show(Double var0) {
        JOptionPane.showMessageDialog((Component)null, " " + var0, "Db.show (Double)", 1);
    }

    public static final synchronized void show(String var0, float var1) {
        JOptionPane.showMessageDialog((Component)null, var0 + " " + var1, "Db.show (float)", 1);
    }

    public static final synchronized void show(float var0) {
        JOptionPane.showMessageDialog((Component)null, " " + var0, "Db.show (float)", 1);
    }

    public static final synchronized void show(String var0, float var1, int var2) {
        JOptionPane.showMessageDialog((Component)null, var0 + " " + Fmath.truncate(var1, var2), "Db.show (float)", 1);
    }

    public static final synchronized void show(float var0, int var1) {
        JOptionPane.showMessageDialog((Component)null, " " + Fmath.truncate(var0, var1), "Db.show (float)", 1);
    }

    public static final synchronized void show(String var0, Float var1) {
        JOptionPane.showMessageDialog((Component)null, var0 + " " + var1, "Db.show (float)", 1);
    }

    public static final synchronized void show(Float var0) {
        JOptionPane.showMessageDialog((Component)null, " " + var0, "Db.show (float)", 1);
    }

    public static final synchronized void show(String var0, BigDecimal var1) {
        JOptionPane.showMessageDialog((Component)null, var0 + " " + var1.toString(), "Db.show (BigDecimal)", 1);
    }

    public static final synchronized void show(BigDecimal var0) {
        JOptionPane.showMessageDialog((Component)null, " " + var0.toString(), "Db.show (BigDecimal)", 1);
    }

    public static final synchronized void show(String var0, BigInteger var1) {
        JOptionPane.showMessageDialog((Component)null, var0 + " " + var1.toString(), "Db.show (BigInteger)", 1);
    }

    public static final synchronized void show(BigInteger var0) {
        JOptionPane.showMessageDialog((Component)null, " " + var0.toString(), "Db.show (BigInteger)", 1);
    }

    public static final synchronized void show(String var0, int var1) {
        JOptionPane.showMessageDialog((Component)null, var0 + " " + var1, "Db.show (int)", 1);
    }

    public static final synchronized void show(int var0) {
        JOptionPane.showMessageDialog((Component)null, " " + var0, "Db.show (int)", 1);
    }

    public static final synchronized void show(String var0, Integer var1) {
        JOptionPane.showMessageDialog((Component)null, var0 + " " + var1, "Db.show (int)", 1);
    }

    public static final synchronized void show(Integer var0) {
        JOptionPane.showMessageDialog((Component)null, " " + var0, "Db.show (int)", 1);
    }

    public static final synchronized void show(String var0, long var1) {
        JOptionPane.showMessageDialog((Component)null, var0 + " " + var1, "Db.show (long)", 1);
    }

    public static final synchronized void show(long var0) {
        JOptionPane.showMessageDialog((Component)null, " " + var0, "Db.show (long)", 1);
    }

    public static final synchronized void show(String var0, Long var1) {
        JOptionPane.showMessageDialog((Component)null, var0 + " " + var1, "Db.show (long)", 1);
    }

    public static final synchronized void show(Long var0) {
        JOptionPane.showMessageDialog((Component)null, " " + var0, "Db.show (long)", 1);
    }

    public static final synchronized void show(String var0, short var1) {
        JOptionPane.showMessageDialog((Component)null, var0 + " " + var1, "Db.show (short)", 1);
    }

    public static final synchronized void show(short var0) {
        JOptionPane.showMessageDialog((Component)null, " " + var0, "Db.show (short)", 1);
    }

    public static final synchronized void show(String var0, Short var1) {
        JOptionPane.showMessageDialog((Component)null, var0 + " " + var1, "Db.show (short)", 1);
    }

    public static final synchronized void show(Short var0) {
        JOptionPane.showMessageDialog((Component)null, " " + var0, "Db.show (short)", 1);
    }

    public static final synchronized void show(String var0, byte var1) {
        JOptionPane.showMessageDialog((Component)null, var0 + " " + var1, "Db.show (byte)", 1);
    }

    public static final synchronized void show(byte var0) {
        JOptionPane.showMessageDialog((Component)null, " " + var0, "Db.show (byte)", 1);
    }

    public static final synchronized void show(String var0, Byte var1) {
        JOptionPane.showMessageDialog((Component)null, var0 + " " + var1, "Db.show (byte)", 1);
    }

    public static final synchronized void show(Byte var0) {
        JOptionPane.showMessageDialog((Component)null, " " + var0, "Db.show (byte)", 1);
    }

    public static final synchronized void show(String var0, Complex var1) {
        JOptionPane.showMessageDialog((Component)null, var0 + " " + var1, "Db.show (Complex)", 1);
    }

    public static final synchronized void show(Complex var0) {
        JOptionPane.showMessageDialog((Component)null, " " + var0, "Db.show (Complex)", 1);
    }

    public static final synchronized void show(String var0, Complex var1, int var2) {
        JOptionPane.showMessageDialog((Component)null, var0 + " " + Complex.truncate(var1, var2), "Db.show (Complex)", 1);
    }

    public static final synchronized void show(Complex var0, int var1) {
        JOptionPane.showMessageDialog((Component)null, " " + Complex.truncate(var0, var1), "Db.show (Complex)", 1);
    }

    public static final synchronized void show(String var0, Phasor var1) {
        JOptionPane.showMessageDialog((Component)null, var0 + " " + var1, "Db.show (Phasor)", 1);
    }

    public static final synchronized void show(Phasor var0) {
        JOptionPane.showMessageDialog((Component)null, " " + var0, "Db.show (Phasor)", 1);
    }

    public static final synchronized void show(String var0, Phasor var1, int var2) {
        JOptionPane.showMessageDialog((Component)null, var0 + " " + Phasor.truncate(var1, var2), "Db.show (Phasor)", 1);
    }

    public static final synchronized void show(Phasor var0, int var1) {
        JOptionPane.showMessageDialog((Component)null, " " + Phasor.truncate(var0, var1), "Db.show (Phasor)", 1);
    }

    public static final synchronized void show(String var0, ErrorProp var1) {
        JOptionPane.showMessageDialog((Component)null, var0 + " " + var1, "Db.show (ErrorProp)", 1);
    }

    public static final synchronized void show(ErrorProp var0) {
        JOptionPane.showMessageDialog((Component)null, " " + var0, "Db.show (ErrorProp)", 1);
    }

    public static final synchronized void show(String var0, ErrorProp var1, int var2) {
        JOptionPane.showMessageDialog((Component)null, var0 + " " + ErrorProp.truncate(var1, var2), "Db.show (ErrorProp)", 1);
    }

    public static final synchronized void show(ErrorProp var0, int var1) {
        JOptionPane.showMessageDialog((Component)null, " " + ErrorProp.truncate(var0, var1), "Db.show (ErrorProp)", 1);
    }

    public static final synchronized void show(String var0, ComplexErrorProp var1) {
        JOptionPane.showMessageDialog((Component)null, var0 + " " + var1, "Db.show (ComplexErrorProp)", 1);
    }

    public static final synchronized void show(ComplexErrorProp var0) {
        JOptionPane.showMessageDialog((Component)null, " " + var0, "Db.show (ComplexErrorProp)", 1);
    }

    public static final synchronized void show(String var0, ComplexErrorProp var1, int var2) {
        JOptionPane.showMessageDialog((Component)null, var0 + " " + ComplexErrorProp.truncate(var1, var2), "Db.show (ComplexErrorProp)", 1);
    }

    public static final synchronized void show(ComplexErrorProp var0, int var1) {
        JOptionPane.showMessageDialog((Component)null, " " + ComplexErrorProp.truncate(var0, var1), "Db.show (ComplexErrorProp)", 1);
    }

    public static final synchronized void show(String var0, boolean var1) {
        JOptionPane.showMessageDialog((Component)null, var0 + " " + var1, "Db.show (boolean)", 1);
    }

    public static final synchronized void show(boolean var0) {
        JOptionPane.showMessageDialog((Component)null, " " + var0, "Db.show (boolean)", 1);
    }

    public static final synchronized void show(String var0, Boolean var1) {
        JOptionPane.showMessageDialog((Component)null, var0 + " " + var1, "Db.show (boolean)", 1);
    }

    public static final synchronized void show(Boolean var0) {
        JOptionPane.showMessageDialog((Component)null, " " + var0, "Db.show (boolean)", 1);
    }

    public static final synchronized void show(String var0, char var1) {
        JOptionPane.showMessageDialog((Component)null, var0 + " " + var1, "Db.show (char)", 1);
    }

    public static final synchronized void show(char var0) {
        JOptionPane.showMessageDialog((Component)null, " " + var0, "Db.show (char)", 1);
    }

    public static final synchronized void show(String var0, Character var1) {
        JOptionPane.showMessageDialog((Component)null, var0 + " " + var1, "Db.show (char)", 1);
    }

    public static final synchronized void show(Character var0) {
        JOptionPane.showMessageDialog((Component)null, " " + var0, "Db.show (char)", 1);
    }

    public static final synchronized void show(String var0, String var1) {
        JOptionPane.showMessageDialog((Component)null, var0 + " " + var1, "Db.show (String)", 1);
    }

    public static final synchronized void show(String var0) {
        JOptionPane.showMessageDialog((Component)null, var0, "Db.show (message only)", 1);
    }

    public static final synchronized int optionBox(String var0, String[] var1, String[] var2, int var3) {
        int var4 = var2.length;
        if (var4 != var1.length) {
            throw new IllegalArgumentException("There must be the same number of boxTitles and comments");
        } else {
            Object[] var5 = new Object[var4];

            for(int var6 = 0; var6 < var4; ++var6) {
                var5[var6] = "(" + (var6 + 1) + ") " + var2[var6];
            }

            String var8 = "1. " + var1[0] + "\n";

            for(int var7 = 1; var7 < var4; ++var7) {
                var8 = var8 + (var7 + 1) + ". " + var1[var7] + "\n";
            }

            return 1 + JOptionPane.showOptionDialog((Component)null, var8, var0, 1, 3, (Icon)null, var5, var5[var3 - 1]);
        }
    }

    public static final synchronized int optionBox(String var0, String var1, String[] var2, int var3) {
        int var4 = var2.length;
        Object[] var5 = new Object[var4];

        for(int var6 = 0; var6 < var4; ++var6) {
            var5[var6] = "(" + (var6 + 1) + ") " + var2[var6];
        }

        return 1 + JOptionPane.showOptionDialog((Component)null, var1, var0, 1, 3, (Icon)null, var5, var5[var3 - 1]);
    }

    public static final synchronized void endProgram() {
        int var0 = JOptionPane.showConfirmDialog((Component)null, "Do you wish to end the program", "End Program", 0, 3);
        if (var0 == 0) {
            System.exit(0);
        } else {
            JOptionPane.showMessageDialog((Component)null, "Now you must press the appropriate escape key/s, e.g. Ctrl C, to exit this program");
        }

    }
}
