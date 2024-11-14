import javax.swing.*;
import java.util.Objects;

public class GameInterface {
    ImageIcon happy = new ImageIcon(Objects.requireNonNull(GameInterface.class.getResource("/happy.png")));
    ImageIcon sad = new ImageIcon(Objects.requireNonNull(GameInterface.class.getResource("/sad.png")));
    private final GameLogic gameLogic;
    public GameInterface() {
        gameLogic = new GameLogic();
        showStartMenu();
    }

    void showStartMenu(){
        String[] options = {"Start", "Exit"};
        int choice = JOptionPane.showOptionDialog(null,
                "Welcome to the Number guessing game!",
                "Number guessing game",
                0,1,null,options,options[0]);
        if(choice == 0){
            showLevelSelection();
        }else{
            System.exit(0);
        }
    }
    void showLevelSelection(){
        String[] level = {"Easy", "Medium", "Hard"};
        int choice = JOptionPane.showOptionDialog(null,"Select level",
                "Number guessing game",JOptionPane.DEFAULT_OPTION,
                1,null, level, level[0]);
        String levelName = level[choice];
        gameLogic.setLevel(levelName);
        playGame();
    }
    void playGame(){
        gameLogic.setNewGame();
        int remaining = gameLogic.getRemainingAttempts();
        while (!gameLogic.isGameOver()){
            try {
                int guess = Integer.parseInt(JOptionPane.showInputDialog(
                        "Remaining attempts: " + remaining +
                                "\nGuess a number between 1 and " + gameLogic.getMaxRange() + ": "));
                String result = gameLogic.makeGuess(guess);
                if (result.equals("correct")) {
                    showWinMessage();
                } else if (result.equals("lower")) {
                    JOptionPane.showMessageDialog(null,
                            "Try higher number.");
                } else {
                    JOptionPane.showMessageDialog(null,
                            "Try lower number.");
                }
            }catch(NumberFormatException e){
                JOptionPane.showMessageDialog(null,
                        "Please enter valid number.","Invalid input",0);
            }
            remaining--;

        }
        showLossMessage();
    }

    private void showLossMessage() {
        String[] options = {"Try again","Exit"};
        int choice = JOptionPane.showOptionDialog(null,
                "You lost the game",
                "Game Over",0,1,sad,options,options[0]);
        if(choice == 0){
            showLevelSelection();
        }else {
            System.exit(0);
        }
    }

    private void showWinMessage() {
        String[] options = {"Play again","Exit"};
        int choice = JOptionPane.showOptionDialog(null,
                "Congratulation You guessed the number.\nyour Score = " + gameLogic.getScore()
                        + "\nDo you want to play again?","Number guessing game",0,1,
                happy,options,options[0]);
        if(choice == 0){
            showLevelSelection();
        }else{
            System.exit(0);
        }
    }
}