package com.starwars.lab7

data class StarWarAPIResp(
    val count: Int,
    val next: String,
    val previous: Any,
    val results: List<Result>
)