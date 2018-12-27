package moura.renan.domain.usecase.browse

import io.reactivex.Completable
import moura.renan.domain.executor.PostExecutionThread
import moura.renan.domain.repository.ProjectsRepository
import moura.renan.domain.usecase.BaseCompletableUseCase
import java.lang.IllegalArgumentException
import javax.inject.Inject


//Ver Aula 12
class BookmarkProjectUsecase @Inject constructor(
    private val projectsRepository: ProjectsRepository,
    postExecutionThread: PostExecutionThread
) : BaseCompletableUseCase<Params>(postExecutionThread) {


    override fun buildUseCaseCompletable(params: Params?): Completable {
        if (params == null) throw IllegalArgumentException("Params Cant be null!")

        return projectsRepository.bookmarkProject(params.projectId)


    }
}