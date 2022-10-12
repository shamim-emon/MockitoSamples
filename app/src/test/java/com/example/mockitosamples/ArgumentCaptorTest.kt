package com.example.mockitosamples

import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentCaptor
import org.mockito.Captor
import org.mockito.Mock
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ArgumentCaptorTest {
    @Captor lateinit var personCaptor:ArgumentCaptor<Person>
    @Mock lateinit var personList:MutableList<Person>

    @Before
    fun setUp(){
        personList.add(Person(1,"A"))
        personList.add(Person(2,"B"))
        personList.add(Person(3,"C"))
    }
    @Test
    fun captorTest(){
        verify(personList, times(3)).add(capture(personCaptor))
        val results=personCaptor.allValues
        assertThat(results.get(0).name, `is`("A"))
        assertThat(results.get(1).name, `is`("B"))
        assertThat(results.get(2).name, `is`("C"))
    }
}

data class Person(val id:Int,val name:String)

/**
 * Returns ArgumentCaptor.capture() as nullable type to avoid java.lang.IllegalStateException
 * when null is returned.
 */
@Suppress("UNCHECKED_CAST")
private fun <T> capture(captor: ArgumentCaptor<T>): T = captor.capture()