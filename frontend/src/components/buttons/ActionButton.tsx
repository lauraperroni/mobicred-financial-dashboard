import React from "react";
import { Transaction } from "../lists/TransactionList";

interface ActionButtonProps {
    onClick: () => void;
    imageSrc: string;
    altText: string;
    title: string;
    description: string;
    type: "income" | "expense" | "transfer";
    addTransaction: (transaction: Transaction) => void;
    formData: Transaction; // Adiciona formData como prop
    setFormData: React.Dispatch<React.SetStateAction<Transaction>>; // Adiciona setFormData como prop
}

const ActionButton: React.FC<ActionButtonProps> = ({
    onClick,
    imageSrc,
    altText,
    title,
    description,
    type,
    addTransaction,
    formData, // Recebe formData como prop
    setFormData, // Recebe setFormData como prop
}) => {
    const handleClick = () => {
        // Adiciona o tipo correto ao formData antes de adicionar a transação
        setFormData({ ...formData, type: type === "income" ? 1 : type === "expense" ? 2 : 1 });
        addTransaction(formData);
        onClick();
    };

    return (
        <button
            className="flex flex-1 p-3 bg-white rounded-xl border border-gray-100 border-solid max-w-xs"
            onClick={handleClick} // Usa handleClick ao invés de onClick diretamente
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
};

export default ActionButton;
