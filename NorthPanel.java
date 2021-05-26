package sk.stuba.fei.uim.oop;

import javax.swing.*;
import java.awt.*;

public class NorthPanel extends JPanel{

    private int numberOfWins = 0;
    private final JLabel result = new JLabel("Result: " + numberOfWins);

    public NorthPanel() {
        setLayout(new GridLayout(2, 3));
        setSize(600, 100);
        setVisible(true);
        add(result);
    }

    public void updateResult(){
        this.numberOfWins++;
        result.setText("Result: " + numberOfWins);
    }

    public void restartCounter(){
        this.numberOfWins = 0;
        result.setText("Result: " + numberOfWins);
    }

}
