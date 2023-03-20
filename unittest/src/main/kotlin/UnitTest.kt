import org.junit.Assert.assertTrue
import org.junit.Test

class UnitTest {

    @Test
    fun test1(){
        heroes.clear()
        monarchHero = CaoCao()
        heroes.add(monarchHero)
        heroes.add(ZhangFei(MinisterRole()))
        assertTrue(monarchHero.name == "Cao Cao")
        println(heroes.size)

    }

    @Test
    fun test5(){
        println(heroes.size)
        assertTrue(heroes.size == 2)
    }

}