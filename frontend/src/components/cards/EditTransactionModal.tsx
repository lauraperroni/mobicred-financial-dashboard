import React, { useState, useEffect } from 'react';
import { BankAccountsService, TransactionsService } from '../../services';
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
    id: number; // Adicionei o ID para identificar a transação que está sendo editada
    amount: number;
    date: string;
    type: 1 | 2;
    categoryId: number;
    categoryName: string;
    bankName: string;
    bankAccountId: number;
    method: string;
    description: string;
}

interface EditTransactionModalProps {
    isOpen: boolean;
    onClose: () => void;
    transaction: Transaction; // Recebe a transação que será editada
}

const EditTransactionModal: React.FC<EditTransactionModalProps> = ({ isOpen, onClose, transaction }) => {
    const [formData, setFormData] = useState<Transaction>(transaction);
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

        if (transaction.date) {
            const formattedDate = new Date(transaction.date).toISOString().split('T')[0];
            setFormData(prevState => ({ ...prevState, date: formattedDate }));
        }
    }, []);

    const handleSubmit = async () => {
        try {
            const response = await TransactionsService.putTransactions(formData.id, formData);
            if (response && response.status === 200) {
                console.log('Transação atualizada com sucesso:', response.data);
                onClose(); // Fechar modal após sucesso
            } else {
                console.error('Erro ao atualizar transação:', response);
                // Tratar erro aqui (exibir mensagem de erro, etc.)
            }
        } catch (error) {
            console.error('Erro ao atualizar transação:', error);
            // Tratar erro aqui (exibir mensagem de erro, etc.)
        }
    };

    return (
        <>
            {isOpen && (
                <div className="fixed top-0 left-0 flex items-center justify-center w-full h-full bg-gray-900 bg-opacity-50 z-50">
                    <div className="bg-gray-50 shadow-lg rounded-lg overflow-hidden max-w-xl w-4/5 m-4 z-50">
                        <div className="bg-green-500 text-white px-4 py-2">
                            <h2 className="text-xl font-semibold">Edit Transaction</h2>
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
                                        value={formData.date || ''}
                                        onChange={(e) => setFormData({ ...formData, date: e.target.value })}
                                    />
                                    <div className="flex items-center">
                                        <input
                                            type="text"
                                            placeholder="Amount"
                                            className="border border-gray-300 rounded-md px-3 py-2 mb-2"
                                            style={{ width: "calc(100%)" }}
                                            value={"$" + formData.amount}
                                            onChange={(e) => {
                                                const value = e.target.value.replace("$", "");
                                                setFormData({ ...formData, amount: parseFloat(value) });
                                            }}
                                        />
                                    </div>
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
                                        <option value={0}>Select a category...</option>
                                        {categories.map(category => (
                                            <option key={category.id} value={category.id} selected={category.name === formData.categoryName}>{category.name}</option>
                                        ))}
                                    </select>
                                    <select
                                        value={formData.bankAccountId}
                                        onChange={(e) => setFormData({ ...formData, bankAccountId: parseInt(e.target.value) })}
                                        className="border border-gray-300 rounded-md px-3 py-2 mb-2"
                                    >
                                        <option value={0}>Select a bank account...</option>
                                        {bankAccounts.map(account => (
                                            <option key={account.id} value={account.id} selected={account.bankName === formData.bankName}>{account.bankName}</option>
                                        ))}
                                    </select>
                                    <div className="flex justify-center">
                                        <button type="submit" className="bg-green-500 text-white px-4 py-2 rounded-md w-full mr-2">Save</button>
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

export default EditTransactionModal;
