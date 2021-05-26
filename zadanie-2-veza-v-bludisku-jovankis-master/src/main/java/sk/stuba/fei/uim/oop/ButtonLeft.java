package sk.stuba.fei.uim.oop;

public class ButtonLeft extends MyButton {
    public ButtonLeft() {
        super("LEFT");
        setFocusable(false);
    }

    public void doMove(Cell player, Cell[][] matrix, MyFrame frame){
        player.moveLeft(matrix, frame);
    }
}
