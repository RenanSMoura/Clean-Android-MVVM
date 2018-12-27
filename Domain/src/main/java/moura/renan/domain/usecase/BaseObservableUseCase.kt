package moura.renan.domain.usecase

import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import moura.renan.domain.executor.PostExecutionThread


//Ver aula 09 pq é meio embaçada essa parte
// A thread  que passa aqui como parametro serve que a thread aqui não seja a Main thread do pacote android

// T nesse caso é o dado que deve retornar
abstract class BaseObservableUseCase<T, in Params> constructor(private val postExecutionThread: PostExecutionThread) {

    private val disposables = CompositeDisposable()

    protected abstract fun buildUseCaseObservable(params: Params? = null) : Observable<T>

    open fun execute(observer : DisposableObserver<T>, params: Params? = null) {
        val observable = this.buildUseCaseObservable(params)
                        .subscribeOn(Schedulers.io())
            .observeOn(postExecutionThread.scheduler)

        addDisposable(observable.subscribeWith(observer))
    }


    fun dispose() {
        disposables.dispose()
    }

    private fun addDisposable(disposable: Disposable) {
        disposables.add(disposable)
    }

}