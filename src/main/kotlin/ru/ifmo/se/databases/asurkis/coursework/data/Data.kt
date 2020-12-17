package ru.ifmo.se.databases.asurkis.coursework.data

import java.io.Serializable
import java.sql.Timestamp
import javax.persistence.*

@Entity
@Table(name = "human")
data class Human(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int = 0,
    var name: String,
    var info: String,
    var phone: String?,
    var email: String?,
    var telegram: String?,
    var vk: String?,
) : Serializable

@Entity
@Table(name = "artist")
data class Artist(
    @Id var human: Int,
//    @ManyToMany // (mappedBy = "performance")
//    var performedAt: Set<Tournament>
)

@Entity
@Table(name = "artist")
data class Organizer(@Id var human: Int)

@Entity
@Table(name = "artist")
data class Player(@Id var human: Int)

@Entity
@Table(name = "artist")
data class Sponsor(@Id var human: Int)

@Entity
@Table(name = "sponsor_contract")
data class SponsorContract(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int = 0,
    @ManyToOne
    var sponsor: Sponsor,
    @ManyToOne
    var organizer: Organizer,
    var info: String,
)

@Entity
@Table(name = "rule_set")
data class RuleSet(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int = 0,
    var name: String,
)

@Entity
@Table(name = "character_stat_type")
data class CharacterStatType(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int = 0,
    @ManyToOne
    var rule_set: RuleSet,
    var name: String,
    var description: String,
    var default_value: Int?,
)

@Entity
@Table(name = "rule")
data class Rule(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int = 0,
    @ManyToOne
    var rule_set: RuleSet,
    var condition: String,
    @ManyToOne
    var stat_to_modify: CharacterStatType,
    var action_with_stat: String,
    var value_of_modification: Int,
)

@Entity
@Table(name = "character")
data class Character(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int = 0,
    var name: String,
    @ManyToOne
    var rule_set: RuleSet,
    @ManyToOne
    var player: Player,
)

@Embeddable
data class CharacterStatPK(@Id var character: Int, @Id var type: Int) : Serializable

@Entity
@Table(name = "character_stat")
@IdClass(CharacterStatPK::class)
data class CharacterStat(
    @Id
    var character: Int,
    @Id
    var type: Int,
    var value: Int,
)

@Entity
@Table(name = "tournament")
data class Tournament(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int = 0,
    var place: String,
    var start_date: Timestamp,
    var finish_date: Timestamp,
    @ManyToOne
    var organizer: Organizer,
    @ManyToOne
    var rule_set: RuleSet,
//    @ManyToMany
//    var artistsPerformed: Set<Artist>,
)

data class PerformancePK(var artist: Artist, var tournament: Tournament) : Serializable

@Entity
@Table(name = "performance")
@IdClass(PerformancePK::class)
data class Performance(
    @Id
    @ManyToOne
    var artist: Artist,
    @Id
    @ManyToOne
    var tournament: Tournament,
)

@Entity
@Table(name = "game")
data class Game(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int = 0,
    @ManyToOne
    var tournament: Tournament
)

@Entity
@Table(name = "game_event")
data class GameEvent(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int = 0,
    @ManyToOne
    var game: Game,
    @ManyToOne
    var agent: Character,
    @ManyToOne
    var `object`: Character,
    var description: String,
    var time: Timestamp,
    @ManyToOne
    var rule_applied: Rule,
)

data class TournamentResultPK(var tournament: Int, var player: Int): Serializable

@Entity
@Table(name = "tournament_result")
@IdClass(TournamentResultPK::class)
data class TournamentResult(
    @Id
    var tournament: Int,
    @Id
    var player: Int,
    var score: Int?,
    var place: Int?,
)

@Entity
@Table(name = "tournament_result_joined_view")
@IdClass(TournamentResultPK::class)
data class TournamentResultJoined(
    @Id
    var player: Int,
    var name: String,
    var info: String,
    var phone: String?,
    var email: String?,
    var telegram: String?,
    var vk: String?,
    @Id
    var tournament: Int,
    var physical_place: String,
    var start_date: Timestamp,
    var finish_date: Timestamp,
    @ManyToOne
    var organizer: Organizer,
    @ManyToOne
    var rule_set: RuleSet,
    var score: Int?,
    var ladder_place: Int?,
)
