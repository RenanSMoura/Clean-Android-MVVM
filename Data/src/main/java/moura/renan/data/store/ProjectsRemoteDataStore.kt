package moura.renan.data.store

import io.reactivex.Completable
import io.reactivex.Observable
import moura.renan.data.model.ProjectEntity
import moura.renan.data.repository.ProjectsDataStore
import moura.renan.data.repository.ProjectsRemote
import javax.inject.Inject

//Esse cara aqui vai fornecer acesso a camada de remote
class ProjectsRemoteDataStore @Inject constructor(
   private val projectsRemote : ProjectsRemote
) : ProjectsDataStore {
    override fun getProjects(): Observable<List<ProjectEntity>> {
       return  projectsRemote.getPojects()
    }

    override fun saveProjects(projects: List<ProjectEntity>): Completable {
        throw IllegalAccessException("Esse carinha n pode salvar")
    }

    override fun clearProjects(): Completable {
        throw IllegalAccessException("Esse carinha n pode limpar")
    }

    override fun getBookmarkedProjects(): Observable<List<ProjectEntity>> {
        throw IllegalAccessException("Esse carinha tbm não pega os projetos salvos")
    }

    override fun setProjectBookmarked(projectId: String): Completable {
        throw IllegalAccessException("Esse carinha não salva apenas um projeto")
    }

    override fun setProjectNotBookmarked(projectId: String): Completable {
        throw IllegalAccessException("PQ vc ainda ta implementando essa classe? Ela vai te dar erro na cara")
    }

}