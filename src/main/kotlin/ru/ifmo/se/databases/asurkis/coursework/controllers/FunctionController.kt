package ru.ifmo.se.databases.asurkis.coursework.controllers

import org.springframework.web.bind.annotation.*
import ru.ifmo.se.databases.asurkis.coursework.data.*
import ru.ifmo.se.databases.asurkis.coursework.repositories.*
import java.util.*

@RestController
class FunctionController(
    val humanRepository: HumanRepository,
    val tournamentRepository: TournamentRepository,
    val characterStatRepository: CharacterStatRepository,
    val artistRepository: ArtistRepository,
    val organizerRepository: OrganizerRepository,
    val playerRepository: PlayerRepository,
    val sponsorRepository: SponsorRepository,
    val performanceRepository: PerformanceRepository,
    val tournamentWithLinksRepository: TournamentWithLinksRepository,
    val humanWithRolesRepository: HumanWithRolesRepository,
    val performanceWithNameRepository: PerformanceWithNameRepository
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
    fun modifyCharacterStat(@RequestBody triplet: CharacterStat) {
        characterStatRepository.modifyCharacterStat(triplet.character, triplet.type, triplet.value)
    }

    @GetMapping("/tournament/artist/{id}")
    fun tournamentsByArtist(@PathVariable id: Int) =
        performanceRepository.findAllByArtist(id).map { tournamentWithLinksRepository.findById(it.tournament) }

    @GetMapping("/artist/tournament/{id}")
    fun artistsByTournament(@PathVariable id: Int) =
        performanceRepository.findAllByTournament(id).map { humanWithRolesRepository.findById(it.artist) }

    @GetMapping("/artist/byTournamentList")
    fun artistsByMultipleTournaments(idx: Array<Int>) = idx.map { artistsByTournament(it) }

    @GetMapping("/allTournamentsAndArtists")
    fun allTournamentsAndArtists(): Pair<Iterable<Tournament>, Iterable<Iterable<PerformanceWithName>>> {
        val tournaments = tournamentRepository.findAll()
        val artists = tournaments.map { performanceWithNameRepository.findAllByTournament(it.id) }
        return Pair(tournaments, artists)
    }
}
