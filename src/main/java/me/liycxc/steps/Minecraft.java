package me.liycxc.steps;

import me.liycxc.Main;
import me.liycxc.driver.Driver;
import me.liycxc.driver.Waiter;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Random;

/**
 * This file is part of AutoXGP Remake project.
 * Copyright 2023 Liycxc
 * All Rights Reserved.
 *
 * @author Liycxc
 * @date: 2023-07-09
 * @time: 20:09
 */
public class Minecraft {
    public static boolean setArchive() {
        // 设置PlayerID
        Main.MC_PLAYERID = randomPlayerId();

        // 获取浏览器
        WebDriver driver = Driver.getDriver();

        // 获取WebDriverWait
        WebDriverWait driverWait = Waiter.getWaiter(driver);

        try {
            // 打开网页 请禁用你的梯子
            driver.navigate().to("https://www.minecraft.net/en-us/login");

            // 查找Microsoft登录
            WebElement element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@aria-label='Sign in with Microsoft account']")));

            // 点击登录
            element.click();

            Thread.sleep(500);

            // 查找我的档案
            element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@data-aem-contentname='Profile Name']")));

            // 点击我的档案
            element.click();

            // 等待网页刷新
            Thread.sleep(2000);

            do {
                // 查找档案名输入框
                element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@data-testid='profile-name-input']")));

                // 输入我的档案名
                element.sendKeys(Main.MC_PLAYERID);

                Thread.sleep(50);

                // 输入回车
                element.sendKeys(Keys.ENTER);

                // 等待网页刷新
                Thread.sleep(3000);

                try {
                    if (driver.getPageSource().contains("This profile name already exists") || driver.getPageSource().contains("You can't use language that some might find offensive")) {
                        Main.MC_PLAYERID = randomPlayerId();
                    }
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            } while (!driver.getPageSource().contains("You’re all set up - It’s time to dive in! "));

            return true;
        } catch (Exception exception) {
            exception.printStackTrace();
            return false;
        }
    }


    // 随机生成玩家ID
    private static String randomPlayerId() {
        String characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();

        StringBuilder randomString = new StringBuilder().append("Li");

        for (int i = 0; i < 7; i++) {
            int randomIndex = random.nextInt(characters.length());
            randomString.append(characters.charAt(randomIndex));
        }

        return randomString.toString();
    }
}
