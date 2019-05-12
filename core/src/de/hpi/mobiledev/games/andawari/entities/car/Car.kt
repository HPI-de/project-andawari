package de.hpi.mobiledev.games.andawari.entities.car

import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.Body
import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.World
import com.badlogic.gdx.physics.box2d.joints.WheelJoint
import ktx.box2d.body
import ktx.box2d.wheelJointWith

class Car private constructor(val hull: Hull) {
    companion object {
        const val ACCELERATION = 1f
        private const val WHEEL_RADIUS = .25f

        fun forParameters(world: World): Car {
            val offset = Vector2(5f, 5f)

            val chassis = world.body {
                type = BodyDef.BodyType.DynamicBody
                box(1.5f, 0.5f, offset)
            }
            val wheelFront = world.body {
                type = BodyDef.BodyType.DynamicBody
                //                position.set(10f, 10f)
                circle(WHEEL_RADIUS, offset) {
                    friction = 0.7f
                    restitution = 0.7f
                    density = 0.6f
                }
            }
            val wheelFrontJoint = chassis.wheelJointWith(wheelFront) {
//                dampingRatio = 0.5f
//                enableMotor = true
//                localAnchorA.set(1f, 0.5f)
//                localAxisA.set(0f, 1f)
//                localAnchorB.set(0f, 0f)
            }
//            val wheelBack = world.body {
//                type = BodyDef.BodyType.DynamicBody
//                position.set(offset)
//                circle(WHEEL_RADIUS) {
//                    friction = 0.7f
//                    restitution = 0.7f
//                    density = 0.6f
//                }
//            }
//            wheelBack.applyTorque(1f, true)
//            val wheelBackJoint = chassis.wheelJointWith(wheelBack) {
//                dampingRatio = 0.5f
//                enableMotor = true
//                localAnchorA.set(2f, 0.5f)
//                localAxisA.set(0f, 1f)
//            }
            return Car(Hull(chassis, wheelFront, wheelFrontJoint))
        }
    }

    fun accelerate(forwards: Boolean) {
        val torque = if (forwards) ACCELERATION else -ACCELERATION
        hull.wheelFrontJoint.motorSpeed = -torque
//        hull.wheelBackJoint.motorSpeed = -torque
    }

    data class Hull(
        val chassis: Body,
        val wheelFront: Body,
        val wheelFrontJoint: WheelJoint
//        val wheelBack: Body,
//        val wheelBackJoint: WheelJoint
    )
}
