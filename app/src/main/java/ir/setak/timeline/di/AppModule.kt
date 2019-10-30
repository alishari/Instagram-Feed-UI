package ir.setak.timeline.di

import android.content.Context
import dagger.Module
import dagger.Provides
import ir.setak.timeline.MainApplication

@Module
class AppModule {

    @Provides
    fun provideContext(application: MainApplication): Context {
        return application.applicationContext
    }
}
