package moura.renan.domain.usecase.browse

import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Observable
import moura.renan.domain.executor.PostExecutionThread
import moura.renan.domain.model.Project
import moura.renan.domain.repository.ProjectsRepository
import moura.renan.domain.usecase.DataFactory.ProjectDataFactory
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class GetBookmarkedProjectsUseCaseBaseTest{
    private lateinit var getBookmarkProjects : GetBookmarkedProjectsUseCaseBase
    @Mock
    lateinit var projectsRepository: ProjectsRepository
    @Mock
    lateinit var postExecutionThread: PostExecutionThread

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        getBookmarkProjects = GetBookmarkedProjectsUseCaseBase(projectsRepository, postExecutionThread)
    }

    @Test
    fun `check if call is complete`(){
        stubGetBookMarkProjects(Observable.just(ProjectDataFactory.makeProjectList(2)))
        getBookmarkProjects.buildUseCaseObservable().test().assertComplete()
    }

    @Test
    fun `check if get all bookmark projects are returned`() {
        val projects = ProjectDataFactory.makeProjectList(3)
        stubGetBookMarkProjects(Observable.just(projects))
        getBookmarkProjects.buildUseCaseObservable().test().assertValue(projects)
    }


    private fun stubGetBookMarkProjects(observableList : Observable<List<Project>>) {
        whenever(projectsRepository.getBookmarkProjects()).thenReturn(observableList)
    }

}