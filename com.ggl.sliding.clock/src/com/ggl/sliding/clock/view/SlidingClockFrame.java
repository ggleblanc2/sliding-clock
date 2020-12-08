package com.ggl.sliding.clock.view;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import com.ggl.sliding.clock.model.SlidingClockModel;
import com.ggl.sliding.clock.runnable.ClockRunnable;

public class SlidingClockFrame {

	private ClockPanel clockPanel;

	private JFrame frame;

	private SlidingClockModel model;

	public SlidingClockFrame(SlidingClockModel model) {
		this.model = model;
		createPartControl();
	}

	private void createPartControl() {
		frame = new JFrame("Clock");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setIconImage(getImage());
		frame.setUndecorated(true);
		
		clockPanel = new ClockPanel(model);
		frame.add(clockPanel.getPanel());

		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

		ClockRunnable clockRunnable = new ClockRunnable(this, model);
		new Thread(clockRunnable).start();
	}

	public void updatePartControl() {
		clockPanel.updatePartControl();
	}

	private BufferedImage getImage() {
		try {
			return ImageIO.read(getClass().getResourceAsStream("/clock.png"));
		} catch (IOException e) {
			return null;
		}
	}
}
