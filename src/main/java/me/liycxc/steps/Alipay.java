package me.liycxc.steps;

import me.liycxc.Main;
import me.liycxc.driver.Driver;
import me.liycxc.driver.Waiter;
import org.openqa.selenium.By;
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
 * @time: 13:14
 */
public class Alipay {
    public static boolean alipayLogin() {
        // 获取浏览器
        WebDriver driver = Driver.getDriver();

        // 打开支付宝登录网页
        driver.get(Main.ALIPAY_URL);

        // 获取WebDriverWait
        WebDriverWait driverWait = Waiter.getWaiter(driver);

        // 寻找 账密登录
        WebElement element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//li[text()='账密登录']")));

        // 点击 账密登录
        element.click();

        // 设置登录次数
        int counter = 0;

        // 循环登录
        while (driver.getCurrentUrl().toLowerCase().startsWith("https://auth")) {

            // 如果超过限定登录次数则返回登录失败
            if (counter > 15) {
                return false;
            }

            try {
                // 寻找账号输入框
                element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='J-input-user']")));

                // 清空账号输入框
                element.clear();

                // 模拟账号输入
                for (char c : Main.ALIPAY_USER.toCharArray()) {
                    int delay = new Random().nextInt(300) + 50;
                    // 模拟输入延迟
                    Thread.sleep(delay);

                    // 输入字符
                    element.sendKeys(String.valueOf(c));
                }

                // 寻找密码输入框
                element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='password_rsainput']")));

                // 清空密码输入框
                element.clear();

                // 模拟密码输入
                for (char c : Main.ALIPAY_PWD.toCharArray()) {
                    int delay = new Random().nextInt(300) + 50;
                    // 模拟输入延迟
                    Thread.sleep(delay);

                    // 输入字符
                    element.sendKeys(String.valueOf(c));
                }

                // 寻找【登录】
                element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='J-login-btn']")));

                // 模拟点击延迟
                Thread.sleep(600);

                // 点击登录
                element.click();

                // 等待页面加载
                Thread.sleep(1200);
            } catch (Exception exception) {
                exception.printStackTrace();
            }

            // 重新加载页面
            driver.navigate().refresh();

            // 登录次数增加
            counter++;
        }

        // 登录完成
        return true;
    }
}
