import java.util.Date;
import java.util.UUID;

public class Transaction {
    User user;
    int amount;
    Item transactionItem;
    boolean isSuccessful;
    String status;
    String paymentSignature;
    PaymentSystem paymentSystem;
    Date createdAt;

    static Transaction init (User user , int amount , PaymentSystem paymentSystem ,Item item){
        TransActionBuilder builder = new TransActionBuilder();
        builder.setUser(user).setAmount(amount).setPaymentSystem(paymentSystem).setTransactionItem(item).setStatus("NOT_STARTED");
        Transaction transaction = builder.build();
//        System.out.println("Transaction Initialized");
//        System.out.println(transaction);
        return transaction;
    }

    void start(){
        this.status = "STARTED";
//        System.out.println(this);
    }

    void fail(){
        this.status = "IN_VALID";
        this.isSuccessful= false;
    }

    void complete(){
        this.status = "COMPLETE";
        this.isSuccessful = true;
        user.validTransActionsAmt+=this.amount;
    }

    int processTransAction(){
        if(transactionItem.price==-1 || !paymentSystem.verifyPayment(amount)) {// Item invalid or payment system deos not verify payment , mark is as failed
            this.fail();
            user.transactionList.add(this);
            System.out.println();
            return user.validTransActionsAmt;
        }
        this.complete();
        user.transactionList.add(this);
//        user.printTransactionList();
        System.out.println();

        return user.validTransActionsAmt;
    }

    Transaction(TransActionBuilder builder){
        this.user = builder.user;
        this.amount = builder.amount;
        this.transactionItem = builder.transactionItem;
        this.isSuccessful = builder.isSuccessful;
        this.paymentSignature = builder.paymentSignature;
        this.paymentSystem = builder.paymentSystem;
        this.createdAt = builder.createdAt;
        this.status = builder.status;
    }


    @Override
    public String toString() {
        return "Transaction{" +
                " user=" + user +
                ", amount=" + amount +
                ", transactionItem=" + transactionItem +
                ", isSuccessful=" + isSuccessful +
                ", paymentSignature='" + paymentSignature + '\'' +
                ", paymentSystem=" + paymentSystem +
                ", status=" + status +
                ", createdAt=" + createdAt +
                '}';
    }


}

class TransActionBuilder{
    Transaction transAction;
    User user;
    int amount;
    Item transactionItem;
    PaymentSystem paymentSystem;
    boolean isSuccessful;
    String paymentSignature;
    String status;

    Date createdAt;
    TransActionBuilder(){
        this.paymentSignature = "pay_"+ UUID.randomUUID().toString();
        this.createdAt = new Date();
        this.isSuccessful = false;
    }
    public Transaction build(){
        Transaction transaction = new Transaction(this);
        this.transAction = transaction;
        return transaction;
    }

    public TransActionBuilder setStatus(String status){
        this.status = status;
        return this;
    }


    public TransActionBuilder setPaymentSystem(PaymentSystem paymentSystem){
        this.paymentSystem = paymentSystem;
        return this;
    }

    public TransActionBuilder setTransactionItem(Item transactionItem){
        this.transactionItem = transactionItem;
        return this;
    }

    public TransActionBuilder setUser(User user){
        this.user = user;
        return this;
    }

    public TransActionBuilder setAmount(int amount){
        this.amount = amount;
        return this;
    }

}
