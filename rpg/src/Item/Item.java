package Item;

import java.lang.reflect.Constructor;
import java.util.Random;

import Monster.*;

public class Item {
	public String codeName = null;
	public String name = "";
	public int grade = 0;
	public int quantity = 0;
	public String type = "null";
	public int cost = 0;
	public int atk = 0;
	public int def = 0;
	public int effect = 0;
	public String src = "";
	public int code = 0;
	public int fixCost = 0;
	public int fixAtk = 0;
	public int fixDef = 0;
	public int fixEffect = 0;
	public int abilityRate = 0;
	public String abilityName = "특수능력 없음";
	public String abilityEffect = "특수능력 없음";
	public int abilityAtk = 0;
	
	public static Item whoAmI(String name){
		if(name == null){
			return null;
		}
		try {	
			Class cls = Class.forName("Item."+name);		//패키지.클래스 형태로
			Constructor constr = cls.getConstructor();	
			Item retobj = (Item) constr.newInstance();		//이거 새로운 객체 new 생성함
			
			return retobj;
		} catch (Throwable e) {}
		return null;
	}
	
	public static void itemSetUp(Item item){		//등급에 변동이 있을 때만 실행
		if(item.type.equals("atk")){
			item.atk = item.fixAtk*(item.grade+1);		
		}else if(item.type.equals("def")){
			item.def = item.fixDef*(item.grade+1);
		}else if(item.type.equals("staff")){
			item.atk = item.fixAtk*(item.grade+1);
		}else{
			item.effect = item.fixEffect*(item.grade+1);
			item.cost = item.fixCost*(item.grade+1)*item.quantity;
			return;
		}
		item.cost = item.fixCost*(item.grade+1);
		
	}
	
	public boolean specialAbility(Item item, Monster monster){
		int rand = new Random().nextInt(100);
		
		if(item.abilityRate>rand){
			return true;		
		}
		return false;
	}
	
	/* 
	 * 아이템 객체 추가하려면
	 * 인터페이스에 코드 넣고
	 * town컨트롤러 아이템 buy 메소드에서 코드 추가해야 함
	 * */
}
