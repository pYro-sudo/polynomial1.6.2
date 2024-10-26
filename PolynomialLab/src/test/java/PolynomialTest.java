import by.losik.polynomial.Monomial;
import by.losik.polynomial.Polynomial;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class PolynomialTest {
    Polynomial polynomial = new Polynomial();
    Polynomial polynomial1 = new Polynomial();
    Monomial monomial = new Monomial();
    Polynomial unitPoly = new Polynomial();
    List<Monomial> testList = new ArrayList<>();
    List<Monomial> testListLong = new ArrayList<>();

    @BeforeEach
    void setMockData(){
        monomial.setValue(10.5);
        monomial.setPower(10);

        Monomial unit = new Monomial();
        unit.setValue(1);
        unit.setPower(0);
        List<Monomial> monomialArrayList = new ArrayList<>();
        monomialArrayList.add(unit);
        unitPoly.setPolynomial(monomialArrayList);

        testList.add(monomial);
        polynomial.setPolynomial(testList);

        Monomial monomial1 = new Monomial();
        monomial1.setPower(3);
        monomial1.setValue(1);
        testListLong.add(monomial1);
        Monomial monomial2 = new Monomial();
        monomial2.setPower(4);
        monomial2.setValue(5);
        testListLong.add(monomial2);
        polynomial1.setPolynomial(testListLong);
    }

    @Test
    void gettingPolynomial(){
        Assertions.assertNotEquals(polynomial.getPolynomial().isEmpty(),true);
        Assertions.assertEquals(polynomial.getPolynomial(),testList);
        Assertions.assertEquals(polynomial.getCoefficients(10).stream().toList(),testList);
        Polynomial P1 = new Polynomial();
        P1.setPolynomial(testList);
        Assertions.assertNotEquals(polynomial.polynomialSum(polynomial),P1);
        Assertions.assertTrue(polynomial.subtractPolynomial(polynomial).getPolynomial().isEmpty());
        Assertions.assertEquals(polynomial.evaluate(1.),10.5);
        Assertions.assertTrue(polynomial.multiplyPolynomial(unitPoly).subtractPolynomial(polynomial).getPolynomial().isEmpty());
        polynomial1.normalizeRepresentation();
        Assertions.assertTrue(polynomial1.subtractPolynomial(polynomial1).getPolynomial().isEmpty());
        Assertions.assertTrue(polynomial.divPolynomial(polynomial).subtractPolynomial(unitPoly).getPolynomial().isEmpty());
        Assertions.assertTrue(polynomial.modPolynomial(polynomial).getPolynomial().isEmpty());
    }
}
