package algonquin.cst2335.hu000109;


import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.room.Room;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import algonquin.cst2335.hu000109.databinding.ActivityChatRoomBinding;


//以下是新增
import android.app.AlertDialog;
import android.view.Menu;
import android.view.MenuItem;

import android.widget.Toast;

import androidx.annotation.NonNull;


public class ChatRoom extends AppCompatActivity {

    private ArrayList<ChatMessage> messages;

    private static final String DATABASE_NAME = "chat_message_db";
    private static final String DATE_FORMAT = "EEEE, dd-MMM-yyyy hh:mm-ss a";
    private ActivityChatRoomBinding binding;
    private MyAdapter myAdapter;
    private ChatMessageDAO mDAO;

    private ExecutorService executorService = Executors.newSingleThreadExecutor();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_room);
        binding = ActivityChatRoomBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initializeDatabaseAndAdapter();
        setupMessageSendListeners();


        setSupportActionBar(binding.myToolbar);
    }

    private void initializeDatabaseAndAdapter() {
        mDAO = Room.databaseBuilder(getApplicationContext(), MessageDatabase.class, DATABASE_NAME)
                .build()
                .cmDAO();
        myAdapter = new MyAdapter(this, new ArrayList<>());
        binding.myRecycleView.setLayoutManager(new LinearLayoutManager(this));
        binding.myRecycleView.setAdapter(myAdapter);

        loadMessagesFromDatabase();
    }

    private void loadMessagesFromDatabase() {
        // Use the executorService to fetch messages from the database
        executorService.execute(() -> {
            ArrayList<ChatMessage> messages = new ArrayList<>(mDAO.getAllMessages());
            runOnUiThread(() -> myAdapter.setMessages(messages));
        });
    }

    private void setupMessageSendListeners() {
        binding.submitButton.setOnClickListener(click -> sendMessage(true));
        binding.receiveButton.setOnClickListener(click -> sendMessage(false));
    }

    private void sendMessage(boolean isSent) {
        String messageText = binding.editText.getText().toString().trim();
        if (!messageText.isEmpty()) {
            addMessageToDatabase(messageText, isSent);
            binding.editText.setText("");
        }
    }

    private void addMessageToDatabase(String text, boolean isSent) {
        String currentDateAndTime = getCurrentDateAndTime();
        ChatMessage chatMessage = isSent ? ChatMessage.createSentMessage(text, currentDateAndTime)
                : ChatMessage.createReceiveMessage(text, currentDateAndTime);

        executorService.execute(() -> {
            mDAO.insertMessage(chatMessage);
            runOnUiThread(() -> {
                myAdapter.addMessage(chatMessage);
                binding.myRecycleView.scrollToPosition(myAdapter.getItemCount() - 1);
            });
        });
    }

    private String getCurrentDateAndTime() {
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT, Locale.getDefault());
        return sdf.format(new Date());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        executorService.shutdownNow(); // Ensure proper shutdown of the executor service
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.my_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_item1) {
            showDeleteAllMessagesDialog();
            return true;
        } else if (id == R.id.action_item2) {
            showToastAbout();
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    private void showDeleteAllMessagesDialog() {
        new AlertDialog.Builder(this)
                .setTitle("Delete All Messages")
                .setMessage("Are you sure you want to delete all messages?")
                .setPositiveButton("Delete", (dialog, which) -> {
                    executorService.execute(() -> {
                        mDAO.deleteAll();
                        runOnUiThread(() -> {
                            int size = messages.size();
                            messages.clear();
                            myAdapter.notifyItemRangeRemoved(0, size);
                            Toast.makeText(ChatRoom.this, "All messages deleted", Toast.LENGTH_SHORT).show();
                        });
                    });
                })
                .setNegativeButton("Cancel", null)
                .show();
    }

    private void showToastAbout() {
        Toast.makeText(this, "Version 1.0, created by Danni Hu", Toast.LENGTH_SHORT).show();
    }


}


