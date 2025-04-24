# Java Banking Application

## Description
The **Java Banking Application** is a fully-functional banking system built using **Java Swing** for the graphical user interface (GUI) and an **ArrayList** of user objects for data storage. The application offers a range of banking features such as user authentication, account management, transaction tracking, loan applications, transfer modules, and basic functionalities like deposit, withdraw, and more. It is designed to provide a realistic banking experience with both front-end and back-end integration, ensuring seamless and secure financial management.

### Key Features:
- **User Authentication**: Sign-up and login functionality, allowing users to securely access their banking accounts.
- **Account Management**: Users can manage their personal and account details, including balance,loans and transaction history.
- **Transaction History**: The system displays a detailed history of user transactions, including deposits, withdrawals,tranfers and balances.
- **Loan Applications**: Users can apply for loans, track the status of their applications, and can view loan details like the principal,interest,tenure,total amount payable .
- **Expense Manager** : Users can manage their montly expenditure based on certain limit that is initialised by the user at the starting of the month and has a great functionality that it will disable once the                            limit has been reached so that the further transactions cannot be made.
- **Real-time Updates**: The home page features a live clock for real-time updates and a dynamic user interface also there is a feature which should be updated later on such that the admin can approve th loan applications that are submitted with in 24 hours of being applied .
- 

## Project Structure
The project is divided into several key components:

- **Swing UI**: The graphical interface is designed using Java Swing components. Key panels include:

- **SIGNUPPANEL**: User sign-up and validation panel.
- **LOGINPANEL** : User login panel which allows the user to login to their account which is being created before.
- **HomePagePanel**: Displays the user's account info, live clock, and various banking actions.
- **VIEWACCOUNTDETAILSPANEL** : Displays the user's personal and banking information in a structured and read-only format for quick reference.
- **ApplyLoanPanel**: Loan application form to request loans and view loan statuses.
- **LoanStatusPanel**: Displays detailed information on user loan applications.
  

- **Backend Logic**: Includes classes to manage user accounts, bank accounts,bank transactions,personal information and loan applications. The system is designed with object-oriented principles using classes like `User`, `BankAccount`, `Transaction`, and `Loan`.

- **Data Management**: An in-memory ArrayList<User> is used to manage user information and transaction records, allowing the system to store and retrieve data during runtime without external database integration.
## Installation

### Prerequisites:
- **Java 8 or later**: Make sure you have Java installed on your system.


### Steps to Install:

1. **Clone the Repository**:
   ```bash
   git clone https://github.com/karthik768990/BANKING-SYSTEM-SIMULATOR.git
2.After the cloning has done,compile all the files in src folder using    javac *.java and then run the Main using **java Main**
