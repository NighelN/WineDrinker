package winedrinker;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.SwingUtilities;

import org.powerbot.script.PollingScript;
import org.powerbot.script.Script;
import org.powerbot.script.rt6.ClientContext;

import winedrinker.banking.Banking;
import winedrinker.drinking.Drink;
import winedrinker.util.GUI;
import winedrinker.util.Task;

@Script.Manifest(name = "WineDrinker", description = "v0.1 Created by Nighel")
public class WineDrinker extends PollingScript<ClientContext> {
	
	public static boolean usePresets = false;
	public static boolean pressedOk = false;
	
	@SuppressWarnings("rawtypes")
	private List<Task> taskList = new ArrayList<Task>();
	
    @Override
    public void start() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    final GUI startup = new GUI();
                	startup.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    	taskList.addAll(Arrays.asList(new Drink(ctx), new Banking(ctx)));
    }

    @SuppressWarnings("rawtypes")
	@Override
    public void poll() {
    	if(pressedOk) {
	    	for(Task task : taskList) {
	    		if(task.activate()) {
	    			task.execute();
	    		}
	    	}
    	}
    }
}