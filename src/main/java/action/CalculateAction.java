/**
 * 
 */
package main.java.action;

import java.awt.event.ActionEvent;
import java.text.DecimalFormat;

import javax.swing.AbstractAction;
import javax.swing.JFormattedTextField;

import main.java.ui.ButtonPanel;
import main.java.ui.InputPanel;
import main.java.ui.MainPanel;
import main.java.ui.OutputPanel;

/**
 * 
 *
 * @author Michael Massarelli
 */
@SuppressWarnings("serial")
public class CalculateAction extends AbstractAction {
    public static final String TAG = "CalculateAction";

    private double price, width, thick, length;


    /**
     * Calculate action
     */
    public CalculateAction() {
	super("Calculate");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
	if (validInputs()) {
	    calculate();
	} else {
	    System.out.println("Not Valid");
	}
    }

    /**
     * Calculates the pricing
     */
    private void calculate() {
	DecimalFormat form = new DecimalFormat("#.###");
	price = InputPanel.priced;
	String s = InputPanel.width.getText();
	if (InputPanel.width.isEnabled()) {
	    if (InputPanel.width.getText().contains(",")) {
		s = InputPanel.width.getText().trim().replace(",", "");
	    }
	    switch (InputPanel.widthUnit.getSelectedIndex()) {
	    // Inches
	    case 0:
		width = Double.parseDouble(s);
		break;
	    case 1:
		width = Double.parseDouble(s) / 25.4;
		break;
	    case 2:
		width = Double.parseDouble(s) * 12;
		break;
	    case 3:
		width = Double.parseDouble(s) * 36;
		break;
	    case 4: 
		width = Double.parseDouble(s) * 39.36996;
		break;
	    }
	}
	if (InputPanel.thickness.isEnabled()) {
	    switch (InputPanel.thicknessUnit.getSelectedIndex()) {
	    case 0:
		thick = Double.parseDouble(InputPanel.thickness.getText()) / 1000;
		break;
	    case 1:
		thick = Double.parseDouble(InputPanel.thickness.getText()) / 25.4;
		break;
	    default:
		thick = Double.parseDouble(InputPanel.thickness.getText());
		break;
	    }
	}
	if (InputPanel.length.isEnabled()) {
	    switch (InputPanel.lengthUnit.getSelectedIndex()) {
	    // Inches
	    case 0:
		length = Double.parseDouble(InputPanel.length.getText());
		break;
	    case 1:
		length = Double.parseDouble(InputPanel.length.getText()) / 25.4;
		break;
	    case 2:
		length = Double.parseDouble(InputPanel.length.getText()) * 12;
		break;
	    case 3:
		length = Double.parseDouble(InputPanel.length.getText()) * 36;
		break;
	    case 4: 
		length = Double.parseDouble(InputPanel.length.getText()) * 39.36996;
		break;
	    }
	}
	switch (MainPanel.cFrom.getSelectedIndex()) {
	// FROM - Linear Feet
	case 0:
	    switch (MainPanel.cTo.getSelectedIndex()) {
	    // TO - Linear Feet
	    case 0:
		OutputPanel.setValue(form.format(price));
		break;
		// TO - Linear Yards
	    case 1:
		OutputPanel.setValue(form.format(price * 3));
		break;
	    case 2:
		OutputPanel.setValue(form.format(price * 3.2808));
		break;
	    case 3:
		OutputPanel.setValue(form.format(price / (width*12/1000)));
		break;
	    case 4:
		OutputPanel.setValue(form.format(price / (width/12)));
		break;
	    case 5:
		OutputPanel.setValue(form.format((price * 3) / (width/36)));
		break;
	    case 6:
		OutputPanel.setValue(form.format((price * 3.2808) / (width/39.36996)));
		break;
	    case 7:
		OutputPanel.setValue(form.format(price / (12 * width * thick) * 144));
		break;
	    case 8:
		OutputPanel.setValue(form.format(price * (length / 12)));
		break;
	    case 9:
		OutputPanel.setValue(form.format(price * (length / 12)));
		break;
	    }
	    break;
	case 1:
	    switch (MainPanel.cTo.getSelectedIndex()) {
	    case 0:
		OutputPanel.setValue(form.format(price / 3));
		break;
	    case 1:
		OutputPanel.setValue(form.format(price));
		break;
	    case 2:
		OutputPanel.setValue(form.format(price * 1.09361));
		break;
	    case 3:
		OutputPanel.setValue(form.format(price / (width  *36 / 1000)));
		break;
	    case 4:
		OutputPanel.setValue(form.format(price / (width / 12 * 3)));
		break;
	    case 5:
		OutputPanel.setValue(form.format(price / (width / 36)));
		break;
	    case 6:
		OutputPanel.setValue(form.format((price * 1.09361) / (width / 39.36996)));
		break;
	    case 7:
		OutputPanel.setValue(form.format(price / (36 * width * thick) * 144));
		break;
	    case 8:
		OutputPanel.setValue(form.format(price * (length / 36)));
		break;
	    case 9:
		OutputPanel.setValue(form.format(price * (length / 36)));
		break;
	    }
	    break;
	    // FROM - Linear Meter
	case 2:
	    switch (MainPanel.cTo.getSelectedIndex()) {
	    case 0:
		OutputPanel.setValue(form.format(price * 0.3048));
		break;
	    case 1:
		OutputPanel.setValue(form.format(price * 0.9144));
		break;
	    case 2:
		OutputPanel.setValue(form.format(price));
		break;
	    case 3:
		OutputPanel.setValue(form.format(price / (width * 39.36996 / 1000)));
		break;
	    case 4:
		OutputPanel.setValue(form.format(price / (width / 12 * 3.2808)));
		break;
	    case 5:
		OutputPanel.setValue(form.format(price / (width / 36 * 1.09361)));
		break;
	    case 6:
		OutputPanel.setValue(form.format(price / (width / 39.36996)));
		break;
	    case 7:
		OutputPanel.setValue(form.format(price / (39.36996 * width * thick) * 144));
		break;
	    case 8:
		OutputPanel.setValue(form.format(price * (length / 39.36996)));
		break;
	    case 9:
		OutputPanel.setValue(form.format(price * (length / 39.36996)));
		break;
	    }
	    break;
	    // FROM - MSI
	case 3:
	    switch (MainPanel.cTo.getSelectedIndex()) {
	    case 0:
		OutputPanel.setValue(form.format(price * (width * 12) / 1000));
		break;
	    case 1:
		OutputPanel.setValue(form.format(price * (width * 36) / 1000));
		break;
	    case 2:
		OutputPanel.setValue(form.format(price * (width * 39.36996) / 1000));
		break;
	    case 3:
		OutputPanel.setValue(form.format(price));
		break;
	    case 4:
		OutputPanel.setValue(form.format(price * 0.144));
		break;
	    case 5:
		OutputPanel.setValue(form.format(price * 1.296));
		break;
	    case 6:
		OutputPanel.setValue(form.format(price * 1.550));
		break;
	    case 7:
		OutputPanel.setValue(form.format(price / ((1000 * thick) / 144)));
		break;
	    case 8:
		OutputPanel.setValue(form.format(price * (width * length / 1000)));
		break;
	    case 9:
		OutputPanel.setValue(form.format(price * (width * length / 1000)));
		break;
	    }
	    break;
	    // FROM - Square Feet
	case 4:
	    switch (MainPanel.cTo.getSelectedIndex()) {
	    case 0:
		OutputPanel.setValue(form.format(price * (width / 12)));
		break;
	    case 1:
		OutputPanel.setValue(form.format(price * (width / 4)));
		break;
	    case 2:
		OutputPanel.setValue(form.format(price * (width / 3.65764)));
		break;
	    case 3:
		OutputPanel.setValue(form.format(price / 0.144));
		break;
	    case 4:
		OutputPanel.setValue(form.format(price));
		break;
	    case 5:
		OutputPanel.setValue(form.format(price / 9));
		break;
	    case 6:
		OutputPanel.setValue(form.format(price / 10.763648));
		break;
	    case 7:
		OutputPanel.setValue(form.format(price / ((144 * thick) / 144)));
		break;
	    case 8:
		OutputPanel.setValue(form.format(price * ((width / 12) * (length / 12))));
		break;
	    case 9:
		OutputPanel.setValue(form.format(price * ((width / 12) * (length / 12))));
		break;
	    }
	    break;
	    // FROM - Square Yards
	case 5:
	    switch (MainPanel.cTo.getSelectedIndex()) {
	    case 0:
		OutputPanel.setValue(form.format(price * (width / 36) / 3));
		break;
	    case 1:
		OutputPanel.setValue(form.format(price * (width / 36)));
		break;
	    case 2:
		OutputPanel.setValue(form.format(price * (width / 36) * 1.09361));
		break;
	    case 3:
		OutputPanel.setValue(form.format(price / 1.296));
		break;
	    case 4:
		OutputPanel.setValue(form.format(price * 9));
		break;
	    case 5:
		OutputPanel.setValue(form.format(price));
		break;
	    case 6:
		OutputPanel.setValue(form.format(price * 0.8361274));
		break;
	    case 7:
		OutputPanel.setValue(form.format(price / ((1296 * thick) / 144)));
		break;
	    case 8:
		OutputPanel.setValue(form.format(price * ((width / 36) * (length / 36))));
		break;
	    case 9:
		OutputPanel.setValue(form.format(price * ((width / 36) * (length / 36))));
		break;
	    }
	    break;
	    // FROM - Square Meters
	case 6:
	    switch (MainPanel.cTo.getSelectedIndex()) {
	    case 0:
		OutputPanel.setValue(form.format(price * (width / 39.36996) / 3.2808));
		break;
	    case 1:
		OutputPanel.setValue(form.format(price * (width / 39.36996) / 1.09361));
		break;
	    case 2:
		OutputPanel.setValue(form.format(price * (width / 39.36996)));
		break;
	    case 3:
		OutputPanel.setValue(form.format(price / 1.55));
		break;
	    case 4:
		OutputPanel.setValue(form.format(price * 10.76391));
		break;
	    case 5:
		OutputPanel.setValue(form.format(price * 1.19599));
		break;
	    case 6:
		OutputPanel.setValue(form.format(price));
		break;
	    case 7:
		OutputPanel.setValue(form.format(price / ((1550 * thick) / 144)));
		break;
	    case 8:
		OutputPanel.setValue(form.format(price * ((width / 39.36996) * (length / 39.36996))));
		break;
	    case 9:
		OutputPanel.setValue(form.format(price * ((width / 39.36996) * (length / 39.36996))));
		break;
	    }
	    break;
	    // FROM - Board Feet
	case 7:
	    switch (MainPanel.cTo.getSelectedIndex()) {
	    case 0:
		OutputPanel.setValue(form.format(price / (144 / thick / width / 12)));
		break;
	    case 1:
		OutputPanel.setValue(form.format(price / (144 / thick / width / 36)));
		break;
	    case 2:
		OutputPanel.setValue(form.format(price / (144 / thick / width / 39.36996)));
		break;
	    case 3:
		OutputPanel.setValue(form.format(price / (144 / thick / 1000)));
		break;
	    case 4:
		OutputPanel.setValue(form.format(price / (144 / thick / 144)));
		break;
	    case 5:
		OutputPanel.setValue(form.format(price / (144 / thick / 1296)));
		break;
	    case 6:
		OutputPanel.setValue(form.format(price / (144 / thick / 1550)));
		break;
	    case 7:
		OutputPanel.setValue(form.format(price));
		break;
	    case 8:
		OutputPanel.setValue(form.format(price * ((width * length) / (144 / thick))));
		break;
	    case 9:
		OutputPanel.setValue(form.format(price * ((width * length) / (144 / thick))));
		break;
	    }
	    break;
	    // FROM - Sheets
	case 8:
	    switch (MainPanel.cTo.getSelectedIndex()) {
	    case 0:
		OutputPanel.setValue(form.format(price / (length / 12)));
		break;
	    case 1:
		OutputPanel.setValue(form.format(price / (length / 36)));
		break;
	    case 2:
		OutputPanel.setValue(form.format(price / (length / 39.36996)));
		break;
	    case 3:
		OutputPanel.setValue(form.format(price / (length * width / 1000)));
		break;
	    case 4:
		OutputPanel.setValue(form.format(price / ((length / 12) * (width / 12))));
		break;
	    case 5:
		OutputPanel.setValue(form.format(price / ((length / 36) * (width / 36))));
		break;
	    case 6:
		OutputPanel.setValue(form.format(price / ((length / 39.36996) * (width / 39.36996))));
		break;
	    case 7:
		OutputPanel.setValue(form.format(price / (length * width / (144 / thick))));
		break;
	    case 8:
		OutputPanel.setValue(form.format(price));
		break;
	    case 9:
		OutputPanel.setValue(form.format(price));
		break;
	    }
	    break;
	    // FROM - Rolls
	case 9:
	    switch (MainPanel.cTo.getSelectedIndex()) {
	    case 0:
		OutputPanel.setValue(form.format(price / (length / 12)));
		break;
	    case 1:
		OutputPanel.setValue(form.format(price / (length / 36)));
		break;
	    case 2:
		OutputPanel.setValue(form.format(price / (length / 39.36996)));
		break;
	    case 3:
		OutputPanel.setValue(form.format(price / (length * width / 1000)));
		break;
	    case 4:
		OutputPanel.setValue(form.format(price / ((length / 12) * (width / 12))));
		break;
	    case 5:
		OutputPanel.setValue(form.format(price / ((length / 36) * (width / 36))));
		break;
	    case 6:
		OutputPanel.setValue(form.format(price / ((length / 39.36996) * (width / 39.36996))));
		break;
	    case 7:
		OutputPanel.setValue(form.format(price / (length * width / (144 / thick))));
		break;
	    case 8:
		OutputPanel.setValue(form.format(price));
		break;
	    case 9:
		OutputPanel.setValue(form.format(price));
		break;
	    }
	    break;
	}
	if (OutputPanel.getValue().substring(OutputPanel.getValue().indexOf('.') + 1).length() != 3) {
	    if (!OutputPanel.getValue().contains(".")) {
		OutputPanel.setValue(OutputPanel.getValue() + ".0");
	    }
	    int zeros = 3 - OutputPanel.getValue().substring(OutputPanel.getValue().indexOf('.') + 1).length();
	    for (int i = 0; i < zeros; i++) {
		OutputPanel.setValue(OutputPanel.getValue() + "0");
	    }
	};
	ButtonPanel.initCopy();
    }

    JFormattedTextField[] fields = {InputPanel.price, InputPanel.length, InputPanel.width, InputPanel.thickness};
    /**
     * @return are user inputs valid
     */
    private boolean validInputs() {
	for (JFormattedTextField field : fields) {
	    if (field.isEnabled() && !field.isEditValid()) return false;
	}
	return true;
    }
}
