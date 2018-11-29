package com.w3engineers.jitpackbottomnav.data.model;

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
public class User {
    @Id
    public long userIndex = 0;
    public String userName;
    public String userId;
    public ToMany<Message> messages;
}
