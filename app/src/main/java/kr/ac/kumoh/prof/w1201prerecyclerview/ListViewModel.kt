package kr.ac.kumoh.prof.w1201prerecyclerview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ListViewModel: ViewModel() {
    private val songs = ArrayList<String>()
    val list = MutableLiveData<ArrayList<String>>()

    init {
        list.value = songs
    }

    fun getList(): LiveData<ArrayList<String>> = list

    fun add(song: String) {
        songs.add(song)
        list.value = songs
    }

    fun getSong(i: Int) = songs[i]
    fun getSize() = songs.size
}