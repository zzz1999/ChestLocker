package net.mcbbs.zzz1999.ChestLocker;

import cn.nukkit.Player;
import cn.nukkit.blockentity.BlockEntityChest;

import cn.nukkit.level.Position;

import cn.nukkit.nbt.tag.ListTag;
import cn.nukkit.nbt.tag.StringTag;
import net.mcbbs.zzz1999.ChestLocker.Command.*;


import java.util.*;


public class ChestLocker extends cn.nukkit.plugin.PluginBase implements cn.nukkit.event.Listener{

    private static ChestLocker instance = null;

    private Map<String,Boolean> LockSetting = new HashMap<>();
    private Map<String,String> ShareSetting = new HashMap<>();
    private Map<String,Boolean> UnlockSetting = new HashMap<>();
    private Map<String,String> UnshareSetting = new HashMap<>();
    private Map<String,Boolean> DemandChest = new HashMap<>();

    @Override
    public void onEnable(){
        instance = this;
        this.getServer().getPluginManager().registerEvents(new EventListener(this), this);

        this.getServer().getCommandMap().register("chestinfo",new ChestInfo(this));
        this.getServer().getCommandMap().register("chestlock", new ChestLock(this));
        this.getServer().getCommandMap().register("chestshare", new ChestShare(this));
        this.getServer().getCommandMap().register("chestunlock", new ChestUnlock(this));
        this.getServer().getCommandMap().register("chestunshare", new ChestUnshare(this));

    }

    public static ChestLocker getInstance(){
        return instance;
    }

    /*private Block FindChest(Block block){
        switch(block.getDamage()){
            case 2:
                block = block.getLevel().getBlock(block.add(0,0,1));
                break;
            case 3:
                block = block.getLevel().getBlock(block.add(0,0,-1));
                break;
            case 4:
                block = block.getLevel().getBlock(block.add(1,0,0));
                break;
            case 5:
                block = block.getLevel().getBlock(block.add(-1,0,0));
                break;
        }
        return block;
    }*/

    public Map<String,Boolean> getLockSetting (){
        return LockSetting;
    }
    public Map<String,String> getShareSetting (){
        return ShareSetting;
    }
    public Map<String,Boolean> getUnLockSetting (){
        return UnlockSetting;
    }
    public Map<String,String> getUnshareSetting (){
        return UnshareSetting;
    }
    public Map<String,Boolean> getDemandChest(){
        return DemandChest;
    }


    public void setLockSetting (Map<String,Boolean> map){
        LockSetting = map;
    }
    public void setShareSetting (Map<String,String> map){
        ShareSetting = map;
    }
    public void setUnLockSetting (Map<String,Boolean> map){
        UnlockSetting = map;
    }
    public void setUnshareSetting (Map<String,String> map){
        UnshareSetting = map;
    }
    public void setDemandChest(Map<String,Boolean> map ){
        DemandChest = map;
    }



    public String getChestOwner(BlockEntityChest chest){
        if(chest.namedTag.contains("Owner")){
            return chest.namedTag.getString("Owner");
        }
        return null;
    }
    public List<StringTag> getChestGuestList(BlockEntityChest chest){
        return chest.namedTag.getList("Guest",StringTag.class).getAll();
    }
    @Deprecated
    public Boolean isChestOwner(BlockEntityChest chest,String name ){
        return chest.namedTag.exist("Owner") && chest.namedTag.getString("Owner").equals(name);
    }
    public Boolean isChestGuest(BlockEntityChest chest ,String name){
        return chest.namedTag.exist("Guest") && chest.namedTag.getList("Guest",StringTag.class).getAll().contains(new StringTag("", name));
    }
    public Position getChestPair(BlockEntityChest chest){
        if(chest.isPaired()){
            return new Position(chest.namedTag.getInt("pairx"),chest.getY(),chest.namedTag.getInt("pairz"),chest.getLevel());
        }
        return null;
    }

    public boolean testPermission(BlockEntityChest chest,String name) {
        if(chest.namedTag.exist("Owner")) {
            return Objects.equals(chest.namedTag.getString("Owner"), name) || chest.namedTag.getList("Guest",StringTag.class).getAll().contains(new StringTag("", name));
        }else{
            return false;
        }

    }
    public boolean testPermission(BlockEntityChest chest,Player player) {
        if(chest.namedTag.exist("Owner")) {
            return Objects.equals(chest.namedTag.getString("Owner"), player.getName()) || chest.namedTag.getList("Guest",StringTag.class).getAll().contains(new StringTag("", player.getName()));
        }else{
            return false;
        }
    }

    /**
     *
     * @param chest1 xD
     * @param chest2 xD
     * 将chest1的Owner与Guest 信息拷贝到chest2
     */
    public void CopyChestInformation(BlockEntityChest chest1,BlockEntityChest chest2){
        if(chest1.namedTag.exist("Owner")){
            String Owner  = chest1.namedTag.getString("Owner");
            chest2.namedTag.putString("Owner",Owner);
        }
        if(chest1.namedTag.exist("Guest")){
            ListTag<StringTag> Guest = chest1.namedTag.getList("Guest",StringTag.class);
            chest2.namedTag.putList(Guest);
        }
    }



}