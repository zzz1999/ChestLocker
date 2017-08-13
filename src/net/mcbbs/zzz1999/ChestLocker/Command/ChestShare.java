package net.mcbbs.zzz1999.ChestLocker.Command;

import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;

import cn.nukkit.utils.TextFormat;
import net.mcbbs.zzz1999.ChestLocker.ChestLocker;

import java.util.Map;

public class ChestShare extends Command {

    private ChestLocker plugin;

    public ChestShare(ChestLocker owner) {
        super("chestshare","共享一个你锁住的箱子","/chestshare <共享给谁>",new String[]{"cs","分享","分享箱子","share"});
        this.plugin = owner;
        this.setPermission("ChestLocker.commands.chestshare");
        this.commandParameters.clear();


    }

    @Override
    public boolean execute(CommandSender sender, String label, String[] args) {
        if(!this.testPermission(sender)) {
            return true;
        }
        if(args.length == 1){
            String invite = args[0];
            if(this.plugin.getShareSetting().containsKey(sender.getName())){
                Map<String,String> map = this.plugin.getShareSetting();
                map.remove(sender.getName());
                this.plugin.setShareSetting(map);
                sender.sendMessage(TextFormat.GRAY+"[ChestLocker] 你已退出设置箱子共享状态");
            }else{
                Map<String,String> map = this.plugin.getShareSetting();
                map.put(sender.getName(),invite);
                this.plugin.setShareSetting(map);
                sender.sendMessage(TextFormat.YELLOW+"[ChestLocker] 你希望与"+invite+"一起分享箱子吗,确认请点击想要分享的箱子,取消请重新输入此命令");


            }
        }else if (args.length == 0){
            if(this.plugin.getShareSetting().containsKey(sender.getName())){
                Map<String,String> map = this.plugin.getShareSetting();
                map.remove(sender.getName());
                this.plugin.setShareSetting(map);
                sender.sendMessage(TextFormat.GRAY+"[ChestLocker] 你已退出设置箱子共享状态");
            }else{
                sender.sendMessage(TextFormat.YELLOW+"[ChestLocker] 用法"+this.getUsage());
            }
        }
        return true;
    }
}