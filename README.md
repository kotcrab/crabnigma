crabNigma
=========

Enigma implementation in Java with GUI

No gui example: (Check out EnigmaNoGuiExample.java)
```java
Enigma enigma = new Enigma(RotorType.II, RotorType.IV, RotorType.I, ReflectorType.UKW_B, new Plugboard().addConnection('G', 'T').addConnection('J', 'P'));
enigma.setRot1Pos(4);
enigma.setRot2Pos(7);
enigma.setRot3Pos(14);

System.out.println(enigma.processString("WELCOME TO CRABNIGMA")); //this will result NSVGUHR EC HLDSUESWY
```

GUI screenshot:
![GUI screenshot](dl.kotcrab.pl/github/crabnigma.png)
