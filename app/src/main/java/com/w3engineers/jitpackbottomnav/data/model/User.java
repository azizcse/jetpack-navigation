package com.w3engineers.jitpackbottomnav.data.model;

import android.os.Parcel;
import android.os.Parcelable;
import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;
import io.objectbox.relation.ToMany;

/*
 *  ****************************************************************************
 *  * Created by : Md. Azizul Islam on 11/29/2018 at 6:48 PM.
 *  * Email : azizul@w3engineers.com
 *  *
 *  * Purpose:
 *  *
 *  * Last edited by : Md. Azizul Islam on 11/29/2018.
 *  *
 *  * Last Reviewed by : <Reviewer Name> on <mm/dd/yy>
 *  ****************************************************************************
 */
@Entity
public class User implements Parcelable {
    @Id
    public long id;
    public String userName;
    public String userId;

    public User() {
    }

    protected User(Parcel in) {
        id = in.readLong();
        userName = in.readString();
        userId = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(userName);
        dest.writeString(userId);
    }


    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

}
