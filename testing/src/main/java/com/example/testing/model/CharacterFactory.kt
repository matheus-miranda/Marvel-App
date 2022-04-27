package com.example.testing.model

import br.com.msmlabs.core.domain.model.Character

class CharacterFactory {

    fun create(hero: Hero) = when (hero) {
        Hero.ABomb -> Character(
            id = 1011334,
            name = "A-Bomb (HAS)",
            imageUrl = "https://i.annihil.us/u/prod/marvel/i/mg/3/20/5232158de5b16.jpg"
        )
        Hero.ThreeDMan -> Character(
            id = 1011334,
            name = "3-D Man",
            imageUrl = "http://i.annihil.us/u/prod/marvel/i/mg/c/e0/535fecbbb9784.jpg"
        )
    }

    sealed class Hero {
        object ThreeDMan : Hero()
        object ABomb : Hero()
    }
}