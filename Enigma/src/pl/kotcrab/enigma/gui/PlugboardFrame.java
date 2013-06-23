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

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

import pl.kotcrab.enigma.Enigma;

/**
 * Warning!
 * This is gui class, it was my first gui application and I don't recomend looking on it or using it for learning!!!
 * 
 * @author Pawel Pastuszak
 * @since 1.0
 *
 */
public class PlugboardFrame extends JFrame implements ListSelectionListener
{
	private static final long serialVersionUID = -7404237898795655055L;
	
	private Enigma enigma;
	private JList<String> list;
	private DefaultListModel<String> listModel;
	
	private static final String hireString = "Add";
	private static final String fireString = "Remove";
	private JButton removeButton;
	private JTextField employeeName;
	
	public PlugboardFrame(Enigma enigma, EnigmaFrame enigmaFrame)
	{
		super("crabNigma - Plugboard Configuration");
		
		this.enigma = enigma;
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setLayout(new BorderLayout());
		setSize(350, 135);
		
		listModel = new DefaultListModel<String>();
		
		list = new JList<String>(listModel);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setSelectedIndex(0);
		list.addListSelectionListener(this);
		list.setVisibleRowCount(3);
		list.setLayoutOrientation(JList.VERTICAL_WRAP);
		
		JScrollPane listScrollPane = new JScrollPane(list);
		
		JButton addButton = new JButton(hireString);
		HireListener hireListener = new HireListener(addButton);
		addButton.setActionCommand(hireString);
		addButton.addActionListener(hireListener);
		addButton.setEnabled(false);
		
		removeButton = new JButton(fireString);
		removeButton.setActionCommand(fireString);
		removeButton.addActionListener(new FireListener());
		removeButton.setEnabled(false);
		
		employeeName = new JTextField(10);
		employeeName.addActionListener(hireListener);
		employeeName.setText("Format: AD");
		employeeName.getDocument().addDocumentListener(hireListener);

		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.LINE_AXIS));
		buttonPane.add(removeButton);
		buttonPane.add(Box.createHorizontalStrut(5));
		buttonPane.add(new JSeparator(SwingConstants.VERTICAL));
		buttonPane.add(Box.createHorizontalStrut(5));
		buttonPane.add(employeeName);
		buttonPane.add(addButton);
		buttonPane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		
		add(listScrollPane, BorderLayout.CENTER);
		add(buttonPane, BorderLayout.PAGE_END);
	}
	
	class FireListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			int index = list.getSelectedIndex();
			enigma.getPlugboard().removeConnection(listModel.getElementAt(index).charAt(0), listModel.getElementAt(index).charAt(1));
			listModel.remove(index);
			
			int size = listModel.getSize();
			
			if (size == 0)
			{
				removeButton.setEnabled(false);			
			}
			else
			{ 
				if (index == listModel.getSize())
				{
					index--;
				}
				
				list.setSelectedIndex(index);
				list.ensureIndexIsVisible(index);
			}
		}
	}
	
	class HireListener implements ActionListener, DocumentListener
	{
		private boolean alreadyEnabled = false;
		private JButton button;
		
		public HireListener(JButton button)
		{
			this.button = button;
		}
		
		public void actionPerformed(ActionEvent e)
		{
			String name = employeeName.getText();
			name = name.toUpperCase();
			employeeName.setText(name);
	        StringBuilder stringBuilder = new StringBuilder(name);
	        String nameInverted = stringBuilder.reverse().toString();
	        
			if (name.equals("") || alreadyInList(name)|| alreadyInList(nameInverted) || name.length() != 2)
			{
				Toolkit.getDefaultToolkit().beep();
				employeeName.requestFocusInWindow();
				employeeName.selectAll();
				return;
			}
			
			int index = list.getSelectedIndex();
			if (index == -1)
			{ 
				index = 0;
			}
			else
			{ 
				index++;
			}
			name = name.toUpperCase();

			enigma.getPlugboard().addConnection(name.charAt(0), name.charAt(1));
			listModel.addElement(employeeName.getText());
			
			employeeName.requestFocusInWindow();
			employeeName.setText("");
			
			list.setSelectedIndex(index);
			list.ensureIndexIsVisible(index);
		}
		
		protected boolean alreadyInList(String name)
		{
			return listModel.contains(name);
		}
		
		public void insertUpdate(DocumentEvent e)
		{
			enableButton();
		}
		
		public void removeUpdate(DocumentEvent e)
		{
			handleEmptyTextField(e);
		}
		
		public void changedUpdate(DocumentEvent e)
		{
			if (!handleEmptyTextField(e))
			{
				enableButton();
			}
		}
		
		private void enableButton()
		{
			if (!alreadyEnabled)
			{
				button.setEnabled(true);
			}
		}
		
		private boolean handleEmptyTextField(DocumentEvent e)
		{
			if (e.getDocument().getLength() <= 0)
			{
				button.setEnabled(false);
				alreadyEnabled = false;
				return true;
			}
			return false;
		}
	}
	
	public void valueChanged(ListSelectionEvent e)
	{
		if (e.getValueIsAdjusting() == false)
		{
			
			if (list.getSelectedIndex() == -1)
			{
				removeButton.setEnabled(false);
				
			}
			else
			{
				removeButton.setEnabled(true);
			}
		}
	}
}
