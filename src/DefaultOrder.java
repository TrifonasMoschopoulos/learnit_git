import java.util.Arrays;

public class DefaultOrder implements Order{
    private static final int AMOUNT_OF_DIGITS_IN_CREDIT_CARD_NUMBER = 16;

    private String creditCardNumber;
    private Product[] products;
    private int customerId;


    @Override
    public boolean isCreditCardNumberValid(String creditCardNumber) {
        if (creditCardNumber.length() != AMOUNT_OF_DIGITS_IN_CREDIT_CARD_NUMBER)
            return false;
        for (Character digit : creditCardNumber.toCharArray()){
            if (Character.isDigit(digit) == false) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void setCreditCardNumber(String creditCardNumber) {
        if (creditCardNumber == null)
            return;
        this.creditCardNumber = creditCardNumber;
    }

    @Override
    public void setProducts(Product[] products) {
        this.products = products;
    }

    @Override
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }


    @Override
    public int getCustomerId() {
        return this.customerId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Order: customer id - " + getCustomerId() + "\t");
        sb.append("credit card number - " + creditCardNumber + "\t");
        sb.append("products - " + Arrays.toString(products));
        return sb.toString();
    }

}
