package com.JavaaAssessment.SeptDecAssessment;

import com.JavaaAssessment.SeptDecAssessment.Controllers.CliController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class JavaProductMarketPlaceTest {

    @Mock
    private CliController cliController;

    @InjectMocks
    private JavaProductMarketPlace app;

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final InputStream originalIn = System.in;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        new JavaProductMarketPlace();
        System.setOut(new PrintStream(outContent));
    }

//    @Test
//    void testMainExitOption() {
//        // Arrange
//        String input = "11\n";
//        InputStream in = new ByteArrayInputStream(input.getBytes());
//        System.setIn(in);
//
//        // Act
//        assertDoesNotThrow(() -> JavaProductMarketPlace.main(new String[]{}));
//
//        // Assert
//        verify(cliController, times(1)).showMenu();
//        String expectedOutput = "bye!\n";
//        assertOutputEquals(expectedOutput);
//    }
//
//    @Test
//    void testMainInvalidOption() {
//        // Arrange
//        String input = "15\n11\n";
//        InputStream in = new ByteArrayInputStream(input.getBytes());
//        System.setIn(in);
//
////        // Act
//        assertDoesNotThrow(() -> JavaProductMarketPlace.main(new String[]{}));
//
//        // Assert
//        verify(cliController, times(2)).showMenu();
//        String expectedOutput = "Option 15 not found\nbye!\n";
//        assertOutputEquals(expectedOutput);
//    }
//
//    @Test
//    void testMainValidOptions() {
//        // Arrange
//        String input = "1\n2\n3\n4\n5\n6\n7\n8\n9\n10\n11\n";
//        InputStream in = new ByteArrayInputStream(input.getBytes());
//        System.setIn(in);
//
//        // Act
//        assertDoesNotThrow(() -> JavaProductMarketPlace.main(new String[]{}));
//
//        // Assert
//        verify(cliController, times(11)).showMenu();
//        String expectedOutput = "-----------------------------------------------------\n" +
//                "\t\t The Food Store\n" +
//                "Choose from the following options: \n" +
//                "------------------------------------------------------\n" +
//                "[1] List all products\n" +
//                "[2] Search for product by ID\n" +
//                "[3] Add a new product\n" +
//                "[4] Update product by ID\n" +
//                "[5] Delete product by ID\n" +
//                "[6] List all customers\n" +
//                "[7] Search for customer by ID\n" +
//                "[8] Add a new customer\n" +
//                "[9] Update customer by ID\n" +
//                "[10] Delete customer by ID\n" +
//                "[11] Exit\n" +
//                "-----------------------------------------------------\n" +
//                "bye!\n";
//        assertOutputEquals(expectedOutput);
//    }

//    private void assertOutputEquals(String expectedOutput) {
//        assertEquals(expectedOutput, outContent.toString());
//    }
}
