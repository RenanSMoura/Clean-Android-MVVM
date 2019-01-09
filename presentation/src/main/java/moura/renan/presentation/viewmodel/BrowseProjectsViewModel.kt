package moura.renan.presentation.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import io.reactivex.observers.DisposableCompletableObserver
import io.reactivex.observers.DisposableObserver
import moura.renan.domain.model.Project
import moura.renan.domain.usecase.bookmark.BookmarkProjectUsecase
import moura.renan.domain.usecase.bookmark.Params
import moura.renan.domain.usecase.bookmark.UnbookmarkProjectUseCase
import moura.renan.domain.usecase.browse.GetProjectsUseCaseBase
import moura.renan.presentation.mapper.ProjectViewMapper
import moura.renan.presentation.model.ProjectView
import moura.renan.presentation.state.Resource
import moura.renan.presentation.state.ResourceState
import javax.inject.Inject

class BrowseProjectsViewModel @Inject constructor(
    private val getProjects: GetProjectsUseCaseBase,
    private val bookmarkedProjectsUseCaseBase: BookmarkProjectUsecase,
    private val unbookmarkProjectUseCase: UnbookmarkProjectUseCase,
    private val mapper: ProjectViewMapper
) : ViewModel() {

    private val liveData: MutableLiveData<Resource<List<ProjectView>>> = MutableLiveData()


    override fun onCleared() {
        getProjects.dispose()
        super.onCleared()
    }


    fun bookmarkProject(projectId: String) {
        liveData.postValue(Resource(ResourceState.LOADING))
        return bookmarkedProjectsUseCaseBase.execute(object : DisposableCompletableObserver() {
            override fun onComplete() {
                liveData.postValue(Resource(ResourceState.SUCCESS, liveData.value?.data))
            }

            override fun onError(e: Throwable) {
                liveData.postValue(Resource(ResourceState.ERROR, message = e.localizedMessage))
            }

        }, Params.forProject(projectId))
    }


    fun unbookmarkProject(projectId: String) {
        liveData.postValue(Resource(ResourceState.LOADING))
        return unbookmarkProjectUseCase.execute(object : DisposableCompletableObserver() {
            override fun onComplete() {
                liveData.postValue(Resource(ResourceState.SUCCESS, liveData.value?.data))
            }

            override fun onError(e: Throwable) {
                liveData.postValue(Resource(ResourceState.ERROR, message = e.localizedMessage))
            }

        }, Params.forProject(projectId))
    }


    fun getProjects(): LiveData<Resource<List<ProjectView>>> {
        return liveData
    }

    fun fetchProjects() {
        liveData.postValue(Resource(ResourceState.LOADING))
        return getProjects.execute(ProjectsSubscriber())
    }


    inner class ProjectsSubscriber : DisposableObserver<List<Project>>() {
        override fun onComplete() {

        }

        override fun onNext(t: List<Project>) {
            liveData.postValue(Resource(ResourceState.SUCCESS, t.map { mapper.mapToView(it) }))
        }

        override fun onError(e: Throwable) {
            liveData.postValue(Resource(ResourceState.ERROR, message =  e.localizedMessage))
        }

    }

}