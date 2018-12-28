package moura.renan.data.store.factory

import moura.renan.data.repository.ProjectsDataStore
import moura.renan.data.store.ProjectsCacheDataStore
import moura.renan.data.store.ProjectsRemoteDataStore
import javax.inject.Inject

open class ProjectsDataStoreFactory @Inject constructor(
    private val projectsCacheDataStore: ProjectsCacheDataStore,
    private val projectsRemoteDataStore: ProjectsRemoteDataStore
) {


    open fun getDataStore(projectsCached: Boolean, cacheExpired: Boolean): ProjectsDataStore {
        return if (projectsCached && !cacheExpired) {
            projectsCacheDataStore
        } else {
            projectsRemoteDataStore
        }
    }


    //Esse carinha aqui, serve para pegar dados que não estão disponíveis na api
    // Ou seja, só pedem vir dados da camada de cache
    open fun getCacheDataStore(): ProjectsDataStore {
        return projectsCacheDataStore
    }

}