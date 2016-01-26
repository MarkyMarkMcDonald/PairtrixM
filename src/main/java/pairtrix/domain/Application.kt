package pairtrix.domain

import java.time.LocalDate
import java.util.*

class Application(private val teamMembersRepository: TeamMembersRepository) {

    private val random: Random = Random()

    private var teamSetups = listOf<TeamSetup>()

    val teamMembers: TeamMembers = TeamMembers(teamMembersRepository)

    fun pairings(): List<Pairing> {
        val team = randomTeamMembers
        if (team.size == 0) return listOf()

        return aggregateByTwo(team).map { Pairing(it.first, it.second) }
    }

    private fun aggregateByTwo(team: List<String>): List<Pair<String, String?>> {
        return team.zip(team.subList(1, team.size).plus(null)).filterIndexed { i, pair -> i % 2 == 0 }
    }

    private val randomTeamMembers: List<String>
        get() = teamMembers.withSeed(random)

    fun addTeamMember(name: String) {
        teamMembers.add(name)
    }

    @SafeVarargs
    fun recordPairings(dateReported: LocalDate, vararg stringPairings: List<String>) {
        val pairings = stringPairings.map { teamMembers -> Pairing(teamMembers[0], teamMembers[1]) }
        val teamSetup = TeamSetup(pairings, dateReported)
        teamSetups = teamSetups.plus(teamSetup)
    }

    val previousTeamSetups: List<TeamSetup>
        get() {
            return teamSetups.sortedByDescending { it.dateReported }
        }
}
