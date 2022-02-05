package com.example.testing.model

import br.com.msmlabs.core.domain.model.Character

class CharacterFactory {

    fun create(hero: Hero) = when (hero) {
        Hero.ABomb -> Character("A-Bomb", "https://image.com")
        Hero.ThreeDMan -> Character("3-D Man", "https://image2.com")
    }

    sealed class Hero {
        object ThreeDMan : Hero()
        object ABomb : Hero()
    }
}