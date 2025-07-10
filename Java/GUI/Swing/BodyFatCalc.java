/**
 * @author Joe Baskin
 * @version 1.0
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class BodyFatCalcGUI extends JFrame {
    String gender;
    JComboBox<String> cb;
    JTextField tfMaleWeight, tfMaleWaist;
    JTextField tfFemaleWeight, tfFemaleWaist, tfWrist, tfHips, tfForeArm;
    double weight, waist, wrist, hips, forearm;
    double factor1, factor2, factor3, factor4, factor5;
    double leanBodyMass, bodyFatWeight, bodyFatPercent;
    JLabel lblMaleBodyFatPct, lblFemaleBodyFatPct;

    public BodyFatCalcGUI() {
        JPanel cardPanel = new JPanel();
        cardPanel.setLayout(new CardLayout());
        cardPanel.setPreferredSize(new Dimension(700, 600));

        JPanel blankPanel = new JPanel();

        JLabel lblMaleWeight = new JLabel("Please eenter your weight (in pounds) [e.g., 201.4]: ", JLabel.LEFT);
        tfMaleWeight = new JTextField(8);
        JLabel lblMaleWaist = new JLabel("Please enter your waist measurement at the naval (in inches) [e.g., 37.6]: ", JLabel.LEFT);
        tfMaleWaist = new JTextField(8);
        JLabel lblEmpty = new JLabel("                   ");
        lblMaleBodyFatPct = new JLabel("\nBody Fat: ");

        JPanel malePanel = new JPanel();
        malePanel.setLayout(new BoxLayout(malePanel, BoxLayout.Y_AXIS));
        malePanel.add(lblMaleWeight);
        malePanel.add(tfMaleWeight);
        malePanel.add(lblMaleWaist);
        malePanel.add(tfMaleWaist);
        malePanel.add(lblEmpty);
        malePanel.add(lblMaleBodyFatPct);

        JLabel lblFemaleWeight = new JLabel("Please eenter your weight (in pounds) [e.g., 201.4]: ", JLabel.LEFT);
        tfFemaleWeight = new JTextField(8);
        JLabel lblFemaleWaist = new JLabel("Please enter your waist measurement at the naval (in inches) [e.g., 37.6]: ");
        tfFemaleWaist = new JTextField(8);
        JLabel lblWrist = new JLabel("Please enter your wrist measurement (in inches) [e.g., 8.6]: ");
        JLabel lblHips = new JLabel("Please enter your hip measurement (in inches) [e.g., 34.6]: ");
        JLabel lblForeArm = new JLabel("Please enter your forearm measurement (in inches) [e.g., 9.3]:");
        tfWrist = new JTextField(8);
        tfHips = new JTextField(8);
        tfForeArm = new JTextField(8);
        JLabel lblEmpty2 = new JLabel("");
        lblFemaleBodyFatPct = new JLabel("Body Fat: ");

        JPanel femalePanel = new JPanel();
        femalePanel.setLayout(new BoxLayout(femalePanel, BoxLayout.Y_AXIS));
        femalePanel.add(lblFemaleWeight); femalePanel.add(tfFemaleWeight);
        femalePanel.add(lblFemaleWaist); femalePanel.add(tfFemaleWaist);
        femalePanel.add(lblWrist); femalePanel.add(tfWrist);
        femalePanel.add(lblHips); femalePanel.add(tfHips);
        femalePanel.add(lblForeArm); femalePanel.add(tfForeArm);
        femalePanel.add(lblEmpty2); femalePanel.add(lblFemaleBodyFatPct);

        cardPanel.add(blankPanel, "");
        cardPanel.add(malePanel, "Male");
        cardPanel.add(femalePanel, "Female");

        JPanel comboBoxPanel = new JPanel();
        JLabel lblGender = new JLabel("Select Gender: ", JLabel.LEFT);
        String comboBoxItems[] = { "", "Male","Female" };
        JComboBox<String> cb = new JComboBox<>(comboBoxItems);
        cb.setEditable(false);
        cb.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent evt) {
                CardLayout cl = (CardLayout)(cardPanel.getLayout());
                cl.show(cardPanel, (String)evt.getItem());
                gender = (String)cb.getSelectedItem();
            }
        });

        comboBoxPanel.add(lblGender);
        comboBoxPanel.add(cb);

        JPanel btnPanel = new JPanel();
        JButton btnCalc = new JButton("Calculate");
        JButton btnClear = new JButton("Clear");
        JButton btnExit = new JButton("Exit");

        btnPanel.add(btnCalc);
        btnPanel.add(btnClear);
        btnPanel.add(btnExit);

        btnExit.addActionListener(new ButtonExitL());
        btnCalc.addActionListener(new ButtonCalcL());
        btnClear.addActionListener(new ButtonClearL());

        add(comboBoxPanel, BorderLayout.PAGE_START);
        add(cardPanel, BorderLayout.CENTER);
        add(btnPanel, BorderLayout.SOUTH);
    }

    class ButtonExitL implements ActionListener {
        public void actionPerformed(ActionEvent evt) {
            System.exit(0);
        }
    }

    class ButtonCalcL implements ActionListener {
        public void actionPerformed(ActionEvent evt) {
            if (gender == "Male") { calculateMale(); }
            else if (gender == "Female") { calculateFemale(); }
        }
    }

    class ButtonClearL implements ActionListener {
        public void actionPerformed(ActionEvent evt) {
            if (gender == "Male") {
                tfMaleWeight.setText("");
                tfMaleWaist.setText("");
                lblMaleBodyFatPct.setText("Body Fat: ");
            } else if (gender == "Female") {
                tfFemaleWeight.setText("");
                tfFemaleWaist.setText("");
                tfWrist.setText("");
                tfHips.setText("");
                tfForeArm.setText("");
                lblFemaleBodyFatPct.setText("Body Fat: ");
            }
        }
    }

    public void calculateMale() {
        weight = Double.parseDouble(tfMaleWeight.getText());
        waist = Double.parseDouble(tfMaleWaist.getText());

        factor1 = (weight * 1.082) + 94.42;
        factor2 = waist * 4.15;
        leanBodyMass = factor1 - factor2;
        bodyFatWeight = weight - leanBodyMass;
        bodyFatPercent = (bodyFatWeight * 100) / weight;

        lblMaleBodyFatPct.setText("Body Fat: " +
            (int)(bodyFatPercent * 100) / 100.0 + "%");
    }

    public void calculateFemale() {
        weight = Double.parseDouble(tfFemaleWeight.getText());
        waist = Double.parseDouble(tfFemaleWaist.getText());
        wrist = Double.parseDouble(tfWrist.getText());
        hips = Double.parseDouble(tfHips.getText());
        forearm = Double.parseDouble(tfForeArm.getText());

        factor1 = (weight * 0.732) + 8.987;
        factor2 = wrist / 3.140;
        factor3 = waist * 0.157;
        factor4 = hips * 0.249;
        factor5 = forearm * 0.434;
        leanBodyMass = factor1 + factor2 - factor3 - factor4 + factor5;
        bodyFatWeight = weight - leanBodyMass;
        bodyFatPercent = (bodyFatWeight * 100) / weight;

        lblFemaleBodyFatPct.setText("Body Fat: " +
            (int)(bodyFatPercent * 100) / 100.0 + "%");
    }

    public static void main(String[] args) {
        JFrame f = new BodyFatCalcGUI();

        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setTitle("Body Fat Calculator");
        f.setLocationRelativeTo(null);

        f.getContentPane();

        f.pack();
        f.setVisible(true);
    }
}