package StrikeMaster;

import StrikeMaster.Units.Unit;

import java.io.IOException;
import java.util.ArrayList;

/**
 * UnitManager class handles all units in a game.
 * Employs a singleton pattern to ensure there is
 * only one copy of this data throughout entire program.
 */
public class UnitManager {

    private static UnitManager instance;

    private static ArrayList<Unit> units = new ArrayList<>();
    private static int idNum = 0;
    private static UnitLibrary unitLibrary;

    /**
     * Instantiate singleton instance of UnitManager
     * @return TacticController - Always returns the original TacticController object
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

        units.add(UnitFactory.buidUnit(unitLibrary.getUnitData("AWS-9M"), idNum++));
        units.add(UnitFactory.buidUnit(unitLibrary.getUnitData("AS7-D"), idNum++));
        units.add(UnitFactory.buidUnit(unitLibrary.getUnitData("DRG-1N"), idNum++));
        units.add(UnitFactory.buidUnit(unitLibrary.getUnitData("KGC-0000"), idNum++));
        units.add(UnitFactory.buidUnit(unitLibrary.getUnitData("MAD-3R"), idNum++));
        units.add(UnitFactory.buidUnit(unitLibrary.getUnitData("RFL-3N"), idNum++));
        units.add(UnitFactory.buidUnit(unitLibrary.getUnitData("SHD-2H"), idNum++));
        units.add(UnitFactory.buidUnit(unitLibrary.getUnitData("TDR-5S"), idNum++));
        units.add(UnitFactory.buidUnit(unitLibrary.getUnitData("UM-R60"), idNum++));
        units.add(UnitFactory.buidUnit(unitLibrary.getUnitData("VLK-QA"), idNum++));
        units.add(UnitFactory.buidUnit(unitLibrary.getUnitData("VL-2T"), idNum++));
        /*units.add(UnitFactory.buidUnit(unitLibrary.getUnitData("VND-1R"), idNum++));
        units.add(UnitFactory.buidUnit(unitLibrary.getUnitData("VTR-9B"), idNum++));
        units.add(UnitFactory.buidUnit(unitLibrary.getUnitData("WHM-6R"), idNum++));
        units.add(UnitFactory.buidUnit(unitLibrary.getUnitData("WSP-1A"), idNum++));
        units.add(UnitFactory.buidUnit(unitLibrary.getUnitData("WVR-6R"), idNum++));
        units.add(UnitFactory.buidUnit(unitLibrary.getUnitData("ZEU-6S"), idNum++));
        units.add(UnitFactory.buidUnit(unitLibrary.getUnitData("ARC-2R"), idNum++));
        units.add(UnitFactory.buidUnit(unitLibrary.getUnitData("BLR-1G"), idNum++));
        units.add(UnitFactory.buidUnit(unitLibrary.getUnitData("CPLT-C1"), idNum++));
        units.add(UnitFactory.buidUnit(unitLibrary.getUnitData("HBK-4G"), idNum++));
        units.add(UnitFactory.buidUnit(unitLibrary.getUnitData("PNT-9R"), idNum++));
        units.add(UnitFactory.buidUnit(unitLibrary.getUnitData("CDA-2A"), idNum++));
        units.add(UnitFactory.buidUnit(unitLibrary.getUnitData("CGR-1A1"), idNum++));
        units.add(UnitFactory.buidUnit(unitLibrary.getUnitData("CN9-A"), idNum++));
        units.add(UnitFactory.buidUnit(unitLibrary.getUnitData("JM6-S"), idNum++));
        units.add(UnitFactory.buidUnit(unitLibrary.getUnitData("JR7-D"), idNum++));*/
        // end prototyping
    }
    
    public static Unit getUnit (int id){
        // TODO make better exception handling than returning a null

        for(Unit unit : units){
            if(unit.getID() == id) return unit;
        }
        return null;
    }



    public static int getUnitCount(){
        return units.size();
    }
}
