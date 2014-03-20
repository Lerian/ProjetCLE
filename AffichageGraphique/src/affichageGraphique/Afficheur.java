package affichageGraphique;

import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Scanner;

import interfaces.IAfficheur;
import interfaces.IPlugin;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;

import armor.Armor;
import armor.Body;
import armor.Equipement;

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
                    lHead.setText(lHead.getText()+"<br>"+equi.toString());
                    lHead.setOpaque(true);
                    lHead.setBackground(colorMatch.get(((Body)equi).getColor()));
                break;
                case RHAND:
                    lRHand.setText(lRHand.getText()+"<br>"+equi.toString());
                break;
                case LHAND:
                    lLHand.setText(lLHand.getText()+"<br>"+equi.toString());
                break;
                case RARM:
                    lRArm.setText(lRArm.getText()+"<br>"+equi.toString());
                    lRArm.setOpaque(true);
                    lRArm.setBackground(colorMatch.get(((Body)equi).getColor()));
                break;
                case LARM:
                    lLArm.setText(lLArm.getText()+"<br>"+equi.toString());
                    lLArm.setOpaque(true);
                    lLArm.setBackground(colorMatch.get(((Body)equi).getColor()));
                break;
                case RLEG:
                    lRLeg.setText(lRLeg.getText()+"<br>"+equi.toString());
                    lRLeg.setOpaque(true);
                    lRLeg.setBackground(colorMatch.get(((Body)equi).getColor()));
                break;
                case LLEG:
                    lLLeg.setText(lLLeg.getText()+"<br>"+equi.toString());
                    lLLeg.setOpaque(true);
                    lLLeg.setBackground(colorMatch.get(((Body)equi).getColor()));
                break;
                case BODY:
                    lBody.setText(lBody.getText()+"<br>"+equi.toString());
                    lBody.setOpaque(true);
                    lBody.setBackground(colorMatch.get(((Body)equi).getColor()));
                break;
            }
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
            lab = new JLabel(new ImageIcon(new File("resources/"+armure.getImage()).toURI().toURL()));
            System.out.println(new File("resources/"+armure.getImage()).toURI().toURL());
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
