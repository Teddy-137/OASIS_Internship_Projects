public class  ATMInterface{
    public static void main(String[] args) {
        Bank bank = new Bank();
        ATM atm = new ATM(bank);
        byte maxTrail = 3;
        atm.start();
    }
}
