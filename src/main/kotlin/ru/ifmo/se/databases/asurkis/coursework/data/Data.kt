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
    var vk: String?
) : Serializable

@Entity
@Table(name = "artist")
data class Artist(@Id var human: Int)

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
    var sponsor: Int,
    var organizer: Int,
    var info: String
)

@Entity
@Table(name = "rule_set")
data class RuleSet(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int = 0,
    var name: String
)

@Entity
@Table(name = "character_stat_type")
data class CharacterStatType(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int = 0,
    @Column(name="rule_set")
    var ruleSet: Int,
    var name: String,
    var description: String,
    @Column(name="default_value")
    var defaultValue: Int?
)

@Entity
@Table(name = "rule")
data class Rule(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int = 0,
    @Column(name = "rule_set")
    var ruleSet: Int,
    var condition: String,
    @Column(name = "stat_to_modify")
    var statToModify: Int,
    @Column(name = "action_with_stat")
    var actionWithStat: String,
    @Column(name = "value_of_modification")
    var valueOfModification: Int
)

@Entity
@Table(name = "character")
data class Character(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int = 0,
    var name: String,
    @Column(name = "rule_set")
    var ruleSet: Int,
    var player: Int
)

@Embeddable
data class CharacterStatPK(@Id var character: Int, @Id var type: Int) : Serializable

@Entity
@Table(name = "character_stat")
@IdClass(CharacterStatPK::class)
data class CharacterStat(
    @Id var character: Int,
    @Id var type: Int,
    var value: Int
)

@Entity
@Table(name = "tournament")
data class Tournament(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int = 0,
    var place: String,
    @Column(name = "start_date")
    var startDate: Timestamp,
    @Column(name = "finish_date")
    var finishDate: Timestamp,
    var organizer: Int,
    @Column(name = "rule_set")
    var ruleSet: Int
)

data class PerformancePK(var artist: Int = 0, var tournament: Int = 0) : Serializable

@Entity
@Table(name = "performance")
@IdClass(PerformancePK::class)
data class Performance(
    @Id var artist: Int = 0,
    @Id var tournament: Int = 0
)

@Entity
@Table(name = "game")
data class Game(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int = 0,
    var tournament: Int
)

@Entity
@Table(name = "game_event")
data class GameEvent(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int = 0,
    var game: Int,
    var agent: Int,
    var `object`: Int,
    var description: String,
    var time: Timestamp,
    @Column(name = "rule_applied")
    var ruleApplied: Int
)

data class TournamentResultPK(var player: Int = 0, var tournament: Int = 0) : Serializable

@Entity
@Table(name = "tournament_result")
@IdClass(TournamentResultPK::class)
data class TournamentResult(
    @Id var player: Int = 0,
    @Id var tournament: Int = 0,
    var score: Int?,
    var place: Int?
)

@Entity
@Table(name = "human_with_roles_view")
data class HumanWithRoles(
    @Id var id: Int = 0,
    var name: String,
    var info: String,
    var phone: String?,
    var email: String?,
    var telegram: String?,
    var vk: String?,
    @Column(name="is_artist")
    var isArtist: Boolean = false,
    @Column(name="is_organizer")
    var isOrganizer: Boolean = false,
    @Column(name="is_player")
    var isPlayer: Boolean = false,
    @Column(name="is_sponsor")
    var isSponsor: Boolean = false
)

@Entity
@Table(name = "tournament_result_joined_view")
@IdClass(TournamentResultPK::class)
data class TournamentResultWithLinks(
    @Id var player: Int,
    @Id var tournament: Int,
    var name: String,
    var score: Int?,
    var place: Int?
)

@Entity
@Table(name = "tournament_rule_set_joined_view")
data class TournamentWithLinks(
    @Id var tournament: Int = 0,
    var place: String,
    @Column(name="start_date")
    var startDate: Timestamp,
    @Column(name="finish_date")
    var finishDate: Timestamp,
    var organizer: Int,
    @Column(name="rule_set")
    var ruleSet: Int,
    @Column(name="rule_set_name")
    var ruleSetName: String
)

@Entity
@Table(name = "character_rule_set_human_joined_view")
data class CharacterWithLinks(
    @Id var id: Int = 0,
    var name: String,
    @Column(name="rule_set")
    var ruleSet: Int,
    var player: Int,
    @Column(name="player_name")
    var playerName: String,
    @Column(name="rule_set_name")
    var ruleSetName: String
)

@Entity
@Table(name = "character_stat_value_and_type_joined_view")
@IdClass(CharacterStatPK::class)
data class CharacterStatWithType(
    @Id var character: Int,
    @Id var type: Int,
    @Column(name="type_name")
    var typeName: String,
    var value: Int
)

@Entity
@Table(name = "sponsor_contract_with_names_view")
data class SponsorContractWithLinks(
    @Id var id: Int = 0,
    var sponsor: Int,
    var organizer: Int,
    var info: String,
    var organizer_name: String,
    var sponsor_name: String
)

@Entity
@Table(name = "game_event_agent_object_joined_view")
data class GameEventWithLinks(
    @Id var id: Int = 0,
    var game: Int,
    var agent: Int,
    var `object`: Int,
    var description: String,
    var time: Timestamp,
    @Column(name="rule_applied")
    var ruleApplied: Int,
    @Column(name="agent_name")
    var agentName: String,
    @Column(name="object_name")
    var objectName: String
)

@Entity
@Table(name = "artist_performances_joined_view")
@IdClass(PerformancePK::class)
data class PerformanceWithName(
    @Id var artist: Int = 0,
    @Id var tournament: Int = 0,
    var name: String
)
