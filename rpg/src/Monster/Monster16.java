package Monster;

import java.util.Random;

import Item.Item;

public class Monster16 extends Monster{
	
	public Monster16(){
		Random rand = new Random();
		super. name = "마왕군 간부";
		super. skillName = "프로즌 프로스트";
		super. lev = 40;
		super.skillRate = 70;
		
		super. hp = 50000 + lev*2;
		super. maxHp = 800 + lev*2;
		super. atk = 3000 + (int)((double)lev*2);
		super. skillDmg = atk*3;
		super. def = 5000;
		super. isBoss = true;
		super.exp = 430 + lev*10;
		super.imgSrc = "../reImages/mon16.png";
		super.dropItem = (rand.nextInt(2)==1)?Item.whoAmI("atk8"):Item.whoAmI("def8");
		super.dropRate = 15;
		super. pattern = "RREWQR";
	}
}