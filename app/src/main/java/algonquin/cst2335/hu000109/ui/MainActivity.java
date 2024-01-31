package algonquin.cst2335.hu000109.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import algonquin.cst2335.hu000109.data.MainViewModel;

import androidx.lifecycle.ViewModelProvider;
//下面都是新增加的
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import algonquin.cst2335.hu000109.R;
import algonquin.cst2335.hu000109.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private MainViewModel model;
    private ActivityMainBinding variableBinding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        model = new ViewModelProvider(this).get(MainViewModel.class);

        variableBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(variableBinding.getRoot());

        //3+4
        if(variableBinding.mybutton != null) variableBinding.mybutton.setOnClickListener(click ->
        {
            model.editString.postValue(variableBinding.myedittext.getText().toString());

            model.editString.observe(this, s -> {
                variableBinding.textview.setText("Your edit text has: " + s);
            });
        });
        }
}