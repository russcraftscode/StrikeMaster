package StrikeMaster;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class UnitLibrary {
    // TODO replace UnitDataFactory with a xml or json reader

    private ArrayList<UnitData> library;
    public UnitLibrary(String libraryFileName) throws IOException {
        this.library = new ArrayList<>();
        List<String> lst = Files.readAllLines(Paths.get(libraryFileName));
        // for( String e: lst) System.out.println(e); // DEBUG
        for( String entry : lst ) {
            // System.out.println(entry); // DEBUG
            //disregard the 1st line that does not have a |
            if(entry.contains("|")) library.add(UnitDataFactory.buildUnitData(entry));
        }
    }
    public ArrayList<String> getVariants (){
        ArrayList<String> allVariants = new ArrayList<>();
        for(UnitData entry : this.library){
            allVariants.add(entry.getVariant());
        }
        return allVariants;
    }
    public UnitData getUnitData(String variant){
        for(UnitData entry : this.library){
            // System.out.println(entry.getVariant() + "  " + variant);// DEBUG
            if(entry.getVariant().equals(variant)){
                return entry;
            }
        }
        throw new IllegalArgumentException("Unit " + variant + " not found in library");
    }
}
