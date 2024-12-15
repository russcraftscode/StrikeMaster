package StrikeMaster;



public class Main {
    public static void main(String[] args) {

        boolean hiRez = true;
        // TODO make this an option for the user
        if(hiRez){
            System.setProperty("sun.java2d.uiScale", "2.0");
        }else {
            System.setProperty("sun.java2d.uiScale", "1.0");
        }
        AppWindow window = new AppWindow(hiRez);

        window.changeMode("Combat Phase prototype");

    }
}