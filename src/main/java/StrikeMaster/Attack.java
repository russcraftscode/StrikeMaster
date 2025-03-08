package StrikeMaster;

/**
 * This class holds the data for an attack made by one unit on another
 */
public class Attack {
    public int baseDamage; // 0 is minimum damage, AKA '*'
    public int totalDamage;
    public DamageType damageType;
    public boolean thruArmorCrit;
    public boolean rearHit;


   public enum DamageType {REGULAR, HEAT, TANDEM, PHYSICAL, MISSILE, BALLISTIC }

    public Attack (int damage, DamageType damageType, boolean critical, boolean rearHit){
        baseDamage = damage;
        this.damageType = damageType;
        thruArmorCrit = critical;
        this.rearHit = rearHit;
        totalDamage = baseDamage;
        if(rearHit) totalDamage ++;
    }

    public int getTotalDamage(){
       return totalDamage;
    }



}
