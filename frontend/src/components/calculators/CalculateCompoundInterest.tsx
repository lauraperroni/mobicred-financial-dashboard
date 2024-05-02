import React, { useState } from 'react';

const CalculateCompoundInterest: React.FC = () => {
    const [principal, setPrincipal] = useState<number>(0);
    const [interestRate, setInterestRate] = useState<number>(0);
    const [timePeriod, setTimePeriod] = useState<number>(0);
    const [totalAmount, setTotalAmount] = useState<number>(0);

    const handlePrincipalChange = (event: React.ChangeEvent<HTMLInputElement>) => {
        setPrincipal(parseFloat(event.target.value));
    };

    const handleInterestRateChange = (event: React.ChangeEvent<HTMLInputElement>) => {
        setInterestRate(parseFloat(event.target.value));
    };

    const handleTimePeriodChange = (event: React.ChangeEvent<HTMLInputElement>) => {
        setTimePeriod(parseFloat(event.target.value));
    };

    const calculateTotalAmount = () => {
        const rate = interestRate / 100;
        const amount = principal * Math.pow((1 + rate), timePeriod);
        setTotalAmount(amount);
    };

    return (
        <div className="flex justify-center items-center h-screen">
            <div className="shadow-lg rounded-lg overflow-hidden max-w-md w-full p-8">
                <h2 className="text-lg font-semibold mb-4">Compound Interest Calculator</h2>
                <div className="mb-4">
                    <label className="block mb-2">
                        Principal:
                        <input type="number" value={principal} onChange={handlePrincipalChange} className="w-full border border-gray-300 rounded px-3 py-2"/>
                    </label>
                </div>
                <div className="mb-4">
                    <label className="block mb-2">
                        Interest Rate (%):
                        <input type="number" value={interestRate} onChange={handleInterestRateChange} className="w-full border border-gray-300 rounded px-3 py-2"/>
                    </label>
                </div>
                <div className="mb-4">
                    <label className="block mb-2">
                        Time Period (years):
                        <input type="number" value={timePeriod} onChange={handleTimePeriodChange} className="w-full border border-gray-300 rounded px-3 py-2"/>
                    </label>
                </div>
                <button onClick={calculateTotalAmount} className="bg-green-500 hover:bg-green-600 text-white px-4 py-2 rounded-md mr-2">Calculate</button>
                {totalAmount > 0 && <p>Total amount after {timePeriod} years: ${totalAmount.toFixed(2)}</p>}
            </div>
        </div>
    );
};

export default CalculateCompoundInterest;
