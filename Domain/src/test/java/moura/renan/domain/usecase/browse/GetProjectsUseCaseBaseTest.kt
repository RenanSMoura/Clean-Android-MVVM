package moura.renan.domain.usecase.browse

import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Observable
import moura.renan.domain.executor.PostExecutionThread
import moura.renan.domain.module.Project
import moura.renan.domain.repository.ProjectsRepository
import moura.renan.domain.usecase.DataFactory.ProjectDataFactory
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class GetProjectsUseCaseBaseTest {

    private lateinit var getProjects : GetProjectsUseCaseBase
    @Mock lateinit var projectsRepository: ProjectsRepository
    @Mock lateinit var postExecutionThread: PostExecutionThread


    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        getProjects = GetProjectsUseCaseBase(projectsRepository,postExecutionThread)
    }


    @Test
    fun getProjectsComplete() {
        stubGetProjects(Observable.just(ProjectDataFactory.makeProjectList(5)))
        getProjects.buildUseCaseObservable().test().assertComplete()
    }

    @Test
    fun getProjectsReturnData() {
        val projects = ProjectDataFactory.makeProjectList(5)
        stubGetProjects(Observable.just(projects))
        getProjects.buildUseCaseObservable().test().assertValue(projects)
    }



    private fun stubGetProjects(observable : Observable<List<Project>>) {
        whenever(projectsRepository.getProjects()).thenReturn(observable)
    }

}