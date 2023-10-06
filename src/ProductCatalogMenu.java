import java.util.Scanner;

public class ProductCatalogMenu implements Menu{
    private static final String CHECKOUT_COMMAND = "checkout";
    private static final String GUIDE_FOR_USER_INIT_MESSAGE = "Enter product id to add it to the cart or ‘menu’ if you want to navigate back to the main menu";
    private static final String ADDED_TO_CART_MESSAGE = "Product %s has been added to your cart. If you want to add a new product - enter the product id. If you want to proceed with checkout - enter word ‘checkout’ to console";
    private static final String NOT_MATCHING_ID_PRODUCT_ERROR_MESSAGE = "Please, enter product ID if you want to add product to cart. Or enter ‘checkout’ if you want to proceed with checkout. Or enter ‘menu’ if you want to navigate back to the main menu";
    private static final String HAS_TO_BE_LOGGED_IN = "You are not logged in. Please, sign in or create new account";

    private ApplicationContext context;
    private ProductManagementService productManagementService;

    {
        context = ApplicationContext.getInstance();
        productManagementService = DefaultProductManagementService.getInstance();
    }

    @Override
    public void start() {
        if (!context.someoneIsLoggedIn()){
            System.out.println(HAS_TO_BE_LOGGED_IN);
            return;
        }

   loop_1:  while (true){
            printMenuHeader();
            System.out.println(productManagementService.toString());
            String command = getUserInput();
            switch (command){
                case MainMenu.MENU_COMMAND:
                    break loop_1;
                case CHECKOUT_COMMAND:
                    Menu checkoutMenu = new CheckoutMenu();
                    if ( ((CheckoutMenu)checkoutMenu).accessAllowed()){
                        checkoutMenu.start();
                        break loop_1;
                    }
                    break;
                default:
                    // someone wants to add to cart
                    if (isValidId(command)) {
                        // id matches to product, we add it to the cart
                        Product productToAdd = productManagementService.getProductById( Integer.parseInt(command));
                        context.getSessionCart().addProduct(productToAdd);  // add product to the cart
                        System.out.printf(ADDED_TO_CART_MESSAGE + "%n", productToAdd.getProductName());          // print message.

                    }else{
                        System.out.println(NOT_MATCHING_ID_PRODUCT_ERROR_MESSAGE);
                    }
            }

        }
    }

    @Override
    public void printMenuHeader() {
        System.out.println("***** PRODUCT CATALOG *****");
        System.out.println(GUIDE_FOR_USER_INIT_MESSAGE);
    }






    private boolean isValidId(String id_string){
        if (id_string == null || id_string.isEmpty()){
            return false;
        }
        for (int i=0; i<id_string.length(); i++){
            if (!Character.isDigit(id_string.charAt(i))) {
                return false;
            }
        }

        int id = Integer.parseInt(id_string);
        return productManagementService.getProductById(id) != null;
    }



    private String getUserInput(){
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        return input;
    }
}
