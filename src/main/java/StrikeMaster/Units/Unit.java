package StrikeMaster.Units;

import java.util.ArrayList;

public class Unit {
    //identity
    protected   int ID;
    protected String variant;
    protected  String name;
    protected  int PV;
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

    public Unit (){
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

    public int getSkill(){
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

    public int getTMM(){
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
}


/* setting this aside in case these variables need to be again
//identity
    private int ID;
    private String variant;
    private String name;
    private int pv;
    //attributes
    private int armorMax;
    private int structureMax;
    private int maxMove;
    private int specialAbilities;
    private int size;
    private int overheat;
    private int tmmMax;
    private int shortDmg;
    private int medDmg;
    private int longDmg;
    private int extDmg;
    // condition
    private int moveCur;
    private int armorCur;
    private int structureCur;
    private int tmmCur;
    private int heatCur;
    private int engHit = 0;
    private int FCHit = 0;
    private int MPHit = 0;
    private int wepHit = 0;
    //status
    private boolean sprinted = false;
    private boolean moved = false;
    private boolean inWater = false;
    private boolean inWoods = false;
    private boolean destroyed = false;
    private boolean shutdown = false;
    private boolean hullDown = false;
    private boolean jumped = false;
    private boolean attacked = false;

 */
