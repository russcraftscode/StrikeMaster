package StrikeMaster;


import StrikeMaster.UI.AppWindow;

public class Main {
    public static void main(String[] args) {

        // Initialize singletons
        UnitManager unitManager = UnitManager.getInstance();
        ImageManager imageLoader = ImageManager.getInstance();
        MsgManager msgManager = MsgManager.getInstance();

        // TODO make this an option for the user
        //boolean hiRez = false;
        boolean hiRez = true;
        if(hiRez){
            System.setProperty("sun.java2d.uiScale", "2.0");
        }else {
            System.setProperty("sun.java2d.uiScale", "1.0");
        }
        AppWindow window = new AppWindow(hiRez);

        window.changeMode("Combat Phase prototype");
    }
}