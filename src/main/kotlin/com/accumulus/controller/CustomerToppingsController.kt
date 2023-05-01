package com.accumulus.controller

import com.accumulus.data.CustomerToppings
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.springframework.core.io.ClassPathResource
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

//API that gives the data ingress for our pipeline i.e. returns list of customer topping preferences.
@RestController
@RequestMapping("/customerToppings")
class CustomerToppingsController {

    @GetMapping(produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getCustomerToppings(): List<CustomerToppings> {
        val jsonStream =
            ClassPathResource("./customer_toppings.json").inputStream
        val objectMapper = jacksonObjectMapper()
        return objectMapper.readValue(jsonStream)
    }

}
