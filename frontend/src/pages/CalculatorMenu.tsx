import { Link } from "react-router-dom";
import Header from "../components/Header";
import SavingsIcon from "../assets/savings.png";
import InterestIcon from "../assets/interest.png";

const CalculatorMenu = () => {
    return (
        <div className="custom-bg flex flex-col min-h-screen">
            <Header />
            <div className="text-2xl mb-24 justify-center justify-items-center">
            </div>
            <div className="calculator-menu text-center">
                <div className="grid-cols-2 gap-4 flex justify-center">
                    <Link to="/calculators/savings" className="flex flex-col items-center bg-green-500 hover:bg-green-600 text-white font-bold py-3 px-4 max-w-xl w-full rounded-lg shadow-md">
                        <div className="mb-2">
                            <img src={SavingsIcon} alt="Savings Icon" className="h-32" />
                        </div>
                        <span className="mb-1 text-lg">Savings Calculator</span>
                        <p className="text-sm text-white text-center font-normal">Calculate your savings based on monthly contributions.</p>
                    </Link>
                    <Link to="/calculators/compound-interest" className="flex flex-col items-center bg-green-500 hover:bg-green-600 text-white font-bold py-3 px-4 max-w-xl w-full rounded-lg shadow-md">
                        <div className="mb-2">
                            <img src={InterestIcon} alt="Interest Icon" className="h-32" />
                        </div>
                        <span className="mb-1 text-lg">Compound Interest Calculator</span>
                        <p className="text-sm text-white text-center font-normal">Estimate compound interest earnings over time.</p>
                    </Link>
                </div>
            </div>
        </div>
    );
};

export default CalculatorMenu;
