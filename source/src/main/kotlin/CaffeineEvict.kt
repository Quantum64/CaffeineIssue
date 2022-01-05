import com.github.benmanes.caffeine.cache.Caffeine
import com.github.benmanes.caffeine.cache.Expiry
import com.github.benmanes.caffeine.cache.RemovalCause
import com.github.benmanes.caffeine.cache.Scheduler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import java.util.concurrent.TimeUnit

var fooRemoved = false

fun main() {
    runBlocking(Dispatchers.Default) {
        val cache = Caffeine
            .newBuilder()
            .expireAfter(object : Expiry<String, Unit> {
                override fun expireAfterUpdate(key: String, value: Unit, time: Long, remaining: Long) = remaining
                override fun expireAfterRead(key: String, value: Unit, time: Long, remaining: Long) = remaining
                override fun expireAfterCreate(key: String, value: Unit, time: Long) = TimeUnit.SECONDS.toNanos(1)
            })
            .scheduler(Scheduler.systemScheduler())
            .removalListener { key: String?, value: Unit?, cause ->
                if (cause != RemovalCause.REPLACED && key != null && value != null) {
                    println("Removal listener called for $key")
                    if (key == "foo") fooRemoved = true
                }
            }
            .build<String, Unit>()

        cache.put("foo", Unit)

        delay(1500)

        cache.cleanUp()

        delay(500)

        println("Foo removed $fooRemoved")
    }
}