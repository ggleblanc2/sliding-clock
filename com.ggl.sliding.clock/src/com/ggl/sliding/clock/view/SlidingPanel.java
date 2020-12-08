package com.ggl.sliding.clock.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class SlidingPanel extends JPanel {

	private static final long serialVersionUID = 661553022861652947L;

	private static final int MARGIN = 4;

	private int imageY;

	private BufferedImage slidingImage;

	private Dimension characterDimension;

	private final Font font;

	private String currentValue;

	private final String[] panelValues;

	public SlidingPanel(String[] panelValues, Font font) {
		this.panelValues = panelValues;
		this.font = font;
		this.characterDimension = calculateFontSize();
		this.slidingImage = generateSlidingImage();
		this.setPreferredSize(characterDimension);
	}

	private Dimension calculateFontSize() {
		int maxWidth = 0;
		int maxHeight = 0;
		FontRenderContext frc = new FontRenderContext(null, true, true);
		for (String s : panelValues) {
			Rectangle2D r2D = font.getStringBounds(s, frc);
			int rWidth = (int) Math.round(r2D.getWidth());
			int rHeight = (int) Math.round(r2D.getHeight());
			maxWidth = Math.max(maxWidth, rWidth);
			maxHeight = Math.max(maxHeight, rHeight);
		}

		return new Dimension(maxWidth, maxHeight);
	}

	private BufferedImage generateSlidingImage() {
		int height = calculateStringHeight() * (panelValues.length + 1);
		BufferedImage slidingImage = new BufferedImage(characterDimension.width, 
				height, BufferedImage.TYPE_INT_RGB);
		Graphics g = slidingImage.getGraphics();
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, characterDimension.width, height);
		g.setColor(Color.BLACK);
		g.setFont(font);

		int y = characterDimension.height - MARGIN;

		for (String s : panelValues) {
			g.drawString(s, 0, y);
			y += calculateStringHeight();
		}

		g.drawString(panelValues[0], 0, y);
		g.dispose();
		return slidingImage;
	}

	public void setPanelValue(String value) {
		int index = getValueIndex(value);
		this.currentValue = value;
		this.imageY = calculateStringHeight() * index;
		repaint();
	}

	public void updatePanelValue(String value) {
		if (!currentValue.equals(value)) {
			int index = getValueIndex(value);
			int finalY = calculateStringHeight() * index;
			SliderAnimation sliderAnimation = new SliderAnimation(imageY, finalY);
			new Thread(sliderAnimation).start();
			this.currentValue = value;
		}
	}

	private int getValueIndex(String value) {
		for (int index = 0; index < panelValues.length; index++) {
			if (value.equals(panelValues[index])) {
				return index;
			}
		}

		return -1;
	}

	private int calculateStringHeight() {
		return characterDimension.height + MARGIN;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		BufferedImage subImage = slidingImage.getSubimage(0, imageY, 
				characterDimension.width,
				characterDimension.height);
		g.drawImage(subImage, 0, 0, this);
	}

	public class SliderAnimation implements Runnable {
		private int originalY;
		private int finalY;

		public SliderAnimation(int originalY, int finalY) {
			this.originalY = originalY;
			this.finalY = finalY;
		}

		@Override
		public void run() {
			int differenceY = finalY - originalY;
			if (finalY == 0) {
				differenceY = characterDimension.height + MARGIN;
			}

			int steps = 10;
			double difference = (double) differenceY / steps;
			for (int index = 1; index <= steps; index++) {
				imageY = (int) Math.round(difference * index + originalY);
				update();
				sleep(30L);
			}

			if (finalY == 0) {
				imageY = 0;
				update();
			} else {
				imageY = finalY;
			}
		}

		private void update() {
			SwingUtilities.invokeLater(new Runnable() {
				@Override
				public void run() {
					SlidingPanel.this.repaint();
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
}
