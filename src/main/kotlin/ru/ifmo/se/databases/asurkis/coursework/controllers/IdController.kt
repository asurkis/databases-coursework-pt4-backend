package ru.ifmo.se.databases.asurkis.coursework.controllers

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController
import ru.ifmo.se.databases.asurkis.coursework.data.*
import ru.ifmo.se.databases.asurkis.coursework.repositories.*

@RestController
class IdController(
    val humanRepository: HumanRepository,
    val artistRepository: ArtistRepository,
    val organizerRepository: OrganizerRepository,
    val playerRepository: PlayerRepository,
    val sponsorRepository: SponsorRepository,
    val sponsorContractRepository: SponsorContractRepository,
    val ruleSetRepository: RuleSetRepository,
    val characterStatTypeRepository: CharacterStatTypeRepository,
    val ruleRepository: RuleRepository,
    val characterRepository: CharacterRepository,
    val characterStatRepository: CharacterStatRepository,
    val tournamentRepository: TournamentRepository,
    val performanceRepository: PerformanceRepository,
    val gameRepository: GameRepository,
    val gameEventRepository: GameEventRepository,
    val tournamentResultRepository: TournamentResultRepository,
    val tournamentResultJoinedRepository: TournamentResultJoinedRepository
) {
    @GetMapping("/human/{id}")
    fun humanById(@PathVariable id: Int) = humanRepository.findById(id)

    @GetMapping("/human/all")
    fun allHumans() = humanRepository.findAll()

    @PostMapping("/human/add")
    fun addHuman(human: Human) = humanRepository.save(human)

    @GetMapping("/artist/{id}")
    fun artistById(@PathVariable id: Int) = artistRepository.findById(id)

    @GetMapping("/artist/all")
    fun allArtists() = artistRepository.findAll()

    @PostMapping("/artist/addRole")
    fun addArtistRole(artist: Artist) = artistRepository.save(artist)

    @GetMapping("/organizer/{id}")
    fun organizerById(@PathVariable id: Int) = organizerRepository.findById(id)

    @GetMapping("/organizer/all")
    fun allOrganizers() = organizerRepository.findAll()

    @PostMapping("/organizer/addRole")
    fun addOrganizerRole(organizer: Organizer) = organizerRepository.save(organizer)

    @GetMapping("/player/{id}")
    fun playerById(@PathVariable id: Int) = playerRepository.findById(id)

    @GetMapping("/player/all")
    fun allPlayers() = playerRepository.findAll()

    @PostMapping("/player/addRole")
    fun addPlayerRole(player: Player) = playerRepository.save(player)

    @GetMapping("/sponsor/{id}")
    fun sponsorById(@PathVariable id: Int) = sponsorRepository.findById(id)

    @GetMapping("/sponsor/all")
    fun allSponsors() = sponsorRepository.findAll()

    @PostMapping("/sponsor/addRole")
    fun addSponsorRole(sponsor: Sponsor) = sponsorRepository.save(sponsor)

    @GetMapping("/sponsorContract/{id}")
    fun sponsorContractById(@PathVariable id: Int) = sponsorContractRepository.findById(id)

    @GetMapping("/sponsorContract/all")
    fun allSponsorContracts() = sponsorContractRepository.findAll()

    @PostMapping("/sponsorContract/add")
    fun addSponsorContract(sponsorContract: SponsorContract) = sponsorContractRepository.save(sponsorContract)

    @GetMapping("/ruleSet/{id}")
    fun ruleSetById(@PathVariable id: Int) = ruleSetRepository.findById(id)

    @GetMapping("/ruleSet/all")
    fun allRuleSets() = ruleSetRepository.findAll()

    @PostMapping("/ruleSet/add")
    fun addRuleSet(ruleSet: RuleSet) = ruleSetRepository.save(ruleSet)

    @GetMapping("/characterStatType/{id}")
    fun characterStatTypeById(@PathVariable id: Int) = characterStatTypeRepository.findById(id)

    @GetMapping("/characterStatType/all")
    fun allCharacterStatTypes() = characterStatTypeRepository.findAll()

    @PostMapping("/characterStatType/add")
    fun addCharacterStatType(characterStatType: CharacterStatType) = characterStatTypeRepository.save(characterStatType)

    @GetMapping("/rule/{id}")
    fun ruleById(@PathVariable id: Int) = ruleRepository.findById(id)

    @GetMapping("/rule/all")
    fun allRules() = ruleRepository.findAll()

    @PostMapping("/rule/add")
    fun addRule(rule: Rule) = ruleRepository.save(rule)

    @GetMapping("/character/{id}")
    fun characterById(@PathVariable id: Int) = characterRepository.findById(id)

    @GetMapping("/character/all")
    fun allCharacters() = characterRepository.findAll()

    @PostMapping("/character/add")
    fun addCharacter(character: Character) = characterRepository.save(character)

    @GetMapping("/characterStat/{characterId}:{typeId}")
    fun characterStatById(
        @PathVariable characterId: Int,
        @PathVariable typeId: Int
    ) = characterStatRepository.findById(CharacterStatPK(characterId, typeId))

    @GetMapping("/characterStat/all")
    fun characterStatById() = characterStatRepository.findAll()

    @GetMapping("/tournament/{id}")
    fun tournamentById(@PathVariable id: Int) = tournamentRepository.findById(id)

    @GetMapping("/tournament/all")
    fun allTournaments() = tournamentRepository.findAll()

    @PostMapping("/tournament/add")
    fun addTournament(tournament: Tournament) = tournamentRepository.save(tournament)

    @GetMapping("/performance/{artistId}:{tournamentId}")
    fun performanceById(@PathVariable artistId: Int, @PathVariable tournamentId: Int) =
        performanceRepository.findById(PerformancePK(artistId, tournamentId))

    @GetMapping("/performance/all")
    fun allPerformances() = performanceRepository.findAll()

    @PostMapping("/performance/add")
    fun addPerformance(performance: Performance) = performanceRepository.save(performance)

    @GetMapping("/game/{id}")
    fun gameById(@PathVariable id: Int) = gameRepository.findById(id)

    @GetMapping("/game/all")
    fun allGames() = gameRepository.findAll()

    @GetMapping("/gameEvent/{id}")
    fun gameEventById(@PathVariable id: Int) = gameEventRepository.findById(id)

    @GetMapping("/gameEvent/all")
    fun allGameEvents() = gameEventRepository.findAll()

    @PostMapping("/gameEvent/add")
    fun addGameEvent(gameEvent: GameEvent) = gameEventRepository.save(gameEvent)

    @GetMapping("/tournamentResult/player/{id}")
    fun tournamentResultByPlayer(@PathVariable id: Int) = tournamentResultRepository.findAllByPlayer(id)

    @GetMapping("/tournamentResult/tournament/{id}")
    fun tournamentResultByTournament(@PathVariable id: Int) = tournamentResultRepository.findAllByTournament(id)

    @GetMapping("/tournamentResult/all")
    fun allTournamentResults() = tournamentResultRepository.findAll()

    @PostMapping("/tournamentResult/add")
    fun addTournamentResult(tournamentResult: TournamentResult) = tournamentResultRepository.save(tournamentResult)

    @GetMapping("/tournamentResultJoined/{playerId}:{tournamentId}")
    fun tournamentResultJoinedById(@PathVariable playerId: Int, @PathVariable tournamentId: Int) =
        tournamentResultJoinedRepository.findById(TournamentResultPK(tournamentId, playerId))

    @GetMapping("/tournamentResultJoined/player/{id}")
    fun tournamentResultJoinedByPlayer(@PathVariable id: Int) = tournamentResultJoinedRepository.findAllByPlayer(id)

    @GetMapping("/tournamentResultJoined/tournament/{id}")
    fun tournamentResultJoinedByTournament(@PathVariable id: Int) =
        tournamentResultJoinedRepository.findAllByTournament(id)

    @GetMapping("/tournamentResultJoined/all")
    fun allTournamentResultsJoined() = tournamentResultJoinedRepository.findAll()
}
