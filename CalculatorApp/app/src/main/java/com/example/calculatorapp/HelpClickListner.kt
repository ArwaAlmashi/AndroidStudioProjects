package com.example.calculatorapp

import android.view.View
import android.widget.TextView
import android.widget.Toast

class HelpClickListner {

    fun onClickNumber(v: View?, tv: TextView) {
        val view = v?.rootView
        when (v?.id) {
            R.id.oneButton -> tv.text = "${tv.text}1"
            R.id.twoButton -> tv.text = "${tv.text}2"
            R.id.threeButton -> tv.text = "${tv.text}3"
            R.id.fourButton -> tv.text = "${tv.text}4"
            R.id.fiveButton -> tv.text = "${tv.text}5"
            R.id.sixButton -> tv.text = "${tv.text}6"
            R.id.sevenButton -> tv.text = "${tv.text}7"
            R.id.eightButton -> tv.text = "${tv.text}8"
            R.id.nineButton -> tv.text = "${tv.text}9"
            R.id.zeroButton -> tv.text = "${tv.text}0"
            R.id.dotButton -> tv.text ="${tv.text}."
        }
    }

}