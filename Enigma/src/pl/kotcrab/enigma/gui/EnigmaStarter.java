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

import java.awt.EventQueue;

/**
 * Warning!
 * This is gui class, it was my first gui application and I don't recomend looking on it or using it for learning!!!
 * 
 * @author Pawel Pastuszak
 * @since 1.0
 *
 */
public class EnigmaStarter
{
	public static void main(String[] args)
	{
		
		EventQueue.invokeLater(new Runnable()
		{
			@Override
			public void run()
			{
				new EnigmaFrame();
			}
		});
	}
}