package Monster;

import java.util.Random;

import Item.Item;

public class Monster20 extends Monster{
	
	public Monster20(){
		Random rand = new Random();
		super. name = "정예 간부";
		super. skillName = "강타";
		super.skillRate = 70;
		super. lev = 26 + rand.nextInt(5);
		
		super. hp = 200000 + lev*2;
		super. maxHp = 200000 + lev*2;
		super. atk = 7000 + (int)((double)lev*2);
		super. skillDmg = atk*2;
		super. def = 10000;
		super. isBoss = true;
		super.exp = 263 + lev*10;
		super.imgSrc = "../reImages/mon20.png";
		super.dropItem = (rand.nextInt(2)==1)?Item.whoAmI("atk9"):Item.whoAmI("def9");
		super.dropRate = 10;
		super. pattern = "QWWW";
	}
}