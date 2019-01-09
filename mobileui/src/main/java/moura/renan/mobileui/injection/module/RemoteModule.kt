package moura.renan.mobileui.injection.module

import dagger.Binds
import dagger.Module
import dagger.Provides
import moura.renan.data.repository.ProjectsRemote
import moura.renan.mobileui.BuildConfig
import moura.renan.remote.module.ProjectsRemoteImpl
import moura.renan.remote.module.service.GithubTrendingService
import moura.renan.remote.module.service.GithubTrendingServiceFactory


@Module
abstract class RemoteModule {

    @Module
    companion object {
        @Provides
        @JvmStatic
        fun provideGitHubService() : GithubTrendingService {
            return GithubTrendingServiceFactory.makeGithubTrendingService(BuildConfig.DEBUG)
        }
    }

    @Binds
    abstract fun bindProjectsRemote(projectsRemote: ProjectsRemoteImpl) : ProjectsRemote
}