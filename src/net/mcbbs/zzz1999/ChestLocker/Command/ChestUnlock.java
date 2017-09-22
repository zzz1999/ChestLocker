package net.mcbbs.zzz1999.ChestLocker.Command;

import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.utils.TextFormat;
import net.mcbbs.zzz1999.ChestLocker.ChestLocker;

import java.util.Map;

public class ChestUnlock extends Command {

    private ChestLocker plugin;

    public ChestUnlock(ChestLocker owner) {
        super("chestunlock", "解锁一个你曾经锁住的箱子", "/chestunlock 点击你要解锁的箱子",new String[]{"解锁","解锁箱子","unlock"});
        this.plugin = owner;
        this.setPermission("ChestLocker.commands.ChestUnlock");
        this.commandParameters.clear();

    }

    @Override
    public boolean execute(CommandSender sender, String label, String[] args) {
        if(!this.testPermission(sender)) {
            return true;
        }
        if(this.plugin.getUnLockSetting().containsKey(sender.getName())){
            this.plugin.getUnLockSetting().remove(sender.getName());
            sender.sendMessage(TextFormat.GRAY+"[ChestLocker] 你已退出解锁箱子模式");
        }else{
            this.plugin.getUnLockSetting().put(sender.getName(),true);
            sender.sendMessage(TextFormat.YELLOW+"[ChestLocker] 你已进入解锁箱子模式,如需退出请重新输入此命令");
        }
        return true;
    }
}