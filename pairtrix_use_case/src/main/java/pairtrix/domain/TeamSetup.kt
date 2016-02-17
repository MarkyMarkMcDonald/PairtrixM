package pairtrix.domain

import java.time.LocalDate

data class TeamSetup(val pairings: Set<Pairing>, val dateReported: LocalDate) : Comparable<TeamSetup> {

    override fun compareTo(other: TeamSetup): Int = other.dateReported.compareTo(dateReported)

}
