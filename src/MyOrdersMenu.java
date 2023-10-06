public class MyOrdersMenu implements Menu{
    private ApplicationContext context;
    private OrderManagementService orderManagementService;

    private static final String NOT_LOGGED_IN_USER_ERROR_MESSAGE = "Please, log in or create new account to see list of your orders";
    private static final String NO_ORDERS_MESSAGE = "Unfortunately, you don’t have any orders yet. Navigate back to main menu to place a new order";

    {
        context = ApplicationContext.getInstance();
        orderManagementService = DefaultOrderManagementService.getInstance();
    }

    @Override
    public void start() {
        printMenuHeader();
        if (!context.someoneIsLoggedIn()){
            System.out.println(NOT_LOGGED_IN_USER_ERROR_MESSAGE);
            return;
        }

        int userId = context.getLoggedInUser().getId();
        Order[] userOrders = orderManagementService.getOrdersByUserId( userId );
        if (userOrders == null || userOrders.length == 0){
            System.out.println(NO_ORDERS_MESSAGE);
        }else{
            for (Order order : userOrders){
                System.out.println(order);
            }
        }
    }

    @Override
    public void printMenuHeader() {
        System.out.println("***** MY ORDERS *****");
    }



}
