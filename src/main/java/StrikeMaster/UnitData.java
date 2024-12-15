package StrikeMaster;

import java.util.ArrayList;

public class UnitData {
    //identity
     String variant;
     String name;
     String role;
    public int PV;
    //attributes
    int armorMax;
     int structureMax;
     int moveMax;
     int jumpMax;
     ArrayList<String> specialAbilities = new ArrayList<>();
     int size;
     int overheat;
     int TMMMax;
     char shortDmg;
     char medDmg;
     char longDmg;
     char extDmg;

     public UnitData(){

     }

    /*
    //identity
    private String variant;
    private String name;
    private int pv;
    //attributes
    private int armorMax;
    private int structureMax;
    private int maxMove;
    private ArrayList<String> specialAbilities;
    private int size;
    private int overheat;
    private int tmmMax;
    private int shortDmg;
    private int medDmg;
    private int longDmg;
    private int extDmg;*/

    public String getVariant() {
        return variant;
    }

    public String getName() {
        return name;
    }

    public String getRole() {
        return role;
    }

    public int getPV() {
        return PV;
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

    public int getTmmMax() {
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

    public void setVariant(String variant) {
        this.variant = variant;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setPV(int PV) {
        this.PV = PV;
    }

    public void setArmorMax(int armorMax) {
        this.armorMax = armorMax;
    }

    public void setStructureMax(int structureMax) {
        this.structureMax = structureMax;
    }

    public void setMoveMax(int moveMax) {
        this.moveMax = moveMax;
    }

    public void setJumpMax(int j) {
        this.jumpMax = j;
    }

    public void setSpecialAbilities(ArrayList<String> specialAbilities) {
        this.specialAbilities = specialAbilities;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setOverheat(int overheat) {
        this.overheat = overheat;
    }

    public void setTMMMax(int tmmMax) {
        this.TMMMax = tmmMax;
    }

    public void setShortDmg(char shortDmg) {
        this.shortDmg = shortDmg;
    }

    public void setMedDmg(char medDmg) {
        this.medDmg = medDmg;
    }

    public void setLongDmg(char longDmg) {
        this.longDmg = longDmg;
    }

    public void setExtDmg(char extDmg) {
        this.extDmg = extDmg;
    }

    public void addSpecialAbility(String ability){
        if(!this.specialAbilities.contains(ability)) this.specialAbilities.add(ability);
    }


    //Name,Variant,Role,Tech Base,Rules,Year,Type,Armor,Internal,TMM,Move,Jump,S,M,L,E,OV,Tons,Size,Skill,PV,Special Abilities
   // public UnitData(String name, String variant, String role, String ){

}
