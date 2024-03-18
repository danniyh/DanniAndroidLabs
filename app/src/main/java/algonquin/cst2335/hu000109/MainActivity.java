package algonquin.cst2335.hu000109;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
/**
 * MainActivity is the entry point of the app that demonstrates how to check password complexity.
 * It includes input fields for the user to enter a password and a button to submit the password.
 * Upon submission, the app checks the complexity of the password and provides feedback.
 * @author Danni Hu
 * @version 1.0
 */
public class MainActivity extends AppCompatActivity {
    /**
     * TextView for displaying the result of password complexity check.
     */
    private TextView textView;
    /**
     * EditText for user to input their password.
     */
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
        editText = findViewById(R.id.editText);
        Button button = findViewById(R.id.button);

        button.setOnClickListener(clk -> {
            String password = editText.getText().toString();
            boolean isComplex = checkPasswordComplexity(password);

            if (isComplex) {
                textView.setText(R.string.password_meets_requirements);
            } else {
                textView.setText(R.string.password_does_not_meet_requirements);
                showToast();
            }
        });
    }


    /**
     * Checks the complexity of the entered password and returns true if it meets the requirements.
     *
     * @param password The password to be checked.
     * @return true if the password meets the complexity requirements, false otherwise.
     */
    private boolean checkPasswordComplexity(String password) {
        boolean hasUpperCase = !password.equals(password.toLowerCase());
        boolean hasLowerCase = !password.equals(password.toUpperCase());
        boolean hasDigit = password.matches(".*\\d.*");
        boolean hasSpecialChar = containsSpecialCharacter(password);

        return hasUpperCase && hasLowerCase && hasDigit && hasSpecialChar;
    }


    /**
     * Checks if the given password contains at least one special character.
     *
     * @param password The password to be checked
     * @return true if the password contains a special character, false otherwise
     */
    private boolean containsSpecialCharacter(String password) {
        for (char c : password.toCharArray()) {
            if (isSpecialCharacter(c)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Displays a Toast message indicating that the password is not complex enough.
     */
    private void showToast() {
        Toast.makeText(this, R.string.password_not_complex_enough, Toast.LENGTH_SHORT).show();
    }

    /**
     * Checks if the given character is a special character.This method determines whether the character passed as an argument is one of the specified special characters: #, $, %, ^, &, *, !, @, ?, ', ".
     * @param c The character to be checked
     * @return true if the character is a special character (#$%^&*!@?), false otherwise
     */
    private boolean isSpecialCharacter(char c) {
        switch (c) {
            case '#':
            case '$':
            case '%':
            case '^':
            case '&':
            case '*':
            case '!':
            case '@':
            case '?':
            case '.':
            case '\'':
            case '\"':
                return true;
            default:
                return false;
        }
    }
}