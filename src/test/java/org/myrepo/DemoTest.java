package org.myrepo;

import org.openqa.selenium.WebDriver;
import org.test.PageObjects.TravelHomePage;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class DemoTest extends BaseTest {

    WebDriver driver;
    TravelHomePage homePage;

    @BeforeTest
    public void setUp()
    {
        driver= initializeDriver();
        homePage = new TravelHomePage(driver);

    }

    @Test(dataProvider = "getData")
    public void flightTest(HashMap<String, String> reservationDetails) throws InterruptedException {

        homePage.goTo();
        System.out.println(homePage.getFooterNav().getFlightAttribute());
        System.out.println(homePage.getNavigationBar().getFlightAttribute());
        System.out.println(homePage.getFooterNav().getLinkCount());
        System.out.println(homePage.getNavigationBar().getLinkCount());
        homePage.setBookingStrategy("multiTrip");
        homePage.checkAvail(reservationDetails);
        homePage.setBookingStrategy("roundTrip");
        homePage.checkAvail(reservationDetails);



    }

    @AfterTest
    public void tearDown()
    {

        driver.quit();
    }

    @DataProvider
    public Object[][] getData() throws IOException {
        /* HashMap<String, String> reservationDetails = new HashMap<String, String>();
        reservationDetails.put("origin", "MAA");
        reservationDetails.put("destination", "HYD");
        reservationDetails.put("destination2", "BLR");

        HashMap<String, String> reservationDetails1 = new HashMap<String, String>();
        reservationDetails1.put("origin", "DEL");
        reservationDetails1.put("destination", "HYD");
        reservationDetails1.put("destination2", "MAA");

        List<HashMap<String, String>> l= new ArrayList<>();
        l.add(reservationDetails);
        l.add(reservationDetails1); */

        List<HashMap<String, String>> l= getJsonData(System.getProperty("user.dir")+"//src//test//java//org//myrepo//DataLoads//reservationDetails.json");

        return new Object[][]
                {
                        {l.get(0)}, {l.get(1)}
                };
    }


}
