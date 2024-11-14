import java.util.HashMap;

public class Bank {
    private HashMap<String, User> users;

    public Bank() {
        users = new HashMap<>();
        // Adding sample users
        users.put("Teddy", new User("Teddy", "0001", 2000.0));
        users.put("Sami", new User("Sami", "0002", 3200.0));
        users.put("Mamaru", new User("Mamaru", "0003", 6500.0));
        users.put("John", new User("John", "0004", 430.0));
    }

    public void addUser(User user) {
        users.put(user.getId(), user);
    }

    public User authenticateUser(String userId, String pin) {
        User user = users.get(userId);
        if (user != null && user.getPin().equals(pin)) {
            return user;
        }
        return null;
    }

    public User getUserById(String receiverId) {
        return users.get(receiverId);
    }
}