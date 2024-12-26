import StrikeMaster.Attack;
import StrikeMaster.UnitFactory;
import StrikeMaster.UnitLibrary;
import StrikeMaster.Units.Unit;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;


// TODO update this to work with jUnit 5 when you get internet access again.
public class MechTest {
    /**
     * Tests creating a mech from the unit library
     */
    @Test
    public void testMechBuild() {
        UnitLibrary unitLibrary;
        try {
            unitLibrary = new UnitLibrary();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Unit testUnit = UnitFactory.buidUnit(unitLibrary.getUnitData("AWS-9M"), 0);

        // test ID info
        assertEquals(0, testUnit.getID()); // test id number
        assertEquals("AWS-9M", testUnit.getVariant());
        assertEquals("Awesome", testUnit.getName());
        assertEquals('m', testUnit.getType());
        assertEquals(41, testUnit.getPV());
        // test non-calculated data from library
        assertEquals(1, testUnit.getTMMMax());
        assertEquals(0, testUnit.getJumpMax());
        assertEquals('4', testUnit.getShortDmg());
        assertEquals('4', testUnit.getDmg('m'));
        assertEquals('3', testUnit.getDmg('l'));
        assertEquals('0', testUnit.getExtDmg());
        assertEquals(8, testUnit.getMoveMax());
        assertEquals(1, testUnit.getOverheat());
        assertEquals(4, testUnit.getSize());
    }

    /**
     * Tests the extra damage method
     */
    @Test
    public void testMechExtraDamage() {
        UnitLibrary unitLibrary;
        try {
            unitLibrary = new UnitLibrary();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Unit testUnit = UnitFactory.buidUnit(unitLibrary.getUnitData("AWS-9M"), 0);
        // test armor reduction
        int beforeHitArmor = testUnit.getArmorCur();
        int beforeHitStructure = testUnit.getStructureCur();
        testUnit.extraDamage();
        assertEquals(beforeHitArmor - 1, testUnit.getArmorCur());
        assertEquals(beforeHitStructure, testUnit.getStructureCur());
        assertFalse(testUnit.isDestroyed());

        // test last armor point hit
        testUnit.setArmorCur(1);
        beforeHitArmor = testUnit.getArmorCur();
        beforeHitStructure = testUnit.getStructureCur();
        testUnit.extraDamage();
        assertEquals(beforeHitArmor - 1, testUnit.getArmorCur());
        assertEquals(beforeHitStructure, testUnit.getStructureCur());
        assertFalse(testUnit.isDestroyed());

        // test hit with zero armor and full structure
        testUnit.setArmorCur(0); // yeah, it should be 0 already, but why risk it
        beforeHitArmor = testUnit.getArmorCur();
        beforeHitStructure = testUnit.getStructureCur();
        testUnit.extraDamage();
        assertEquals(beforeHitArmor, testUnit.getArmorCur()); // armor should stay 0
        // internal structure should go down
        assertEquals(beforeHitStructure - 1, testUnit.getStructureCur());
        assertFalse(testUnit.isDestroyed());

        // test destroying unit with extra damage
        testUnit.setArmorCur(0);
        testUnit.setStructureCur(1);
        beforeHitArmor = testUnit.getArmorCur();
        beforeHitStructure = testUnit.getStructureCur();
        testUnit.extraDamage();
        assertEquals(beforeHitArmor, testUnit.getArmorCur()); // armor should stay 0
        // internal structure should go down
        assertEquals(beforeHitStructure - 1, testUnit.getStructureCur());
        assertTrue(testUnit.isDestroyed());

    }

    /**
     * Tests the ResolveDamage method
     */
    @Test
    public void testMechResolveDamage() {
        UnitLibrary unitLibrary;
        try {
            unitLibrary = new UnitLibrary();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Unit testUnit = UnitFactory.buidUnit(unitLibrary.getUnitData("AWS-9M"), 0);
        // test armor reduction
        int beforeHitArmor = testUnit.getArmorCur();
        int beforeHitStructure = testUnit.getStructureCur();
        // hit the mech for 1 damage
        testUnit.hit(new Attack(1, Attack.DamageType.REGULAR, false, false ) );
        System.out.println(testUnit.resolveDamage());
        ;
        assertEquals(beforeHitArmor - 1, testUnit.getArmorCur());
        assertEquals(beforeHitStructure, testUnit.getStructureCur());
        assertFalse(testUnit.isDestroyed());

        // reset the unit
        testUnit = UnitFactory.buidUnit(unitLibrary.getUnitData("AWS-9M"), 0);

        // test last armor point hit
        testUnit.setArmorCur(1);
        beforeHitArmor = testUnit.getArmorCur();
        beforeHitStructure = testUnit.getStructureCur();
        testUnit.hit(new Attack(1, Attack.DamageType.REGULAR, false, false ) );
        System.out.println(testUnit.resolveDamage());
        ;
        assertEquals(beforeHitArmor - 1, testUnit.getArmorCur());
        assertEquals(beforeHitStructure, testUnit.getStructureCur());
        //assertFalse(testUnit.isDestroyed());

        // reset the unit
        testUnit = UnitFactory.buidUnit(unitLibrary.getUnitData("AWS-9M"), 0);

        // test hit with zero armor and full structure
        testUnit.setArmorCur(0);
        beforeHitArmor = testUnit.getArmorCur();
        beforeHitStructure = testUnit.getStructureCur();
        testUnit.hit(new Attack(1, Attack.DamageType.REGULAR, false, false ) );
        System.out.println(testUnit.resolveDamage());
        ;
        assertEquals(beforeHitArmor, testUnit.getArmorCur()); // armor should stay 0
        // internal structure should go down
        assertEquals(beforeHitStructure - 1, testUnit.getStructureCur());


        // reset the unit
        testUnit = UnitFactory.buidUnit(unitLibrary.getUnitData("AWS-9M"), 0);

        // test destroying unit
        testUnit.setArmorCur(0);
        testUnit.setStructureCur(1);
        beforeHitArmor = testUnit.getArmorCur();
        beforeHitStructure = testUnit.getStructureCur();
        testUnit.hit(new Attack(1, Attack.DamageType.REGULAR, false, false ) );
        System.out.println(testUnit.resolveDamage());
        ;
        assertEquals(beforeHitArmor, testUnit.getArmorCur()); // armor should stay 0
        // internal structure should go down
        assertEquals(beforeHitStructure - 1, testUnit.getStructureCur());
        assertTrue(testUnit.isDestroyed());

        // reset the unit
        testUnit = UnitFactory.buidUnit(unitLibrary.getUnitData("AWS-9M"), 0);

        // test destroying unit from a fresh state
        beforeHitArmor = testUnit.getArmorCur();
        beforeHitStructure = testUnit.getStructureCur();
        testUnit.hit(new Attack(50, Attack.DamageType.REGULAR, false, false ) );
        System.out.println(testUnit.resolveDamage());
        assertEquals(0, testUnit.getArmorCur()); // armor should be gone
        // internal structure should go down
        assertEquals(0, testUnit.getStructureCur()); // structure should be gone
        assertTrue(testUnit.isDestroyed());

    }

    /**
     * Tests ammo critical on ENE mechs and non ENE mechs
     */
    @Test
    public void testMechAmmoCritical() {
        // TODO update this when CASE is implemented
        UnitLibrary unitLibrary;
        try {
            unitLibrary = new UnitLibrary();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // make a mech without ammo
        Unit testUnit = UnitFactory.buidUnit(unitLibrary.getUnitData("AWS-8Q"), 0);

        // fail to blow it up
        assertFalse(testUnit.isDestroyed());
        assertFalse(testUnit.isImmobile());
        testUnit.ammoCritHit();
        assertFalse(testUnit.isDestroyed());
        assertFalse(testUnit.isImmobile());

        // make a mech with ammo
        testUnit = UnitFactory.buidUnit(unitLibrary.getUnitData("AS7-D"), 0);

        // fail to blow it up
        assertFalse(testUnit.isDestroyed());
        assertFalse(testUnit.isImmobile());
        testUnit.ammoCritHit();
        assertTrue(testUnit.isDestroyed());
        assertTrue(testUnit.isImmobile());

    }

    /**
     * Tests fire control critical on mechs
     */
    @Test
    public void testMechFcCritical() {
        UnitLibrary unitLibrary;
        try {
            unitLibrary = new UnitLibrary();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // make a mech to test
        Unit testUnit = UnitFactory.buidUnit(unitLibrary.getUnitData("AWS-8Q"), 0);

        // fail to blow it up
        assertEquals(0, testUnit.getFCHits());
        assertTrue(testUnit.fireControlCritHit());
        assertEquals(1, testUnit.getFCHits());
        assertTrue(testUnit.fireControlCritHit());
        assertEquals(2, testUnit.getFCHits());
        assertTrue(testUnit.fireControlCritHit());
        assertEquals(3, testUnit.getFCHits());
        assertTrue(testUnit.fireControlCritHit());
        assertEquals(4, testUnit.getFCHits());
        assertFalse(testUnit.fireControlCritHit()); // the 5th FC critical should be invalid
        assertEquals(4, testUnit.getFCHits()); // no additional FC hits should be marked

    }

    /**
     * Tests wep hits. Doesn't test ext damage
     */
    @Test
    public void testMechWepCritical() {
        UnitLibrary unitLibrary;
        try {
            unitLibrary = new UnitLibrary();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // make a mech with more than 4 damage
        Unit testUnit = UnitFactory.buidUnit(unitLibrary.getUnitData("ANH-1X"), 0);
        int beforeSDamage = Character.getNumericValue(testUnit.getDmg('s'));
        int beforeMDamage = Character.getNumericValue(testUnit.getDmg('m'));
        int beforeLDamage = Character.getNumericValue(testUnit.getDmg('l'));
        
        // give the mech 3 wep hits
        for (int i = 1; i <= 3; i++) {
            //give the mech 1 wep hit
            assertTrue(testUnit.wepCritHit());
            assertEquals(i , testUnit.getWepHits());
            System.out.println(testUnit.getDmg('s')
                    + " " + testUnit.getDmg('m')
                    + " " + testUnit.getDmg('l')); // DEBUG

            if (beforeSDamage > 0) {
                assertEquals(beforeSDamage - 1,
                        Character.getNumericValue(testUnit.getDmg('s')));
            } else {
                assertEquals(0, Character.getNumericValue(testUnit.getDmg('s')));
            }
            if (beforeMDamage > 0) {
                assertEquals(beforeMDamage - 1,
                        Character.getNumericValue(testUnit.getDmg('m')));
            } else {
                assertEquals(0, Character.getNumericValue(testUnit.getDmg('m')));
            }
            if (beforeLDamage > 0) {
                assertEquals(beforeLDamage - 1,
                        Character.getNumericValue(testUnit.getDmg('l')));
            } else {
                assertEquals(0, Character.getNumericValue(testUnit.getDmg('l')));
            }
            beforeSDamage = Character.getNumericValue(testUnit.getDmg('s'));
            beforeMDamage = Character.getNumericValue(testUnit.getDmg('m'));
            beforeLDamage = Character.getNumericValue(testUnit.getDmg('l'));
        }

        // test the 4th wep hit. Damamge should be 0 for all ranges
        assertTrue(testUnit.wepCritHit());
        assertEquals(4, testUnit.getWepHits());
        assertEquals(0, Character.getNumericValue(testUnit.getDmg('s')));
        assertEquals(0, Character.getNumericValue(testUnit.getDmg('m')));
        assertEquals(0, Character.getNumericValue(testUnit.getDmg('l')));

        // test the 5th wep hit, which should be invalid
        assertFalse(testUnit.wepCritHit());
        assertEquals(4, testUnit.getWepHits());
        assertEquals(0, Character.getNumericValue(testUnit.getDmg('s')));
        assertEquals(0, Character.getNumericValue(testUnit.getDmg('m')));
        assertEquals(0, Character.getNumericValue(testUnit.getDmg('l')));

    }

    /**
     * Tests engine hits. Doesn't test ext damage
     */
    @Test
    public void testMechEngineCritical() {
        // TODO add testing for adding +1 heat to firing weapons after the engine is hit
        UnitLibrary unitLibrary;
        try {
            unitLibrary = new UnitLibrary();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // make a mech to test
        Unit testUnit = UnitFactory.buidUnit(unitLibrary.getUnitData("AS7-D"), 0);
        // should start with no engine hits
        assertEquals(0, testUnit.getEngHits());
        // after 1 hit the mech should not be destroyed but have 1 engin hit marked
        testUnit.engineCritHit();
        assertEquals(1, testUnit.getEngHits());
        assertFalse(testUnit.isDestroyed());
        // after a 2nd engine hit mech should be destroyed and still have just 1 engine hit marked
        testUnit.engineCritHit();
        assertEquals(1, testUnit.getEngHits());
        assertTrue(testUnit.isDestroyed());
        assertTrue(testUnit.isImmobile());
    }

}
