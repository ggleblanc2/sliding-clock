package com.ggl.sliding.clock.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

import com.ggl.sliding.clock.model.SlidingClockModel;

public class ClockPanel {

	private JPanel panel;

	private SlidingClockModel model;

	private SlidingPanel hourPanel;
	private SlidingPanel tenMinutePanel;
	private SlidingPanel minutePanel;
	private SlidingPanel tenSecondPanel;
	private SlidingPanel secondPanel;
	private SlidingPanel meridianPanel;

	public ClockPanel(SlidingClockModel model) {
		this.model = model;
		createPartControl();
	}

	private void createPartControl() {
		panel = new JPanel();
		panel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 4, false));
		panel.setLayout(new BoxLayout(panel, BoxLayout.LINE_AXIS));

		Font font = panel.getFont();
		Font derivedFont = font.deriveFont(Font.BOLD, 48F);

		String[] hourValues = { " 1", " 2", " 3", " 4", " 5", " 6", " 7", " 8", " 9", "10", "11", "12" };
		String[] tenValues = { "0", "1", "2", "3", "4", "5" };
		String[] digitValues = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9" };
		String[] meridianValues = { "AM", "PM" };

		hourPanel = new SlidingPanel(hourValues, derivedFont);
		hourPanel.setPanelValue(model.getHourString());
		panel.add(hourPanel);

		panel.add(createSeparatorPanel());

		tenMinutePanel = new SlidingPanel(tenValues, derivedFont);
		tenMinutePanel.setPanelValue(model.getTenMinuteString());
		panel.add(tenMinutePanel);

		minutePanel = new SlidingPanel(digitValues, derivedFont);
		minutePanel.setPanelValue(model.getMinuteString());
		panel.add(minutePanel);

		panel.add(createSeparatorPanel());

		tenSecondPanel = new SlidingPanel(tenValues, derivedFont);
		tenSecondPanel.setPanelValue(model.getTenSecondString());
		panel.add(tenSecondPanel);

		secondPanel = new SlidingPanel(digitValues, derivedFont);
		secondPanel.setPanelValue(model.getSecondString());
		panel.add(secondPanel);

		panel.add(createSeparatorPanel());

		meridianPanel = new SlidingPanel(meridianValues, derivedFont);
		meridianPanel.setPanelValue(model.getMeridianString());
		panel.add(meridianPanel);
	}

	public void updatePartControl() {
		hourPanel.updatePanelValue(model.getHourString());
		tenMinutePanel.updatePanelValue(model.getTenMinuteString());
		minutePanel.updatePanelValue(model.getMinuteString());
		tenSecondPanel.updatePanelValue(model.getTenSecondString());
		secondPanel.updatePanelValue(model.getSecondString());
		meridianPanel.updatePanelValue(model.getMeridianString());
	}

	public JPanel getPanel() {
		return panel;
	}

	private JPanel createSeparatorPanel() {
		JPanel panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);

		Component component = Box.createHorizontalStrut(0);
		panel.add(component);

		return panel;
	}

}
