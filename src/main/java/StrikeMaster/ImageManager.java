package StrikeMaster;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;

import java.io.IOException;

/**
 * UnitManager class handles all units in a game.
 * Employs a singleton pattern to ensure there is
 * only one copy of this data throughout entire program.
 * Additionally, UserManger employs an Observer pattern to inform
 * other classes when there is a change to a unit.
 */
public class ImageManager {

    private static ImageManager instance;

    private static BufferedImage mechIcon;
    private static BufferedImage armorIcon;
    private static BufferedImage infIcon;
    private static BufferedImage artyIcon;
    private static BufferedImage vtolIcon;

    // counter circle icons
    private static BufferedImage one0Icon;
    private static BufferedImage one1Icon;
    private static BufferedImage two0Icon;
    private static BufferedImage two1Icon;
    private static BufferedImage two2Icon;
    private static BufferedImage three0Icon;
    private static BufferedImage three1Icon;
    private static BufferedImage three2Icon;
    private static BufferedImage three3Icon;
    private static BufferedImage four0Icon;
    private static BufferedImage four1Icon;
    private static BufferedImage four2Icon;
    private static BufferedImage four3Icon;
    private static BufferedImage four4Icon;
    private static BufferedImage five0Icon;
    private static BufferedImage five1Icon;
    private static BufferedImage five2Icon;
    private static BufferedImage five3Icon;
    private static BufferedImage five4Icon;
    private static BufferedImage five5Icon;
    private static BufferedImage six0Icon;
    private static BufferedImage six1Icon;
    private static BufferedImage six2Icon;
    private static BufferedImage six3Icon;
    private static BufferedImage six4Icon;
    private static BufferedImage six5Icon;
    private static BufferedImage six6Icon;
    private static BufferedImage seven0Icon;
    private static BufferedImage seven1Icon;
    private static BufferedImage seven2Icon;
    private static BufferedImage seven3Icon;
    private static BufferedImage seven4Icon;
    private static BufferedImage seven5Icon;
    private static BufferedImage seven6Icon;
    private static BufferedImage seven7Icon;
    private static BufferedImage eight0Icon;
    private static BufferedImage eight1Icon;
    private static BufferedImage eight2Icon;
    private static BufferedImage eight3Icon;
    private static BufferedImage eight4Icon;
    private static BufferedImage eight5Icon;
    private static BufferedImage eight6Icon;
    private static BufferedImage eight7Icon;
    private static BufferedImage eight8Icon;
    private static BufferedImage nine0Icon;
    private static BufferedImage nine1Icon;
    private static BufferedImage nine2Icon;
    private static BufferedImage nine3Icon;
    private static BufferedImage nine4Icon;
    private static BufferedImage nine5Icon;
    private static BufferedImage nine6Icon;
    private static BufferedImage nine7Icon;
    private static BufferedImage nine8Icon;
    private static BufferedImage nine9Icon;
    private static BufferedImage ten0Icon;
    private static BufferedImage ten1Icon;
    private static BufferedImage ten2Icon;
    private static BufferedImage ten3Icon;
    private static BufferedImage ten4Icon;
    private static BufferedImage ten5Icon;
    private static BufferedImage ten6Icon;
    private static BufferedImage ten7Icon;
    private static BufferedImage ten8Icon;
    private static BufferedImage ten9Icon;
    private static BufferedImage ten10Icon;
    /**
     * Instantiate singleton instance of ImageLoader
     * @return the only instance of ImageLoader
     */
    public static ImageManager getInstance() {
        if (ImageManager.instance == null) {
            ImageManager.instance = new ImageManager();
        }
        return ImageManager.instance;
    }


    public ImageManager(){

        try {
            ImageManager.loadImages();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

public static ImageIcon getMechIcon(){
        return new ImageIcon(mechIcon);
}

    public static ImageIcon getInfIcon(){
        return new ImageIcon(infIcon);
    }


    /**
     * Gets an image of circle counters that represent an x out of y situation.
     * @param total the total number of circles
     * @param current the number of circles not filled in
     * @return the requested image of counters
     */
    public static BufferedImage getCounterImage(int total, int current) {
        // TODO consider moving this to it's own class. Maybe a static class.
        switch (total) {
            case 1:
                switch (current) {
                    case 1:
                        return one1Icon;
                    default:
                        return one0Icon;
                }
            case 2:
                switch (current) {
                    case 1:
                        return two1Icon;
                    case 2:
                        return two2Icon;
                    default:
                        return two0Icon;
                }
            case 3:
                switch (current) {
                    case 1:
                        return three1Icon;
                    case 2:
                        return three2Icon;
                    case 3:
                        return three3Icon;
                    default:
                        return three0Icon;
                }
            case 4:
                switch (current) {
                    case 1:
                        return four1Icon;
                    case 2:
                        return four2Icon;
                    case 3:
                        return four3Icon;
                    case 4:
                        return four4Icon;
                    default:
                        return four0Icon;
                }
            case 5:
                switch (current) {
                    case 1:
                        return five1Icon;
                    case 2:
                        return five2Icon;
                    case 3:
                        return five3Icon;
                    case 4:
                        return five4Icon;
                    case 5:
                        return five5Icon;
                    default:
                        return five0Icon;
                }
            case 6:
                switch (current) {
                    case 1:
                        return six1Icon;
                    case 2:
                        return six2Icon;
                    case 3:
                        return six3Icon;
                    case 4:
                        return six4Icon;
                    case 5:
                        return six5Icon;
                    case 6:
                        return six6Icon;
                    default:
                        return six0Icon;
                }
            case 7:
                switch (current) {
                    case 1:
                        return seven1Icon;
                    case 2:
                        return seven2Icon;
                    case 3:
                        return seven3Icon;
                    case 4:
                        return seven4Icon;
                    case 5:
                        return seven5Icon;
                    case 6:
                        return seven6Icon;
                    case 7:
                        return seven7Icon;
                    default:
                        return seven0Icon;
                }
            case 8:
                switch (current) {
                    case 1:
                        return eight1Icon;
                    case 2:
                        return eight2Icon;
                    case 3:
                        return eight3Icon;
                    case 4:
                        return eight4Icon;
                    case 5:
                        return eight5Icon;
                    case 6:
                        return eight6Icon;
                    case 7:
                        return eight7Icon;
                    case 8:
                        return eight8Icon;
                    default:
                        return eight0Icon;
                }
            case 9:
                switch (current) {
                    case 1:
                        return nine1Icon;
                    case 2:
                        return nine2Icon;
                    case 3:
                        return nine3Icon;
                    case 4:
                        return nine4Icon;
                    case 5:
                        return nine5Icon;
                    case 6:
                        return nine6Icon;
                    case 7:
                        return nine7Icon;
                    case 8:
                        return nine8Icon;
                    case 9:
                        return nine9Icon;
                    default:
                        return nine0Icon;
                }
            case 10:
                switch (current) {
                    case 1:
                        return ten1Icon;
                    case 2:
                        return ten2Icon;
                    case 3:
                        return ten3Icon;
                    case 4:
                        return ten4Icon;
                    case 5:
                        return ten5Icon;
                    case 6:
                        return ten6Icon;
                    case 7:
                        return ten7Icon;
                    case 8:
                        return ten8Icon;
                    case 9:
                        return ten9Icon;
                    case 10:
                        return ten10Icon;
                    default:
                        return ten0Icon;
                }
            default:
                return one1Icon;
        }
    }

    private static void loadImages() throws IOException {
        ClassLoader classLoader = ImageManager.class.getClassLoader();

        mechIcon = ImageIO.read(classLoader.getResourceAsStream("simpleMechV3.png"));
        infIcon = ImageIO.read(classLoader.getResourceAsStream("simpleInf.png"));
        armorIcon = ImageIO.read(classLoader.getResourceAsStream("simpleArmorInf.png"));
        artyIcon = ImageIO.read(classLoader.getResourceAsStream("simpleArty.png"));
        vtolIcon = ImageIO.read(classLoader.getResourceAsStream("simpleVtol.png"));

        one0Icon = ImageIO.read(classLoader.getResourceAsStream("0of1.png"));
        one1Icon = ImageIO.read(classLoader.getResourceAsStream("1of1.png"));
        two0Icon = ImageIO.read(classLoader.getResourceAsStream("0of2.png"));
        two1Icon = ImageIO.read(classLoader.getResourceAsStream("1of2.png"));
        two2Icon = ImageIO.read(classLoader.getResourceAsStream("2of2.png"));
        three0Icon = ImageIO.read(classLoader.getResourceAsStream("0of3.png"));
        three1Icon = ImageIO.read(classLoader.getResourceAsStream("1of3.png"));
        three2Icon = ImageIO.read(classLoader.getResourceAsStream("2of3.png"));
        three3Icon = ImageIO.read(classLoader.getResourceAsStream("3of3.png"));
        four0Icon = ImageIO.read(classLoader.getResourceAsStream("0of4.png"));
        four1Icon = ImageIO.read(classLoader.getResourceAsStream("1of4.png"));
        four2Icon = ImageIO.read(classLoader.getResourceAsStream("2of4.png"));
        four3Icon = ImageIO.read(classLoader.getResourceAsStream("3of4.png"));
        four4Icon = ImageIO.read(classLoader.getResourceAsStream("4of4.png"));
        five0Icon = ImageIO.read(classLoader.getResourceAsStream("0of5.png"));
        five1Icon = ImageIO.read(classLoader.getResourceAsStream("1of5.png"));
        five2Icon = ImageIO.read(classLoader.getResourceAsStream("2of5.png"));
        five3Icon = ImageIO.read(classLoader.getResourceAsStream("3of5.png"));
        five4Icon = ImageIO.read(classLoader.getResourceAsStream("4of5.png"));
        five5Icon = ImageIO.read(classLoader.getResourceAsStream("5of5.png"));
        six0Icon = ImageIO.read(classLoader.getResourceAsStream("0of6.png"));
        six1Icon = ImageIO.read(classLoader.getResourceAsStream("1of6.png"));
        six2Icon = ImageIO.read(classLoader.getResourceAsStream("2of6.png"));
        six3Icon = ImageIO.read(classLoader.getResourceAsStream("3of6.png"));
        six4Icon = ImageIO.read(classLoader.getResourceAsStream("4of6.png"));
        six5Icon = ImageIO.read(classLoader.getResourceAsStream("5of6.png"));
        six6Icon = ImageIO.read(classLoader.getResourceAsStream("6of6.png"));
        seven0Icon = ImageIO.read(classLoader.getResourceAsStream("0of7.png"));
        seven1Icon = ImageIO.read(classLoader.getResourceAsStream("1of7.png"));
        seven2Icon = ImageIO.read(classLoader.getResourceAsStream("2of7.png"));
        seven3Icon = ImageIO.read(classLoader.getResourceAsStream("3of7.png"));
        seven4Icon = ImageIO.read(classLoader.getResourceAsStream("4of7.png"));
        seven5Icon = ImageIO.read(classLoader.getResourceAsStream("5of7.png"));
        seven6Icon = ImageIO.read(classLoader.getResourceAsStream("6of7.png"));
        seven7Icon = ImageIO.read(classLoader.getResourceAsStream("7of7.png"));
        eight0Icon = ImageIO.read(classLoader.getResourceAsStream("0of8.png"));
        eight1Icon = ImageIO.read(classLoader.getResourceAsStream("1of8.png"));
        eight2Icon = ImageIO.read(classLoader.getResourceAsStream("2of8.png"));
        eight3Icon = ImageIO.read(classLoader.getResourceAsStream("3of8.png"));
        eight4Icon = ImageIO.read(classLoader.getResourceAsStream("4of8.png"));
        eight5Icon = ImageIO.read(classLoader.getResourceAsStream("5of8.png"));
        eight6Icon = ImageIO.read(classLoader.getResourceAsStream("6of8.png"));
        eight7Icon = ImageIO.read(classLoader.getResourceAsStream("7of8.png"));
        eight8Icon = ImageIO.read(classLoader.getResourceAsStream("8of8.png"));
        nine0Icon = ImageIO.read(classLoader.getResourceAsStream("0of9.png"));
        nine1Icon = ImageIO.read(classLoader.getResourceAsStream("1of9.png"));
        nine2Icon = ImageIO.read(classLoader.getResourceAsStream("2of9.png"));
        nine3Icon = ImageIO.read(classLoader.getResourceAsStream("3of9.png"));
        nine4Icon = ImageIO.read(classLoader.getResourceAsStream("4of9.png"));
        nine5Icon = ImageIO.read(classLoader.getResourceAsStream("5of9.png"));
        nine6Icon = ImageIO.read(classLoader.getResourceAsStream("6of9.png"));
        nine7Icon = ImageIO.read(classLoader.getResourceAsStream("7of9.png"));
        nine8Icon = ImageIO.read(classLoader.getResourceAsStream("8of9.png"));
        nine9Icon = ImageIO.read(classLoader.getResourceAsStream("9of9.png"));
        ten0Icon = ImageIO.read(classLoader.getResourceAsStream("0of10.png"));
        ten1Icon = ImageIO.read(classLoader.getResourceAsStream("1of10.png"));
        ten2Icon = ImageIO.read(classLoader.getResourceAsStream("2of10.png"));
        ten3Icon = ImageIO.read(classLoader.getResourceAsStream("3of10.png"));
        ten4Icon = ImageIO.read(classLoader.getResourceAsStream("4of10.png"));
        ten5Icon = ImageIO.read(classLoader.getResourceAsStream("5of10.png"));
        ten6Icon = ImageIO.read(classLoader.getResourceAsStream("6of10.png"));
        ten7Icon = ImageIO.read(classLoader.getResourceAsStream("7of10.png"));
        ten8Icon = ImageIO.read(classLoader.getResourceAsStream("8of10.png"));
        ten9Icon = ImageIO.read(classLoader.getResourceAsStream("9of10.png"));
        ten10Icon = ImageIO.read(classLoader.getResourceAsStream("10of10.png"));
    }

}
