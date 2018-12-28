package moura.renan.data.store.factory.factory

import moura.renan.data.model.ProjectEntity
import moura.renan.data.store.factory.factory.DataFactory.randomBoolean
import moura.renan.data.store.factory.factory.DataFactory.randomString
import moura.renan.domain.model.Project

object ProjectFactory {


    fun makeProjectEntity() = ProjectEntity(
        randomString(), randomString(), randomString(), randomString()
        , randomString(), randomString(), randomString()
    )

    fun makeProject() = Project(randomString(), randomString(), randomString(), randomString(), randomString(),
        randomString(), randomString(), randomBoolean())
}