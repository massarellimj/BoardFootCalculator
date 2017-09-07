/**
 * 
 */
package main.java.ui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingWorker;
import javax.swing.WindowConstants;

import org.pushingpixels.substance.api.skin.SkinChangeListener;

/**
 * 
 *
 * @author Michael Massarelli
 */
@SuppressWarnings("serial")
public class AppFrame extends JFrame implements SkinChangeListener {
    public static final String TAG = "AltAppFrame";

    public MainPanel mainPanel;
    private InputPanel inputPanel;
    private OutputPanel outputPanel;
    
    public AppFrame(boolean top) {
	super("Price Calculator");
	setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	try {
	    URL logoURL = ClassLoader.getSystemResource("resource/logo.png");
	    if (logoURL != null) setIconImage(ImageIO.read(logoURL));
	} catch (Exception e) {
	    e.printStackTrace();
	}
	init();
	pack();
	setResizable(false);
	setAlwaysOnTop(top);
	setLocationRelativeTo(null);
	setVisible(true);
    }

    /**
     * Initialize
     */
    private void init() {
	getContentPane().setLayout(new FlowLayout(FlowLayout.LEFT));
	getContentPane().setPreferredSize(new Dimension(465,240));
	mainPanel = new MainPanel();
	inputPanel = new InputPanel();
	outputPanel = new OutputPanel();
	populate();
	addWindowListener(new WindowAdapter() {
	    @Override
	    public void windowClosing(WindowEvent e) {
		ButtonPanel.exit.doClick();
	    }
	});
    }

    /**
     * Populate the content pane
     */
    private void populate() {
	getContentPane().add(mainPanel);
	getContentPane().add(inputPanel);
	getContentPane().add(outputPanel);
	getContentPane().add(new ButtonPanel());
    }



    @SuppressWarnings("unused")
    private JLabel result;

    /* (non-Javadoc)
     * @see org.pushingpixels.substance.api.skin.SkinChangeListener#skinChanged()
     */
    @Override
    public void skinChanged() {
	new SwingWorker<Object, Object>() {
	    @Override
	    protected Object doInBackground() throws Exception {
		return null;
	    }
	};
    }




}
