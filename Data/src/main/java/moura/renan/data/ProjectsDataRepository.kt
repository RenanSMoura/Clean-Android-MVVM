package moura.renan.data

import io.reactivex.Completable
import io.reactivex.Observable
import moura.renan.data.mapper.ProjectMapper
import moura.renan.data.store.ProjectsDataStoreFactory
import moura.renan.domain.module.Project
import moura.renan.domain.repository.ProjectsRepository
import javax.inject.Inject


//Aqui vai ficar a lógica de saber se pega do server ou se pega do cache
class ProjectsDataRepository
    @Inject constructor(private val projectMapper: ProjectMapper,
                        private val factory: ProjectsDataStoreFactory)  : ProjectsRepository{
    override fun getProjects(): Observable<List<Project>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun bookmarkProject(projectId: String): Completable {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun unBookmarkProject(projectId: String): Completable {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getBookmarkProjects(): Observable<List<Project>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}