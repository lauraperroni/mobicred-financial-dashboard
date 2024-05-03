import Header from "../components/Header";

const CalculatorMenu = () => {
    return (
        <>
            <Header />
            <div className="text-2xl mb-4 justify-center justify-items-center">
                <h2 className="text-2xl mb-4 m-4 text-center">Select a Calculator</h2>
            </div>
            <div className="calculator-menu text-center">
                <div className="grid-cols-2 gap-4 flex justify-center">
                    <a href="/calculators/savings" className="bg-green-500 hover:bg-green-600 text-white font-bold py-2 px-2 max-w-100 w-72 rounded">Savings</a>
                    <a href="/calculators/compound-interest" className="bg-green-500 hover:bg-green-600 text-white font-bold w-72 max-w-100 py-2 px-2 rounded">Compound Interest Calculator</a>
                </div>
            </div>
        </>
    );
};

export default CalculatorMenu;

