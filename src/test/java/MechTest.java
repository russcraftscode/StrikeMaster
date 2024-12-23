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
        assertEquals("AWS-9M",testUnit.getVariant());
        assertEquals("Awesome",testUnit.getName());
        assertEquals('m',testUnit.getType());
        assertEquals(41, testUnit.getPV());
        // test non-calculated data from library
        assertEquals(1, testUnit.getTMMMax());
        assertEquals(0, testUnit.getJumpMax());
        assertEquals('4', testUnit.getShortDmg());
        assertEquals('4', testUnit.getMedDmg());
        assertEquals('3', testUnit.getLongDmg());
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
        assertEquals(beforeHitArmor , testUnit.getArmorCur()); // armor should stay 0
        // internal structure should go down
        assertEquals(beforeHitStructure-1, testUnit.getStructureCur());
        assertFalse(testUnit.isDestroyed());

        // test destroying unit with extra damage
        testUnit.setArmorCur(0);
        testUnit.setStructureCur(1);
        beforeHitArmor = testUnit.getArmorCur();
        beforeHitStructure = testUnit.getStructureCur();
        testUnit.extraDamage();
        assertEquals(beforeHitArmor , testUnit.getArmorCur()); // armor should stay 0
        // internal structure should go down
        assertEquals(beforeHitStructure-1, testUnit.getStructureCur());
        assertTrue(testUnit.isDestroyed());

    }
    /**
     * Tests the extra damage method
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
        assertEquals(beforeHitArmor , testUnit.getArmorCur()); // armor should stay 0
        // internal structure should go down
        assertEquals(beforeHitStructure-1, testUnit.getStructureCur());
        assertFalse(testUnit.isDestroyed());

        // test destroying unit with extra damage
        testUnit.setArmorCur(0);
        testUnit.setStructureCur(1);
        beforeHitArmor = testUnit.getArmorCur();
        beforeHitStructure = testUnit.getStructureCur();
        testUnit.extraDamage();
        assertEquals(beforeHitArmor , testUnit.getArmorCur()); // armor should stay 0
        // internal structure should go down
        assertEquals(beforeHitStructure-1, testUnit.getStructureCur());
        assertTrue(testUnit.isDestroyed());

    }
}
