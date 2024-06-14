import Accounts from "../components/lists/Accounts";
import Header from "../components/Header";
import TransactionLisNoEdit from "../components/lists/TransactionListNoEdit";

const BankAccounts = () => {

    return (
        <>
            <div className="custom-bg flex flex-col">
                <Header />
                <Accounts />
            </div>
        </>
    )
};

export default BankAccounts;
