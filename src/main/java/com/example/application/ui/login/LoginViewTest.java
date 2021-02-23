package com.example.application.ui.login;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * This class contains tests for the login
 *
 * @author  Rebecca Schirmacher
 * @version 1.0
 * @since   26.01.2021
 * @lastUpdated 22.02.2021
 */

class LoginViewTest {


    @org.junit.jupiter.api.Test
    public void validLogin() {
        System.setProperty("webdriver.gecko.driver", "C:\\Users\\Uni\\Desktop\\Projekt\\Tests\\Programs\\Geckodriver\\geckodriver-v0.29.0-win64\\geckodriver.exe");
        WebDriver driver = new FirefoxDriver();
        driver.get("http://localhost:8080/");
        driver.findElement(By.cssSelector("#vaadinLoginUsername > input")).click();
        driver.findElement(By.cssSelector("#vaadinLoginUsername > input")).sendKeys("user");
        driver.findElement(By.cssSelector("#vaadinLoginPassword > input")).click();
        driver.findElement(By.cssSelector("#vaadinLoginPassword > input")).sendKeys("user");
        driver.findElement(By.cssSelector("#vaadinLoginPassword > input")).sendKeys(Keys.ENTER);
    }

    @org.junit.jupiter.api.Test
    public void invalidLogin() {
        System.setProperty("webdriver.gecko.driver", "C:\\Users\\Uni\\Desktop\\Projekt\\Tests\\Programs\\Geckodriver\\geckodriver-v0.29.0-win64\\geckodriver.exe");
        WebDriver driver = new FirefoxDriver();
        driver.get("http://localhost:8080/");
        driver.findElement(By.cssSelector("#vaadinLoginUsername > input")).click();
        driver.findElement(By.cssSelector("#vaadinLoginUsername > input")).sendKeys("wronguser");
        driver.findElement(By.cssSelector("#vaadinLoginPassword > input")).click();
        driver.findElement(By.cssSelector("#vaadinLoginPassword > input")).sendKeys("wrongpassword");
        driver.findElement(By.cssSelector("#vaadinLoginPassword > input")).sendKeys(Keys.ENTER);
    }

    @org.junit.jupiter.api.Test
    public void noLoginData() {
        System.setProperty("webdriver.gecko.driver", "C:\\Users\\Uni\\Desktop\\Projekt\\Tests\\Programs\\Geckodriver\\geckodriver-v0.29.0-win64\\geckodriver.exe");
        WebDriver driver = new FirefoxDriver();
        driver.get("http://localhost:8080/");
        driver.findElement(By.cssSelector("#vaadinLoginUsername > input")).click();
        driver.findElement(By.cssSelector("#vaadinLoginUsername > input")).sendKeys("");
        driver.findElement(By.cssSelector("#vaadinLoginPassword > input")).click();
        driver.findElement(By.cssSelector("#vaadinLoginPassword > input")).sendKeys("");
        driver.findElement(By.cssSelector("#vaadinLoginPassword > input")).sendKeys(Keys.ENTER);
    }

    @org.junit.jupiter.api.Test
    public void sqlLogin() {
        System.setProperty("webdriver.gecko.driver", "C:\\Users\\Uni\\Desktop\\Projekt\\Tests\\Programs\\Geckodriver\\geckodriver-v0.29.0-win64\\geckodriver.exe");
        WebDriver driver = new FirefoxDriver();
        driver.get("http://localhost:8080/");
        driver.findElement(By.cssSelector("#vaadinLoginUsername > input")).click();
        driver.findElement(By.cssSelector("#vaadinLoginUsername > input")).sendKeys("user");
        driver.findElement(By.cssSelector("#vaadinLoginPassword > input")).click();
        driver.findElement(By.cssSelector("#vaadinLoginPassword > input")).sendKeys("DROP DATABASE local_intranet_database;");
        driver.findElement(By.cssSelector("#vaadinLoginPassword > input")).sendKeys(Keys.ENTER);
    }

    @org.junit.jupiter.api.Test
    public void Logout() throws InterruptedException {
        System.setProperty("webdriver.gecko.driver", "C:\\Users\\Uni\\Desktop\\Projekt\\Tests\\Programs\\Geckodriver\\geckodriver-v0.29.0-win64\\geckodriver.exe");
        WebDriver driver = new FirefoxDriver();
        driver.get("http://localhost:8080/");
        driver.findElement(By.cssSelector("#vaadinLoginUsername > input")).click();
        driver.findElement(By.cssSelector("#vaadinLoginUsername > input")).sendKeys("user");
        driver.findElement(By.cssSelector("#vaadinLoginPassword > input")).click();
        driver.findElement(By.cssSelector("#vaadinLoginPassword > input")).sendKeys("user");
        driver.findElement(By.cssSelector("#vaadinLoginPassword > input")).sendKeys(Keys.ENTER);

        Thread.sleep(2000);

        driver.findElement(By.id("logout")).click();
    }

    // login tests for other users
    @org.junit.jupiter.api.Test
    public void validLoginOtherUser() throws InterruptedException {
        System.setProperty("webdriver.gecko.driver", "C:\\Users\\Uni\\Desktop\\Projekt\\Tests\\Programs\\Geckodriver\\geckodriver-v0.29.0-win64\\geckodriver.exe");
        WebDriver driver = new FirefoxDriver();
        driver.get("http://localhost:8080/");

        // tmueller
        driver.findElement(By.cssSelector("#vaadinLoginUsername > input")).click();
        driver.findElement(By.cssSelector("#vaadinLoginUsername > input")).sendKeys("tmueller");
        driver.findElement(By.cssSelector("#vaadinLoginPassword > input")).click();
        driver.findElement(By.cssSelector("#vaadinLoginPassword > input")).sendKeys("1988KillMe");
        driver.findElement(By.cssSelector("#vaadinLoginPassword > input")).sendKeys(Keys.ENTER);

        Thread.sleep(5000);

        driver.findElement(By.id("logout")).click();

        Thread.sleep(5000);

        // cbishopf
        driver.findElement(By.cssSelector("#vaadinLoginUsername > input")).click();
        driver.findElement(By.cssSelector("#vaadinLoginUsername > input")).sendKeys("cbishopf");
        driver.findElement(By.cssSelector("#vaadinLoginPassword > input")).click();
        driver.findElement(By.cssSelector("#vaadinLoginPassword > input")).sendKeys("PeterLust33");
        driver.findElement(By.cssSelector("#vaadinLoginPassword > input")).sendKeys(Keys.ENTER);

        Thread.sleep(5000);

        driver.findElement(By.id("logout")).click();

        Thread.sleep(5000);

        // rschmidt
        driver.findElement(By.cssSelector("#vaadinLoginUsername > input")).click();
        driver.findElement(By.cssSelector("#vaadinLoginUsername > input")).sendKeys("rschmidt");
        driver.findElement(By.cssSelector("#vaadinLoginPassword > input")).click();
        driver.findElement(By.cssSelector("#vaadinLoginPassword > input")).sendKeys("Uil55689");
        driver.findElement(By.cssSelector("#vaadinLoginPassword > input")).sendKeys(Keys.ENTER);

        Thread.sleep(5000);

        driver.findElement(By.id("logout")).click();

        Thread.sleep(5000);

        // kantine
        driver.findElement(By.cssSelector("#vaadinLoginUsername > input")).click();
        driver.findElement(By.cssSelector("#vaadinLoginUsername > input")).sendKeys("kantine");
        driver.findElement(By.cssSelector("#vaadinLoginPassword > input")).click();
        driver.findElement(By.cssSelector("#vaadinLoginPassword > input")).sendKeys("kantine");
        driver.findElement(By.cssSelector("#vaadinLoginPassword > input")).sendKeys(Keys.ENTER);

        Thread.sleep(5000);

        driver.findElement(By.id("logout")).click();

        Thread.sleep(5000);

        // user
        driver.findElement(By.cssSelector("#vaadinLoginUsername > input")).click();
        driver.findElement(By.cssSelector("#vaadinLoginUsername > input")).sendKeys("user");
        driver.findElement(By.cssSelector("#vaadinLoginPassword > input")).click();
        driver.findElement(By.cssSelector("#vaadinLoginPassword > input")).sendKeys("user");
        driver.findElement(By.cssSelector("#vaadinLoginPassword > input")).sendKeys(Keys.ENTER);

        Thread.sleep(5000);

        driver.findElement(By.id("logout")).click();

        Thread.sleep(5000);


    }


}