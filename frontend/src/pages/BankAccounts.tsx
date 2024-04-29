import Accounts from "../components/Accounts";
import Header from "../components/Header";
import TransactionList from "../components/TransactionList";

const BankAccounts = () => {

    return (
        <>
            <Header />
            <Accounts />
            <div>
                <TransactionList period={''} />
            </div>

        </>
    )
};

export default BankAccounts;
