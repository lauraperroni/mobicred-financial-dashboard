import React, { useState } from "react";
import { Transaction } from "./TransactionList";

interface MoneyCardProps {
    title: string;
    amount: number; // Alterado para número
    percentage: string;
    imageSrc: string;
    altText: string;
}

function MoneyCard({
    title,
    amount,
    percentage,
    imageSrc,
    altText,
}: MoneyCardProps) {
    return (
        <div className="flex flex-col w-full max-w-xs">
            <div className="flex flex-col grow p-4 mx-auto w-full whitespace-nowrap bg-white rounded-xl border border-gray-100 border-solid">
                <div className="text-sm font-medium leading-5 text-slate-600">
                    {title}
                </div>
                <div className="flex gap-2 mt-2">
                    <div className="text- font-semibold tracking-tighter leading-8 text-zinc-800">
                        ${amount.toFixed(2)}
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
    type: "income" | "expense" | "transfer";
    addTransaction: (transaction: Transaction) => void;
}

function ActionButton({
    onClick,
    imageSrc,
    altText,
    title,
    description,
    type,
    addTransaction,
}: ActionButtonProps) {
    const [isHovered, setIsHovered] = useState(false);

    return (
        <button
            className={`flex flex-1 p-3 bg-white rounded-xl border border-gray-100 border-solid max-w-xs ${isHovered ? "shadow-md" : ""
                }`}
            onMouseEnter={() => setIsHovered(true)}
            onMouseLeave={() => setIsHovered(false)}
            onClick={() => {
                onClick();
            }}
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
    const [showModal, setShowModal] = useState<boolean>(false);
    const [selectedType, setSelectedType] = useState<"income" | "expense" | "transfer">("income");
    const [transactions, setTransactions] = useState<Transaction[]>([]);
    const [formData, setFormData] = useState<Transaction>({
        id: 0,
        description: "",
        method: "",
        date: "",
        amount: 0,
        type: 1,
    });

    const addTransaction = () => {
        console.log("Adding transaction:", formData);
        setTransactions([...transactions, formData]);
        setShowModal(false);
    };

    const openModal = (type: "income" | "expense" | "transfer") => {
        setShowModal(true);
        setSelectedType(type);
        setFormData({ ...formData, type: type === "income" ? 1 : type === "expense" ? 2 : 1 });
    };

    return (
        <div className="flex flex-col w-full justify-center max-md:flex-row">
            <div className="flex flex-wrap gap-5 max-md:flex-nowrap max-md:w-full md:p-2 justify-center">
                <MoneyCard
                    title="Balance"
                    amount={5502.45}
                    percentage="12,5%"
                    imageSrc="https://cdn.builder.io/api/v1/image/assets/TEMP/dafa6cc42fc2797fdbe88d07494905f335c4ad9cedf460bf9f06d8a5505ad602?"
                    altText="Percentage Icon"
                />
                <MoneyCard
                    title="Incomes"
                    amount={9450.00}
                    percentage="27%"
                    imageSrc="https://cdn.builder.io/api/v1/image/assets/TEMP/dafa6cc42fc2797fdbe88d07494905f335c4ad9cedf460bf9f06d8a5505ad602?"
                    altText="Percentage Icon"
                />
                <MoneyCard
                    title="Expenses"
                    amount={3945.55}
                    percentage="-15%"
                    imageSrc="https://cdn.builder.io/api/v1/image/assets/TEMP/1c6d15ee7f1e2f87dd26fb18555cd52a4e74b03d1221ae1b519f3e8ee27e7cad?"
                    altText="Percentage Icon"
                />
            </div>
            <div className="flex gap-5 max-md:flex-wrap max-md:w-full justify-center m-p">
                <ActionButton
                    onClick={() => openModal("income")}
                    imageSrc="https://cdn.builder.io/api/v1/image/assets/TEMP/b4703860a96b3754a5143af34fbfd0f493b1d74d0e9f54492ee549672e324de1?"
                    altText="Add Income Icon"
                    title="Add income"
                    description="Create an income manually"
                    type="income"
                    addTransaction={addTransaction}
                />
                <ActionButton
                    onClick={() => openModal("expense")}
                    imageSrc="https://cdn.builder.io/api/v1/image/assets/TEMP/a6c2d3bb83a00fa5a280517fa3980310a5d43195c0ae3ec57b0dbcdfdeb45dcb?"
                    altText="Add Expense Icon"
                    title="Add expense"
                    description="Create an expense manually"
                    type="expense"
                    addTransaction={addTransaction}
                />
                <ActionButton
                    onClick={() => openModal("transfer")}
                    imageSrc="https://cdn.builder.io/api/v1/image/assets/TEMP/c0312c5478e39c646b9785e3f7b909998eadcf22f5263aba14884e6d00511926?"
                    altText="Log Transfer Icon"
                    title="Log a money transfer"
                    description="Select the amount and make a transfer"
                    type="transfer"
                    addTransaction={addTransaction}
                />
            </div>

            
            {showModal && (
                <div className="fixed top-0 left-0 z-50 flex items-center justify-center w-full h-full bg-gray-900 bg-opacity-50">
                    <div className="bg-white p-8 rounded-xl">
                        <h2 className="text-lg font-semibold mb-4">Add {selectedType}</h2>
                        <input
                            type="text"
                            placeholder="Description"
                            className="mb-2 w-full border border-gray-300 rounded px-3 py-2"
                            onChange={(e) => setFormData({ ...formData, description: e.target.value })}
                        />
                        <input
                            type="text"
                            placeholder="Method"
                            className="mb-2 w-full border border-gray-300 rounded px-3 py-2"
                            onChange={(e) => setFormData({ ...formData, method: e.target.value })}
                        />
                        <input
                            type="date"
                            placeholder="Date"
                            className="mb-2 w-full border border-gray-300 rounded px-3 py-2"
                            onChange={(e) => setFormData({ ...formData, date: e.target.value })}
                        />
                        <input
                            type="number"
                            placeholder="Amount"
                            className="mb-4 w-full border border-gray-300 rounded px-3 py-2"
                            onChange={(e) => setFormData({ ...formData, amount: parseFloat(e.target.value) })}
                        />
                        <label className="block mb-2">
                            Type:
                            <select
                                value={formData.type}
                                onChange={(e) => setFormData({ ...formData, type: parseInt(e.target.value) as (1 | 2) })}
                                className="w-full border border-gray-300 rounded px-3 py-2"
                            >
                                <option value={1}>Income</option>
                                <option value={2}>Expense</option>
                                <option value={3}>Transfer</option>
                            </select>
                        </label>
                        <button
                            onClick={addTransaction}
                            className="bg-green-500 text-white px-4 py-2 rounded-md mr-2"
                        >
                            Add
                        </button>
                        <button onClick={() => setShowModal(false)} className="bg-gray-300 text-gray-800 px-4 py-2 rounded-md">Cancel</button>
                    </div>
                </div>
            )}
        </div>
    );
}

export default IncomeExpenseBalanceButtons;
