package de.hpi.mobiledev.games.andawari.entities.world

class WorldProperties(
        val gravity: Float = DEFAULT_GRAVITY,
        val friction: Float = DEFAULT_FRICTION,
        val generator: LandscapeGenerator
) {
    companion object {
        const val DEFAULT_GRAVITY = -10f
        const val DEFAULT_FRICTION = 1f
    }
}