//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package flanagan.io;

import java.io.File;
import java.util.Enumeration;
import java.util.Hashtable;
import javax.swing.filechooser.FileFilter;

public class FileTypeFilter extends FileFilter {
    private static String TYPE_UNKNOWN = "Type Unknown";
    private static String HIDDEN_FILE = "Hidden File";
    private Hashtable<String, FileTypeFilter> filters;
    private String description;
    private String fullDescription;
    private boolean useExtensionsInDescription;

    public FileTypeFilter() {
        this.filters = null;
        this.description = null;
        this.fullDescription = null;
        this.useExtensionsInDescription = true;
        this.filters = new Hashtable();
    }

    public FileTypeFilter(String var1) {
        this((String)var1, (String)null);
    }

    public FileTypeFilter(String var1, String var2) {
        this();
        if (var1 != null) {
            this.addExtension(var1);
        }

        if (var2 != null) {
            this.setDescription(var2);
        }

    }

    public FileTypeFilter(String[] var1) {
        this((String[])var1, (String)null);
    }

    public FileTypeFilter(String[] var1, String var2) {
        this();

        for(int var3 = 0; var3 < var1.length; ++var3) {
            this.addExtension(var1[var3]);
        }

        if (var2 != null) {
            this.setDescription(var2);
        }

    }

    public boolean accept(File var1) {
        if (var1 != null) {
            if (var1.isDirectory()) {
                return true;
            }

            String var2 = this.getExtension(var1);
            if (var2 != null && this.filters.get(this.getExtension(var1)) != null) {
                return true;
            }
        }

        return false;
    }

    public String getExtension(File var1) {
        if (var1 != null) {
            String var2 = var1.getName();
            int var3 = var2.lastIndexOf(46);
            if (var3 > 0 && var3 < var2.length() - 1) {
                return var2.substring(var3 + 1).toLowerCase();
            }
        }

        return null;
    }

    public void addExtension(String var1) {
        if (this.filters == null) {
            this.filters = new Hashtable(5);
        }

        this.filters.put(var1.toLowerCase(), this);
        this.fullDescription = null;
    }

    public String getDescription() {
        if (this.fullDescription == null) {
            if (this.description != null && !this.isExtensionListInDescription()) {
                this.fullDescription = this.description;
            } else {
                this.fullDescription = this.description == null ? "(" : this.description + " (";
                Enumeration var1 = this.filters.keys();
                if (var1 != null) {
                    for(this.fullDescription = this.fullDescription + "." + (String)var1.nextElement(); var1.hasMoreElements(); this.fullDescription = this.fullDescription + ", ." + (String)var1.nextElement()) {
                        ;
                    }
                }

                this.fullDescription = this.fullDescription + ")";
            }
        }

        return this.fullDescription;
    }

    public void setDescription(String var1) {
        this.description = var1;
        this.fullDescription = null;
    }

    public void setExtensionListInDescription(boolean var1) {
        this.useExtensionsInDescription = var1;
        this.fullDescription = null;
    }

    public boolean isExtensionListInDescription() {
        return this.useExtensionsInDescription;
    }
}
