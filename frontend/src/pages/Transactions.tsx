import Header from "../components/Header";
import HelloPeriod from "../components/HelloPeriod";
import IncomeExpenseBalanceButtons from "../components/buttons/IncomeExpenseBalanceButtons";
import TransactionList, { Transaction } from "../components/lists/TransactionList";

const Transactions = () => {
    return (
        <>
            <div className="bg-gray-50">
                <Header />
                <HelloPeriod handlePeriodChange={function (): void {
                    throw new Error("Function not implemented.");
                }} />
                <IncomeExpenseBalanceButtons />
                <TransactionList period={""} onAddTransaction={function (newTransaction: Transaction): void {
                    throw new Error('Function not implemented.');
                } } />
            </div>
        </>
    )
};

export default Transactions;
