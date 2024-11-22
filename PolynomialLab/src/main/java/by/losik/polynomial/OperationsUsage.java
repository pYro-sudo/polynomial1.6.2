package by.losik.polynomial;

import java.util.Scanner;

public class OperationsUsage {
    private Polynomial polynomial = new Polynomial();
    private Polynomial polynomial1 = new Polynomial();

    public Polynomial getPolynomial() {
        return polynomial;
    }
    public Polynomial getPolynomial1(){return polynomial1;}

    public Polynomial printer(Polynomial polynomial){
        polynomial.getPolynomial().forEach(x->System.out.print(x.getValue()+"*x^"+x.getPower()));
        System.out.print("\n");
        return polynomial;
    }
    public void inputCoefficients() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the configuration of the polynomial\nFor breaking the cycle press 'q'\n" +
                "Enter something besides 'q' if you want to continue");

        while(!scanner.nextLine().equals("q")){
            try{
                double val; int power;
                System.out.println("Enter the power");
                power = scanner.nextInt();
                System.out.println("Enter the value of the coefficient");
                val = scanner.nextDouble();
                this.polynomial.setMonomial(power,val);
            }
            catch (Exception e){
                System.out.println("Wrong input");
                e.printStackTrace();
                this.polynomial.getPolynomial().clear();
                return;
            }
        }

        System.out.println("Enter the configuration of the polynomial\nFor breaking the cycle press 'q'\n" +
                "Enter something besides 'q' if you want to continue");

        while(!scanner.nextLine().equals('q')){
            try{
                double value; int power;
                System.out.println("Enter the power");
                power = scanner.nextInt();
                System.out.println("Enter the value of the coefficient");
                value = scanner.nextDouble();
                this.polynomial1.setMonomial(power,value);
            }
            catch (Exception e){
                System.out.println("Wrong input");
                e.printStackTrace();
                this.polynomial1.getPolynomial().clear();
                return;
            }
        }

    }

    public void getValue() throws Exception{
        System.out.println("Enter the power");
        Scanner scanner = new Scanner(System.in);
        int power = scanner.nextInt();
        this.polynomial.getCoefficients(power).forEach(x->System.out.println("The coefficient is:"+x.getValue()));
    }

    public void chooseOperation() throws Exception{
        String choice = new String();
        System.out.println("Welcome!\nEnter something besides 'q'");
        Scanner scanner = new Scanner(System.in);

        while(!scanner.nextLine().equals("q")){
            System.out.print("Enter the number of operation\n\"enter\" For entering coefficients\n\"add\" For adding polynomials\n\"sub\" For subtracting polynomials" +
                    "\n\"mul\" For multiplying polynomials\n\"div\" For dividing polynomials\n\"q\" For quitting\n");
            choice = scanner.nextLine();
            switch (choice){
                case "val":
                    if(!this.polynomial.getPolynomial().isEmpty()){
                        getValue();
                    }
                    else{
                        System.out.println("Nothing is in the polynomial at this power");
                        break;
                    }
                case "enter":
                    this.inputCoefficients();

                    break;
                case "add":
                    if(!this.getPolynomial1().getPolynomial().isEmpty() && !this.getPolynomial().getPolynomial().isEmpty()){
                        printer(polynomial1.polynomialSum(this.getPolynomial()));
                    }
                    else{
                        System.out.println("No input for the first polynomial");
                    }
                    break;
                case "sub":
                    if(!this.getPolynomial1().getPolynomial().isEmpty() && !this.getPolynomial().getPolynomial().isEmpty()){
                        printer(polynomial.subtractPolynomial(this.getPolynomial1()));
                    }
                    else{
                        System.out.println("No input for the first polynomial");
                    }
                    break;
                case "mul":
                    if(!this.getPolynomial1().getPolynomial().isEmpty() && !this.getPolynomial().getPolynomial().isEmpty()){
                        printer(polynomial1.multiplyPolynomial(this.getPolynomial()));
                    }
                    else{
                        System.out.println("No input for the first polynomial");
                    }
                    break;
                case "div":
                    if(!this.getPolynomial1().getPolynomial().isEmpty() && !this.getPolynomial().getPolynomial().isEmpty()){
                        printer(polynomial.divPolynomial(polynomial1));
                        printer(polynomial.modPolynomial(polynomial1));
                    }
                    else{
                        System.out.println("No input for the first polynomial");
                    }
                    break;
                case "q":
                    return ;
                default:
                    System.out.println("Wrong input");
                    break;
            }
        }
        return ;
    }
}
