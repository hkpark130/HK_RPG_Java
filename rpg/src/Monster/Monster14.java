package Monster;

import java.util.Random;

import Item.Item;

public class Monster14 extends Monster{
	
	public Monster14(){
		Random rand = new Random();
		super. name = "¸¶¿Õ±º±Ã»ç";
		super. skillName = "µ¿»ó";
		super. lev = 21 + rand.nextInt(5);
		super. skillDmg = 67 + (int)((double)lev*2);
		super. hp = 20000 + lev*2;
		super. maxHp = 20000 + lev*2;
		super. atk = 4000 + (int)((double)lev*2);
		super. def = 2000;
		super. isBoss = false;
		super.exp = 263 + lev*10;
		super.imgSrc = "../reImages/mon14.png";
		super.dropItem = Item.whoAmI("atk7");
		super.dropRate = 10;
	}
}