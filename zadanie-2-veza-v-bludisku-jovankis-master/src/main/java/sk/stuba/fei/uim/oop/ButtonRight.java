package sk.stuba.fei.uim.oop;

public class ButtonRight extends MyButton {
    public ButtonRight() {
        super("RIGHT");
        setFocusable(false);
    }

    public void doMove(Cell player, Cell[][] matrix, MyFrame frame){
        player.moveRight(matrix, frame);
    }
}
