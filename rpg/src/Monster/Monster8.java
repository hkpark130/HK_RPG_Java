package Monster;

import java.util.Random;

import Item.Item;

public class Monster8 extends Monster{
	
	public Monster8(){
		Random rand = new Random();
		super. name = "더블 헤드";
		super. skillName = "암습";
		super. lev = 19;
		super.skillRate = 37;
		
		super. hp = 5000 + lev*2;
		super. maxHp = 5000 + lev*2;
		super. atk = 450 + (int)((double)lev*2);
		super. skillDmg = atk*2;
		super. def = 400;
		super. isBoss = true;
		super. exp = 100 + lev*10;
		super. imgSrc = "../reImages/mon8.png";
		super. dropItem = (rand.nextInt(2)==1)?Item.whoAmI("atk5"):Item.whoAmI("def5");
		super. dropRate = 50;
		super. pattern = "REWQ";
	}
	
	
}