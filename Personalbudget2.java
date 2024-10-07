
import java.util.Scanner;

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
        String choice = sc.nextLine();  // This works fine now as we have cleared the buffer

        double rent = 0;
        double monthlyPayment = 0;

        if (choice.equalsIgnoreCase("rent")) {
            System.out.println("Enter the monthly rent amount: ");
            rent = sc.nextDouble();
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
                return;
            }

            double loanAmount = purchasePrice - deposit;
            System.out.println("Loan amount: " + loanAmount);

            double monthlyInterestRate = interestRate / 12;

            monthlyPayment = loanAmount * (monthlyInterestRate * Math.pow(1 + monthlyInterestRate, months)) /
                    (Math.pow(1 + monthlyInterestRate, months) - 1);

            System.out.println("Your monthly mortgage payment is: " + monthlyPayment);
        } else {
            System.out.println("Invalid choice. Please enter either 'rent' or 'buy'.");
            return;
        }

        // Step 4: Calculate total expenses
        double totalExpenses = rent + monthlyPayment + taxDeducted;
        for (double expense : expenses) {
            totalExpenses += expense;
        }

        System.out.println("Your total monthly expenses (including rent/mortgage) are: " + totalExpenses);

        double remainingIncome = grossIncome - totalExpenses;
        System.out.println("Your remaining income after all expenses is: " + remainingIncome);

        // Step 5: Ask about buying a vehicle
        sc.nextLine(); // Clear buffer again before nextLine()
        System.out.println("Do you want to buy a vehicle? (yes or no): ");
        String vehicleChoice = sc.nextLine();

        if (vehicleChoice.equalsIgnoreCase("no")) {
            System.out.println("You have chosen not to buy a vehicle.");
        } else if (vehicleChoice.equalsIgnoreCase("yes")) {
            System.out.print("Enter model and make: ");
            String modelAndMake = sc.nextLine();

            System.out.print("Enter purchase price of the vehicle: ");
            double vehiclePurchasePrice = sc.nextDouble();

            System.out.print("Enter total deposit: ");
            double vehicleTotalDeposit = sc.nextDouble();

            System.out.print("Enter interest rate (percentage): ");
            double vehicleAnnualInterestRate = sc.nextDouble();

            System.out.print("Enter estimated insurance premium: ");
            double insurance = sc.nextDouble();

            // Calculate monthly vehicle payment
            int vehicleLoanTerm = 60; // Assuming 5-year loan
            double vehicleLoanAmount = vehiclePurchasePrice - vehicleTotalDeposit;
            double vehicleMonthlyInterestRate = (vehicleAnnualInterestRate / 100) / 12;

            double vehicleMonthlyPayment = vehicleLoanAmount * (vehicleMonthlyInterestRate * Math.pow(1 + vehicleMonthlyInterestRate, vehicleLoanTerm)) /
                    (Math.pow(1 + vehicleMonthlyInterestRate, vehicleLoanTerm) - 1);

            double totalVehicleCost = vehicleMonthlyPayment + insurance;
            System.out.println("Your monthly vehicle payment is: " + vehicleMonthlyPayment);
            System.out.println("Your total monthly vehicle cost including insurance is: " + totalVehicleCost);

            // Update total expenses
            totalExpenses += totalVehicleCost;
            remainingIncome = grossIncome - totalExpenses;

            System.out.println("Updated total monthly expenses with vehicle cost: " + totalExpenses);
            System.out.println("Updated remaining income after all expenses: " + remainingIncome);
        } else {
            System.out.println("Invalid input. Please choose 'yes' or 'no'.");
        }

        sc.close(); // Close scanner
    }
}