package com.sorrowblue.android.kotpref.example

import com.sorrowblue.android.kotpref.R
import com.sorrowblue.android.kotpref.annotation.KotPref
import com.sorrowblue.android.kotpref.annotation.KotPrefKey

@KotPref("user_settings-")
object UserPreference {

    @KotPrefKey(key = "user_id")
    const val ID = -1

    const val FLOAT: Float = 5.6f

    const val LONG: Long =  Long.MAX_VALUE

    const val BOOLEAN: Boolean = false

    @KotPrefKey(resId = R.string.preference_key_user_name)
    const val USER_NAME: String = ""

    val TAGS: Set<String> = setOf("a", "b", "b")

}
