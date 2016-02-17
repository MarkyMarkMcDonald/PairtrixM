package pairtrix.domain

import java.time.LocalDate

data class TeamSetup(val pairings: Set<Pairing>, val dateReported: LocalDate) : Comparable<TeamSetup>, Set<Pairing> by pairings {

    override fun compareTo(other: TeamSetup): Int = other.dateReported.compareTo(dateReported)

    fun compairing(otherSetup: Set<Pairing>): Boolean {
        return intersect(otherSetup).isEmpty()
    }

}
