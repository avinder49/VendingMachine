import java.util.Date;
import java.util.List;

 interface PaymentSystem {
     boolean verifyPayment(int amount);
}

class CashPaymentSystem implements PaymentSystem{
        Dem denomination;

    @Override
    public boolean verifyPayment(int amount) {
        // Logic to handle

        return true;
    }
}

class User {
    boolean admin;
}

enum PaymentMethod {
    CASH, WALLET;
};

enum Dem {
     FIVE, TEN, TWENTY;

}

class VendingMachine{
    int r;
    int c;
    Item[][] matrix;
    PaymentSystem paymentSystem;
    VendingMachine(int row, int column ){
        r = row;
        c = column;
        matrix = new Item[row][column];
        for(int i=0;i<r;i++) {
            for (int j = 0; j < c; j++) {
            matrix[i][j] = new EmptyItem();
            }
        }
    }

    void loadAtSlot(User user , int row, int col, Item item ){
        if(user.admin)
        this.matrix[row][col] = item;
    }

//    public void load(User user , List<Item> items){
//        for(int i=0;i<r;i++) {
//            for (int j = 0; j < c; j++) {
//                loadAtSlot(user, row, col, item);
//            }
//        }
//    }

    void deliverItem (){

    }

//    public static void main(String[] args) {
//        int r= 2;
//        int c = 3;
//        VendingMachine v = new VendingMachine(2,3);
//        for(int i=0;i<r;i++){
//            for(int j=0;j<c;j++){
//                System.out.println(v.matrix[i][j]);
//            }
//        }
//    }


}

interface Item {

    void setName(String name);
    void setPrice(double price);
    String getName(String name);
    double getPrice(double price);
}

class EmptyItem implements Item {
    String name;
    double price;
    EmptyItem(){
        name = "empty";
        price = -1;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String getName(String name) {
        return null;
    }

    @Override
    public double getPrice(double price) {
        return 0;
    }

    @Override
    public String toString() {
        return "EmptyItem{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}

/*
Entities

1. Vending Machine
2. User
3. Item
4. Payment


Vending Machine:
Attributes:
Row: Item[]
Column: Item[]

class VendingMachine {
    int row
    int column
    Item[][] matrix
    constructor(row, column) {
        this.matrix = new Item[row][column];
    }
}

interface Item {
    string name
    double price
    Date expiryDate

    setName()
    getName()
    ...
}

class User {
    Bool admin
}

class Payment {
    enum paymentMethod
    enum denomination
}
 */