package de.hpi.mobiledev.games.andawari.entities

import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.Body
import com.badlogic.gdx.physics.box2d.World
import ktx.box2d.body

class Ground private constructor(val body: Body) {
    companion object {
        fun forParameters(world: World): Ground {
            val body = world.body {
                edge(from = Vector2(0f, 0f), to = Vector2(100f, 0f)) {
                    restitution = 0f
                }
            }
            return Ground(body)
        }
    }
}
