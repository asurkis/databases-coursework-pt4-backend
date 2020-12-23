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
interface CharacterStatTypeRepository : CrudRepository<CharacterStatType, Int>
interface RuleRepository : CrudRepository<Rule, Int>
interface CharacterRepository : CrudRepository<Character, Int>
interface PerformanceRepository : CrudRepository<Performance, PerformancePK>
interface GameRepository : CrudRepository<Game, Int>
interface GameEventRepository : CrudRepository<GameEvent, Int>

interface CharacterStatRepository : CrudRepository<CharacterStat, CharacterStatPK> {
    @Transactional
    @Modifying
    @Query("UPDATE CharacterStat cs SET cs.value = :new_value " +
                   "WHERE cs.character = :arg_character " +
                   "AND cs.type = :arg_type")
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

interface TournamentResultRepository : CrudRepository<TournamentResult, TournamentResultPK> {
    fun findAllByTournament(tournament: Int): Iterable<TournamentResult>
    fun findAllByPlayer(player: Int): Iterable<TournamentResult>
}

interface TournamentResultJoinedRepository : CrudRepository<TournamentResultJoined, TournamentResultPK> {
    fun findAllByTournament(tournament: Int): Iterable<TournamentResultJoined>
    fun findAllByPlayer(player: Int): Iterable<TournamentResultJoined>
}
