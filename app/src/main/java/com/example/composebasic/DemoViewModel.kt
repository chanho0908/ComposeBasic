package com.example.composebasic

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class DemoViewModel: ViewModel() {
    var isFahrenheit by mutableStateOf(true)
    var result by mutableStateOf("")

    fun converTemp(tmp: String){
        result = try {
            val tmpInt = tmp.toInt()

            if (isFahrenheit){
                ((tmpInt - 32) * 0.5556).toString()
            }else{
                ((tmpInt * 1.8) + 32).toString()
            }
        }catch (e: Exception){
            "Invalid Entry"
        }
    }

    fun swtichChange(){
        isFahrenheit = !isFahrenheit
    }
}