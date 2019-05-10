package de.hpi.mobiledev.games.andawari.screens.game

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.utils.viewport.ExtendViewport
import de.hpi.mobiledev.games.andawari.AndawariGame
import ktx.app.KtxScreen

class GameScreen(val game: AndawariGame) : KtxScreen {
    companion object {
        const val WIDTH_MIN = 20f
        const val HEIGHT_MIN = 10f
    }

    private val camera = OrthographicCamera().apply {
        setToOrtho(false, WIDTH_MIN, HEIGHT_MIN)
    }
    private val viewport = ExtendViewport(WIDTH_MIN, HEIGHT_MIN, camera)


    private val img = Texture("badlogic.jpg")

    override fun render(delta: Float) {
        super.render(delta)

        Gdx.gl.glClearColor(1f, 0f, 0f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

        camera.update()
        game.batch.projectionMatrix = camera.combined

        game.batch.begin()
        game.batch.draw(img, 0f, 0f, 1f, 1f)
        game.batch.end()
    }

    override fun resize(width: Int, height: Int) {
        super.resize(width, height)

        viewport.update(width, height)
    }
}
