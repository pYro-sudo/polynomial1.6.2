package by.losik.polynomial;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Polynomial {
    private List<Monomial> Polynomial = new ArrayList<>();

    public void setPolynomial(List<Monomial> Poly){
        this.Polynomial = Poly;
    }
    public List<Monomial> getPolynomial() {
        return Polynomial;
    }

    public Monomial addMonomial(Integer power, Double value) throws IOException {
        Monomial monomial = new Monomial();
        monomial.setValue(value); monomial.setPower(power);
        this.Polynomial.add(monomial);
        return monomial;
    }

    public void normalizeRepresentation() {
        for(Monomial itemsInFirst:this.Polynomial){
            for(Monomial itemsInSecond:this.Polynomial){
                if(itemsInFirst.getPower()>itemsInSecond.getPower()){
                    Monomial temporaryForSwap = itemsInFirst;
                    itemsInFirst = itemsInSecond;
                    itemsInSecond = temporaryForSwap;
                }
            }
        }
    }

    public List<Monomial> getCoefficients(int power){
        return Polynomial.stream().filter(x -> x.getPower() == power).toList();
    }

    public double evaluate(double value) {
        double result;
        result = 0;
        for(Monomial M:Polynomial){
            result+=M.getValue()*Math.pow(value,M.getPower());
        }
        return result;
    }

    public Polynomial polynomialSum(Polynomial P){
        List<Monomial> monomialArrayList = new ArrayList<>();
        for (Monomial itemsInFirst: P.getPolynomial()) {
            Monomial itemsInSecond = new Monomial();
            for(Monomial N: Polynomial){
                if(itemsInFirst.getPower() == N.getPower() && itemsInFirst.getValue() == -N.getValue()){
                    itemsInSecond.setPower(itemsInFirst.getPower());
                    itemsInSecond.setValue(itemsInFirst.getValue()+N.getValue());
                    monomialArrayList.add(itemsInSecond);
                    break;
                }
            }
        }

        Polynomial Result = new Polynomial();
        Result.setPolynomial(monomialArrayList);
        return Result;
    }

    public Polynomial subtractPolynomial(Polynomial P){
        List<Monomial> result = new ArrayList<>();
        for(Monomial itemInFirst:P.getPolynomial()){
            Monomial compareResult = new Monomial();
            for(Monomial itemInSecond:Polynomial){
                if(itemInSecond.getPower() == itemInFirst.getPower() && !(itemInSecond.getValue() == itemInFirst.getValue())) {
                    compareResult.setValue(itemInSecond.getValue()-itemInFirst.getValue());
                    compareResult.setPower(itemInSecond.getPower());
                    result.add(compareResult);
                    break;
                }
            }
        }

        Polynomial Result = new Polynomial();
        Result.setPolynomial(result);
        return Result;
    }


    public Polynomial multiplyPolynomial(Polynomial polynomial){
        by.losik.polynomial.Polynomial result = new Polynomial();
        List<Monomial> MonomialArrayList = new ArrayList<>();

        for(Monomial itemInFirst: Polynomial){
            for(Monomial itemInSecond: polynomial.getPolynomial()){
                Monomial qualifierForAdding = new Monomial();
                qualifierForAdding.setPower(itemInFirst.getPower()+itemInSecond.getPower());
                qualifierForAdding.setValue(itemInFirst.getValue()*itemInSecond.getValue());
                Boolean isFound = false;
                for(Monomial forRepeats: MonomialArrayList){
                    if(forRepeats.getPower() == qualifierForAdding.getPower()){
                        isFound = true;
                        forRepeats.setValue(forRepeats.getValue()+qualifierForAdding.getValue());
                    }
                }
                if(isFound.equals(false)){
                    MonomialArrayList.add(qualifierForAdding);
                }
            }
        }


        result.setPolynomial(MonomialArrayList);
        return result;
    }

    public Polynomial divPolynomial(Polynomial polynomial){
        polynomial.normalizeRepresentation();
        Polynomial divResult = new Polynomial();
        divResult.setPolynomial(Polynomial);
        divResult.normalizeRepresentation();
        List<Monomial> divPart = new ArrayList<>();

        for(Monomial itemInFirst:divResult.getPolynomial()){
            for(Monomial itemInSecond:polynomial.getPolynomial()){
                if(itemInFirst.getPower()-itemInSecond.getPower() < 0){
                    break;
                }

                Monomial qualifierForAdding = new Monomial();
                qualifierForAdding.setPower(itemInFirst.getPower()- itemInSecond.getPower());
                qualifierForAdding.setValue(itemInFirst.getValue()/itemInSecond.getValue());
                divPart.add(qualifierForAdding);

                List<Monomial> qualifierList = new ArrayList<>();
                qualifierList.add(qualifierForAdding);
                by.losik.polynomial.Polynomial qualifierPolynomial = new Polynomial();
                qualifierPolynomial.setPolynomial(qualifierList);

                divResult.setPolynomial(divResult.subtractPolynomial(qualifierPolynomial.multiplyPolynomial(polynomial)).getPolynomial());
                divResult.divPolynomial(polynomial);
            }
        }

        return divResult;
    }

    public Polynomial modPolynomial(Polynomial polynomial){
        by.losik.polynomial.Polynomial tempForDiv = new Polynomial();
        tempForDiv.setPolynomial(Polynomial);

        by.losik.polynomial.Polynomial temp = new Polynomial();
        temp.setPolynomial(Polynomial);

        return temp.subtractPolynomial(tempForDiv.multiplyPolynomial(tempForDiv.divPolynomial(polynomial)));
    }
}
