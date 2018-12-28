package moura.renan.data.store

import io.reactivex.Completable
import io.reactivex.Observable
import moura.renan.data.model.ProjectEntity
import moura.renan.data.repository.ProjectsCache
import moura.renan.data.repository.ProjectsDataStore
import javax.inject.Inject


// Esse carinha aqui é o que vai fornecer o acesso da camada de Data para a camada de CACHE
class ProjectsCacheDataStore @Inject constructor(
    private val projectsCache: ProjectsCache

) : ProjectsDataStore {


    override fun getProjects(): Observable<List<ProjectEntity>> {
        return projectsCache.getProjects()
    }


    override fun saveProjects(projects: List<ProjectEntity>): Completable {
        return projectsCache.saveProjects(projects)
            .andThen(projectsCache.setLastCacheTime(System.currentTimeMillis()))
    }

    override fun clearProjects(): Completable {
        return projectsCache.clearProjects()
    }

    override fun getBookmarkedProjects(): Observable<List<ProjectEntity>> {
        return projectsCache.getBookmarkedProjects()
    }

    override fun setProjectBookmarked(projectId: String): Completable {
        return projectsCache.setProjectAsBookmarked(projectId)
    }

    override fun setProjectNotBookmarked(projectId: String): Completable {
        return projectsCache.setProjectAsNotBookmarked(projectId)
    }
}