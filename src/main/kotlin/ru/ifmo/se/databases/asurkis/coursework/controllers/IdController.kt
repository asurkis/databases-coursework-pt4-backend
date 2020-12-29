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
    val humanWithRolesRepository: HumanWithRolesRepository,
    val tournamentWithLinksRepository: TournamentWithLinksRepository,
    val tournamentResultWithLinksRepository: TournamentResultWithLinksRepository,
    val characterWithLinksRepository: CharacterWithLinksRepository,
    val characterStatWithTypeRepository: CharacterStatWithTypeRepository,
    val sponsorContractWithLinksRepository: SponsorContractWithLinksRepository,
    val gameEventWithLinksRepository: GameEventWithLinksRepository,
    val performanceWithNameRepository: PerformanceWithNameRepository,
    val ruleWithLinksRepository: RuleWithLinksRepository
) {
    @GetMapping("/human/{id}")
    fun humanById(@PathVariable id: Int) = humanWithRolesRepository.findById(id)

    @GetMapping("/human/all")
    fun allHumans() = humanWithRolesRepository.findAll()

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

    @GetMapping("/sponsorContract/organizer/{id}")
    fun sponsorContractByOrganizer(@PathVariable id: Int) = sponsorContractWithLinksRepository.findAllByOrganizer(id)

    @GetMapping("/sponsorContract/sponsor/{id}")
    fun sponsorContractBySponsor(@PathVariable id: Int) = sponsorContractWithLinksRepository.findAllBySponsor(id)

    @GetMapping("/sponsorContract/{id}")
    fun sponsorContractById(@PathVariable id: Int) = sponsorContractWithLinksRepository.findById(id)

    @GetMapping("/sponsorContract/all")
    fun allSponsorContracts() = sponsorContractWithLinksRepository.findAll()

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
    fun ruleById(@PathVariable id: Int) = ruleWithLinksRepository.findById(id)

    @GetMapping("/rule/set/{id}")
    fun ruleByRuleSet(@PathVariable id: Int) = ruleWithLinksRepository.findAllByRuleSet(id)

    @GetMapping("/rule/all")
    fun allRules() = ruleWithLinksRepository.findAll()

    @PostMapping("/rule/add")
    fun addRule(rule: Rule) = ruleRepository.save(rule)

    @GetMapping("/character/{id}")
    fun characterById(@PathVariable id: Int) = characterWithLinksRepository.findById(id)

    @GetMapping("/character/all")
    fun allCharacters() = characterWithLinksRepository.findAll()

    @PostMapping("/character/add")
    fun addCharacter(character: Character) = characterRepository.save(character)

    @GetMapping("/characterStat/{characterId}:{typeId}")
    fun characterStatById(
        @PathVariable characterId: Int,
        @PathVariable typeId: Int
    ) = characterStatWithTypeRepository.findById(CharacterStatPK(characterId, typeId))

    @GetMapping("/characterStat/character/{id}")
    fun characterStatByCharacter(@PathVariable id: Int) = characterStatWithTypeRepository.findAllByCharacter(id)

    @GetMapping("/characterStat/all")
    fun allCharacterStats() = characterStatWithTypeRepository.findAll()

    @GetMapping("/tournament/{id}")
    fun tournamentById(@PathVariable id: Int) = tournamentWithLinksRepository.findById(id)

    @GetMapping("/tournament/all")
    fun allTournaments() = tournamentWithLinksRepository.findAll()

    @PostMapping("/tournament/add")
    fun addTournament(tournament: Tournament) = tournamentRepository.save(tournament)

    @GetMapping("/performance/artist/{id}")
    fun performanceByArtist(@PathVariable id: Int) = performanceWithNameRepository.findAllByArtist(id)

    @GetMapping("/performance/tournament/{id}")
    fun performanceByTournament(@PathVariable id: Int) = performanceWithNameRepository.findAllByTournament(id)

    @GetMapping("/performance/{artistId}:{tournamentId}")
    fun performanceById(@PathVariable artistId: Int, @PathVariable tournamentId: Int) =
        performanceWithNameRepository.findById(PerformancePK(artistId, tournamentId))

    @GetMapping("/performance/all")
    fun allPerformances() = performanceWithNameRepository.findAll()

    @PostMapping("/performance/add")
    fun addPerformance(performance: Performance) = performanceRepository.save(performance)

    @GetMapping("/game/tournament/{id}")
    fun gameByTournamentId(@PathVariable id: Int) = gameRepository.findAllByTournament(id)

    @GetMapping("/game/{id}")
    fun gameById(@PathVariable id: Int) = gameRepository.findById(id)

    @GetMapping("/game/all")
    fun allGames() = gameRepository.findAll()

    @GetMapping("/gameEvent/game/{id}")
    fun gameEventByGame(@PathVariable id: Int) = gameEventWithLinksRepository.findAllByGame(id)

    @GetMapping("/gameEvent/{id}")
    fun gameEventById(@PathVariable id: Int) = gameEventWithLinksRepository.findById(id)

    @GetMapping("/gameEvent/all")
    fun allGameEvents() = gameEventWithLinksRepository.findAll()

    @PostMapping("/gameEvent/add")
    fun addGameEvent(gameEvent: GameEvent) = gameEventRepository.save(gameEvent)

    @GetMapping("/tournamentResult/player/{id}")
    fun tournamentResultByPlayer(@PathVariable id: Int) = tournamentResultWithLinksRepository.findAllByPlayer(id)

    @GetMapping("/tournamentResult/tournament/{id}")
    fun tournamentResultByTournament(@PathVariable id: Int) =
        tournamentResultWithLinksRepository.findAllByTournament(id)

    @GetMapping("/tournamentResult/all")
    fun allTournamentResults() = tournamentResultWithLinksRepository.findAll()

    @PostMapping("/tournamentResult/add")
    fun addTournamentResult(tournamentResult: TournamentResult) = tournamentResultRepository.save(tournamentResult)
}
