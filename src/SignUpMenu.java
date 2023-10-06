import java.util.Scanner;

public class SignUpMenu implements Menu{
    private UserManagementService userManagementService;
    private ApplicationContext context;

    {
        userManagementService = DefaultUserManagementService.getInstance();
        context = ApplicationContext.getInstance();
    }

    @Override
    public void start() {
        // take the input we need (firstname, lastname, password, email)
        String[] userData = getUserInput();

        User newUser = new DefaultUser(userData[0], userData[1], userData[2], userData[3]);
        String errorMessage = userManagementService.registerUser(newUser);

        if (errorMessage.isEmpty()){  // user is created successfully
            context.setLoggedInUser(newUser);  // user is logged in
            System.out.println("New user is created");
        }else{
            System.out.println(errorMessage);   // couldn't create user so we output the error.
        }

    }

    @Override
    public void printMenuHeader() {
        System.out.println("***** SIGN UP *****");
    }



    private String[] getUserInput(){
        String[] userData = new String[4];
        userData[0] = getUserInputAsString("Enter first name: ");
        userData[1] = getUserInputAsString("Enter last name: ");
        userData[2] = getUserInputAsString("Enter password: ");
        userData[3] = getUserInputAsString("Enter email: ");

        return userData;
    }

    private String getUserInputAsString(String outputMessage){
        Scanner sc = new Scanner(System.in);
        System.out.print(outputMessage);
        String userInput = sc.nextLine();
        return userInput;
    }


}
