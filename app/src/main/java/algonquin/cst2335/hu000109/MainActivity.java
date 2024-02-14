package algonquin.cst2335.hu000109;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.w( "MainActivity", "In onCreate() - Loading Widgets" );

        // 在这里初始化 loginButton 并设置点击事件监听器
        Button loginButton = findViewById(R.id.login);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 获取电子邮件地址
                EditText emailEditText = findViewById(R.id.email);
                String emailAddress = emailEditText.getText().toString();

                // 创建Intent启动SecondActivity
                Intent nextPageIntent = new Intent(MainActivity.this, SecondActivity.class);
                nextPageIntent.putExtra("EmailAddress", "123@abc.com"); // 将电子邮件地址作为额外信息传递
                startActivity(nextPageIntent); // 启动SecondActivity
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.w( "MainActivity", "In onStart() - Registering Listeners" );
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.w( "MainActivity", "In onResume() - Starting the App" );
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.w( "MainActivity", "In onPause() - Stopping the App" );
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.w( "MainActivity", "In onStop() - Unregistering Listeners" );
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.w( "MainActivity", "In onDestroy() - Cleaning Up" );
    }

    Button loginButton = findViewById(R.id.login);


    }

