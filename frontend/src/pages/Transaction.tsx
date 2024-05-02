import Header from "../components/Header";
import TransactionList, { Transaction } from "../components/lists/TransactionList";

const Transactions = () => {
    return (
        <>
            <div className="bg-gray-50">
                <Header />
                <TransactionList period={""} onAddTransaction={function (newTransaction: Transaction): void {
                    throw new Error('Function not implemented.');
                } } />
            </div>
        </>
    )
};

export default Transactions;
