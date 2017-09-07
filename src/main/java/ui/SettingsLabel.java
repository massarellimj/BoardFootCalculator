/**
 * 
 */
package main.java.ui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;

import javax.swing.JButton;
import javax.swing.SwingConstants;

import org.pushingpixels.substance.api.skin.SubstanceGraphiteLookAndFeel;

import main.java.Main;
import main.java.ui.skin.SettingsMenu;

/**
 * The custom-shaped settings button
 *
 * @author Michael Massarelli
 */
@SuppressWarnings("serial")
public class SettingsLabel extends JButton {
    public static final String TAG = "SettingsLabel";

    private Shape poly = createPoly();
    private SettingsMenu menu;

    public SettingsLabel() {
	super();
	setFocusPainted(false);
	setHorizontalTextPosition(SwingConstants.CENTER);
	setVerticalTextPosition(SwingConstants.TOP);
	setPreferredSize(new Dimension(24,24));
	setHorizontalAlignment(SwingConstants.RIGHT);
	setHorizontalTextPosition(SwingConstants.RIGHT);
	setToolTipText("Settings");
	init();
	addActionListener(actLis);
    }

    /**
     * @return The circle shape
     */
    private Shape createPoly() {
	Ellipse2D e = new Ellipse2D.Double(0, 0, 12, 12);
	return e;
    }
    public void paintBorder( Graphics g ) {
	((Graphics2D)g).draw(poly);
    }
    public void paintComponent( Graphics g ) {
	((Graphics2D)g).fill(poly);
    }
    public Dimension gPreferredSize() {
	return new Dimension(24,24);
    }
    public boolean contains(int x, int y) {
	return poly.contains(x, y);
    }
    /**
     * Initialize
     */
    private void init() {
	menu = new SettingsMenu(this, Main.onTop, SubstanceGraphiteLookAndFeel.getCurrentSkin().getDisplayName());
	setComponentPopupMenu(menu);
    }

    /**
     * ActionListener used to display the settings popup menu
     */
    ActionListener actLis = new ActionListener() {
	@Override
	public void actionPerformed(ActionEvent e) {
	    menu.setLocation(Main.af.getLocation().x + getLocation().x + 5, Main.af.getLocation().y + getLocation().y + 45);
	    menu.setEnabled(true);
	    menu.setVisible(true);
	}	
    };

}
