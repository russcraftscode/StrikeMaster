package StrikeMaster.UI;

import StrikeMaster.UnitManager;
import StrikeMaster.Units.Unit;
import StrikeMaster.ImageManager;

import javax.swing.*;
import java.awt.*;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;


/**
 * This panel displays contextually relevant information about all units and allows
 * the user to select a unit. UnitManger tracks which unit is selected.
 */
public abstract class UnitSelectPanel extends JPanel implements Observer {



    protected final JPanel unitDataPanel = new JPanel();
    protected final JButton editUnitButton = new JButton("Edit Unit");
    protected final JLabel panelLabel = new JLabel();

    // create a button group for all unit select radio buttons
    protected final ButtonGroup unitSelectGroup = new ButtonGroup();


    /**
     * Constructs either an attacker-unit select panel, a target-unit select panel,
     * or a move-unit select panel.
     */
   public UnitSelectPanel() {
        // make this panel an observer of UnitManger
        UnitManager.getInstance().addObserver(this);

        this.setLayout(new GridBagLayout());
        this.buildUnitPanel();

        // create the scroll pane that will hold the unit data panel
        JScrollPane unitScroll = new JScrollPane();
        unitScroll.getVerticalScrollBar().setUnitIncrement(5); // speed up the scrolling
        // anchor the unitDataPanel to the top of the scroll pane if it isn't big enough to scroll
        unitScroll.getViewport().setLayout(new FlowLayout(FlowLayout.LEADING));
        // preferred height of 0 will let the AttackOptions panel set vertical size of this window
        unitScroll.setPreferredSize(new Dimension(unitDataPanel.getPreferredSize().width+30, 0));
        // Add unit data panel to scroll pane and add that to AttackPanel
        unitScroll.getViewport().add(unitDataPanel);

        editUnitButton.setPreferredSize(new Dimension(editUnitButton.getPreferredSize().width, 20));

        // add components to unit select panel
        GridBagConstraints selectLoc = new GridBagConstraints();
        selectLoc.gridy=0;
        this.add(panelLabel, selectLoc);

        selectLoc.gridy++;
        selectLoc.weighty = 1;
        selectLoc.fill = GridBagConstraints.VERTICAL;
        this.add(unitScroll, selectLoc);

        selectLoc.anchor = GridBagConstraints.SOUTH;
        selectLoc.fill = GridBagConstraints.NONE;
        selectLoc.gridy++;
        selectLoc.weighty = 0;
        this.add(editUnitButton, selectLoc);
    }

    protected abstract void buildUnitPanel();

    /**
     * Updates all unit entries to reflect any changes to unit objects.
     */
    public abstract void updateUnits();

    /**
     * Whenever a unit is changed the panel will update its graphics.
     * @param o   the observable object.
     * @param arg an argument passed to the {@code notifyObservers} method.
     */
    @Override
    public void update(Observable o, Object arg) {
        // TODO determine if updating only a single unit will be needed to maintain performance
        if (o instanceof UnitManager) {
            updateUnits();
        }
    }
}
