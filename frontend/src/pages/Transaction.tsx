import Header from "../components/Header";
import TransactionList from "../components/lists/TransactionList";

const Transactions = () => {
    return (
        <>
            <div className="custom-bg">
                <Header />
                <TransactionList onAddTransaction={function (): void {
                    throw new Error('Function not implemented.');
                } } />
            </div>
        </>
    )
};

export default Transactions;
