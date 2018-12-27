package moura.renan.domain.usecase.DataFactory

import moura.renan.domain.module.Project
import java.util.*

object ProjectDataFactory {


    fun randomUuid() =  UUID.randomUUID().toString()

    fun randomBoolean() : Boolean = Math.random() < 0.5

    fun makeProject() = Project(randomUuid(), randomUuid(), randomUuid(), randomUuid(), randomUuid(), randomUuid(),
        randomUuid(), randomBoolean())

    fun makeProjectList(count : Int) : List<Project>  {
        val projects = mutableListOf<Project>()
        repeat(count) {
            projects.add(makeProject())
        }
        return projects
    }

}