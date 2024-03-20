package algonquin.cst2335.hu000109;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@Entity
public class ChatMessage {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name ="message")
    private final String message;

    @ColumnInfo(name ="TimeSent")
    public final String timeSent;

    @ColumnInfo(name ="IsSentButton")
    private final boolean isSent;

    public ChatMessage(String message, String timeSent, boolean isSent) {
        this.message = message;
        this.timeSent = timeSent;
        this.isSent = isSent;
    }

    public String getMessage() {
        return message;
    }

    public String getTimeSent() {
        return timeSent;
    }

    public String getFormattedTime() {
        return new SimpleDateFormat("EEEE, dd-MMM-yyyy hh-mm-ss a", Locale.getDefault()).format(new Date(Long.parseLong(timeSent)));
    }

    public boolean isSent() {
        return isSent;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static ChatMessage createSentMessage(String message, String timeSent) {
        return new ChatMessage(message, timeSent, true);
    }

    public static ChatMessage createReceiveMessage(String message, String timeSent) {
        return new ChatMessage(message, timeSent, false);
    }

    private static String getCurrentDateAndTime() {
        return String.valueOf(System.currentTimeMillis());
    }
}