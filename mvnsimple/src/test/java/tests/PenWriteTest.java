package tests;

import org.junit.Assert;
import pen.Pen;
import org.junit.jupiter.api.Test;


public class PenWriteTest extends BaseTest {
    @Test
    public void testPenWriteWordSizeLessThankInkCounter(){
        Pen pen = new Pen();
        String expecterResult = "Hello";
        String actualResult = pen.write("Hello");
        Assert.assertEquals(String.format("Expected %s, actual result %s", expecterResult,actualResult)
                ,actualResult,expecterResult);
    }

}
