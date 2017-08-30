package Monster;

import java.util.Random;

import Item.Item;

public class Monster4 extends Monster{
	
	public Monster4(){
		Random rand = new Random();
		super. name = "도적 두목";
		super. skillName = "스매쉬";
		super.skillRate = 10;
		super. lev = 15;
		super. hp = 500 + lev*2;
		super. maxHp = 500 + lev*2;
		super. atk = 20 + (int)((double)lev*2);
		super. skillDmg = super. atk*2;
		super. def = 5;
		super. isBoss = true;
		super. exp = 100 + lev*10;
		super. imgSrc = "../reImages/mon4.png";
		super. dropItem = (rand.nextInt(2)==1)?Item.whoAmI("atk3"):Item.whoAmI("def3");
		super. dropRate = 25;
		super. pattern = "QWER";
	}
	
	
}