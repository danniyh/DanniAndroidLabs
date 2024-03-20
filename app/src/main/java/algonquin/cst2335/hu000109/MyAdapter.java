package algonquin.cst2335.hu000109;


import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import algonquin.cst2335.hu000109.databinding.ReceiveMessageBinding;
import algonquin.cst2335.hu000109.databinding.SentMessageBinding;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.BaseMessageViewHolder> {

    private final ArrayList<ChatMessage> messages;

    public MyAdapter(ArrayList<ChatMessage> messages) {
        this.messages = messages;
    }

    @NonNull
    @Override
    public BaseMessageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        if (viewType == MyAdapterItemType.SENT.ordinal()) {
            SentMessageBinding binding = DataBindingUtil.inflate(inflater, R.layout.sent_message, parent, false);
            return new SentMessageViewHolder(binding);
        } else {
            ReceiveMessageBinding binding = DataBindingUtil.inflate(inflater, R.layout.receive_message, parent, false);
            return new ReceiveMessageViewHolder(binding);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull BaseMessageViewHolder holder, int position) {
        holder.bind(messages.get(position));
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    @Override
    public int getItemViewType(int position) {
        return messages.get(position).isSentButton() ? MyAdapterItemType.SENT.ordinal() : MyAdapterItemType.RECEIVE.ordinal();
    }

    abstract static class BaseMessageViewHolder extends RecyclerView.ViewHolder {
        public BaseMessageViewHolder(ViewDataBinding binding) {
            super(binding.getRoot());
        }

        public abstract void bind(ChatMessage message);
    }

    public static class SentMessageViewHolder extends BaseMessageViewHolder {
        private final SentMessageBinding binding;

        public SentMessageViewHolder(SentMessageBinding binding) {
            super(binding);
            this.binding = binding;
        }

        @Override
        public void bind(ChatMessage message) {
            binding.setChatMessage(message);
            binding.executePendingBindings();
        }
    }

    private static class ReceiveMessageViewHolder extends BaseMessageViewHolder {
        private final ReceiveMessageBinding binding;

        public ReceiveMessageViewHolder(ReceiveMessageBinding binding) {
            super(binding);
            this.binding = binding;
        }

        @Override
        public void bind(ChatMessage message) {
            binding.setChatMessage(message);
            binding.executePendingBindings();
        }
    }

    private enum MyAdapterItemType {
        SENT, RECEIVE
    }
}