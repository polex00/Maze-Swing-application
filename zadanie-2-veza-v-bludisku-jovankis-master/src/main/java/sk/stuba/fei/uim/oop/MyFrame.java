package sk.stuba.fei.uim.oop;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MyFrame extends JFrame implements KeyListener, ActionListener, MouseListener, MouseMotionListener {

    private Cell[][] matrix;
    protected Cell player;
    private boolean clicked;
    private final NorthPanel northPanel = new NorthPanel();
    private CenterPanel centerPanel = new CenterPanel();

    public MyFrame(){
        addMouseMotionListener(this);
        addKeyListener(this);
        addMouseListener(this);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 700);
        setLayout(new BorderLayout());
        add(northPanel, BorderLayout.NORTH);

        ButtonUp up = new ButtonUp();
        northPanel.add(up);
        RestartButton restart = new RestartButton();
        northPanel.add(restart);
        ButtonLeft left = new ButtonLeft();
        northPanel.add(left);
        ButtonDown down = new ButtonDown();
        northPanel.add(down);
        ButtonRight right = new ButtonRight();
        northPanel.add(right);
        up.addActionListener(this);
        restart.addActionListener(this);
        left.addActionListener(this);
        down.addActionListener(this);
        right.addActionListener(this);

        generateMaze();
        add(centerPanel, BorderLayout.CENTER);
        repaint();

        setFocusable(true);
        setVisible(true);
    }

    public void generateMaze(){
        Maze maze = new Maze();
        matrix = maze.mazeMatrix;
        addPanelsOnCenter();
        addPlayerAndFinish();
    }
    public void addPanelsOnCenter(){
        for(Cell[] row:matrix){
            for(Cell col:row){
                centerPanel.add(col);
            }
        }
    }

    public void addPlayerAndFinish(){
        matrix[1][1].setBackground(Color.RED);
        player = matrix[1][1];
        matrix[11][11].setBackground(Color.BLUE);
    }


    @Override
    public void keyTyped(KeyEvent e) {
        hidePossibilities();
        clicked = false;
        char motion = e.getKeyChar();
        switch(motion) {
            case 'd':
            case 'D':
                player.moveRight(matrix, this);
                if(checkWin()){
                    startNewGame();
                }
                break;
            case 's':
            case 'S':
                player.moveDown(matrix, this);
                if(checkWin()){
                    startNewGame();
                }
                break;
            case 'a':
            case 'A':
                player.moveLeft(matrix, this);
                break;
            case 'w':
            case 'W':
                player.moveUp(matrix, this);
                break;
            default:
                break;
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {}
    @Override
    public void keyReleased(KeyEvent e) {}

    public boolean checkWin(){
        int x = player.xPos;
        int y = player.yPos;
        return x == 11 && y == 11;
    }

    public void startNewGame(){
        clicked = false;
        this.northPanel.updateResult();
        remove(centerPanel);
        centerPanel = new CenterPanel();
        generateMaze();
        add(centerPanel, BorderLayout.CENTER);
        centerPanel.setFocusable(true);
        setSize(600, 700);
        setVisible(true);
        repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() instanceof RestartButton){
            startNewGame();
            this.northPanel.restartCounter();
        } else {
            hidePossibilities();
            MyButton button = (MyButton) e.getSource();
            button.doMove(player, matrix, this);
            if (checkWin()) startNewGame();
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int y = (e.getY()-127)/40;
        int x = (e.getX()-53)/38;
        try{
            if (matrix[y][x] == player) {
                clicked = true;
                matrix[y][x].showPossibilities(matrix, this);
            }
            else if((matrix[y][x].getBackground()==Color.YELLOW || matrix[y][x].getBackground()==Color.CYAN) && clicked ){
                player.setBackground(Color.WHITE);
                player = matrix[y][x];
                hidePossibilities();
                matrix[y][x].setBackground(Color.RED);
                clicked = false;
            }else if(matrix[y][x].getBackground()==Color.BLUE && clicked){
                    startNewGame();
                }
            else{
                clicked=false;
                hidePossibilities();
            }
        } catch(ArrayIndexOutOfBoundsException exception) {
            hidePossibilities();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {}
    @Override
    public void mouseReleased(MouseEvent e) {}
    @Override
    public void mouseEntered(MouseEvent e) {}
    @Override
    public void mouseExited(MouseEvent e) {}

    public void hidePossibilities(){
        for(Cell[] row:matrix){
            for(Cell cell: row){
                if(cell instanceof Path && (cell.getBackground()==Color.YELLOW || cell.getBackground()==Color.CYAN)) cell.setBackground(Color.WHITE);
            }
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {}

    @Override
    public void mouseMoved(MouseEvent e) {
        int y = (e.getY()-127)/40;
        int x = (e.getX()-53)/38;
        try{
            if(clicked && matrix[y][x].getBackground()==Color.YELLOW){
                player.showPossibilities(matrix, this);
                matrix[y][x].setBackground(Color.CYAN);
            } else if(clicked && matrix[y][x].getBackground()!=Color.YELLOW && matrix[y][x].getBackground()!=Color.CYAN){
                player.showPossibilities(matrix, this);
            }
        } catch(ArrayIndexOutOfBoundsException exception){
            if(clicked) {
                player.showPossibilities(matrix, this);
                clicked = false;
            }
        }
    }
}
