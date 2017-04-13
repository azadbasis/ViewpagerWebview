package volleycom.azhar.volleyrecyclerviewmodel001.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Azhar on 4/11/2017.
 */

public class Employee implements Parcelable {

    public int postId;
    public int id;
    public String name;
    public String email;
    public String body;


    public Employee(){}

    protected Employee(Parcel in) {
        postId = in.readInt();
        id = in.readInt();
        name = in.readString();
        email = in.readString();
        body = in.readString();
    }

    public static final Creator<Employee> CREATOR = new Creator<Employee>() {
        @Override
        public Employee createFromParcel(Parcel in) {
            return new Employee(in);
        }

        @Override
        public Employee[] newArray(int size) {
            return new Employee[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(postId);
        parcel.writeInt(id);
        parcel.writeString(name);
        parcel.writeString(email);
        parcel.writeString(body);
    }
}
