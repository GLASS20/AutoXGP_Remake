package me.liycxc;

import me.liycxc.utils.ConsoleListener;

import java.util.Scanner;

/**
 * This file is part of AutoXGP Remake project.
 * Copyright 2023 Liycxc
 * All Rights Reserved.
 *
 * @author Liycxc
 * @date: 2023-07-09
 * @time: 12:52
 */
public class Main {
    // Driver
    public static String DRIVER_PROFILE = null;
    // Alipay
    public static String ALIPAY_URL = "https://auth.alipay.com/login/index.htm";
    public static String ALIPAY_USER = null;
    public static String ALIPAY_PWD = null;
    public static String ALIPAY_KEY = null;

    // YX Get api
    public static String YX_ID = null;
    public static String YX_TOKEN = null;
    public static String YX_API = null;

    // Microsoft Live
    public static String LIVE_URL = "https://login.live.com";
    public static String LIVE_USER = null;
    public static String LIVE_PWD = null;

    // Xbox Game Pass
    public static String XGP_URL = "https://www.xbox.com/zh-HK/games/store/pc-game-pass-pc-1/CFQ7TTC0KGQ8/0002";

    // Minecraft
    public static String MC_PLAYERID = null;


    /** 程序启动函数
     * @param args 启动参数
     * @args 0 FireFox profile 文件
     * @args 1 支付宝用户名
     * @args 2 支付宝密码
     * @args 3 支付宝付款密钥
     * @args 4 邮箱提取ID
     * @args 5 邮箱提取密钥
     */
    public static void main(String[] args) throws InterruptedException {
        DRIVER_PROFILE = args[0];
        ALIPAY_USER = args[1];
        ALIPAY_PWD = args[2];
        ALIPAY_KEY = args[3];
        YX_ID = args[4]; // 69 or 70
        YX_TOKEN = args[5];
        YX_API = "https://api.yx1024.net/getAccountApi.aspx?uid=56264&type=" + YX_ID + "&token=" + YX_TOKEN + "&count=1";
        run();
    }

    // 运行池
    public static boolean running;
    public static void run() {
        // 注册运行函数
        Runner runner = new Runner();

        // 注册控制台触发器
        ConsoleListener.Action action = msg -> {
            if ("/start".equalsIgnoreCase(msg)) {
                running = true;
                runner.start();
            }
            if ("/stop".equalsIgnoreCase(msg)) {
                running = false;
            }
        };

        ConsoleListener cs = new ConsoleListener(new Scanner(System.in), action);
        cs.listenInNewThread();
    }
}

