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

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import pl.kotcrab.enigma.Enigma;
import pl.kotcrab.enigma.parts.Plugboard;
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
public class EnigmaFrame extends JFrame
{
	private static final long serialVersionUID = 8728706163602850776L;
	
	SpinnerModel rotor1Model = new SpinnerNumberModel(0, 0, 25, 1);
	SpinnerModel rotor2Model = new SpinnerNumberModel(0, 0, 25, 1);
	SpinnerModel rotor3Model = new SpinnerNumberModel(0, 0, 25, 1);
	
	JTextArea inputText = new JTextArea();
	JTextArea outputText = new JTextArea();
	
	JSpinner rotor1spinner = new JSpinner(rotor1Model);
	JSpinner rotor2spinner = new JSpinner(rotor2Model);
	JSpinner rotor3spinner = new JSpinner(rotor3Model);
	
	JButton rotorsButton = new JButton("Rotors Configuration");
	JButton reflectorButton = new JButton("Reflector Configuration");
	JButton plugboardButton = new JButton("Plugboard Configuration");
	JButton pasteButton = new JButton("Paste and Process");
	
	JLabel inputLabel = new JLabel("Input:");
	JLabel outputLabel = new JLabel("Output:");
	
	Plugboard plugboard = new Plugboard();
	Enigma enigma = new Enigma(RotorType.I, RotorType.II, RotorType.III, ReflectorType.UKW_B, plugboard);
	
	RotorFrame rotorConfig;
	ReflectorFrame reflectorConfig;
	PlugboardFrame plugboardConfig;
	
	public EnigmaFrame()
	{
		super("Enigma - crabNigma");
		
		rotorConfig = new RotorFrame(enigma, this);
		reflectorConfig = new ReflectorFrame(enigma, this);
		plugboardConfig = new PlugboardFrame(enigma, this);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setSize(720, 295);
		setLocation(50, 50);
		setResizable(false);
		getContentPane().setLayout(null);
		
		rotor1spinner.setBounds(10, 15, 40, 30); // zgodnie z ukadlem w gui
		getContentPane().add(rotor1spinner);
		
		rotor2spinner.setBounds(60, 15, 40, 30);
		getContentPane().add(rotor2spinner);
		
		rotor3spinner.setBounds(110, 15, 40, 30);
		getContentPane().add(rotor3spinner);
		
		// 3 rotor w gui, 1 w engimie
		rotor3spinner.addChangeListener(new ChangeListener()
		{
			@Override
			public void stateChanged(ChangeEvent e)
			{
				for (;;)
				{
					if ((int) rotor3spinner.getValue() == enigma.getRotor1Positon())
					{
						break;
					}
					
					if ((int) rotor3spinner.getValue() > enigma.getRotor1Positon())
					{
						enigma.rotateRot1();
					}
					else
					{
						enigma.undoRotateRot1();
					}
				}
			}
		});
		
		// 2 rotor w gui, 2 w engimie
		rotor2spinner.addChangeListener(new ChangeListener()
		{
			
			@Override
			public void stateChanged(ChangeEvent e)
			{
				for (;;)
				{
					if ((int) rotor2spinner.getValue() == enigma.getRotor2Positon())
					{
						break;
					}
					
					if ((int) rotor2spinner.getValue() > enigma.getRotor2Positon())
					{
						enigma.rotateRot2();
						
					}
					else
					{
						enigma.undoRotateRot2();
					}
				}
				
			}
		});
		
		rotor1spinner.addChangeListener(new ChangeListener()
		{
			
			@Override
			public void stateChanged(ChangeEvent e)
			{
				for (;;)
				{
					if ((int) rotor1spinner.getValue() == enigma.getRotor3Positon())
					{
						break;
					}
					
					if ((int) rotor1spinner.getValue() > enigma.getRotor3Positon())
					{
						enigma.rotateRot3();
						
					}
					else
					{
						enigma.undoRotateRot3();
					}
				}
				
			}
		});
		
		rotorsButton.setBounds(170, 15, 170, 30);
		getContentPane().add(rotorsButton);
		
		rotorsButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				rotorConfig.setVisible(true);
			}
		});
		
		reflectorButton.setBounds(350, 15, 170, 30);
		getContentPane().add(reflectorButton);
		
		reflectorButton.addActionListener(new ActionListener()
		{
			
			@Override
			public void actionPerformed(ActionEvent e)
			{
				reflectorConfig.setVisible(true);
			}
		});
		
		plugboardButton.setBounds(530, 15, 170, 30);
		getContentPane().add(plugboardButton);
		
		plugboardButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				plugboardConfig.setVisible(true);
			}
		});
		
		pasteButton.setBounds(530, 230, 170, 30);
		getContentPane().add(pasteButton);
		
		pasteButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				inputText.setText(getClipboardContents());
				outputText.setText("");
				String textToProcess = getClipboardContents();
				for (int i = 0; i < textToProcess.length(); i++)
				{
					KeyEvent test = new KeyEvent(pasteButton, 0, 0, 0, textToProcess.charAt(i), textToProcess.charAt(i));
					processToOutput(test);
				}
			}
		});
		
		inputLabel.setBounds(10, 46, 35, 20);
		getContentPane().add(inputLabel);
		
		inputText.setLineWrap(true);
		inputText.setBounds(10, 65, 690, 70);
		
		getContentPane().add(inputText);
		
		outputLabel.setBounds(10, 133, 60, 20);
		getContentPane().add(outputLabel);
		
		outputText.setLineWrap(true);
		outputText.setEditable(false);
		outputText.setBounds(10, 152, 690, 70);
		getContentPane().add(outputText);
		
		inputText.addKeyListener(new KeyListener()
		{
			@Override
			public void keyTyped(KeyEvent e)
			{
				processToOutput(e);
			}
			
			@Override
			public void keyPressed(KeyEvent e)
			{
			}
			
			@Override
			public void keyReleased(KeyEvent e)
			{
			}
		});
	}
	
	public void processToOutput(KeyEvent e)
	{
		
		if (e.getKeyChar() == KeyEvent.VK_BACK_SPACE)
		{
			if (outputText.getText().length() > 0)
			{
				if (outputText.getText().charAt(outputText.getText().length() - 1) != ' ')
				{
					enigma.undoRotateRotor();
				}
				
				outputText.setText(outputText.getText().substring(0, outputText.getText().length() - 1));
				rotor3spinner.setValue(enigma.getRotor1Positon());
				rotor2spinner.setValue(enigma.getRotor2Positon());
				rotor1spinner.setValue(enigma.getRotor3Positon());
			}
			
			return;
		}
		
		outputText.setText(outputText.getText() + enigma.fullCharProcess(e.getKeyChar()));
		
		rotor3spinner.setValue(enigma.getRotor1Positon());
		rotor2spinner.setValue(enigma.getRotor2Positon());
		rotor1spinner.setValue(enigma.getRotor3Positon());
	}
	
	@SuppressWarnings("null")
	public String getClipboardContents()
	{
		String result = "";
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		Transferable contents = clipboard.getContents(null);
		boolean hasTransferableText = (contents != null) && contents.isDataFlavorSupported(DataFlavor.stringFlavor);
		if (hasTransferableText)
		{
			try
			{
				result = (String) contents.getTransferData(DataFlavor.stringFlavor);
			}
			catch (UnsupportedFlavorException ex)
			{
				ex.printStackTrace();
			}
			catch (IOException ex)
			{
				ex.printStackTrace();
			}
		}
		return result;
	}
	
}