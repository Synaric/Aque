package com.synaric.aque.test

import java.lang.Appendable
import kotlin.reflect.jvm.internal.impl.load.kotlin.JvmType

/**
 * 第九章——泛型
 *
 * 关键字：
 * where
 * in
 * out
 *
 * 概念：
 * 子类型
 * 协变：如果A是B的子类型，那么P<A>是P<B>子类型时，就称P对于AB是可协变的。
 * 逆变
 */
class GenericParadigm {

    fun <T> List<T>.doFilter(t: (T) -> Boolean) {
        this.filter(t)
    }

    /**
     * 演示泛型用法。
     */
    fun test1() {
        val list = listOf(1, 2, 3, 4)
        list.doFilter { it > 2 }
    }

    /**
     * 演示T有多个上边界。
     * where指定泛型参数必须实现CharSequence、Appendable。
     */
    fun <T> doString(s: T) where T : CharSequence, T : Appendable {

    }

    /**
     * 子类型不 只 是子类，例如：
     * Int是Number的子类型
     * Any?是Any的子类型
     *
     * 即：A是B的子类型，意味着接受B类型为实参的函数，可以也接受A类型
     * 同理，List<A>是List<B>的子类型（这被称为协变），但是MutableList<A>不是MutableList<B>的子类型，
     * 因为被当成MutableList<B>的MutableList<A>就可以添加B元素，与MutableList<A>的约束冲突了！
     */


    /**
     * 下面小例子演示协变。
     */
    open class Animal {
        fun feed() {}
    }

    class Cat : Animal() {}

    /**
     * out有两层含义：
     * 声明Herd<Cat>是Herd<Animal>的子类型；
     * 有关T的使用，只能用在out位置，例如函数返回T是可以的，接受T不行。
     *
     * 这里构造函数允许接受T，但是只允许赋值val类型。
     */
    class Herd<out T : Animal, K : Animal>(val t: T) {

        /**
         * List<Cat>中每一个元素都被当成Animal。
         */
        fun feedAllCat(list: List<Cat>) {
            for (c in list) {
                c.feed()
            }
        }

        fun feedAllCat2(list: MutableList<K>) {
            for (c in list) {
                c.feed()
            }
        }
    }

    /**
     * 演示协变
     */
    fun test2() {
        val cats = mutableListOf(Cat(), Cat())
        val animals = mutableListOf(Cat(), Cat(), Animal())
        Herd<Cat, Animal>(Cat()).feedAllCat2(animals)
    }

    class H<in T : Animal>

    /**
     * 演示逆变
     */
    fun test3() {
        val h1 = H<Cat>()
        val h2 = H<Animal>()

        fun feedAll(h: H<Cat>) {
            // 一个H<Animal>能处理（in）所有的Animal，自然也能处理Cat
            // 所以接受H<Cat>的参数h，传h2也是可以的
        }

        feedAll(h1)
        feedAll(h2)
    }

    fun test4() {
        val src = mutableListOf(1, 2, 3)
        val des = mutableListOf<Any>()

        fun <T> copy(src: MutableList<out T>, des: MutableList<T>) {
            for (item in src) {
                des.add(item)
            }

            // 不行，受限
//            src.add(1)
        }
        // 声明src: MutableList<T>时，根据类型推断，des不是src: MutableList<Int>，所以下面一句会报错
        // 所以要声明为src: MutableList<out T>，这称为投影
        // 注意协变逆变是Class声明泛型参数中的概念，函数里对象的out称为投影
        // 这表示src受限，不能使用in的方法
        copy(src, des)

        // 投影和Java中的点变型是一个意思
        // 例如out T和? extends T、 in T和? super T是一个意思
        // 协变逆变和点变型不是一个意思
    }

    fun test5() {
        val arg: String? = null

        fun doTest(arg: String?) {
            val let1 = arg.let { "hello" }  // hello
            val let2 = arg?.let { "hello" }     // null
            val let3 = arg?.let { "hello" } ?: run {
                "hello run"
            }      // hello run
            println(let1)
            println(let2)
            println(let3)
        }
        doTest(arg)
    }
}