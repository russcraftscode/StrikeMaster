package StrikeMaster.Units;

import java.util.ArrayList;

public abstract class Unit {
    //identity
    protected int ID;
    protected String variant;
    protected String name;
    protected int PV;
    protected String faction;
    // pilot attributes
    protected int skill = 4; // default skill level
    //attributes
    protected int armorMax;
    protected int structureMax;
    protected int moveMax;
    protected int jumpMax;
    protected ArrayList<String> specialAbilities;
    protected int size;
    protected int overheat;
    protected int TMMMax;
    protected char shortDmg;
    protected char medDmg;
    protected char longDmg;
    protected char extDmg;
    // condition
    protected int moveCur;
    protected int jumpCur;
    protected int armorCur;
    protected int structureCur;
    protected int TMMCur;
    protected int heatCur;
    protected int engHits;
    protected int FCHits;
    protected int MPHits;
    protected int wepHits;
    //status
    protected boolean sprinted;
    protected boolean movedThisRound;
    protected boolean moveComplete;
    protected boolean fireComplete;
    protected boolean inWater;
    protected boolean inWoods;
    protected boolean destroyed;
    protected boolean shutdown;
    protected boolean immobile;
    protected boolean hullDown;
    protected boolean jumpedThisRound;
    protected boolean attackedThisRound;
    // hit trackers
    // damage value of each hit that must be resolved in end phase
    protected ArrayList<Integer> hits = new ArrayList<>();
    // the number of crits this unit must resolve in the end phase
    protected int criticalHits;

    public Unit() {
        this.specialAbilities = new ArrayList<>();
        this.engHits = 0;
        this.FCHits = 0;
        this.MPHits = 0;
        this.wepHits = 0;
        this.sprinted = false;
        this.movedThisRound = false;
        this.inWater = false;
        this.inWoods = false;
        this.destroyed = false;
        this.shutdown = false;
        this.immobile = false;
        this.hullDown = false;
        this.jumpedThisRound = false;
        this.attackedThisRound = false;
    }

    /**
     * @param damage the ammount of damage done in by the hit
     * @param crits  number of extra critcitals done by the attack. Does not include crits
     *               done by this attack's raw damage.
     */
    public void hit(int damage, int crits) {
        hits.add(damage);
        criticalHits += crits;
    }

    /**
     * If the unit received an invalid crit, apply one point of damage that cannot cause another
     * critical hit.
     */
    public void extraDamage(){
        if ( armorCur > 0) {
            armorCur--; // remove a point of armor if there is any to remove
        } else if (structureCur > 1) {
            structureCur--; // remove a point of structure if there is any to remove
        } else {
            // if there is no structure left then destroy the unit
            structureCur = 0;
            destroyed = true;
            immobile = true;
        }
    }

    public abstract String resolveDamage();

    /**
     * Applies damage from a hit first to armor and then to internal structure.
     * If the armor was penetrated it adds a critical hit that must be resolved
     * in the end phase of the turn.
     *
     * @param amountOfDamage the amount of damage this attack did
     */
    protected void takeDamage(int amountOfDamage) {
        boolean wentCrit = false;
        // if the damage overcomes the armor
        if (amountOfDamage > armorCur) {
            // apply damage left over after armor to internal structure
            structureCur -= amountOfDamage - armorCur;
            // if there is no structure left, destroy the unit
            if (structureCur < 0) {
                structureCur = 0;
                destroyed = true;
                immobile = true;
            }
            // zero out the armor because it has been used up
            armorCur = 0;
            // apply a critical hi
            this.criticalHits++;
        } else {// if the damage did not get through the armor
            armorCur -= amountOfDamage;
        }
    }

    /**
     * Must be overridden. Determines the effects of a critical hit on a unit.
     *
     * @return brief report on what was destroyed in the critical hit
     */
    protected abstract String resolveCritical();


    public char getType() {
        return 'x';
    }

    public int getID() {
        return ID;
    }

    public String getVariant() {
        return variant;
    }

    public String getName() {
        return name;
    }

    public int getPV() {
        return PV;
    }

    public String getFaction() {
        return faction;
    }

    public int getSkill() {
        return this.skill;
    }

    public int getArmorMax() {
        return armorMax;
    }

    public int getStructureMax() {
        return structureMax;
    }

    public int getMoveMax() {
        return moveMax;
    }

    public int getJumpMax() {
        return jumpMax;
    }

    public ArrayList<String> getSpecialAbilities() {
        return specialAbilities;
    }

    public int getSize() {
        return size;
    }

    public int getOverheat() {
        return overheat;
    }

    public int getTMMMax() {
        return TMMMax;
    }

    public char getShortDmg() {
        return shortDmg;
    }

    public char getMedDmg() {
        return medDmg;
    }

    public char getLongDmg() {
        return longDmg;
    }

    public char getExtDmg() {
        return extDmg;
    }

    public int getMoveCur() {
        return moveCur;
    }

    public int getJumpCur() {
        return jumpCur;
    }

    public int getArmorCur() {
        return armorCur;
    }

    public int getStructureCur() {
        return structureCur;
    }

    public int getTMMCur() {
        return TMMCur;
    }

    public int getTMM() {
        return 0;
    }

    public int getAttackMoveMod() {
        return 0;
    }

    public int getHeatCur() {
        return heatCur;
    }

    public int getEngHits() {
        return engHits;
    }

    public int getFCHits() {
        return FCHits;
    }

    public int getMPHits() {
        return MPHits;
    }

    public int getWepHits() {
        return wepHits;
    }

    public boolean didSprintThisRound() {
        return sprinted;
    }

    public boolean didMoveThisRound() {
        return movedThisRound;
    }

    public boolean isInWater() {
        return inWater;
    }

    public boolean isInWoods() {
        return inWoods;
    }

    public boolean isDestroyed() {
        return destroyed;
    }

    public boolean isShutdown() {
        return shutdown;
    }

    public boolean isImmobile() {
        return immobile;
    }

    public boolean isHullDown() {
        return hullDown;
    }

    public boolean didJumpThisRound() {
        return jumpedThisRound;
    }

    public boolean didAttackThisRound() {
        return attackedThisRound;
    }

    public boolean isFireComplete() {
        return fireComplete;
    }

    public boolean isMoveComplete() {
        return moveComplete;
    }

    public void setSkill(int skill) {
        this.skill = skill;
    }

    public void setWepHits(int wepHits) {
        this.wepHits = wepHits;
    }

    public void setMPHits(int MPHits) {
        this.MPHits = MPHits;
    }

    public void setFCHits(int FCHits) {
        this.FCHits = FCHits;
    }

    public void setEngHits(int engHits) {
        this.engHits = engHits;
    }

    public void setHeatCur(int heatCur) {
        this.heatCur = heatCur;
    }

    public void setStructureCur(int structureCur) {
        this.structureCur = structureCur;
    }

    public void setArmorCur(int armorCur) {
        this.armorCur = armorCur;
    }

    public void setSprinted(boolean sprinted) {
        this.sprinted = sprinted;
    }

    public void setJumped(boolean jumpped) {
        this.jumpedThisRound = jumpped;
    }

    public void setMovedThisRound(boolean movedThisRound) {
        this.movedThisRound = movedThisRound;
    }

    public void setAttackedThisRound(boolean attackedThisRound) {
        this.attackedThisRound = attackedThisRound;
    }

    public void setImmobile(boolean immobile) {
        this.immobile = immobile;
    }

    public void setShutdown(boolean shutdown) {
        this.shutdown = shutdown;
    }

    public void setDestroyed(boolean destroyed) {
        this.destroyed = destroyed;
    }

    public void setInWater(boolean inWater) {
        this.inWater = inWater;
    }
}
