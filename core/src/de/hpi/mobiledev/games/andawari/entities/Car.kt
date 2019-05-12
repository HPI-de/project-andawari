package de.hpi.mobiledev.games.andawari.entities

import com.badlogic.gdx.physics.box2d.Body
import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.World
import ktx.box2d.body

class Car private constructor(val body: Body) {
    companion object {
        fun forParameters(world: World): Car {
            val body = world.body {
                type = BodyDef.BodyType.DynamicBody
                position.set(10f, 10f)
                circle(1f) {
                    friction = 0.7f
                    restitution = 0.7f
                    density = 0.6f
                }
            }
            return Car(body)
        }
    }
}
