package com.w3engineers.jitpackbottomnav.util

import java.text.SimpleDateFormat
import java.util.*


/*
*  ****************************************************************************
*  * Created by : Md. Azizul Islam on 1/2/2019 at 10:12 AM.
*  * Email : azizul@w3engineers.com
*  * 
*  * Purpose:
*  *
*  * Last edited by : Md. Azizul Islam on 1/2/2019.
*  * 
*  * Last Reviewed by : <Reviewer Name> on <mm/dd/yy>  
*  ****************************************************************************
*/

object TimeUtil {
    private val dateFormat9 = "hh:mm aa"
    fun getOnlyTime(milliSeconds: Long): String {
        val date = Date(milliSeconds)
        val format = SimpleDateFormat(dateFormat9, Locale.getDefault())
        format.timeZone = TimeZone.getDefault()
        return format.format(date)
    }
}
