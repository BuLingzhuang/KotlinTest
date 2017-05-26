package com.bulingzhuang.kotlintest

/**
 * Created by bulingzhuang
 * on 2017/5/25
 * E-mail:bulingzhuang@foxmail.com
 */
class StringProcessor(var str: String) {
    fun repeat() {
        val split = str.split(",")
        val sb: StringBuilder = StringBuilder()
        for (item in split) {
            sb.append(item).append(item).append(",")
        }
        str = sb.toString()
        println("repeat：$str")
    }

    fun upperCase() {
        str = str.toUpperCase()
        println("upperCase：$str")
    }

    fun removeLastChar() {
        if (str.length > 1) {
            str = str.substring(0, str.length - 1)
        }
        println("removeLastChar：$str")
    }
}