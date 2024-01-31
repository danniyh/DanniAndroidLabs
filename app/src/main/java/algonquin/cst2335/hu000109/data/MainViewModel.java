package algonquin.cst2335.hu000109.data;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.MutableLiveData;


public class MainViewModel extends ViewModel{

    public MutableLiveData<String> editString = new MutableLiveData<>();
    public String editString;
}
