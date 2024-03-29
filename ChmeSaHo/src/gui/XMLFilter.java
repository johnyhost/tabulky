package gui;

import java.io.File;
import javax.swing.filechooser.*;
// tato trieda zabezpecuje filtrovanie suborov pri nacitani, zobrazuju sa teda len XML subory
public class XMLFilter extends FileFilter {
    public boolean accept(File f) {
        if (f.isDirectory()) {
            return true;
        }

        String extension = getExtension(f);
        if (extension != null) {
            if (extension.equalsIgnoreCase("xml")) {
                    return true;
            } else {
                return false;
            }
        }

        return false;
    }
    public static String getExtension(File f) {
        String ext = null;
        String s = f.getName();
        int i = s.lastIndexOf('.');

        if (i > 0 &&  i < s.length() - 1) {
            ext = s.substring(i+1).toLowerCase();
        }
        return ext;
    }
    public String getDescription() {
        return "XML Subory";
    }
}