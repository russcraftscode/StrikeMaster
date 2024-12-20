package StrikeMaster;

import java.util.ArrayList;

public class MechData extends UnitData {

    MechData(){
       /*this.variant = "TST-01";
        this.name = "TestMech";
        //this.pv = 42;
        //attributes
        this.armorMax = 10;
        this.structureMax = 10;
        this.moveMax = 5;
        this.size = 4;
        this.overheat = 1;
        //this.tmmMax = 2;
        this.shortDmg = 3;
        this.medDmg = 2;
        this.longDmg = 1;
        this.extDmg = 0;*/
    }

    @Override
    public char getType(){
        return 'm';
    }


    @Override
    public String toString() {
        //String myString = new String();
        String myString = this.name + " " + this.variant + " Role: " + "_____" + " PV: " + String.valueOf(this.PV) + "\n";
        myString = myString + "Type: Mech Size: " + String.valueOf(this.size) + " TMM: " + String.valueOf(this.TMMMax) +
                " Move/Jump: " + String.valueOf(this.moveMax) + "/" + String.valueOf(this.jumpMax) + "\n";
        myString = myString + "Damage Short: " + String.valueOf(this.shortDmg) + " Medium : " + String.valueOf(this.medDmg) +
                " Long : " + String.valueOf(this.longDmg) + " Extreme : " + String.valueOf(this.extDmg) + "\n";
        myString = myString + "Armor: " + String.valueOf(this.armorMax) + " Internal Structure : " + String.valueOf(this.structureMax) +
                " Overheat : " + String.valueOf(this.overheat) + "\n";
        myString = myString + "Special Abilities: ";
        for(String a : this.specialAbilities){
            myString = myString + a + ", ";
        }
        return myString;
    }
}
