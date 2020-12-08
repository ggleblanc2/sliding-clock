package com.ggl.sliding.clock;

import javax.swing.SwingUtilities;

import com.ggl.sliding.clock.model.SlidingClockModel;
import com.ggl.sliding.clock.view.SlidingClockFrame;

public class SlidingClock implements Runnable {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new SlidingClock());
	}

	@Override
	public void run() {
		new SlidingClockFrame(new SlidingClockModel());
	}

}
