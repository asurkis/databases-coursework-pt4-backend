package ru.ifmo.se.databases.asurkis.coursework.repositories

import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.transaction.annotation.Transactional
import ru.ifmo.se.databases.asurkis.coursework.data.*

interface ArtistRepository : CrudRepository<Artist, Int>
interface OrganizerRepository : CrudRepository<Organizer, Int>
interface PlayerRepository : CrudRepository<Player, Int>
interface SponsorRepository : CrudRepository<Sponsor, Int>
interface SponsorContractRepository : CrudRepository<SponsorContract, Int>
interface RuleSetRepository : CrudRepository<RuleSet, Int>
interface CharacterRepository : CrudRepository<Character, Int>
interface GameEventRepository : CrudRepository<GameEvent, Int>
interface TournamentResultRepository : CrudRepository<TournamentResult, TournamentResultPK>
interface HumanWithRolesRepository : CrudRepository<HumanWithRoles, Int>
interface RuleRepository : CrudRepository<Rule, Int>

interface CharacterStatTypeRepository : CrudRepository<CharacterStatType, Int> {
    fun findAllByRuleSet(ruleSet: Int): Iterable<CharacterStatType>
}

interface CharacterWithLinksRepository : CrudRepository<CharacterWithLinks, Int> {
    fun findAllByRuleSet(ruleSet: Int): Iterable<CharacterWithLinks>
}

interface RuleWithLinksRepository : CrudRepository<RuleWithLinks, Int> {
    fun findAllByRuleSet(ruleSet: Int): Iterable<RuleWithLinks>
}

interface CharacterStatWithTypeRepository : CrudRepository<CharacterStatWithType, CharacterStatPK> {
    fun findAllByCharacter(character: Int): Iterable<CharacterStatWithType>
}

interface PerformanceRepository : CrudRepository<Performance, PerformancePK> {
    fun findAllByArtist(artist: Int): Iterable<Performance>
    fun findAllByTournament(artist: Int): Iterable<Performance>
}

interface SponsorContractWithLinksRepository : CrudRepository<SponsorContractWithLinks, Int> {
    fun findAllByOrganizer(organizer: Int): Iterable<SponsorContractWithLinks>
    fun findAllBySponsor(sponsor: Int): Iterable<SponsorContractWithLinks>
}

interface GameEventWithLinksRepository : CrudRepository<GameEventWithLinks, Int> {
    fun findAllByGame(game: Int): Iterable<GameEventWithLinks>
}

interface GameRepository : CrudRepository<Game, Int> {
    fun findAllByTournament(tournament: Int): Iterable<Game>
}

interface PerformanceWithNameRepository : CrudRepository<PerformanceWithName, PerformancePK> {
    fun findAllByArtist(artist: Int): Iterable<PerformanceWithName>
    fun findAllByTournament(tournament: Int): Iterable<PerformanceWithName>
}

interface CharacterStatRepository : CrudRepository<CharacterStat, CharacterStatPK> {
    @Transactional
    @Modifying
    @Query(
        "UPDATE CharacterStat cs SET cs.value = :new_value " +
                "WHERE cs.character = :arg_character " +
                "AND cs.type = :arg_type"
    )
    fun modifyCharacterStat(arg_character: Int, arg_type: Int, new_value: Int)
}

interface HumanRepository : CrudRepository<Human, Int> {
    @Query("SELECT h FROM Human h WHERE h.name LIKE :arg_name")
    fun humansByName(arg_name: String): Array<Human>
}

interface TournamentRepository : CrudRepository<Tournament, Int> {
    @Query("SELECT t FROM Tournament t WHERE t.place LIKE :arg_place")
    fun tournamentsByPlace(arg_place: String): Iterable<Tournament>
}

interface TournamentWithLinksRepository : CrudRepository<TournamentWithLinks, Int> {
    @Query("SELECT t FROM TournamentWithLinks t WHERE t.place LIKE :arg_place")
    fun tournamentsByPlace(arg_place: String): Iterable<Tournament>
}

interface TournamentResultWithLinksRepository : CrudRepository<TournamentResultWithLinks, TournamentResultPK> {
    fun findAllByTournament(tournament: Int): Iterable<TournamentResultWithLinks>
    fun findAllByPlayer(player: Int): Iterable<TournamentResultWithLinks>
}
