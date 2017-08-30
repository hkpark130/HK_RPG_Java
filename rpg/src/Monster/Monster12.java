package Monster;

import java.util.Random;

import Item.Item;

public class Monster12 extends Monster{
	
	public Monster12(){
		Random rand = new Random();
		super. name = "사이클롭스";
		super. skillName = "암습";
		super. lev = 25;
		super.skillRate = 10;
		
		super. hp = 25000 + lev*2;
		super. maxHp = 25000 + lev*2;
		super. atk = 2000 ;
		super. skillDmg = atk*2;
		super. def = 1500;
		super. isBoss = true;
		super. exp = 100 + lev*10;
		super. imgSrc = "../reImages/mon12.png";
		super. dropItem = (rand.nextInt(2)==1)?Item.whoAmI("atk7"):Item.whoAmI("def7");
		super. dropRate = 25;
		super. pattern = "WWERQ";
	}
	
	
}