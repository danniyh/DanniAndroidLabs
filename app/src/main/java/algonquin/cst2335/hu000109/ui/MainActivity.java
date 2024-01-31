package algonquin.cst2335.hu000109.ui;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

//2


//2
import algonquin.cst2335.hu000109.databinding.ActivityMainBinding;

//4
import algonquin.cst2335.hu000109.data.MainViewModel;

import androidx.lifecycle.ViewModelProvider;



//5
import android.widget.Toast;





public class MainActivity extends AppCompatActivity {


    private ActivityMainBinding variableBinding;//2
    private MainViewModel model;//4

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //2
        variableBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(variableBinding.getRoot());


        //3+4
        if(variableBinding.mybutton != null) variableBinding.mybutton.setOnClickListener(click ->
        {
            model.editString.postValue(variableBinding.myedittext.getText().toString());

            model.editString.observe(this,s -> {
                variableBinding.textview.setText( "Your edit text has: " + s);
            });


        });

        //4
        model = new ViewModelProvider(this).get(MainViewModel.class);


        //5


        model.getIsSelected().observe(this, selected ->{
            variableBinding.checkBox.setChecked(selected);
            variableBinding.radioButton.setChecked(selected);
            variableBinding.switchButton.setChecked(selected);
            showToast("The value is now: " + selected);
        });



        variableBinding.switchButton.setOnCheckedChangeListener((buttonView, isChecked) -> model.getIsSelected().postValue(isChecked));

        variableBinding.radioButton.setOnCheckedChangeListener((buttonView, isChecked) -> model.setIsSelected(isChecked));

        variableBinding.checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> model.setIsSelected(isChecked));


        //6
        variableBinding.imageView.setOnClickListener(v -> showToast("ImageView clicked"));

        variableBinding.myimagebutton.setOnClickListener(v->
        {
            showToast("ImageButton clicked");
            int width = v.getWidth();
            int height = v.getHeight();
            showToast("The width = " + width + " and height = " + height);
        });
    }


    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }


}