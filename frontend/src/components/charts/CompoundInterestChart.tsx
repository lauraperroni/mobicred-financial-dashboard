import React from 'react';
import { Bar } from 'react-chartjs-2';
import { ChartOptions } from 'chart.js';

interface CompoundInterestChartProps {
    totalInvestment: number;
    totalInterest: number;
    finalAmount: number;
}

const CompoundInterestChart: React.FC<CompoundInterestChartProps> = ({ totalInvestment, totalInterest, finalAmount }) => {
    const data = {
        labels: ['Total Investment', 'Total Interest', 'Final Amount'],
        datasets: [
            {
                label: 'Amount',
                backgroundColor: ['#3182CE', '#63B3ED', '#EBF4FF'],
                borderColor: '#4A90E2',
                borderWidth: 1,
                hoverBackgroundColor: ['#3182CE', '#63B3ED', '#EBF4FF'],
                hoverBorderColor: '#4A90E2',
                data: [totalInvestment, totalInterest, finalAmount]
            }
        ]
    };

    const options: ChartOptions<'bar'> = {
        maintainAspectRatio: false,
        scales: {
            y: {
                beginAtZero: true,
                ticks: {
                    callback: function (value: string | number) {
                        if (typeof value === 'number') {
                            return `$${value.toFixed(2)}`;
                        }
                        return value;
                    }
                }
            }
        }
    };

    return (
        <div className="shadow-lg rounded-lg overflow-hidden max-w-md w-full p-8 m-2 bg-white">
            <h2 className="text-lg font-semibold mb-4">Compound Interest Summary</h2>
            <Bar data={data} options={options} />
        </div>
    );
};

export default CompoundInterestChart;
