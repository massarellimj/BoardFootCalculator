/**
 * 
 */
package main.java.ui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JPanel;

import main.java.action.CalculateAction;
import main.java.action.CopyTextAction;
import main.java.action.ExitAction;

/**
 * The lower panel containing the main application buttons
 *
 * @author Michael Massarelli
 */
@SuppressWarnings("serial")
public class ButtonPanel extends JPanel {
    public static final String TAG = "ButtonPanel";
    
    private JButton calculate;

    private static JButton copyTxt;

    public static JButton exit;
    
    public ButtonPanel() {
	super(new FlowLayout(FlowLayout.CENTER));
	((FlowLayout)getLayout()).setHgap(5);
	((FlowLayout)getLayout()).setVgap(5);
	init();
    }
    
    
    /**
     * Initialize
     */
    private void init() {
	Font font = getFont().deriveFont(12f);
	
	calculate = new JButton(new CalculateAction());
	calculate.setFont(font);
	calculate.setToolTipText("Calculate Pricing");
	calculate.setPreferredSize(new Dimension(270, 25));
	
	copyTxt = new JButton("Copy Text");
	copyTxt.setFont(font);
	copyTxt.setToolTipText("Copy to clipboard");
	copyTxt.setPreferredSize(new Dimension(90, 25));
	copyTxt.setEnabled(false);
	copyTxt.setAction(new CopyTextAction());
	
	exit = new JButton(new ExitAction());
	exit.setFont(font);
	exit.setToolTipText("Exit");
	exit.setPreferredSize(new Dimension(75, 25));
	exit.setOpaque(true);

	deinitCopy();
	
	add(calculate);
	add(copyTxt);
	add(exit);
    }


    /**
     * Enable the 'Copy Text' button
     */
    public static void initCopy() {
	copyTxt.setToolTipText("Copy to clipboard");
	copyTxt.setEnabled(true);
    }
    
    /**
     * Disable the 'Copy Text' button
     */
    public static void deinitCopy() {
	copyTxt.setEnabled(false);
    }
}
