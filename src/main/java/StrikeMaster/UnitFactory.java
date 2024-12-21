package StrikeMaster;

import StrikeMaster.Units.Mech;
import StrikeMaster.Units.Unit;

/**
 * This class creates
 */
public class UnitFactory {

    public static Unit buidUnit(UnitData data, int id){
        Unit builtUnit = null;
        //System.out.println(data.getClass()); // DEBUG
        switch (data.getType() ){
            case 'm':
                // TODO ID will have to be unique
                builtUnit = new Mech(data, id, "Test Faction");
                break;
        }

        return builtUnit;
    }

}
