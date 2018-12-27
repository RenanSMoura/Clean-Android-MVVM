package moura.renan.domain.usecase.bookmark

data class Params constructor(val projectId: String) {
    companion object {
        fun forProject(projectId: String): Params {
            return Params(projectId)
        }
    }
}