package com.chemasmas.fakestoreapi.presentation.features.users_list

import com.chemasmas.fakestoreapi.core.data.repository.models.SimpleUser

val simpleUserMock = SimpleUser(
    id = 1,
    username = "admin",
    email = "admin@yopmail.com",
    firstName = "Ateek",
    lastName = "Hussain",
    profileIcon = "http://13.233.102.144:8000/media/user_pics/2491682488.jpeg"
)

val simpleUserListMock = (0..10).map { simpleUserMock }