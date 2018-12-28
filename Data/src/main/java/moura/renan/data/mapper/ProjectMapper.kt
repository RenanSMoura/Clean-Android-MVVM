package moura.renan.data.mapper

import moura.renan.data.model.ProjectEntity
import moura.renan.domain.model.Project
import javax.inject.Inject

class ProjectMapper @Inject constructor() : EntityMapper<ProjectEntity,Project>{

    override fun mapFromEntity(entity: ProjectEntity): Project {
//        TODO("Falta definir o valor para o isBookmarked")
        return Project(entity.id, entity.name, entity.fullName, entity.starCount,
            entity.dateCreated, entity.ownerName, entity.ownerAvatar,false)

    }

    override fun mapToEntity(domain: Project): ProjectEntity {
        return ProjectEntity(domain.id, domain.name, domain.fullName, domain.starCount,
        domain.dateCreated, domain.ownerName, domain.ownerAvatar)
    }
}