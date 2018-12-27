package moura.renan.data.store

import moura.renan.data.repository.ProjectsDataStore
import javax.inject.Inject

class ProjectsDataStoreFactory @Inject constructor(
    private val projectsCacheDataStore: ProjectsCacheDataStore,
    private val projectsRemoteDataStore: ProjectsRemoteDataStore
) {

    fun getCachedDataStore() = projectsCacheDataStore
    

    fun getRemoteDataStore() = projectsRemoteDataStore
}