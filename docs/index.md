# KotPref -KotlinSharedPreferences-

![KotPref](logo.png)

*KotPref* is the `Kotlin` extension of `Android Shared Preferences`.

* Compatibility: Supports types that can be used in Shared Preferences. All types can be used by serializing.
* Modern: KotPref is Kotlin-first and uses modern libraries including AndroidX Preferences.

Koup is an acronym for: **Kot** Shared **Pref**erences.

## Download

KotPref is not available on `jcenter()`

```kotlin
repositories {
    maven(url = "http://sorrowblue.com/KotPref/repository")
}

dependencies {
    implementation("com.sorrowblue.android.kotPref:kotPref:1.0.0")
    kapt("com.sorrowblue.android.kotPref:compiler:1.0.0")
}
```

## Quick Start

```kotlin
sealed class UserPreference<T : Any>(default: T, key: String? = null, resId: Int? = null) : IKotPrefKey<T>(default, key, resId) {

    override val prefix = "user_settings-"

    object ID : UserPreference<Int>(-1, key = "user_id")
    object NAME : UserPreference<String>("", resId = R.string.preference_key_user_name)
}

class MainActivity : AppCompatActivity() {

    private var useId by sharedPreference(UserPreference.ID, commit = true)
    private var userName by sharedPreference(
        UserPreference.NAME,
        commit = false,
        name = "custom_prefferences_file"
    )

    fun loadData() {
        val userId = getSharedPreference(UserPreference.ID)
        val userName = getSharedPreference(UserPreference.NAME, isCommit = true)

        putSharedPreference(
            UserPreference.NAME,
            value = "sorrow blue",
            isCommit = false,
            name = "custom_prefferences_file"
        )
    }
}
```

Or the annotation `@KotPref` is easy

```kotlin
package myapplication.example

@KotPref(keyPrefix = "user_settings-", classRename = "UserPreference")
object UserInfo {

    @KotPrefKey(key = "user_id")
    const val ID = -1

    @KotPrefKey(resId = R.string.preference_key_user_name)
    const val NAME: String = ""
}
```

Generated in myapplication.example.**preference**

```kotlin
package myapplication.example.preference

sealed class UserPreference<T : Any>(default: T, key: String? = null, resId: Int? = null) :
    IKotPrefKey<T>(default, key, resId) {

    override val prefix = "user_settings-"

    object ID : UserPreference<Int>(myapplication.example.UserPreference.ID, key = "user_id")
    object NAME : UserPreference<String>(myapplication.example.UserPreference.NAME, resId = 2131491896)
}
```
