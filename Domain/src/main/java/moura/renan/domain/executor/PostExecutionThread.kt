package moura.renan.domain.executor

import io.reactivex.Scheduler


// Essa implementação dessa interface, serve para o RXJava usado no módulo de Domain
// não seja usado pelo módulo de Presentation, por que no módulo de Presentation,
// será usado o RxAndroid, o que iria resultar num braje de padrão dentro da clean archecture
//Fazendo assim que a nossa thread seja abstrata em relação a camada de Presentation
interface PostExecutionThread {
    val scheduler : Scheduler
}