package moura.renan.data.store

import io.reactivex.Completable
import io.reactivex.Observable
import moura.renan.data.model.ProjectEntity
import moura.renan.data.repository.ProjectsDataStore

class ProjectsRemoteDataStore : ProjectsDataStore {
    override fun getProjects(): Observable<List<ProjectEntity>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun clearProjects(): Completable {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getBookmarkedProjects(): Observable<List<ProjectEntity>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setProjectBookmarked(projectId: String): Completable {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setProjectNotBookmarked(projectId: String): Completable {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}