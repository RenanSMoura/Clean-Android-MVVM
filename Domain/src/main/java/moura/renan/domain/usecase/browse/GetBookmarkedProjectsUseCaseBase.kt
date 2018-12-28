package moura.renan.domain.usecase.browse

import io.reactivex.Observable
import moura.renan.domain.executor.PostExecutionThread
import moura.renan.domain.usecase.BaseObservableUseCase
import moura.renan.domain.model.Project
import moura.renan.domain.repository.ProjectsRepository
import javax.inject.Inject

//Ver aula 11
class GetBookmarkedProjectsUseCaseBase @Inject constructor(
    private val projectsRepository: ProjectsRepository,
    postExecutionThread: PostExecutionThread
) : BaseObservableUseCase<List<Project>, Nothing>(postExecutionThread) {

    override fun buildUseCaseObservable(params: Nothing?): Observable<List<Project>> {
        return  projectsRepository.getBookmarkProjects()
    }
}