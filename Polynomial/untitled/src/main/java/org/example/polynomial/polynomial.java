package org.example.polynomial;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class polynomial {
    private List<monomial> Polynomial = new ArrayList<>();

    public void setPolynomial(List<monomial> P){
        this.Polynomial = P;
    }
    public List<monomial> getPolynomial() {
        return Polynomial;
    }

    public void setMonomial(Integer power, Double value) throws IOException {
        monomial Mono = new monomial();
        Mono.setValue(value); Mono.setPower(power);
        this.Polynomial.add(Mono);
    }

    public void normalizeRepresentation() {
        for(monomial M:this.Polynomial){
            for(monomial N:this.Polynomial){
                if(M.getPower()>N.getPower()){
                    monomial T = M;
                    M = N;
                    N = T;
                }
            }
        }
    }

    public List<monomial> getCoefficients(@NotNull Integer power){
        return Polynomial.stream().filter(x -> x.getPower().equals(power)).toList();
    }

    public double evaluate(double value) {
        double result;
        result = 0;
        for(monomial M:Polynomial){
            result+=M.getValue()*Math.pow(value,M.getPower());
        }
        return result;
    }

    public polynomial polynomialSum(@NotNull polynomial P){
        List<monomial> Res = new ArrayList<>();
        for (monomial M: P.getPolynomial()) {
            monomial T = new monomial();
            for(monomial N: Polynomial){
                if(M.getPower().equals(N.getPower()) && M.getValue().equals(-N.getValue())){
                    T.setPower(M.getPower());
                    T.setValue(M.getValue()+N.getValue());
                    Res.add(T);
                    break;
                }
            }
        }

        polynomial Result = new polynomial();
        Result.setPolynomial(Res);
        return Result;
    }

    public polynomial subtractPolynomial(@NotNull polynomial P){
        List<monomial> Res = new ArrayList<>();
        for(monomial M:P.getPolynomial()){
            monomial T = new monomial();
            for(monomial N:Polynomial){
                if(N.getPower().equals(M.getPower()) && !N.getValue().equals(M.getValue())) {
                    T.setValue(N.getValue()-M.getValue());
                    T.setPower(N.getPower());
                    Res.add(T);
                    break;
                }
            }
        }

        polynomial Result = new polynomial();
        Result.setPolynomial(Res);
        return Result;
    }


    public polynomial multiplyPolynomial(@NotNull polynomial P){
        polynomial Result = new polynomial();
        List<monomial> T = new ArrayList<>();

        for(monomial N: Polynomial){
            for(monomial M: P.getPolynomial()){
                monomial V = new monomial();
                V.setPower(N.getPower()+M.getPower());
                V.setValue(N.getValue()*M.getValue());
                Boolean isFound = false;
                for(monomial K: T){
                    if(K.getPower().equals(V.getPower())){
                        isFound = true;
                        K.setValue(K.getValue()+V.getValue());
                    }
                }
                if(isFound.equals(false)){
                    T.add(V);
                }
            }
        }


        Result.setPolynomial(T);
        return Result;
    }

    public polynomial divPolynomial(@NotNull polynomial P){
        P.normalizeRepresentation();
        polynomial Temp = new polynomial();
        Temp.setPolynomial(Polynomial);
        Temp.normalizeRepresentation();
        List<monomial> div = new ArrayList<>();

        for(monomial N:Temp.getPolynomial()){
            for(monomial M:P.getPolynomial()){
                if(N.getPower()-M.getPower() < 0){
                    break;
                }

                monomial T = new monomial();
                T.setPower(N.getPower()- M.getPower());
                T.setValue(N.getValue()/M.getValue());
                div.add(T);

                List<monomial> K = new ArrayList<>();
                K.add(T);
                polynomial V = new polynomial();
                V.setPolynomial(K);

                Temp.setPolynomial(Temp.subtractPolynomial(V.multiplyPolynomial(P)).getPolynomial());
                Temp.divPolynomial(P);
            }
        }

        return Temp;
    }

    public polynomial modPolynomial(@NotNull polynomial P){
        polynomial tempForDiv = new polynomial();
        tempForDiv.setPolynomial(Polynomial);

        polynomial Temp = new polynomial();
        Temp.setPolynomial(Polynomial);

        return Temp.subtractPolynomial(tempForDiv.multiplyPolynomial(tempForDiv.divPolynomial(P)));
    }
}