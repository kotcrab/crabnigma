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

import pl.kotcrab.enigma.parts.types.ReflectorType;

public class Reflector
{
	public static final char[] set_UKW_A = { 'A', 'E', 'B', 'J', 'C', 'M', 'D', 'Z', 'F', 'L', 'G', 'Y', 'H', 'X', 'I', 'V', 'K', 'W', 'N', 'R', 'O', 'Q', 'P', 'U', 'S', 'T' };
	public static final char[] set_UKW_B = { 'A', 'Y', 'B', 'R', 'C', 'U', 'D', 'H', 'E', 'Q', 'F', 'S', 'G', 'L', 'I', 'P', 'J', 'X', 'K', 'N', 'M', 'O', 'T', 'Z', 'V', 'W' };
	public static final char[] set_UKW_C = { 'A', 'F', 'B', 'V', 'C', 'P', 'D', 'J', 'E', 'I', 'G', 'O', 'H', 'Y', 'K', 'R', 'L', 'Z', 'M', 'X', 'N', 'W', 'Q', 'T', 'S', 'U' };
	
	private char[] refSetup;
	
	public Reflector(ReflectorType refType)
	{
		switch (refType)
		{
		case UKW_A:
			refSetup = set_UKW_A;
			break;
		case UKW_B:
			refSetup = set_UKW_B;
			break;
		case UKW_C:
			refSetup = set_UKW_C;
			break;
		default:
			break;
		
		}
		
	}
	
	public char reflectChar(char _char)
	{
		for (int i = 0; i < refSetup.length; i = i + 2)
		{
			char charA = refSetup[i];
			char charB = refSetup[i + 1];
			
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
}