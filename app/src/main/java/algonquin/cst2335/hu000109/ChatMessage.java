package algonquin.cst2335.hu000109;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class ChatMessage {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name ="message")
    private final String message;

    @ColumnInfo(name ="TimeSent")
    public final String timeSent;

    @ColumnInfo(name ="IsSentButton")
    private final boolean isSentButton;


    public ChatMessage(String message, String timeSent, boolean isSentButton) {
        this.message = message;
        this.timeSent = timeSent;
        this.isSentButton = isSentButton;
    }

    public String getMessage() {
        return message;
    }

    public String getTimeSent() {
        return timeSent;
    }

    public String getFormattedTime() {
        return timeSent;
    }

    public boolean isSentButton() {
        return isSentButton;
    }

    public static ChatMessage createSentMessage(String message) {
        String currentDateAndTime = getCurrentDateAndTime();
        return new ChatMessage(message, currentDateAndTime, true);
    }

    public static ChatMessage createReceiveMessage(String message) {
        String currentDateAndTime = getCurrentDateAndTime();
        return new ChatMessage(message, currentDateAndTime, false);
    }

    private static String getCurrentDateAndTime() {
        return String.valueOf(System.currentTimeMillis());
    }
}
