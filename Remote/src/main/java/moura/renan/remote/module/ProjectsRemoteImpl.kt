package moura.renan.remote.module

import io.reactivex.Observable
import moura.renan.data.model.ProjectEntity
import moura.renan.data.repository.ProjectsRemote
import moura.renan.remote.module.mapper.ProjectsResponseModelMapper
import moura.renan.remote.module.service.GithubTrendingService
import javax.inject.Inject
//Testes na aulta 38
class ProjectsRemoteImpl @Inject constructor(
    private val service: GithubTrendingService,
    private val mapper: ProjectsResponseModelMapper
) : ProjectsRemote {
    override fun getPojects(): Observable<List<ProjectEntity>> {
        return service.searchRepositories("language:klotlin", "starts", "desc")
            .map {
                it.items.map { mapper.mapFromModel(it) }
            }
    }
}