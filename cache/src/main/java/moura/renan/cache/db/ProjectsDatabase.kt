package moura.renan.cache.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import moura.renan.cache.dao.CachedProjectsDao
import moura.renan.cache.dao.ConfigDao
import moura.renan.cache.model.CachedProject
import moura.renan.cache.model.Config
import javax.inject.Inject

@Database(entities = [CachedProject::class, Config::class],version = 1)
abstract class ProjectsDatabase @Inject constructor() : RoomDatabase() {

    abstract fun cachedProjectsDao() : CachedProjectsDao
    abstract fun configDao() : ConfigDao

    private var INSTANCE : ProjectsDatabase? = null
    private val lock  = Any()

    fun getInstance(context: Context) : ProjectsDatabase {
        if (INSTANCE == null) {
            synchronized(lock){
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        ProjectsDatabase::class.java, "projects.db").build()
                }
            }
            return INSTANCE as ProjectsDatabase
        }
        return INSTANCE as ProjectsDatabase
    }

}