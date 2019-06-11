package com.rgs.capstone.ui.main;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.support.annotation.Nullable;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.rgs.capstone.Adapter;
import com.rgs.capstone.MainActivity;
import com.rgs.capstone.R;
import com.rgs.capstone.pojo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * A placeholder fragment containing a simple view.
 */
public class PlaceholderFragment extends Fragment {
    Adapter adapter;
    RecyclerView recyclerView;
    RequestQueue requestQueue;
    private ArrayList<pojo> list;
    int pos;
    ProgressBar progressBar;
   private static final String bussiness = "https://newsapi.org/v2/top-headlines?country=us&category=business&apiKey=b37f681a7f3442ba8f208ff0ce67b279";
    private static final String techcrunch = "https://newsapi.org/v2/top-headlines?sources=techcrunch&apiKey=b37f681a7f3442ba8f208ff0ce67b279";
    private static final String bitcoin = "https://newsapi.org/v2/everything?q=bitcoin&from=2019-05-11&sortBy=publishedAt&apiKey=b37f681a7f3442ba8f208ff0ce67b279";


    private static final String ARG_SECTION_NUMBER = "section_number";

    private PageViewModel pageViewModel;

    public static PlaceholderFragment newInstance(int index) {
        PlaceholderFragment fragment = new PlaceholderFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER, index);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageViewModel = ViewModelProviders.of(this).get(PageViewModel.class);
        int index = 1;
        if (getArguments() != null) {
            index = getArguments().getInt(ARG_SECTION_NUMBER);
        }
        pageViewModel.setIndex(index);
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);
        pos = getArguments().getInt(ARG_SECTION_NUMBER);
        progressBar = root.findViewById(R.id.progerss_bar);
        recyclerView = root.findViewById(R.id.recyclerview);
        adapter = new Adapter(getActivity());
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2));
        requestQueue = Volley.newRequestQueue(getActivity());
        json();

//        final TextView textView = root.findViewById(R.id.section_label);
//        pageViewModel.getText().observe(this, new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });
        return root;
    }
    public void json()
    {
        progressBar.setVisibility(View.VISIBLE);
        String url = bussiness;
        switch (pos)
        {
            case 1:
                url = bussiness;
                break;
            case 2:
                url = techcrunch;
                break;
            case 3:
                url = bitcoin;
                break;
        }
        String api = "b37f681a7f3442ba8f208ff0ce67b279";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                list = new ArrayList<>();
                try {
                    JSONArray jsonArray = response.getJSONArray("articles");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject news = jsonArray.getJSONObject(i);
                        JSONObject name = news.getJSONObject("source");
                        String author = name.getString("name");
                        String content = news.getString("content");
                        String imgae = news.getString("urlToImage");
                        String title = news.getString("title");
                        String url = news.getString("url");
                        String date = news.getString("publishedAt");
                        String description = news.getString("description");
                        list.add(new pojo(author,content,imgae,title,url,date,description));
                        Log.d("newx111" , String.valueOf(list.size()));
                    }
                    progressBar.setVisibility(View.INVISIBLE);
                    adapter.setList(list);
                    recyclerView.setAdapter(adapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Log.d("url" , String.valueOf(response));
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(jsonObjectRequest);
    }
}