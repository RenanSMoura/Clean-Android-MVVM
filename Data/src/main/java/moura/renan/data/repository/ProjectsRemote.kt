package moura.renan.data.repository

import io.reactivex.Observable
import moura.renan.data.model.ProjectEntity

//Ver Aula 21
interface ProjectsRemote {

    fun getPojects() : Observable<List<ProjectEntity>>
}