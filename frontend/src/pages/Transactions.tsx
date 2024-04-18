import Header from "../components/Header";
import HelloPeriod from "../components/HelloPeriod";
import IncomeExpenseBalanceButtons from "../components/IncomeExpenseBalanceButtons";

const Transactions = () => {

    return (
        <>
        <div className="bg-gray-50">
            <Header />
            <HelloPeriod />
            <IncomeExpenseBalanceButtons />
            </div>
        </>
    )
};

export default Transactions;
