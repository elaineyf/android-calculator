package com.example.calculator

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.sunset_grid.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.sunset_grid)
        var state = 1 //current operation: -1 if minus, 1 if plus, 10 if start
        //initialize state as 1 for the first inputted number
        var total = 0
        var display = 0
        val buttons = arrayOf(zero, one, two, three, four, five, six, seven, eight, nine)

        fun resetOpButtons(){
            plus.setBackgroundColor(resources.getColor(R.color.greyBlue2));
            minus.setBackgroundColor(resources.getColor(R.color.greyBlue2));
        }

        //Function that clears the screen
        fun clr(){
            display = 0
            state = 1
            total = 0
            bar.text = ""
            resetOpButtons()
        }

        //-------------- Number Buttons -----------------//
        fun pressNum(n: Int){
            when (state) {
                -1,1 -> {
                    resetOpButtons()
                }
                10 -> { //if number is pressed immediately after =, reset
                    clr()
                }
            }
            display = n + display*10
            bar.text = display.toString()
        }

        for ((index, value) in buttons.withIndex()) {
           value.setOnClickListener{
               pressNum(index)
           }
        }

        //-------------- Equal Button -----------------//

        //Function that calculates current total and updates with the answer
        fun eq(){
            if (state == -1){
                total -= display
                display = 0
            } else if(state == 1){
                total += display
                display = 0
            }
            bar.text = total.toString()
        }

        equal.setOnClickListener{
            resetOpButtons()
            eq()
            state = 10
        }

        equal.setOnLongClickListener{
            clr()
            Toast.makeText(this@MainActivity, "Cleared!",
                Toast.LENGTH_SHORT).show()
            true
        }

        //-------------- Operation Buttons -----------------//
        plus.setOnClickListener{
            if (state == -1 || state == 1){
                resetOpButtons()
                eq()
            }
            plus.setBackgroundColor(resources.getColor(R.color.greyBlue1))
            state = 1
            display = 0
        }

        minus.setOnClickListener{
            if (state == -1 || state == 1){
                resetOpButtons()
                eq()
            }
            minus.setBackgroundColor(resources.getColor(R.color.greyBlue1));
            state = -1
            display = 0
        }

        clear.setOnClickListener{
            clr()
            Toast.makeText(this@MainActivity, "Cleared!",
                Toast.LENGTH_SHORT).show()
        }


    }
}

