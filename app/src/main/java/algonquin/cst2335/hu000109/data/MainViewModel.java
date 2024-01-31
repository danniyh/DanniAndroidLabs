package algonquin.cst2335.hu000109.data;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
public class MainViewModel extends ViewModel {

    //4
    public MutableLiveData<String> editString = new MutableLiveData<>();


    //5
    private MutableLiveData<Boolean> isSelected = new MutableLiveData<>();

    public MutableLiveData<Boolean> getIsSelected() {
        return isSelected;
    }

    public void setIsSelected(boolean preference) {
        isSelected.setValue(preference);
    }


    //6
    private MutableLiveData<Boolean> imageClicked = new MutableLiveData<>();

}
