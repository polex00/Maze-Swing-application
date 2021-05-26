package sk.stuba.fei.uim.oop;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class CenterPanel extends JPanel{

    public CenterPanel() {
        setBorder(new EmptyBorder(40, 40, 40, 40));
        setLayout(new GridLayout(13, 13));
        setSize(600, 600);
        setVisible(true);
        setFocusable(true);
        repaint();
    }

}
