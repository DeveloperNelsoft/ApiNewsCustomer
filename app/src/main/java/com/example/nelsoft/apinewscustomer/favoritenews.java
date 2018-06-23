package com.example.nelsoft.apinewscustomer;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.support.v4.app.FragmentTabHost;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Map;

public class favoritenews extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        ArrayList<String> newTitleList = new ArrayList<String>();

        View vi = inflater.inflate(R.layout.favoritenews, container, false);

        ListView view =  vi.findViewById(R.id.listViewFavoriteNews);

        SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);

        Map<String, ?> map = sharedPref.getAll();

        for (Map.Entry<String, ?> entry : map.entrySet()){
            newTitleList.add(entry.getValue().toString());
        }

        ArrayAdapter<String> arrayAdapter =
                new ArrayAdapter<String>(getActivity().getApplicationContext(), android.R.layout.simple_list_item_1, newTitleList);
        view.setAdapter(arrayAdapter);

       return vi;

    }

}