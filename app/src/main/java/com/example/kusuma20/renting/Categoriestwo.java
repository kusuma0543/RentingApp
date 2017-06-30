package com.example.kusuma20.renting;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by kusuma20 on 23/6/17.
 */

public class Categoriestwo  implements Parcelable{
   String cid;
    String id;
    String sid;
    String sname;
String stype;
    String scode;
    String sactualcost;
    String spickup;
    String sreturn;
    String simgurl;
    String srent;
    String scostdiscount;
    String stilldate;
    String slat;
    String slongi;

    protected Categoriestwo(Parcel in) {
        cid = in.readString();
        id = in.readString();
        sid = in.readString();
        sname = in.readString();
        stype = in.readString();
        scode = in.readString();
        sactualcost = in.readString();
        spickup = in.readString();
        sreturn = in.readString();
        simgurl = in.readString();
        srent = in.readString();
        scostdiscount = in.readString();
        stilldate = in.readString();
        slat = in.readString();
        slongi = in.readString();
    }

    public static final Creator<Categoriestwo> CREATOR = new Creator<Categoriestwo>() {
        @Override
        public Categoriestwo createFromParcel(Parcel in) {
            return new Categoriestwo(in);
        }

        @Override
        public Categoriestwo[] newArray(int size) {
            return new Categoriestwo[size];
        }
    };

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getStype() {
        return stype;
    }

    public void setStype(String stype) {
        this.stype = stype;
    }

    public String getScode() {
        return scode;
    }

    public void setScode(String scode) {
        this.scode = scode;
    }

    public String getSactualcost() {
        return sactualcost;
    }

    public void setSactualcost(String sactualcost) {
        this.sactualcost = sactualcost;
    }

    public String getSpickup() {
        return spickup;
    }

    public void setSpickup(String spickup) {
        this.spickup = spickup;
    }

    public String getSreturn() {
        return sreturn;
    }

    public void setSreturn(String sreturn) {
        this.sreturn = sreturn;
    }

    public String getSimgurl() {
        return simgurl;
    }

    public void setSimgurl(String simgurl) {
        this.simgurl = simgurl;
    }

    public String getSrent() {
        return srent;
    }

    public void setSrent(String srent) {
        this.srent = srent;
    }

    public String getScostdiscount() {
        return scostdiscount;
    }

    public void setScostdiscount(String scostdiscount) {
        this.scostdiscount = scostdiscount;
    }

    public String getStilldate() {
        return stilldate;
    }

    public void setStilldate(String stilldate) {
        this.stilldate = stilldate;
    }

    public String getSlat() {
        return slat;
    }

    public void setSlat(String slat) {
        this.slat = slat;
    }

    public String getSlongi() {
        return slongi;
    }

    public void setSlongi(String slongi) {
        this.slongi = slongi;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(cid);
        dest.writeString(id);
        dest.writeString(sid);
        dest.writeString(sname);
        dest.writeString(stype);
        dest.writeString(scode);
        dest.writeString(sactualcost);
        dest.writeString(spickup);
        dest.writeString(sreturn);
        dest.writeString(simgurl);
        dest.writeString(srent);
        dest.writeString(scostdiscount);
        dest.writeString(stilldate);
        dest.writeString(slat);
        dest.writeString(slongi);
    }
}
