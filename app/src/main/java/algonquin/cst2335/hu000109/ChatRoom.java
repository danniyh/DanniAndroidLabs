package algonquin.cst2335.hu000109;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import algonquin.cst2335.hu000109.databinding.ActivityChatRoomBinding;

public class ChatRoom extends AppCompatActivity {

    private static final String DATE_FORMAT = "EEEE, dd-MMM-yyyy hh-mm-ss a";
    private ActivityChatRoomBinding binding;
    private MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityChatRoomBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ChatRoomViewModel chatModel = new ViewModelProvider(this).get(ChatRoomViewModel.class);

        ArrayList<ChatMessage> chatMessages = chatModel.messages.getValue();

        if (chatMessages == null) {
            chatMessages = new ArrayList<>();
            chatModel.messages.postValue(chatMessages);
        }

        myAdapter = new MyAdapter(chatMessages);
        binding.myRecycleView.setAdapter(myAdapter);
        binding.myRecycleView.setLayoutManager(new LinearLayoutManager(this));

        ArrayList<ChatMessage> finalChatMessages = chatMessages;
        binding.submitButton.setOnClickListener(click -> {
            String whatIsTyped = binding.editText.getText().toString();

            SimpleDateFormat sdf = new SimpleDateFormat("EEEE, dd-MMM-yyyy hh-mm-ss a", Locale.getDefault());
            String currentDateAndTime = sdf.format(new Date());

            if (!whatIsTyped.isEmpty()) {
                boolean isSentButton = true;
                finalChatMessages.add(new ChatMessage(whatIsTyped, currentDateAndTime, isSentButton));
                binding.editText.setText("");
                myAdapter.notifyItemInserted(finalChatMessages.size() - 1);
            }
        });


        binding.receiveButton.setOnClickListener(click -> {
            String receivedMessage = binding.editText.getText().toString();
            Date timeNow = new Date();

            SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT, Locale.getDefault());
            String currentDateAndTime = sdf.format(timeNow);

            boolean isSentButton = false;
            finalChatMessages.add(new ChatMessage(receivedMessage, currentDateAndTime, isSentButton));
            myAdapter.notifyItemInserted(finalChatMessages.size() - 1);
        });
    }
}