package StrikeMaster.Units;

import StrikeMaster.Attack;
import StrikeMaster.Dice;
import StrikeMaster.MsgManager;
import StrikeMaster.UnitData;

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
        this.overheatMax = data.getOverheat();
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

    public int getIndirect(){
        int indirectLevel = 0;
        // TODO handle IF0* (min damage indirect) attacks
        // TODO consider reworking special abilities to be more flexible with IF values
        if(specialAbilities.contains("IF1")) indirectLevel = 1;
        if(specialAbilities.contains("IF2")) indirectLevel = 2;
        if(specialAbilities.contains("IF3")) indirectLevel = 3;
        if(specialAbilities.contains("IF4")) indirectLevel = 4;
        if(specialAbilities.contains("IF5")) indirectLevel = 5;
        if(specialAbilities.contains("IF6")) indirectLevel = 6;
        if(specialAbilities.contains("IF7")) indirectLevel = 7;
        if(specialAbilities.contains("IF8")) indirectLevel = 8;
        if(specialAbilities.contains("IF9")) indirectLevel = 9;
        return indirectLevel;
    }

    /**
     *
     * @param target the unit being attacked
     * @param overheat how much overheat to apply to the attack
     * @param range the range between the 2 units
     * @param toHit the required roll to hit the target
     * @return report of the attack
     */
    public String makeAttack(Unit target, int overheat, char range, int toHit, boolean indirect, boolean rearHit) {
        // do not accept incorrectly input attacks
        //if(indirect && getIndirect() == 0)
        //    return "*Attack Options Error* Unit does not have indirect fire ability. Please unselect Indirect checkbox";

        // apply effects of firing weapons
        this.heatCur += overheat;
        this.firedWepThisRound = true;
        this.fireComplete = true; // used to determine if the unit can make an attack

        // initialize the attackReport
        int attackRoll = Dice.roll2d6();
        String attackReport = faction + "'s " + name + " needed to roll a " + toHit
                + " and rolled a " + attackRoll;

        // resolve if the attack hit
        if (attackRoll < toHit){ // a miss
            attackReport += " and missed.";
            return attackReport;
        }
        // determine the damage
        // TODO make allowances for target's AMS or other defences here
        // TODO add minimum damage functionality in later release
        int damageValue = 0;
        // get the base damage
        if (getDmg( range ) != '*') damageValue = Character.getNumericValue(this.getDmg(range));
        // apply overheat damage only if short/med  range or has overheat long ability
        if (range == 's' || range == 'm' || specialAbilities.contains("OVL") ){
            damageValue += overheat;
        }
        // if attack is indirect use only indirect damage
        if(indirect){
            damageValue = getIndirect();
        }
        if ( attackRoll == 12) { // if thru-armor critical
            Attack newAttack = new Attack(damageValue, Attack.DamageType.REGULAR, true, rearHit);
            target.hit(newAttack);
            attackReport += " and hit " + target.getFaction() + "'s "
                    + target.getName() + " for " + newAttack.getTotalDamage() + " damage with critical damage.";
        }else{ // no thru-armor critical
            Attack newAttack = new Attack(damageValue, Attack.DamageType.REGULAR, false, rearHit);
            target.hit(newAttack);
            attackReport += " and hit " + target.getFaction() + "'s "
                    + target.getName() + " for " + newAttack.getTotalDamage() + " damage.";
        }
        return attackReport;
    }


    /**
     * Determines the effects of a critical hit on a unit.
     *
     * @return brief report on what was destroyed in the critical hit
     */
    @Override
    protected String resolveCritical() {
        String criticalReport = "";
        boolean validCrit = true;
        int critRoll = Dice.roll2d6();
        if (critRoll == 2) {
            validCrit = ammoCritHit();
            return "ammo";
        }
        if (critRoll == 3 || critRoll == 11) {
            validCrit = engineCritHit();
            return "engine";
        }
        if (critRoll == 4 || critRoll == 10) {
            validCrit = fireControlCritHit();
            return "fire control";
        }
        if (critRoll == 6 || critRoll == 8) {
            validCrit = wepCritHit();
            return "weapons";
        }
        if (critRoll == 7) {
            validCrit = mpCritHit();
            return "drive train";
        }
        if (critRoll == 12) {
            destroyed = true;
            immobile = true;
            return "unit destroyed";
        }
        if (!validCrit) extraDamage();
        return "no critical hit";
    }

    public String resolveDamage() {
        // if the unit received no damage
        if(hits.size() == 0){
            return faction + "'s " + name + " " + variant + " was unharmed this round.";
        }
        // if the unit took at least 1 hit
        int totalDamageThisTurn = 0;
        String damageReport = faction + "'s " + name + " " + variant + " took ";
        for (Attack hit : hits) {
            // TODO add handling of different damage types here in later release
            takeDamage(hit.getTotalDamage());
            totalDamageThisTurn += hit.getTotalDamage();
        }
        damageReport = damageReport + totalDamageThisTurn + " points of damage";
        String critcialHitsReport = "";
        for (int i = 0; i < criticalHits; i++) {
            String critReport = resolveCritical();
            // do not report on non-critical hits
            if(!critReport.equals("no critical hit")) {
                critcialHitsReport = critcialHitsReport + critReport;
                // add a comma if there is another critical report coming
                if (i != criticalHits - 1) critcialHitsReport = critcialHitsReport + ", ";
            }
        }
        // if there are any noteworthy critical hits, then add them to the damage report
        if(!critcialHitsReport.isEmpty()){
            damageReport = damageReport + " and took critical hits to " + critcialHitsReport;
        }

        // check if destroyed AFTER critical hits because extra damage could have occurred.
        if(structureCur <= 0){
            structureCur = 0;
            destroyed = true;
            immobile = true;
        }
        if (destroyed) damageReport = damageReport + " and was destroyed";
        else damageReport = damageReport + ".";

        return damageReport;
    }

    /**
     * Resolves a hit to internal ammo storage
     *
     * @return false if the crit was invalid and damage needs to be applied instead
     */
    public boolean ammoCritHit() {
        // TODO apply CASE rules
        // energy weapon only mechs are unaffected by ammo hits
        if (!specialAbilities.contains("ENE")) {
            // mechs susceptible to ammo hits are destroyed outright
            destroyed = true;
            immobile = true;
        }
        return true; // ammo hits on units without ammo never cause extra damage
    }

    /**
     * Resolves a hit to the reactor
     *
     * @return false if the crit was invalid and damage needs to be applied instead
     */
    public boolean engineCritHit() {
        // if the engine previously undamaged the unit takes one engine hit
        if (engHits == 0) engHits = 1;
        else {
            // engines can only take one hit. A second hit destroys the mech.
            destroyed = true;
            immobile = true;
        }
        return true; // all mechs are susceptible to engine hits
    }

    /**
     * Resolves a hit to the fire control systems
     *
     * @return false if the crit was invalid and damage needs to be applied instead
     */
    public boolean fireControlCritHit() {
        // if the fire control system was already destroyed take an extra point of damage instead
        if (FCHits == 4) return false;
        FCHits += 1;
        return true;
    }

    /**
     * Resolves a hit to the motive systems or drive train
     *
     * @return false if the crit was invalid and damage needs to be applied instead
     */
    public boolean mpCritHit() {
        // if the mp system was already destroyed take an extra point of damage instead
        if (MPHits == 4) return false;
        MPHits += 1;
        return true;
    }

    /**
     * Resolves a hit to the weapons
     *
     * @return false if the crit was invalid and damage needs to be applied instead
     */
    public boolean wepCritHit() {
        // if the weapons were already destroyed take an extra point of damage instead
        if (wepHits == 4) return false;
        wepHits += 1;
        return true;
    }


    @Override
    public char getType() {
        return 'm';
    }

    /**
     * @return the value of the current TMM
     */
    @Override
    public int getTMM() {
        // TODO replace literals with constants
        // if the unit moved it receives its current TMM
        if (this.movedThisRound) return this.TMMCur;
        // if the unit is shutdown or immobilized it is easier to hit
        if (this.shutdown || this.immobile) return -4;
        // if the unit did not move, but is not immobilized, then it gets no TMM
        return 0;
    }

    /**
     * @return the value of the current attacking move mod
     */
    @Override
    public int getAttackMoveMod() {
        // TODO replace literals with constants
        // if the unit jumped it gets a +2 to shot difficulty
        if (this.jumpedThisRound) return 2;
        // normal movement does not mod attack rolls. Not moving give attack difficulty a -1
        if (this.movedThisRound) return 0;
        else return -1;
    }

    public String toString() {
        String myString = new String();
        myString = "***** " + this.faction + " " + this.name + " " + this.variant + "  ID: " + String.valueOf(this.ID) +
                " ****\n";
        myString = myString + " Type: Mech  Size: " + String.valueOf(this.size) + "  Role: " + "_____" + "  PV: " +
                String.valueOf(this.PV) + "\n";
        myString = myString + " TMM: " + String.valueOf(this.TMMCur) + "  Move/Jump: " + String.valueOf(this.moveCur) +
                "/" + String.valueOf(this.jumpCur) + "  Overheat : " + String.valueOf(this.overheatMax) + "\n";
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
        if (wepHits == 4) return '0';
        // asterisks means minimum damage.
        if (dmg == '*') {
            if (this.wepHits > 0) return '0';
        } else {// if damage is represented by a number
            int dmgVal = Character.getNumericValue(dmg) - this.wepHits;
            // if damage has been reduced to nothing
            if (dmgVal <= 0) return '0';
            // convert int damage back to char the stupidest way possible by converting it to a string first
            dmg = String.valueOf(dmgVal).charAt(0);
        }
        return dmg;
    }


    /**
     * Resolves heat accumulation and shutdowns
     */
    public void resolveHeat() {


    }

    /**
     * Resets the unit at the end of the round
     */
    @Override
    public void endRound() {
        // clear out the hits and crits
        hits.clear();
        criticalHits = 0;

        if(!destroyed){

            // resolve heat
            // if no weapons fired heat is dissipated
            if(!this.firedWepThisRound) {
                heatCur = 0;
            } else {
                // if there is an engine hit then weapons fire raises heat by 1
                if (engHits > 0 && firedWepThisRound) heatCur++;
                // if in water then dissipate 1 unit of heat
                if (inWater && heatCur > 0) heatCur--;
                // shutdown if too hot
                if (heatCur >= 4) {
                    MsgManager.postMsg(this.faction + "'s " + this.name + " overheated to "
                            + this.heatCur + " and has shutdown.");
                    shutdown = true;
                }
            }
            // if heat has reduced back to zero then startup
            if (heatCur == 0 && shutdown) {
                MsgManager.postMsg(this.faction + "'s " + this.name
                        + " has cooled down to safe levels and has restarted");
                shutdown = false;
            }

            movedThisRound = false;
            sprinted = false;
            moveComplete = false;
            jumpedThisRound = false;
            firedWepThisRound = false;
            immobile = false;
            fireComplete = false;

            // each MPHit halves move/TMM/jump and each heat point lowers move by 1
            if(MPHits > 0){
                moveCur = moveMax / (2*MPHits);
                TMMCur = TMMMax / (2*MPHits);
                jumpCur = jumpMax / (2*MPHits);
            }else {
                moveCur = moveMax;
                TMMCur = TMMMax;
                jumpCur = jumpMax;
            }
            moveCur -= heatCur;
            if (moveCur < 1){
                moveCur = 0;
                immobile = true;
            }
            if (jumpCur < 1) jumpCur = 0;

            if(shutdown) {
                immobile = true;
                fireComplete = true;
            }
            if(immobile){
                moveCur = 0;
                jumpCur =  0;
                TMMCur = -4;
            }

        }
        else{ // destroyed mechs can't do much and don't take turns.
            movedThisRound = false;
            sprinted = false;
            moveComplete = true;
            jumpedThisRound = false;
            firedWepThisRound = false;
            immobile = false;
            fireComplete = true;
            TMMCur = -4;
            moveCur = 0;
            jumpCur = 0;
        }
    }
}
