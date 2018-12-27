package moura.renan.data

import moura.renan.data.mapper.ProjectMapper
import moura.renan.data.store.ProjectsDataStoreFactory
import javax.inject.Inject

class ProjectsDataRepository
    @Inject constructor(private val projectMapper: ProjectMapper, private val factory: ProjectsDataStoreFactory) {
}