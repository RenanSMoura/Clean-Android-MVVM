package moura.renan.mobileui.injection.module

import android.app.Application
import dagger.Binds
import dagger.Module
import dagger.Provides
import moura.renan.cache.ProjectsCacheImpl
import moura.renan.cache.db.ProjectsDatabase
import moura.renan.data.repository.ProjectsCache

@Module
abstract class CacheModule {

    @Module
    companion object {
        @Provides
        @JvmStatic
        fun providesDataBase(application: Application) : ProjectsDatabase {
            return ProjectsDatabase.getInstance(application)
        }
    }
    @Binds
    abstract fun bindProjectsCache(projectsCache: ProjectsCacheImpl) : ProjectsCache

}

