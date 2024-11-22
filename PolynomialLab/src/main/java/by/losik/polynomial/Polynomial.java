package by.losik.polynomial;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Polynomial {
    private List<Monomial> polynomial = new ArrayList<>();

    public void setPolynomial(List<Monomial> polynomial){
        this.polynomial = polynomial;
    }
    public List<Monomial> getPolynomial() {
        return polynomial;
    }

    public Monomial addMonomial(Integer power, Double value) throws IOException {
        Monomial monomial = new Monomial();
        monomial.setValue(value); monomial.setPower(power);
        this.polynomial.add(monomial);
        return monomial;
    }

    public void normalizeRepresentation() {
        for(Monomial itemsInFirst:this.polynomial){
            for(Monomial itemsInSecond:this.polynomial){
                if(itemsInFirst.getPower()>itemsInSecond.getPower()){
                    Monomial temporaryForSwap = itemsInFirst;
                    itemsInFirst = itemsInSecond;
                    itemsInSecond = temporaryForSwap;
                }
            }
        }
    }

    public List<Monomial> getCoefficients(int power){
        return polynomial.stream().filter(x -> x.getPower() == power).toList();
    }

    public double evaluate(double value) {
        double result;
        result = 0;
        for(Monomial monomial:polynomial){
            result+=monomial.getValue()*Math.pow(value,monomial.getPower());
        }
        return result;
    }

    public Polynomial polynomialSum(Polynomial polynomial){
        List<Monomial> monomialArrayList = new ArrayList<>();
        for (Monomial itemsInFirst: polynomial.getPolynomial()) {
            Monomial itemsInSecond = new Monomial();
            for(Monomial itemsInPolynomial: polynomial){
                if(itemsInFirst.getPower() == itemsInPolynomial.getPower() && itemsInFirst.getValue() == -itemsInPolynomial.getValue()){
                    itemsInSecond.setPower(itemsInFirst.getPower());
                    itemsInSecond.setValue(itemsInFirst.getValue()+itemsInPolynomial.getValue());
                    monomialArrayList.add(itemsInSecond);
                    break;
                }
            }
        }

        Polynomial sumResult = new Polynomial();
        sumResult.setPolynomial(monomialArrayList);
        return sumResult;
    }

    public Polynomial subtractPolynomial(Polynomial polynomial){
        List<Monomial> result = new ArrayList<>();
        for(Monomial itemInFirst:polynomial.getPolynomial()){
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

        Polynomial substractionResult = new Polynomial();
        substractionResult.setPolynomial(result);
        return substractionResult;
    }


    public Polynomial multiplyPolynomial(Polynomial polynomial){
        Polynomial multiplicationResult = new Polynomial();
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


        multiplicationResult.setPolynomial(MonomialArrayList);
        return multiplicationResult;
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
                Polynomial qualifierPolynomial = new Polynomial();
                qualifierPolynomial.setPolynomial(qualifierList);

                divResult.setPolynomial(divResult.subtractPolynomial(qualifierPolynomial.multiplyPolynomial(polynomial)).getPolynomial());
                divResult.divPolynomial(polynomial);
            }
        }

        return divResult;
    }

    public Polynomial modPolynomial(Polynomial polynomial){
        Polynomial tempForDiv = new Polynomial();
        tempForDiv.setPolynomial(Polynomial);

        Polynomial temporaryForMod = new Polynomial();
        temporaryForMod.setPolynomial(Polynomial);

        return temporaryForMod.subtractPolynomial(tempForDiv.multiplyPolynomial(tempForDiv.divPolynomial(polynomial)));
    }
}
