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
        amount: -1, // Definindo inicialmente como null
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
                    console.error('Erro ao buscar transações:', response);
                }
            } catch (error) {
                console.error('Erro na requisição de transações:', error);
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
                <div className="fixed top-0 left-0 flex items-center justify-center w-full h-full bg-gray-900 bg-opacity-50 z-50">
                    <div className="bg-gray-50 shadow-lg rounded-lg overflow-hidden max-w-xl w-4/5 m-4 z-50">
                        <div className="bg-green-500 text-white px-4 py-2">
                            <h2 className="text-xl font-semibold">Post new transaction</h2>
                        </div>
                        <div className="p-4">
                            <form onSubmit={handleSubmit} className="grid grid-cols-2 gap-4">
                                <div className="flex flex-col">
                                    <input
                                        type="text"
                                        placeholder="Description"
                                        className="border border-gray-300 rounded-md px-3 py-2 mb-2"
                                        value={formData.description}
                                        onChange={(e) => setFormData({ ...formData, description: e.target.value })}
                                    />
                                    <input
                                        type="text"
                                        placeholder="Method"
                                        className="border border-gray-300 rounded-md px-3 py-2 mb-2"
                                        value={formData.method}
                                        onChange={(e) => setFormData({ ...formData, method: e.target.value })}
                                    />
                                    <input
                                        type="date"
                                        placeholder="Date"
                                        className="border border-gray-300 rounded-md px-3 py-2 mb-2"
                                        value={formData.date}
                                        onChange={(e) => setFormData({ ...formData, date: e.target.value })}
                                    />
                                    <input
                                        type="number"
                                        placeholder="Amount"
                                        className="border border-gray-300 rounded-md px-3 py-2 mb-2"
                                        value={formData.amount === -1 ? "" : formData.amount}
                                        onChange={(e) => setFormData({ ...formData, amount: parseFloat(e.target.value) })}
                                    />
                                </div>
                                <div className="flex flex-col">
                                    <select
                                        value={formData.type}
                                        onChange={(e) => setFormData({ ...formData, type: parseInt(e.target.value) as (1 | 2) })}
                                        className="border border-gray-300 rounded-md px-3 py-2 mb-2"
                                    >
                                        <option value={0}>{formData.type ? "" : "Type"}</option>
                                        <option value={2}>Expense</option>
                                        <option value={1}>Credit</option>
                                    </select>
                                    <select
                                        value={formData.categoryId}
                                        onChange={(e) => setFormData({ ...formData, categoryId: parseInt(e.target.value) })}
                                        className="border border-gray-300 rounded-md px-3 py-2 mb-2"
                                    >
                                        <option value={0}>{formData.categoryId ? "" : "Select a category..."}</option>
                                        {categories.map(category => (
                                            <option key={category.id} value={category.id}>{category.name}</option>
                                        ))}
                                    </select>
                                    <select
                                        value={formData.bankAccountId}
                                        onChange={(e) => setFormData({ ...formData, bankAccountId: parseInt(e.target.value) })}
                                        className="border border-gray-300 rounded-md px-3 py-2 mb-2"
                                    >
                                        <option value={0}>{formData.bankAccountId ? "" : "Select a bank account..."}</option>
                                        {bankAccounts.map(account => (
                                            <option key={account.id} value={account.id}>{account.bankName}</option>
                                        ))}
                                    </select>
                                    <div className="flex justify-center">
                                        <button type="submit" className="bg-green-500 text-white px-4 py-2 rounded-md w-full mr-2">Add</button>
                                        <button type="button" onClick={onClose} className="bg-gray-300 text-gray-800 px-4 py-2 rounded-md w-full">Cancel</button>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            )}
        </>


    );

};

export default AddTransactionModal;
