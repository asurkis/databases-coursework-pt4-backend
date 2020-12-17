package ru.ifmo.se.databases.asurkis.coursework.repositories

import org.springframework.data.repository.CrudRepository
import ru.ifmo.se.databases.asurkis.coursework.data.*

interface HumanRepository: CrudRepository<Human, Int> {
    fun findAllByName(name: String): Iterable<Human>
}

interface ArtistRepository: CrudRepository<Artist, Int>
interface OrganizerRepository: CrudRepository<Organizer, Int>
interface PlayerRepository: CrudRepository<Player, Int>
interface SponsorRepository: CrudRepository<Sponsor, Int>
interface SponsorContractRepository: CrudRepository<SponsorContract, Int>
interface RuleSetRepository: CrudRepository<RuleSet, Int>
interface CharacterStatTypeRepository: CrudRepository<CharacterStatType, Int>
interface RuleRepository: CrudRepository<Rule, Int>
interface CharacterRepository: CrudRepository<Character, Int>
interface CharacterStatRepository: CrudRepository<CharacterStat, CharacterStatPK>
interface TournamentRepository: CrudRepository<Tournament, Int>
interface PerformanceRepository: CrudRepository<Performance, PerformancePK>
interface GameRepository: CrudRepository<Game, Int>
interface GameEventRepository: CrudRepository<GameEvent, Int>

interface TournamentResultRepository: CrudRepository<TournamentResult, TournamentResultPK> {
    fun findAllByTournament(tournament: Int): Iterable<TournamentResult>
    fun findAllByPlayer(player: Int): Iterable<TournamentResult>
}

interface TournamentResultJoinedRepository: CrudRepository<TournamentResultJoined, TournamentResultPK> {
    fun findAllByTournament(tournament: Int): Iterable<TournamentResultJoined>
    fun findAllByPlayer(player: Int): Iterable<TournamentResultJoined>
}
