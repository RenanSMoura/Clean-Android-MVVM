package moura.renan.domain.usecase

import io.reactivex.Completable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableCompletableObserver
import io.reactivex.schedulers.Schedulers
import moura.renan.domain.executor.PostExecutionThread

// Ver aula 9 tbm
abstract class BaseCompletableUseCase<in Params> constructor(private val postExecutionThread: PostExecutionThread) {

    private val disposables = CompositeDisposable()

    abstract fun buildUseCaseCompletable(params: Params? = null) : Completable

    open fun execute(observer : DisposableCompletableObserver, params: Params? = null) {
        val observable = this.buildUseCaseCompletable(params)
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