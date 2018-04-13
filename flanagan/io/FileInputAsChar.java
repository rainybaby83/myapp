//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package flanagan.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class FileInputAsChar {
    protected String fileName = "";
    protected String stemName = "";
    protected String pathName = "";
    protected String dirPath = "";
    protected BufferedReader input = null;
    protected boolean testFullLine = false;
    protected boolean testFullLineT = false;
    protected boolean eof = false;
    protected boolean fileFound = true;

    public FileInputAsChar(String var1) {
        this.pathName = var1;
        this.fileName = var1;
        int var2 = var1.indexOf("//");
        int var3 = var1.indexOf("\\");
        if (var2 != -1 || var3 != -1) {
            File var4 = new File(this.pathName);
            this.fileName = var4.getName();
            this.dirPath = var4.getParentFile().toString();
        }

        int var7 = this.fileName.indexOf(46);
        if (var7 == -1) {
            this.stemName = this.fileName;
        } else {
            this.stemName = this.fileName.substring(0, var7);
        }

        try {
            this.input = new BufferedReader(new FileReader(this.pathName));
        } catch (FileNotFoundException var6) {
            System.out.println(var6);
            this.fileFound = false;
        }

    }

    public String getPathName() {
        return this.pathName;
    }

    public String getFileName() {
        return this.fileName;
    }

    public String getStemName() {
        return this.stemName;
    }

    public String getDirPath() {
        return this.dirPath;
    }

    public final synchronized char readchar() {
        int var1 = -1;
        boolean var2 = false;

        try {
            var1 = this.input.read();
        } catch (IOException var4) {
            System.out.println(var4);
        }

        char var5;
        if (var1 == -1) {
            System.out.println("FileManip.readchar:  attempt to read beyond end of file");
            this.eof = true;
            var5 = 0;
        } else {
            var5 = (char)var1;
        }

        return var5;
    }

    public final synchronized Character readCharacter() {
        int var1 = -1;
        boolean var2 = false;
        Character var3 = null;

        try {
            var1 = this.input.read();
        } catch (IOException var5) {
            System.out.println(var5);
        }

        if (var1 == -1) {
            System.out.println("FileManip.readChar:  attempt to read beyond end of file");
            this.eof = true;
            var2 = false;
            var3 = null;
        } else {
            char var6 = (char)var1;
            var3 = new Character(var6);
        }

        return var3;
    }

    public final synchronized int readint() {
        int var1 = -1;

        try {
            var1 = this.input.read();
        } catch (IOException var3) {
            System.out.println(var3);
        }

        if (var1 == -1) {
            System.out.println("FileManip.readint:  attempt to read beyond end of file");
            this.eof = true;
        }

        return var1;
    }

    public final synchronized void close() {
        if (this.fileFound) {
            try {
                this.input.close();
            } catch (IOException var2) {
                System.out.println(var2);
            }
        }

    }

    public boolean eof() {
        return this.eof;
    }

    public boolean fileFound() {
        return this.fileFound;
    }

    public String toSingleLine() {
        String var1 = "";
        int var2 = 0;
        boolean var3 = true;

        while(var3) {
            try {
                var2 = this.input.read();
            } catch (IOException var5) {
                var3 = false;
            }

            if (var2 == -1) {
                this.eof = true;
                var3 = false;
            }

            if (var3 && var2 > 31) {
                var1 = var1 + (char)var2;
            }
        }

        return var1;
    }

    public void toSingleLineFile() {
        String var1 = this.stemName + "SingleLine" + ".txt";
        var1 = Db.readLine("Input the single line filename", var1);
        this.toSingleLineFile(var1);
    }

    public void toSingleLineFile(String var1) {
        FileOutput var2 = new FileOutput(var1);
        String var3 = this.toString();
        var2.println(var3);
        var2.close();
    }

    public void toLineLength(int var1) {
        String var2 = this.stemName + "LineLength" + var1 + ".txt";
        var2 = Db.readLine("Input the output filename", var2);
        this.toLineLength(var1, var2);
    }

    public void toLineLength(int var1, String var2) {
        FileOutput var3 = new FileOutput(var2);
        String var4 = this.toSingleLine();
        String var5 = var4;
        String var6 = null;
        boolean var7 = true;
        boolean var8 = true;
        boolean var9 = false;

        while(var8) {
            int var11 = var5.length();
            if (var11 <= var1) {
                var3.println(var5);
                var8 = false;
            } else if (var5.charAt(var1) == ' ') {
                var3.println(var5.substring(0, var1));
                if (var1 + 1 < var11) {
                    var5 = var5.substring(var1 + 1);
                    var11 = var5.length();
                } else {
                    var8 = false;
                }
            } else {
                var6 = var5.substring(0, var1);
                int var10 = var6.lastIndexOf(32);
                var3.println(var6.substring(0, var10));
                if (var10 + 1 < var11) {
                    var5 = var5.substring(var10 + 1);
                    var11 = var5.length();
                } else {
                    var8 = false;
                }
            }
        }

        var3.close();
    }
}
