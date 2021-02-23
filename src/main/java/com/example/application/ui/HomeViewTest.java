package com.example.application.ui;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * This class contains tests for the sidebar
 *
 * @author  Rebecca Schirmacher
 * @version 1.0
 * @since   26.01.2021
 * @lastUpdated 22.02.2021
 */

class HomeViewTest {


    @Test
    public void login(){
        System.setProperty("webdriver.gecko.driver", "C:\\Users\\Uni\\Desktop\\Projekt\\Tests\\Programs\\Geckodriver\\geckodriver-v0.29.0-win64\\geckodriver.exe");
        WebDriver driver = new FirefoxDriver();
        driver.get("http://localhost:8080/home");
        driver.findElement(By.cssSelector("#vaadinLoginUsername > input")).click();
        driver.findElement(By.cssSelector("#vaadinLoginUsername > input")).sendKeys("user");
        driver.findElement(By.cssSelector("#vaadinLoginPassword > input")).click();
        driver.findElement(By.cssSelector("#vaadinLoginPassword > input")).sendKeys("user");
        driver.findElement(By.cssSelector("#vaadinLoginPassword > input")).sendKeys(Keys.ENTER);
    }

    @Test
    public void sidebarLinkMyProfile() throws InterruptedException {

        System.setProperty("webdriver.gecko.driver", "C:\\Users\\Uni\\Desktop\\Projekt\\Tests\\Programs\\Geckodriver\\geckodriver-v0.29.0-win64\\geckodriver.exe");
        WebDriver driver = new FirefoxDriver();
        driver.get("http://localhost:8080/home");

        driver.findElement(By.cssSelector("#vaadinLoginUsername > input")).click();
        driver.findElement(By.cssSelector("#vaadinLoginUsername > input")).sendKeys("user");
        driver.findElement(By.cssSelector("#vaadinLoginPassword > input")).click();
        driver.findElement(By.cssSelector("#vaadinLoginPassword > input")).sendKeys("user");
        driver.findElement(By.cssSelector("#vaadinLoginPassword > input")).sendKeys(Keys.ENTER);

        Thread.sleep(2000);

        driver.findElement(By.linkText("Mein Profil")).click();

    }

    @Test
    public void sidebarLinkPhoneBook() throws InterruptedException {

        System.setProperty("webdriver.gecko.driver", "C:\\Users\\Uni\\Desktop\\Projekt\\Tests\\Programs\\Geckodriver\\geckodriver-v0.29.0-win64\\geckodriver.exe");
        WebDriver driver = new FirefoxDriver();
        driver.get("http://localhost:8080/home");

        driver.findElement(By.cssSelector("#vaadinLoginUsername > input")).click();
        driver.findElement(By.cssSelector("#vaadinLoginUsername > input")).sendKeys("user");
        driver.findElement(By.cssSelector("#vaadinLoginPassword > input")).click();
        driver.findElement(By.cssSelector("#vaadinLoginPassword > input")).sendKeys("user");
        driver.findElement(By.cssSelector("#vaadinLoginPassword > input")).sendKeys(Keys.ENTER);

        Thread.sleep(5000);

        driver.findElement(By.cssSelector("vaadin-horizontal-layout > label")).click();
        //Thread.sleep(3000);
        //driver.findElement(By.linkText("Internes Telefonbuch")).click();

    }

    @Test
    public void sidebarLinkSettings() throws InterruptedException {

        System.setProperty("webdriver.gecko.driver", "C:\\Users\\Uni\\Desktop\\Projekt\\Tests\\Programs\\Geckodriver\\geckodriver-v0.29.0-win64\\geckodriver.exe");
        WebDriver driver = new FirefoxDriver();
        driver.get("http://localhost:8080/home");

        driver.findElement(By.cssSelector("#vaadinLoginUsername > input")).click();
        driver.findElement(By.cssSelector("#vaadinLoginUsername > input")).sendKeys("user");
        driver.findElement(By.cssSelector("#vaadinLoginPassword > input")).click();
        driver.findElement(By.cssSelector("#vaadinLoginPassword > input")).sendKeys("user");
        driver.findElement(By.cssSelector("#vaadinLoginPassword > input")).sendKeys(Keys.ENTER);

        Thread.sleep(5000);

        driver.findElement(By.linkText("Einstellungen")).click();

    }

    @Test
    public void sidebarLinkHelp() throws InterruptedException {

        System.setProperty("webdriver.gecko.driver", "C:\\Users\\Uni\\Desktop\\Projekt\\Tests\\Programs\\Geckodriver\\geckodriver-v0.29.0-win64\\geckodriver.exe");
        WebDriver driver = new FirefoxDriver();
        driver.get("http://localhost:8080/home");

        driver.findElement(By.cssSelector("#vaadinLoginUsername > input")).click();
        driver.findElement(By.cssSelector("#vaadinLoginUsername > input")).sendKeys("user");
        driver.findElement(By.cssSelector("#vaadinLoginPassword > input")).click();
        driver.findElement(By.cssSelector("#vaadinLoginPassword > input")).sendKeys("user");
        driver.findElement(By.cssSelector("#vaadinLoginPassword > input")).sendKeys(Keys.ENTER);

        Thread.sleep(5000);

        driver.findElement(By.linkText("Hilfe")).click();

    }

    @Test
    public void sidebarLinkMyContacts() throws InterruptedException {

        System.setProperty("webdriver.gecko.driver", "C:\\Users\\Uni\\Desktop\\Projekt\\Tests\\Programs\\Geckodriver\\geckodriver-v0.29.0-win64\\geckodriver.exe");
        WebDriver driver = new FirefoxDriver();
        driver.get("http://localhost:8080/home");

        driver.findElement(By.cssSelector("#vaadinLoginUsername > input")).click();
        driver.findElement(By.cssSelector("#vaadinLoginUsername > input")).sendKeys("user");
        driver.findElement(By.cssSelector("#vaadinLoginPassword > input")).click();
        driver.findElement(By.cssSelector("#vaadinLoginPassword > input")).sendKeys("user");
        driver.findElement(By.cssSelector("#vaadinLoginPassword > input")).sendKeys(Keys.ENTER);

        Thread.sleep(5000);

        driver.findElement(By.linkText("Meine Kontakte")).click();

    }

    @Test
    public void sidebarLinkMailing() throws InterruptedException {

        System.setProperty("webdriver.gecko.driver", "C:\\Users\\Uni\\Desktop\\Projekt\\Tests\\Programs\\Geckodriver\\geckodriver-v0.29.0-win64\\geckodriver.exe");
        WebDriver driver = new FirefoxDriver();
        driver.get("http://localhost:8080/home");

        driver.findElement(By.cssSelector("#vaadinLoginUsername > input")).click();
        driver.findElement(By.cssSelector("#vaadinLoginUsername > input")).sendKeys("user");
        driver.findElement(By.cssSelector("#vaadinLoginPassword > input")).click();
        driver.findElement(By.cssSelector("#vaadinLoginPassword > input")).sendKeys("user");
        driver.findElement(By.cssSelector("#vaadinLoginPassword > input")).sendKeys(Keys.ENTER);

        Thread.sleep(5000);

        driver.findElement(By.linkText("Mailing")).click();

    }

    @Test
    public void sidebarLinkCalender() throws InterruptedException {

        System.setProperty("webdriver.gecko.driver", "C:\\Users\\Uni\\Desktop\\Projekt\\Tests\\Programs\\Geckodriver\\geckodriver-v0.29.0-win64\\geckodriver.exe");
        WebDriver driver = new FirefoxDriver();
        driver.get("http://localhost:8080/home");

        driver.findElement(By.cssSelector("#vaadinLoginUsername > input")).click();
        driver.findElement(By.cssSelector("#vaadinLoginUsername > input")).sendKeys("user");
        driver.findElement(By.cssSelector("#vaadinLoginPassword > input")).click();
        driver.findElement(By.cssSelector("#vaadinLoginPassword > input")).sendKeys("user");
        driver.findElement(By.cssSelector("#vaadinLoginPassword > input")).sendKeys(Keys.ENTER);

        Thread.sleep(5000);

        driver.findElement(By.linkText("Kalender")).click();

    }

    @Test
    public void sidebarLinkFahrplan() throws InterruptedException {

        System.setProperty("webdriver.gecko.driver", "C:\\Users\\Uni\\Desktop\\Projekt\\Tests\\Programs\\Geckodriver\\geckodriver-v0.29.0-win64\\geckodriver.exe");
        WebDriver driver = new FirefoxDriver();
        driver.get("http://localhost:8080/home");

        driver.findElement(By.cssSelector("#vaadinLoginUsername > input")).click();
        driver.findElement(By.cssSelector("#vaadinLoginUsername > input")).sendKeys("user");
        driver.findElement(By.cssSelector("#vaadinLoginPassword > input")).click();
        driver.findElement(By.cssSelector("#vaadinLoginPassword > input")).sendKeys("user");
        driver.findElement(By.cssSelector("#vaadinLoginPassword > input")).sendKeys(Keys.ENTER);

        Thread.sleep(5000);

        driver.findElement(By.linkText("Fahrplan")).click();

    }

    @Test
    public void sidebarLinkApps() throws InterruptedException {

        System.setProperty("webdriver.gecko.driver", "C:\\Users\\Uni\\Desktop\\Projekt\\Tests\\Programs\\Geckodriver\\geckodriver-v0.29.0-win64\\geckodriver.exe");
        WebDriver driver = new FirefoxDriver();
        driver.get("http://localhost:8080/home");

        driver.findElement(By.cssSelector("#vaadinLoginUsername > input")).click();
        driver.findElement(By.cssSelector("#vaadinLoginUsername > input")).sendKeys("user");
        driver.findElement(By.cssSelector("#vaadinLoginPassword > input")).click();
        driver.findElement(By.cssSelector("#vaadinLoginPassword > input")).sendKeys("user");
        driver.findElement(By.cssSelector("#vaadinLoginPassword > input")).sendKeys(Keys.ENTER);

        Thread.sleep(5000);

        driver.findElement(By.linkText("Apps")).click();

    }

    @Test
    public void sidebarLinkFavorites() throws InterruptedException {

        System.setProperty("webdriver.gecko.driver", "C:\\Users\\Uni\\Desktop\\Projekt\\Tests\\Programs\\Geckodriver\\geckodriver-v0.29.0-win64\\geckodriver.exe");
        WebDriver driver = new FirefoxDriver();
        driver.get("http://localhost:8080/home");

        driver.findElement(By.cssSelector("#vaadinLoginUsername > input")).click();
        driver.findElement(By.cssSelector("#vaadinLoginUsername > input")).sendKeys("user");
        driver.findElement(By.cssSelector("#vaadinLoginPassword > input")).click();
        driver.findElement(By.cssSelector("#vaadinLoginPassword > input")).sendKeys("user");
        driver.findElement(By.cssSelector("#vaadinLoginPassword > input")).sendKeys(Keys.ENTER);

        Thread.sleep(5000);

        driver.findElement(By.linkText("Favoriten")).click();

    }

    @Test
    public void sidebarLinkCanteen() throws InterruptedException {

        System.setProperty("webdriver.gecko.driver", "C:\\Users\\Uni\\Desktop\\Projekt\\Tests\\Programs\\Geckodriver\\geckodriver-v0.29.0-win64\\geckodriver.exe");
        WebDriver driver = new FirefoxDriver();
        driver.get("http://localhost:8080/home");

        driver.findElement(By.cssSelector("#vaadinLoginUsername > input")).click();
        driver.findElement(By.cssSelector("#vaadinLoginUsername > input")).sendKeys("user");
        driver.findElement(By.cssSelector("#vaadinLoginPassword > input")).click();
        driver.findElement(By.cssSelector("#vaadinLoginPassword > input")).sendKeys("user");
        driver.findElement(By.cssSelector("#vaadinLoginPassword > input")).sendKeys(Keys.ENTER);

        Thread.sleep(5000);

        driver.findElement(By.linkText("Betriebsrestaurant")).click();

    }

    @Test
    public void sidebarLinkLastVisited() throws InterruptedException {

        System.setProperty("webdriver.gecko.driver", "C:\\Users\\Uni\\Desktop\\Projekt\\Tests\\Programs\\Geckodriver\\geckodriver-v0.29.0-win64\\geckodriver.exe");
        WebDriver driver = new FirefoxDriver();
        driver.get("http://localhost:8080/home");

        driver.findElement(By.cssSelector("#vaadinLoginUsername > input")).click();
        driver.findElement(By.cssSelector("#vaadinLoginUsername > input")).sendKeys("user");
        driver.findElement(By.cssSelector("#vaadinLoginPassword > input")).click();
        driver.findElement(By.cssSelector("#vaadinLoginPassword > input")).sendKeys("user");
        driver.findElement(By.cssSelector("#vaadinLoginPassword > input")).sendKeys(Keys.ENTER);

        Thread.sleep(5000);

        driver.findElement(By.linkText("Zuletzt besucht")).click();

    }

    @Test
    public void sidebarLinkAdd() throws InterruptedException {

        System.setProperty("webdriver.gecko.driver", "C:\\Users\\Uni\\Desktop\\Projekt\\Tests\\Programs\\Geckodriver\\geckodriver-v0.29.0-win64\\geckodriver.exe");
        WebDriver driver = new FirefoxDriver();
        driver.get("http://localhost:8080/home");

        driver.findElement(By.cssSelector("#vaadinLoginUsername > input")).click();
        driver.findElement(By.cssSelector("#vaadinLoginUsername > input")).sendKeys("user");
        driver.findElement(By.cssSelector("#vaadinLoginPassword > input")).click();
        driver.findElement(By.cssSelector("#vaadinLoginPassword > input")).sendKeys("user");
        driver.findElement(By.cssSelector("#vaadinLoginPassword > input")).sendKeys(Keys.ENTER);

        Thread.sleep(5000);

        driver.findElement(By.linkText("Hinzufügen")).click();

    }

    @Test
    public void sidebarLinkMyTasks() throws InterruptedException {

        System.setProperty("webdriver.gecko.driver", "C:\\Users\\Uni\\Desktop\\Projekt\\Tests\\Programs\\Geckodriver\\geckodriver-v0.29.0-win64\\geckodriver.exe");
        WebDriver driver = new FirefoxDriver();
        driver.get("http://localhost:8080/home");

        driver.findElement(By.cssSelector("#vaadinLoginUsername > input")).click();
        driver.findElement(By.cssSelector("#vaadinLoginUsername > input")).sendKeys("user");
        driver.findElement(By.cssSelector("#vaadinLoginPassword > input")).click();
        driver.findElement(By.cssSelector("#vaadinLoginPassword > input")).sendKeys("user");
        driver.findElement(By.cssSelector("#vaadinLoginPassword > input")).sendKeys(Keys.ENTER);

        Thread.sleep(5000);

        driver.findElement(By.linkText("Meine Aufgaben")).click();

    }

}