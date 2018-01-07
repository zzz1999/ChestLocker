package net.mcbbs.zzz1999.ChestLocker.Command;

import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.command.data.CommandParameter;
import cn.nukkit.utils.TextFormat;
import net.mcbbs.zzz1999.ChestLocker.ChestLocker;

public class ChestCustom extends Command {
    private ChestLocker plugin;

    public ChestCustom(ChestLocker owner) {
        super("ChestCustom", "自定义一个箱子的CustomName", "/chestcustom <name>", new String[]{"ci"});
        this.plugin=owner;
        this.setPermission("ChestLocker.commands.ChestCustom");
        this.commandParameters.put("default",new CommandParameter[]{
                new CommandParameter("CustomName",CommandParameter.ARG_TYPE_STRING)
        });

    }

    @Override
    public boolean execute(CommandSender sender, String label, String[] args) {
        if(!this.testPermission(sender)) {
            return true;
        }
        if (this.plugin.getCustomName().containsKey(sender.getName())) {
            this.plugin.getCustomName().remove(sender.getName());
            sender.sendMessage(TextFormat.GRAY + "[ChestLocker] 以退出设置箱子自定义名字");
        } else {
            StringBuilder sb = new StringBuilder();
            if (args.length > 0) {
                for(int i = 0 ; i < args.length ; i++){
                    sb.append(args[i]);
                    if(i != args.length-1) {
                        sb.append(" ");
                    }
                }
            }else{
                sb.append(sender.getName()).append("的箱子");
            }
            this.plugin.getCustomName().put(sender.getName(), sb.toString());
            sender.sendMessage(TextFormat.GREEN + "[ChestLocker] 请点击你需要自定义名字的箱子,点击之后系统将扣除500金币并修改这个箱子的名字(如果箱子属于你),取消请再次输入命令");
        }
        return true;
    }
}