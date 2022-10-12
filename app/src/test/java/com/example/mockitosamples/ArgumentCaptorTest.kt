package com.example.mockitosamples

import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentCaptor
import org.mockito.Captor
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.never
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ArgumentCaptorTest {
    @Captor lateinit var personCaptor:ArgumentCaptor<Person>
    @Mock lateinit var personList:MutableList<Person>

    @Before
    fun setUp(){

    }
    @Test
    fun captorTest(){

        personList.add(Person(1,"A"))
        /**
         * Below  fail because personList.add(Person) is expected to call never
         * but been called once above
         */
        verify(personList, never()).add(any(Person::class.java))
    }
}

private fun <T> any(type: Class<T>): T = Mockito.any<T>(type)

data class Person(val id:Int,val name:String)

/**
 * Returns ArgumentCaptor.capture() as nullable type to avoid java.lang.IllegalStateException
 * when null is returned.
 */
@Suppress("UNCHECKED_CAST")
private fun <T> capture(captor: ArgumentCaptor<T>): T = captor.capture()