package com.synaric.aque.test

/**
 * 第五章——Lambda编程
 */
class Lambda {

    fun testLambda() {
        // 演示带接收者的with()和apply()

        val str = "abc"
        // 等同于with(str, { ... })
        with(str) {
            // this.get()调用成员方法，也可以省略this
            get(0)
            get(1)
            get(2)
        }

        // 总是返回str的引用，其他行为和with一样
        val str1 = str.apply {
            get(0)
            get(1)
        }

        // 演示内建的apply()实现
        val result = buildString {
            append("aaa")
        }
    }
}