package algonquin.cst2335.hu000109;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyRowHolder extends RecyclerView.ViewHolder {
    TextView messageText;
    TextView timeText;

    public MyRowHolder(@NonNull View itemView) {
        super(itemView);
        messageText = itemView.findViewById(R.id.message);
        timeText = itemView.findViewById(R.id.time);
    }
}
