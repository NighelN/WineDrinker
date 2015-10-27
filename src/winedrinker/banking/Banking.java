package winedrinker.banking;

import javax.swing.JOptionPane;

import org.powerbot.script.rt6.ClientContext;
import org.powerbot.script.rt6.Item;

import winedrinker.WineDrinker;
import winedrinker.util.Task;

@SuppressWarnings("rawtypes")
public class Banking extends Task {
	
	private int wineId = 1993;

	public Banking(ClientContext ctx) {
		super(ctx);
	}

	@Override
	public boolean activate() {
		return ctx.bank.opened();
	}

	@SuppressWarnings("deprecation")
	@Override
	public void execute() {
		Item wine = ctx.bank.select().id(wineId).poll();
		if(!ctx.bank.contains(wine)) {
			ctx.controller().stop();
			JOptionPane.showMessageDialog(null, "Ran out of wines.");
		}
		if(ctx.bank.depositInventory()) {
			if(WineDrinker.usePresets) {
				ctx.bank.presetGear1();
			} else {
				ctx.bank.withdraw(wineId, 28);
			}
		}
		ctx.bank.close();
	}
	
}
