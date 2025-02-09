package StrikeMaster;

import StrikeMaster.Units.Unit;
import java.util.Observable;

import java.io.IOException;
import java.util.ArrayList;

/**
 * UnitManager class handles all units in a game.
 * Employs a singleton pattern to ensure there is
 * only one copy of this data throughout entire program.
 * Additionally, UserManger employs an Observer pattern to inform
 * other classes when there is a change to a unit.
 */
public class UnitManager extends Observable {

    private static UnitManager instance;

    private static int attackerId = 0;
    private static int targetId = 0;
    private static int moveId = 0;
    // TODO determine unit selection is necessary during move phase
    //private static int moveId = 0;
    private static int latestIdNum = 0;

    private static final ArrayList<Unit> units = new ArrayList<>();
    private static UnitLibrary unitLibrary;

    /**
     * Instantiate singleton instance of UnitManager
     * @return the only instance of UnitManger
     */
    public static UnitManager getInstance() {
        if (UnitManager.instance == null) {
            UnitManager.instance = new UnitManager();
        }
        return UnitManager.instance;
    }


    public UnitManager(){
        // TODO delete this next part. This is only to provide data for prototyping the panel.
        try {
           unitLibrary = new UnitLibrary();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        units.add(UnitFactory.buidUnit(unitLibrary.getUnitData("AWS-9M"), "Red", latestIdNum++));
        units.add(UnitFactory.buidUnit(unitLibrary.getUnitData("AS7-D"),"Red", latestIdNum++));
        units.add(UnitFactory.buidUnit(unitLibrary.getUnitData("ASN-21"),"Blu", latestIdNum++));
        units.add(UnitFactory.buidUnit(unitLibrary.getUnitData("DRG-1N"), "Blu",latestIdNum++));
        units.add(UnitFactory.buidUnit(unitLibrary.getUnitData("KGC-0000"), "Blu", latestIdNum++));
        units.add(UnitFactory.buidUnit(unitLibrary.getUnitData("MAD-3R"), "Blu",latestIdNum++));
        units.add(UnitFactory.buidUnit(unitLibrary.getUnitData("RFL-3N"), "Red",latestIdNum++));
        units.add(UnitFactory.buidUnit(unitLibrary.getUnitData("SHD-2H"), "Blu",latestIdNum++));
        units.add(UnitFactory.buidUnit(unitLibrary.getUnitData("CPLT-C1"),"Red", latestIdNum++));
        /*units.add(UnitFactory.buidUnit(unitLibrary.getUnitData("WSP-1A"), latestIdNum++));
        units.add(UnitFactory.buidUnit(unitLibrary.getUnitData("TDR-5S"), latestIdNum++));
        units.add(UnitFactory.buidUnit(unitLibrary.getUnitData("UM-R60"), latestIdNum++));
        units.add(UnitFactory.buidUnit(unitLibrary.getUnitData("VLK-QA"), latestIdNum++));
        units.add(UnitFactory.buidUnit(unitLibrary.getUnitData("VL-2T"), latestIdNum++));
        units.add(UnitFactory.buidUnit(unitLibrary.getUnitData("VND-1R"), latestIdNum++));
        units.add(UnitFactory.buidUnit(unitLibrary.getUnitData("VTR-9B"), latestIdNum++));
        units.add(UnitFactory.buidUnit(unitLibrary.getUnitData("WHM-6R"), latestIdNum++));
        units.add(UnitFactory.buidUnit(unitLibrary.getUnitData("WSP-1A"), latestIdNum++));
        units.add(UnitFactory.buidUnit(unitLibrary.getUnitData("WVR-6R"), latestIdNum++));
        units.add(UnitFactory.buidUnit(unitLibrary.getUnitData("ZEU-6S"), latestIdNum++));
        units.add(UnitFactory.buidUnit(unitLibrary.getUnitData("ARC-2R"), latestIdNum++));
        units.add(UnitFactory.buidUnit(unitLibrary.getUnitData("BLR-1G"), latestIdNum++));
        units.add(UnitFactory.buidUnit(unitLibrary.getUnitData("CPLT-C1"), latestIdNum++));
        units.add(UnitFactory.buidUnit(unitLibrary.getUnitData("HBK-4G"), latestIdNum++));
        units.add(UnitFactory.buidUnit(unitLibrary.getUnitData("PNT-9R"), latestIdNum++));
        units.add(UnitFactory.buidUnit(unitLibrary.getUnitData("CDA-2A"), latestIdNum++));
        units.add(UnitFactory.buidUnit(unitLibrary.getUnitData("CGR-1A1"), latestIdNum++));
        units.add(UnitFactory.buidUnit(unitLibrary.getUnitData("CN9-A"), latestIdNum++));
        units.add(UnitFactory.buidUnit(unitLibrary.getUnitData("JM6-S"), latestIdNum++));
        units.add(UnitFactory.buidUnit(unitLibrary.getUnitData("JR7-D"), latestIdNum++));*/

        // mark every unit as having moved for prototyping attack panel purposes.
       // for (Unit unit : units){
        //    unit.setMovedThisRound(true);
        //}
        // end prototyping
    }

    /**
     * Handles end-of-turn steps for all units
     */
    public static void endRound(){
        // each step must be in its own loop so that a step is completed for all units before
        // starting on the next step.
        // resolve damage for all units
        for(Unit unit : units){
            // TODO move resolveDamage() out of a print and into a text readout
            //if(!unit.isDestroyed()) System.out.println(unit.resolveDamage());
            if(!unit.isDestroyed()) {
                MsgManager.postMsg(unit.resolveDamage());
            }
        }
        // resolve heat for all units
        for(Unit unit : units){
            //unit.heat();
        }
        // reset attack and move for all units still in play
        for(Unit unit : units){
            unit.endRound();
            //System.out.println(unit);// debug
        }

        // inform observers that the units have been updated
        UnitManager.getInstance().updatedUnit();
    }

    /**
     * @param id the number of the unit to be retrieved
     * @return the requested unit
     */
    public static Unit getUnit (int id){
        // TODO make better exception handling than returning a null

        for(Unit unit : units){
            if(unit.getID() == id) return unit;
        }
        return null;
    }

    /**
     * @param id the ID number of the newly selected unit
     */
    public static void changeSelectedAttacker(int id){
        attackerId = id;
        UnitManager.getInstance().updatedUnit();
    }

    /**
     * @return the ID of the currently selected attacking unit
     */
    public static int getSelectedAttackerId(){
        return attackerId;
    }

    /**
     * @return the currently selected attacking unit
     */
    public static Unit getSelectedAttacker(){
        return units.get(attackerId);
    }

    /**
     * @param id the ID number of the newly selected unit
     */
    public static void changeSelectedTarget(int id){
        targetId = id;
        UnitManager.getInstance().updatedUnit();
    }

    /**
     * @return the ID of the currently selected targeted unit
     */
    public static int getSelectedTargetId(){
        return targetId;
    }

    /**
     * @return the currently selected targeted unit
     */
    public static Unit getSelectedTarget(){
        return units.get(targetId);
    }

    /**
     * @param id the ID number of the newly selected unit
     */
    public static void changeSelectedMoveUnit(int id){
        moveId = id;
        UnitManager.getInstance().updatedUnit();
    }

    /**
     * @return the ID of the currently selected targeted unit
     */
    public static int getSelectedMoveUnitId(){
        return moveId;
    }

    /**
     * @return the currently selected moving unit
     */
    public static Unit getSelectedMoveUnit(){
        return units.get(moveId);
    }

    /**
     * Needs to be called whenever a unit is updated.
     * Informs all observers that one or more units have been updated.
     * Uses methods from the java.util.Observable import.
     */
    public void updatedUnit(){
        /* TODO It might be safer to have the units monitor themselves for
        when they are updated. That way there will not be an error if a unit
        is updated and this method is not called.
         */
        setChanged();
        notifyObservers();
    }


    public static int getUnitCount(){
        return units.size();
    }
}
