package com.accumulus

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class AccumulusAnalyticsDashboardApplication

fun main(args: Array<String>) {
    runApplication<AccumulusAnalyticsDashboardApplication>(*args)
}

