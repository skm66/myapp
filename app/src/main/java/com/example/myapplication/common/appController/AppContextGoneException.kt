package com.example.myapplication.common.appController

object AppContextGoneException : Exception(
    "App context is null, try calling init function of the " +
            "implementing class"
)