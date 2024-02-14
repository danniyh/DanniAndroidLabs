package algonquin.cst2335.hu000109;


import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {

    private ImageView imageViewCamera;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        imageViewCamera = findViewById(R.id.imageViewCamera);

        // 获取从MainActivity传递过来的Intent
        Intent fromPrevious = getIntent();
        String emailAddress = fromPrevious.getStringExtra("EmailAddress");

        // 在TextView中显示欢迎信息
        TextView welcomeTextView = findViewById(R.id.textView);
        if (emailAddress != null && !emailAddress.isEmpty()) {
            welcomeTextView.setText("Welcome back " + emailAddress);
        } else {
            welcomeTextView.setText("Welcome!");
        }

        // 获取 "CALL NUMBER" 按钮
        Button callButton = findViewById(R.id.buttonCall);
        callButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: 在这里编写拨打电话的实际代码
                Toast.makeText(SecondActivity.this, "Call number button clicked", Toast.LENGTH_LONG).show();
            }
        });

        // 获取 "CHANGE PICTURE" 按钮
        Button changePictureButton = findViewById(R.id.buttonChangePicture);
        changePictureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: 在这里编写更换图片的实际代码
                Toast.makeText(SecondActivity.this, "Change picture button clicked", Toast.LENGTH_LONG).show();
            }
        });
    }

    Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);



       ActivityResultLauncher<Intent> cameraResult = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            if (result.getResultCode() == Activity.RESULT_OK) {
                Intent data = result.getData();
                if (data != null && data.getExtras() != null) {
                    Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
                    imageViewCamera.setImageBitmap(thumbnail);
                }
            }
        }
    });

};