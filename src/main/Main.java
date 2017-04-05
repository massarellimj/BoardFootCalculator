package main;

import javax.swing.SwingUtilities;

import ui.AppFrame;

public class Main {

	private Main() {}

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				AppFrame af = new AppFrame();
			}
		});
	}
}
