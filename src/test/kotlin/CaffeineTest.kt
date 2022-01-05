package test

import fooRemoved
import io.kotest.matchers.shouldBe
import main
import org.junit.jupiter.api.Test

class CaffeineTest {
    @Test
    fun evictionTest() {
        main()

        fooRemoved shouldBe true
    }
}