//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package flanagan.io;

import java.awt.Component;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class MultipleFilesChooser {
    private String[] fileNames = null;
    private String[] pathNames = null;
    private String[] dirNames = null;
    private String[] stemNames = null;
    private FileInput[] fileObjects = null;
    private int nFiles = 0;
    private String path = null;
    private String extn = null;

    public MultipleFilesChooser() {
        this.path = System.getProperty("user.dir");
    }

    public MultipleFilesChooser(String var1) {
        this.path = var1;
    }

    public FileInput[] selectFiles() {
        return this.selectFiles("Select File");
    }

    public FileInput[] selectFiles(String var1) {
        JFileChooser var2 = new JFileChooser(this.path);
        var2.setMultiSelectionEnabled(true);
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
        File[] var6 = var2.getSelectedFiles();
        this.nFiles = var6.length;
        this.fileObjects = new FileInput[this.nFiles];
        this.fileNames = new String[this.nFiles];
        this.stemNames = new String[this.nFiles];
        this.pathNames = new String[this.nFiles];
        this.dirNames = new String[this.nFiles];

        for(int var4 = 0; var4 < this.nFiles; ++var4) {
            this.fileNames[var4] = var6[var4].getName();
            this.pathNames[var4] = var6[var4].toString();
            this.dirNames[var4] = var6[var4].getParentFile().toString();
            this.fileObjects[var4] = new FileInput(this.pathNames[var4]);
            int var5 = this.fileNames[var4].indexOf(46);
            if (var5 == -1) {
                this.stemNames[var4] = this.fileNames[var4];
            } else {
                this.stemNames[var4] = this.fileNames[var4].substring(0, var5);
            }
        }

        return this.fileObjects;
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

    public int getNumberOfFiles() {
        return this.nFiles;
    }

    public String[] getFileNames() {
        return this.fileNames;
    }

    public String[] getStemNames() {
        return this.stemNames;
    }

    public String[] getPathNames() {
        return this.pathNames;
    }

    public String[] getDirPaths() {
        return this.dirNames;
    }

    public final synchronized void close() {
        for(int var1 = 0; var1 < this.nFiles; ++var1) {
            this.fileObjects[var1].close();
        }

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
