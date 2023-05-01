package com.accumulus.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.mongodb.core.MongoOperations
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

//API for deriving the metrics that were computed by our pipeline.
@RestController
@RequestMapping("/metrics")
class AccumulusMetricsController(
    @Autowired
    val mongoOperations: MongoOperations
) {

    @GetMapping("/totalCountPerTopping")
    fun getTotalCountPerTopping(): ResponseEntity<MutableMap<String, Long>> {
        val totalCountPerTopping = mutableMapOf<String, Long>()
        mongoOperations.getCollection("total_count_per_topping").find().forEach {
            totalCountPerTopping[it["topping"] as String] = it["total_count"] as Long
        }
        return ResponseEntity.ok(totalCountPerTopping)
    }

    @GetMapping("/uniqueUsersCountPerTopping")
    fun getUniqueUsersCountPerTopping(): ResponseEntity<MutableMap<String, Long>> {
        val uniqueUsersCountPerTopping = mutableMapOf<String, Long>()
        mongoOperations.getCollection("unique_users_count_per_topping").find().forEach {
            uniqueUsersCountPerTopping[it["topping"] as String] = it["unique_user_count"] as Long
        }
        return ResponseEntity.ok(uniqueUsersCountPerTopping)
    }

    @GetMapping("/list3MostPopularToppings")
    fun get3MostPopularToppings(): ResponseEntity<MutableMap<String, Long>> {
        val mostPopularToppings = mutableMapOf<String, Long>()
        mongoOperations.getCollection("most_popular_toppings").find().forEach {
            mostPopularToppings[it["topping"] as String] = it["count"] as Long
        }
        return ResponseEntity.ok(mostPopularToppings)
    }

    @GetMapping("/list3LeastPopularToppings")
    fun get3LeastPopularToppings(): ResponseEntity<MutableMap<String, Long>> {
        val leastPopularToppings = mutableMapOf<String, Long>()
        mongoOperations.getCollection("least_popular_toppings").find().forEach {
            leastPopularToppings[it["topping"] as String] = it["count"] as Long
        }
        return ResponseEntity.ok(leastPopularToppings)
    }

    @GetMapping("/numberOfUsersWhoLikeToHaveMoreThan2Toppings")
    fun getNumberOfUsersWhoLikeToHaveMoreThan2Toppings(): ResponseEntity<Long> {
        var numberOfUsersWithMoreThan2Toppings: Long = 0
        mongoOperations.getCollection("users_with_more_than_2_toppings").find().forEach {
            numberOfUsersWithMoreThan2Toppings += 1
        }
        return ResponseEntity.ok(numberOfUsersWithMoreThan2Toppings)
    }

    @GetMapping("/averageNumberOfToppingsThatUsersLike")
    fun getAverageNumberOfToppingsThatUsersLike(): ResponseEntity<Double> {
        var averageNumberOfToppingsThatUsersLike: Double =
            mongoOperations.getCollection("avg_number_of_toppings_that_users_like").find().first()
                ?.getDouble("avg_number_of_toppings") as Double
        return ResponseEntity.ok(averageNumberOfToppingsThatUsersLike)
    }

    @GetMapping("/findOutWhichToppingsUsuallyGoTogetherInPairsOf2")
    fun findOutWhichToppingsUsuallyGoTogetherInPairsOf2(): ResponseEntity<MutableMap<Pair<String, String>, Long>> {
        val toppingPairsThatUsuallyGoTogether = mutableMapOf<Pair<String, String>, Long>()
        mongoOperations.getCollection("topping_pairs_that_go_together").find().forEach {
            toppingPairsThatUsuallyGoTogether[Pair(
                it["topping1"] as String,
                it["topping2"] as String
            )] = it["count"] as Long
        }
        return ResponseEntity.ok(toppingPairsThatUsuallyGoTogether)
    }
}