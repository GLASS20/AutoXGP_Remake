package me.liycxc.driver;

import me.liycxc.Main;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * This file is part of AutoXGP Remake project.
 * Copyright 2023 Liycxc
 * All Rights Reserved.
 *
 * @author Liycxc
 * @date: 2023-07-09
 * @time: 13:14
 */
public class Driver {
    private static FirefoxDriver driver;

    public static FirefoxDriver getDriver() {
        return driver;
    }

    public static void setDriver(FirefoxDriver newDriver) {
        driver = newDriver;
    }

    public static void initDriver() {
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        System.setProperty("webdriver.gecko.driver", "geckodriver.exe");

        FirefoxDriver driver;
        FirefoxOptions options = new FirefoxOptions();
        FirefoxProfile firefoxProfile = new FirefoxProfile(new File(Main.DRIVER_PROFILE));
        firefoxProfile.setPreference("general.useragent.override", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36 Edg/114.0.1823.58");
        // 绕过支付宝爬虫检测 FireFox 版本 < 88 推荐使用87
        firefoxProfile.setPreference("dom.webdriver.enabled", false);
        firefoxProfile.setPreference("useAutomationExtension", false);
        options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
        // 无头模式
        // options.addArguments("--headless");
        options.addArguments("--disable-gpu");
        options.addPreference("dom.webdriver.enabled", false);
        options.addPreference("useAutomationExtension", false);
        options.setProfile(firefoxProfile);
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("intl.accept_languages", "en-US"); // 设置为英语（美国）语言片
        options.setCapability("prefs", prefs);

        driver = new FirefoxDriver();
        driver.manage().deleteAllCookies();

        setDriver(driver);
    }
}
