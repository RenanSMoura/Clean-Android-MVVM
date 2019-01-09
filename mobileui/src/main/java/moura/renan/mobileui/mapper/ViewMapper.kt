package moura.renan.mobileui.mapper

interface ViewMapper<P,V> {
    fun mapToView(presentation : P) : V
}