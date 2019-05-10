package de.hpi.mobiledev.games.andawari

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.utils.Disposable
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

abstract class BaseGame : ApplicationAdapter() {

    private val creators = mutableListOf<() -> Unit>()
    private val disposables = mutableListOf<() -> Unit>()


    // region Lifecycle
    override fun create() {
        super.create()

        creators.forEach { it() }
    }

    override fun dispose() {
        disposables.forEach { it() }

        super.dispose()
    }
    // endregion

    fun <T : Disposable> disposable(factory: () -> T): ReadOnlyProperty<BaseGame, T> {
        var value: T? = null
        creators += { value = factory() }
        disposables += { value?.dispose() }

        return object : ReadOnlyProperty<BaseGame, T> {
            override fun getValue(thisRef: BaseGame, property: KProperty<*>): T {
                return value ?: throw IllegalStateException("Property not initialized until create() is called")
            }
        }
    }
}
