package sk.stuba.fei.uim.oop;

public class ButtonDown extends MyButton {
    public ButtonDown() {
        super("DOWN");
        setFocusable(false);
    }

    public void doMove(Cell player, Cell[][] matrix, MyFrame frame){
        player.moveDown(matrix, frame);
    }
}
