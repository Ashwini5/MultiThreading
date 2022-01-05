package org.example;

// importing awt class
import javax.swing.*;
import javax.swing.plaf.ButtonUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// class to construct a frame and containing main method
public class Layout extends JPanel implements ActionListener {
    // class constructor
    private JToggleButton startButton;
    private JToggleButton pauseButton;
    private JToggleButton unpauseButton;

    public void setLayout(JFrame frame) {
        // setting layout, size and visibility of frame
        frame.setLayout(null);
        frame.setSize(Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
        frame.setVisible(true);
        setBackground (Color.DARK_GRAY);
        setSize(Constants.RACE_AREA_WIDTH, Constants.RACE_AREA_HEIGHT);
    }

    public void addStartButton(JFrame frame){

        this.startButton = new JToggleButton("Start Race", false);
        this.startButton.setBounds(
            (Constants.UI_AREA_WIDTH - Constants.START_BUTTON_WIDTH) / 4,
            Constants.RACE_AREA_HEIGHT + Constants.UI_AREA_HEIGHT / 2 - Constants.START_BUTTON_HEIGHT,
            Constants.START_BUTTON_WIDTH,
            Constants.START_BUTTON_HEIGHT);
        this.startButton.addActionListener(this);
        frame.add(this.startButton);
    }

    private void addPauseButton(JFrame frame) {
        this.pauseButton = new JToggleButton("Pause Race");
        this.pauseButton.setBounds(
                (2 * (Constants.UI_AREA_WIDTH - Constants.START_BUTTON_WIDTH)) / 4,
                Constants.RACE_AREA_HEIGHT + Constants.UI_AREA_HEIGHT / 2 - Constants.START_BUTTON_HEIGHT,
                Constants.START_BUTTON_WIDTH,
                Constants.START_BUTTON_HEIGHT);
        this.pauseButton.addActionListener(this);
        frame.add(this.pauseButton);
    }

    private void addUnPauseButton(JFrame frame) {
        this.unpauseButton = new JToggleButton("Unpause Race");
        this.unpauseButton.setBounds(
                (3 * (Constants.UI_AREA_WIDTH - Constants.START_BUTTON_WIDTH)) / 4,
                Constants.RACE_AREA_HEIGHT + Constants.UI_AREA_HEIGHT / 2 - Constants.START_BUTTON_HEIGHT,
                Constants.START_BUTTON_WIDTH,
                Constants.START_BUTTON_HEIGHT);
        this.unpauseButton.addActionListener(this);
        frame.add(this.unpauseButton);
    }


    // paint() method to draw inside the canvas
    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        g.drawImage(
            new ImageIcon(getClass().getResource("/race_track.png")).getImage(),
            0,
            0,
            Constants.WINDOW_WIDTH,
            Constants.WINDOW_HEIGHT,
            this);

//        Stroke dashedLineStroke = getDashedLineStroke(1);
//        Graphics2D g2 = (Graphics2D) g;
//        g2.setColor(Color.RED);
//        g2.setStroke(dashedLineStroke);
//
//        for (int i = 1; i < (this.getWidth()-10) ; i++)
//            g.drawLine(10, (i*10), this.getWidth()-10, (i*10));
//
//        for (int i = 1; i < (this.getHeight()-10) ; i++)
//            g.drawLine((i*10), 10, (i*10), this.getHeight()-10);

        // UI AREA
//        g.setColor(Color.LIGHT_GRAY);
//        g.fillRect(0, Constants.RACE_AREA_HEIGHT, Constants.RACE_AREA_WIDTH, Constants.RACE_AREA_HEIGHT);
    }

    private Stroke getDashedLineStroke(int width)
    {
        float[] dashPattern = {2, 2};
        return new BasicStroke(width, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 2, dashPattern, 0);
    }

    // class constructor
    public Layout()
    {

        // creating a frame
        JFrame frame = new JFrame("Racing Squares");
        // adding canvas to frame
        frame.add(this);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setFont(new Font(null, 0, 20));
        this.addStartButton(frame);
        this.addPauseButton(frame);
        this.addUnPauseButton(frame);
        this.setLayout(frame);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    public JToggleButton getStartButton() {
        return startButton;
    }

    public JToggleButton getPauseButton() {
        return pauseButton;
    }

    public JToggleButton getUnpauseButton() {
        return unpauseButton;
    }
}


