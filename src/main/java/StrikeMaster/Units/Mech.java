package StrikeMaster.Units;

import StrikeMaster.UnitData;

import java.util.ArrayList;

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

    @Override
    public char getType(){
        return 'm';
    }

    /**
     * @return the value of the current TMM
     */
    @Override
    public int getTMM(){
        // TODO replace literals with constants
        // if the unit moved it recieves its current TMM
        if(this.movedThisRound) return this.TMMCur;
        // if the unit is shutdown or immobilized it is easier to hit
        if( this.shutdown || this.immobile) return -4;
        // if the unit did not move, but is not immobilized, then it gets no TMM
        return 0;
    }

    /**
     * @return the value of the current attacking move mod
     */
    @Override
    public int getAttackMoveMod(){
        // TODO replace literals with constants
        // if the unit jumped it gets a +2 to shot difficulty
        if(this.jumpedThisRound) return 2;
        // normal movement does not mod attack rolls. Not moving give attack difficulty a -1
        if(this.movedThisRound) return 0;
        else return -1;
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
        myString = myString + "  Engine Hits: " + String.valueOf(this.engHits) + "  Fire Control: " +
                String.valueOf(this.FCHits) + "\n";
        myString = myString + "  MP Hits: " + String.valueOf(this.MPHits) + "  Weapon Hits: " +
                String.valueOf(this.wepHits) + "\n ";
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
            if(this.wepHits > 0) return '0';
        }
        else{// if damage is represented by a number
            int dmgVal = Character.getNumericValue(dmg) - this.wepHits;
            // if damage has been reduced to nothing
            if(dmgVal <= 0) return '0';
            // convert int damage back to char the stupidest way possible by converting it to a string first
            dmg = String.valueOf(dmgVal).charAt(0);
        }
        return dmg;
    }


}
