package moura.renan.data.store.factory

import com.nhaarman.mockitokotlin2.mock
import moura.renan.data.store.ProjectsCacheDataStore
import moura.renan.data.store.ProjectsRemoteDataStore
import org.junit.Assert.*
import org.junit.Test

class ProjectsDataStoreFactoryTest {
    private val cacheStore = mock<ProjectsCacheDataStore>()
    private val remoteStore = mock<ProjectsRemoteDataStore>()
    private val factory = ProjectsDataStoreFactory(cacheStore,remoteStore)



    @Test
    fun `get data store returns remote when cache is expired`(){
        assertEquals(remoteStore,factory.getDataStore(true,true))
    }

    @Test
    fun `get data store returns remote when projects is not cached`(){
        assertEquals(remoteStore,factory.getDataStore(false,false))
    }


    @Test
    fun `get data store returns cache when projects are cached`(){
        assertEquals(cacheStore,factory.getDataStore(true,false))
    }

    @Test
    fun `get cache data store returns cached data store`() {
        assertEquals(cacheStore,factory.getCacheDataStore())
    }
}

