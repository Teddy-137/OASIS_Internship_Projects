import java.util.Random;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;

public class NumberGuessing {
    Random rand = new Random();
    private int score = 0;
    private int scoreIncrement;
    private int maxRange;
    private int maxAttempts;
    private final String[] levels = {"easy", "medium", "hard"};
    private final String[] options = {"Yes","No","Score"};
    private final String[] start = {"Start","exit"};
    private final String[] options2 = {"Play again", "Exit game"};
    ImageIcon iconHappy = new ImageIcon("happy.png");
    ImageIcon iconSad = new ImageIcon("sad.png");


    public NumberGuessing(){
        this.start();
    }

    private void start(){
        int choice = JOptionPane.showOptionDialog(null,"Welcome to number guessing game",
                "Number guessing game",0,1,null,start,0);
        if(choice == 0){
            level();
            play();
        }
        else{
            System.exit(0);
        }
    }

    private void level(){
        int choice = JOptionPane.showOptionDialog(null,"select level",
                "Number guessing game",
                0,0,null,levels,levels[0]);
        if(choice == 0){
                maxRange = 50;
                maxAttempts = 10;
                scoreIncrement = 1;
            }else if(choice == 1){
                maxRange = 100;
                maxAttempts = 7;
                scoreIncrement = 3;
            }else {
                maxRange = 200;
                maxAttempts = 5;
                scoreIncrement = 5;
            }
    }

    private void play() {
        int randomNum = rand.nextInt(maxRange) + 1;
        boolean invalid = false;
        int guess = -1;
        while(!invalid){
            try{
                guess = Integer.parseInt(JOptionPane.showInputDialog("guess a number between 1 and "+ maxRange +" : "));
                invalid = true;
            }catch(NumberFormatException e){
                JOptionPane.showMessageDialog(null, "Please enter a number!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        while (maxAttempts > 0 && randomNum != guess) {
            try {
                if (guess > randomNum) {
                    guess = Integer.parseInt(JOptionPane.showInputDialog("Try lower number." +
                            "\nguess a number between 1 and "+ maxRange +" : "));
                } else {
                    guess = Integer.parseInt(JOptionPane.showInputDialog("Try higher number." +
                            "\nguess a number between 1 and "+ maxRange +" : "));
                }

            } catch (IllegalArgumentException e) {
                guess = Integer.parseInt(JOptionPane.showInputDialog("Invalid input!. " +
                        "\nguess a number between 1 and "+ maxRange +" : "));
            }
            maxAttempts--;
        }
        if(maxAttempts == 0){
            lost();
            return;
        }
        if(randomNum == guess){
            this.score+=scoreIncrement;
            int result = JOptionPane.showOptionDialog(null,
                "Congratulations! You guessed the number "+ guess + " !\nDo you want to play again?",
                "You win",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                iconHappy,options,options[0]);
            if (result == 0) {
                play();
            }
            else if (result == 1) {
            System.exit(0);
            }
            else if (result == 2) {
                int answer = JOptionPane.showConfirmDialog(null,
                    "Your score: " + score +"\nDo you want to play again? ",
                    "Score",
                    JOptionPane.YES_NO_OPTION);
                if(answer == 0){
                    play();
                }
                else{
                  System.exit(0);
                }
            }
        }
    }
    private void lost(){
        int choice = JOptionPane.showOptionDialog(null, "You lost the game!",
                "You lost!",0,1,iconSad, options2, options2[0]);
        if(choice == 0){
            level();
            play();
        } else{
            System.exit(0);
        }
    }
}