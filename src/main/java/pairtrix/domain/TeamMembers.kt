package pairtrix.domain

import java.util.*

class TeamMembers(private val teamRepository: TeamMembersRepository) {

    fun withSeed(random: Random): List<String> {
        val randomizedList = ArrayList(teamRepository.findAll())
        Collections.shuffle(randomizedList, random)
        return randomizedList
    }

    fun add(name: String) {
        teamRepository.addMember(name)
    }
}
