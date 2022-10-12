package com.example.mockitosamples

import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.nullValue
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class UserDbTests {

    private var user=User(id = 1, name = "Some user")
    @Mock lateinit var userDb: UserDb

    @Before
    fun setUp() {
        insertUserSuccess()
        fetchUserSuccess()
    }

    @Test
    fun insertUserSuccessTrueReturned() {
        val result = userDb.insertUser(User(id = 1, name = "Some Name"))
        assertThat(result, `is`(true))
    }

    @Test
    fun insertUserFailureFalseReturned() {
        insertUserFailure()
        val result = userDb.insertUser(user)
        assertThat(result, `is`(false))
    }

    @Test
    fun fetchUserSuccessUserReturned(){
        val user = userDb.fetchUser("1")
        assertThat(user,`is`(user))
    }

    @Test
    fun fetchUserFailureNullReturned(){
        fetchUserFailure()
        val user = userDb.fetchUser("1")
        assertThat(user,`is`(nullValue()))
    }


    //region helper methods
    private fun insertUserSuccess() {
        `when`(userDb.insertUser(any(User::class.java)))
            .thenReturn(true)
    }

    private fun insertUserFailure() {
        `when`(userDb.insertUser(any(User::class.java)))
            .thenReturn(false)
    }

    private fun fetchUserSuccess(){
        `when`(userDb.fetchUser(any(String::class.java)))
            .thenReturn(user)
    }

    private fun fetchUserFailure(){
        `when`(userDb.fetchUser(any(String::class.java)))
            .thenReturn(null)
    }
    //endregion
}

private fun <T> any(type: Class<T>): T = Mockito.any<T>(type)