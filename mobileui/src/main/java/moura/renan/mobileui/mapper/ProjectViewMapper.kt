package moura.renan.mobileui.mapper

import moura.renan.mobileui.model.Project
import moura.renan.presentation.model.ProjectView
import javax.inject.Inject

class ProjectViewMapper @Inject constructor() : ViewMapper<ProjectView, Project> {
    override fun mapToView(presentation: ProjectView): Project {
        return Project(
            presentation.id, presentation.name, presentation.fullName,
            presentation.starCount, presentation.dateCreated, presentation.ownerName, presentation.ownerAvatar,
            presentation.isBookmarked
        )
    }
}