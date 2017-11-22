package net.mcbbs.zzz1999.ChestLocker.Command;

import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.command.data.CommandParameter;
import cn.nukkit.utils.TextFormat;
import net.mcbbs.zzz1999.ChestLocker.ChestLocker;


public class ChestUnshare extends Command {

    private ChestLocker plugin;

    public ChestUnshare(ChestLocker owner) {
        super("chestunshare","取消给某个玩家的共享","/chestshare <取消共享人的名字>",new String[]{"unshare"});
        this.setPermission("ChestLocker.commands.ChestUnshare");
        this.plugin = owner;
        this.commandParameters.put("default",new CommandParameter[]{
                new CommandParameter("Player",CommandParameter.ARG_TYPE_STRING)
        });

    }

    @Override
    public boolean execute(CommandSender sender, String label, String[] args) {
        if(!this.testPermission(sender)) {
            return true;
        }
        if(args.length == 1){
            String invite = args[0];
            if(this.plugin.getUnshareSetting().containsKey(sender.getName())){
                this.plugin.getUnshareSetting().remove(sender.getName());
                sender.sendMessage(TextFormat.GRAY+"[ChestLocker] 你已退出设置箱子取消共享状态");
            }else{
                this.plugin.getUnshareSetting().put(sender.getName(),invite);
                sender.sendMessage(TextFormat.YELLOW+"[ChestLocker] 你希望取消箱子对"+invite+"的共享吗,确认请点击想要取消分享的箱子,取消请重新输入此命令");


            }
        }else if (args.length == 0){
            if(this.plugin.getUnshareSetting().containsKey(sender.getName())){
                this.plugin.getUnshareSetting().remove(sender.getName());
                sender.sendMessage(TextFormat.GRAY+"[ChestLocker] 你已退出设置箱子取消共享状态");
            }else{
                sender.sendMessage(TextFormat.YELLOW+"[ChestLocker] 用法"+this.getUsage());
            }
        }
        return true;
    }
}