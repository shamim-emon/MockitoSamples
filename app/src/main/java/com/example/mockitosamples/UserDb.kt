package com.example.mockitosamples

interface UserDb {
    fun insertUser(user: User):Boolean
    fun fetchUser(id:String):User?
}