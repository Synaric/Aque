package com.synaric.aque.test

import java.lang.RuntimeException
import java.lang.reflect.InvocationHandler
import java.lang.reflect.Method
import java.lang.reflect.Proxy

/**
 * 第六章——TypeSystem
 * 操作：
 * ?.
 * ?:
 * as?
 * let()
 * lateinit
 *
 * 类型：
 * Any
 * Unit(Kotlin中的void)
 * Nothing(表明函数不会正常结束)
 *
 * 与java的集合交互，需要考虑：
 * 1、集合是否为null
 * 2、集合元素是否为null
 * 3、集合是否可变
 */
class TypeSystem {

    fun testTypeSystem() {
        // 类似于js里b = a || "unknown"
        val a = null
        val b = a ?: "unknown"

        // as? T尝试做类型转换，如果c不是T类型，则返回null
        val c: String? = ""
        val d = c as? Int

        // 如果c为null，则let内不会执行
        c?.let {
            println(it)
        }
        // 不使用?.时，it是String?可空类型
        c.let {
            println(it)
        }

        // 一些针对空字符串或者空白字符串的判断，可以像null一样处理
        // 不需要写成c?
        c.isNullOrBlank()
        c.isNullOrEmpty()

        // Kotlin中必须显式转换
        // 将Int的f转换为Long后，才能in检查
        val f: Int = 1
        f.toLong() in listOf(1L, 2L)
        val g: Byte = 2
        // 算数操作仍然接受所有适当的类型，允许Int和Byte相加，h为Int类型，i为Long
        val h = f + g
        val i = h + 3L

        // 演示List<Int?>的操作
        val j = listOf(1, 2, 3, null)
        j.filterNotNull()
        j.asSequence().filterNotNull().sum()
    }

    // 声明一个延迟初始化属性
    // Kotlin要求属性在构造器初始化，但是Android中一般在onCreate()初始化，就需要用到这个
    // lateinit必须是var
    lateinit var e: String

    /**
     * T默认是Any?，所以需要非空检查?.
     * K不需要非空检查，因为显式声明为Any。
     */
    fun <T, K: Any> foo(t: T, k: K) {
        t?.toString()
        k.toString()
    }

    /**
     * Nothing返回类型表明函数不会正常结束
     */
    fun fail(): Nothing {
        throw RuntimeException()
    }

    interface Game {

        fun play(name: String)
    }

    class AGame: Game {
        override fun play(name: String) {
            println("AGame.play")
        }
    }

    fun testProxy() {
        val game = AGame()
        val newProxyInstance: Game = Proxy.newProxyInstance(
            game.javaClass.classLoader,
            arrayOf(Game::class.java)
        ) { any: Any, method: Method, arrayOfAnys: Array<Any> ->
//            println(any)
            method.invoke(game, *arrayOfAnys)
        } as Game

        newProxyInstance.play("hi")
    }
}