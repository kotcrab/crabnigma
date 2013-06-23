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
public class ReflectorFrame extends JFrame
{
	private static final long serialVersionUID = -1053154317625464019L;
	
	Enigma enigma;
	private EnigmaFrame enigmaFrame;
	
	RotorType Rotor1TypeSelected;
	RotorType Rotor2TypeSelected;
	RotorType Rotor3TypeSelected;

	ReflectorType reflectorType;

	JLabel reflectorLabel = new JLabel("Reflector Type:");
	JRadioButton reflectorTypeA = new JRadioButton("UKW_A");
	JRadioButton reflectorTypeB = new JRadioButton("UKW_B");
	JRadioButton reflectorTypeC = new JRadioButton("UKW_C");
	
	JButton saveButton = new JButton("Save and reset machine");
	
	ReflectorListener reflectorListener = new ReflectorListener();
	
	public ReflectorFrame(Enigma enigma, EnigmaFrame enigmaFrame)
	{
		super("crabNigma - Reflector Configuration");
		
		this.enigma = enigma;
		this.enigmaFrame = enigmaFrame;
		
		Rotor1TypeSelected = enigma.getRot1Type();
		Rotor2TypeSelected = enigma.getRot2Type();
		Rotor3TypeSelected = enigma.getRot3Type();
		
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setSize(350, 100);
		setResizable(false);
		setLayout(new FlowLayout());
		
		add(reflectorLabel);
		
		add(reflectorTypeA);
		add(reflectorTypeB);
		add(reflectorTypeC);
		
		reflectorTypeA.setActionCommand("A");
		reflectorTypeB.setActionCommand("B");
		reflectorTypeC.setActionCommand("C");
		
		reflectorTypeA.addActionListener(reflectorListener);
		reflectorTypeB.addActionListener(reflectorListener);
		reflectorTypeC.addActionListener(reflectorListener);
		
		add(saveButton);
		saveButton.addActionListener(new ActionListener()
		{	
			@Override
			public void actionPerformed(ActionEvent e)
			{
				newEnigma();
				
			}
		});
		
	    ButtonGroup group = new ButtonGroup();
	    group.add(reflectorTypeA);
	    group.add(reflectorTypeB);
	    group.add(reflectorTypeC);
	}
	
	public class ReflectorListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			if(e.getActionCommand() == "A")
			{
				reflectorType = ReflectorType.UKW_A;
			}
			
			if(e.getActionCommand() == "B")
			{
				reflectorType = ReflectorType.UKW_B;
			}
			
			if(e.getActionCommand() == "C")
			{
				reflectorType = ReflectorType.UKW_C;
			}
			
			
		}
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
	
	@Override
	public void setVisible(boolean visible)
	{
		super.setVisible(visible);
		
		reflectorTypeA.setSelected(false);
		reflectorTypeB.setSelected(false);
		reflectorTypeC.setSelected(false);
		
		switch (enigma.getReflectorType())
		{
		case UKW_A:
			reflectorTypeA.setSelected(true);
			break;
		case UKW_B:
			reflectorTypeB.setSelected(true);
			break;
		case UKW_C:
			reflectorTypeC.setSelected(true);
			break;
		default:
			break;

		}
		
	}
}