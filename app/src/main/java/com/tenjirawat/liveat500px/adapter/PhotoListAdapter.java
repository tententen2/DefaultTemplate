package com.tenjirawat.liveat500px.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.tenjirawat.liveat500px.dao.PhotoItemCollectionDAO;
import com.tenjirawat.liveat500px.dao.PhotoItemDAO;
import com.tenjirawat.liveat500px.manager.PhotoListManager;
import com.tenjirawat.liveat500px.view.PhotoListItem;

/**
 * Created by ten30 on 3/17/2017.
 */

public class PhotoListAdapter extends BaseAdapter {
    private PhotoItemCollectionDAO photoItemCollectionDAO;

    public PhotoItemCollectionDAO getPhotoItemCollectionDAO() {
        return photoItemCollectionDAO;
    }

    public void setPhotoItemCollectionDAO(PhotoItemCollectionDAO photoItemCollectionDAO) {
        this.photoItemCollectionDAO = photoItemCollectionDAO;
    }

    @Override
    public int getCount() {
        if(photoItemCollectionDAO == null){
            return 0;
        }
        if(photoItemCollectionDAO.getData() == null){
            return 0;
        }
        return photoItemCollectionDAO.getData().size();
    }

    @Override
    public Object getItem(int i) {
        return photoItemCollectionDAO.getData().get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

//    @Override
//    public int getViewTypeCount() {
//        return 2;
//    }
//
//    @Override
//    public int getItemViewType(int position) {
//        return position % 2 == 0 ? 0:1;
//    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        //view is recycle
//        if (getItemViewType(i) == 0) {
            PhotoListItem item;
            if (view != null) {
                item = (PhotoListItem) view;
            } else {
                item = new PhotoListItem(viewGroup.getContext());
            }

            PhotoItemDAO dao = (PhotoItemDAO) getItem(i);
            item.setNameText(dao.getCaption());
            item.setDescriptionText(dao.getUsername()+ "\n" + dao.getCamera());
            item.setImageUrl(dao.getImgUrl());

            return item;
//        }else{
//            TextView item;
//            if (view != null) {
//                item = (TextView) view;
//            } else {
//                item = new TextView(viewGroup.getContext());
//            }
//            item.setText("position"+i);
//            return item;
//        }
    }
}
