package com.example.mockitosamples

import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Spy
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class SpyAnnotationTest {

    /**
     * @Spy creates actual implementation of a the instance
     * in order to use @Spy an implementation must be open
     */
    @Spy
    lateinit var dummyInterface: DummyInterfaceImpl

    @Test
    fun getOutPutTest(){
        val result = dummyInterface.getOutPut()
        assertThat(result, `is`("This is an output string"))
    }
}