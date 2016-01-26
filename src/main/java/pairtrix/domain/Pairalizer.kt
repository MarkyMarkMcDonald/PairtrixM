package pairtrix.domain

import java.util.*

interface Pairalizer {
    fun pairings(teamMembers: List<String>): List<Pairing>
}

class RandomPairalizer : Pairalizer {
    override fun pairings(teamMembers: List<String>): List<Pairing> {
        val randomizedTeam = randomize(teamMembers)
        if (randomizedTeam.size == 0) return listOf()
        return aggregateByTwo(randomizedTeam).map { Pairing(it.first, it.second) }
    }

    private fun randomize(teamMembers: List<String>): List<String> {
        val randomizedList = ArrayList(teamMembers)
        Collections.shuffle(randomizedList)
        return randomizedList
    }

    private fun aggregateByTwo(team: List<String>): List<Pair<String, String?>> {
        return team.zip(team.subList(1, team.size).plus(null)).filterIndexed { i, pair -> i % 2 == 0 }
    }
}

