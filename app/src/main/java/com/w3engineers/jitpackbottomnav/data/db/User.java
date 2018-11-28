package com.w3engineers.jitpackbottomnav.data.db;

/*
 *  ****************************************************************************
 *  * Created by : Md. Azizul Islam on 11/28/2018 at 6:43 PM.
 *  * Email : azizul@w3engineers.com
 *  *
 *  * Purpose:
 *  *
 *  * Last edited by : Md. Azizul Islam on 11/28/2018.
 *  *
 *  * Last Reviewed by : <Reviewer Name> on <mm/dd/yy>
 *  ****************************************************************************
 */

import io.objectbox.annotation.Backlink;
import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;
import io.objectbox.relation.ToMany;

@Entity
public class User {
    @Id
    public long userId;
    public String userName;

    @Backlink(to = "user")
    public ToMany<Message> messages;
}
