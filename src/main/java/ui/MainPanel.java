/**
 * 
 */
package main.java.ui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.MatteBorder;

import org.pushingpixels.substance.api.DecorationAreaType;
import org.pushingpixels.substance.api.SubstanceLookAndFeel;

import main.java.util.Units;

/**
 * Upper most panel containing the comboboxes and the settings button
 *
 * @author Michael Massarelli
 */
@SuppressWarnings("serial")
public class MainPanel extends JPanel {
    public static final String TAG = "MainPanel";
    
    private JLabel from, to;
    public static JComboBox<String> cFrom, cTo;
    public static SettingsLabel set;
    
    public MainPanel()  {
	super(new FlowLayout(FlowLayout.LEFT));
	((FlowLayout)getLayout()).setVgap(0);
	((FlowLayout)getLayout()).setHgap(0);
	setBorder(new MatteBorder(0,0,1,0,SubstanceLookAndFeel.getCurrentSkin().getActiveColorScheme(DecorationAreaType.GENERAL).getForegroundColor()));
	setPreferredSize(new Dimension(455,60));
	init();
    }

    /**
     * Initialize
     */
    private void init() {
	JPanel l = new JPanel(new GridLayout(2,2,70,1));
	l.setPreferredSize(new Dimension(295,50));
	set = new SettingsLabel();
	Font font = getFont().deriveFont(12f);
	
	from = new JLabel("Convert From:");
	from.setLabelFor(cFrom);
	from.setPreferredSize(new Dimension(100,18));
	to = new JLabel("Convert To:");
	to.setLabelFor(cTo);
	to.setPreferredSize(new Dimension(100,18));
	
	cFrom = new JComboBox<String>(Units.units);
	cFrom.setPreferredSize(new Dimension(100, 22));
	cFrom.setFont(font);
	cFrom.setSelectedIndex(4);
	cFrom.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		check();
		InputPanel.setUnit();
	    }
	});

	cTo = new JComboBox<String>(Units.units);	
	cTo.setPreferredSize(new Dimension(100, 22));
	cTo.setFont(font);
	cTo.setSelectedIndex(1);
	cTo.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		check();
		OutputPanel.setUnit();
		OutputPanel.setValue("");
		ButtonPanel.deinitCopy();
	    }
	});
	JLabel space = new JLabel();
	space.setPreferredSize(new Dimension(130,50));
	
	l.add(from);
	l.add(to);
	l.add(cFrom);
	l.add(cTo);
	add(l);
	add(space);
	add(set);
    }
    
    /**
     * Checks the user unit selections and toggles the text fields as appropriate
     */
    static void check() {
	if (cFrom.getSelectedIndex() != -1 && cTo.getSelectedIndex() != -1) {
	    if (cFrom.getSelectedIndex() <= 2) {
		if (cTo.getSelectedIndex() <= 2) {
		    InputPanel.toggle(false, false, false);
		} else if (cTo.getSelectedIndex() > 2 && cTo.getSelectedIndex() <= 6) {
		    InputPanel.toggle(true, false, false);
		} else if (cTo.getSelectedIndex() == 7) {
		    InputPanel.toggle(true, true, false);
		}
		else {
		    InputPanel.toggle(true, false, true);
		}
	    } else if (cFrom.getSelectedIndex() <= 6) {
		if (cTo.getSelectedIndex() <= 2) {
		    InputPanel.toggle(true, false, false);
		} else if (cTo.getSelectedIndex() > 2 && cTo.getSelectedIndex() <= 6) {
		    InputPanel.toggle(false, false, false);
		} else if (cTo.getSelectedIndex() == 7) {
		    InputPanel.toggle(false, true, false);
		} else {
		    InputPanel.toggle(true, false, true);
		}
	    } else {
		if (cTo.getSelectedIndex() <= 2) {
		    InputPanel.toggle(true, true, false);
		} else if (cTo.getSelectedIndex() <= 6) {
		    InputPanel.toggle(true, false, true);
		} else if (cTo.getSelectedIndex() == 7) {
		    InputPanel.toggle(true, true, true);
		} else {
		    InputPanel.toggle(false, false, false);
		}
	    }
	}
    }
    /**
     * Changes the border color to accommodate the new skin 
     */
    public void refresh() {
	setBorder(new MatteBorder(0,0,1,0,SubstanceLookAndFeel.getCurrentSkin().getActiveColorScheme(DecorationAreaType.PRIMARY_TITLE_PANE).getForegroundColor()));
	revalidate();
	repaint();
    }
}
