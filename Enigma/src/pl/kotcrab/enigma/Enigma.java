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

package pl.kotcrab.enigma;

import pl.kotcrab.enigma.parts.Plugboard;
import pl.kotcrab.enigma.parts.Reflector;
import pl.kotcrab.enigma.parts.Rotor;
import pl.kotcrab.enigma.parts.types.ReflectorType;
import pl.kotcrab.enigma.parts.types.RotorType;

public class Enigma
{
	Rotor rot1;
	Rotor rot2;
	Rotor rot3;
	
	RotorType rot1Type;
	RotorType rot2Type;
	RotorType rot3Type;
	
	Reflector reflector;
	ReflectorType reflectorType;
	
	Plugboard plugboard;
	
	public Enigma(RotorType rotor1Type, RotorType rotor2Type, RotorType rotor3Type, ReflectorType refType, Plugboard plugboard)
	{
		rot1 = new Rotor(rotor1Type);
		rot2 = new Rotor(rotor2Type);
		rot3 = new Rotor(rotor3Type);
		
		rot1Type = rotor1Type;
		rot2Type = rotor2Type;
		rot3Type = rotor3Type;
		
		reflector = new Reflector(refType);
		reflectorType = refType;
		
		this.plugboard = plugboard;
	}
	
	public void fullReset()
	{
		boolean stop = false;
		do
		{
			undoRotateRotor();
			
			if(rot1.getRotorCurrentPosition() != 0)
				continue;
			else
			{
				if(rot2.getRotorCurrentPosition() != 0)
					continue;
				else
				{
					if(rot3.getRotorCurrentPosition() != 0)
						continue;
					else
					{
						stop = true;
					}
				}
			}
			
		}while (stop == false);
		
	}
	
	public ReflectorType getReflectorType()
	{
		return reflectorType;
	}

	public Reflector getReflector()
	{
		return reflector;
	}
	
	public Plugboard getPlugboard()
	{
		return plugboard;
	}

	public void setPlugboard(Plugboard plugboard)
	{
		this.plugboard = plugboard;
	}
	
	public int getRotor1Positon()
	{
		return rot1.getRotorCurrentPosition();
	}
	
	public int getRotor2Positon()
	{
		return rot2.getRotorCurrentPosition();
	}
	
	public int getRotor3Positon()
	{
		return rot3.getRotorCurrentPosition();
	}
	
	public char fullCharProcess(char _char)
	{
		if (_char >= 'a')
		{
			_char -= 32;
		}
		
		if (_char == ' ')
			return ' ';
		
		_char = plugboard.processChar(_char);
		
		_char = rot1.processChar(_char);
		_char = rot2.processChar(_char);
		_char = rot3.processChar(_char);
		
		_char = reflectChar(_char);
		
		_char = rot3.processCharRef(_char);
		_char = rot2.processCharRef(_char);
		_char = rot1.processCharRef(_char);
		
		_char = plugboard.processChar(_char);
		
		rotateRotors();
		
		return _char;
	}
	
	public String processString(String s)
	{
		String res = "";
		
		for(int i = 0; i < s.length(); i++)
		{
			res += fullCharProcess(s.charAt(i));
		}
		
		return res;
	}
	
	public char reflectChar(char _char)
	{
		return reflector.reflectChar(_char);
	}
	
	public void setRot1Pos(int newRotorPos)
	{
		if(newRotorPos < 0 || newRotorPos >= 26)
			return;
		
		if(getRotor1Positon() == newRotorPos)
			return;
		
		if(getRotor1Positon() > newRotorPos)
		{
			do
			{
				rot1.undoRotate();
			}while(getRotor1Positon() > newRotorPos);
			
		}
		
		if(getRotor1Positon() < newRotorPos)
		{
			do
			{
				rot1.rotate();
			}while(getRotor1Positon() < newRotorPos);
			
		}

	}
	
	public void setRot2Pos(int newRotorPos)
	{
		if(newRotorPos < 0 || newRotorPos >= 26)
			return;
		
		if(getRotor2Positon() == newRotorPos)
			return;
		
		if(getRotor2Positon() > newRotorPos)
		{
			do
			{
				rot2.undoRotate();
			}while(getRotor2Positon() > newRotorPos);
			
		}
		
		if(getRotor2Positon() < newRotorPos)
		{
			do
			{
				rot2.rotate();
			}while(getRotor2Positon() < newRotorPos);
			
		}

	}
	
	public void setRot3Pos(int newRotorPos)
	{
		if(newRotorPos < 0 || newRotorPos >= 26)
			return;
		
		if(getRotor3Positon() == newRotorPos)
			return;
		
		if(getRotor3Positon() > newRotorPos)
		{
			do
			{
				rot3.undoRotate();
			}while(getRotor3Positon() > newRotorPos);
			
		}
		
		if(getRotor3Positon() < newRotorPos)
		{
			do
			{
				rot3.rotate();
			}while(getRotor3Positon() < newRotorPos);
			
		}

	}
	
	public void rotateRot1()
	{
		rot1.rotate();
	}
	
	public void rotateRot2()
	{
		rot2.rotate();
	}
	
	public void rotateRot3()
	{
		rot3.rotate();
	}
	
	public void undoRotateRot1()
	{
		rot1.undoRotate();
	}
	
	public void undoRotateRot2()
	{
		rot2.undoRotate();
	}
	
	public void undoRotateRot3()
	{
		rot3.undoRotate();
	}
	
	public void rotateRotors()
	{
		if (rot1.rotate() == true)
		{
			if (rot2.rotate() == true)
			{
				rot3.rotate();
			}
		}
	}
	
	public void undoRotateRotor()
	{
		if (rot1.undoRotate() == true)
		{
			if (rot2.undoRotate() == true)
			{
				rot3.undoRotate();
			}
		}
	}
	
	public RotorType getRot1Type()
	{
		return rot1Type;
	}

	public RotorType getRot2Type()
	{
		return rot2Type;
	}

	public RotorType getRot3Type()
	{
		return rot3Type;
	}
}
