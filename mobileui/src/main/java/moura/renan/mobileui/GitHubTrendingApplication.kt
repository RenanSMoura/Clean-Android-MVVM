package moura.renan.mobileui

import android.app.Activity
import android.app.Application
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import moura.renan.mobileui.injection.DaggerApplicationComponent
import timber.log.Timber
import javax.inject.Inject

class GitHubTrendingApplication : Application(), HasActivityInjector {

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Activity>

    override fun activityInjector(): AndroidInjector<Activity> {
        return androidInjector
    }

    override fun onCreate() {
        super.onCreate()
        setUpTimber()
        DaggerApplicationComponent.builder().application(this).build().inject(this)
    }


    private fun setUpTimber() {
        Timber.plant(Timber.DebugTree())
    }
}