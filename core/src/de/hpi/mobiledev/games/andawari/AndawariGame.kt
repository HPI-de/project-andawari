package de.hpi.mobiledev.games.andawari

import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import de.hpi.mobiledev.games.andawari.screens.game.GameScreen
import ktx.app.KtxGame

class AndawariGame : KtxGame<Screen>() {
    lateinit var batch: SpriteBatch

    override fun create() {
        super.create()

        batch = SpriteBatch()

        addScreen(GameScreen(this))
        setScreen<GameScreen>()
    }

    override fun dispose() {
        super.dispose()

        batch.dispose()
    }
}
