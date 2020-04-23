package ru.skillbranch.devintensive.utils

object Utils {
    fun parseFullName(fullName:String?):Pair<String?,String?> {
        // TODO FIX ME
        val parts : List<String>? = fullName?.split(" ")

        val firstName = parts?.getOrNull(0)
        val lastName = parts?.getOrNull(1)
        // return Pair(firstName, lastName)
        return firstName to lastName
    }

    fun transliteration(payload: String, separator: String = " "): String {
        TODO("not implemented")
    }

    fun toInitials(firstName: String?, lastName: String?): String? {
        var initials = ""
        for (name in arrayOf(firstName?.trim(), lastName?.trim())) {
            if(name != null && name.isNotEmpty()) {
                initials += name.first().toString()
            }
        }
        return if(initials.isNotEmpty()) initials else "null"
    }
}
