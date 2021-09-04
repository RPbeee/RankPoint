package net.kyuukei.rpbeee.rankpoint;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public final class Rankpoint extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getLogger().info("プラグインが開始しました");
        saveDefaultConfig();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        reloadConfig();
        FileConfiguration config = getConfig();
        String[] ranks = new String[9];
        ranks[0] = "black";ranks[1] = "red";ranks[2] = "orange";ranks[3] = "yellow";ranks[4] = "green";
        ranks[5] = "blue";ranks[6] = "indigo";ranks[7] = "violet";ranks[8] = "white";
        if (cmd.getName().equalsIgnoreCase("rankpoint") && sender.hasPermission("rankpoint.rankpoint")) {
            if (args.length == 0) {
                sender.sendMessage("[rankpoint] Hi!");
            } else if (args[0].equalsIgnoreCase("points")) {
                sender.sendMessage("[rankpoint] " + sender.getName() + "さんの現在のポイントは:" + ChatColor.GOLD + config.getInt("points."+sender.getName()) + "点です");
            } else if (args[0].equalsIgnoreCase("standd")) {
                for (int i=1;i<9;i++) {
                    sender.sendMessage("[rankpoint] "+ranks[i]+"の必要ポイントは:"+ChatColor.GOLD+config.getInt("rank."+ranks[i]+".point")+"点です");
                }
            } else if (args[0].equalsIgnoreCase("oink")) {
                sender.sendMessage("[rankpig]"+ChatColor.LIGHT_PURPLE+"Oink Oink!!");
            } else if (args[0].equalsIgnoreCase("help")){
                sender.sendMessage("[rankpoint] /rankpoint はランクポイントの基本コマンドです");
                sender.sendMessage("[rankpoint] /rankpoint points  今の自分のポイントを確認する");
                sender.sendMessage("[rankpoint] /rankpoint standd  各ランクの基準ポイントを見る");
                sender.sendMessage("[rankpoint] /rankpoint oink  ぶ〜ぶ〜");
                sender.sendMessage("[rankpoint] /rankpoint help  このヘルプを表示する");
            } else {
                sender.sendMessage("[rankpoint] コマンド間違ってます");
            }
        } else if (cmd.getName().equalsIgnoreCase("rankpad") && sender.hasPermission("rankpoint.rankpad")) {
            if (args.length == 0) {
                sender.sendMessage("[rankpoint] なにもすることないよ");
            } else if (args[0].equalsIgnoreCase("padd")) {
                sender.sendMessage("[rankpoint] point adding command");
                if (args.length != 3) {
                    sender.sendMessage("[rankpoint] 引数足りない");
                    return true;
                } else if (!config.contains("points."+args[1])) {
                    config.set("points."+args[1], Integer.parseInt(args[2]));
                    sender.sendMessage("[rankpoint] "+args[1]+" のポイントを "+args[2]+" 点にした");
                } else {
                    config.set("points."+args[1], config.getInt("points."+args[1])+Integer.parseInt(args[2]));
                    sender.sendMessage("[rankpoint] "+args[1]+" のポイントを "+config.getInt("points."+args[1])+" 点にした");
                }
            } else if (args[0].equalsIgnoreCase("pset")) {
                sender.sendMessage("[rankpoint] point setting command");
                if (args.length != 3) {
                    sender.sendMessage("[rankpoint] 引数足りない");
                    return true;
                }
                config.set("points."+args[1], Integer.parseInt(args[2]));
                sender.sendMessage("[rankpoint] "+args[1]+" のポイントを "+args[2]+" 点にした");
            } else if (args[0].equalsIgnoreCase("reload")) {
                reloadConfig();
                for (String player : config.getConfigurationSection("points").getKeys(false)) {
                    int point = config.getInt("points."+player);
                    if (point < config.getInt("rank.red.point")){
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), String.format(config.getString("rank.black.command"), player));
                    } else {
                        for (int j = 8; j > 0; j--) {
                            int need = config.getInt("rank." + ranks[j] + ".point");
                            if (point >= need) {
                                //rankup command
                                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), String.format(config.getString("rank." + ranks[j] + ".command"), player));
                                break;
                            }
                        }
                    }
                }
                sender.sendMessage("[rankpoint] コンフィグとポイントリロードしたよ");
            } else if (args[0].equalsIgnoreCase("help")){
                sender.sendMessage("[rankpoint] /rankpad はランクポイントのアドミンコマンドです");
                sender.sendMessage("[rankpoint] /rankpad padd [プレイヤー] [追加するポイント量]");
                sender.sendMessage("[rankpoint] 特定のプレイヤーにポイントを追加する");
                sender.sendMessage("[rankpoint] /rankpad pset [プレイヤー] [設定するポイント量]");
                sender.sendMessage("[rankpoint] 特定のプレイヤーのポイントを設定する");
                sender.sendMessage("[rankpoint] /rankpad reload");
                sender.sendMessage("[rankpoint] リロードする。コレを実行するとランクも再設定される");
                sender.sendMessage("[rankpoint] /rankpad help  このヘルプを表示する");
            } else {
                sender.sendMessage("[rankpoint] コマンド間違ってます");
            }

        } else {
            sender.sendMessage("[rankpoint] 権限ないっす");
        }
        saveConfig();
        return true;
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        saveConfig();
        getLogger().info("プラグインが終了しました");
    }
}
