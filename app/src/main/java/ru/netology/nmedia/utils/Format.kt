package ru.netology.nmedia.utils

object Format {
    fun formatNumber(number: Long): String {
        when {
            (number >= 1_000_000) -> {
                val newNumber = (number.toDouble() / 1000000)
                if (String.format("%.1f", newNumber).endsWith("0")) {
                    return String.format("%.0f", newNumber) + "M"
                }
                return String.format("%.1f", newNumber) + "M"
            }
            (number >= 10_000) -> {
                val newNumber = number / 1000
                return "$newNumber" + "K"
            }
            (number >= 1_000) -> {
                val newNumber: Double = (number.toDouble() / 1000)
                if (String.format("%.1f", newNumber).endsWith("0")) {
                    return String.format("%.0f", newNumber) + "K"
                }
                return String.format("%.1f", newNumber) + "K"
            }
            else -> return "$number"
        }
    }
}