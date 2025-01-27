package edu.ithaca.dturnbull.bank;

public class BankAccount {

    private String email;
    private double balance;

    /**
     * @throws IllegalArgumentException if email is invalid
     */
    public BankAccount(String email, double startingBalance){
        boolean tester=isAmountValid(startingBalance);
        if(tester==false){
            throw new IllegalArgumentException("Invalid starting balance");
        }
        if (isEmailValid(email)){
            this.email = email;
            this.balance = startingBalance;
        }
        else {
            throw new IllegalArgumentException("Email address: " + email + " is invalid, cannot create account");
        }
    }

    public double getBalance(){
        return balance;
    }

    public String getEmail(){
        return email;
    }

    /**
     * @post reduces the balance by amount if amount is non-negative and smaller than balance
     */
    public void withdraw (double amount) throws InsufficientFundsException{
        boolean tester=isAmountValid(amount);
        if(tester==false){
            throw new InsufficientFundsException("Invalid amount");
        }
        if (amount <= balance){
            balance -= amount;
        }
        else {
            throw new InsufficientFundsException("Not enough money");
        }
    }

    // post adds the amount being wished to be deposited into the instance
    public void deposit (double amount)throws InsufficientFundsException{
        boolean tester=isAmountValid(amount);
        if(tester==false){
            throw new InsufficientFundsException("Invalid amount");
        }
        balance=balance+amount;

    }

    //post, money from account being applied to gets transferred to other account
    public void transfer(double amount, BankAccount other) throws InsufficientFundsException{
        boolean tester=isAmountValid(amount);
        if(tester==false){
            throw new InsufficientFundsException("Incorrect amount");
        }
        if(amount>balance){
            throw new InsufficientFundsException("Not enough funds");
        }
        this.withdraw(amount);
        other.deposit(amount);

    }

    //returns true if correct, returns false if negative or bigger than 2 decimal places
    public static boolean isAmountValid(double amount){
        if(amount<0){
            return false;
        }
        String stringAmount=""+amount;
        String[] seperated=stringAmount.split("\\.");
        if(seperated.length<=1){
            return true;
        }
        else{
            if(seperated[1].length()>2){
                return false;
            }
            return true;
        }
        
    }


    public static boolean isEmailValid(String email){
        if (email.indexOf('@') == -1){
            return false;
        }
        else {
            return true;
        }
    }
}