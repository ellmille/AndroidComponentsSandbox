package com.sandbox.androidcomponents.ui;


import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sandbox.androidcomponents.R;
import com.sandbox.androidcomponents.databinding.FragmentMessageBinding;
import com.sandbox.androidcomponents.viewmodel.MessageViewModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class MessageFragment extends Fragment {
    private static final String KEY_MESSAGE_ID = "message_id";
    private FragmentMessageBinding binding;

    public MessageFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_message, container, false);
        // Inflate the layout for this fragment
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        final MessageViewModel model = ViewModelProviders.of(this)
                .get(MessageViewModel.class);

        binding.setViewmodel(model);
    }

    /**
     * Statically creates {@link Fragment} for a specific {@link com.sandbox.androidcomponents.data.model.TestMessage}.
     */
    public static MessageFragment forMessage(int messageId){
        MessageFragment fragment = new MessageFragment();
        Bundle args = new Bundle();
        args.putInt(KEY_MESSAGE_ID, messageId);
        fragment.setArguments(args);
        return fragment;
    }
}
