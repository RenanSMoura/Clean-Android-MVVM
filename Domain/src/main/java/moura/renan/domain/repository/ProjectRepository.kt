package moura.renan.domain.repository

import io.reactivex.Completable
import io.reactivex.Observable
import moura.renan.domain.module.Project


//O Repository vai permitir os outros modulos acessarem os dados que estão dentro do Domain Module
// sem necessariamente ter conhecimento da lógica de negócio
//Ver aula 7
interface ProjectRepository {

    fun getProjects() : Observable<List<Project>>

    fun bookmarkProject(projectId : String) : Completable

    fun unBookmarkProject(projectId: String) : Completable

    fun getBookmarkProjects() : Observable<List<Project>>
}