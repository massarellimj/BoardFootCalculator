/**
 * 
 */
package main.java.ui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.text.NumberFormatter;

import main.java.util.Units;

/**
 * Panel containing the necessary components for user input
 *
 * @author Michael Massarelli
 */
@SuppressWarnings("serial")
public class InputPanel extends JPanel {
    public static final String TAG = "InputPanel";


    public static JFormattedTextField width, thickness, length, price;
    private static JLabel wd, th, ln, pr, uPrice;
    public static JComboBox<String> widthUnit;
    public static JComboBox<String> thicknessUnit;
    public static JComboBox<String> lengthUnit;


    public static double priced;


    public InputPanel() {
	super(new FlowLayout(FlowLayout.LEFT));
	setPreferredSize(new Dimension(300,130));
	init();
    }

    /**
     * Initialize
     */
    private void init() {
	Font font = getFont().deriveFont(12f);
	Dimension d = new Dimension(70,22);
	NumberFormatter form = new NumberFormatter();
	
	width = new JFormattedTextField(form);
	width.setPreferredSize(new Dimension(60, 25));
	width.setHorizontalAlignment(SwingConstants.RIGHT);
	width.setFont(getFont().deriveFont(14f));

	thickness = new JFormattedTextField(form);
	thickness.setPreferredSize(new Dimension(60, 25));
	thickness.setHorizontalAlignment(SwingConstants.RIGHT);
	thickness.setFont(getFont().deriveFont(14f));

	length = new JFormattedTextField(form);
	length.setPreferredSize(new Dimension(60, 25));
	length.setHorizontalAlignment(SwingConstants.RIGHT);
	length.setFont(getFont().deriveFont(14f));

	price = new JFormattedTextField();
	price.setPreferredSize(new Dimension(60, 25));
	price.setHorizontalAlignment(SwingConstants.RIGHT);
	price.setFont(getFont().deriveFont(14f));
	price.addFocusListener(new FocusListener() {
	    @Override
	    public void focusGained(FocusEvent e) {
		if (price.getText().contains("$")) {
		    price.setText(price.getText().replace('$', ' '));
		}
	    }
	    @Override
	    public void focusLost(FocusEvent e) {
		if (!price.getText().isEmpty() && !price.getText().contains("$")) {
		    priced = Double.parseDouble(price.getText().trim());
		    price.setText("$" + Double.parseDouble(price.getText().trim()));
		}
	    }
	});

	wd = new JLabel("Material Width");
	wd.setLabelFor(width);
	wd.setFont(font);
	wd.setPreferredSize(new Dimension(110,22));
	th = new JLabel("Material Thickness");
	th.setLabelFor(thickness);
	th.setFont(font);
	th.setPreferredSize(new Dimension(110,22));
	ln = new JLabel("Material Length");
	ln.setLabelFor(length);
	ln.setFont(font);
	ln.setPreferredSize(new Dimension(110,22));
	pr = new JLabel("Provided Price");
	pr.setLabelFor(price);
	pr.setFont(font);
	pr.setPreferredSize(new Dimension(110,22));

	widthUnit = new JComboBox<String>(Units.unitsWidth);
	widthUnit.setPreferredSize(d);
	widthUnit.setSelectedIndex(0);
	thicknessUnit = new JComboBox<String>(Units.unitsThickness);
	thicknessUnit.setPreferredSize(d);
	thicknessUnit.setSelectedIndex(2);
	lengthUnit = new JComboBox<String>(Units.unitsLength);
	lengthUnit.setPreferredSize(d);
	lengthUnit.setSelectedIndex(2);
	uPrice = new JLabel();
	uPrice.setFont(font);
	setUnit();

	add(pr);
	add(price);
	add(uPrice);
	add(wd);
	add(width);
	add(widthUnit);
	add(th);
	add(thickness);
	add(thicknessUnit);
	add(ln);
	add(length);
	add(lengthUnit);
	toggle(true, false, false);
    }
    
    /**
     * Sets the price unit depending on the selection of first combobox
     */
    public static void setUnit() {
	    switch (MainPanel.cFrom.getSelectedIndex()) {
		case 0:
		    uPrice.setText("Per Linear Foot");
		    break;
		case 1:
		    uPrice.setText("Per Linear Yard");
		    break;
		case 2:
		    uPrice.setText("Per Linear Meter");
		    break;
		case 3:
		    uPrice.setText("Per MSI");
		    break;
		case 4:
		    uPrice.setText("Per Square Foot");
		    break;
		case 5:
		    uPrice.setText("Per Square Yard");
		    break;
		case 6:
		    uPrice.setText("Per Square Meter");
		    break;
		case 7:
		    uPrice.setText("Per Board Foot");
		    break;
		case 8:
		    uPrice.setText("Per Sheet");
		    break;
		case 9:
		    uPrice.setText("Per Roll");
		    break;
		}
	}

    
    /**
     * Toggles the width, thickness, and length text fields
     * 
     * @param w Width text field
     * @param t Thickness text field
     * @param l Length text field
     */
    static void toggle(boolean w, boolean t, boolean l) {
	length.setEnabled(l); 
	ln.setEnabled(l);
	lengthUnit.setEnabled(l);
	thickness.setEnabled(t);
	th.setEnabled(t);
	thicknessUnit.setEnabled(t);
	width.setEnabled(w);
	wd.setEnabled(w);
	widthUnit.setEnabled(w);
    }
    
}
