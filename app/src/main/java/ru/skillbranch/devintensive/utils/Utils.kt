package ru.skillbranch.devintensive.utils

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable


object Utils {
    private fun getElementIfAvailable(collection: List<String>?, index: Int): String? {
        return if (collection?.getOrNull(index) != null && collection[index].isNotEmpty()) collection[index] else null
    }

    fun parseFullName(fullName: String?): Pair<String?, String?> {
        val parts: List<String>? = fullName?.trim()?.split(" ")

        val firstName = getElementIfAvailable(parts, 0)
        val lastName = getElementIfAvailable(parts, 1)
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
        var result  = ""

        for (char in payload) {
            if (dictionary.containsKey(char)) {
                result += dictionary[char]
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
                initials += name.first().toString().toUpperCase()
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

    fun specifyTime(future: Boolean, timeString: String): String {
        return "${if (future) "через $timeString" else "$timeString назад"}"
    }

    fun textAsDrawable(text: String?, textSize: Float = 1000F, textColor: Int = Color.WHITE, backgroundColor: Int = Color.BLACK): Drawable {
        val paint = Paint(Paint.ANTI_ALIAS_FLAG)
        paint.isAntiAlias = true
        paint.textSize = textSize
        paint.color = textColor
        paint.textAlign = Paint.Align.LEFT
        val baseline: Float = -paint.ascent() // ascent() is negative
        val width = (paint.measureText(text) + 400).toInt() // round
        val height = (baseline + paint.descent() + 400).toInt()
        val image = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(image)
        canvas.drawColor(backgroundColor)
        if (text != null) {
            canvas.drawText(text, 200F, baseline + 200F, paint)
        }
        return BitmapDrawable(image)
    }
}
