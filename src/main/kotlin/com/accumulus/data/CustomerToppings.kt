package com.accumulus.data

import java.io.Serializable

data class CustomerToppings(
    val email: String,
    val toppings: List<String>
) : Serializable
