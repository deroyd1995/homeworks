package tests;

import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.ValueSource;
import pen.Pen;

import static org.junit.Assert.assertTrue;


public class PenGetColorTest extends BaseTest {
    @Test
    public void PenGetColorBasicColorTest(){
        Pen pen = new Pen();
        String expectedResult = "BLUE";
        String actualResult = pen.getColor();
        Assert.assertEquals(String.format("Expected %s, actual result %s",expectedResult,actualResult),expectedResult,actualResult);
    }

    @Test
    public void PenGetColorTest(){
        Pen pen = new Pen("RED");
        String expectedResult = "RED";
        String actualResult = pen.getColor();
        Assert.assertEquals(String.format("Expected %s, actual result %s",expectedResult,actualResult),expectedResult,actualResult);

    }

    @Test
    @DisplayName("Отображаемое название тестового метода")
    void givePenTestInfo(TestInfo testInfo){
        System.out.println("displayName = "+testInfo.getDisplayName());
        assertTrue(true);
    }

    @ParameterizedTest
    @ValueSource (strings = {"RED","WHITE","BLUE"})
    public void TestPenGetColorRedColor(String expectedResult){
        Pen pen = new Pen(expectedResult);
        String actualResult = pen.getColor();
        Assert.assertEquals(String.format("Expected %s, actual result %s",expectedResult,actualResult),expectedResult,actualResult);
    }

    @ParameterizedTest
    @ArgumentsSource(PenGetColorArguments.class)
    public void TestPenGetColorColorS(String color, int inkCounter){
        Pen pen = new Pen(color, inkCounter);
        String actualResult = pen.getColor();
        Assert.assertEquals(String.format("Expected %s, actual result %s",color,actualResult),color,actualResult);
    }

    //Аннотация TimeOut
    //Аннотация Disabled
    //Аннотация Tags


}
