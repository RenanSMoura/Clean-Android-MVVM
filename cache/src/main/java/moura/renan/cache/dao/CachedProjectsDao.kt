package moura.renan.cache.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import io.reactivex.Flowable
import moura.renan.cache.db.ProjectConstants.DELETE_PROJECTS
import moura.renan.cache.db.ProjectConstants.QUERY_BOOKMARKED_PROJECTS
import moura.renan.cache.db.ProjectConstants.QUERY_PROJECTS
import moura.renan.cache.db.ProjectConstants.QUERY_UPDATE_BOOKMARK_STATUS
import moura.renan.cache.model.CachedProject

@Dao
abstract class CachedProjectsDao {

    @Query(QUERY_PROJECTS)
    @JvmSuppressWildcards
    abstract fun getProjects(): Flowable<List<CachedProject>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @JvmSuppressWildcards
    abstract fun insertProjects(projects: List<CachedProject>)

    @Query(DELETE_PROJECTS)
    abstract fun deleteProjects()

    @Query(QUERY_BOOKMARKED_PROJECTS)
    abstract fun getBookmarkedProjects(): Flowable<List<CachedProject>>

    @Query(QUERY_UPDATE_BOOKMARK_STATUS)
    abstract fun setBookmarkStatus(isBookmarked: Boolean,
                                   projectId: String)

}