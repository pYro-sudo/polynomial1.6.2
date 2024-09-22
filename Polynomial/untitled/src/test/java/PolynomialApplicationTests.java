import org.example.polynomial.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class PolynomialApplicationTests {
    polynomial Poly = new polynomial();
    polynomial Poly1 = new polynomial();
    monomial Mono = new monomial();
    polynomial UnitPoly = new polynomial();
    List<monomial> testList = new ArrayList<>();
    List<monomial> testListLong = new ArrayList<>();

    @BeforeEach
    void setMockData(){
        Mono.setValue(10.5);
        Mono.setPower(10);

        monomial Unit = new monomial();
        Unit.setValue(1);
        Unit.setPower(0);
        List<monomial> T = new ArrayList<>();
        T.add(Unit);
        UnitPoly.setPolynomial(T);

        testList.add(Mono);
        Poly.setPolynomial(testList);

        monomial M1 = new monomial();
        M1.setPower(3);
        M1.setValue(1);
        testListLong.add(M1);
        monomial M2 = new monomial();
        M2.setPower(4);
        M2.setValue(5);
        testListLong.add(M2);
        Poly1.setPolynomial(testListLong);
    }

    @Test
    void testPowerMono(){
        Assertions.assertEquals(Mono.getPower(),10);
    }

    @Test
    void testValueMono(){
        Assertions.assertEquals(Mono.getValue(),10.5);
    }

    @Test
    void testPolyNotEmpty(){
        Assertions.assertNotEquals(Poly.getPolynomial().isEmpty(),true);
    }

    @Test
    void gettingPolynomial(){
        Assertions.assertEquals(Poly.getPolynomial(),testList);
    }

    @Test
    void gettingCoefficient(){
        Assertions.assertEquals(Poly.getCoefficients(10).stream().toList(),testList);
    }

    @Test
    void polynomialSumTest(){
        polynomial P1 = new polynomial();
        P1.setPolynomial(testList);
        Assertions.assertNotEquals(Poly.polynomialSum(Poly),P1);
    }

    @Test
    void polynomialSubtractionTest(){
        Assertions.assertEquals(Poly.subtractPolynomial(Poly).getPolynomial().isEmpty(), true);
    }

    @Test
    void evaluate(){
        Assertions.assertEquals(Poly.evaluate(1.),10.5);
    }

    @Test
    void multiplyPoly(){
        Assertions.assertEquals(Poly.multiplyPolynomial(UnitPoly).subtractPolynomial(Poly).getPolynomial().isEmpty(),true);
    }

    @Test
    void normalizationTest(){
        Poly1.normalizeRepresentation();
        Assertions.assertEquals(Poly1.subtractPolynomial(Poly1).getPolynomial().isEmpty(),true);
    }

    @Test
    void dividePolynomials(){
        Assertions.assertEquals(Poly.divPolynomial(Poly).subtractPolynomial(UnitPoly).getPolynomial().isEmpty(), true);
        Assertions.assertEquals(Poly.modPolynomial(Poly).getPolynomial().isEmpty(), true);
    }
}
