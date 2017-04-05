package ui;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JWindow;
import javax.swing.LookAndFeel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;

import net.miginfocom.swing.MigLayout;

@SuppressWarnings("serial")
public class AppFrame extends JFrame implements ComponentListener {

	private Image FAVICON;
	private final String VERSION = "0.1.0.1";
	private final String AUTHOR = "Michael Massarelli";
	private final String COMPANY = "United Gasket Corporation";
	private final String DESCRIPTION = "This tool allows price conversion \nfrom $/board foot(BF) to $/linear feet or &/linear yards.\n";
	private final String COPYRIGHT = "GNU Public License (GPL)";
	private MigLayout layout = new MigLayout();

	private JComboBox<String> toUnits;
	private final String[] units = { "Linear Feet", "Linear Yards" };
	private JLabel banner, pLabel, tLabel, wLabel, uLabel;
	private JFormattedTextField thickness, bfPrice, width;
	private final Dimension min = new Dimension(60, 20);
	private JButton calc;
	private boolean lft = false;
	private JWindow out = null;
	private JMenuBar menuBar;
	private JMenu options, toggle, help, laf;
	private JCheckBoxMenuItem onTop;
	private JMenuItem about, basic, metal, nimbus, synth;
	private int currLAF = 0;

	public AppFrame() {
		super("Board Foot Price Calculator");
		try {
			FAVICON = ImageIO.read(AppFrame.class.getResource("logo.png"));
		} catch (IOException e) {
			FAVICON = null;
			e.printStackTrace();
		}
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setIconImage(FAVICON);
		getContentPane().setLayout(layout);
		init();
		addComponentListener(this);
		pack();
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	private void init() {
		thickness = new JFormattedTextField();
		thickness.setMinimumSize(min);
		bfPrice = new JFormattedTextField();
		bfPrice.setMinimumSize(min);
		width = new JFormattedTextField();
		width.setMinimumSize(min);

		banner = new JLabel("Enter material thickness, width, and board-foot price in INCHES");
		banner.setHorizontalAlignment(SwingConstants.LEFT);

		toUnits = new JComboBox<String>(units);
		toUnits.setSelectedIndex(1);
		toUnits.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (toUnits.getSelectedIndex() == 0) {
					lft = true;
				} else {
					lft = false;
				}
			}
		});

		pLabel = new JLabel("Board-Foot Price:");
		pLabel.setLabelFor(bfPrice);
		uLabel = new JLabel("Convert To:");
		uLabel.setLabelFor(toUnits);
		tLabel = new JLabel("Thickness:");
		tLabel.setLabelFor(thickness);
		wLabel = new JLabel("Width:");
		wLabel.setLabelFor(width);

		calc = new JButton("Calculate");
		calc.addActionListener(calculate);

		

		Container f = getContentPane();
		f.add(banner, "gapleft 5, gapbottom 10, gapright 10, wrap, span 5");
		f.add(pLabel, "gap 5, span 2");
		f.add(bfPrice, "gap 5, span 1");
		f.add(uLabel, "gap 5, span 1, align right");
		f.add(toUnits, "gap 5, span 1, wrap");
		f.add(tLabel, "gap 5, span 2");
		f.add(thickness, "gap 5, span 1");
		f.add(wLabel, "gap 5, span 1, align right");
		f.add(width, "gap 5, span 1, wrap 15");
		f.add(calc, "gap 5, span 5, grow");
		
		menuBar = new JMenuBar();
		options = new JMenu("Options");
		toggle = new JMenu("Toggle");
		onTop = new JCheckBoxMenuItem("Keep Window on Top");
		help = new JMenu("Help");
		about = new JMenuItem("About");
		laf = new JMenu("Look & Feel");
		ButtonGroup looks = new ButtonGroup();
		basic = new JRadioButtonMenuItem("Basic");
		metal = new JRadioButtonMenuItem("Metal");
		nimbus = new JRadioButtonMenuItem("Nimbus");
		synth = new JRadioButtonMenuItem("Synth");
		looks.add(basic);
		looks.add(nimbus);
		looks.add(metal);
		looks.add(synth);
		basic.setSelected(true);
		
		laf.add(basic);
		laf.add(metal);
		laf.add(nimbus);
		laf.add(synth);
		toggle.add(laf);
		help.add(about);
		toggle.add(onTop);
		options.add(toggle);
		menuBar.add(options);
		menuBar.add(help);
		setJMenuBar(menuBar);
		
		about.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(getContentPane(), "Title:               Board-Foot Calculator\n" +
						"Author:          " + AUTHOR +
						"\nCompany:      " + COMPANY +
						"\nVersion:         " + VERSION +
						"\nDescription:  " + DESCRIPTION +
						"\nCopyright:    " + COPYRIGHT, "About", JOptionPane.INFORMATION_MESSAGE);				
			}
		});
		onTop.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setAlwaysOnTop(onTop.isSelected());
			}
		});
		basic.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (currLAF != LAFs.BASIC.ordinal()) {
					try {
						UIManager.setLookAndFeel("javax.swing.plaf.basic.BasicLookAndFeel");
						currLAF = LAFs.BASIC.ordinal();
						repaint();
					} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
							| UnsupportedLookAndFeelException e1) {
						e1.printStackTrace();
					}
				}
			}
			
		});
		metal.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (currLAF != LAFs.METAL.ordinal()) {
					try {
						UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
						currLAF = LAFs.METAL.ordinal();
						repaint();
					} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
							| UnsupportedLookAndFeelException e1) {
						e1.printStackTrace();
					}
				}
			}
			
		});
	}

	ActionListener calculate = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (check())
				calculate(lft);
			else {
				JOptionPane.showMessageDialog(getRootPane(), "All fields must have valid digit values.", "Empty Fields",
						JOptionPane.OK_OPTION);
			}
		}

	};

	private void calculate(boolean unit) {
		double length;
		if (unit)
			length = 12;
		else {
			length = 36;
		}
		double w = Double.valueOf(width.getText());
		double t = Double.valueOf(thickness.getText());
		double p = Double.valueOf(bfPrice.getText());
		double result = (length * w * t) / 144 * p;
		showOutput(result);
	}

	private void showOutput(double result) {
		out = new JWindow(this);
		out.setSize(150, this.getHeight() - 26);
		out.setLocation(this.getX() + this.getWidth() - 4, this.getY() + 24);
		out.setVisible(true);
		out.getContentPane().add(new JLabel("Price per " + toUnits.getSelectedItem()), "North");
		if (String.valueOf(result).length() > 6) {
			out.getContentPane().add(
					new JLabel("$ " + String.valueOf(result).substring(0, String.valueOf(result).indexOf(".") + 4)),
					"Center");
		} else {
			out.getContentPane().add(new JLabel("$ " + String.valueOf(result)));
		}
		((JLabel) out.getContentPane().getComponent(1)).setHorizontalAlignment(SwingConstants.CENTER);
	}

	private boolean check() {
		if (bfPrice.getText().isEmpty() || bfPrice.getText().trim().equals(null))
			return false;
		for (char c : bfPrice.getText().toCharArray()) {
			if (Character.isLetter(c))
				return false;
		}
		if (thickness.getText().isEmpty() || thickness.getText().trim().equals(null))
			return false;
		for (char c : thickness.getText().toCharArray()) {
			if (Character.isLetter(c))
				return false;
		}
		if (width.getText().isEmpty() || width.getText().trim().equals(null))
			return false;
		for (char c : width.getText().toCharArray()) {
			if (Character.isLetter(c))
				return false;
		}
		return true;
	}

	@Override
	public void componentResized(ComponentEvent e) {
	}

	@Override
	public void componentMoved(ComponentEvent e) {
		try {
			if (out != null) {
				out.setLocation(e.getComponent().getX() + e.getComponent().getWidth() - 4,
						e.getComponent().getY() + 24);
			}
		} catch (NullPointerException np) {
			np.printStackTrace();
		}

	}

	@Override
	public void componentShown(ComponentEvent e) {
	}

	@Override
	public void componentHidden(ComponentEvent e) {
	}
	
	enum LAFs {
		BASIC,
		METAL,
		NIMBUS,
		SYNTH
	}
}
