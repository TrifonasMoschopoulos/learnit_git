import java.util.Scanner;

public class ChangePasswordMenu implements Menu{
    private ApplicationContext context;

    {
        context = ApplicationContext.getInstance();
    }

    @Override
    public void start() {
        printMenuHeader();
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter password: ");
        String newPassword = sc.nextLine();

        context.getLoggedInUser().setPassword(newPassword);
        System.out.println("Your password has been successfully changed");
    }


    @Override
    public void printMenuHeader() {
        System.out.println("***** CHANGE PASSWORD *****");
    }
}
