package moura.renan.data.repository

import io.reactivex.Completable
import io.reactivex.Observable
import moura.renan.data.model.ProjectEntity

interface ProjectsDataStore {
    fun getProjects(): Observable<List<ProjectEntity>>

    fun saveProjects(projects: List<ProjectEntity>) : Completable

    fun clearProjects() : Completable

    fun getBookmarkedProjects(): Observable<List<ProjectEntity>>

    fun setProjectBookmarked(projectId: String): Completable

    fun setProjectNotBookmarked(projectId: String): Completable


}