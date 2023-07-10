package me.liycxc.steps;

import me.liycxc.Main;
import me.liycxc.driver.Driver;
import me.liycxc.driver.Waiter;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

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

        // 获取WebDriverWait
        WebDriverWait driverWait = Waiter.getWaiter(driver);

        try {
            // 打开网页
            driver.navigate().to("https://account.microsoft.com/services/pcgamepass/cancel?fref=billing-cancel");

            // 等待网页加载
            Thread.sleep(3000);

            // 查找 Cancel subscription
            WebElement element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@aria-label='Cancel subscription' and @data-bi-id='benefit-cancel' and @id='benefit-cancel']")));

            // 点击 Cancel subscription
            element.click();

            Thread.sleep(500);

            // 查找 Cancel now and get refund
            element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@aria-label='Cancel now and get refund']")));

            // 点击 Cancel now and get refund
            element.click();

            Thread.sleep(800);

            // 查找 Cancel subscription (xbox)
            element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@data-bi-id='xbox-cancel-select-cancel' and @id='cancel-select-cancel']")));

            // 点击 Cancel subscription (xbox)
            element.click();

            // 等待页面刷新
            Thread.sleep(3000);

            // 设置标识符
            element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h1[text()='Goodbye for now!']")));
            System.out.println(element.getText());

            Thread.sleep(1000);

            // 打开网页
            driver.navigate().to("https://account.microsoft.com/billing/payments?fref=home.drawers.payment-options.manage-payment");

            // 等待网页加载
            try {
                WebElement check = new WebDriverWait(driver, Duration.ofSeconds(5), Duration.ofMillis(500)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[text()='Enter password']")));
                System.out.println("2FA: " + check.isDisplayed());

                element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@name='passwd']")));

                Thread.sleep(500);

                element.sendKeys(Main.LIVE_PWD);

                Thread.sleep(200);

                element.sendKeys(Keys.ENTER);

                // 等待网页加载
                Thread.sleep(1000);
            } catch (Exception exception) {
                System.out.println("No 2FA");
            }

            // 查找 Remove Alipay
            element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@aria-label='Remove Alipay']")));

            // 点击 Remove Alipay
            element.click();

            Thread.sleep(800);

            // 查找 Remove (侧边栏)
            element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[text()='Remove']")));

            Thread.sleep(800);

            // 点击 Remove
            element.click();

            // 等待网页加载
            Thread.sleep(3000);

            // 设置标识符
            element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[text()='Alipay account has been removed from your account!']")));
            System.out.println("Ok? " + element.isDisplayed());

            return true;
        } catch (Exception exception) {
            exception.printStackTrace();
            return false;
        }
    }
}
