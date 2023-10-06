import java.util.Scanner;

public class SettingsMenu implements Menu{
    private static final String SETTINGS = "1. Change Password" + System.lineSeparator()
            + "2. Change Email";

    private static final String SETTINGS_MENU_ACCESS_DENIED_MESSAGE = "Please, log in or create new account to change your account settings";

    private ApplicationContext context;

    {
        context = ApplicationContext.getInstance();
    }


    @Override
    public void start() {
        if (context.someoneIsLoggedIn() == false){
            printMenuHeader();
            System.out.println(SETTINGS_MENU_ACCESS_DENIED_MESSAGE);
            return;
        }

        boolean closeSettingsMenu;
        do {
            printMenuHeader();
            closeSettingsMenu = true;

            System.out.println(SETTINGS);
            System.out.print(
                    "Please, enter option or type 'menu' to navigate back to the main menu: ");
            String inputUserOption = getUserInputAsString();   // get user's choice

            switch (inputUserOption) {
                case "1":  // change user's password
                    Menu changePasswordMenu = new ChangePasswordMenu();
                    changePasswordMenu.start();
                    break;
                case "2":  // change user's email
                    Menu changeEmailMenu = new ChangeEmailMenu();
                    changeEmailMenu.start();
                    break;
                case MainMenu.MENU_COMMAND:
                    break;
                default:
                    System.out.println("Only 1, 2 is allowed. Try one more time");
                    closeSettingsMenu = false;
            } //end of switch case
        } while (closeSettingsMenu == false);
    }


    @Override
    public void printMenuHeader() {
        System.out.println("***** SETTINGS *****");
    }



    private String getUserInputAsString(){
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        return input;
    }

    public boolean accessAllowed(){
        if (!context.someoneIsLoggedIn()){
            System.out.println(SETTINGS_MENU_ACCESS_DENIED_MESSAGE);
        }
        return context.someoneIsLoggedIn();
    }

}
