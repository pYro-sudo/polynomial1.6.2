import by.losik.polynomial.Monomial;
import by.losik.polynomial.OperationsUsage;
import by.losik.polynomial.Polynomial;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class OperationUsageTest {
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
    void operationUsage() throws Exception {
        OperationsUsage operationsUsage = new OperationsUsage();
        Assertions.assertEquals(operationsUsage.getPolynomial().getPolynomial().isEmpty(),true);
        operationsUsage.getPolynomial().setPolynomial(testList);
        Assertions.assertEquals(operationsUsage.getPolynomial().getPolynomial(),testList);
        Assert.assertTrue(operationsUsage.getPolynomial1() instanceof Polynomial);
        Assert.assertTrue(operationsUsage.printer(new Polynomial()) instanceof Polynomial);
        Assert.assertTrue(polynomial.addMonomial(10,10.0) instanceof Monomial);
    }
}
