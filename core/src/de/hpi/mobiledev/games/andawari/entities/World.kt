package de.hpi.mobiledev.games.andawari.entities

import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.World
import de.hpi.mobiledev.games.andawari.entities.car.Car
import de.hpi.mobiledev.games.andawari.entities.player.Player
import de.hpi.mobiledev.games.andawari.entities.world.Landscape
import de.hpi.mobiledev.games.andawari.entities.world.WorldProperties

class GameWorld(
        val props: WorldProperties,
        player: Player
) {

    val physicsWorld: World
    val cars: List<Car>
    val landscape: Landscape

    init {
        physicsWorld = World(Vector2(0f, props.gravity), true)
        cars = listOf(Car.forPlayer(player))
        landscape = props.generator.createLandscape()
    }

    fun step(deltaTime: Float) {

    }

    fun render(batch: Batch) {

    }
}