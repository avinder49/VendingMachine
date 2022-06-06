import java.util.ArrayList;
import java.util.List;

public class User {
    String name;
    boolean admin;
    int balance;
    int validTransActionsAmt;
    boolean hasStoppedTxn;

    List<Transaction> transactionList;
    User(String name , boolean admin){
        this.name = name;
        this.admin = admin;
        this.transactionList = new ArrayList<>();
        this.validTransActionsAmt = 0;
        this.hasStoppedTxn = false;
    }

    public void printTransactionList() {
         transactionList.stream().forEach(transaction -> System.out.println(transaction));
    }

    public void reset(){
        this.transactionList = new ArrayList<>();
        this.validTransActionsAmt = 0;
        this.hasStoppedTxn = false;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", admin=" + admin +
                ", validTransActionsAmt=" + validTransActionsAmt +
                ", hasStoppedTxn=" + hasStoppedTxn +
                '}';
    }
}
