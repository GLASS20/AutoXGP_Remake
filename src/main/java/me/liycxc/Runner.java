package me.liycxc;

import me.liycxc.driver.Driver;
import me.liycxc.steps.Alipay;

/**
 * @author Liycxc
 */
public class Runner extends Thread {
    @Override
    public void run() {
        System.out.println("-------Thread Started-------");

        //  初始化浏览器
        Driver.initDriver();

        while (Main.running) {
            // 登录支付宝
            Alipay.alipayLogin();


        }
        System.out.println("-------Thread Stopped-------");
        System.exit(0);
    }
}
