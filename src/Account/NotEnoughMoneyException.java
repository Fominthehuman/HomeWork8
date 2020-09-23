package Account;

public class NotEnoughMoneyException extends Exception {

    public NotEnoughMoneyException() {
        System.out.println("НЕДОСТАТОЧНО СРЕДСТВ");
    }
}
