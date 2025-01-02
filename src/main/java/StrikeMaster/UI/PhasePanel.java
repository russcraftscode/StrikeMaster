package StrikeMaster.UI;

import StrikeMaster.MsgManager;
import StrikeMaster.PhaseName;
import StrikeMaster.UnitManager;

import javax.swing.*;
import java.awt.*;

public class PhasePanel extends JPanel {
    private PhaseName phase = PhaseName.MOVE;
    private HeaderPanel headerPanel;
    private AppWindow mainWindow;

    private JButton movePhaseButton = new JButton();
    private JButton combatPhaseButton = new JButton();
    private JButton endRoundButton = new JButton();

    public PhasePanel(AppWindow ancestor) {
        this.mainWindow = ancestor;

        // build and place buttons
        this.setLayout(new GridLayout(3, 1, 2, 2));
        Font phaseButtonFont = new Font("Impact", Font.BOLD, 20);
        movePhaseButton.setText("Move Phase");
        movePhaseButton.setMinimumSize(new Dimension(150, 50));
        movePhaseButton.setFont(phaseButtonFont);
        this.add(movePhaseButton);
        combatPhaseButton.setText("<html><center>Combat<br>Phase</html>");
        combatPhaseButton.setFont(phaseButtonFont);
        this.add(combatPhaseButton);
        combatPhaseButton.setPreferredSize(new Dimension(150, 50));
        endRoundButton.setText("<html><center>Resolve Damage<br>and<br>End Round</center></html>");
        endRoundButton.setPreferredSize(new Dimension(150, 50));
        endRoundButton.setFont(phaseButtonFont);
        this.add(endRoundButton);

        this.updateButtons();

        // add logic to buttons
        movePhaseButton.addActionListener(e -> {
            changePhase(PhaseName.MOVE);
        });
        combatPhaseButton.addActionListener(e -> {
            changePhase(PhaseName.COMBAT);
        });
        endRoundButton.addActionListener(e -> {
            changePhase(PhaseName.END);
        });
    }

    private void changePhase(PhaseName newPhase) {
        // check to make sure in proper phase before switching
        switch (newPhase) {
            case MOVE:
                if (phase != PhaseName.END) {
                    AlertPopup wrongPhase = new AlertPopup("Phase Error",
                            "Must be in end the round before moving back to \"Move Phase\"",
                            AlertPopup.AlertType.ERROR);
                    return;
                }
                break;
            case COMBAT:
                // can only go to combat phase from move phase
                if (phase != PhaseName.MOVE) {
                    AlertPopup wrongPhase = new AlertPopup("Phase Error",
                            "Must be in \"Move Phase\" before moving to "
                                    + "\"Combat Phase\"",
                            AlertPopup.AlertType.ERROR);
                    return;
                }
                break;
            case END:
                if (phase != PhaseName.COMBAT) {
                    AlertPopup wrongPhase = new AlertPopup("Phase Error",
                            "Must be in \"Combat Phase\" before moving to "
                                    + "\"Resolve Damage Phase\"",
                            AlertPopup.AlertType.ERROR);
                    return;
                }
                MsgManager.postMsg("------ Damage Resolution Report -------");
                UnitManager.endRound();
                MsgManager.postMsg("******** End of Round ********\n");
                break;
        }
        // if in right phase then switch to new phase
        phase = newPhase;
        mainWindow.changeMode(newPhase);
        updateButtons();
    }

    private void updateButtons() {
        switch (phase) {
            case MOVE:
                movePhaseButton.setBackground(Color.WHITE);
                combatPhaseButton.setBackground(Color.LIGHT_GRAY);
                endRoundButton.setBackground(Color.LIGHT_GRAY);
                break;
            case COMBAT:
                movePhaseButton.setBackground(Color.LIGHT_GRAY);
                combatPhaseButton.setBackground(Color.WHITE);
                endRoundButton.setBackground(Color.LIGHT_GRAY);
                break;
            case END:
                movePhaseButton.setBackground(Color.LIGHT_GRAY);
                combatPhaseButton.setBackground(Color.LIGHT_GRAY);
                endRoundButton.setBackground(Color.WHITE);
                break;
            default:
                movePhaseButton.setBackground(Color.LIGHT_GRAY);
                combatPhaseButton.setBackground(Color.LIGHT_GRAY);
                endRoundButton.setBackground(Color.LIGHT_GRAY);
                break;
        }
        this.revalidate();
        this.repaint();
    }
}
