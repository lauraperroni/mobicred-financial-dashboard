import Header from "../components/Header";
import HelloPeriod from "../components/HelloPeriod";
import HomeChart from "../components/HomeChart";
import IncomeExpenseBalance from "../components/IncomeExpenseBalance";
import TransactionListNoEdit from "../components/lists/TransactionListNoEdit";
import HelloUser from "../components/titles/HelloUser";

const Home = () => {
    return (
        <div className="bg-gray-50 flex flex-col">
            <div>
                <Header />
                <HelloUser userName={"Laura"}/>
                <IncomeExpenseBalance />
            </div>
            <div className="flex mx-32"> {/* Aumentei o espaçamento horizontal para mx-16 */}
                <TransactionListNoEdit period={""} />
            </div>
        </div>
    )
};

export default Home;
