package com.w3engineers.jitpackbottomnav.data.model;

import androidx.annotation.IntDef;
import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;
import io.objectbox.relation.ToOne;

/*
 *  ****************************************************************************
 *  * Created by : Md. Azizul Islam on 11/29/2018 at 6:57 PM.
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
public class Message {
    @Id
    public long id = 0;
    public String messageId;
    public String friendsId;
    public String message;
    public long time;
    public boolean incoming;

    public ToOne<User> user;
}
