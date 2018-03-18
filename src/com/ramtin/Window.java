package com.ramtin;

import javax.swing.*;
import java.awt.*;

/**
 * Created by PC User on 2017-04-20.
 */
public class Window extends Canvas {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Window(int width, int height, String title, Game game) {
        JFrame frame = new JFrame(title);

        frame.setPreferredSize(new Dimension(width, height));
        frame.setMaximumSize(new Dimension(width, height));
        frame.setMinimumSize(new Dimension(width, height));

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null); //window opens in the middle of the screen instead of top left
        frame.add(game); //adding game class into the frame
        frame.setVisible(true);
        game.start();
        //frame.pack();
    }
}
