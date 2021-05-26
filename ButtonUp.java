package sk.stuba.fei.uim.oop;

public class ButtonUp extends MyButton{
    public ButtonUp() {
        super("UP");
        setFocusable(false);
    }
    public void doMove(Cell player, Cell[][] matrix, MyFrame frame){
        player.moveUp(matrix, frame);
    }
}
