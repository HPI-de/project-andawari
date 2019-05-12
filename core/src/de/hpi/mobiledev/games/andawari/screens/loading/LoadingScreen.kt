package de.hpi.mobiledev.games.andawari.screens.loading

import de.hpi.mobiledev.games.andawari.Assets

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.utils.viewport.ExtendViewport
import de.hpi.mobiledev.games.andawari.AndawariGame
import de.hpi.mobiledev.games.andawari.loadAll
import ktx.app.KtxScreen
import ktx.box2d.createWorld
import ktx.box2d.earthGravity

class LoadingScreen(val assets: AssetManager, val game: AndawariGame, val onComplete: () -> Unit) : KtxScreen {
    companion object {
        const val WIDTH_MIN = 20f
        const val HEIGHT_MIN = 10f
    }

    private val camera = OrthographicCamera().apply {
        setToOrtho(false, WIDTH_MIN, HEIGHT_MIN)
    }
    private val viewport = ExtendViewport(WIDTH_MIN, HEIGHT_MIN, camera)
    private val shapeRenderer = ShapeRenderer().apply {
        projectionMatrix = camera.combined
    }

    init {
        assets.loadAll()
    }

    override fun render(delta: Float) {
        super.render(delta)

        if (assets.update()) {
            onComplete() // We are done loading.
        }

        Gdx.gl.glClearColor(1f, 1f, 1f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

        game.batch.begin();
        with(shapeRenderer) {
            begin(ShapeRenderer.ShapeType.Filled)
            color = Color.RED
            rect(1f, 4.5f, 18f, 1f)
            end()

            begin(ShapeRenderer.ShapeType.Filled)
            color = Color.GREEN
            rect(1f, 4.5f, 18f * assets.progress, 1f)
            end()
        }
        game.batch.end();
    }

    override fun resize(width: Int, height: Int) {
        super.resize(width, height)

        viewport.update(width, height)
    }
}
