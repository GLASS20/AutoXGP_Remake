package me.liycxc;

import me.liycxc.driver.Driver;
import me.liycxc.steps.Microsoft;
import me.liycxc.steps.Minecraft;
import me.liycxc.steps.XboxGamePass;
import me.liycxc.utils.Account;
import me.liycxc.utils.FileUtils;

/**
 * @author Liycxc
 */
public class Runner extends Thread {
    @Override
    public void run() {
        System.out.println("-------Thread Started-------");

        int exitCode = 0;

        // 初始化文件
        FileUtils.fileInit();

        while (Main.running) {
            try {
                // 初始化浏览器
                Driver.initDriver();

                // 登录支付宝
/*                System.out.println("Alipay Login Start");
                if(!Alipay.alipayLogin()) {
                    throw new Exception("Alipay Error");
                }
                System.out.println("Alipay Login Stopped");*/

                // 获取本地微软账户
                String[] account = Account.getLocAcc();
                if(account[0] == null || account[1] == null) {
                    account = Account.getMailByApi();
                }

                if (account != null) {
                    System.out.println("Email: " + account[0]);
                    System.out.println("Password: " + account[1]);
                    Main.LIVE_USER = account[0];
                    Main.LIVE_PWD = account[1];
                } else {
                    throw new Exception("Account is null");
                }

                // 微软登录
                System.out.println("Microsoft Login Start");
                if (!Microsoft.microsoftLogin()) {
                    throw new Exception("Microsoft Error");
                }
                System.out.println("Microsoft Login Stopped");

                // Xbox Game Pass 登记
                System.out.println("Xbox Game Pass Start");
                if (!XboxGamePass.xboxGamePass()) {
                    throw new Exception("Xbox Game Pass Error");
                }
                System.out.println("Xbox Game Pass Stopped");

                // Minecraft Archive 设置
                System.out.println("Minecraft Archive Start");
                if (!Minecraft.setArchive()) {
                    throw new Exception("Minecraft Archive Error");
                }
                System.out.println("Minecraft Archive Stopped");

                System.out.println("Back Money Start");
                if (!Microsoft.backMoney()) {
                    throw new Exception("我操我的钱 操你妈");
                }
                System.out.println("Back Money Stopped");

                // 保存账密
                FileUtils.fileSave(Main.LIVE_USER + ":" + Main.LIVE_PWD);

                Driver.getDriver().quit();
            } catch (Exception exception) {
                exception.printStackTrace();
                exitCode = 1;
                break;
            }
        }

        System.out.println("-------Thread Stopped-------");
        System.exit(exitCode);
    }
}
