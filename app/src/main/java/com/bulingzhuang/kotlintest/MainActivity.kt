package com.bulingzhuang.kotlintest

import android.annotation.SuppressLint
import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tv.text = "结果：" + sum(2, 2)
        tv2.text = "结果：" + sum2(2, 2)
        tv3.text = "结果：${sum2(2, 2)}"
        tv4.text = printSum(2, 2)
        btn.setOnClickListener {
            getTv5Num()
        }
        tv6.text = printStr()
        tv7.text = printProduct("3", "4")
        tv8.text = getString("应该是显示一行字符串")
        tv9.text = getStringArray()
        btn2.setOnClickListener { printlnWhen() }
        btn3.setOnClickListener { rangeTest() }
        btn4.setOnClickListener { inTest() }
        btn5.setOnClickListener { lambdaTest() }
        btn6.setOnClickListener { mapTest() }
        btn7.setOnClickListener { extensionTest() }
        btn8.setOnClickListener { showToast("打印一行字符串") }
        btn9.setOnClickListener { "这是一行字符串".showToast(this) }
        btn10.setOnClickListener {
            ifNotNullAndElse("有一个字符串来了")
            ifNotNullAndElse(null)
        }
        btn11.setOnClickListener {
            val str ="a,b,c,dd,卜令壮"
            val processor = StringProcessor(str)
            with(processor) {
                upperCase()
                for (i in 0..2) {
                    repeat()
                }
                removeLastChar()
            }
            println("初始数据：$str")
            println("最后结果：${processor.str}")
        }
    }

    private fun getStringArray(): String? {
        val items = listOf("第一", "第二", "第三")
        var str: String = ""
        for (index in items.indices) {
            str += items[index]
        }
        return str
    }

    private fun getTv5Num() {
        val a = 2
        val b: Int = 2
        tv5.text = "结果：${a + b}"
    }

    fun sum(num1: Int, num2: Int): Int {
        return num1 + num2
    }

    fun sum2(num1: Int, num2: Int) = num1 + num2

    fun printSum(num1: Int, num2: Int): String {
        return "结果：$num1+$num2 = ${num1 + num2}"
    }

    fun printStr(): String {
        var a = 1
        var str = "a is $a"
        a = 2
        var str2 = "${str.replace("is", "was")}，but now is $a"
        return str2
    }

    fun printProduct(str1: String, str2: String): String {
        val i1 = str1.toIntOrNull()
        val i2 = str2.toIntOrNull()
        if (i1 != null && i2 != null) {
            return "结果：${i1 + i2}"
        } else {
            return "无结果"
        }
    }
    
    fun getString(obj: Any): String? {
        if (obj is String) {
            return obj
        } else {
            return null
        }
    }

    fun whenTest(obj: Any): String = when (obj) {
        1 -> "结果是1"
        "hello" -> "显示Hello"
        is Long -> "是Long类型"
        !is String -> "不是String类型"
        else -> "未知"
    }

    fun printlnWhen() {
        println(whenTest(1))
        println(whenTest(2))
        println(whenTest("hello"))
        println(whenTest(2000L))
        println(whenTest(500F))
    }

    fun rangeTest() {
        val x: Int = 10
        val y = 100
        if (x in 0..y) {
            println("在里面")
        }
        val list = listOf("a", "b", "c")
        if (2 in list.indices) {
            println("2属于list")
        }
        for (position in 0..10) {
            println("数字是：" + position)
        }
        for (position in 0 until 10) {
            println("数字是(半开区间，不包含后面的数字)：" + position)
        }
        for (position in 0..10 step 2) {
            println("数字是(step=2)：" + position)
        }
        //downTo 表示倒叙，从10到0
        for (position in 10 downTo 0 step 3) {
            println("数字是(step=3，downTo=0)：" + position)
        }
    }

    fun inTest() {
        val itemList = listOf("a", "b", "c", "d")
        when {
            "a" in itemList -> println("a包含在内")
            else -> println("不包含在内")
        }
    }

    fun lambdaTest() {
        val fruits = listOf("banana", "orange", "avocado", "apple", "kiwi")
        //首字母筛选，排序，映射全大写，遍历结果
        fruits.filter { it.startsWith("a") }.sortedBy { it }.map { it.toUpperCase() }.forEach { println(it) }
        //两种写法
//        fruits.filter { x -> x.startsWith("a") }.sortedBy { it }.map { it.toUpperCase() }.forEach { println(it) }
    }

    fun mapTest() {
        val mapList = mapOf(Pair("a", "111"), Pair("b", "222"))
        //简写方法
//        val mapList = mapOf("a" to "111", "b" to "222")
        for ((k, v) in mapList) {
            println("Key：$k，Value：$v")
        }

        println("Value=${mapList["a"]}")
    }

    fun String.calculateNum(): String? {
        if (this.length > 1) {
            val substring = this.substring(0, this.length - 1)
            return substring
        }
        return null
    }

    fun Context.showToast(msg: String, length: Int = Toast.LENGTH_SHORT) {
        Toast.makeText(this, msg, length).show()
    }

    fun String.showToast(context: Context, length: Int = Toast.LENGTH_SHORT) {
        Toast.makeText(context, this, length).show()
    }

    fun extensionTest() {
        val str: String = "这是一个字符串"
        println("源字符串：$str")
        val calculateNum = str.calculateNum()
        println("扩展函数执行后(去除最后一位)：$calculateNum")
    }

    fun ifNotNullAndElse(str: String?) = println(str?.let { "字符串长度：${it.length}" } ?: "为空")

    //同上面这一行
//    fun ifNotNullAndElse(str: String?) {
//        val result: Any? = str?.length ?: "为空"
//        when (result) {
//            is Int -> println("字符串长度：$result")
//            is String -> println(result)
//        }
//    }

}
