package com.example.sarthak.try1;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

/**
 * Created by sarthak on 9/11/15.
 */
public class Fragment1 extends Fragment {

    FragmentListener listener;

    public interface FragmentListener{
        public void showscore(int level);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try{
            listener=(FragmentListener)getActivity();
        }catch (ClassCastException e)
        {
            throw new ClassCastException(getActivity().toString());
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_1,container,false);

        ListView listview =(ListView)view.findViewById(R.id.listView);
        String [] values=new String[]{"Level 1","Level 2","Level 3"};
        ArrayAdapter<String> adapter;
        adapter = new ArrayAdapter<String>(getContext(), R.layout.rowlayout,values);
        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                listener.showscore(position);
            }
        });

        return view;
    }
}
