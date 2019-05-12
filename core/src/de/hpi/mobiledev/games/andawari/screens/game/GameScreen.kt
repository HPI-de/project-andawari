package de.hpi.mobiledev.games.andawari.screens.game

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer
import com.badlogic.gdx.utils.viewport.ExtendViewport
import de.hpi.mobiledev.games.andawari.AndawariGame
import de.hpi.mobiledev.games.andawari.entities.car.Car
import de.hpi.mobiledev.games.andawari.Assets
import de.hpi.mobiledev.games.andawari.entities.Ground
import de.hpi.mobiledev.games.andawari.get
import ktx.app.KtxScreen
import ktx.box2d.createWorld
import ktx.box2d.earthGravity
import kotlin.math.min

class GameScreen(assets: AssetManager, val game: AndawariGame) : KtxScreen {
    companion object {
        const val WIDTH_MIN = 20f
        const val HEIGHT_MIN = 10f

        const val TIME_STEP = 1 / 60f
        const val TIME_STEP_MAX = 1 / 4f
        const val TIME_ITERATIONS_VELOCITY = 6
        const val TIME_ITERATIONS_POSITION = 2
    }

    private val camera = OrthographicCamera().apply {
        setToOrtho(false, WIDTH_MIN, HEIGHT_MIN)
    }
    private val viewport = ExtendViewport(WIDTH_MIN, HEIGHT_MIN, camera)

    private val world = createWorld(gravity = earthGravity)
    private val debugRenderer = Box2DDebugRenderer()
    private var physicsTimeAcc = 0f

    private val ground = Ground.forParameters(world)
    private val car = Car.forParameters(world)

    private val img = assets.get(Assets.Textures.BadLogic)

    override fun render(delta: Float) {
        super.render(delta)

        // Draw
        Gdx.gl.glClearColor(1f, 1f, 1f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

        camera.update()
        game.batch.projectionMatrix = camera.combined

        debugRenderer.render(world, camera.combined)

        game.batch.begin()
        game.batch.end()


        // Update
        if (Gdx.input.isTouched)
            car.accelerate(true)
        step(delta)
    }

    private fun step(deltaTime: Float) {
        // fixed time step
        // max frame time to avoid spiral of death (on slow devices)
        val frameTime = min(deltaTime, TIME_STEP_MAX)
        physicsTimeAcc += frameTime
        while (physicsTimeAcc >= TIME_STEP) {
            world.step(TIME_STEP, TIME_ITERATIONS_VELOCITY, TIME_ITERATIONS_POSITION)
            physicsTimeAcc -= TIME_STEP
        }
    }

    override fun resize(width: Int, height: Int) {
        super.resize(width, height)

        viewport.update(width, height)
    }
}
