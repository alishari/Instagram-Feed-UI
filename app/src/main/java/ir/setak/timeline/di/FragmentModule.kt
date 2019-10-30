package ir.setak.timeline.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import ir.setak.timeline.ui.fragment.PostFragment


@Module
abstract class FragmentModule {
    @ContributesAndroidInjector
    internal abstract fun bindPostFragment(): PostFragment
}