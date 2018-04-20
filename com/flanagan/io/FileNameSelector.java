//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package flanagan.io;

import java.awt.Component;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class FileNameSelector {
    private File file;
    private String path = null;
    private String extn = null;
    private String fileName = null;
    private String stemName = null;
    private String pathName = null;
    private String dirPath = null;
    private boolean fileFound = false;

    public FileNameSelector() {
    }

    public FileNameSelector(String var1) {
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
            this.fileName = null;
            this.stemName = null;
            this.pathName = null;
            this.dirPath = null;
            this.fileFound = false;
        } else {
            this.pathName = this.file.toString();
            this.fileName = this.file.getName();
            this.dirPath = this.file.getParentFile().toString();
            int var4 = this.fileName.indexOf(46);
            if (var4 == -1) {
                this.stemName = this.fileName;
            } else {
                this.stemName = this.fileName.substring(0, var4);
            }
        }

        return this.fileName;
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

    public boolean fileFound() {
        return this.fileFound;
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
