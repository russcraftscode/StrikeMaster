package StrikeMaster;

/**
 * This class holds the data for an attack made by one unit on another
 */
public class Attack {
    public int baseDamage; // 0 is minimum damage, AKA '*'
    public DamageType damageType;
    public boolean thruArmorCrit;

   public enum DamageType {REGULAR, HEAT, TANDEM, PHYSICAL, MISSILE, BALLISTIC }

    public Attack (int damage, DamageType damageType, boolean critical){
        baseDamage = damage;
        this.damageType = damageType;
        thruArmorCrit = critical;
    }


}
