import org.example.*;

import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.instanceOf;

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
    void setMono(){
        monomial M = new monomial();
        M.setPower(10);
        M.setValue(10);
        Assertions.assertEquals(M.getValue(),10);
        Assertions.assertEquals(M.getValue(),10);
    }

    @Test
    void testValueMono(){
        Assertions.assertEquals(Mono.getPower(),10);
        Assertions.assertEquals(Mono.getValue(),10.5);
    }

    @Test
    void testMonomial(){
        monomial monomial = new monomial();
        Assertions.assertEquals(monomial.getPower(),null);
        Assertions.assertEquals(monomial.getValue(),0);
        monomial.setValue(10);
        monomial.setPower(10);
        Assertions.assertEquals(monomial.getValue(),10);
        Assertions.assertEquals(monomial.getPower(),10);
    }

    @Test
    void gettingPolynomial(){
        Assertions.assertNotEquals(Poly.getPolynomial().isEmpty(),true);
        Assertions.assertEquals(Poly.getPolynomial(),testList);
        Assertions.assertEquals(Poly.getCoefficients(10).stream().toList(),testList);
        polynomial P1 = new polynomial();
        P1.setPolynomial(testList);
        Assertions.assertNotEquals(Poly.polynomialSum(Poly),P1);
        Assertions.assertTrue(Poly.subtractPolynomial(Poly).getPolynomial().isEmpty());
        Assertions.assertEquals(Poly.evaluate(1.),10.5);
        Assertions.assertTrue(Poly.multiplyPolynomial(UnitPoly).subtractPolynomial(Poly).getPolynomial().isEmpty());
        Poly1.normalizeRepresentation();
        Assertions.assertTrue(Poly1.subtractPolynomial(Poly1).getPolynomial().isEmpty());
        Assertions.assertTrue(Poly.divPolynomial(Poly).subtractPolynomial(UnitPoly).getPolynomial().isEmpty());
        Assertions.assertTrue(Poly.modPolynomial(Poly).getPolynomial().isEmpty());
    }


    @Test
    void operationUsage() throws Exception {
        operationsUsage operationsUsage = new operationsUsage();
        Assertions.assertEquals(operationsUsage.getPoly().getPolynomial().isEmpty(),true);
        operationsUsage.getPoly().setPolynomial(testList);
        Assertions.assertEquals(operationsUsage.getPoly().getPolynomial(),testList);
        Assert.assertThat(operationsUsage.getPoly1(),instanceOf(polynomial.class));
        Assert.assertThat(operationsUsage.printer(new polynomial()),instanceOf(polynomial.class));
        Assert.assertThat(Poly.setMonomial(10,10.0),instanceOf(monomial.class));
    }
}
