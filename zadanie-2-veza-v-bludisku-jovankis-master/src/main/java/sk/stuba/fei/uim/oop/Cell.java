package sk.stuba.fei.uim.oop;

import javax.swing.*;
import java.awt.*;

public class Cell extends JPanel{

    protected boolean visited;
    protected int xPos;
    protected int yPos;


    public Cell(int x, int y) {
        //this.addMouseListener(this);
        this.setSize(40, 40);
        this.visited = false;
        this.xPos = x;
        this.yPos = y;
        setVisible(true);
    }

    public void markVisited(){
        this.visited = true;
        repaint();
    }

    public void moveRight(Cell[][] matrix, MyFrame frame){
        if(matrix[xPos][yPos+1] instanceof Path) {
            matrix[xPos][yPos].setBackground(Color.WHITE);
            frame.player = matrix[xPos][yPos+1];
            matrix[xPos][yPos+1].setBackground(Color.RED);
        }
    }

    public void moveDown(Cell[][] matrix, MyFrame frame){
        if(matrix[xPos+1][yPos] instanceof Path) {
            matrix[xPos][yPos].setBackground(Color.WHITE);
            frame.player = matrix[xPos+1][yPos];
            matrix[xPos+1][yPos].setBackground(Color.RED);
        }
    }

    public void moveLeft(Cell[][] matrix, MyFrame frame){
        if(matrix[xPos][yPos-1] instanceof Path) {
            matrix[xPos][yPos].setBackground(Color.WHITE);
            frame.player = matrix[xPos][yPos-1];
            matrix[xPos][yPos-1].setBackground(Color.RED);
        }
    }

    public void moveUp(Cell[][] matrix, MyFrame frame){
        if(matrix[xPos-1][yPos] instanceof Path) {
            matrix[xPos][yPos].setBackground(Color.WHITE);
            frame.player = matrix[xPos-1][yPos];
            matrix[xPos-1][yPos].setBackground(Color.RED);
        }
    }

    public void showPossibilities(Cell[][] matrix, MyFrame frame){
        setFocusable(false);
        showRight(matrix, this.xPos, this.yPos+1);
        showDown(matrix, this.xPos+1, this.yPos);
        showLeft(matrix, this.xPos, this.yPos-1);
        showUp(matrix, this.xPos-1, this.yPos);
        matrix[11][11].setBackground(Color.BLUE);
        frame.repaint();
    }

    public void showRight(Cell[][] matrix, int x, int y){
        while(matrix[x][y] instanceof Path){
            matrix[x][y].setBackground(Color.YELLOW);
            y++;
        }
    }
    public void showDown(Cell[][] matrix, int x, int y){
        while(matrix[x][y] instanceof Path){
            matrix[x][y].setBackground(Color.YELLOW);
            x++;
        }
    }
    public void showLeft(Cell[][] matrix, int x, int y){
        while(matrix[x][y] instanceof Path){
            matrix[x][y].setBackground(Color.YELLOW);
            y--;
        }
    }
    public void showUp(Cell[][] matrix, int x, int y){
        while(matrix[x][y] instanceof Path){
            matrix[x][y].setBackground(Color.YELLOW);
            x--;
        }
    }

}
