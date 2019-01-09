package moura.renan.remote.module.mapper

import moura.renan.data.model.ProjectEntity
import moura.renan.remote.module.model.ProjectModel
import javax.inject.Inject


//37
class ProjectsResponseModelMapper @Inject constructor() : ModelMapper<ProjectModel, ProjectEntity> {

    override fun mapFromModel(model: ProjectModel): ProjectEntity {
        return ProjectEntity(
            model.id, model.name, model.fullName, model.starCount.toString(),
            model.dateCreated, model.owner.ownerName, model.owner.ownerAvatar)
    }

}