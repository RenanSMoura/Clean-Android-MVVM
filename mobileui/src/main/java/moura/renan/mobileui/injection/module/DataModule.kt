package moura.renan.mobileui.injection.module

import dagger.Binds
import dagger.Module
import moura.renan.data.ProjectsDataRepository


@Module
abstract class DataModule {

    @Binds
    abstract fun bindDataRepository(dataRepository: ProjectsDataRepository) : ProjectsDataRepository
}