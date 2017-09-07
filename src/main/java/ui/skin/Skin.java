/**
 * 
 */
package main.java.ui.skin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.SwingUtilities;

import org.pushingpixels.substance.api.skin.SubstanceGraphiteLookAndFeel;

import main.java.Main;

/**
 * This class represents a skin option in the settings popup menu
 *
 * @author Michael Massarelli
 */
@SuppressWarnings("serial")
public class Skin extends JRadioButtonMenuItem {
    public static final String TAG = "Skin";

    private String name;
    private ButtonGroup group;

    public Skin(String name) {
	super(name);
	this.name = name;
	addActionListener(skinLis);
	group = SettingsMenu.getGroup();
	group.add(this);
    }

    ActionListener skinLis = new ActionListener() {
	@Override
	public void actionPerformed(ActionEvent e) {
	    final StringBuilder sb = new StringBuilder();
	    sb.append("org.pushingpixels.substance.api.skin.");
	    sb.append(name.replaceAll(" ", ""));
	    sb.append("Skin");
	    SwingUtilities.invokeLater(new Runnable() {
		@Override
		public void run() {
		    SubstanceGraphiteLookAndFeel.setSkin(sb.toString());
		    Main.af.revalidate();
		    Main.af.repaint();
		    Main.af.mainPanel.refresh();
		}
	    });
	}
    };
}
