interface MoneyCardProps {
    title: string;
    amount: string;
    percentage: string;
    imageSrc: string;
    altText: string;
}

function MoneyCard({
    title,
    amount,
    percentage,
    imageSrc,
    altText
}: MoneyCardProps) {
    return (
        <div className="flex flex-col w-full max-w-xs">
            <div className="flex flex-col grow p-4 mx-auto w-full whitespace-nowrap bg-white rounded-xl border border-gray-100 border-solid shadow-sm">
                <div className="text-sm font-medium leading-5 text-slate-600">
                    {title}
                </div>
                <div className="flex gap-2 mt-2">
                    <div className="text- font-semibold tracking-tighter leading-8 text-zinc-800">
                        ${amount}
                    </div>
                    <div className="flex items-center gap-1 py-0.5 px-2 text-xs font-medium leading-4 text-center bg-white rounded-md border border-gray-300 border-solid text-zinc-800">
                        <img
                            loading="lazy"
                            src={imageSrc}
                            className="shrink-0 w-2.5 h-2.5"
                            alt={altText}
                        />
                        <div>{percentage}</div>
                    </div>
                </div>
            </div>
        </div>
    );
}

function IncomeExpenseBalance() {
    const moneyCards: MoneyCardProps[] = [
        {
            title: "Balance",
            amount: "5,502.45",
            percentage: "12,5%",
            imageSrc: "https://cdn.builder.io/api/v1/image/assets/TEMP/dafa6cc42fc2797fdbe88d07494905f335c4ad9cedf460bf9f06d8a5505ad602?",
            altText: "Percentage Icon"
        },
        {
            title: "Incomes",
            amount: "9,450.00",
            percentage: "27%",
            imageSrc: "https://cdn.builder.io/api/v1/image/assets/TEMP/dafa6cc42fc2797fdbe88d07494905f335c4ad9cedf460bf9f06d8a5505ad602?",
            altText: "Percentage Icon"
        },
        {
            title: "Expenses",
            amount: "3,945.55",
            percentage: "-15%",
            imageSrc: "https://cdn.builder.io/api/v1/image/assets/TEMP/1c6d15ee7f1e2f87dd26fb18555cd52a4e74b03d1221ae1b519f3e8ee27e7cad?",
            altText: "Percentage Icon"
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
}

export default IncomeExpenseBalance;
