package moura.renan.presentation.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import io.reactivex.observers.DisposableObserver
import moura.renan.domain.model.Project
import moura.renan.domain.usecase.browse.GetBookmarkedProjectsUseCase
import moura.renan.presentation.mapper.ProjectViewMapper
import moura.renan.presentation.model.ProjectView
import moura.renan.presentation.state.Resource
import moura.renan.presentation.state.ResourceState
import javax.inject.Inject

class BrowseBookmarkedProjectsViewModel @Inject constructor(
    private val getBookmarkProjectUsecase: GetBookmarkedProjectsUseCase,
    private val mapper: ProjectViewMapper
) : ViewModel() {

    private val liveData: MutableLiveData<Resource<List<ProjectView>>> = MutableLiveData()


    override fun onCleared() {
        getBookmarkProjectUsecase.dispose()
        super.onCleared()
    }

    fun getBookmarkProjects(): LiveData<Resource<List<ProjectView>>> {
        return liveData
    }

    fun fetchProjects() {
        liveData.postValue(Resource(ResourceState.LOADING))

        return getBookmarkProjectUsecase.execute(object : DisposableObserver<List<Project>>() {
            override fun onComplete() {}

            override fun onNext(t: List<Project>) {
                liveData.postValue(Resource(ResourceState.SUCCESS, t.map { mapper.mapToView(it) }))
            }

            override fun onError(e: Throwable) {
                liveData.postValue(Resource(ResourceState.ERROR, message = e.localizedMessage))
            }

        })
    }
}







