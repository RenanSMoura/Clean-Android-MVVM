package moura.renan.domain.usecase.bookmark

import io.reactivex.Completable
import moura.renan.domain.executor.PostExecutionThread
import moura.renan.domain.repository.ProjectsRepository
import moura.renan.domain.usecase.BaseCompletableUseCase
import java.lang.IllegalArgumentException
import javax.inject.Inject


//Ver aula 13
class UnbookmarkProjectUseCase @Inject constructor(
    private val projectsRepository: ProjectsRepository,
    postExecutionThread: PostExecutionThread
) : BaseCompletableUseCase<Params>(postExecutionThread) {


    override fun buildUseCaseCompletable(params: Params?): Completable {
        if (params == null) throw IllegalArgumentException("Params Cant be null!")
        return projectsRepository.unBookmarkProject(params.projectId)
    }
}