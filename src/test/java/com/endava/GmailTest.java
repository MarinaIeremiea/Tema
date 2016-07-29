package com.endava;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

/**
 * Created by mieremiea on 7/29/2016.
 */
public class GmailTest {

    static WebDriver webDriver;

    @BeforeClass
    public static void setUp(){

        webDriver = new FirefoxDriver();
        webDriver.get("https://gmail.com");

        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        webDriver.manage().window().maximize();
    }

    @Test
    public void testGmail(){

        //get the email field
        WebElement emailField = webDriver.findElement(By.xpath("//input[@id='Email']"));
        emailField.sendKeys("endavaselenium");

        WebElement nextButton = webDriver.findElement(By.xpath("//input[@id='next']"));
        nextButton.click();

        WebElement passwordField = webDriver.findElement(By.xpath("//input[@id='Passwd']"));
        passwordField.sendKeys("endavaqa");

        WebElement signInButton = webDriver.findElement(By.xpath("//input[@id='signIn']"));
        signInButton.click();

        WebElement composeButton= webDriver.findElement(By.xpath("//div[text()='COMPOSE']"));
        composeButton.click();

        WebElement toField = webDriver.findElement(By.xpath("//textarea[@name ='to']"));
        toField.sendKeys("endavaselenium@gmail.com");
        toField.sendKeys(Keys.RETURN);

        WebElement subjectField= webDriver.findElement(By.xpath("//input[@name='subjectbox']"));
        subjectField.sendKeys("Test");

        WebElement messageBox = webDriver.findElement(By.xpath("//div[@aria-label='Message Body']"));
        messageBox.sendKeys("MesajTest");

        WebElement submitButton = webDriver.findElement(By.xpath("//div[@aria-label='Send ‪(Ctrl-Enter)‬']"));
        submitButton.click();

        WebDriverWait wait = new WebDriverWait(webDriver, 20);


        WebElement viewEmail = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(.,'View message')]")));
        viewEmail.click();


        WebElement subjectVerify = webDriver.findElement(By.xpath("//h2[contains(text(),'Test')]"));
        assertEquals("Test",subjectVerify.getText());


        WebElement bodyVerify = webDriver.findElement(By.xpath("//div[@role='listitem']//div[contains(text(),'MesajTest')]"));
        assertEquals("MesajTest",bodyVerify.getText());

    }


    @AfterClass
    public static void tearDown(){

    //    webDriver.close();

    }
}
