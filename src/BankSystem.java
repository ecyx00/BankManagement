import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BankSystem {
	private List<BankAccount>accounts;
	public BankSystem() {
		accounts = new ArrayList<>();
	}
	
public void createAccount(String accountType, int accountNumber, double balance, double transactionFeeOrRate, String riskLevel) {
	BankAccount account = null;
	switch(accountType.toLowerCase()) {
	case "savings":
		account=new SavingAccount(accountNumber, balance, transactionFeeOrRate);
		break;
	case "checking":
		account= new CheckingAccount(accountNumber, balance, transactionFeeOrRate);
		break;
	case "investment":
		account = new InvestmentAccount(accountNumber, balance, riskLevel);
		break;
		default:
			System.out.println("Invalid account type!");
			return;
	}
	accounts.add(account);
	System.out.println("Account created succesfully");
}

public BankAccount findAccount(int accountNumber) {
	for(BankAccount account:accounts) {
		if(account.getAccountNumber()==accountNumber) {
			return account;
		}
	}
	return null;
}
public BankAccount deposit(int accountNumber,double amount) {
	
	BankAccount account=findAccount(accountNumber);
	if(account!=null) {
		account.deposit(amount);
	}else {
		System.out.println("Account not found");
	}
	return account;
}

public void withdraw(int accountNumber, double amount) {
    BankAccount account = findAccount(accountNumber);
    if (account != null) {
        if (account instanceof SavingAccount) {
            SavingAccount savings = (SavingAccount) account; // Downcasting
            if (savings.getBalance() - amount < 500) { // Özel kural: Minimum bakiye 500 olmalı
                System.out.println("Insufficient balance! Minimum balance of 500 must be maintained.");
                return;
            }
        } else if (account instanceof CheckingAccount) {
            CheckingAccount checking = (CheckingAccount) account; // Downcasting
            double totalAmount = amount + checking.getTransactionFee();
            if (checking.getBalance() < totalAmount) {
                System.out.println("Insufficient balance for withdrawal including transaction fee.");
                return;
            }
        }
        account.withdraw(amount); // Genel çekim işlemi
        System.out.println("Withdrawal successful!");
    } else {
        System.out.println("Account not found!");
    }
}


public void listAccounts() {
    for (BankAccount account : accounts) {
        System.out.println("Account Number: " + account.getAccountNumber());
        System.out.println("Balance: " + account.getBalance());

        // Hesap türüne göre işlem
        if (account instanceof SavingAccount) {
            SavingAccount savings = (SavingAccount) account; // Downcasting
            System.out.println("Interest Rate: " + savings.getInterestRate());
        } else if (account instanceof CheckingAccount) {
            CheckingAccount checking = (CheckingAccount) account; // Downcasting
            System.out.println("Transaction Fee: " + checking.getTransactionFee());
        } else if (account instanceof InvestmentAccount) {
            InvestmentAccount investment = (InvestmentAccount) account; // Downcasting
            System.out.println("Risk Level: " + investment.getRiskLevel());
            System.out.println("Investment Rate: " + investment.getInvestmentRate());
        }
        System.out.println("---------------------------");
    }
}

public void calculateInterest(int accountNumber) {
    BankAccount account = findAccount(accountNumber);
    if (account != null) {
        if (account instanceof SavingAccount) {
            SavingAccount savings = (SavingAccount) account; // Downcasting
            double interest = savings.calculateInterest();
            System.out.println("Savings Account Interest: " + interest);
        } else if (account instanceof InvestmentAccount) {
            InvestmentAccount investment = (InvestmentAccount) account; // Downcasting
            double interest = investment.calculateInterest();
            System.out.println("Investment Account Interest: " + interest);
        } else {
            System.out.println("This account type does not support interest calculation.");
        }
    } else {
        System.out.println("Account not found!");
    }
}


public void start() {
	Scanner sc=new Scanner(System.in);
	while(true) {
		System.out.println("\\n--- Bank System Menu ---");
		System.out.println("1. Create Account");
		System.out.println("2. Deposit");
		System.out.println("3. Withdraw");
		System.out.println("4. List Accounts");
		System.out.println("5. Calculate Interest");
		System.out.println("6. Exit");
		System.out.print("Choose an option: ");
		int choice=sc.nextInt();
		
		switch(choice) {
		case 1:
			System.out.println("Enter account type (savings/checking/investment): ");
			String accountType=sc.next();
			System.out.println("Enter account number: ");
			int accountNumber = sc.nextInt();
            System.out.print("Enter initial balance: ");
            double balance = sc.nextDouble();
            if (accountType.equalsIgnoreCase("investment")) {
                System.out.print("Enter risk level (low/medium/high): ");
                String riskLevel = sc.next();
                createAccount(accountType, accountNumber, balance, 0, riskLevel);
            } else {
                System.out.print("Enter transaction fee or interest rate: ");
                double transactionFeeOrRate = sc.nextDouble();
                createAccount(accountType, accountNumber, balance, transactionFeeOrRate, null);
            }
            break;
		case 3:
            System.out.print("Enter account number: ");
            int withdrawAccount = sc.nextInt();
            System.out.print("Enter amount to withdraw: ");
            double withdrawAmount = sc.nextDouble();
            withdraw(withdrawAccount, withdrawAmount);
            break;
        case 4:
            listAccounts();
            break;
        case 5:
            System.out.print("Enter account number: ");
            int interestAccount = sc.nextInt();
            calculateInterest(interestAccount);
            break;
        case 6:
            System.out.println("Exiting...");
            sc.close();
            return;
        default:
            System.out.println("Invalid option!");
		}
	}
}

}

