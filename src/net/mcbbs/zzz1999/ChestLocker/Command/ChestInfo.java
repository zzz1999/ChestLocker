package net.mcbbs.zzz1999.ChestLocker.Command;

import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.utils.TextFormat;
import net.mcbbs.zzz1999.ChestLocker.ChestLocker;


public class ChestInfo extends Command {
    private ChestLocker plugin;

    public ChestInfo(ChestLocker owner) {
        super("chestinfo", "检测一个箱子所对应的关联", "/chestinfo", new String[]{"ci"});
        this.plugin=owner;
        this.setPermission("ChestLocker.commands.ChestInfo");

    }

    @Override
    public boolean execute(CommandSender sender, String label, String[] args) {
        if(!this.testPermission(sender)) {
            return true;
        }
        if(this.plugin.getDemandChest().containsKey(sender.getName())){
            this.plugin.getDemandChest().remove(sender.getName());
            sender.sendMessage(TextFormat.GRAY+"[ChestLocker] 以退出箱子查询模式");
        }else{
            this.plugin.getDemandChest().put(sender.getName(),true);
            sender.sendMessage(TextFormat.GREEN+"[ChestLocker] 进入箱子查询模式,请点击你想要查询信息的箱子");
        }
        return true;
    }
}
