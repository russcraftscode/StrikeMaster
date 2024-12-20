package StrikeMaster;

/**
 * This class builds UnitData objects of the appropriate type based off strings pulled from the unit library csv.
 */
public class UnitDataFactory {

    /**
     * @param unitInfo String that holds all unit info
     * @return a UnitData child class object that is loaded with the data from unitInfo
     */
    public static UnitData buildUnitData (String unitInfo){
        // TODO make an enum for info string data
        // Format of info string: 0Name,1Variant,2Role,3Tech Base,4Rules,5Year,6Type,
        // 7Armor,8Internal,9TMM,10Move,11Jump,12S,13M,14L,15E,16OV,17Tons,18Size,19Skill,20PV,Special Abilities
        UnitData builtUnitData = null;
        //Split the unit info string into a string of attributes and a string of special abilities
        unitInfo = unitInfo.replaceAll("\"", "");
        String[] statSections = unitInfo.split("\\|,");
        // Split the stats section into sub strings
        String[] stats = statSections[0].split(",");
        //determine the unit type
        switch (stats[6]){
            case "BM":
                builtUnitData = new MechData();
                break;
        }
        // load the data into the unit data object
        if (builtUnitData != null) {
            builtUnitData.setName(stats[0]);
            builtUnitData.setVariant(stats[1]);
            builtUnitData.setArmorMax(Integer.parseInt(stats[7]));
            builtUnitData.setStructureMax(Integer.parseInt(stats[8]));
            builtUnitData.setTMMMax(Integer.parseInt(stats[9]));
            builtUnitData.setMoveMax(Integer.parseInt(stats[10]));
            builtUnitData.setJumpMax(Integer.parseInt(stats[11]));
            builtUnitData.setShortDmg( stats[12].charAt(0) );
            builtUnitData.setMedDmg( stats[13].charAt(0) );
            builtUnitData.setLongDmg( stats[14].charAt(0) );
            builtUnitData.setExtDmg( stats[15].charAt(0) );
            builtUnitData.setOverheat(Integer.parseInt(stats[16]));
            builtUnitData.setSize(Integer.parseInt(stats[18]));
            builtUnitData.setPV(Integer.parseInt(stats[20]));

            //if there are special abilities then there should be another section after the basic stats
            if(statSections.length > 1) {
                // split the special abilities into sub strings
                String[] abilities = statSections[1].split(",");
                for (String a : abilities) {
                    builtUnitData.addSpecialAbility(a);
                }
            }

        }
        return builtUnitData;
    }



}
