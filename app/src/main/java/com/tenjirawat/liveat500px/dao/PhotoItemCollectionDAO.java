package com.tenjirawat.liveat500px.dao;

import com.google.gson.annotations.SerializedName;
import com.tenjirawat.liveat500px.view.PhotoListItem;

import java.util.List;

/**
 * Created by ten30 on 3/18/2017.
 */

public class PhotoItemCollectionDAO {
    @SerializedName("success") private boolean success;
    @SerializedName("data") private List<PhotoItemDAO> data;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<PhotoItemDAO> getData() {
        return data;
    }

    public void setData(List<PhotoItemDAO> data) {
        this.data = data;
    }
}
