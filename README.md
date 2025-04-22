# Java Banking Application

## Description
The **Java Banking Application** is a fully-functional banking system built using **Java Swing** for the graphical user interface (GUI) and an **ArrayList** of user objects for data storage. The application offers a range of banking features such as user authentication, account management, transaction tracking, loan applications, transfer modules, and basic functionalities like deposit, withdraw, and more. It is designed to provide a realistic banking experience with both front-end and back-end integration, ensuring seamless and secure financial management.

### Key Features:
- **User Authentication**: Sign-up and login functionality, allowing users to securely access their banking accounts.
- **Account Management**: Users can manage their personal and account details, including balance,loans and transaction history.
- **Transaction History**: The system displays a detailed history of user transactions, including deposits, withdrawals,tranfers and balances.
- **Loan Applications**: Users can apply for loans, track the status of their applications, and can view loan details like the principal,interest,tenure,total                               amount payable .
- **Expense Manager** : Users can manage their montly expenditure based on certain limit that is initialised by the user at the starting of the month and has a                             great functionality that it will disable once the limit has been reached so that the further transactions cannot be made.
- **Real-time Updates**: The home page features a live clock for real-time updates and a dynamic user interface.

## Project Structure
The project is divided into several key components:

- **Swing UI**: The graphical interface is designed using Java Swing components. Key panels include:
- **HomePagePanel**: Displays the user's account info, live clock, and various banking actions.
- **SIGNUPPANEL**: User sign-up and validation panel.

- **ApplyLoanPanel**: Loan application form to request loans and view loan statuses.
- **LoanStatusPanel**: Displays detailed information on user loan applications.

- **Backend Logic**: Includes classes to manage user accounts, bank transactions, and loan applications. The system is designed with object-oriented principles using classes like `User`, `BankAccount`, `Transaction`, and `Loan`.

- **Database Integration**: **JDBC** is used for database integration, allowing the system to persist user information and transaction records. The system interacts with an SQL database to store and retrieve data securely.

## Installation

### Prerequisites:
- **Java 8 or later**: Make sure you have Java installed on your system.
- **Database Setup**: The application uses a MySQL database for storing user and transaction data. You need to set up the database and configure JDBC.

### Steps to Install:

1. **Clone the Repository**:
   ```bash
   git clone https://github.com/username/banking-application.git
