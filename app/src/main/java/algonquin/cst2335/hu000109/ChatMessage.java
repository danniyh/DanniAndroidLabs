package algonquin.cst2335.hu000109;

public class ChatMessage {

    private final String message;
    private final String timeSent;
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
