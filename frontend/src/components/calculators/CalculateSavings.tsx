import React, { useState } from 'react';

const SavingsCalculator = () => {
    const [monthlySavings, setMonthlySavings] = useState<number>(0);
    const [timePeriod, setTimePeriod] = useState<number>(0);
    const [timeUnit, setTimeUnit] = useState<'months' | 'years'>('years');
    const [totalSavings, setTotalSavings] = useState<number>(0);

    const handleMonthlySavingsChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        setMonthlySavings(parseFloat(e.target.value));
    };

    const handleTimePeriodChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        setTimePeriod(parseInt(e.target.value));
    };
    const calculateTotalSavings = () => {
        let total = 0;
        if (timeUnit === 'years') {
            for (let i = 0; i < timePeriod; i++) {
                total += monthlySavings * 12;
            }
        } else {
            total = monthlySavings * timePeriod;
        }
        setTotalSavings(total);
    };


    return (
        <div className="flex justify-center items-center">
            <div className="flex flex-col justify-around shadow-lg rounded-lg overflow-hidden max-w-md w-full p-8 m-4">
                <h2 className="text-lg font-semibold mb-4">Savings Calculator</h2>
                <div className="mb-4">
                    <label className="block mb-2">
                        Monthly Savings:
                        <input type="number" placeholder="Enter monthly savings" value={monthlySavings} onChange={handleMonthlySavingsChange} className="w-full border border-gray-300 rounded px-3 py-2" />
                    </label>
                </div>
                <div className="mb-4">
                    <label className="block mb-2">
                        Time Period:
                        <input type="number" placeholder="Enter time period" value={timePeriod} onChange={handleTimePeriodChange} className="w-full border border-gray-300 rounded px-3 py-2" />
                    </label>
                    <select value={timeUnit} onChange={(e) => setTimeUnit(e.target.value as 'months' | 'years')} className="w-full border border-gray-300 rounded px-3 py-2">
                        <option value="months">Months</option>
                        <option value="years">Years</option>
                    </select>
                </div>
                <button onClick={calculateTotalSavings} className="bg-green-500 hover:bg-green-600 text-white px-4 py-2 rounded-md mr-2">Calculate</button>
                {totalSavings > 0 && (
                    <p className="text-gray-800 m-2 p-4">Total savings after {timePeriod} {timeUnit}: ${totalSavings.toFixed(2)}</p>
                )}
            </div>
        </div>
    );
};

export default SavingsCalculator;
