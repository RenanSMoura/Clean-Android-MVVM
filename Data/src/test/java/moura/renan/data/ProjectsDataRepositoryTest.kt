package moura.renan.data

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.whenever
import moura.renan.data.mapper.ProjectMapper
import moura.renan.data.repository.ProjectsCache
import moura.renan.data.repository.ProjectsDataStore
import moura.renan.data.store.factory.ProjectsDataStoreFactory
import org.junit.Assert.*
import org.junit.Before
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock

@RunWith(JUnit4::class)
class ProjectsDataRepositoryTest {

    @Mock private lateinit var mapper : ProjectMapper
    @Mock private lateinit var factory : ProjectsDataStoreFactory
    @Mock private lateinit var cache : ProjectsCache
    @Mock private lateinit var store : ProjectsDataStore

    private val repository = ProjectsDataRepository(mapper,factory,cache)





    private fun stubFactoryGetDataStore() {
        whenever(factory.getDataStore(any(), any())).thenReturn(store)
    }
}