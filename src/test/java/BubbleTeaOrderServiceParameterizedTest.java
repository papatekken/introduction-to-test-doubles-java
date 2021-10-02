import com.techreturners.bubbleteaordersystem.model.*;
import com.techreturners.bubbleteaordersystem.service.BubbleTeaMessenger;
import com.techreturners.bubbleteaordersystem.service.BubbleTeaOrderService;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import testhelper.DummySimpleLogger;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(Parameterized.class)
public class BubbleTeaOrderServiceParameterizedTest {

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                { BubbleTeaTypeEnum.LycheeIceTea },
                { BubbleTeaTypeEnum.PeachIceTea },
                { BubbleTeaTypeEnum.MatchaMilkTea},
                { BubbleTeaTypeEnum.OolongMilkTea},
                { BubbleTeaTypeEnum.JasmineMilkTea}
        });
    }

    private DebitCard testDebitCard;
    private PaymentDetails paymentDetails;
    private DummySimpleLogger dummySimpleLogger;
    private BubbleTeaMessenger mockMessenger;
    private BubbleTeaOrderService bubbleTeaOrderService;
    private BubbleTeaTypeEnum bubbleTeaTypeEnum;

    public  BubbleTeaOrderServiceParameterizedTest(BubbleTeaTypeEnum bubbleTeaTypeEnum){this.bubbleTeaTypeEnum =bubbleTeaTypeEnum;}

    @Before
    public void setup() {
        testDebitCard = new DebitCard("0123456789");
        paymentDetails = new PaymentDetails("hello kitty", "sanrio puroland", testDebitCard);
        dummySimpleLogger = new DummySimpleLogger();
        mockMessenger = mock(BubbleTeaMessenger.class);
        bubbleTeaOrderService = new BubbleTeaOrderService(dummySimpleLogger, mockMessenger);
    }

    @Test
    public void shouldCreateBubbleTeaOrderRequestWhenCreateOrderRequestIsCalled() {

        //Arrange
        BubbleTea bubbleTea = new BubbleTea(bubbleTeaTypeEnum, 4.5);
        BubbleTeaRequest bubbleTeaRequest = new BubbleTeaRequest(paymentDetails, bubbleTea);

        BubbleTeaOrderRequest expectedResult = new BubbleTeaOrderRequest(
                "hello kitty",
                "sanrio puroland",
                "0123456789",
                bubbleTeaTypeEnum
        );

        //Act
        BubbleTeaOrderRequest result = bubbleTeaOrderService.createOrderRequest(bubbleTeaRequest);

        //Assert
        assertEquals(expectedResult.getName(), result.getName());
        assertEquals(expectedResult.getAddress(), result.getAddress());
        assertEquals(expectedResult.getDebitCardDigits(), result.getDebitCardDigits());
        assertEquals(expectedResult.getBubbleTeaType(), result.getBubbleTeaType());

        //Verify Mock was called with the BubbleTeaOrderRequest result object
        verify(mockMessenger).sendBubbleTeaOrderRequestEmail(result);
        verify(mockMessenger, times(1)).sendBubbleTeaOrderRequestEmail(result);
    }


}