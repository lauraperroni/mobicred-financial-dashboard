import React, { useState } from 'react';

// Definição da interface Transaction
interface Transaction {
    id: number;
    description: string;
    method: string;
    date: string;
    amount: number;
    type: 1 | 2; // Tipo definido como 1 (Expense) ou 2 (Credit)
}

// Definição do enum TransactionType
enum TransactionType {
    Expense = 1,
    Credit = 2,
}

interface AddTransactionModalProps {
    isOpen: boolean;
    onClose: () => void;
    onAddTransaction: (newTransaction: Transaction) => void;
}

// Interface para os dados do formulário de transação
interface TransactionFormData {
    description: string;
    method: string;
    date: string;
    amount: number; // Alterado para número
    type: string;
}


const AddTransactionModal: React.FC<AddTransactionModalProps> = ({ isOpen, onClose, onAddTransaction }) => {
    const [formData, setFormData] = useState<TransactionFormData>({
        description: '',
        method: '',
        date: '',
        amount: 0, // Inicializado como string vazia
        type: '1', // Default type
    });

    const handleSubmit = () => {
        console.log("Form submitted!");
        const newTransaction: Transaction = {
            id: Date.now(),
            description: formData.description,
            method: formData.method,
            date: formData.date,
            amount: formData.amount,
            type: parseInt(formData.type) as (1 | 2),
        };

        console.log("New transaction:", newTransaction);

        // Chama a função onAddTransaction com a nova transação
        onAddTransaction(newTransaction);
        // Limpa o formulário após a adição da transação
        setFormData({
            description: '',
            method: '',
            date: '',
            amount: 0,
            type: '1', // Resetando para o tipo padrão
        });

        // Fecha o modal após a adição da transação
        onClose();
    };
    return (
        <>
            {isOpen && (
                <div className="fixed top-0 left-0 z-50 flex items-center justify-center w-full h-full bg-gray-900 bg-opacity-50">
                    <div className="bg-white p-8 rounded-xl">
                        <h2 className="text-lg font-semibold mb-4">Add Transaction</h2>
                        <input
                            type="text"
                            placeholder="Description"
                            className="mb-2 w-full border border-gray-300 rounded px-3 py-2"
                            value={formData.description}
                            onChange={(e) => setFormData({ ...formData, description: e.target.value })}
                        />
                        <input
                            type="text"
                            placeholder="Method"
                            className="mb-2 w-full border border-gray-300 rounded px-3 py-2"
                            value={formData.method}
                            onChange={(e) => setFormData({ ...formData, method: e.target.value })}
                        />
                        <input
                            type="date"
                            placeholder="Date"
                            className="mb-2 w-full border border-gray-300 rounded px-3 py-2"
                            value={formData.date}
                            onChange={(e) => setFormData({ ...formData, date: e.target.value })}
                        />
                        <input
                            type="number"
                            placeholder="Amount"
                            className="mb-2 w-full border border-gray-300 rounded px-3 py-2"
                            value={formData.amount}
                            onChange={(e) => setFormData({ ...formData, amount: parseFloat(e.target.value) })}
                        />
                        <label className="block mb-2">
                            Type:
                            <select
                                value={formData.type}
                                onChange={(e) => setFormData({ ...formData, type: e.target.value })}
                                className="w-full border border-gray-300 rounded px-3 py-2"
                            >
                                <option value="1">Expense</option>
                                <option value="2">Credit</option>
                            </select>
                        </label>
                        <button onClick={handleSubmit} className="bg-green-500 text-white px-4 py-2 rounded-md mr-2">Add</button>
                        <button onClick={onClose} className="bg-gray-300 text-gray-800 px-4 py-2 rounded-md">Cancel</button>
                    </div>
                </div>
            )}
        </>
    );
};

export default AddTransactionModal;
