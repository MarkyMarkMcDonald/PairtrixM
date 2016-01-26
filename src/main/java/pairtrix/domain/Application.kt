package pairtrix.domain

import java.time.LocalDate
import java.util.*

class Application {

    private var teamMembers: TeamMembers? = null

    private var random: Random
    private val teamSetups = ArrayList<TeamSetup>()

    constructor(teamRepository: TeamMembersRepository) {
        teamMembers = TeamMembers(teamRepository)
        this.random = Random()
    }

    constructor(teamRepository: TeamMembersRepository, random: Random) {
        teamMembers = TeamMembers(teamRepository)
        this.random = random
    }

    fun pairings(): List<Pairing> {
        val team = randomTeamMembers
        if (team.size == 0) return listOf()


        return aggregateByTwo(team).map { Pairing(it.first, it.second) }
    }

    private fun aggregateByTwo(team: List<String>): List<Pair<String, String?>> {
        return team.zip(team.subList(1, team.size).plus(null)).filterIndexed { i, pair -> i % 2 == 0 }
    }

    private val randomTeamMembers: List<String>
        get() = teamMembers!!.withSeed(random)

    fun addTeamMember(name: String) {
        teamMembers!!.add(name)
    }

    @SafeVarargs
    fun recordPairings(dateReported: LocalDate, vararg stringPairings: List<String>) {
        val pairings = stringPairings.map { teamMembers -> Pairing(teamMembers[0], teamMembers[1]) }
        val teamSetup = TeamSetup(pairings, dateReported)
        teamSetups.add(teamSetup)
    }

    val previousTeamSetups: List<TeamSetup>
        get() {
            teamSetups.sort { obj, other -> obj.compareTo(other) }
            return teamSetups
        }
}
