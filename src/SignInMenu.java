import java.util.Scanner;

public class SignInMenu implements Menu{
    private ApplicationContext context;
    private UserManagementService userManagementService;

    private static final String WELCOME_MESSAGE = "Glad to see you back %s %s %n";
    private static final String LOGGIN_ERROR_MESSAGE = "Unfortunately, such login and password doesn’t exist";

    {
        context = ApplicationContext.getInstance();
        userManagementService = DefaultUserManagementService.getInstance();
    }

    @Override
    public void start() {
        String inputEmail = getUserInput("Enter email: ");
        String inputPassword = getUserInput("Enter password: ");
        User user = userManagementService.getUserByEmail(inputEmail);     // we get the user who owns the input email

        if (correctPassword(user, inputPassword)){  // this method checks if the password is correct. Method returns false if user equals to null or wrong password given.
            System.out.printf(WELCOME_MESSAGE, user.getFirstName(),user.getLastName());
            context.setLoggedInUser(user);   // user is logged in
        }else{
            System.out.println(LOGGIN_ERROR_MESSAGE);
        }
    }

    @Override
    public void printMenuHeader() {
        System.out.println("***** Sign In *****");
    }


    private String getUserInput(String outputMessage){
        Scanner sc = new Scanner(System.in);
        System.out.print(outputMessage);
        String input = sc.nextLine();

        return input;
    }


    private boolean correctPassword(User user, String passwordToCheck){
        if (user == null){
            return false;
        }
        String correctPassword = user.getPassword();
        return correctPassword.equals(passwordToCheck);
    }


    public boolean accessAllowed(){
        return !context.someoneIsLoggedIn();
    }
}
