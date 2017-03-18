package com.tenjirawat.liveat500px.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.Toast;

import com.inthecheesefactory.thecheeselibrary.manager.Contextor;
import com.tenjirawat.liveat500px.R;
import com.tenjirawat.liveat500px.adapter.PhotoListAdapter;
import com.tenjirawat.liveat500px.dao.PhotoItemCollectionDAO;
import com.tenjirawat.liveat500px.manager.HttpManager;
import com.tenjirawat.liveat500px.manager.PhotoListManager;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by nuuneoi on 11/16/2014.
 */
public class MainFragment extends Fragment {
    private ListView listView;
    private PhotoListAdapter photoListAdapter;
    private SwipeRefreshLayout refreshLayout;

    public MainFragment() {
        super();
    }

    public static MainFragment newInstance() {
        MainFragment fragment = new MainFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        initInstances(rootView);
        return rootView;
    }

    private void initInstances(final View rootView) {
        // Init 'View' instance(s) with rootView.findViewById here
        listView = (ListView) rootView.findViewById(R.id.listview);
        photoListAdapter = new PhotoListAdapter();
        listView.setAdapter(photoListAdapter);
        refreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.swiperefresh);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                reloadData();
            }
        });
        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView absListView, int i) {

            }

            @Override
            public void onScroll(AbsListView absListView, int i, int i1, int i2) {
                refreshLayout.setEnabled(i == 0);

            }
        });


        reloadData();
    }

    private void reloadData() {
        Call<PhotoItemCollectionDAO> call = HttpManager.getInstance().getService().loadPhotoList();
        call.enqueue(new Callback<PhotoItemCollectionDAO>() {
            @Override
            public void onResponse(Call<PhotoItemCollectionDAO> call, Response<PhotoItemCollectionDAO> response) {
                refreshLayout.setRefreshing(false);
                if(response.isSuccessful()){
                    PhotoItemCollectionDAO dao = response.body();
//                    PhotoListManager.getInstance().setDao(dao);
                    photoListAdapter.setPhotoItemCollectionDAO(dao);
                    photoListAdapter.notifyDataSetChanged();
                    Toast.makeText(Contextor.getInstance().getContext(),dao.getData().get(0).getCaption(),Toast.LENGTH_LONG).show();
                }else{
                    //Handle
                    try {
                        Toast.makeText(Contextor.getInstance().getContext(),response.errorBody().string(),Toast.LENGTH_LONG).show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<PhotoItemCollectionDAO> call, Throwable t) {
                refreshLayout.setRefreshing(false);
                    //Handle
                Toast.makeText(Contextor.getInstance().getContext(),t.toString(),Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    /*
     * Save Instance State Here
     */
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // Save Instance State here
    }

    /*
     * Restore Instance State Here
     */
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState != null) {
            // Restore Instance State here
        }
    }
}
