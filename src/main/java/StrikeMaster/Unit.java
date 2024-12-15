package StrikeMaster;

import java.util.ArrayList;

public class Unit {
    //identity
    int ID;
    String variant;
    String name;
    int PV;
    String faction;
    //attributes
    int armorMax;
    int structureMax;
    int moveMax;
    int jumpMax;
    ArrayList<String> specialAbilities;
    int size;
    int overheat;
    int TMMMax;
    char shortDmg;
    char medDmg;
    char longDmg;
    char extDmg;
    // condition
    int moveCur;
    int jumpCur;
    int armorCur;
    int structureCur;
    int TMMCur;
    int heatCur;
    int engHit;
    int FCHit;
    int MPHit;
    int wepHit;
    //status
    boolean sprinted;
    boolean moved;
    boolean inWater;
    boolean inWoods;
    boolean destroyed;
    boolean shutdown;
    boolean hullDown;
    boolean jumped;
    boolean attacked;

    public Unit (){
        this.specialAbilities = new ArrayList<>();
        this.engHit = 0;
        this.FCHit = 0;
        this.MPHit = 0;
        this.wepHit = 0;
        this.sprinted = false;
        this.moved = false;
        this.inWater = false;
        this.inWoods = false;
        this.destroyed = false;
        this.shutdown = false;
        this.hullDown = false;
        this.jumped = false;
        this.attacked = false;
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
