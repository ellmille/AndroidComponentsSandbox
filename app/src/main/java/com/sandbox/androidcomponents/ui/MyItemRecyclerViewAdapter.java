package com.sandbox.androidcomponents.ui;

import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.sandbox.androidcomponents.R;
import com.sandbox.androidcomponents.data.model.TestMessage;
import com.sandbox.androidcomponents.databinding.ListItemBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * {@link RecyclerView.Adapter} that can display a {@link TestMessage}
 */
public class MyItemRecyclerViewAdapter extends RecyclerView.Adapter<MyItemRecyclerViewAdapter.ViewHolder> {

    private List<TestMessage> mValues;

    @Nullable
    private final ItemClickCallback itemClickCallback;

    public MyItemRecyclerViewAdapter(@Nullable ItemClickCallback itemClickCallback) {
        this.itemClickCallback = itemClickCallback;
    }

    public void setList(final List<TestMessage> messageList){
        if(messageList != null){
            if(mValues == null){
                mValues = messageList;
                notifyItemRangeInserted(0, messageList.size());
            }else{
                DiffUtil.DiffResult result = DiffUtil.calculateDiff(new DiffUtil.Callback() {
                    @Override
                    public int getOldListSize() {
                        return mValues.size();
                    }

                    @Override
                    public int getNewListSize() {
                        return mValues.size();
                    }

                    @Override
                    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                        return mValues.get(oldItemPosition).getId() ==
                                messageList.get(newItemPosition).getId();
                    }

                    @Override
                    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                        TestMessage newMessage = messageList.get(newItemPosition);
                        TestMessage oldMessage = mValues.get(oldItemPosition);
                        return newMessage.getId() == oldMessage.getId()
                                && Objects.equals(newMessage.getMessage(), oldMessage.getMessage());
                    }
                });
                mValues = messageList;
                result.dispatchUpdatesTo(this);
                notifyItemInserted(messageList.size());
            }
        }else{
            mValues = new ArrayList<TestMessage>();
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ListItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.list_item, parent, false);
        binding.setCallback(itemClickCallback);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.binding.setMessage(mValues.get(position));
        holder.binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        final ListItemBinding binding;

        public ViewHolder(ListItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
