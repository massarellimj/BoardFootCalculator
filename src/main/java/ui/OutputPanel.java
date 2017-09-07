/**
 * 
 */
package main.java.ui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontFormatException;
import java.io.File;
import java.io.IOException;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 * Panel containing the resulting price from the calculation
 *
 * @author Michael Massarelli
 */
@SuppressWarnings("serial")
public class OutputPanel extends JPanel {
    public static final String TAG = "OutputPanel";
    
    private static JLabel result, value, e;
    private Font font;
    
    public OutputPanel() {
	super(new FlowLayout(FlowLayout.CENTER));
	setPreferredSize(new Dimension(150, 130));
	font = getFont().deriveFont(14f);
	try {
	    File fontFile = new File(ClassLoader.getSystemResource("resource/aller.ttf").getPath());
	    if (fontFile != null) font = Font.createFont(Font.TRUETYPE_FONT, fontFile).deriveFont(14f);
	} catch (FontFormatException | IOException e) {
	    e.printStackTrace();
	}
	init();
    }

    /**
     * Initialize
     */
    private void init() {
	result = new JLabel("Linear Yard");
	result.setPreferredSize(new Dimension(140,20));
	result.setHorizontalAlignment(SwingConstants.CENTER);
	value = new JLabel("");
	result.setFont(font);
	value.setFont(font);
	value.setPreferredSize(new Dimension(100,50));
	value.setHorizontalAlignment(SwingConstants.CENTER);
	JLabel pp = new JLabel("Price Per");
	pp.setFont(font);
	pp.setPreferredSize(new Dimension(140,20));
	pp.setHorizontalTextPosition(SwingConstants.LEFT);
	e = new JLabel("");
	e.setFont(font.deriveFont(18f));
	e.setPreferredSize(new Dimension(20,50));
	e.setHorizontalTextPosition(SwingConstants.LEFT);
	add(pp);
	add(result);
	add(e);
	add(value);
    }
    
    /**
     * Sets the new price
     * @param val The price string
     */
    public static void setValue(String val) {
	if (val != "") {
	    value.setText("$ " + val);
	    e.setText("=");
	}
    }
    
    /**
     * @return The calculated price
     */
    public static String getValue() {
	if (value.getText() == "") return "";
	else return value.getText().trim().substring(value.getText().indexOf('$') + 1);
    }

    /**
     * Applies the unit based on user selection
     */
    public static void setUnit() {
	switch (MainPanel.cTo.getSelectedIndex()) {
	case 0:
	    result.setText("Linear Foot");
	    break;
	case 1:
	    result.setText("Linear Yard");
	    break;
	case 2:
	    result.setText("Linear Meter");
	    break;
	case 3:
	    result.setText("MSI");
	    break;
	case 4:
	    result.setText("Square Foot");
	    break;
	case 5:
	    result.setText("Square Yard");
	    break;
	case 6:
	    result.setText("Square Meter");
	    break;
	case 7:
	    result.setText("Board Foot");
	    break;
	case 8:
	    result.setText("Sheet");
	    break;
	case 9:
	    result.setText("Roll");
	    break;
	}
    }
}
