package com.synaric.aque.test

/**
 * 第四章——对象和接口
 *
 * 关键字：
 * open
 * abstract
 * final
 * internal
 * this@Outer用法
 * sealed
 * override
 * constructor
 * init
 * data
 * by
 * object               对象声明（常用于实现单例）、伴生对象（私有的、静态的对象引用，常用于实现工厂方法）、对象表达式（匿名内部类）
 */
class ClassAndInterface {

    /**
     * 需要显式声明open的class和fun能够被重写。
     */
    open class OpenClass {

        open fun enableOverride() {}
    }

    /**
     * 抽象类和抽象方法默认open。
     */
    abstract class AbsClass {

        abstract fun absFun()

        /**
         * 非抽象方法仍然需要显式声明open以被重写。
         */
        open fun openFUn() {}
    }

    class SubClass: OpenClass() {

        /**
         * 重写的open方法默认也是open的，添加final修饰符以避免被子类重写。
         */
        final override fun enableOverride() {
            super.enableOverride()
            println("SubClass.enableOverride()")
        }
    }

    /**
     * 访问可见性public（默认）/internal/protected（类和子类可见）/private。
     */
    internal class InternalClass {

        fun InternalClass() {}
    }

    /**
     * 默认内部类是static，非静态内部类需要显式使用inner修饰符。
     * 内部类访问外部类引用可以使用this@Outer的方式。
     */
    class Outer {
        inner class Inner {
            fun getOuter(): Outer {
                // this@外部类名
                return this@Outer
            }
        }
    }

    /**
     * 密封类，规定所有子类必须和Expr在同一文件。
     * 这使得when子句不必显式提供一个default条件实现，当sealed被一个新类继承时，未修改添加新分支条件的when会在编译时报错。
     */
    sealed class Expr {

        class Num: Expr() {

        }
    }

    /**
     * 声明主构造方法DeclareCons(val v: String)
     */
    open class DeclareCons(val v: String) {

        init {
            // 初始代码块，在对象创建的时候执行
            println(v)
        }
    }

    /**
     * 继承时，指定父类主构造方法
     */
    class SubDeclareCons(val k: Int, v: String): DeclareCons(v) {

    }

    /**
     * 多个构造函数。
     */
    open class MultiCons {

        val k: Int
        val v: String
            get() = "$k ___"

        constructor(k: Int) {
            this.k = k
//            this.v = ""
        }

        constructor(k: Int, v: String) {
            this.k = k
//            this.v = v
        }

        /**
         * 委托给另一个构造方法。
         */
        constructor(k: Int, v: String, n: Int): this(k, v) {

        }

        override fun equals(other: Any?): Boolean {
            return (other as MultiCons).k == k
        }

        /**
         * equals()相等时，hashCode()必定相等
         */
        override fun hashCode(): Int {
            return k.hashCode()
        }
    }

    class SubMultiCons: MultiCons {

        /**
         * 委托父类构造方法。
         */
        constructor(k: Int): super(k) {

        }

        /**
         * 私有化构造方法。
         */
        private constructor(k: Int, v: String): super(k, v)
    }

    fun createMultiCOns() {
        // 没有无参的默认构造方法
        // MultiCOns()

        // 可以
        MultiCons(1)
        MultiCons(1, "")
    }


    fun equalTest() {
        val a = "a"
        val b = "a"

        val c = MultiCons(1)
        val d = MultiCons(1)

        // Kotlin中，a == b等同于a.equals(b)，如果a不为null的话
        println(a == b)
        // 比较a和b的引用是否相等
        println(a === b)
        // 重写equals()比较
        println(c == d)
        // 比较c和d的引用是否相等
        println(c === d)

        val e: MultiCons? = null
        println(e?.k)
    }

    /**
     * 演示类委托，将所有方法委托给delegate对象。
     * 只有实现interface才能使用by，下边例子里是Map<K, V>。
     */
    class SubHashMap<K, V>(val delegate: HashMap<K, V> = HashMap()): Map<K, V> by delegate {

    }

    /**
     * 声明全局单例对象。
     * SingletonClass.foo()调用。
     */
    object SingletonClass {

        fun foo() {}
    }

    open class AClass {

        private var aField: String = ""

        /**
         * 每个Class允许一个companion object。
         * Factory是类名，可以省略。
         */
        companion object Factory {

            fun create(): AClass {
                val aClass = AClass()
                // 可以访问private属性
                aClass.aField = "aField"
                return aClass
            }
        }
    }

    class BClass: AClass() {

        companion object {

            fun createOfB() {}
        }
    }

    fun testCompanionObject() {
        AClass.create()
        AClass.Factory.create()
        // BClass.create() 不行，不能调用
        BClass.createOfB()
        BClass.Companion.createOfB()

        // 伴生对象的扩展方法
        // BClass必须先声明一个伴生对象，才能在这里对伴生对象扩展。
        fun BClass.Companion.fromJSON(json: String) {

        }
    }
}