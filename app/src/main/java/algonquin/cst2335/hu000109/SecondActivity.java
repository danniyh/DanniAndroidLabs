package algonquin.cst2335.hu000109;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class SecondActivity extends AppCompatActivity {
    private SharedPreferences prefs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        // Retrieve the Intent object that caused this transition
        Intent fromPrevious = getIntent();
        // Retrieve the email address from the Intent's extras
        String emailAddress = fromPrevious.getStringExtra("EmailAddress");
        int age = fromPrevious.getIntExtra("Age", 0); // 0 is the default value if "Age" is not found
        String name = fromPrevious.getStringExtra("Name");
        String postalCode = fromPrevious.getStringExtra("PostalCode");

        TextView welcomeTextView = findViewById(R.id.textViewTitle);
        welcomeTextView.setText("Welcome back " + emailAddress);


        Button callButton = findViewById(R.id.buttonCallNumber);
        EditText phoneNumberEditText = findViewById(R.id.editTextPhone);
        prefs = getSharedPreferences("MyData", Context.MODE_PRIVATE);

        // Retrieve the saved phone number from SharedPreferences
        String savedPhoneNumber = prefs.getString("PhoneNumber", "");

        // Pre-fill the phone number EditText with the saved value
        phoneNumberEditText.setText(savedPhoneNumber);

        callButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the phone number from the EditText
                String phoneNumber = phoneNumberEditText.getText().toString();

                // Create an Intent with ACTION_DIAL and set the phone number
                Intent call = new Intent(Intent.ACTION_DIAL);
                call.setData(Uri.parse("tel:" + phoneNumber));

                // Save the phone number to SharedPreferences
                SharedPreferences.Editor editor = prefs.edit();
                editor.putString("PhoneNumber", phoneNumber);
                editor.apply();

                // Start the phone call activity
                startActivity(call);
            }
        });

        Button changePictureButton = findViewById(R.id.buttonChangePicture);
        ImageView imageViewCamera = findViewById(R.id.imageViewCamera);

        // Check if the picture file exists
        File file = new File(getFilesDir(), "Picture.png");
        if (file.exists()) {
            // Load the saved image into the ImageView
            Bitmap theImage = BitmapFactory.decodeFile(file.getPath());
            imageViewCamera.setImageBitmap(theImage);
        }

        ActivityResultLauncher<Intent> cameraResult = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult o) {
                        if (o.getResultCode() == RESULT_OK) {
                            Intent data = o.getData();
                            Bitmap thumbnail = (Bitmap) data.getExtras().get("data");

                            FileOutputStream fOut = null;

                            try { fOut = openFileOutput("Picture.png", Context.MODE_PRIVATE);

                                thumbnail.compress(Bitmap.CompressFormat.PNG, 100, fOut);

                                fOut.flush();

                                fOut.close();

                            }

                            catch (FileNotFoundException e)

                            { e.printStackTrace();

                            } catch (IOException e) {
                                e.printStackTrace();
                            }


                            imageViewCamera.setImageBitmap(thumbnail);
                        }
                    }
                });

        changePictureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                cameraResult.launch(cameraIntent);
            }
        });


    }

    @Override
    protected void onPause() {
        super.onPause();

        EditText phoneEditText = findViewById(R.id.editTextPhone);
        String phoneNumber = phoneEditText.getText().toString();

        // Save the phone number to SharedPreferences
        prefs = getSharedPreferences("MyData", Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("PhoneNumber", phoneNumber);
        editor.apply();
    }
}