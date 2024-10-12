
package personalbudget2;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
    
    // Abstract class for Property (Base Class)
abstract class Property {
abstract double calculateMonthlyCost();
}

// Rent class (Inherits Property)
class Rent extends Property {
    private double rentAmount;

    public Rent(double rentAmount) {
        this.rentAmount = rentAmount;
    }

    @Override
    double calculateMonthlyCost() {
        return rentAmount;
    }
}

// HomeLoan class (Inherits Property)
class HomeLoan extends Property {
    private double loanAmount, monthlyInterestRate;
    private int months;

    public HomeLoan(double loanAmount, double monthlyInterestRate, int months) {
        this.loanAmount = loanAmount;
        this.monthlyInterestRate = monthlyInterestRate;
        this.months = months;
    }

    @Override
    double calculateMonthlyCost() {
        return loanAmount * (monthlyInterestRate * Math.pow(1 + monthlyInterestRate, months)) /
                (Math.pow(1 + monthlyInterestRate, months) - 1);
    }
}

// Abstract class for Vehicle (Base Class)
abstract class Vehicle {
    abstract double calculateMonthlyCost();
}

// Car class (Inherits Vehicle)
class Car extends Vehicle {
    private double vehicleLoanAmount, vehicleMonthlyInterestRate, insurance;
    private int vehicleLoanTerm;

    public Car(double vehicleLoanAmount, double vehicleMonthlyInterestRate, int vehicleLoanTerm, double insurance) {
        this.vehicleLoanAmount = vehicleLoanAmount;
        this.vehicleMonthlyInterestRate = vehicleMonthlyInterestRate;
        this.vehicleLoanTerm = vehicleLoanTerm;
        this.insurance = insurance;
    }

    @Override
    double calculateMonthlyCost() {
        double monthlyPayment = vehicleLoanAmount * (vehicleMonthlyInterestRate * Math.pow(1 + vehicleMonthlyInterestRate, vehicleLoanTerm)) /
                (Math.pow(1 + vehicleMonthlyInterestRate, vehicleLoanTerm) - 1);
        return monthlyPayment + insurance;
    }
}

public class Personalbudget2 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Step 1: Enter gross income and tax
        System.out.println("Enter your monthly salary before tax: ");
        double grossIncome = sc.nextDouble();

        System.out.println("Enter your monthly tax: ");
        double taxDeducted = sc.nextDouble();
        double incomeAfterTax = grossIncome - taxDeducted;

        System.out.println("Your remaining balance after tax is: " + incomeAfterTax);

        // Step 2: Input monthly expenses
        double[] expenses = new double[5];

        System.out.println("Enter money for groceries: ");
        expenses[0] = sc.nextDouble();

        System.out.println("Enter money spent on water and electricity: ");
        expenses[1] = sc.nextDouble();

        System.out.println("Enter your monthly travelling (include petrol if user has a car): ");
        expenses[2] = sc.nextDouble();

        System.out.println("Enter money spent on electronics (phone, data, wifi): ");
        expenses[3] = sc.nextDouble();

        System.out.println("Enter your other expenses: ");
        expenses[4] = sc.nextDouble();

        // Clear buffer after using nextDouble()
        sc.nextLine();

        // Step 3: Rent or Buy a property
        System.out.println("Do you want to rent or buy a property? (Enter 'rent' or 'buy')");
        String choice = sc.nextLine();

        Property property = null;
        double monthlyPayment = 0;

        if (choice.equalsIgnoreCase("rent")) {
            System.out.println("Enter the monthly rent amount: ");
            double rent = sc.nextDouble();
            property = new Rent(rent);
            monthlyPayment = property.calculateMonthlyCost();

            System.out.println("You are renting a property at: " + rent + " per month.");

        } else if (choice.equalsIgnoreCase("buy")) {
            System.out.println("Enter the purchase price of the property: ");
            double purchasePrice = sc.nextDouble();

            System.out.println("Enter the deposit of the property: ");
            double deposit = sc.nextDouble();

            System.out.print("Enter the interest rate (in %): ");
            double interestRate = sc.nextDouble() / 100;

            System.out.println("Enter the number of months to pay off the house (240 - 360): ");
            int months = sc.nextInt();

            if (!(months >= 240 && months <= 360)) {
                System.out.println("The number of months must be between 240 and 360.");
              
                int repaymentMonths = sc.nextInt();

            if (purchasePrice <= 0 || deposit < 0 || deposit > purchasePrice || 
                interestRate < 0 || repaymentMonths < 240 || repaymentMonths > 360) {
                System.out.println("Invalid input. Please ensure all values are valid.");
                sc.close();
                return;
            }

            double loanAmount = purchasePrice - deposit;
            double monthlyInterestRate = interestRate/100 / 12;
            property = new HomeLoan(loanAmount, monthlyInterestRate, months);
            monthlyPayment = property.calculateMonthlyCost();

            System.out.println("Your monthly mortgage payment is: " + monthlyPayment);
        } 
            else{ 
                System.out.println("Invalid,Enter either buy or rent");
        }

        // Step 4: Calculate total expenses
        double totalExpenses = taxDeducted + monthlyPayment;
        for (double expense : expenses) {
            totalExpenses += expense;
        }

        System.out.println("Your total monthly expenses (including rent/mortgage) are: " + totalExpenses);

        double remainingIncome = grossIncome - totalExpenses;
        System.out.println("Your remaining income after all expenses is: " + remainingIncome);

        //part 2
        // Step 5: Ask about buying a vehicle
        sc.nextLine(); // Clear buffer again before nextLine()
        System.out.println("Do you want to buy a vehicle? (yes or no): ");
        String vehicleChoice = sc.nextLine();

        if (vehicleChoice.equalsIgnoreCase("yes")) {
            
            System.out.println("Enter Make of the vehicle:");
            String make = sc.nextLine();
            
             System.out.println("Enter the model of the vehicle:");
            String model = sc.nextLine();
            
            System.out.print("Enter purchase price of the vehicle: ");
            double vehiclePurchasePrice = sc.nextDouble();

            System.out.print("Enter total deposit: ");
            double vehicleTotalDeposit = sc.nextDouble();

            System.out.print("Enter interest rate (percentage): ");
            double vehicleAnnualInterestRate = sc.nextDouble();

            System.out.print("Enter estimated insurance premium: ");
            double insurance = sc.nextDouble();

            // Calculate monthly vehicle payment
            double vehicleLoanAmount = vehiclePurchasePrice - vehicleTotalDeposit;
            double vehicleMonthlyInterestRate = (vehicleAnnualInterestRate / 100) / 12;
            int vehicleLoanTerm = 5 *12;
            
            double vehicleMonthlyLoanPayment = vehicleLoanAmount * (vehicleMonthlyInterestRate * Math.pow(1 + vehicleMonthlyInterestRate, vehicleLoanTerm)) 
                                        / (Math.pow(1 + vehicleMonthlyInterestRate, vehicleLoanTerm) - 1);

    double vehicleMonthlyInsurance = insurance / 12;
    double totalVehicleMonthlyCost = vehicleMonthlyLoanPayment + vehicleMonthlyInsurance;

    //U SEDZA ARALI ZWI TSHI FHIRA 75% YA MUHOLO
    if(totalVehicleMonthlyCost > 0.75 * grossIncome) {
        System.out.println("Warning: Your total expenses exceed 75% of your income.");
    }
        List<Double> expense;
                expense = new ArrayList<>();
    expense.add(vehicleMonthlyLoanPayment);
    expense.add(vehicleMonthlyInsurance);
    expense.add(totalVehicleMonthlyCost);

 
    Collections.sort(expense, Collections.reverseOrder());

    System.out.println("Expenses in descending order:");
    for (double Expense : expense) {
        System.out.printf("%.2f\n", Expense);      
        } 
    
        }
        else {
            System.out.println("You have chosen not to buy a vehicle.");
        }
        sc.close(); // Close scanner 
        }
    }
}
