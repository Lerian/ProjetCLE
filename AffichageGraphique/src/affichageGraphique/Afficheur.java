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
        
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        Box box1 = new Box(BoxLayout.Y_AXIS);
        Box box2 = new Box(BoxLayout.Y_AXIS);
        Box box3 = new Box(BoxLayout.Y_AXIS);
        Box box4 = new Box(BoxLayout.Y_AXIS);
        Box box5 = new Box(BoxLayout.Y_AXIS);
        
        box1.add(taHead);
        box1.add(new JSeparator());
        box1.add(taRArm);
        box1.add(new JSeparator());
        box1.add(taRHand);
        box1.add(new JSeparator());
        box1.add(taRLeg);
        
        box2.add(new JSeparator());
        JLabel lab;
        
        try {
            lab = new JLabel(new ImageIcon(new File("resources/tech_clone_armure2_02.png").toURI().toURL()));
            lab.setBounds(0, 0, 200, 200);
            box3.add(lab);
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        box4.add(new JSeparator());
        
        box5.add(taBody);
        box5.add(new JSeparator());
        box5.add(taLArm);
        box5.add(new JSeparator());
        box5.add(taLHand);
        box5.add(new JSeparator());
        box5.add(taLLeg);
        
        panel.add(box1);
        panel.add(box2);
        panel.add(box3);
        panel.add(box4);
        panel.add(box5);
        //TODO mise en forme des données lors de l'affichage.
        
        frame.setContentPane(panel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    @Override
    public void run() {
        
    }

}
