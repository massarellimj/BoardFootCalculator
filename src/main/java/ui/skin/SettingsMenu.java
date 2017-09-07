/**
 * 
 */
package main.java.ui.skin;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenu;
import javax.swing.JPopupMenu;

import org.pushingpixels.substance.api.skin.SubstanceGraphiteLookAndFeel;

import main.java.Main;
import main.java.ui.SettingsLabel;
import main.java.util.FileUtils;

/**
 * 
 * A {@link}javax.swing.JPopupMenu holding options for keeping window on top and choosing the skin
 * @author Michael Massarelli
 */
@SuppressWarnings({"serial","unused"})
public class SettingsMenu extends JPopupMenu {
    public static final String TAG = "SettingsMenu";
    
    /**
     * ButtonGroup holds all skin options
     */
    private static ButtonGroup group;
    private String[] skinList = {"Autumn","Business","Business Black Steel","Business Blue Steel",
	    "Cerulean","Creme","Dust","Dust Coffee","Gemini","Graphite","Graphite Aqua","Graphite Chalk",
	    "Graphite Glass","Magellan","Mariner","Mist Aqua","Mist Silver","Moderate","Nebula",
	    "Office Silver 2007", "Office Black 2007","Office Blue 2007","Raven","Sahara", "Twilight"};
    private static JCheckBoxMenuItem top;
    private JMenu skins;
    private boolean onTop;
    
    
    public SettingsMenu(SettingsLabel set, boolean onTop, String selectedSkin) {
	super("Settings");
	this.onTop = onTop;
	setInvoker(set);
	init();
	add(top);
	addSeparator();
	add(skins);
    }


    /**
     * Initialize
     */
    private void init() {
	group = new ButtonGroup();
	top = new JCheckBoxMenuItem("Keep On Top");
	if (Main.onTop) {
	    top.setSelected(true);
	} else {
	    top.setSelected(false);
	}
	top.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		Main.af.setAlwaysOnTop(top.isSelected());
	    }
	});
	skins = new JMenu("Skin");	
	
	for (String s : skinList) {
	    skins.add(new Skin(s));
	}

	String curr = SubstanceGraphiteLookAndFeel.getCurrentSkin().getDisplayName();
	for (Component s : skins.getComponents()) {
	    if (((Skin)s).getText() == curr) {
		((Skin)s).setSelected(true);
	    } else {
		((Skin)s).setSelected(false);
	    }
	}
    }
    
    /**
     * Visually selects the skin radio button
     * @param skin The name of the selected skin
     */
    public void select(String skin) {
	for (Skin s : ((Skin[])((JMenu)getComponent(2)).getComponents())) {
	    if (skin == s.getText()) {
		s.setSelected(true);
		getGroup().setSelected(s.getModel(), true);
	    }
	}
    }


    /**
     * @return Skin's button group
     */
    public static ButtonGroup getGroup() {
	return group;
    }


    /**
     * @return is 'keep-on-top' selected
     */
    public static boolean isAlwaysOnTop() {
	return top.isSelected();
    }
    
       
       
}
