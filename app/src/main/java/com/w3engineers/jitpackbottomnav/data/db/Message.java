package com.w3engineers.jitpackbottomnav.data.db;

/*
 *  ****************************************************************************
 *  * Created by : Md. Azizul Islam on 11/28/2018 at 6:46 PM.
 *  * Email : azizul@w3engineers.com
 *  *
 *  * Purpose:
 *  *
 *  * Last edited by : Md. Azizul Islam on 11/28/2018.
 *  *
 *  * Last Reviewed by : <Reviewer Name> on <mm/dd/yy>
 *  ****************************************************************************
 */

import io.objectbox.annotation.Id;
import io.objectbox.relation.ToOne;

public class Message {
    @Id
    public long index;
    public String friendsId;
    public String messageId;
    public String message;
    public long time;
    public int status;

    public ToOne<User> user;

}
