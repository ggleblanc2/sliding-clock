package com.ggl.sliding.clock.runnable;

import javax.swing.SwingUtilities;

import com.ggl.sliding.clock.model.SlidingClockModel;
import com.ggl.sliding.clock.view.SlidingClockFrame;

public class ClockRunnable implements Runnable {

	private SlidingClockFrame frame;

	private SlidingClockModel model;

	public ClockRunnable(SlidingClockFrame frame, SlidingClockModel model) {
		this.frame = frame;
		this.model = model;
	}

	@Override
	public void run() {
		while (true) {
			model.setCurrentTime();
			update();
			sleep(200L);
		}
	}

	private void update() {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				frame.updatePartControl();
			}
		});
	}

	private void sleep(long duration) {
		try {
			Thread.sleep(duration);
		} catch (InterruptedException e) {

		}
	}

}
