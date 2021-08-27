package com.noureddine10kh.calculator_application

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.noureddine10kh.calculator_application.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var oldNumber :String
    lateinit var historyNumber :String
    lateinit var newTextResult :String
    lateinit var newDigit :String
    var operationSign : Operation? = null
    var result :Double? =null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        clear()
        makeOperation()
        displayResult()
    }

    fun makeOperation (){
        binding.plusOper.setOnClickListener {
            doOperation(Operation.plus,newTextResult)
        }
        binding.minOper.setOnClickListener {
            doOperation(Operation.minus,newTextResult)
        }
        binding.divOper.setOnClickListener {
            doOperation(Operation.division,newTextResult)
        }
        binding.multiOper.setOnClickListener {
            doOperation(Operation.multiplication,newTextResult)
        }
    }
    fun doOperation (operation: Operation,text:String){
        if (text!=""){
            historyNumber = text
            operationSign = operation
            clearTextResult()
        }else {
            Toast.makeText(this,"you must enter a number !!",Toast.LENGTH_SHORT).show()
        }
    }

    fun displayResult() {
        binding.equalBtn.setOnClickListener {
            if (historyNumber!="" && newTextResult!= ""){
                when(operationSign){
                    Operation.plus -> {
                        result = historyNumber.toDouble() + newTextResult.toDouble()
                        binding.result.text = result.toString()
                    }
                    Operation.minus -> {
                        result = historyNumber.toDouble() - newTextResult.toDouble()
                        binding.result.text = result.toString()
                    }
                    Operation.multiplication -> {
                        result = historyNumber.toDouble() * newTextResult.toDouble()
                        binding.result.text = result.toString()
                    }
                    Operation.division -> {
                        result = historyNumber.toDouble() / newTextResult.toDouble()
                        binding.result.text = result.toString()
                    }
                }
            }else if (historyNumber!="" && newTextResult==""){
                Toast.makeText(this,"you must enter another number\nfor display the result !!",Toast.LENGTH_LONG).show()
            }else{
                Toast.makeText(this,"it's not yet for using this option !!",Toast.LENGTH_LONG).show()
            }
        }
    }

    fun onClickNumber(v:View){
        newDigit = (v as Button).text.toString()
        oldNumber =  binding.result.text.toString()
        newTextResult = oldNumber + newDigit
        binding.result.text = newTextResult
    }
    fun clear (){
       binding.cleanBtn.setOnClickListener {
            clearTextResult()
            historyNumber = ""
        }
    }
    fun clearTextResult() {
        binding.result.text = ""
        newTextResult = ""
    }
}
