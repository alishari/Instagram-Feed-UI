package ir.setak.timeline.di

import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import ir.setak.timeline.MainApplication
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        AppModule::class,
        FragmentModule::class,
        ViewModelModule::class
    ]
)
interface AppComponent : AndroidInjector<MainApplication> {
    override fun inject(instance: MainApplication?)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: MainApplication): Builder

        fun build(): AppComponent
    }
}