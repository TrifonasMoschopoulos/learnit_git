import java.util.Arrays;

public class DefaultUserManagementService implements UserManagementService{
    private static final String NOT_UNIQUE_EMAIL_ERROR_MESSAGE = "This email is already used by another user. Please, use another email";
    private static final String EMPTY_EMAIL_ERROR_MESSAGE = "You have to input email to register. Please, try one more time";
    private static final String NO_ERROR_MESSAGE = "";

    private static final int DEFAULT_USERS_CAPACITY = 10;

    private static DefaultUserManagementService instance;
    private User[] users;
    private int numberOfUsers;

    private DefaultUserManagementService(){
        users = new User[DEFAULT_USERS_CAPACITY];
        numberOfUsers = 0;
    }

    public String registerUser(User user){
        if (emptyOrNullEmail(user.getEmail())){
            return EMPTY_EMAIL_ERROR_MESSAGE;
        }
        if (getUserByEmail(user.getEmail()) != null){
            return NOT_UNIQUE_EMAIL_ERROR_MESSAGE;
        }

        // We can add new user!
        if (users.length <= numberOfUsers){
            users = Arrays.copyOf(users, users.length << 1);
        }
        users[numberOfUsers++] = user;
        return NO_ERROR_MESSAGE;
    }


    public static UserManagementService getInstance(){
        if (instance == null){
            instance = new DefaultUserManagementService();
        }
        return instance;
    }


    public User[] getUsers(){
        User[] usersArray = new User[numberOfUsers];
        for (int i=0; i<numberOfUsers; i++){
            usersArray[i] = users[i];
        }
        return usersArray;
    }



    public boolean emptyOrNullEmail(String email){
        if (email == null || email.isEmpty()){
            return true;
        }
        return false;
    }


    public User getUserByEmail(String userEmail){
        if (emptyOrNullEmail(userEmail)){
            return null;
        }
        for (int i=0; i<numberOfUsers; i++){
            String email = users[i].getEmail();
            if (email.equals(userEmail)){
                    return users[i];
            }
        }
        return null;
    }



    public void clearServiceState() {
        instance = null;
    }

}
