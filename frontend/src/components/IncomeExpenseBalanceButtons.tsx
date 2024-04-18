import { useState } from "react";

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
interface ActionButtonProps {
    onClick: () => void;
    imageSrc: string;
    altText: string;
    title: string;
    description: string;
}

function ActionButton({
    onClick,
    imageSrc,
    altText,
    title,
    description
}: ActionButtonProps) {
    const [isHovered, setIsHovered] = useState(false);

    return (
        <button
            className={`flex flex-1 p-3 bg-white rounded-xl border border-gray-100 border-solid max-w-xs transition-shadow ${isHovered ? 'shadow-md' : ''}`}
            onMouseEnter={() => setIsHovered(true)}
            onMouseLeave={() => setIsHovered(false)}
            onClick={onClick}
        >
            <img
                loading="lazy"
                src={imageSrc}
                className="shrink-0 w-8 h-8"
                alt={altText}
            />
            <div className="flex flex-col flex-1 self-center ml-2">
                <div className="text-base font-semibold leading-6 text-gray-700">
                    {title}
                </div>
                <div className="text-sm leading-5 text-ellipsis text-slate-600">
                    {description}
                </div>
            </div>
        </button>
    );
}

function IncomeExpenseBalanceButtons() {
    return (
        <div className="flex flex-col w-full justify-center max-md:flex-row">
            <div className="flex flex-wrap gap-5 max-md:flex-nowrap max-md:w-full md:p-2 justify-center">
                <MoneyCard
                    title="Balance"
                    amount="5,502.45"
                    percentage="12,5%"
                    imageSrc="https://cdn.builder.io/api/v1/image/assets/TEMP/dafa6cc42fc2797fdbe88d07494905f335c4ad9cedf460bf9f06d8a5505ad602?"
                    altText="Percentage Icon"
                />
                <MoneyCard
                    title="Incomes"
                    amount="9,450.00"
                    percentage="27%"
                    imageSrc="https://cdn.builder.io/api/v1/image/assets/TEMP/dafa6cc42fc2797fdbe88d07494905f335c4ad9cedf460bf9f06d8a5505ad602?"
                    altText="Percentage Icon"
                />
                <MoneyCard
                    title="Expenses"
                    amount="3,945.55"
                    percentage="-15%"
                    imageSrc="https://cdn.builder.io/api/v1/image/assets/TEMP/1c6d15ee7f1e2f87dd26fb18555cd52a4e74b03d1221ae1b519f3e8ee27e7cad?"
                    altText="Percentage Icon"
                />
            </div>
            <div className="flex gap-5 max-md:flex-wrap max-md:w-full justify-center shadow-sm m-p">
                <ActionButton
                    onClick={() => console.log("Add income clicked")}
                    imageSrc="https://cdn.builder.io/api/v1/image/assets/TEMP/b4703860a96b3754a5143af34fbfd0f493b1d74d0e9f54492ee549672e324de1?"
                    altText="Add Income Icon"
                    title="Add income"
                    description="Create an income manually"
                />
                <ActionButton
                    onClick={() => console.log("Add expense clicked")}
                    imageSrc="https://cdn.builder.io/api/v1/image/assets/TEMP/a6c2d3bb83a00fa5a280517fa3980310a5d43195c0ae3ec57b0dbcdfdeb45dcb?"
                    altText="Add Expense Icon"
                    title="Add expense"
                    description="Create an expense manually"
                />
                <ActionButton
                    onClick={() => console.log("Log transfer clicked")}
                    imageSrc="https://cdn.builder.io/api/v1/image/assets/TEMP/c0312c5478e39c646b9785e3f7b909998eadcf22f5263aba14884e6d00511926?"
                    altText="Log Transfer Icon"
                    title="Log a money transfer"
                    description="Select the amount and make a transfer"
                />
            </div>
        </div>
    );
}

export default IncomeExpenseBalanceButtons;
