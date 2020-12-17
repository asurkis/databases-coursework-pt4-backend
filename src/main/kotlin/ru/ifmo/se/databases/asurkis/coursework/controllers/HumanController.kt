package ru.ifmo.se.databases.asurkis.coursework.controllers

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import ru.ifmo.se.databases.asurkis.coursework.repositories.HumanRepository

@RestController
class HumanController(val humanRepository: HumanRepository) {
    @GetMapping("/humansByName")
    fun humanByName(name: String) = humanRepository.findAllByName(name)

    @GetMapping("/human/{id}")
    fun humanById(@PathVariable id: Int) = humanRepository.findById(id)
}
