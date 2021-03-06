package moura.renan.mobileui.injection.module

import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import moura.renan.domain.executor.PostExecutionThread
import moura.renan.mobileui.bookmarked.BookmarkedActivity
import moura.renan.mobileui.browse.BrowseActivity
import moura.renan.mobileui.thread.UiThread

@Module
abstract class UiModule {
    @Binds
    abstract fun bindPostExecutionThread(uiThread: UiThread) : PostExecutionThread

    @ContributesAndroidInjector
    abstract fun contributesBrowseActivity() : BrowseActivity

    @ContributesAndroidInjector
    abstract fun contributesBookmarkedActivity () : BookmarkedActivity

}