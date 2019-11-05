package ir.alish.timeline.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import ir.alish.timeline.ui.timeline.PostFragment


@Module
abstract class FragmentModule {
    @ContributesAndroidInjector
    internal abstract fun bindPostFragment(): PostFragment
}