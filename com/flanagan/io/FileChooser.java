//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package flanagan.io;

import java.awt.Component;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class FileChooser extends FileInput {
    private File file;
    private String path = null;
    private String extn = null;

    public FileChooser() {
        this.path = System.getProperty("user.dir");
    }

    public FileChooser(String var1) {
        this.path = var1;
    }

    public String selectFile() {
        return this.selectFile("Select File");
    }

    public String selectFile(String var1) {
        JFileChooser var2 = new JFileChooser(this.path);
        if (this.extn != null) {
            FileTypeFilter var3 = new FileTypeFilter();
            var3.addExtension(this.extn);
            var3.setDescription(this.extn + " files");
            var2.setFileFilter(var3);
        } else {
            var2.setAcceptAllFileFilterUsed(true);
        }

        var2.setDialogTitle(var1);
        var2.showOpenDialog((Component)null);
        this.file = var2.getSelectedFile();
        if (this.file == null) {
            super.fileName = null;
            super.stemName = null;
            super.extension = null;
            super.pathName = null;
            super.dirPath = null;
            super.fileFound = false;
        } else {
            super.pathName = this.file.toString();
            super.fileName = this.file.getName();
            super.dirPath = this.file.getParentFile().toString();
            int var6 = super.fileName.indexOf(46);
            if (var6 == -1) {
                super.stemName = super.fileName;
                super.extension = "";
            } else {
                super.stemName = super.fileName.substring(0, var6);
                super.extension = super.fileName.substring(var6);
                this.extn = super.extension;
            }

            try {
                super.input = new BufferedReader(new FileReader(super.pathName));
            } catch (FileNotFoundException var5) {
                System.out.println(var5);
                super.fileFound = false;
            }
        }

        return super.fileName;
    }

    public void setPath(String var1) {
        this.path = var1;
    }

    public String getPath() {
        return this.path;
    }

    public void setExtension(String var1) {
        this.extn = var1;
    }

    public void setAllExtensions() {
        this.extn = null;
    }

    public String getExtension() {
        return this.extn;
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
