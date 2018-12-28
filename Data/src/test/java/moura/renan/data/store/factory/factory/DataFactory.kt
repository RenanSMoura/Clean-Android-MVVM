package moura.renan.data.store.factory.factory

import moura.renan.domain.model.Project
import java.util.*
import java.util.concurrent.ThreadLocalRandom

object DataFactory {


    fun randomString() =  UUID.randomUUID().toString()

    fun randomBoolean() : Boolean = Math.random() < 0.5

    fun randomLong()  = randomInt().toLong()

    fun randomInt() = ThreadLocalRandom.current().nextInt(0,1000 + 1)

}