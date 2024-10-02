package demo.solution.problemfirebase.Model;

public class User1 {
    private String username;
    private String email;

    public User1() {
        // Default constructor required for Firebase calls
    }

    public User1(String username, String email) {
        this.username = username;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
