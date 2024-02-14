package algonquin.cst2335.hu000109;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private static String TAG = "MainActivity";
    @Override
    protected void onResume() {
        super.onResume();
        Log.w( TAG, "In onResume() - The application is now responding to user input" );
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.w( TAG, "In onStart() - The application is now visible on screen" );
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.w( TAG, "In onStop() - The application is no longer visible." );
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.w( TAG, "In onDestroy() - Any memory used by the application is freed." );
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.w( TAG, "In onPause() - The application no longer responds to user input" );
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.w( TAG, "In onCreate() - Loading Widgets" );
        SharedPreferences prefs = getSharedPreferences("MyData", Context.MODE_PRIVATE);

        Button loginButton = findViewById(R.id.buttonLogin);
        TextView editTextEmail = findViewById(R.id.editTextEmail);

        String savedEmailAddress = prefs.getString("LoginName", "");
        editTextEmail.setText(savedEmailAddress);

        loginButton.setOnClickListener(v -> {
            String emailAddress = editTextEmail.getText().toString();

            // Save the email address to SharedPreferences
            SharedPreferences.Editor editor = prefs.edit();
            editor.putString("LoginName", emailAddress);
            editor.putFloat("Hi",4.5f);
            editor.putInt("Age",32);
            editor.apply();


            // Create an Intent and add the email address as an extra
            Intent nextPage = new Intent(MainActivity.this, SecondActivity.class);
            nextPage.putExtra("EmailAddress", emailAddress);

            // Add additional data
            int age = 32; // replace with the actual age value
            String name = "Danni"; // replace with the actual name value
            String postalCode = "K2G 6C3"; // replace with the actual postal code value

            nextPage.putExtra("Age", age);
            nextPage.putExtra("Name", name);
            nextPage.putExtra("PostalCode", postalCode);
            // Start the transition to SecondActivity
            startActivity(nextPage);
        });
    }


}