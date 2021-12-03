package com.synaric.aque.test

class KotlinPlay {

    fun testFor() {
        (1..5).forEach { i ->
            println("print $i")
        }

        (10 downTo 1 step 2).forEach { i ->
            println("print $i")
        }

        (1 until 5).forEach { i ->
            println("print $i")
        }
    }

    fun testIn() {
        // 创建不可变set，使用in检查是否c在of集合中
        val of = setOf("a", "b")
        println("c" in of)
    }

    fun testExtFUn() {
        // 在别的地方使用时，需要先import com.synaric.aque.test.lastChar
        // 扩展函数的调用根据静态类型，而不是运行时确定，因为扩展函数是使用静态方法实现的
        fun String.lastChar(index: Int): Char {
            println(index)
            return this[length - 1]
        }
        fun String.idxAt(index: Int): Char = this[index]


        val ff = {index: Int -> index}
        ff(1)
    }

    fun testVararg() {
        fun aa(vararg i: Int) {
            i.size
        }
        // 演示可变数量参数
        val l = listOf(1, 2, 3)
        aa(*l.toIntArray())
        aa(1, 2, 3)
    }
}