import java.util.Arrays;
import java.util.List;

public class VendingMachine{ // Singleton
    int r;
    int c;
    Item[][] matrix;
    static VendingMachine machine;
    private VendingMachine(){

    }
    public static VendingMachine getVendingMachine(int row, int column){
        if(VendingMachine.machine != null){
            return VendingMachine.machine;
        }
        VendingMachine machine = new VendingMachine();
        machine.r = row;
        machine.c = column;
        machine.matrix = new Item[row][column];
        for(int i=0;i<row;i++) {
            for (int j = 0; j < column; j++) {
                machine.matrix[i][j] = new EmptyItem();
            }
        }
        VendingMachine.machine = machine;
        return VendingMachine.machine;
    }

    public Item[][] getMatrix(){
        return machine.matrix;
    }

    public static VendingMachine reset(){
        VendingMachine.machine = null;
        return null;
    }

    Item getItem(int row, int col){
        return matrix[row][col];
    }

    void loadAtSlot(User user , int row, int col, Item item ) throws Exception{
        if(!user.admin){
            throw new Exception("Only Admin Can Load At Slot");
        }
        if(!matrix[row][col].getClass().getName().contains("Empty")) // Not Empty
            return;
        this.matrix[row][col] = item;
    }

    void showMatrix(){
        for(int i=0;i<r;i++) {
            for (int j = 0; j < c; j++) {
                System.out.print(" | "+matrix[i][j]+" | ");
            }
            System.out.println();
            System.out.println();
        }
    }

    public void load(User user , List<Item> items) throws Exception {
        System.out.println("Vending Machine Being Loaded By "+user);
        if(!user.admin){
            throw new Exception("Only Admin Can Load At Slot");
        }
        for(int i=0;i<r;i++) {
            for (int j = 0; j < c; j++) {
                if(items.isEmpty()){
                    System.out.println("All items Loaded");
                    return;
                }
                if(!matrix[i][j].getClass().getName().contains("Empty")) // Not Empty
                    continue;
                Item item = items.get(0);
                loadAtSlot(user, i, j, item);
                items.remove(item);
            }
        }
        if(!items.isEmpty()){
            System.out.println("Some items can't be loaded");
            return;
        }
    }

    void deliverItem (){

    }

    @Override
    public String toString() {
        return "VendingMachine{" +
                "r=" + r +
                ", c=" + c +
                ", matrix=" + Arrays.toString(matrix) +
                '}';
    }
}
