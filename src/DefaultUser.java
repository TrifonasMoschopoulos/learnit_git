public class DefaultUser implements User{
    private static int userCounter = 0;

    private int id;
    private String firstName;
    private String lastName;
    private String password;
    private String email;


    {
        id = ++userCounter;
    }

    public DefaultUser(){}

    public DefaultUser(String firstName, String lastName, String password, String email){
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.email = email;
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("First Name: " + getFirstName() + "\t\t");
        sb.append("Last Name: " + getLastName() + "\t\t");
        sb.append("Email: " + getEmail());

        return sb.toString();
    }

    public int getId() { return this.id; }
    public String getFirstName() { return this.firstName; }
    public String getLastName() { return this.lastName; }
    public String getPassword() { return password; }
    public String getEmail() { return email; }

    public void setPassword(String newPassword) {
        if (newPassword == null)
            return;
        this.password = newPassword;
    }

    public void setEmail(String newEmail) {
        if (newEmail == null)
            return;
        this.email = newEmail;
    }


    void clearState(){
        userCounter = 0;
    }
}
