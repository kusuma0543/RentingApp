package com.example.kusuma20.renting;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by enchanterkusma on 6/7/17.
 */

public class Categoriesusers implements Parcelable{
    String uid;
    String uemail;
    String ename;
    String umobilenumber;

    protected Categoriesusers(Parcel in) {
        uid = in.readString();
        uemail = in.readString();
        ename = in.readString();
        umobilenumber = in.readString();
    }

    public static final Creator<Categoriesusers> CREATOR = new Creator<Categoriesusers>() {
        @Override
        public Categoriesusers createFromParcel(Parcel in) {
            return new Categoriesusers(in);
        }

        @Override
        public Categoriesusers[] newArray(int size) {
            return new Categoriesusers[size];
        }
    };

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUemail() {
        return uemail;
    }

    public void setUemail(String uemail) {
        this.uemail = uemail;
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public String getUmobilenumber() {
        return umobilenumber;
    }

    public void setUmobilenumber(String umobilenumber) {
        this.umobilenumber = umobilenumber;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(uid);
        parcel.writeString(uemail);
        parcel.writeString(ename);
        parcel.writeString(umobilenumber);
    }
}
