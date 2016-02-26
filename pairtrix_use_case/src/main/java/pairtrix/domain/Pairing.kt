package pairtrix.domain

import java.util.*

data class Pairing(val firstMember: String, val secondMember: String?) {
    private val members = ArrayList<String>()

    init {
        members.add(firstMember)
        if (secondMember != null) {
            members.add(secondMember)
        }
    }

    fun getMembers(): List<String> {
        return members
    }

    fun equals(other: Pairing): Boolean {
        return members.containsAll(other.getMembers())
    }
}
