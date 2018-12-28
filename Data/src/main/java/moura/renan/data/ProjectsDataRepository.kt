package moura.renan.data

import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.functions.BiFunction
import moura.renan.data.mapper.ProjectMapper
import moura.renan.data.repository.ProjectsCache
import moura.renan.data.store.factory.ProjectsDataStoreFactory
import moura.renan.domain.model.Project
import moura.renan.domain.repository.ProjectsRepository
import javax.inject.Inject

//Aula 25

class ProjectsDataRepository
@Inject constructor(
    private val projectMapper: ProjectMapper,
    private val factory: ProjectsDataStoreFactory,
    private val cache: ProjectsCache
) : ProjectsRepository {

    //Dentro desse cara, antes de chamar minha factory,
    // preciso pegar os valores de cache da minha aplicação para dar sequencia na lógica
    // Meu repositório não precisa saber de onde o dado está vindo
    // Essa responsabilidade é unica da minha factory
    override fun getProjects(): Observable<List<Project>> {
        return Observable.zip(
            cache.areProjectsCached().toObservable(),
            cache.isProjectsCacheExpired().toObservable(),

            BiFunction<Boolean, Boolean, Pair<Boolean, Boolean>> { areCached, isExpired ->
                Pair(areCached, isExpired)
            })

            .flatMap { factory.getDataStore(it.first, it.second).getProjects() }
            .flatMap { projects ->
                factory.getCacheDataStore().saveProjects(projects).andThen(Observable.just(projects))
            }.map {
                it.map {
                    projectMapper.mapFromEntity(it)
                }
            }
    }

    override fun bookmarkProject(projectId: String): Completable {
        return factory.getCacheDataStore().setProjectBookmarked(projectId)
    }

    override fun unBookmarkProject(projectId: String): Completable {
        return factory.getCacheDataStore().setProjectNotBookmarked(projectId)
    }

    override fun getBookmarkProjects(): Observable<List<Project>> {
        return factory.getCacheDataStore().getBookmarkedProjects().map {
            it.map { projectMapper.mapFromEntity(it) }
        }
    }
}