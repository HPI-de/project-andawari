package de.hpi.mobiledev.games.andawari

import com.badlogic.gdx.assets.AssetDescriptor
import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.audio.Music
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.utils.Disposable

object Assets {
    fun allDescriptors(): List<AssetDescriptor<out Disposable>> {
        return Textures.values().map { it.toDescriptor() }.toList();
    }

    enum class Textures {
        BadLogic;

        fun toDescriptor(): AssetDescriptor<Texture> {
            val filename = when (this) {
                BadLogic -> "badlogic.jpg"
            }
            return AssetDescriptor(filename, Texture::class.java)
        }
    }

    // Here goes other stuff like fonts and music.
}

fun AssetManager.loadAll() {
    for (descriptor in Assets.allDescriptors()) {
        this.load(descriptor)
    }
}

fun AssetManager.get(texture: Assets.Textures): Texture = this.get(texture.toDescriptor())
