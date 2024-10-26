import by.losik.polynomial.Monomial;
import by.losik.polynomial.Polynomial;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class MonomialTest {
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
    void testPowerMono(){
        Assertions.assertEquals(monomial.getPower(),10);
    }

    @Test
    void setMono(){
        Monomial monomial1 = new Monomial();
        monomial1.setPower(10);
        monomial1.setValue(10);
        Assertions.assertEquals(monomial1.getValue(),10);
        Assertions.assertEquals(monomial1.getValue(),10);
    }

    @Test
    void testValueMono(){
        Assertions.assertEquals(monomial.getPower(),10);
        Assertions.assertEquals(monomial.getValue(),10.5);
    }

    @Test
    void testMonomial(){
        Monomial monomial = new Monomial();
        Assertions.assertEquals(monomial.getValue(),0);
        monomial.setValue(10);
        monomial.setPower(10);
        Assertions.assertEquals(monomial.getValue(),10);
        Assertions.assertEquals(monomial.getPower(),10);
    }
}
