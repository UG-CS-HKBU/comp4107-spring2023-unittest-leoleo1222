import org.junit.Assert.assertTrue
import org.junit.Test

class UnitTest {

    @Test
    fun testCaoDodgeAttack() {
        monarchHero = CaoCao()
        for(i in 0..6){
            heroes.add(NoneMonarchFactory.createRandomHero())
        }
        assertTrue(monarchHero.dodgeAttack())
    }

    @Test
    fun testBeingAttacked(){
        var hero: Hero? = null;
        hero = if(heroes.isEmpty()){
            for(i in 0..6){
                heroes.add(NoneMonarchFactory.createRandomHero())
            }
            heroes.random()
        }else
            NoneMonarchFactory.createRandomHero()
        val spy = object : Hero(hero.role){
            override val name = hero.name
            override fun beingAttacked() {
                hero.beingAttacked()
                assertTrue(hero.hp >= 0)
            }
        }
        for(i in 0..10)
            spy.beingAttacked()
    }

    object FakeNonmonarchFactory : GameObjectFactory {
        var count = 0
        var last: WeiHero? = null
        init {
            monarchHero = CaoCao()
        }
        override fun getRandomRole(): Role =
            MinisterRole()
        override fun createRandomHero(): Hero {
            val hero = when(count++) {
                0->SimaYi(getRandomRole())
                else->XuChu(getRandomRole())
            }
            val cao = monarchHero as CaoCao
            if (last == null)
                cao.helper = hero
            else
                last!!.setNext(hero)
            last = hero
            return hero
        }
    }

    object FakeMonarchFactory : GameObjectFactory {
        override fun getRandomRole(): Role = MonarchRole()
        override fun createRandomHero(): Hero = CaoCao()
    }

    class CaoCaoUnitTest {
        @Test
        fun testCaoDodgeAttack() {
            monarchHero = FakeMonarchFactory.createRandomHero() as MonarchHero
            for(i in 0..2){
                heroes.add(FakeNonmonarchFactory.createRandomHero())
            }
            assertTrue(monarchHero.dodgeAttack())
        }
    }

}