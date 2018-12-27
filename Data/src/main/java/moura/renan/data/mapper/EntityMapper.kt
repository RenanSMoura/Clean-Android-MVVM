package moura.renan.data.mapper


//É o carinha que vai modificar o objeto que vem da camada de domain para usar na camada de data
interface EntityMapper<E, D> {

    fun mapFromEntity(entity : E) : D

    fun mapToEntity(domain : D) : E
}