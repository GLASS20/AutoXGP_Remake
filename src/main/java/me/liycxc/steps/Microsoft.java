package me.liycxc.steps;

import me.liycxc.Main;
import me.liycxc.driver.Driver;
import me.liycxc.driver.Waiter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * This file is part of AutoXGP Remake project.
 * Copyright 2023 Liycxc
 * All Rights Reserved.
 *
 * @author Liycxc
 * @date: 2023-07-09
 * @time: 13:13
 */
public class Microsoft {
    public static boolean microsoftLogin() {
        try {
            // 获取浏览器
            WebDriver driver = Driver.getDriver();

            // 打开网页
            driver.get(Main.LIVE_URL);

            // 获取WebDriverWait
            WebDriverWait driverWait = Waiter.getWaiter(driver);

            // 查找email输入框
            WebElement element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@type='email']")));

            // 输入email
            element.sendKeys(Main.LIVE_USER);

            Thread.sleep(1000);

            // 寻找Next按钮
            element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@type='submit']")));

            // 点击Next
            element.click();

            Thread.sleep(1000);

            // 寻找password输入框
            element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@type='password']")));

            // 输入password
            element.sendKeys(Main.LIVE_PWD);

            Thread.sleep(1000);

            Actions actions = new Actions(driver);

            // 寻找Sign in按钮
            element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@type='submit' and @id='idSIButton9']")));
            actions.click(element).perform();

            Thread.sleep(1000);

            // 进入 Your Microsoft account brings everything together 页面
            // 寻找Continue按钮

            try {
                element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@type='button']")));

                // 点击Continue
                actions.click(element).perform();
            } catch (Exception exception) {
                exception.printStackTrace();
            }


            // 进入 Stay signed in? 页面
            // 寻找Yes按钮
            element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@type='submit']")));

            // 点击Yes
            actions.click(element).perform();

            // 等待页面加载
            Thread.sleep(2500);
            return true;
        } catch (Exception exception) {
            exception.printStackTrace();
            return false;
        }
    }

    public static boolean backMoney() {
        // 获取浏览器
        WebDriver driver = Driver.getDriver();

        try {
            driver.get("https://account.microsoft.com/services/pcgamepass/cancel?fref=billing-cancel");
            driver.findElement(By.xpath("//button[@id='benefit-cancel']")).click();

            driver.findElement(By.xpath("//input[@aria-label='Cancel now and get refund']")).click();
            driver.findElement(By.xpath("//button[@id='cancel-select-cancel']")).click();
            driver.findElement(By.xpath("//button[@data-bi-id='confirm-resubscribe']"));
            System.out.println("退订成功");

            while (true) {
                try {
                    driver.get("https://account.microsoft.com/billing/payments");
                    driver.findElement(By.xpath("//button[@aria-label='Remove Alipay']")).click();
                    driver.findElement(By.xpath("//span[text()='Remove']/..")).click();

                    driver.findElement(By.xpath("//span[text()='Alipay account has been removed from your account!']"));
                    System.out.println("支付宝付款方式已移除");
                    break;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            return true;
        } catch (Exception exception) {
            exception.printStackTrace();
            return false;
        }
    }
}
