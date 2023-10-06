import java.util.Scanner;

public class ChangeEmailMenu implements Menu{
    private ApplicationContext context;

    {
        context = ApplicationContext.getInstance();
    }

    @Override
    public void start() {
        printMenuHeader();
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter email: ");
        String newEmail = sc.nextLine();

        context.getLoggedInUser().setEmail(newEmail);
        System.out.println("Your email has been successfully changed");
    }

    @Override
    public void printMenuHeader() {
        System.out.println("***** CHANGE EMAIL *****");
    }


}
