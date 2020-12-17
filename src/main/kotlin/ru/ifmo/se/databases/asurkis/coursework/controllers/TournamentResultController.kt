package ru.ifmo.se.databases.asurkis.coursework.controllers

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import ru.ifmo.se.databases.asurkis.coursework.repositories.TournamentResultJoinedRepository
import ru.ifmo.se.databases.asurkis.coursework.repositories.TournamentResultRepository
import javax.persistence.EntityManager

@RestController
class TournamentResultController(
    val tournamentResultRepository: TournamentResultRepository,
    val tournamentResultJoinedRepository: TournamentResultJoinedRepository
) {
    @GetMapping("/tournamentResults/player/{id}")
    fun tournamentResultByPlayer(@PathVariable id: Int) = tournamentResultJoinedRepository.findAllByPlayer(id)

    @GetMapping("/tournamentResults/tournament/{id}")
    fun tournamentResultByTournament(@PathVariable id: Int) = tournamentResultJoinedRepository.findAllByTournament(id)
}
