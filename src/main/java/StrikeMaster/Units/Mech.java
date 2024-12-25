package StrikeMaster.Units;

import StrikeMaster.Dice;
import StrikeMaster.UnitData;

import java.util.ArrayList;

public class Mech extends Unit {
    //public Mech (MechData data, int ID){
    public Mech(UnitData data, int ID, String side) {
        //super(); // TODO determine if this needed to be here
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
        // condition
        this.moveCur = this.moveMax;
        this.armorCur = this.armorMax;
        this.structureCur = this.structureMax;
        this.TMMCur = this.TMMMax;
        this.heatCur = 0;
    }

    /**
     * Determines the effects of a critical hit on a unit.
     * @return brief report on what was destroyed in the critical hit
     */
    @Override
    protected String resolveCritical() {
        String criticalReport = "";
        boolean validCrit = true;
        int critRoll = Dice.roll2d6();
        if(critRoll == 2){
            validCrit = ammoCritHit();
            return "ammo";
        }
        if(critRoll == 3 || critRoll == 11){
            validCrit = engineCritHit();
            return "engine";
        }
        if(critRoll == 4 || critRoll == 10){
            validCrit = fireControlCritHit();
            return "fire control";
        }
        if(critRoll == 6 || critRoll == 8){
            validCrit = wepCritHit();
            return "weapons";
        }
        if(critRoll == 7){
            validCrit = mpCritHit();
            return "drive train";
        }
        if(critRoll == 12){
            destroyed = true;
            immobile = true;
            return "unit destroyed";
        }
        if(!validCrit) extraDamage();
        return "no critical hit";
    }

    public String resolveDamage() {
        int totalDamageThisTurn = 0;
        String damageReport = faction + "'s " + name + " " + variant + " took ";
        for(Integer hit : hits){
            takeDamage(hit);
            totalDamageThisTurn =+ hit;
        }
        damageReport = damageReport + totalDamageThisTurn + " points of damage";
        if(criticalHits > 0) damageReport = damageReport +  " and took critical hits to ";
        for( int i = 0; i < criticalHits; i++){
            damageReport = damageReport +  resolveCritical();
            // add a comma if there is another critical report coming
            if(i != criticalHits -1) damageReport = damageReport + ", ";
        }

        if(isDestroyed())  damageReport = damageReport + " and was destroyed";
        else damageReport = damageReport + ".";

        return damageReport;
    }

    /**
     * Resolves a hit to internal ammo storage
     * @return false if the crit was invalid and damage needs to be applied instead
     */
    public boolean ammoCritHit(){
        // TODO apply CASE rules
        // energy weapon only mechs are unaffected by ammo hits
        if(!specialAbilities.contains("ENE")) {
            // mechs susceptible to ammo hits are destroyed outright
            destroyed = true;
            immobile = true;
        }
        return true; // ammo hits on units without ammo never cause extra damage
    }

    /**
     * Resolves a hit to the reactor
     * @return false if the crit was invalid and damage needs to be applied instead
     */
    public boolean engineCritHit(){
        // if the engine previously undamaged the unit takes one engine hit
        if (engHits == 0) engHits = 1;
        else{
            // engines can only take one hit. A second hit destroys the mech.
            destroyed = true;
            immobile = true;
        }
        return true; // all mechs are susceptible to engine hits
    }

    /**
     * Resolves a hit to the fire control systems
     * @return false if the crit was invalid and damage needs to be applied instead
     */
    public boolean fireControlCritHit(){
        // if the fire control system was already destroyed take an extra point of damage instead
        if (FCHits == 4)return false;
        FCHits += 1;
        return true;
    }

    /**
     * Resolves a hit to the motive systems or drive train
     * @return false if the crit was invalid and damage needs to be applied instead
     */
    public boolean mpCritHit(){
        // if the mp system was already destroyed take an extra point of damage instead
        if (MPHits == 4)return false;
        MPHits += 1;
        return true;
    }

    /**
     * Resolves a hit to the weapons
     * @return false if the crit was invalid and damage needs to be applied instead
     */
    public boolean wepCritHit(){
        // if the weapons were already destroyed take an extra point of damage instead
        if (wepHits == 4)return false;
        wepHits += 1;
        return true;
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
        // if the unit moved it receives its current TMM
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
        // TODO seriously consider if minimum damage (aka *) is worth implementing
        //int dmg = 0;
        char dmg = '0';
        switch (range) {
            case 's':
                dmg = shortDmg;
                break;
            case 'm':
                dmg = medDmg;
                break;
            case 'l':
                dmg = longDmg;
                break;
            default: // there is no extreme range for a mech, so default to long
                dmg = longDmg;
                break;
        }
        // 4 wep hits means the mech is disarmed
        if ( wepHits == 4) return '0';
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
