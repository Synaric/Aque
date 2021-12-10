package com.synaric.aque;


import com.synaric.aque.test.ClassAndInterface;
import com.synaric.aque.test.TypeSystem;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        TypeSystem typeSystem = new TypeSystem();
        typeSystem.testProxy();
        assertEquals(4, 2 + 2);
    }
}