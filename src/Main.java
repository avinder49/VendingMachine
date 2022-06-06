import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);
        VendingMachine vendingMachine = null;
        User admin = new User("admin", true);
        boolean loopBreak = false;
        String option ="0";
        while(true) {

            switch (option) {

                case "0":
                    if(vendingMachine != null){
                        System.out.println("Vending Machine Already Initialized . ");
                        System.out.println("Do You Wish To Continue? Y/N");
                        String c = sc.next();
                        if(c.contains("N"))
                            break;
                    }
                    VendingMachine.reset();
                    System.out.println("Enter size of Vending Machine");
                    int rows  = sc.nextInt();
                    int cols  = sc.nextInt();
                    vendingMachine = VendingMachine.getVendingMachine(rows, cols);
                    vendingMachine.showMatrix();
                    break;
                case "1":
                    if(vendingMachine == null){
                        throw new Exception("Vending Machine Not Initialized");
                    }
                    List<Item> itemsListToLoad = new ArrayList<>(
                            Arrays.asList(
                                    Can.getDefaultCan(), Coffee.getDefaultCoffee(),
                                    new Can("specialCan", 30), new Coffee("specialCoffee", 35
                                    )
                            ));
                    vendingMachine.load(admin, itemsListToLoad);
                    System.out.println("VENDING MACHINE AFTER LOADING");
                    vendingMachine.showMatrix();
                    break;
                case "2":
                    if(vendingMachine == null){
                        throw new Exception("Vending Machine Not Initialized");
                    }
                    System.out.println("Enter User name");
                    String userName = sc.next();
                    User user = new User(userName, false);
                    Item[][] matrix = vendingMachine.getMatrix();
                    System.out.println("Enter RowNo");
                    int row  = sc.nextInt();
                    System.out.println("Enter ColNo");
                    int col  = sc.nextInt();
                    Item item = matrix[row][col];
                    System.out.println("Item Selected: "+item);
                    System.out.println("Enter Amount");
                    int amt = sc.nextInt();

                    System.out.println("AMOUNT ENTERED:: "+amt);

                    Transaction transaction = Transaction.init(user,amt,new CashPaymentSystem(),item);
                    transaction.start();
                    int totalTXNAmt = transaction.processTransAction();
                    while(totalTXNAmt < item.price){
                        System.out.println("ENTER AMOUNT TO CONTINUE");
                        System.out.println("ENTER CANCEL TO STOP FURTHER TRANSACTIONS");
                        System.out.println("REMAINING AMOUNT LEFT TO BE PAID: "+(item.price-totalTXNAmt));
                        String choice = sc.next();
                        if(choice.contentEquals("CANCEL")){
                            user.hasStoppedTxn = true;
                            break;
                        }
                        int amount = Integer.parseInt(choice);
                        System.out.println("AMOUNT ENTERED:: "+amount);
                        transaction = Transaction.init(user,amount,new CashPaymentSystem(),matrix[row][col]);
                        totalTXNAmt = transaction.processTransAction();
                    }
                    if(totalTXNAmt>0){ // Some Valid Txns have been made
                        user.balance = Math.abs(user.validTransActionsAmt-item.price);
                        System.out.println("User's Balance:: "+user.balance);
                    }
                    System.out.println("User's List of Transactions");
                    user.printTransactionList();
                    if(!user.hasStoppedTxn)
                    {
                        vendingMachine.matrix[row][col] = new EmptyItem();
                        user.reset();
                    }
                    vendingMachine.showMatrix();
                    break;
                case "3":
                    VendingMachine.reset();
                    System.out.println("Enter size of Vending Machine To Reset");
                     rows  = sc.nextInt();
                     cols  = sc.nextInt();
                    vendingMachine = VendingMachine.getVendingMachine(rows, cols);
                    System.out.println(vendingMachine);
                    break;
                case "4":
                    loopBreak = true;
                    break;
                default:
                    break;
            }
            if(loopBreak == true)
                return;
            System.out.println("Enter operations");
            System.out.println("0. Initialize");
            System.out.println("1. Load Items");
            System.out.println("2. Select Item ");
            System.out.println("3. Reset");
            System.out.println("4. Exit");
            option = sc.next();
        }
    }
}
