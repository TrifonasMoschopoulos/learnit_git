import java.util.Scanner;

public class CheckoutMenu implements Menu{
    private ApplicationContext context;
    private OrderManagementService orderManagementService;

    private static final String EMPTY_CART_ERROR_MESSAGE = "Your cart is empty. Please, add product to cart first and then proceed with checkout";
    private static final String NOT_LOGGED_IN_ERROR_MESSAGE = "You are not logged in. Please, sign in or create new account";

    private static final String ENTER_CREDIT_CARD_MESSAGE = "Enter your credit card number without spaces and press enter if you confirm purchase";
    private static final String THANKS_FOR_PURCHASE_MESSAGE = "Thanks a lot for your purchase. Details about order delivery are sent to your email.";
    private static final String INVALID_CREDIT_CARD_MESSAGE = "You entered invalid credit card number. Valid credit card should contain 16 digits. Please, try one more time.";

    {
        context = ApplicationContext.getInstance();
        orderManagementService = DefaultOrderManagementService.getInstance();
    }

    @Override
    public void start() {
        printMenuHeader();
        Order newOrder = new DefaultOrder();
        String usersCreditCard;
        while (true) {
            usersCreditCard = getCreditCardNumberFromUser();
            if (newOrder.isCreditCardNumberValid(usersCreditCard)) {
                newOrder.setCustomerId(context.getLoggedInUser().getId());
                newOrder.setProducts(context.getSessionCart().getProducts());
                newOrder.setCreditCardNumber(usersCreditCard);
                break;
            }
            System.out.println(INVALID_CREDIT_CARD_MESSAGE);
        }
        // we have a new order with valid credit card
        orderManagementService.addOrder(newOrder);
        context.getSessionCart().clear();
        System.out.println(THANKS_FOR_PURCHASE_MESSAGE);
    }

    @Override
    public void printMenuHeader() {
        System.out.println("***** CHECKOUT *****");
    }


    public boolean accessAllowed() {
        if (!context.someoneIsLoggedIn() || context.getSessionCart() == null || context.getSessionCart().isEmpty()) {
            System.out.println(getErrorMessage());
            return false;
        }
        return true;
    }


    public String getErrorMessage(){
         if (context.getSessionCart().isEmpty())
             return EMPTY_CART_ERROR_MESSAGE;
        if (!context.someoneIsLoggedIn())
            return NOT_LOGGED_IN_ERROR_MESSAGE;
        return null;
    }

    private String getCreditCardNumberFromUser(){
        Scanner sc = new Scanner(System.in);
        System.out.println(ENTER_CREDIT_CARD_MESSAGE);
        return sc.nextLine();
    }
}
