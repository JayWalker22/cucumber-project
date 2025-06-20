package utilities;


import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import static utilities.Driver.getDriver;

public class ReusableMethods {
    //Click Method


    public static void moveToElementWithAction(WebElement element){
        Actions action = new Actions(Driver.getDriver());

        try {
            action.moveToElement(element).perform();
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    //HARD WAIT METHOD
    public static void bekle(int saniye) {
        try {
            Thread.sleep(saniye * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    //Alert ACCEPT
    public static void alertAccept() {
        Driver.getDriver().switchTo().alert().accept();
    }

    //Alert DISMISS
    public static void alertDismiss() {
        Driver.getDriver().switchTo().alert().dismiss();
    }

    //Alert getText()
    public static void alertText() {
        Driver.getDriver().switchTo().alert().getText();
    }

    //Alert promptBox
    public static void alertprompt(String text) {
        Driver.getDriver().switchTo().alert().sendKeys(text);
    }

    //DropDown VisibleText
    /*
        Select select2 = new Select(gun);
        select2.selectByVisibleText("7");

        //ddmVisibleText(gun,"7"); --> Yukarıdaki kullanım yerine sadece method ile handle edebilirim
     */
    public static void ddmVisibleText(WebElement ddm, String secenek) {
        Select select = new Select(ddm);
        select.selectByVisibleText(secenek);
    }

    //DropDown Index
    public static void ddmIndex(WebElement ddm, int index) {
        Select select = new Select(ddm);
        select.selectByIndex(index);
    }

    //DropDown Value
    public static void ddmValue(WebElement ddm, String secenek) {
        Select select = new Select(ddm);
        select.selectByValue(secenek);
    }

    //SwitchToWindow1
    public static void switchToWindow(int sayi) {
        List<String> tumWindowHandles = new ArrayList<String>(Driver.getDriver().getWindowHandles());
        Driver.getDriver().switchTo().window(tumWindowHandles.get(sayi));
    }

    //SwitchToWindow2
    public static void window(int sayi) {
        Driver.getDriver().switchTo().window(Driver.getDriver().getWindowHandles().toArray()[sayi].toString());
    }
    //EXPLICIT WAIT METHODS

    //Visible Wait
    public static void visibleWait(WebElement element, int sayi) {
        ReusableMethods.bekle(30);
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(sayi));
        wait.until(ExpectedConditions.visibilityOf(element));

    }

    //VisibleElementLocator Wait
    public static WebElement visibleWait(By locator, int sayi) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(sayi));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));

    }

    //Alert Wait
    public static void alertWait(int sayi) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(sayi));
        wait.until(ExpectedConditions.alertIsPresent());

    }

    //Tüm Sayfa ScreenShot parametreli olan içine resmin nereye ait olduğu yazılır
    public static void tumSayfaResmi(String name) {
        String tarih = new SimpleDateFormat("_hh_mm_ss_ddMMyyyy").format(new Date());
        String dosyaYolu = "TestOutput/screenshot/screenshot" + tarih + name + ".png";
        TakesScreenshot ts = (TakesScreenshot) Driver.getDriver();
        try {
            FileUtils.copyFile(ts.getScreenshotAs(OutputType.FILE), new File(dosyaYolu));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void tumSayfaResmi() {
        String tarih = new SimpleDateFormat("_hh_mm_ss_ddMMyyyy").format(new Date());
        String dosyaYolu = "TestOutput/screenshot/screenshot" + tarih + ".png";
        TakesScreenshot ts = (TakesScreenshot) Driver.getDriver();
        try {
            FileUtils.copyFile(ts.getScreenshotAs(OutputType.FILE), new File(dosyaYolu));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //WebElement ScreenShot
    public static void webElementResmi(WebElement element) {
        String tarih = new SimpleDateFormat("_hh_mm_ss_ddMMyyyy").format(new Date());
        String dosyaYolu = "TestOutput/screenshot/webElementScreenshot" + tarih + ".png";

        try {
            FileUtils.copyFile(element.getScreenshotAs(OutputType.FILE), new File(dosyaYolu));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    //WebTable
    public static void printData(int satir, int sutun) {
        WebElement satirSutun = Driver.getDriver().findElement(By.xpath("(//tbody)[1]//tr[" + satir + "]//td[" + sutun + "]"));
        System.out.println(satirSutun.getText());
    }
    public static void waitForPageToLoad(long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(timeoutInSeconds));
        wait.until(driver -> ((JavascriptExecutor) driver)
                .executeScript("return document.readyState").equals("complete"));
    }



    //JS Scroll
    public static void scroll(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    //JS Sayfa Sonu Scroll
    public static void scrollEnd() {
        JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();
        js.executeScript("window.scrollTo(0,document.body.scrollHeight)");
    }

    //JS Sayfa Başı Scroll
    public static void scrollHome() {
        JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();
        js.executeScript("window.scrollTo(0,-document.body.scrollHeight)");
    }

    /**
     * bu metot ile javascript kullanarak bir elemente sendKey yapılır
     * @param element sendKey yapılacak elementin locate verilmeli
     * @param text elemente gönderilecek değer verilmeli
     */
    public static void sendKeysJS(WebElement element, String text) {
        JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();
        js.executeScript("arguments[0].value='" + text + "'", element);

    }

    /**
     * bu metot ile bir elementin value'suna deger atanir.
     * @param element deger atanacak elementin locate verilmeli
     * @param text elemente gönderilecek value verilmeli
     */
    public static void sendAttributeJS(WebElement element, String text) {
        JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();
        js.executeScript("arguments[0].setAttribute('value','" + text + "')", element);
    }

    /** bu metot ile girilen attribute degerleri ile texti alabilirim
     @param id girilmesi gereken id degeri
     @param attributeName gonderilmesi gereken attribute ismi
     */
    public static void getValueByJavaScript(String id, String attributeName) {
        JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();
        String string = js.executeScript("return document.getElementById('" + id + "')." + attributeName).toString();
        System.out.println(string);
    }

    /**
     * elemente JavascriptExecutor ile string gonderir(java sendkey() ile ayni)
     *
     * @param string     webElemente sendKey ile gonderilecek text
     * @param webElement sendKey ile gonderilecek webelement
     */
    public static void sendKeyWithJavaScript(String string, WebElement webElement) {
        JavascriptExecutor jse = (JavascriptExecutor) Driver.getDriver();//Casting
        jse.executeScript("arguments[0].value = '" + string + "';", webElement);

    }

    /**
     * javascript ile webelemente sendkey yapma methodu.
     *
     * @param webelementXpathYolu webelement yolu string olarak xpath seklinde girilir
     * @param gonderilecekText    sendkey yapilacak text
     */
    public static void sendKeyWithJavaScriptWithXpath(String webelementXpathYolu, String gonderilecekText) {
        WebElement element = Driver.getDriver().findElement(By.xpath(webelementXpathYolu));
        sendKeyWithJavaScript(gonderilecekText, element);
    }

    /**
     * javascript ile webelemente sendkey yapma methodu.
     *
     * @param webelementCssYolu webelement yolu string olarak Css seklinde girilir
     * @param gonderilecekText  sendkey yapilacak text
     */
    public static void sendKeyWithJavaScriptWithCss(String webelementCssYolu, String gonderilecekText) {
        WebElement element = Driver.getDriver().findElement(By.cssSelector(webelementCssYolu));
        sendKeyWithJavaScript(gonderilecekText, element);
    }


    /**
     * bu metot ile herhangi bir webelemente xpath vererek JavascriptExecutor kullanarak tiklayabilirim
     *
     * @param string elementin xpath cinsinden yolu
     */
    public static void clickByJavaScriptWithXpath(String string) {
        WebElement element = Driver.getDriver().findElement(By.xpath(string));
        JavascriptExecutor jse = (JavascriptExecutor) Driver.getDriver();

        jse.executeScript("arguments[0].click();", element);

    }


    /**
     * bu metot ile herhangi bir webelemente JavascriptExecutor kullanarak tiklayabilirim
     *
     * @param webElement click yapilacak webelement
     */
    public static void clickByJavaScript(WebElement webElement) {
        JavascriptExecutor jse = (JavascriptExecutor) Driver.getDriver();

        jse.executeScript("arguments[0].click();", webElement);

    }


    /**
     * bu metot ile herhangi bir webelemente cssSelector vererek JavascriptExecutor kullanarak tiklayabilirim
     *
     * @param string elementin xpath cinsinden yolu
     */
    public static void clickByJavaScriptWithCss(String string) {
        WebElement element = Driver.getDriver().findElement(By.cssSelector(string));
        JavascriptExecutor jse = (JavascriptExecutor) Driver.getDriver();

        jse.executeScript("arguments[0].click();", element);

    }

    /**
     * Bu metot ile elementin xpath değeri string olarak verilerek o classtaki text alinir.
     *
     * @param className text degeri alinmak istenen class ismi string olarak verilir
     * @return
     */
    public static String getTextWithJavaScript(String className) {
        WebElement element = Driver.getDriver().findElement(By.className(className));

        // JavaScriptExecutor kullanarak elementin içeriğini al
        JavascriptExecutor jsExecutor = (JavascriptExecutor) Driver.getDriver();
        String text = (String) jsExecutor.executeScript("return arguments[0].textContent;", element);
        return text;
    }

    /**
     * Bu metot ile elementin className değeri string olarak verilerek o classtaki text alinir.
     *
     * @param  xpath text degeri alinmak istenen elementin xpathi string olarak verilir
     * @return
     */
    public static String getTextWithJavaScriptXpath(String xpath) {
        WebElement element = Driver.getDriver().findElement(By.xpath(xpath));

        // JavaScriptExecutor kullanarak elementin içeriğini al
        JavascriptExecutor jsExecutor = (JavascriptExecutor) Driver.getDriver();
        String text = (String) jsExecutor.executeScript("return arguments[0].textContent;", element);
        return text;
    }

    /**
     * Bu metot string olarak verilen textteki rakamlar haric herseyi siler ve Integer'a donusturur.
     *
     * @param string icindeki harf, karakter ve bosluklar silinecek text
     * @return Integer dondurur
     */
    public static Integer stringConverToInteger(String string) {
        Integer integer = Integer.valueOf(string.replaceAll("[^0-9]", ""));
        return integer;
    }

    /**
     * sayfayi verilen pixel kadar asagi kaydirir
     *
     * @param scrollSize pixel degeridir. integer olarak yazilmali
     */
    public static void scroll(int scrollSize) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) Driver.getDriver();

        // Örneğin, sayfayı 500 piksel aşağı kaydırmak için:
        jsExecutor.executeScript("window.scrollBy(0, " + scrollSize + ");");
    }

    /**
     * * Bu metot sayfadaki expected ve actual urun sayilari birbirine esit olana kadar sayfayi scroll yapar.
     * * Esit degilse sayfa altindaki sayfa numaralarina basarak son sayfaya ve son urune kadar ilerler.
     * sonunda da toplam urun sayisini dondurur
     *
     * @param first  sayfada goruntulenen urun sayisini int olarak verilmeli
     * @param second buraya baslangic degeri olarak 0 verilmeli
     * @return bu metot toplam urun sayisini verir
     */
    public static long ifNotEqualGoScroll(int first, int second) {
        Long urunSayisiniEkle = 0L;
        do {
            JavascriptExecutor jsExecutor = (JavascriptExecutor) Driver.getDriver();
            long elementCountJavascript = (long) jsExecutor.executeScript(
                    "return document.querySelectorAll('.ProductItem__Wrapper').length;"
            );

            long elementCount = elementCountJavascript;

            if (first != elementCount) {
                urunSayisiniEkle = urunSayisiniEkle + elementCount;
                WebElement element = webelementJavaScript("document.querySelector(\"a[title='Sonraki Sayfa']\")");

                if (first != urunSayisiniEkle) {
                    webElementResmi(element);
                    clickByJavaScript(element);
                }
            }
        } while (first != urunSayisiniEkle);
        return urunSayisiniEkle;
    }

    /**
     * JavaScript ile webelement olusturma
     *
     * @param javascriptYolu internet sitesinden sag klik ile JS yolunu kopyala ile alınan metin olacak
     */
    public static WebElement webelementJavaScript(String javascriptYolu) {
        JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();
        WebElement webElement = (WebElement) js.executeScript("return " + javascriptYolu + "");
        return webElement;
    }

    /**
     * İşlem yapılacak olan webelementin arkaplanını renklendirir
     * @param element etrafi cizilecek element
     * @param driver driver
     */
    public static void flash(WebElement element,WebDriver driver){
        JavascriptExecutor js= (JavascriptExecutor) driver;
        String elementColor=element.getCssValue("backgroundColor"); //locate alinan yerin  arka plan rengini alir
        for (int i = 0; i < 10; i++) {
            changeColor("rgb(0,0,0)", element, driver); //elemente siyah renk verir rgb kizmi rengi belirtir
            //changeColor("rgb(255,0,0)", element, driver); //kirmizi renk
            //changeColor("rgb(0,255,0)", element, driver); //yesil renk
            changeColor(elementColor, element, driver);
        }
    }

    /**
     * flash metoduna renk degistirme islemini yaptirir. Elementin arka plan renginin parametre olarak atanacagini bildirir.
     * @param color arka plan rengi
     * @param element   arka plan rengi degisecek element
     * @param driver
     */
    public static void changeColor(String color, WebElement element, WebDriver driver){
        JavascriptExecutor js= (JavascriptExecutor) driver; //javascript kodlarini calistirir
        js.executeScript("arguments[0].style.backgroundColor='"+color+"'", element); //elementin renginin degismesini sağlar

        try{
            Thread.sleep(20);
        }catch (Exception e){

        }
    }
    public static void robotClassDosyaYukleme(String filePath){
        try{
            ReusableMethods.bekle(3);
            StringSelection stringSelection = new StringSelection(filePath);
            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection,null);
            Robot robot = new Robot();
            //pressing ctrl+v
            robot.keyPress(KeyEvent.VK_CONTROL);
            ReusableMethods.bekle(3);
            robot.keyPress(KeyEvent.VK_V);
            ReusableMethods.bekle(3);
            //releasing ctrl+v
            robot.keyRelease(KeyEvent.VK_CONTROL);
            ReusableMethods.bekle(3);
            robot.keyRelease(KeyEvent.VK_V);
            ReusableMethods.bekle(3);
            System.out.println("PASSED");
            //pressing enter
            ReusableMethods.bekle(3);
            robot.keyPress(KeyEvent.VK_ENTER);
            ReusableMethods.bekle(3);
            //releasing enter
            robot.keyRelease(KeyEvent.VK_ENTER);
            ReusableMethods.bekle(3);
            System.out.println("ENTER");
        }catch (Exception e){
        }
    }

    /** Bu metot islem yapilacak elementin etrafina renkli cerceve cizerek belirgin hale getirir.
     *
     * @param locate islem yapilacak elementin cssSelector turunden locate string olarak girilmeli
     */
    public static void showElementWithFrame(String locate){
        WebElement element = Driver.getDriver().findElement(By.cssSelector(""+locate+""));
        String script = "arguments[0].style.border='3px solid red';";
//        String script = "arguments[0].style.border='3px solid white';";
//        String script = "arguments[0].style.border='3px solid yellow';";
//        String script = "arguments[0].style.border='3px solid green';";
        ((JavascriptExecutor) Driver.getDriver()).executeScript(script, element);

    }


    /**
     * bu metot ile JS yolu string olarak verilen elementi JavascriptExecutor kullanarak tiklayabilirim
     */
    public static void clickJSElementWithJavaScript(String javascriptYolu) {
        JavascriptExecutor jse = (JavascriptExecutor) Driver.getDriver();
        WebElement webElement = (WebElement) jse.executeScript("return " + javascriptYolu + "");
        jse.executeScript("arguments[0].click();", webElement);

    }
    /**
     * Performs double click action on an element
     * @param element
     */
    public static void doubleClick(WebElement element) {
        new Actions(Driver.getDriver()).doubleClick(element).build().perform();
    }

    /**
     * click yapilacak element clickible olana kadar bekler
     * @param element elementin locati
     * @param timeout beklenecek sure
     * @return
     */
    public static WebElement waitForClickablility(WebElement element, int timeout) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(timeout));
        return wait.until(ExpectedConditions.elementToBeClickable(element));

    }
}
