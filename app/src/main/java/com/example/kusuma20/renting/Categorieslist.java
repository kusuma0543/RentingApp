package com.example.kusuma20.renting;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by kusuma20 on 23/6/17.
 */

public class Categorieslist implements Parcelable{
int id;
    String cid;
    String cname;
    String cimgurl;

    protected Categorieslist(Parcel in) {
        id = in.readInt();
        cid = in.readString();
        cname = in.readString();
        cimgurl = in.readString();
    }

    public static final Creator<Categorieslist> CREATOR = new Creator<Categorieslist>() {
        @Override
        public Categorieslist createFromParcel(Parcel in) {
            return new Categorieslist(in);
        }

        @Override
        public Categorieslist[] newArray(int size) {
            return new Categorieslist[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getCimgurl() {
        return cimgurl;
    }

    public void setCimgurl(String cimgurl) {
        this.cimgurl = cimgurl;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(cid);
        dest.writeString(cname);
        dest.writeString(cimgurl);
    }
}
