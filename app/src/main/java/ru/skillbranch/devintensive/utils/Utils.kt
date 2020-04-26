package ru.skillbranch.devintensive.utils

object Utils {
    fun parseFullName(fullName: String?): Pair<String?, String?> {
        // TODO FIX ME
        val parts: List<String>? = fullName?.split(" ")

        val firstName = parts?.getOrNull(0)
        val lastName = parts?.getOrNull(1)
        // return Pair(firstName, lastName)
        return firstName to lastName
    }

    fun transliteration(payload: String, separator: String = " "): String {
        val dictionary: HashMap<Char, String> = hashMapOf(
            ' ' to separator,
            'А' to "A",
            'Б' to "B",
            'В' to "V",
            'Г' to "G",
            'Д' to "D",
            'Е' to "E",
            'Ё' to "E",
            'Ж' to "Zh",
            'З' to "Z",
            'И' to "I",
            'Й' to "I",
            'К' to "K",
            'Л' to "L",
            'М' to "M",
            'Н' to "N",
            'О' to "O",
            'П' to "P",
            'Р' to "R",
            'С' to "S",
            'Т' to "T",
            'У' to "U",
            'Ф' to "F",
            'Х' to "H",
            'Ц' to "C",
            'Ч' to "Ch",
            'Ш' to "Sh",
            'Щ' to "Sh'",
            'Ъ' to "",
            'Ы' to "I",
            'Ь' to "",
            'Э' to "E",
            'Ю' to "Yu",
            'Я' to "Ya",
            'а' to "a",
            'б' to "b",
            'в' to "v",
            'г' to "g",
            'д' to "d",
            'е' to "e",
            'ё' to "e",
            'ж' to "zh",
            'з' to "z",
            'и' to "i",
            'й' to "i",
            'к' to "k",
            'л' to "l",
            'м' to "m",
            'н' to "n",
            'о' to "o",
            'п' to "p",
            'р' to "r",
            'с' to "s",
            'т' to "t",
            'у' to "u",
            'ф' to "f",
            'х' to "h",
            'ц' to "c",
            'ч' to "ch",
            'ш' to "sh",
            'щ' to "sh'",
            'ъ' to "",
            'ы' to "i",
            'ь' to "",
            'э' to "e",
            'ю' to "yu",
            'я' to "ya"
        )
        var result: String = ""

        for (char in payload) {
            if (dictionary.containsKey(char)) {
                result += dictionary[char];
            } else {
                result += char
            }
        }
        return result
    }

    fun toInitials(firstName: String?, lastName: String?): String? {
        var initials = ""
        for (name in arrayOf(firstName?.trim(), lastName?.trim())) {
            if (name != null && name.isNotEmpty()) {
                initials += name.first().toString()
            }
        }
        return if (initials.isNotEmpty()) initials else "null"
    }

    fun makePlural(count: Int, nominativeSingular: String, genitiveSingular: String, genitivePlural: String): String {
        val lastTwoDigits = count % 100
        val lastDigit = count % 10
        val word = if (lastDigit in 5..9 || lastTwoDigits in 11..19 || lastDigit == 0 || lastDigit >= 5) genitivePlural
        else if (lastDigit == 1) nominativeSingular
        else if (lastDigit in 2..4) genitiveSingular
        else throw IllegalArgumentException("incorrect input")

        return "$count $word"
    }
}
