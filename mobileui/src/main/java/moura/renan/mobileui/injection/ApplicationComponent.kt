package moura.renan.mobileui.injection

import android.app.Application
import dagger.Binds
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import moura.renan.mobileui.GitHubTrendingApplication
import moura.renan.mobileui.injection.module.*
import javax.inject.Singleton


@Singleton
@Component(modules = [AndroidInjectionModule::class, ApplicationModule::class, UiModule::class, DataModule::class,
    PresentationModule::class, CacheModule::class,RemoteModule::class])
interface ApplicationComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): ApplicationComponent
    }

    fun inject(app: GitHubTrendingApplication)
}