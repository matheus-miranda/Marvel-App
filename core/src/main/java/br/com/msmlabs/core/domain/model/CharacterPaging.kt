package br.com.msmlabs.core.domain.model

data class CharacterPaging(
    val offset: Int,
    val total: Int,
    val characters: List<Character>
)
