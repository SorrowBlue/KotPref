package com.sorrowblue.android.kotpref.example

import android.app.Application
import android.view.View
import android.widget.AdapterView
import androidx.lifecycle.*
import com.sorrowblue.android.kotpref.example.preference.UserPreference
import com.sorrowblue.android.kotpref.sharedPreference

class MainViewModel(application: Application) : AndroidViewModel(application), LifecycleObserver {

    private var userid by sharedPreference(UserPreference.ID)
    private var username by sharedPreference(UserPreference.NAME)
    private var usertags by sharedPreference(UserPreference.TAGS)
    private var useradminmode by sharedPreference(UserPreference.ADMIN_MODE)
    private var userrate by sharedPreference(UserPreference.RATE)
    private var userexperieencepoint by sharedPreference(UserPreference.EXPERIENCE_POINT)
    private var position = 0

    val userName = MutableLiveData<String>(username)
    val userId = MutableLiveData<String>(userid.toString())
    val userTags = MutableLiveData<List<String>>(usertags.toList())
    val userTag = MutableLiveData<String>("")
    val adminMode = MutableLiveData<Boolean>(useradminmode)
    val userRate = MutableLiveData<String>(userrate.toString())
    val experieencePoint = MutableLiveData<String>(userexperieencepoint.toString())
    val message = MutableLiveData<String>()

    fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        userTag.value = userTags.value?.get(position)
        this.position = position
    }

    fun add() {
        val tag = userTag.value ?: return
        val set = userTags.value?.toMutableSet() ?: return
        if (set.add(tag)) {
            message.value = "$tag has been added."
        } else {
            message.value = "$tag is already there."
        }
        userTags.value = set.toList()
    }

    fun remove() {
        val list = userTags.value?.toMutableList() ?: return
        list.removeAt(position).also {
            message.value = "$it has been removed."
        }
        userTags.value = list
    }

    fun update() {
        val tag = userTag.value ?: return
        val list = userTags.value?.toMutableList() ?: return
        if (list.contains(tag)) {
            message.value = "$tag is already there."
        } else {
            list.set(position, tag).also {
                message.value = "$it has been updated to $tag."
            }
            userTags.value = list
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun saveData() {
        adminMode.value?.let { useradminmode = it }
        userTags.value?.toSet()?.also { usertags = it }
        userName.value?.also { username = it }
        experieencePoint.value?.toLongOrNull()?.also { userexperieencepoint = it }
        userRate.value?.toFloatOrNull()?.also { userrate = it }
        userId.value?.toIntOrNull()?.also { userid = it }
    }

}
