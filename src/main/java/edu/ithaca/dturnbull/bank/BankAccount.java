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
            //splitting the name of email and address
            String[] splitMail=email.split("@");
            String prefix=splitMail[0];
            String end=splitMail[1];
            if(splitMail[0]==""){
                return false;
            }
            //checking for starting and ending special characters in name
            if(prefix.charAt(0)=='-' || prefix.charAt(0)=='.'){
                return false;
            }
            if(prefix.charAt(prefix.length()-1)=='-' || prefix.charAt(prefix.length()-1)=='.'){
                return false;
            }
            //making sure not middle special characters in a row
            for(int i=0;i<prefix.length();i++){
                if(prefix.charAt(i)=='.'){
                    if(prefix.charAt(i+1)=='.'){
                        return false;
                    }
                }
                if(prefix.charAt(i)=='#'){
                    return false;
                }
            }
            int counter=0;
            int index=-1;
            //making sure address has a .
            for(int i=0;i<end.length();i++){
                if(end.charAt(i)=='.'){
                    if(end.charAt(i+1)=='.'){
                        return false;
                    }
                    counter++;
                    index=i;
                }
                if(end.charAt(i)=='#'){
                    return false;
                }
            }
            if(counter==0){
                return false;
            }
            String address=end.substring(index);
            //making sure it has a valid address after the .
            if(address.equals(".com") || address.equals(".org") || address.equals(".cc")){
                return true;
            }
            return false;
        }
    }
   
    
}