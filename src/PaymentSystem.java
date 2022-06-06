import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

interface PaymentSystem {
     boolean verifyPayment(int amount);
}

class CashPaymentSystem implements PaymentSystem{
    Denomination denomination;

    @Override
    public boolean verifyPayment(int amount) {
        // Logic to handle
        Denomination[] a = Denomination.values();
        List<Denomination> list = Arrays.asList(a);
        List<Integer> validCashNotesList = list.stream().map(denEnum->denEnum.getValue()).collect(Collectors.toList());
        return validCashNotesList.contains(amount);
    }
}

class WalletPaymentSystem implements   PaymentSystem{
    @Override
    public boolean verifyPayment(int amount) {
        // Logic to handle

        return true;
    }
}

enum Denomination {
    FIVE(5), TEN(10), TWENTY(20);
    private int value;
    Denomination(int num){
         this.value = num;
     }
    public int getValue() {
        return value;
    }
}

