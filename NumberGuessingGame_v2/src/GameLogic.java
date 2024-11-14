import java.util.Random;

public class GameLogic {
    private int score;
    private int maxRange;
    private int maxAttempts;
    private int currentNumber;
    private int remainingAttempts;
    Random rand = new Random();

    public void setLevel(String level) {
            switch (level) {
                case "Easy":
                    maxRange = 50;
                    maxAttempts = 10;
                    score = 3;
                    break;
                case "Medium":
                    maxRange = 100;
                    maxAttempts = 7;
                    score = 5;
                    break;
                case "Hard":
                    maxRange = 200;
                    maxAttempts = 5;
                    score = 10;
                    break;
                default:
                    throw new IllegalArgumentException("Invalid level: " + level);
            }
    }
    public void setNewGame(){
            currentNumber = rand.nextInt(maxRange) + 1;
            remainingAttempts = maxAttempts;
    }
    public String makeGuess(int guess){
            if(guess == currentNumber){
                return "correct";
            } else if (guess < currentNumber) {
                return "lower";
            }else{
                return "higher";
            }
    }
    public boolean isGameOver(){
        return remainingAttempts <= 0;
    }
    public int getScore(){
        return score;
    }
    public int getRemainingAttempts(){
        return remainingAttempts;
    }
    public int getMaxRange(){
        return maxRange;
    }


}
