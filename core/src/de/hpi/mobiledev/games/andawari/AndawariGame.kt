package de.hpi.mobiledev.games.andawari

import com.badlogic.gdx.Screen
import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import de.hpi.mobiledev.games.andawari.screens.game.GameScreen
import de.hpi.mobiledev.games.andawari.screens.loading.LoadingScreen
import ktx.app.KtxGame

class AndawariGame : KtxGame<Screen>() {
    lateinit var batch: SpriteBatch
    lateinit var assets: AssetManager

    override fun create() {
        super.create()

        batch = SpriteBatch()
        assets = AssetManager()

        addScreen(LoadingScreen(this) {
            addScreen(GameScreen(this))
            setScreen<GameScreen>()
        })
        setScreen<LoadingScreen>()
    }

    override fun dispose() {
        super.dispose()

        batch.dispose()
        assets.dispose()
    }
}
