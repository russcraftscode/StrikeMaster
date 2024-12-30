package StrikeMaster;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Observable;

/**
 * MsgManager class handles all units in a game. Employs a singleton pattern to ensure there is
 * only one copy of this data throughout entire program. Additionally, UserManger employs an
 * Observer pattern to inform other classes when there is a change to a unit.
 */
public class MsgManager extends Observable {

    private static MsgManager instance;
    private static int indexCursor = 0;

    private static final ArrayList<String> messages = new ArrayList<>();

    /**
     * Instantiate singleton instance of MsgManager
     * @return the only instance of UnitManger
     */
    public static MsgManager getInstance() {
        if (MsgManager.instance == null) {
            MsgManager.instance = new MsgManager();
        }
        return MsgManager.instance;
    }

    public static String getLastMsg() {
        return messages.get(messages.size() - 1);
    }

    public static void postMsg(String msg) {
        messages.add(msg);
        MsgManager.getInstance().setChanged();
        MsgManager.getInstance().notifyObservers();
    }

    public static void resetIndex() {
        indexCursor = 0;
    }

    public static boolean hasNext() {
        return indexCursor < messages.size();
    }

    public static String next() {
        if (indexCursor >= messages.size()) throw new NoSuchElementException(
                "Error: There is no msg of index" + indexCursor);
        return messages.get(indexCursor++);
    }

}

