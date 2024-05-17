import React, { useState } from 'react';

const CalculateCompoundInterest: React.FC = () => {
    const [initialInvestment, setInitialInvestment] = useState<number>(0);
    const [monthlyInvestment, setMonthlyInvestment] = useState<number>(0);
    const [months, setMonths] = useState<number>(0);
    const [interestRate, setInterestRate] = useState<number>(0);
    const [totalInvestment, setTotalInvestment] = useState<number>(0);
    const [totalInterest, setTotalInterest] = useState<number>(0);
    const [finalAmount, setFinalAmount] = useState<number>(0);

    const calculateCompoundInterest = () => {
        const initialInvestmentFloat = parseFloat(initialInvestment.toString());
        const monthlyInvestmentFloat = parseFloat(monthlyInvestment.toString());
        const monthsInt = parseInt(months.toString());
        const interestRateFloat = parseFloat(interestRate.toString());

        let investmentAccumulated = initialInvestmentFloat;
        let totalInterestAccumulated = 0;

        for (let i = 0; i < monthsInt; i++) {
            const monthlyInterest = (investmentAccumulated + interestRateFloat) / 100;
            totalInterestAccumulated += monthlyInterest;
            investmentAccumulated += monthlyInterest + monthlyInvestmentFloat;
        }

        const totalInvestmentAmount = initialInvestmentFloat + monthlyInvestmentFloat * monthsInt;
        const finalAmountFloat = totalInvestmentAmount + totalInterestAccumulated;

        setTotalInvestment(totalInvestmentAmount);
        setTotalInterest(totalInterestAccumulated);
        setFinalAmount(finalAmountFloat);
    };

    return (
        <div className="flex justify-center">
            <div className="shadow-lg rounded-lg overflow-hidden max-w-md w-full p-8 m-4 bg-white">
                <h2 className="text-lg font-semibold mb-4">Calculate Compound Interest</h2>
                <label className="block mb-2">
                    Initial Investment:
                    <input type="number" value={initialInvestment} onChange={(e) => setInitialInvestment(parseFloat(e.target.value))} className="w-full border border-gray-300 rounded px-3 py-2" />
                </label>
                <label className="block mb-2">
                    Monthly Investment:
                    <input type="number" value={monthlyInvestment} onChange={(e) => setMonthlyInvestment(parseFloat(e.target.value))} className="w-full border border-gray-300 rounded px-3 py-2" />
                </label>
                <label className="block mb-2">
                    Time Period:
                    <input type="number" value={months} onChange={(e) => setMonths(parseInt(e.target.value))} className="w-full border border-gray-300 rounded px-3 py-2" />
                </label>
                <label className="block mb-2">
                    Interest Rate:
                    <input type="number" step="0.01" value={interestRate} onChange={(e) => setInterestRate(parseFloat(e.target.value))} className="w-full border border-gray-300 rounded px-3 py-2" />
                </label>
                <button onClick={calculateCompoundInterest} className="bg-green-500 hover:bg-green-600 text-white px-4 py-2 rounded-md mr-2">Calculate</button>
            </div>
            <div className="shadow-lg rounded-lg overflow-hidden max-w-md w-full p-8 m-2 bg-white">
                <div className="text-gray-800 m-2 p-4">
                    <p>Initial Investment: <strong>${initialInvestment.toFixed(2)}</strong></p>
                    <p>Monthly Investment: <strong>${monthlyInvestment.toFixed(2)}</strong></p>
                    <p>Time Period: <strong>{months} months</strong></p>
                    <p>Total Investment: <strong>${totalInvestment.toFixed(2)}</strong></p>
                    <p>Total Interest: <strong>${totalInterest.toFixed(2)}</strong></p>
                    <p>Final Amount: <strong>${finalAmount.toFixed(2)}</strong></p>
                </div>
            </div>
        </div>
    );
};

export default CalculateCompoundInterest;
