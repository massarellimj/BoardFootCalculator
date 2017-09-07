package main.java;

import java.util.List;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import org.pushingpixels.substance.api.skin.SubstanceGraphiteLookAndFeel;

import main.java.ui.AppFrame;
import main.java.util.FileUtils;

public class Main {

    public static AppFrame af;
    public static boolean onTop;
    public static List<String> vals;

    public static void main(String[] args) {
	JFrame.setDefaultLookAndFeelDecorated(true);
	JDialog.setDefaultLookAndFeelDecorated(true);
	final List<String> vals = FileUtils.initialize();
	final String skinName = vals.get(1);
	onTop = vals.get(0).startsWith("t") ? true : false;
	SwingUtilities.invokeLater(new Runnable() {
	    @Override
	    public void run() {
		if (vals != null) {
		    SubstanceGraphiteLookAndFeel.setSkin("org.pushingpixels.substance.api.skin." + skinName + "Skin");
		    af = new AppFrame(onTop);
		}
	    }
	});
    }
}
