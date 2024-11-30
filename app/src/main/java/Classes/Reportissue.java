package Classes;

import android.os.Parcel;
import android.os.Parcelable;

public class Reportissue implements Parcelable {

    private String brandname;
    private String modelno;
    private String description;
    private String username;
    private String phno;
    private String address;
    private String vehicleno;
    private String issuetitile;
    private String yearofmani;
    private String longitude;
    private String latitude;
    private boolean twowheeler;
    private boolean fourwheeler;
    private String imageuri;

    // Constructors
    public Reportissue() {
        // Default constructor
    }

    public Reportissue(String brandname, String modelno, String description, boolean twowheeler, boolean fourwheeler,
                       String imageuri, String username, String phno, String vehicleno, String issuetitile) {
        this.brandname = brandname;
        this.modelno = modelno;
        this.description = description;
        this.twowheeler = twowheeler;
        this.fourwheeler = fourwheeler;
        this.imageuri = imageuri;
        this.username = username;
        this.phno = phno;
        this.vehicleno = vehicleno;
        this.issuetitile = issuetitile;
    }

    // Parcelable implementation
    protected Reportissue(Parcel in) {
        brandname = in.readString();
        modelno = in.readString();
        description = in.readString();
        twowheeler = in.readByte() != 0;
        fourwheeler = in.readByte() != 0;
        imageuri = in.readString();
        vehicleno = in.readString();
        issuetitile = in.readString();
        longitude = in.readString();
        address = in.readString();
        latitude = in.readString();
        username = in.readString();
        yearofmani = in.readString();
        phno = in.readString();
    }

    public static final Creator<Reportissue> CREATOR = new Creator<Reportissue>() {
        @Override
        public Reportissue createFromParcel(Parcel in) {
            return new Reportissue(in);
        }

        @Override
        public Reportissue[] newArray(int size) {
            return new Reportissue[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(brandname);
        dest.writeString(modelno);
        dest.writeString(description);
        dest.writeString(yearofmani);
        dest.writeString(vehicleno);
        dest.writeString(address);
        dest.writeString(issuetitile);
        dest.writeByte((byte) (twowheeler ? 1 : 0));
        dest.writeByte((byte) (fourwheeler ? 1 : 0));
        dest.writeString(imageuri);
        dest.writeString(username);
        dest.writeString(phno);
        dest.writeString(latitude);
        dest.writeString(longitude);
    }

    // Getters and setters
    public String getBrandname() {
        return brandname;
    }

    public void setBrandname(String brandname) {
        this.brandname = brandname;
    }

    public String getModelno() {
        return modelno;
    }

    public void setModelno(String modelno) {
        this.modelno = modelno;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhno() {
        return phno;
    }

    public void setPhno(String phno) {
        this.phno = phno;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getVehicleno() {
        return vehicleno;
    }

    public void setVehicleno(String vehicleno) {
        this.vehicleno = vehicleno;
    }

    public String getIssuetitile() {
        return issuetitile;
    }

    public void setIssuetitile(String issuetitile) {
        this.issuetitile = issuetitile;
    }

    public String getYearofmani() {
        return yearofmani;
    }

    public void setYearofmani(String yearofmani) {
        this.yearofmani = yearofmani;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public boolean isTwowheeler() {
        return twowheeler;
    }

    public void setTwowheeler(boolean twowheeler) {
        this.twowheeler = twowheeler;
    }

    public boolean isFourwheeler() {
        return fourwheeler;
    }

    public void setFourwheeler(boolean fourwheeler) {
        this.fourwheeler = fourwheeler;
    }

    public String getImageuri() {
        return imageuri;
    }

    public void setImageuri(String imageuri) {
        this.imageuri = imageuri;
    }
}
