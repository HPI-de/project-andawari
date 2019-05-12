package de.hpi.mobiledev.games.andawari

import com.badlogic.gdx.assets.AssetDescriptor
import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.utils.Disposable

object Assets {
    fun allDescriptors(): List<AssetDescriptor<out Disposable>> {
        return Textures.values().map { it.descriptor }.toList();
    }

    enum class Textures(val fileName: String) {
        BadLogic("badlogic.jpg");

        val descriptor: AssetDescriptor<Texture>
        get() = AssetDescriptor(fileName, Texture::class.java)
    }

    // Here goes other stuff like fonts and music.
}

fun AssetManager.loadAll() {
    for (descriptor in Assets.allDescriptors()) {
        this.load(descriptor)
    }
}

operator fun AssetManager.get(texture: Assets.Textures): Texture = this.get(texture.descriptor)
