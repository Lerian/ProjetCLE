package affichageGraphique;

import javax.swing.JFrame;
import javax.swing.JTextArea;

import armor.Armor;
import armor.Equipement;
import interfaces.IAfficheur;
import interfaces.IPlugin;

public class Afficheur implements IPlugin, IAfficheur {

    @Override
    public void affiche(Armor armure) {
        JFrame frame = new JFrame(armure.getName());
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        JTextArea taHead = new JTextArea();
        JTextArea taRHand = new JTextArea();
        JTextArea taLHand = new JTextArea();
        JTextArea taRArm = new JTextArea();
        JTextArea taLArm = new JTextArea();
        JTextArea taRLeg = new JTextArea();
        JTextArea taLLeg = new JTextArea();
        JTextArea taBody = new JTextArea();
        
        for(Equipement equi : armure.getEquipements()){
            switch (equi.getPos()){
                case HEAD :
                    taHead.append(equi.toString());
                break;
                case RHAND :
                    taRHand.append(equi.toString());
                break;
                case LHAND :
                    taLHand.append(equi.toString());
                break;
                case RARM :
                    taRArm.append(equi.toString());
                break;
                case LARM :
                    taLArm.append(equi.toString());
                break;
                case RLEG :
                    taRLeg.append(equi.toString());
                break;
                case LLEG :
                    taLLeg.append(equi.toString());
                break;
                case BODY :
                    taBody.append(equi.toString());
                break;
            }
        }
        
        //TODO mise en forme des données lors de l'affichage.
        
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    @Override
    public void run() {
        
    }

}
