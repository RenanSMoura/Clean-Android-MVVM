package moura.renan.mobileui.thread

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import moura.renan.domain.executor.PostExecutionThread
import javax.inject.Inject

class UiThread @Inject constructor( ) : PostExecutionThread {
    override val scheduler: Scheduler
        get() = AndroidSchedulers.mainThread()

}