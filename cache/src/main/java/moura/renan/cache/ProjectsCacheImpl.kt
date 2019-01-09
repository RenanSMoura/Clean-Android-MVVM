package moura.renan.cache

import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import moura.renan.cache.db.ProjectsDatabase
import moura.renan.cache.mapper.CachedProjectMapper
import moura.renan.cache.model.Config
import moura.renan.data.model.ProjectEntity
import moura.renan.data.repository.ProjectsCache
import javax.inject.Inject

//Testes na aula 48
class ProjectsCacheImpl @Inject constructor(
    private val projectsDatabase: ProjectsDatabase,
    private val cachedMapper: CachedProjectMapper
) : ProjectsCache {
    override fun clearProjects(): Completable {
        return Completable.defer {
            projectsDatabase.cachedProjectsDao().deleteProjects()
            Completable.complete()
        }
    }

    override fun saveProjects(projects: List<ProjectEntity>): Completable {
        return Completable.defer {
            projectsDatabase.cachedProjectsDao().insertProjects(projects.map { cachedMapper.mapToCached(it) })
            Completable.complete()

        }
    }

    override fun getProjects(): Observable<List<ProjectEntity>> {
        return projectsDatabase.cachedProjectsDao().getProjects().toObservable()
            .map { t -> t.map { cachedMapper.mapFromCached(it) } }
    }

    override fun getBookmarkedProjects(): Observable<List<ProjectEntity>> {
        return projectsDatabase.cachedProjectsDao().getBookmarkedProjects()
            .toObservable().map { it ->
                it.map {
                    cachedMapper.mapFromCached(it)
                }
            }
    }

    override fun setProjectAsBookmarked(projectId: String): Completable {
        return Completable.defer {
            projectsDatabase.cachedProjectsDao().setBookmarkStatus(true, projectId)
            Completable.complete()
        }
    }

    override fun setProjectAsNotBookmarked(projectId: String): Completable {
       return Completable.defer {
           projectsDatabase.cachedProjectsDao().setBookmarkStatus(false,projectId)
           Completable.complete()
       }
    }

    override fun areProjectsCached(): Single<Boolean> {
       return projectsDatabase.cachedProjectsDao().getProjects().isEmpty.map{!it}
    }

    override fun setLastCacheTime(lastCache: Long): Completable {
        return Completable.defer {
            projectsDatabase.configDao().insertConfig(Config(lastCacheTime = lastCache))
            Completable.complete()
        }
    }

    override fun isProjectsCacheExpired(): Single<Boolean> {
       val currentTime = System.currentTimeMillis()
        val expirationTime = (60 * 10 * 1000).toLong()
        return projectsDatabase.configDao().getConfig().single(Config(lastCacheTime = 0)).map {
            currentTime - it.lastCacheTime > expirationTime
        }
    }
}