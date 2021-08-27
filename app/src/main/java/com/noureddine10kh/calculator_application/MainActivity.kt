package com.noureddine10kh.calculator_application

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    lateinit var text_result :TextView
    lateinit var plus_btn :Button
    lateinit var minus_btn :Button
    lateinit var div_btn :Button
    lateinit var mul_btn :Button
    lateinit var equal_btn :Button
    lateinit var clear_btn :Button
    lateinit var oldNumber :String
    lateinit var historyNumber :String
    lateinit var newTextResult :String
    lateinit var newDigit :String
    var operationSign : Operation? = null
    var result :Double? =null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView( )
        clear()
        makeOperation()
        displayResult()
    }

    private fun initView() {
        text_result = findViewById(R.id.result)
        equal_btn = findViewById(R.id.equal_btn)
        clear_btn = findViewById(R.id.clean_btn)
        plus_btn = findViewById(R.id.plus_oper)
        minus_btn = findViewById(R.id.min_oper)
        div_btn = findViewById(R.id.div_oper)
        mul_btn = findViewById(R.id.multi_oper)
    }

    fun makeOperation (){
        plus_btn.setOnClickListener {
            doOperation(Operation.plus,newTextResult)
        }
        minus_btn.setOnClickListener {
            doOperation(Operation.minus,newTextResult)
        }
        div_btn.setOnClickListener {
            doOperation(Operation.division,newTextResult)
        }
        mul_btn.setOnClickListener {
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
        equal_btn.setOnClickListener {
            if (historyNumber!="" && newTextResult!= ""){
                when(operationSign){
                    Operation.plus -> {
                        result = historyNumber.toDouble() + newTextResult.toDouble()
                        text_result.text = result.toString()
                    }
                    Operation.minus -> {
                        result = historyNumber.toDouble() - newTextResult.toDouble()
                        text_result.text = result.toString()
                    }
                    Operation.multiplication -> {
                        result = historyNumber.toDouble() * newTextResult.toDouble()
                        text_result.text = result.toString()
                    }
                    Operation.division -> {
                        result = historyNumber.toDouble() / newTextResult.toDouble()
                        text_result.text = result.toString()
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
        oldNumber =  text_result.text.toString()
        newTextResult = oldNumber + newDigit
        text_result.text = newTextResult
    }
    fun clear (){
        clear_btn.setOnClickListener {
            clearTextResult()
            historyNumber = ""
        }
    }
    fun clearTextResult() {
        text_result.text = ""
        newTextResult = ""
    }
}
