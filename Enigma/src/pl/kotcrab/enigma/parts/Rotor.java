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

package pl.kotcrab.enigma.parts;

import pl.kotcrab.enigma.parts.types.RotorType;

public class Rotor
{
	public static final char[] alphabet = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };
	public static final char[] rotor1 = { 'E', 'K', 'M', 'F', 'L', 'G', 'D', 'Q', 'V', 'Z', 'N', 'T', 'O', 'W', 'Y', 'H', 'X', 'U', 'S', 'P', 'A', 'I', 'B', 'R', 'C', 'J' };
	public static final char[] rotor2 = { 'A', 'J', 'D', 'K', 'S', 'I', 'R', 'U', 'X', 'B', 'L', 'H', 'W', 'T', 'M', 'C', 'Q', 'G', 'Z', 'N', 'P', 'Y', 'F', 'V', 'O', 'E' };
	public static final char[] rotor3 = { 'B', 'D', 'F', 'H', 'J', 'L', 'C', 'P', 'R', 'T', 'X', 'V', 'Z', 'N', 'Y', 'E', 'I', 'W', 'G', 'A', 'K', 'M', 'U', 'S', 'Q', 'O' };
	public static final char[] rotor4 = { 'E', 'S', 'O', 'V', 'P', 'Z', 'J', 'A', 'Y', 'Q', 'U', 'I', 'R', 'H', 'X', 'L', 'N', 'F', 'T', 'G', 'K', 'D', 'C', 'M', 'W', 'B' };
	public static final char[] rotor5 = { 'V', 'Z', 'B', 'R', 'G', 'I', 'T', 'Y', 'U', 'P', 'S', 'D', 'N', 'H', 'L', 'X', 'A', 'W', 'M', 'J', 'Q', 'O', 'F', 'E', 'C', 'K' };
	
	public static final int rotorLenght = alphabet.length - 1;
	
	private char[] wiring;
	private int rotorCurrentPosition;
	private char position = 'A';
	
	public Rotor(RotorType rotorType)
	{
		rotorCurrentPosition = 0;
		switch (rotorType)
		{
		case I:
			wiring = rotor1.clone();
			break;
		case II:
			wiring = rotor2.clone();
			break;
		case III:
			wiring = rotor3.clone();
			break;
		case IV:
			wiring = rotor4.clone();
			break;
		case V:
			wiring = rotor5.clone();
			break;
		default:
			break;
		}
	}
	
	public char processChar(char _char)
	{
		int ch = (int) _char;
		ch -= 65;
		ch = alphabet[ch] - 65;
		ch = wiring[ch];
		
		return (char) ch;
	}
	
	public int getRotorCurrentPosition()
	{
		return rotorCurrentPosition;
	}

	public char processCharRef(char _char)
	{
		int ch = (int) _char;
		
		int charInRotorPos = 0;
		
		for (int i = 0; i < wiring.length; i++)
			if (wiring[i] == _char)
			{
				charInRotorPos = i;
				break;
			}
		
		ch = alphabet[charInRotorPos];
		
		return (char) ch;
	}
	
	public boolean rotate()
	{
		if (++position > 'Z')
			position = 'A';
		
		for (int i = 0; i < wiring.length; i++)
		{
			if (++wiring[i] > 'Z')
				wiring[i] = 'A';
		}
		
		char tmp = wiring[wiring.length - 1];
		for (int i = wiring.length - 1; i > 0; i--)
		{
			wiring[i] = wiring[i - 1];
		}
		wiring[0] = tmp;
		
		if (rotorCurrentPosition >= wiring.length - 1)
		{
			rotorCurrentPosition = 0;
			return true;
		}
		else
		{
			rotorCurrentPosition++;
			return false;
		}
	}
	
	public boolean undoRotate()
	{
		if (--position < 'A')
			position = 'Z';
		
		for (int i = 0; i < wiring.length; i++)
		{
			if (--wiring[i] < 'A')
				wiring[i] = 'Z';
		}
		
		char tmp = wiring[0];
		for (int i = 0; i < wiring.length - 1; i++)
		{
			wiring[i] = wiring[i + 1]; 
		}
		wiring[wiring.length - 1] = tmp;
		
		if (rotorCurrentPosition == 0)
		{
			rotorCurrentPosition = wiring.length - 1;
			return true;
		}
		else
		{
			rotorCurrentPosition--;
			return false;
		}
	}
}