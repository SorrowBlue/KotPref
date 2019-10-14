package com.sorrowblue.android.kotpref.example

import android.app.Application
import android.view.View
import android.widget.AdapterView
import androidx.lifecycle.*
import com.sorrowblue.android.kotpref.example.preference.UserPreference
import com.sorrowblue.android.kotpref.sharedPreference

class MainViewModel(application: Application) : AndroidViewModel(application), LifecycleObserver,
    AdapterView.OnItemSelectedListener {

    private var userid by sharedPreference(UserPreference.ID)
    private var username by sharedPreference(UserPreference.USER_NAME)
    private var usertags by sharedPreference(UserPreference.TAGS)
    private var userchecked by sharedPreference(UserPreference.BOOLEAN)
    private var userfloat by sharedPreference(UserPreference.FLOAT)
    private var userlong by sharedPreference(UserPreference.LONG)
    private var position = 0

    val userName = MutableLiveData<String>(username)
    val userId = MutableLiveData<String>(userid.toString())
    val userTags = MutableLiveData<List<String>>(usertags.toList())
    val userTag = MutableLiveData<String>("")
    val userChecked = MutableLiveData<Boolean>(userchecked)
    val userFloat = MutableLiveData<String>(userfloat.toString())
    val userLong = MutableLiveData<String>(userlong.toString())
    val message = MutableLiveData<String>()

    override fun onNothingSelected(parent: AdapterView<*>?) = Unit
    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
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

    fun delete() {
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
        userChecked.value?.let { userchecked = it }
        userTags.value?.toSet()?.also { usertags = it }
        userName.value?.also { username = it }
        userLong.value?.toLongOrNull()?.also { userlong = it }
        userFloat.value?.toFloatOrNull()?.also { userfloat = it }
        userId.value?.toIntOrNull()?.also { userid = it }
    }

}
