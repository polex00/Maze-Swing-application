package sk.stuba.fei.uim.oop;

import java.awt.*;
import java.util.ArrayList;

import static java.util.Collections.shuffle;

public class Maze {

    protected Cell[][] mazeMatrix = new Cell[13][13];

    public Maze(){
        matrixSpawn();
        createMaze();
    }

    public void matrixSpawn(){
        for(int i = 0; i<13; i++){
            for(int j = 0; j<13; j++){
                if(i%2==0 || j%2==0) {
                    mazeMatrix[i][j] = new Wall(i, j);
                }else {
                    mazeMatrix[i][j] = new Path(i, j);
                }
            }
        }
    }

    public ArrayList<Cell> getNeighbours(Cell cell){

        int x = cell.xPos;
        int y = cell.yPos;

        ArrayList<Cell> neighbours = new ArrayList<>();
        if(x < 11 && !mazeMatrix[x+2][y].visited)
            neighbours.add(mazeMatrix[x+2][y]);
        if(y < 11 && !mazeMatrix[x][y+2].visited)
            neighbours.add(mazeMatrix[x][y+2]);
        if(x > 1 && !mazeMatrix[x-2][y].visited)
            neighbours.add(mazeMatrix[x-2][y]);
        if(y > 1 && !mazeMatrix[x][y-2].visited)
            neighbours.add(mazeMatrix[x][y-2]);
        return neighbours;
    }

    public void createMaze(){
        Cell start = mazeMatrix[1][1];
        randomizedDFS(start);
    }

    public void randomizedDFS(Cell current){
        current.markVisited();
        Cell next = randomUnvisitedNeighbour(current);

        while(next != null){
            connectCells(current, next);
            randomizedDFS(next);
            next = randomUnvisitedNeighbour(current);
        }
    }

    public void connectCells(Cell first, Cell second){
        int fx = first.xPos;
        int fy = first.yPos;
        int sx = second.xPos;
        int sy = second.yPos;

        if(fy == sy){
            int newX = (fx + sx) / 2;
            //mazeMatrix[newX][fy].setBackground(Color.WHITE);
            mazeMatrix[newX][fy] = new Path(newX, fy);
        } else {
            int newY = (fy + sy) / 2;
            //mazeMatrix[fx][newY].setBackground(Color.WHITE);
            mazeMatrix[fx][newY] = new Path(fx, newY);
        }
    }

    public Cell randomUnvisitedNeighbour(Cell current){
        ArrayList<Cell> neighbours = getNeighbours(current);
        if(neighbours.size()==0){
            return null;
        } else {
            shuffle(neighbours);
            return neighbours.get(0);
        }
    }

}
