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
import pl.kotcrab.enigma.parts.types.ReflectorType;
import pl.kotcrab.enigma.parts.types.RotorType;

public class EnigmaNoGuiExample
{
	public static void main(String[] args)
	{
		Enigma enigma = new Enigma(RotorType.II, RotorType.IV, RotorType.I, ReflectorType.UKW_B, new Plugboard().addConnection('G', 'T').addConnection('J', 'P'));
		enigma.setRot1Pos(4);
		enigma.setRot2Pos(7);
		enigma.setRot3Pos(14);
		
		System.out.println(enigma.processString("WELCOME TO CRABNIGMA")); //this will result NSVGUHR EC HLDSUESWY
		
		enigma.fullReset();
		enigma.setRot1Pos(4);
		enigma.setRot2Pos(7);
		enigma.setRot3Pos(14);
		
		System.out.println(enigma.processString("NSVGUHR EC HLDSUESWY")); //this will result WELCOME TO CRABNIGMA

	}
}