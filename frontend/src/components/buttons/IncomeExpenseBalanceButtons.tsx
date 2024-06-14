import React, { useState } from "react";
import ActionButton from "./ActionButton";
import AddTransactionModal from "../cards/AddTransactionModal";

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

    const addTransaction = (transaction: Transaction) => {
        console.log("Adding transaction:", transaction);
        setTransactions([...transactions, transaction]);
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
                    formData={formData} // Adiciona formData como prop
                    setFormData={setFormData} // Adiciona setFormData como prop
                />
                <ActionButton
                    onClick={() => openModal("expense")}
                    imageSrc="https://cdn.builder.io/api/v1/image/assets/TEMP/a6c2d3bb83a00fa5a280517fa3980310a5d43195c0ae3ec57b0dbcdfdeb45dcb?"
                    altText="Add Expense Icon"
                    title="Add expense"
                    description="Create an expense manually"
                    type="expense"
                    addTransaction={addTransaction}
                    formData={formData} // Adiciona formData como prop
                    setFormData={setFormData} // Adiciona setFormData como prop
                />
                <ActionButton
                    onClick={() => openModal("transfer")}
                    imageSrc="https://cdn.builder.io/api/v1/image/assets/TEMP/c0312c5478e39c646b9785e3f7b909998eadcf22f5263aba14884e6d00511926?"
                    altText="Log Transfer Icon"
                    title="Log a money transfer"
                    description="Select the amount and make a transfer"
                    type="transfer"
                    addTransaction={addTransaction}
                    formData={formData} // Adiciona formData como prop
                    setFormData={setFormData} // Adiciona setFormData como prop
                />
            </div>


            {showModal && (
                <AddTransactionModal
                    isOpen={showModal}
                    onClose={() => setShowModal(false)}
                    onAddTransaction={addTransaction} // Passa a função de adição de transação como prop
                />
            )}
        </div>
    );
}

export default IncomeExpenseBalanceButtons;
