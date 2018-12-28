package moura.renan.data.mapper

import moura.renan.data.model.ProjectEntity
import moura.renan.data.store.factory.factory.ProjectFactory
import moura.renan.domain.model.Project
import org.junit.Assert.*
import org.junit.Test

class ProjectMapperTest{


    private val mapper = ProjectMapper()


    @Test
    fun mapFromEntityMapsData() {
        val entity = ProjectFactory.makeProjectEntity()
        val model = mapper.mapFromEntity(entity)
        assertEqualData(entity, model)
    }


    @Test
    fun mapFromDataToEntity() {
        val model = ProjectFactory.makeProject()
        val entity = mapper.mapToEntity(model)
        assertEqualData(entity,model)
    }

    private fun assertEqualData(entity: ProjectEntity, model : Project) {
        assertEquals(entity.id, model.id)
        assertEquals(entity.dateCreated, model.dateCreated)
        assertEquals(entity.fullName, model.fullName)
        assertEquals(entity.name, model.name)
        assertEquals(entity.ownerAvatar, model.ownerAvatar)
        assertEquals(entity.ownerName, model.ownerName)
        assertEquals(entity.starCount, model.starCount)
    }

}