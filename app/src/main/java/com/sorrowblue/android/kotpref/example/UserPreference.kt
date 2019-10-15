package com.sorrowblue.android.kotpref.example

import com.sorrowblue.android.kotpref.annotation.KotPref
import com.sorrowblue.android.kotpref.annotation.KotPrefKey

@KotPref(keyPrefix = "user_", classRename = "UserPreference")
object UserPreference {

    @KotPrefKey(resId = R.string.preference_key_user_name)
    const val NAME: String = ""

    val TAGS: Set<String> = setOf("Apple", "Banana", "Cat")

    @KotPrefKey(key = "user_id")
    const val ID = 20191015

    const val ADMIN_MODE: Boolean = false

    const val RATE: Float = 0f

    const val EXPERIENCE_POINT: Long =  0

}
