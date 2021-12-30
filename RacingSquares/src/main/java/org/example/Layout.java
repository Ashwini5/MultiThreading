package org.example;

// importing awt class
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// class to construct a frame and containing main method
public class Layout extends JPanel implements ActionListener {
    // class constructor
    public void setLayout(JFrame frame) {
        // setting layout, size and visibility of frame
        frame.setLayout(null);
        frame.setSize(Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
        frame.setVisible(true);
        setBackground (Color.WHITE);
        setSize(Constants.RACE_AREA_WIDTH, Constants.RACE_AREA_HEIGHT);
    }

    public void addStartButton(JFrame frame){

        Button startButton = new Button("Start Race");
        startButton.setBounds(
            (Constants.UI_AREA_WIDTH - Constants.START_BUTTON_WIDTH) / 2,
            Constants.RACE_AREA_HEIGHT + (Constants.UI_AREA_HEIGHT - Constants.START_BUTTON_HEIGHT) / 2,
            Constants.START_BUTTON_WIDTH,
            Constants.START_BUTTON_HEIGHT);
        startButton.addActionListener(this);
        frame.add(startButton);
    }

    // paint() method to draw inside the canvas
    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        g.drawLine(
            Constants.START_X_CO_ORD + Constants.END_X_DISTANCE,
            Constants.END_Y_MARGIN,
            Constants.START_X_CO_ORD + Constants.END_X_DISTANCE,
            Constants.RACE_AREA_HEIGHT - Constants.END_Y_MARGIN);

        // UI AREA
//        g.setColor(Color.LIGHT_GRAY);
//        g.fillRect(0, Constants.RACE_AREA_HEIGHT, Constants.RACE_AREA_WIDTH, Constants.RACE_AREA_HEIGHT);
    }
    // class constructor
    public Layout()
    {

        // creating a frame
        JFrame frame = new JFrame("Racing Squares");
        // adding canvas to frame
        frame.add(this);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.addStartButton(frame);
        this.setLayout(frame);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}


