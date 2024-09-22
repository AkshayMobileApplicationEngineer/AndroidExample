package demo.solution.problemfirebase.utils;

public class EmailUtils {
    public static boolean isValidEmail(CharSequence email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
}
