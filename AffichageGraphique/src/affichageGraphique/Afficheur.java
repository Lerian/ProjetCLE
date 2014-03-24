package affichageGraphique;

import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Scanner;

import interfaces.IAfficheur;
import interfaces.IPlugin;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.border.AbstractBorder;
import javax.swing.border.BevelBorder;

import armor.Armor;
import armor.Body;
import armor.Equipement;
import armor.Weapon;

public class Afficheur implements IPlugin, IAfficheur {

    @Override
    public void affiche(Armor armure) {
        JFrame frame = new JFrame(armure.getName());
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        JLabel lHead = new JLabel("<html>",JLabel.CENTER);
        JLabel lRHand = new JLabel("<html>",JLabel.CENTER);
        JLabel lLHand = new JLabel("<html>",JLabel.CENTER);
        JLabel lRArm = new JLabel("<html>",JLabel.CENTER);
        JLabel lLArm = new JLabel("<html>",JLabel.CENTER);
        JLabel lRLeg = new JLabel("<html>",JLabel.CENTER);
        JLabel lLLeg = new JLabel("<html>",JLabel.CENTER);
        JLabel lBody = new JLabel("<html>",JLabel.CENTER);
        
        JLabel lName = new JLabel("<html><center><h2><b><i>"+armure.getName()+"</i></b></h2></center></html>", JLabel.CENTER);
        JLabel lEnergy = new JLabel("<html><center>Type : "+armure.getEnergyAvailable().getName()+" - Available : "+armure.getEnergyAvailable().getValue()+"</center></html>",JLabel.CENTER);
        JLabel lImage = null;
        
        HashMap<String, Color> colorMatch = new HashMap<String,Color>();
        
        Scanner scanner=null;
        String path = "resources/colorMatch";
        try {
            scanner = new Scanner(new File(path));
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }
        while (scanner.hasNextLine()){
            String line = scanner.nextLine();
            String[] match = line.split("=");
            String[] rgb = match[1].split(",");
            colorMatch.put(match[0].trim(), new Color(Integer.parseInt(rgb[0].trim()),Integer.parseInt(rgb[1].trim()),Integer.parseInt(rgb[2].trim())));
        }
        scanner.close();
        
        for(Equipement equi : armure.getEquipements()){
            switch (equi.getPos())
            {
                case HEAD:
                    lHead.setText(lHead.getText()+"<br>Name : "+equi.getName()+"<br>Protection : "+((Body)equi).getProtection());
                    lHead.setBorder(BorderFactory.createTitledBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED,colorMatch.get(((Body)equi).getColor()),Color.BLACK), "Head"));
                break;
                case RHAND:
                    lRHand.setText(lRHand.getText()+"<br>Name : "+equi.getName()+"<br>Power : "+((Weapon)equi).getDamage());
                    lRHand.setBorder(BorderFactory.createTitledBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED,Color.BLACK,Color.BLACK), "Right Hand"));
                break;
                case LHAND:
                    lLHand.setText(lLHand.getText()+"<br>Name : "+equi.getName()+"<br>Power : "+((Weapon)equi).getDamage());
                    lLHand.setBorder(BorderFactory.createTitledBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED,Color.BLACK,Color.BLACK), "Left Hand"));
                break;
                case RARM:
                    lRArm.setText(lRArm.getText()+"<br>Name : "+equi.getName()+"<br>Protection : "+((Body)equi).getProtection());
                    lRArm.setBorder(BorderFactory.createTitledBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED,colorMatch.get(((Body)equi).getColor()),Color.BLACK), "Right Arm"));
                break;
                case LARM:
                    lLArm.setText(lLArm.getText()+"<br>Name : "+equi.getName()+"<br>Protection : "+((Body)equi).getProtection());
                    lLArm.setBorder(BorderFactory.createTitledBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED,colorMatch.get(((Body)equi).getColor()),Color.BLACK), "Left Arm"));
                break;
                case RLEG:
                    lRLeg.setText(lRLeg.getText()+"<br>Name : "+equi.getName()+"<br>Protection : "+((Body)equi).getProtection());
                    lRLeg.setBorder(BorderFactory.createTitledBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED,colorMatch.get(((Body)equi).getColor()),Color.BLACK), "Right Leg"));
                break;
                case LLEG:
                    lLLeg.setText(lLLeg.getText()+"<br>Name : "+equi.getName()+"<br>Protection : "+((Body)equi).getProtection());
                    lLLeg.setBorder(BorderFactory.createTitledBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED,colorMatch.get(((Body)equi).getColor()),Color.BLACK), "Left Leg"));
                break;
                case BODY:
                    lBody.setText(lBody.getText()+"<br>Name : "+equi.getName()+"<br>Protection : "+((Body)equi).getProtection());
                    lBody.setBorder(BorderFactory.createTitledBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED,colorMatch.get(((Body)equi).getColor()),Color.BLACK), "Body"));
                break;
            }
        }
        
        lName.setBorder(BorderFactory.createTitledBorder("Name"));
        try {
            lImage = new JLabel(new ImageIcon(new File("resources/"+armure.getImage()).toURI().toURL()));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        lEnergy.setBorder(BorderFactory.createTitledBorder("Energy"));
        
        
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
        box1.add(new JSeparator());
        
        box2.add(lName);
        box2.add(new JSeparator());
        box2.add(lImage);
        box2.add(new JSeparator());
        box2.add(lEnergy);
        
        box3.add(new JSeparator());
        box3.add(lBody);
        box3.add(new JSeparator());
        box3.add(lLArm);
        box3.add(new JSeparator());
        box3.add(lLHand);
        box3.add(new JSeparator());
        box3.add(lLLeg);
        box3.add(new JSeparator());
        
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
