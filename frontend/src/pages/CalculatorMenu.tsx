import Header from "../components/Header";

const CalculatorMenu = () => {
    return (
        <>
            <Header />
            <div className="calculator-menu">
                <h2>Select a Calculator</h2>
                <ul>
                    <li>
                        <a href="/calculators/savings">Compound Interest Calculator</a>
                    </li>
                    <li>
                        <a href="/calculators/compound-interest">Compound Interest Calculator</a>
                    </li>
                </ul>
            </div>
        </>
    );
};

export default CalculatorMenu;

