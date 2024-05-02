import Header from "../components/Header";
import HelloPeriod from "../components/HelloPeriod";
import HomeChart from "../components/HomeChart";
import IncomeExpenseBalance from "../components/IncomeExpenseBalance";
import TransactionListNoEdit from "../components/lists/TransactionListNoEdit";

const Home = () => {
    return (
        <div className="bg-gray-50 flex flex-col">
            <div>
                <Header />
                <HelloPeriod handlePeriodChange={function (period: string): void {
                    throw new Error("Function not implemented.");
                }} />
                <IncomeExpenseBalance />
            </div>
            <div className="flex mx-32"> {/* Aumentei o espaçamento horizontal para mx-16 */}
                <HomeChart />
                <TransactionListNoEdit period={""} />
            </div>
        </div>
    )
};

export default Home;
