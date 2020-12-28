package ru.ifmo.se.databases.asurkis.coursework.controllers

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController
import ru.ifmo.se.databases.asurkis.coursework.data.*
import ru.ifmo.se.databases.asurkis.coursework.repositories.*

@RestController
class FunctionController(
    val humanRepository: HumanRepository,
    val tournamentRepository: TournamentRepository,
    val characterStatRepository: CharacterStatRepository,
    val artistRepository: ArtistRepository,
    val organizerRepository: OrganizerRepository,
    val playerRepository: PlayerRepository,
    val sponsorRepository: SponsorRepository
) {
    @GetMapping("/humansByName")
    fun humansByName(name: String) = humanRepository.humansByName(name)

    @GetMapping("/tournamentsByPlace")
    fun tournamentByPlace(place: String) = tournamentRepository.tournamentsByPlace(place)

    @PostMapping("/artist/addHuman")
    fun addArtistHuman(human: Human) =
        artistRepository.save(Artist(humanRepository.save(human).id))

    @PostMapping("/organizer/addHuman")
    fun addOrganizerHuman(human: Human) =
        organizerRepository.save(Organizer(humanRepository.save(human).id))

    @PostMapping("/player/addHuman")
    fun addPlayerHuman(human: Human) =
        playerRepository.save(Player(humanRepository.save(human).id))

    @PostMapping("/sponsor/addHuman")
    fun addSponsorHuman(human: Human) =
        sponsorRepository.save(Sponsor(humanRepository.save(human).id))

    @PostMapping("/characterStat/modify")
    fun modifyCharacterStat(character: Int, type: Int, value: Int) {
        characterStatRepository.modifyCharacterStat(character, type, value)
    }
}
