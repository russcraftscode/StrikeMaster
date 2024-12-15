package StrikeMaster;



public class Main {
    public static void main(String[] args) {

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