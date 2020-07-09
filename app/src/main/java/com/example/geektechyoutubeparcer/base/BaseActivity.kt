package com.example.geektechyoutubeparcer.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity(private val layout: Int) : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout)
        setupUI()
        setupLiveData()
    }

    abstract fun setupUI() // внтури этого метода мы инициализируем все view
    abstract fun setupLiveData() // внтури этого метода мы инициализируем все livedata

    fun showDisconnectState() {
        //отображение фрагмента без состояния интернета
    }


}