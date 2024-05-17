import React from "react";

interface MoneyCardProps {
    title: string;
    amount: number;
    icon: JSX.Element; // Tipo para o ícone
}

const MoneyCard: React.FC<MoneyCardProps> = ({ title, amount, icon }) => (
    <div className="flex flex-col w-full max-w-xs">
        <div className="flex flex-col grow p-4 mx-auto w-full whitespace-nowrap bg-white rounded-xl border border-gray-100 border-solid shadow-sm">
            <div className="flex items-center justify-between mb-2">
                <div className="text-sm font-medium leading-5 text-slate-600">{title}</div>
                {icon}
            </div>
            <div className="flex gap-2 mt-2">
                <div className="text- font-semibold tracking-tighter leading-8 text-zinc-800">
                    ${amount.toFixed(2)}
                </div>
            </div>
        </div>
    </div>
);

const IncomeExpenseBalance: React.FC = () => {
    const moneyCards: MoneyCardProps[] = [
        {
            title: "Balance",
            amount: 5502.45,
            icon: <svg xmlns="http://www.w3.org/2000/svg" className="h-6 w-6 text-blue-500" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                <path strokeLinecap="round" strokeLinejoin="round" strokeWidth={2} d="M12 6v6m0 0v6m0-6h6m-6 0H6" />
            </svg>,
        },
        {
            title: "Incomes",
            amount: 9450.00,
            icon: <svg xmlns="http://www.w3.org/2000/svg" className="h-6 w-6 text-green-500" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                <path strokeLinecap="round" strokeLinejoin="round" strokeWidth={2} d="M12 6v6m0 0v6m0-6h6m-6 0H6" />
            </svg>,
        },
        {
            title: "Expenses",
            amount: 3945.55,
            icon: <svg xmlns="http://www.w3.org/2000/svg" className="h-6 w-6 text-red-500" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                <path strokeLinecap="round" strokeLinejoin="round" strokeWidth={2} d="M19 14.828V8a2 2 0 0-2-2H7a2 2 0 0-2 2v6.828a4 4 0 0 0 1.474 3.101l4.526 3.016a2 2 0 0 0 2.948 0l4.526-3.016A4 4 0 0 0 19 14.828z" />
                <path strokeLinecap="round" strokeLinejoin="round" strokeWidth={2} d="M10 12l4-4m0 0l4 4m-4-4v9" />
            </svg>,
        }
    ];

    return (
        <div className="flex flex-col w-full justify-center max-md:flex-row">
            <div className="flex flex-wrap gap-5 max-md:flex-nowrap max-md:w-full md:p-2 justify-center">
                {moneyCards.map((card, index) => (
                    <MoneyCard key={index} {...card} />
                ))}
            </div>
        </div>
    );
};

export default IncomeExpenseBalance;
