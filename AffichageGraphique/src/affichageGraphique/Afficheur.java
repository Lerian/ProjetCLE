package affichageGraphique;

import java.io.File;
import java.net.MalformedURLException;

import interfaces.IAfficheur;
import interfaces.IPlugin;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextArea;

import armor.Armor;
import armor.Equipement;

public class Afficheur implements IPlugin, IAfficheur {

    @Override
    public void affiche(Armor armure) {
        JFrame frame = new JFrame(armure.getName());
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        JTextArea taHead = new JTextArea();
        taHead.setEditable(false);
        JTextArea taRHand = new JTextArea();
        taRHand.setEditable(false);
        JTextArea taLHand = new JTextArea();
        taLHand.setEditable(false);
        JTextArea taRArm = new JTextArea();
        taRArm.setEditable(false);
        JTextArea taLArm = new JTextArea();
        taLArm.setEditable(false);
        JTextArea taRLeg = new JTextArea();
        taRLeg.setEditable(false);
        JTextArea taLLeg = new JTextArea();
        taLLeg.setEditable(false);
        JTextArea taBody = new JTextArea();
        taBody.setEditable(false);
        
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
        
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        Box box1 = new Box(BoxLayout.Y_AXIS);
        Box box2 = new Box(BoxLayout.Y_AXIS);
        Box box3 = new Box(BoxLayout.Y_AXIS);
        
        box1.add(new JLabel("Head :"));
        box1.add(taHead);
        box1.add(new JSeparator());
        box1.add(new JLabel("Right Arm :"));
        box1.add(taRArm);
        box1.add(new JSeparator());
        box1.add(new JLabel("Right Hand :"));
        box1.add(taRHand);
        box1.add(new JSeparator());
        box1.add(new JLabel("Right Leg :"));
        box1.add(taRLeg);
        
        JLabel lab;
        try {
            lab = new JLabel(new ImageIcon(new File("resources/tech_clone_armure2_02.png").toURI().toURL()));
            lab.setBounds(0, 0, 200, 200);
            box2.add(lab);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        box2.add(new JLabel("Energy : type ["+armure.getEnergyAvailable().getName()+"] "+armure.getEnergyAvailable().getValue()+" remaining"));
        
        box3.add(new JLabel("Body :"));
        box3.add(taBody);
        box3.add(new JSeparator());
        box3.add(new JLabel("Left Arm :"));
        box3.add(taLArm);
        box3.add(new JSeparator());
        box3.add(new JLabel("Left Hand :"));
        box3.add(taLHand);
        box3.add(new JSeparator());
        box3.add(new JLabel("Left Leg :"));
        box3.add(taLLeg);
        
        panel.add(box1);
        panel.add(box2);
        panel.add(box3);
        
        frame.setContentPane(panel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    @Override
    public void run() {
        
    }

}
