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

public class Plugboard
{
	private char[] plugboard;
	private int position = 0;
	
	public Plugboard()
	{
		plugboard = new char[26];
	}
	
	public char processChar(char _char)
	{
		for (int i = 0; i < plugboard.length; i = i + 2)
		{
			char charA = plugboard[i];
			char charB = plugboard[i + 1];
			
			if(charA == ' ' || charB == ' ')
				return _char;
			
			if (_char == charA)
			{
				return charB;
			}
			if (_char == charB)
			{
				return charA;
			}
		}
		
		return _char;
	}
	
	public Plugboard addConnection(char charA, char charB)
	{
		if(position == plugboard.length)
			return this;
		
		plugboard[position] = charA;
		position++;
		plugboard[position] = charB;
		position++;
		
		return this;
	}
	
	public Plugboard removeConnection(char charA, char charB)
	{
		for (int i = 0; i < plugboard.length; i = i + 2)
		{
			char charAp = plugboard[i];
			char charBp = plugboard[i + 1];
			
			if(charA == charAp && charB == charBp)
			{
				for(int j = i; j < plugboard.length - 1; j++)
				{
					plugboard[j] = plugboard[j + 1];
				}
				
				for(int j = i; j < plugboard.length - 1; j++)
				{
					plugboard[j] = plugboard[j + 1];
				}
			}
		}
		return this;
	}
	
	/**
	 * FOR DEBUG
	 */
	public void print()
	{
		for(int i = 0; i < plugboard.length - 1; i++)
		{
			System.out.print(plugboard[i]);
		}
	}
}