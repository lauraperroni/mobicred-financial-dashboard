import React, { useState, useEffect } from 'react';
import { BankAccountsService, postTransactions } from '../../services'; // Importando o serviço de contas bancárias
import { CategoryService } from '../../services/Category/CategoryService';

interface BankAccount {
    id: number;
    bankName: string;
}

interface Category {
    id: number;
    name: string;
}

interface Transaction {
    amount: number;
    date: string;
    type: 1 | 2;
    categoryId: number;
    bankAccountId: number;
    method: string;
    description: string;
}

interface AddTransactionModalProps {
    isOpen: boolean;
    onClose: () => void;
}

const AddTransactionModal: React.FC<AddTransactionModalProps> = ({ isOpen, onClose }) => {
    const [formData, setFormData] = useState<Transaction>({
        description: '',
        method: '',
        date: '',
        amount: 0,
        type: 1,
        categoryId: 0,
        bankAccountId: 0
    });

    const [bankAccounts, setBankAccounts] = useState<BankAccount[]>([]);
    const [categories, setCategories] = useState<Category[]>([]);

    useEffect(() => {
        const fetchAccounts = async () => {
            try {
                const response = await BankAccountsService.getBankAccounts();
                if (response && response.status === 200) {
                    setBankAccounts(response.data);
                } else {
                    console.error('Erro ao buscar contas bancárias:', response);
                }
            } catch (error) {
                console.error('Erro na requisição de contas bancárias:', error);
            }
        };

        const fetchCategories = async () => {
            try {
                const response = await CategoryService.getCategory();
                if (response && response.status === 200) {
                    setCategories(response.data);
                } else {
                    console.error('Erro ao buscar categorias:', response);
                }
            } catch (error) {
                console.error('Erro na requisição de categorias:', error);
            }
        };

        fetchAccounts();
        fetchCategories();
    }, []);

    const handleSubmit = async () => {
        const newTransaction: Transaction = {
            description: formData.description,
            method: formData.method,
            date: formData.date,
            amount: formData.amount,
            type: formData.type,
            categoryId: formData.categoryId,
            bankAccountId: formData.bankAccountId
        };

        // Emitir o evento para adicionar transação com o novo objeto de transação
        if (newTransaction.description && newTransaction.method && newTransaction.date && newTransaction.amount && newTransaction.type && newTransaction.categoryId && newTransaction.bankAccountId) {
            try {
                const response = await postTransactions(newTransaction);
                if (response && response.status === 200) {
                    console.log("Transaction added successfully:", response.data);
                    onClose(); // Fechar o modal após o sucesso da transação
                } else {
                    console.error("Error adding transaction:", response);
                }
            } catch (error) {
                console.error("Error adding transaction:", error);
            }
        } else {
            console.error("Form data is incomplete");
        }
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
                                onChange={(e) => setFormData({ ...formData, type: parseInt(e.target.value) as (1 | 2) })}
                                className="w-full border border-gray-300 rounded px-3 py-2"
                            >
                                <option value={2}>Expense</option>
                                <option value={1}>Credit</option>
                            </select>
                        </label>
                        <label className="block mb-2">
                            Category:
                            <select
                                value={formData.categoryId}
                                onChange={(e) => setFormData({ ...formData, categoryId: parseInt(e.target.value) })}
                                className="w-full border border-gray-300 rounded px-3 py-2"
                            >
                                <option value={0}>Select a category...</option>
                                {categories.map(category => (
                                    <option key={category.id} value={category.id}>{category.name}</option>
                                ))}
                            </select>
                        </label>
                        <label className="block mb-2">
                            Bank Account:
                            <select
                                value={formData.bankAccountId}
                                onChange={(e) => setFormData({ ...formData, bankAccountId: parseInt(e.target.value) })}
                                className="w-full border border-gray-300 rounded px-3 py-2"
                            >
                                <option value={0}>Select a bank account...</option>
                                {bankAccounts.map(account => (
                                    <option key={account.id} value={account.id}>{account.bankName}</option>
                                ))}
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
