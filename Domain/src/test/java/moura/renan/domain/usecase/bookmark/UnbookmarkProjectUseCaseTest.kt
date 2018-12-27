package moura.renan.domain.usecase.bookmark

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Completable
import moura.renan.domain.executor.PostExecutionThread
import moura.renan.domain.repository.ProjectsRepository
import moura.renan.domain.usecase.DataFactory.ProjectDataFactory
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import java.lang.IllegalArgumentException

class UnbookmarkProjectUseCaseTest {
    private lateinit var bookmarkProject : UnbookmarkProjectUseCase
    @Mock
    lateinit var projectsRepository: ProjectsRepository
    @Mock
    lateinit var postExecutionThread: PostExecutionThread

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        bookmarkProject = UnbookmarkProjectUseCase(projectsRepository, postExecutionThread)
    }


    @Test
    fun `check if unbookmark project is completed`() {
        stubBookMarkProject(Completable.complete())
        bookmarkProject.buildUseCaseCompletable(Params.forProject(ProjectDataFactory.randomUuid()))
            .test().assertComplete()
    }

    @Test(expected = IllegalArgumentException::class)
    fun `check if throws exception when has not id`() {
        bookmarkProject.buildUseCaseCompletable().test()
    }

    private fun stubBookMarkProject(completable : Completable) {
        whenever(projectsRepository.unBookmarkProject(any())).thenReturn(completable)
    }
}