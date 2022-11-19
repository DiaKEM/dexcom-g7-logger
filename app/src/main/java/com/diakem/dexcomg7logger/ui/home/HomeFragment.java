package com.diakem.dexcomg7logger.ui.home;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Debug;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.diakem.dexcomg7logger.R;
import com.diakem.dexcomg7logger.database.DebugDAO;
import com.diakem.dexcomg7logger.database.DebugDatabase;
import com.diakem.dexcomg7logger.database.DebugEntity;
import com.diakem.dexcomg7logger.databinding.FragmentHomeBinding;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Executors;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        //final TextView textView = binding.textHome;
        //homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        Context context = this.getContext();
        DebugDatabase.getInstance(this.getContext()).getDebugDao().getAll(1000).observe(this.getViewLifecycleOwner(), new Observer<List<DebugEntity>>() {
            @Override
            public void onChanged(List<DebugEntity> debugEntities) {
                binding.myListView.setAdapter(new BaseAdapter() {
                    @Override
                    public int getCount() {
                        return debugEntities.size();
                    }

                    @Override
                    public Object getItem(int position) {
                        return null;
                    }

                    @Override
                    public long getItemId(int position) {
                        return 0;
                    }

                    @Override
                    public View getView(int position, View convertView, ViewGroup parent) {
                        View view = inflater.inflate(R.layout.custom_listview, null);
                        TextView id = (TextView)view.findViewById(R.id.customListView_id);
                        TextView message = (TextView)view.findViewById(R.id.customListView_message);
                        TextView creation = (TextView)view.findViewById(R.id.customListView_creation);
                        id.setText("#" + String.valueOf(debugEntities.get(position).id) + "    ");
                        message.setText(debugEntities.get(position).message.toString());
                        Timestamp ts=new Timestamp(debugEntities.get(position).creation);
                        Date date=new Date(ts.getTime());
                        creation.setText(date.toString());
                        return view;
                    }
                });
            }
        });
        return root;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}