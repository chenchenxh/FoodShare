package com.foodsharetest.android.ui.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.foodsharetest.android.R;
import com.foodsharetest.android.db.model.Message;
import com.foodsharetest.android.ui.adapter.MessageAdapter;

import java.util.ArrayList;
import java.util.List;

public class HomePageFragment extends Fragment {
    private String[] data = {"1","12","123","1234562","123456","123456","123456","123456","123456","123456","123456","1234567"};
    private List<Message> messageList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_home_page, container, false);

        return view;
    }

}
