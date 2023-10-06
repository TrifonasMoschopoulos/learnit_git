public class SignOutMenu implements Menu{
    private static final String GOODBYE_MESSAGE = "Have a nice day! Look forward to welcoming back";
    private ApplicationContext context;

    {
        context = ApplicationContext.getInstance();
    }

    @Override
    public void start() {
        printMenuHeader();
        context.setLoggedInUser(null);  // log out the current user
    }

    @Override
    public void printMenuHeader() {
        System.out.println("***** Sign Out *****");
        System.out.println(GOODBYE_MESSAGE);
    }


    public boolean accessAllowed(){
        return context.someoneIsLoggedIn();
    }
}
