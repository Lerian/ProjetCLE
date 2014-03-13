package affichageGraphique;

import java.awt.Color;
import java.io.File;
import java.net.MalformedURLException;
import java.util.HashMap;

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
import armor.Body;
import armor.Equipement;
import armor.Position;

public class Afficheur implements IPlugin, IAfficheur {

    @Override
    public void affiche(Armor armure) {
        JFrame frame = new JFrame(armure.getName());
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        JLabel lHead = new JLabel("<html><center>Head :</center>",JLabel.CENTER);
        JLabel lRHand = new JLabel("<html><center>Right Hand :</center>",JLabel.CENTER);
        JLabel lLHand = new JLabel("<html><center>Left Hand :</center>",JLabel.CENTER);
        JLabel lRArm = new JLabel("<html><center>Right Arm :</center>",JLabel.CENTER);
        JLabel lLArm = new JLabel("<html><center>Left Arm :</center>",JLabel.CENTER);
        JLabel lRLeg = new JLabel("<html><center>Right Leg :</center>",JLabel.CENTER);
        JLabel lLLeg = new JLabel("<html><center>Left Leg :</center>",JLabel.CENTER);
        JLabel lBody = new JLabel("<html><center>Body :</center>",JLabel.CENTER);
        
        HashMap<Position, JLabel> equiPos = new HashMap<Position,JLabel>();
        equiPos.put(Position.HEAD, lHead);
        equiPos.put(Position.RHAND, lRHand);
        equiPos.put(Position.LHAND, lLHand);
        equiPos.put(Position.RARM, lRArm);
        equiPos.put(Position.LARM, lLArm);
        equiPos.put(Position.RLEG, lRLeg);
        equiPos.put(Position.LLEG, lLLeg);
        equiPos.put(Position.BODY, lBody);
        
        
        for(Equipement equi : armure.getEquipements()){
            JLabel temp = equiPos.get(equi.getPos());
            temp.setText(temp.getText()+"<br>"+equi.toString());
            temp.setOpaque(true);
        }
        
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        Box box1 = new Box(BoxLayout.Y_AXIS);
        Box box2 = new Box(BoxLayout.Y_AXIS);
        Box box3 = new Box(BoxLayout.Y_AXIS);
        
        box1.add(new JSeparator());
        box1.add(lHead);
        box1.add(new JSeparator());
        box1.add(lRArm);
        box1.add(new JSeparator());
        box1.add(lRHand);
        box1.add(new JSeparator());
        box1.add(lRLeg);
        
        
        box2.add(new JLabel("<html><center>"+armure.getName()+"</center></html>", JLabel.CENTER));
        box2.add(new JSeparator());
        JLabel lab;
        try {
            lab = new JLabel(new ImageIcon(new File("resources/tech_clone_armure2_02.png").toURI().toURL()));
            lab.setBounds(0, 0, 200, 200);
            box2.add(lab);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        box2.add(new JSeparator());
        box2.add(new JLabel("<html><center>Energy : type ["+armure.getEnergyAvailable().getName()+"] "+armure.getEnergyAvailable().getValue()+" remaining</center></html>",JLabel.CENTER));
        
        box3.add(new JSeparator());
        box3.add(lBody);
        box3.add(new JSeparator());
        box3.add(lLArm);
        box3.add(new JSeparator());
        box3.add(lLHand);
        box3.add(new JSeparator());
        box3.add(lLLeg);
        
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
