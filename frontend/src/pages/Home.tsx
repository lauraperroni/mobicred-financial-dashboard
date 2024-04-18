import Header from "../components/Header";
import HelloPeriod from "../components/HelloPeriod";
import IncomeExpenseBalance from "../components/IncomeExpenseBalance";

const Home = () => {

    return (
        <>
        <div className="bg-gray-50">
            <Header />
            <HelloPeriod />
            <IncomeExpenseBalance />
            </div>
        </>
    )
};

export default Home;
