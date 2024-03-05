package com.shrey.newsbee

object ColorPicker {
    val colors = arrayOf(
        "#FF5733",
        "#7D3C98",
        "#1ABC9C",
        "#F4D03F",
        "#3498DB",
        "#E74C3C",
        "#27AE60",
        "#9B59B6",
        "#34495E",
        "#4169E1",
    )
    var colorIndex = 0
    fun getColor(): String {
        return colors[colorIndex++ % colors.size]
    }
}