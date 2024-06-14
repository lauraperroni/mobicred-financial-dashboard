import Header from "../components/Header";
import HelloPeriod from "../components/HelloPeriod";
import IncomeExpenseBalanceButtons from "../components/buttons/IncomeExpenseBalanceButtons";
import TransactionList from "../components/lists/TransactionList";

const Transactions = () => (
    <>
        <div className="custom-bg bg-gray-50">
            <Header />
            <HelloPeriod handlePeriodChange={function (): void {
                throw new Error("Function not implemented.");
            } } />
            <IncomeExpenseBalanceButtons />
            <TransactionList onAddTransaction={function (): void {
                throw new Error('Function not implemented.');
            } } />
        </div>
    </>
);

export default Transactions;
