import java.util.*;

class User_details 
{
    private String user_id;
    private String pin_no;

    public User_details(String user_id, String pin_no) 
	{
        this.user_id = user_id;
        this.pin_no = pin_no;
    }

    public String getUserid() 
	{
        return user_id;
    }

    public String getPin() 
	{
        return pin_no;
    }
}

class Acc_details 
{
    private double bal;
    private String acc_no;
    private List<String> transaction_history;

    public Acc_details(String acc_no, double initial_deposit) 
	{
        this.acc_no = acc_no;
        this.bal = initial_deposit;
        this.transaction_history = new ArrayList<>();
    }

    public double getBalance() 
	{
        return bal;
    }

    public void deposit(double amt) 
	{
        bal += amt;
        addToTransactionHistory("Deposit: +" + amt);
    }

    public void withdraw(double amt) 
	{
        if (amt > bal) 
		{
            System.out.println("INSUFFICIENT AMOUNT!!!!!");
        } 
		else 
		{
            bal -= amt;
            addToTransactionHistory("Withdrawal: -" + amt);
        }
    }

    public void transaction(Acc_details recipient, double amt)
	{
        if (amt > bal) 
		{
            System.out.println("INSUFFICIENT AMOUNT FOR TRANSACTION!!!!!");
        } 
		else 
		{
            bal -= amt;
            recipient.deposit(amt);
            addToTransactionHistory("Transfer to " + recipient.acc_no + ": -" + amt);
        }
    }

    public List<String> getTransactionHistory() 
	{
        return transaction_history;
    }

    private void addToTransactionHistory(String transaction) 
	{
        transaction_history.add(transaction);
    }
}

class ATM 
{
    private User_details c_User;
    private Acc_details c_Account;

    public void start() 
	{
        Scanner object1 = new Scanner(System.in);

        System.out.print("Enter User ID: ");
        String user_id = object1.next();
        System.out.print("Enter PIN: ");
        String pin_no = object1.next();

        if (user_authentication(user_id, pin_no))
		{
            System.out.println("AUTHENTICATION SUCCESSFUL!!!!!");
            c_Account = new Acc_details("kcl1234", 1000.0); 
            display_Options();
        } 
		else 
		{
            System.out.println("AUTHENTICATION FAILED!!!!!*******EXITING*******");
        }
    }

    private boolean user_authentication(String user_id, String pin_no) 
	{
        User_details object2 = new User_details("kcl1234", "9876");
        return user_id.equals(object2.getUserid()) && pin_no.equals(object2.getPin());
    }

    private void display_Options() 
	{
        System.out.println("1. TRANSACTIONS HISTORY");
        System.out.println("2. WITHDRAW");
        System.out.println("3. DEPOSIT");
        System.out.println("4. TRANSFER");
        System.out.println("5. QUIT");

        Scanner object1 = new Scanner(System.in);
        int choice;

        do 
		{
            System.out.print("ENTER YOUR CHOICE: ");
            choice = object1.nextInt();

            switch (choice) 
			{
                case 1:
                    displayTransactionHistory();
                    break;
                case 2:
                    System.out.print("ENTER WITHDRAWAL AMOUNT: ");
                    double withdraw_amt = object1.nextDouble();
                    c_Account.withdraw(withdraw_amt);
                    displayBalance();
                    break;
                case 3:
                    System.out.print("ENTER DEPOSIT AMOUNT: ");
                    double deposit_amt = object1.nextDouble();
                    c_Account.deposit(deposit_amt);
                    displayBalance();
                    break;
                case 4:
                    System.out.print("ENTER RECIPIENT ACCOUNT NUMBER: ");
                    String recipient_acc_no = object1.next();
                    Acc_details recipient_acc = new Acc_details(recipient_acc_no, 0.0);
                    System.out.print("ENTER TRANSACTION AMOUNT: ");
                    double transfer_amt = object1.nextDouble();
                    c_Account.transaction(recipient_acc, transfer_amt);
                    displayBalance();
                    break;
                case 5:
                    System.out.println("*****EXITING ATM*****\nTHANK YOU!!!!!");
                    break;
                default:
                    System.out.println("INVALID CHOICE!!!!!");
                    break;
            }

        }
		while (choice != 5);
    }

    private void displayBalance() 
	{
        System.out.println("CURRENT BALANCE: " + c_Account.getBalance());
    }

    private void displayTransactionHistory() 
	{
        List<String> history = c_Account.getTransactionHistory();
        if (history.isEmpty()) 
		{
            System.out.println("NO TRANSACTIONS YET!!!!!");
        } 
		else 
		{
            System.out.println("TRANSACTION HISTORY:");
            for (String transaction : history) 
			{
                System.out.println(transaction);
            }
        }
    }
}

public class ATM_Interface1 
{
    public static void main(String[] args) 
	{
        ATM object3 = new ATM();
        object3.start();
    }
}
