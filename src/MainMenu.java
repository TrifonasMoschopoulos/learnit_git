import java.util.Scanner;

public class MainMenu implements Menu{
    public static final String MENU_COMMAND = "menu";

    private static final String MAIN_MENU_TEXT_FOR_LOGGED_OUT_USER = "Please, enter number in console to proceed." + System.lineSeparator()
            + "1. Sign Up" + System.lineSeparator() + "2. Sign In"
            + System.lineSeparator() + "3. Product Catalog" + System.lineSeparator()
            + "4. My Orders" + System.lineSeparator() + "5. Settings" + System.lineSeparator() +
            "6. Customer List";

    private static final String MAIN_MENU_TEXT_FOR_LOGGED_IN_USER = "Please, enter number in console to proceed." + System.lineSeparator()
            + "1. Sign Up" + System.lineSeparator() + "2. Sign Out"
            + System.lineSeparator() + "3. Product Catalog" + System.lineSeparator()
            + "4. My Orders" + System.lineSeparator() + "5. Settings" + System.lineSeparator() +
            "6. Customer List";;

    private static final String INCORRECT_INPUT_MESSAGE = "Only 1, 2, 3, 4, 5, 6 is allowed. Try one more time.";

    private ApplicationContext context;

    {
        context = ApplicationContext.getInstance();
    }

    @Override
    public void start() {
        String userMenuChoice;

        do {
            printMenuHeader();    // output menu choices
            userMenuChoice = getUserInput();   // get input from user (what he chose)
            if (validInput(userMenuChoice)){    // check if input belongs to {"1","2","3","4","5","6","exit"}
                if ( !userMenuChoice.equals( Main.EXIT_COMMAND  ) ){
                    Menu nextMenu = getChosenMenu(userMenuChoice);     // get the menu to run. This is the menu that chosen by the user
                    nextMenu.start();                                                   // run the chosen menu
                }
            }else{
                System.out.println(INCORRECT_INPUT_MESSAGE);
            }
        } while (!userMenuChoice.equals( Main.EXIT_COMMAND ));      // loop ends when user input is "exit".

    }


    @Override
    public void printMenuHeader() {
        if (context.someoneIsLoggedIn()){
            System.out.println(MAIN_MENU_TEXT_FOR_LOGGED_IN_USER);
        }else{
            System.out.println(MAIN_MENU_TEXT_FOR_LOGGED_OUT_USER);
        }
    }


    private boolean validInput(String input){
        if (input.matches("[123456]") || input.matches(Main.EXIT_COMMAND)){
            return true;
        }
        return false;
    }



    private String getUserInput(){
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        return input;
    }



    private Menu getChosenMenu(String menuSelected){
        Menu chosenMenu = null;
        switch (menuSelected){
            case "1":
                chosenMenu = new SignUpMenu();
                break;
            case "2":
                if (context.someoneIsLoggedIn())
                    chosenMenu = new SignOutMenu();
                else
                    chosenMenu = new SignInMenu();
                break;
            case "3":
                chosenMenu = new ProductCatalogMenu();
                break;
            case "4":
                chosenMenu = new MyOrdersMenu();
                break;
            case "5":
                chosenMenu = new SettingsMenu();
                break;
            case "6":
                chosenMenu = new CustomerListMenu();
                break;
        }
        return chosenMenu;
    }



}
