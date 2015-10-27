package winedrinker.drinking;

import java.util.concurrent.Callable;

import org.powerbot.script.Condition;
import org.powerbot.script.rt6.ClientContext;
import org.powerbot.script.rt6.Item;

import winedrinker.util.Task;

@SuppressWarnings("rawtypes")
public class Drink extends Task {
	
	private int jugId = 1935, wineId = 1993;
	private int[] bankers = {3418};

	public Drink(ClientContext ctx) {
		super(ctx);
	}

	@Override
	public boolean activate() {
		return !ctx.bank.opened();
	}

	@Override
	public void execute() {
		if(ctx.backpack.select().id(jugId).count() >= 28 || ctx.backpack.select().id(wineId).count() == 0) {
			if(!ctx.npcs.select().id(bankers).isEmpty()) {
				ctx.npcs.select().id(bankers).poll().interact("Bank");
			} else {
				ctx.bank.open();
			}
		} else {
			Condition.wait(new Callable<Boolean>() {
				@Override
				public Boolean call() {
					return drinkWine();
				}
			}, 1800, 1);
		}
	}
	
	private boolean drinkWine() {
		Item wine = ctx.backpack.select().id(wineId).poll();
		wine.click();
		return true;
	}
	
}