package demo.solution.problemfirebase.utils;

public class EmailUtils {
    public static boolean isValidEmail(CharSequence email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    /*
     private boolean validateFields(Map<String, String> fields) {
        for (Map.Entry<String, String> entry : fields.entrySet()) {
            if (entry.getValue().isEmpty()) {
                Toast.makeText(EmailUtils.this, "Please enter " + entry.getKey(), Toast.LENGTH_SHORT).show();
                return false;
            }
        }
        return true;
    }
    * */




    /*
        Map<String, String> fields = new HashMap<>();
fields.put("Email", Email.getText().toString());
fields.put("Password", Password.getText().toString());
fields.put("Name", Name.getText().toString());
fields.put("Mobile", Mobile.getText().toString());
fields.put("Username", Username.getText().toString());

if (validateFields(fields)) {
        // Proceed with registration
    }
     */

}
