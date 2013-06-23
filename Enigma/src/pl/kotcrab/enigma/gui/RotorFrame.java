/*******************************************************************************
 * Copyright 2013 Pawel Pastuszak
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/

package pl.kotcrab.enigma.gui;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;

import pl.kotcrab.enigma.Enigma;
import pl.kotcrab.enigma.parts.types.ReflectorType;
import pl.kotcrab.enigma.parts.types.RotorType;

/**
 * Warning!
 * This is gui class, it was my first gui application and I don't recomend looking on it or using it for learning!!!
 * 
 * @author Pawel Pastuszak
 * @since 1.0
 *
 */
public class RotorFrame extends JFrame
{
	private static final long serialVersionUID = -6626543615667025349L;
	
	Enigma enigma;
	private EnigmaFrame enigmaFrame;
	
	JLabel rotor1Label = new JLabel("Rotor 1 Type:");
	JRadioButton rotor1TypeI = new JRadioButton("I");
	JRadioButton rotor1TypeII = new JRadioButton("II");
	JRadioButton rotor1TypeIII = new JRadioButton("III");
	JRadioButton rotor1TypeIV = new JRadioButton("IV");
	JRadioButton rotor1TypeV = new JRadioButton("V");
	
	JLabel rotor2Label = new JLabel("Rotor 2 Type:");
	JRadioButton rotor2TypeI = new JRadioButton("I");
	JRadioButton rotor2TypeII = new JRadioButton("II");
	JRadioButton rotor2TypeIII = new JRadioButton("III");
	JRadioButton rotor2TypeIV = new JRadioButton("IV");
	JRadioButton rotor2TypeV = new JRadioButton("V");
	
	JLabel rotor3Label = new JLabel("Rotor 3 Type:");
	JRadioButton rotor3TypeI = new JRadioButton("I");
	JRadioButton rotor3TypeII = new JRadioButton("II");
	JRadioButton rotor3TypeIII = new JRadioButton("III");
	JRadioButton rotor3TypeIV = new JRadioButton("IV");
	JRadioButton rotor3TypeV = new JRadioButton("V");
	
	JButton saveButton = new JButton("Save and reset machine");
	
	Rotor1Listener rotor1Listener = new Rotor1Listener(); 
	Rotor2Listener rotor2Listener = new Rotor2Listener(); 
	Rotor3Listener rotor3Listener = new Rotor3Listener(); 
	
	RotorType Rotor1TypeSelected;
	RotorType Rotor2TypeSelected;
	RotorType Rotor3TypeSelected;

	ReflectorType reflectorType;

	public RotorFrame(Enigma enigma, EnigmaFrame enigmaFrame)
	{
		super("crabNigma - Rotors Configuration");
		
		this.enigma = enigma;
		this.enigmaFrame = enigmaFrame;
		
		Rotor1TypeSelected = enigma.getRot1Type();
		Rotor2TypeSelected = enigma.getRot2Type();
		Rotor3TypeSelected = enigma.getRot3Type();
		reflectorType = enigma.getReflectorType();
		
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setSize(350, 150);
		setResizable(false);
		setLayout(new FlowLayout());
		
		add(rotor1Label);
		
		add(rotor1TypeI);
		add(rotor1TypeII);
		add(rotor1TypeIII);
		add(rotor1TypeIV);
		add(rotor1TypeV);
	    
		add(rotor2Label);
		
		add(rotor2TypeI);
		add(rotor2TypeII);
		add(rotor2TypeIII);
		add(rotor2TypeIV);
		add(rotor2TypeV);
		
		add(rotor3Label);
		
		add(rotor3TypeI);
		add(rotor3TypeII);
		add(rotor3TypeIII);
		add(rotor3TypeIV);
		add(rotor3TypeV);
		
	    ButtonGroup group1 = new ButtonGroup();
	    group1.add(rotor1TypeI);
	    group1.add(rotor1TypeII);
	    group1.add(rotor1TypeIII);
	    group1.add(rotor1TypeIV);
	    group1.add(rotor1TypeV);
		
	    ButtonGroup group2 = new ButtonGroup();
	    group2.add(rotor2TypeI);
	    group2.add(rotor2TypeII);
	    group2.add(rotor2TypeIII);
	    group2.add(rotor2TypeIV);
	    group2.add(rotor2TypeV);
	    
	    ButtonGroup group3 = new ButtonGroup();
	    group3.add(rotor3TypeI);
	    group3.add(rotor3TypeII);
	    group3.add(rotor3TypeIII);
	    group3.add(rotor3TypeIV);
	    group3.add(rotor3TypeV);
		
		rotor1TypeI.addActionListener(rotor1Listener);
		rotor1TypeII.addActionListener(rotor1Listener);
		rotor1TypeIII.addActionListener(rotor1Listener);
		rotor1TypeIV.addActionListener(rotor1Listener);
		rotor1TypeV.addActionListener(rotor1Listener);
		
		rotor1TypeI.setActionCommand("I");
		rotor1TypeII.setActionCommand("II");
		rotor1TypeIII.setActionCommand("III");
		rotor1TypeIV.setActionCommand("IV");
		rotor1TypeV.setActionCommand("V");
		
		rotor2TypeI.addActionListener(rotor2Listener);
		rotor2TypeII.addActionListener(rotor2Listener);
		rotor2TypeIII.addActionListener(rotor2Listener);
		rotor2TypeIV.addActionListener(rotor2Listener);
		rotor2TypeV.addActionListener(rotor2Listener);
		
		rotor2TypeI.setActionCommand("I");
		rotor2TypeII.setActionCommand("II");
		rotor2TypeIII.setActionCommand("III");
		rotor2TypeIV.setActionCommand("IV");
		rotor2TypeV.setActionCommand("V");
		
		rotor3TypeI.addActionListener(rotor3Listener);
		rotor3TypeII.addActionListener(rotor3Listener);
		rotor3TypeIII.addActionListener(rotor3Listener);
		rotor3TypeIV.addActionListener(rotor3Listener);
		rotor3TypeV.addActionListener(rotor3Listener);
		
		rotor3TypeI.setActionCommand("I");
		rotor3TypeII.setActionCommand("II");
		rotor3TypeIII.setActionCommand("III");
		rotor3TypeIV.setActionCommand("IV");
		rotor3TypeV.setActionCommand("V");
		
		add(saveButton);
		saveButton.addActionListener(new ActionListener()
		{
			
			@Override
			public void actionPerformed(ActionEvent e)
			{
				newEnigma();
			}
		});
	}
	
	public void newEnigma()
	{
		enigma = new Enigma(Rotor1TypeSelected, Rotor2TypeSelected, Rotor3TypeSelected, reflectorType, enigma.getPlugboard());
		setVisible(false);
		enigmaFrame.enigma = enigma;
		enigmaFrame.rotor3spinner.setValue(enigma.getRotor1Positon());
		enigmaFrame.rotor2spinner.setValue(enigma.getRotor2Positon());
		enigmaFrame.rotor1spinner.setValue(enigma.getRotor3Positon());
		enigmaFrame.inputText.setText("");
		enigmaFrame.outputText.setText("");
	}
	
	public class Rotor1Listener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e)
		{
			if(e.getActionCommand() == "I")
			{
				Rotor1TypeSelected = RotorType.I;
			}
			
			if(e.getActionCommand() == "II")
			{
				Rotor1TypeSelected = RotorType.II;
			}
			
			if(e.getActionCommand() == "III")
			{
				Rotor1TypeSelected = RotorType.III;
			}
			
			if(e.getActionCommand() == "IV")
			{
				Rotor1TypeSelected = RotorType.IV;
			}
			
			if(e.getActionCommand() == "V")
			{
				Rotor1TypeSelected = RotorType.V;
			}
		}
		
	}
	
	public class Rotor2Listener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e)
		{
			if(e.getActionCommand() == "I")
			{
				Rotor2TypeSelected = RotorType.I;
			}
			
			if(e.getActionCommand() == "II")
			{
				Rotor2TypeSelected = RotorType.II;
			}
			
			if(e.getActionCommand() == "III")
			{
				Rotor2TypeSelected = RotorType.III;
			}
			
			if(e.getActionCommand() == "IV")
			{
				Rotor2TypeSelected = RotorType.IV;
			}
			
			if(e.getActionCommand() == "V")
			{
				Rotor2TypeSelected = RotorType.V;
			}
		}
		
	}
	
	public class Rotor3Listener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e)
		{
			if(e.getActionCommand() == "I")
			{
				Rotor3TypeSelected = RotorType.I;
			}
			
			if(e.getActionCommand() == "II")
			{
				Rotor3TypeSelected = RotorType.II;
			}
			
			if(e.getActionCommand() == "III")
			{
				Rotor3TypeSelected = RotorType.III;
			}
			
			if(e.getActionCommand() == "IV")
			{
				Rotor3TypeSelected = RotorType.IV;
			}
			
			if(e.getActionCommand() == "V")
			{
				Rotor3TypeSelected = RotorType.V;
			}
		}
		
	}
	
	@Override
	public void setVisible(boolean visible)
	{
		super.setVisible(visible);
		
		rotor1TypeI.setSelected(false);
		rotor1TypeII.setSelected(false);
		rotor1TypeIII.setSelected(false);
		rotor1TypeIV.setSelected(false);
		rotor1TypeV.setSelected(false);
		
		rotor2TypeI.setSelected(false);
		rotor2TypeII.setSelected(false);
		rotor2TypeIII.setSelected(false);
		rotor2TypeIV.setSelected(false);
		rotor2TypeV.setSelected(false);
		
		rotor3TypeI.setSelected(false);
		rotor3TypeII.setSelected(false);
		rotor3TypeIII.setSelected(false);
		rotor3TypeIV.setSelected(false);
		rotor3TypeV.setSelected(false);
		
		switch (enigma.getRot1Type())
		{
		case I:
			rotor1TypeI.setSelected(true);
			break;
		case II:
			rotor1TypeII.setSelected(true);
			break;
		case III:
			rotor1TypeIII.setSelected(true);
			break;
		case IV:
			rotor1TypeIV.setSelected(true);
			break;
		case V:
			rotor1TypeV.setSelected(true);
			break;
		default:
			break;
		}
		
		switch (enigma.getRot2Type())
		{
		case I:
			rotor2TypeI.setSelected(true);
			break;
		case II:
			rotor2TypeII.setSelected(true);
			break;
		case III:
			rotor2TypeIII.setSelected(true);
			break;
		case IV:
			rotor2TypeIV.setSelected(true);
			break;
		case V:
			rotor2TypeV.setSelected(true);
			break;
		default:
			break;
		}
		
		switch (enigma.getRot3Type())
		{
		case I:
			rotor3TypeI.setSelected(true);
			break;
		case II:
			rotor3TypeII.setSelected(true);
			break;
		case III:
			rotor3TypeIII.setSelected(true);
			break;
		case IV:
			rotor3TypeIV.setSelected(true);
			break;
		case V:
			rotor3TypeV.setSelected(true);
			break;
		default:
			break;
		}
	}
}