/**
 * 
 */
package main.java.action;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import main.java.ui.OutputPanel;

/**
 * 
 *
 * @author Michael Massarelli
 */
@SuppressWarnings("serial")
public class CopyTextAction extends AbstractAction {
    public static final String TAG = "CopyTextAction";
    
    /**
     * Copy price to clipboard action
     */
    public CopyTextAction() {
	super("Copy Text");
    }

    /* (non-Javadoc)
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    @Override
    public void actionPerformed(ActionEvent e) {
	Clipboard cb = Toolkit.getDefaultToolkit().getSystemClipboard();
	StringSelection ss = new StringSelection(OutputPanel.getValue());
	cb.setContents(ss, ss);
    }
}
