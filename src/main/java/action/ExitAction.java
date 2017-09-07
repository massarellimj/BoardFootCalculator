/**
 * 
 */
package main.java.action;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import org.pushingpixels.substance.api.skin.SubstanceGraphiteLookAndFeel;

import main.java.Main;
import main.java.util.FileUtils;

/**
 * 
 *
 * @author Michael Massarelli
 */
@SuppressWarnings("serial")
public class ExitAction extends AbstractAction {
    public static final String TAG = "ExitAction";
    
    /**
     * Exit application action
     */
    public ExitAction() {
	super("Exit");
    }

    /* (non-Javadoc)
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    @Override
    public void actionPerformed(ActionEvent e) {
	String top = Main.af.isAlwaysOnTop() == true ? "true" : "false";
	String skin = SubstanceGraphiteLookAndFeel.getCurrentSkin().getDisplayName().replaceAll(" ", "");
	FileUtils.saveSettings(top, skin);
	Main.af.dispose();
    }
    
}
