package ir.alish.timeline.di

import android.content.Context
import dagger.Module
import dagger.Provides
import ir.alish.timeline.MainApplication

@Module
class AppModule {

    @Provides
    fun provideContext(application: MainApplication): Context {
        return application.applicationContext
    }
}
