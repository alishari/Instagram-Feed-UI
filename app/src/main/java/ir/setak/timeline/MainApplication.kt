package ir.setak.timeline

import android.content.Context
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import ir.setak.timeline.di.DaggerAppComponent
import javax.inject.Inject

class MainApplication : DaggerApplication() {

    @Inject
    lateinit var context: Context

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().application(this).build()
    }

    override fun onCreate() {
        super.onCreate()
    }

}
