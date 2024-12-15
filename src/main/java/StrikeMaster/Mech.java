package StrikeMaster;

public class Mech extends Unit {
    //public Mech (MechData data, int ID){
    public Mech(UnitData data, int ID, String side) {
        super();
        //identity
        this.faction = side;
        this.ID = ID;
        this.variant = data.getVariant();
        this.name = data.getName();
        this.PV = data.getPV();
        //attributes
        this.armorMax = data.getArmorMax();
        this.structureMax = data.getStructureMax();
        this.moveMax = data.getMoveMax();
        this.size = data.getSize();
        this.overheat = data.getOverheat();
        this.TMMMax = data.getTmmMax();
        this.shortDmg = data.getShortDmg();
        this.medDmg = data.getMedDmg();
        this.longDmg = data.getLongDmg();
        this.extDmg = data.getExtDmg();
        for (String a : data.getSpecialAbilities()) {
            this.specialAbilities.add(a);
        }
        //this.specialAbilities = data.getName();
        // condition
        this.moveCur = this.moveMax;
        this.armorCur = this.armorMax;
        this.structureCur = this.structureMax;
        this.TMMCur = this.TMMMax;
        this.heatCur = 0;

    }

    public String toString() {
        String myString = new String();
        myString = "***** "+  this.faction + " " + this.name + " " + this.variant + "  ID: " + String.valueOf(this.ID) +
                " ****\n";
        myString = myString + " Type: Mech  Size: " + String.valueOf(this.size) + "  Role: " + "_____" + "  PV: " +
                String.valueOf(this.PV) + "\n";
        myString = myString +  " TMM: " + String.valueOf(this.TMMCur) + "  Move/Jump: " + String.valueOf(this.moveCur) +
                "/" + String.valueOf(this.jumpCur) + "  Overheat : " + String.valueOf(this.overheat) + "\n";
        myString = myString +
                " Damage Short: " + this.getDmg('s') + "  Medium: " + this.getDmg('m') +
                "  Long: " + this.getDmg('l') + "  Extreme: " + this.getDmg('e') + "\n";
        myString = myString + " Armor: " + String.valueOf(this.armorCur) + "/" + String.valueOf(this.armorMax) +
                "  Internal Structure: " + String.valueOf(this.structureCur) + "/" + String.valueOf(this.structureMax) +
                "  Heat: " + String.valueOf(this.heatCur) + "\n";
        myString = myString + " Critical Hits:\n";
        myString = myString + "  Engine Hits: " + String.valueOf(this.engHit) + "  Fire Control: " +
                String.valueOf(this.FCHit) + "\n";
        myString = myString + "  MP Hits: " + String.valueOf(this.MPHit) + "  Weapon Hits: " +
                String.valueOf(this.wepHit) + "\n ";
        for (String a : this.specialAbilities) {
            myString = myString + a + ", ";
        }
        myString = myString + "\n**************************************\n";
        return myString;
    }

    /**
     * @param range s,m,l,e ranges
     * @return the current damage capability at the provided range
     */
    public char getDmg(char range) {
        //int dmg = 0;
        char dmg = '0';
        switch (range) {
            case 's':
                dmg = this.shortDmg;
                break;
            case 'm':
                dmg = this.medDmg;
                break;
            case 'l':
                dmg = this.longDmg;
                break;
            case 'e':
                dmg = this.extDmg;
                break;
        }
        // asterisks means minimum damage.
        if(dmg == '*') {
            if(this.wepHit > 0) return '0';
        }
        else{// if damage is represented by a number
            int dmgVal = Character.getNumericValue(dmg) - this.wepHit;
            // if damage has been reduced to nothing
            if(dmgVal <= 0) return '0';
            // convert int damage back to char the stupidest way possible by converting it to a string first
            dmg = String.valueOf(dmgVal).charAt(0);
        }
        return dmg;
    }
}
