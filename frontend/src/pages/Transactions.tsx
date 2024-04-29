import Header from "../components/Header";
import HelloPeriod from "../components/HelloPeriod";
import IncomeExpenseBalanceButtons from "../components/IncomeExpenseBalanceButtons";
import TransactionList from "../components/TransactionList";

const Transactions = () => {

    return (
        <>
        <div className="bg-gray-50">
            <Header />
            <HelloPeriod handlePeriodChange={function (period: string): void {
                    throw new Error("Function not implemented.");
                } } />
            <IncomeExpenseBalanceButtons />
            <TransactionList period={""} />
            </div>
        </>
    )
};

export default Transactions;
