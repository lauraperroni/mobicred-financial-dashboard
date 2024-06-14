import React, { useState, useEffect } from 'react';
import AddTransactionModal from '../cards/AddTransactionModal';
import EditTransactionModal from '../cards/EditTransactionModal';
import { TransactionsService } from '../../services/Transactions/TransactionsService';
import transactionImage from '../../assets/transaction.png'; // Importe a imagem transaction.png

enum TransactionType {
  Expense = 2,
  Credit = 1,
}

interface Transaction {
  id: number;
  description: string;
  method: string;
  date: string;
  amount: number;
  type: 1 | 2;
  categoryId: number;
  categoryName: string;
  bankAccountId: number;
  bankName: string;
}

interface TransactionListProps {
  onAddTransaction: (newTransaction: Transaction) => void;
}

const TransactionList: React.FC<TransactionListProps> = ({ onAddTransaction }) => {
  const [showAddModal, setShowAddModal] = useState<boolean>(false);
  const [showEditModal, setShowEditModal] = useState<boolean>(false);
  const [selectedTransaction, setSelectedTransaction] = useState<Transaction | null>(null);
  const [transactions, setTransactions] = useState<Transaction[]>([]);

  const fetchTransactions = async () => {
    try {
      const response = await TransactionsService.getTransactions();
      if (response && response.status === 200) {
        console.log('Dados recebidos das transações:', response.data);
        setTransactions(response.data);
      } else {
        console.error('Erro ao buscar transações:', response);
      }
    } catch (error) {
      console.error('Erro ao buscar transações:', error);
    }
  };

  useEffect(() => {
    fetchTransactions();
  }, []);

  const handleCloseAddModal = () => {
    setShowAddModal(false);
    fetchTransactions();
  };

  const handleEditModal = (transaction: Transaction) => {
    setSelectedTransaction(transaction);
    setShowEditModal(true);
  };

  const handleCloseEditModal = () => {
    setShowEditModal(false);
    fetchTransactions();
  };

  const handleDelete = async (transaction: Transaction) => {
    try {
      const response = await TransactionsService.deleteTransactions(transaction.id);
      if (response && response.status === 200) {
        console.log('Transação excluída com sucesso:', transaction.id);
        fetchTransactions();
      } else {
        console.error('Erro ao excluir transação:', response);
      }
    } catch (error) {
      console.error('Erro ao excluir transação:', error);
    }
  };

  return (
    <div className="container mx-auto p-8">
      <div className="flex justify-between items-center mb-4">
        <div>
          <h2 className="text-2xl font-bold mb-4">Last Transactions</h2>
          <p className="mb-4">Check out your last transactions</p>
          <button onClick={() => setShowAddModal(true)} className="bg-green-500 text-white px-4 py-2 rounded-md">Add Transaction</button>
        </div>
        <img src={transactionImage} alt="Transactions" className="w-44" />
      </div>
      <div className="relative overflow-x-auto shadow-md sm:rounded-lg z-0">
        <table className="w-full text-sm text-left text-gray-500 dark:text-gray-400">
          <thead className="text-xs bg-gray-200 dark:bg-gray-700 dark:text-gray-400">
            <tr>
              <th className="px-6 py-3">Description</th>
              <th className="px-6 py-3">Method</th>
              <th className="px-6 py-3">Date</th>
              <th className="px-6 py-3">Amount</th>
              <th className="px-6 py-3">Category</th>
              <th className="px-6 py-3">Bank</th>
              <th className="px-6 py-3">Type</th>
              <th className="px-6 py-3 text-right">...</th>
            </tr>
          </thead>
          <tbody>
            {transactions
              .slice()
              .sort((a, b) => new Date(b.date).getTime() - new Date(a.date).getTime())
              .map(transaction => (
                <tr key={transaction.id} className={transaction.type === TransactionType.Expense ? "bg-red-100 border-b dark:bg-gray-800" : "bg-green-100 border-b dark:bg-gray-800"}>
                  <td className="px-6 py-4 font-medium whitespace-nowrap">{transaction.description}</td>
                  <td className="px-6 py-4">{transaction.method}</td>
                  <td className="px-6 py-4">{new Date(transaction.date).toLocaleDateString('en-GB')}</td>
                  <td className="px-6 py-4">${transaction.amount.toFixed(2)}</td>
                  <td className="px-6 py-4">{transaction.categoryName}</td>
                  <td className="px-6 py-4">{transaction.bankName}</td>
                  <td className="px-6 py-4">{transaction.type === TransactionType.Expense ? "Expense" : "Income"}</td>
                  <td className="px-6 py-4 text-right">
                    <button onClick={() => handleEditModal(transaction)} className="mr-2 bg-yellow-500 text-white px-2 py-1 rounded-md">Edit</button>
                    <button onClick={() => handleDelete(transaction)} className="bg-red-500 text-white px-2 py-1 rounded-md">Delete</button>
                  </td>
                </tr>
              ))}
          </tbody>
        </table>
      </div>
      {showAddModal && (
        <div className="fixed top-0 left-0 z-50 flex items-center justify-center w-full h-full bg-gray-900 bg-opacity-50">
          <div className="bg-white p-8 rounded-xl">
            <h2 className="text-lg font-semibold mb-4">New Transactions</h2>
            <AddTransactionModal isOpen={showAddModal} onClose={handleCloseAddModal} />
          </div>
        </div>
      )}
      {showEditModal && selectedTransaction && (
        <div className="fixed top-0 left-0 z-50 flex items-center justify-center w-full h-full bg-gray-900 bg-opacity-50">
          <div className="bg-white p-8 rounded-xl">
            <h2 className="text-lg font-semibold mb-4">Edit</h2>
            <EditTransactionModal isOpen={showEditModal} onClose={handleCloseEditModal} transaction={selectedTransaction} />
          </div>
        </div>
      )}
    </div>
  );
};

export default TransactionList;
