package me.liycxc;

import me.liycxc.driver.Driver;
import me.liycxc.steps.Alipay;
import me.liycxc.steps.Microsoft;
import me.liycxc.utils.Account;

/**
 * @author Liycxc
 */
public class Runner extends Thread {
    @Override
    public void run() {
        System.out.println("-------Thread Started-------");

        int exitCode = 0;

        //  初始化浏览器
        Driver.initDriver();

        while (Main.running) {
            try {
                // 登录支付宝
                if(!Alipay.alipayLogin()) {
                    throw new Exception("Alipay Error");
                }

                // 获取本地微软账户
                String[] account = Account.getLocAcc();
                if(account[0] == null || account[1] == null) {
                    account = Account.getMailByApi();
                }

                // 微软登录
                if (!Microsoft.microsoftLogin(account)) {
                    throw new Exception("Microsoft Error");
                }

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
