package hu.bme.aut.fleetbrotherserver

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication


@SpringBootApplication
class FleetBrotherServerApplication

fun main(args: Array<String>) {
    runApplication<FleetBrotherServerApplication>(*args)
}
