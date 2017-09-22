package net.mcbbs.zzz1999.ChestLocker.Command;

import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import net.mcbbs.zzz1999.ChestLocker.ChestLocker;

import java.util.Map;

public class ChestLock extends Command {

    private ChestLocker plugin;

    public ChestLock(ChestLocker owner) {
        super("chestlock","锁住一个箱子","/chestlock 点击你要锁的箱子",new String[]{"cl","lock","锁箱子","锁"});
        this.plugin = owner;
        this.setPermission("ChestLocker.commands.ChestLock");
        this.commandParameters.clear();


    }

    @Override
    public boolean execute(CommandSender sender, String label, String[] args) {
        if(!this.testPermission(sender)) {
            return true;
        }
        if(this.plugin.getLockSetting().containsKey(sender.getName())){
            this.plugin.getLockSetting().remove(sender.getName());
            sender.sendMessage("[ChestLocker] 你已退出锁定箱子模式");
        }else{
            this.plugin.getLockSetting().put(sender.getName(),true);
            sender.sendMessage("[ChestLocker] 你已进入锁定箱子模式,请点击你想要锁的箱子");
        }
        return true;

    }
}