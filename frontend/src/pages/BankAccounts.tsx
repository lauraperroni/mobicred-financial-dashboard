import Accounts from "../components/lists/Accounts";
import Header from "../components/Header";
import TransactionLisNoEdit from "../components/lists/TransactionListNoEdit";

const BankAccounts = () => {

    return (
        <>
            <Header />
            <Accounts />
            <TransactionLisNoEdit period={""} />
        </>
    )
};

export default BankAccounts;
