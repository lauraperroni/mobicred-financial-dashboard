import Header from "../components/Header";
import IncomeExpenseBalance from "../components/IncomeExpenseBalance";
import TransactionListNoEdit from "../components/lists/TransactionListNoEdit";
import HelloUser from "../components/titles/HelloUser";

const Home = () => {
    return (
        <div className="custom-bg bg-gray-50 flex flex-col">
            <div>
                <Header />
                <HelloUser userName={"Laura"} />
                <IncomeExpenseBalance />
            </div>
            <div className="flex mx-32">
                <TransactionListNoEdit period={""} />
            </div>
        </div>
    )
};

export default Home;
