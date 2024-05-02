import React, { useState } from 'react';
import AddTransactionModal from '../cards/AddTransactionModal';

// Enum para representar o tipo de transação
enum TransactionType {
  Expense = 1,
  Credit = 2,
}

export interface Transaction {
  id: number;
  description: string;
  method: string;
  date: string;
  amount: number;
  type: 1 | 2; // Adicionado o campo "type"
}

// Lista de transações padrão
const defaultTransactions: Transaction[] = [
  {
    id: 1,
    description: 'Groceries',
    method: 'Credit Card',
    date: '2024-04-19',
    amount: 50.00,
    type: TransactionType.Expense,
  },
  {
    id: 2,
    description: 'Restaurant',
    method: 'Cash',
    date: '2024-04-18',
    amount: 30.00,
    type: TransactionType.Expense,
  },
  {
    id: 3,
    description: 'Online Shopping',
    method: 'PayPal',
    date: '2024-04-17',
    amount: 100.00,
    type: TransactionType.Credit,
  }
];

// Props para o componente TransactionList
interface TransactionListProps {
  period: string;
  onAddTransaction: (newTransaction: Transaction) => void; // Adicionado prop para adicionar transação
}

// Componente TransactionList
const TransactionList: React.FC<TransactionListProps> = ({ period, onAddTransaction }) => {
  const [showModal, setShowModal] = useState<boolean>(false);
  const [selectedTransaction, setSelectedTransaction] = useState<Transaction | null>(null);
  const [transactions, setTransactions] = useState<Transaction[]>(defaultTransactions);

  // Função para lidar com a edição de uma transação
  const handleEdit = (transaction: Transaction) => {
    setSelectedTransaction(transaction);
    setShowModal(true);
  };

  // Função para lidar com a conclusão da edição de uma transação
  const handleDone = (updatedTransaction: Transaction) => {
    const updatedTransactions = transactions.map((item) =>
      item.id === updatedTransaction.id ? updatedTransaction : item
    );
    setTransactions(updatedTransactions);
    setShowModal(false);
  };

  // Função para lidar com a exclusão de uma transação
  const handleDelete = (transaction: Transaction) => {
    const confirmed = window.confirm('Are you sure you want to delete this transaction?');
    if (confirmed) {
      setTransactions(transactions.filter(item => item.id !== transaction.id));
      setShowModal(false);
    }
  };

  // Função para adicionar uma nova transação
  const addTransaction = (newTransaction: Transaction) => {
    setTransactions([...transactions, newTransaction]);
  };

  // Função para filtrar as transações com base no período selecionado
  const filterTransactionsByPeriod = (transaction: Transaction): boolean => {
    const today = new Date();
    const transactionDate = new Date(transaction.date);
    switch (period) {
      case 'This month':
        return today.getMonth() === transactionDate.getMonth() && today.getFullYear() === transactionDate.getFullYear();
      case 'Last month':
        const lastMonth = new Date(today.getFullYear(), today.getMonth() - 1, 1);
        const lastMonthEnd = new Date(today.getFullYear(), today.getMonth(), 0);
        return transactionDate >= lastMonth && transactionDate <= lastMonthEnd;
      case 'This year':
        return today.getFullYear() === transactionDate.getFullYear();
      case 'Last 12 months':
        const last12MonthsStart = new Date(today.getFullYear() - 1, today.getMonth(), today.getDate());
        return transactionDate >= last12MonthsStart && transactionDate <= today;
      default:
        return true;
    }
  };

  return (
    <div className="container mx-auto p-8 bg-gray-50">
      <h2 className="text-2xl font-semibold mb-4">Last Transactions</h2>
      <p className="mb-4">Check your last transactions</p>
      {/* Adicionando botão para adicionar transação */}
      <button onClick={() => setShowModal(true)} className="bg-green-500 text-white px-4 py-2 rounded-md mb-4">Add Transaction</button>
      <div className="relative overflow-x-auto shadow-md sm:rounded-lg z-0"> {/* Define uma classe e um z-index menor para a tabela de transações */}
        <table className="w-full text-sm text-left text-gray-500 dark:text-gray-400">
          <colgroup>
            <col style={{ width: '30%' }} />
            <col style={{ width: '10%' }} />
            <col style={{ width: '10%' }} />
            <col style={{ width: '15%' }} />
            <col style={{ width: '15%' }} />
            <col style={{ width: '20%' }} />
          </colgroup>
          <thead className="text-xs bg-gray-200 dark:bg-gray-700 dark:text-gray-400">
            <tr>
              <th scope="col" className="px-6 py-3">
                Description
              </th>
              <th scope="col" className="px-6 py-3">
                Method
              </th>
              <th scope="col" className="px-6 py-3">
                Date
              </th>
              <th scope="col" className="px-6 py-3">
                Amount
              </th>
              <th scope="col" className="px-6 py-3">
                Type
              </th>
              <th scope="col" className="px-6 py-3">
                Options
              </th>
            </tr>
          </thead>
          <tbody>
            {transactions.filter(filterTransactionsByPeriod).map(transaction => (
              <tr key={transaction.id} className={transaction.type === TransactionType.Expense ? "bg-red-100 border-b dark:bg-gray-800" : "bg-green-100 border-b dark:bg-gray-800"}>
                <td className="px-6 py-4 font-medium whitespace-nowrap">{transaction.description}</td>
                <td className="px-6 py-4">{transaction.method}</td>
                <td className="px-6 py-4">{transaction.date}</td>
                <td className="px-6 py-4">${transaction.amount.toFixed(2)}</td>
                <td className="px-6 py-4">{transaction.type === TransactionType.Expense ? "Expense/Transfer" : "Credit"}</td>
                <td className="px-6 py-4 text-right">
                  <button onClick={() => handleEdit(transaction)} className="mr-2 bg-yellow-500 text-white px-2 py-1 rounded-md">Edit</button>
                  <button onClick={() => handleDelete(transaction)} className="bg-red-500 text-white px-2 py-1 rounded-md">Delete</button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
      {/* Renderizando o modal apenas se showModal for verdadeiro */}
      {showModal && (
        <div className="fixed top-0 left-0 z-50 flex items-center justify-center w-full h-full bg-gray-900 bg-opacity-50">
          <div className="bg-white p-8 rounded-xl">
            <h2 className="text-lg font-semibold mb-4">Add Transaction</h2>
            <AddTransactionModal
              isOpen={showModal}
              onClose={() => setShowModal(false)}
              onAddTransaction={addTransaction} // Passa a função de adição de transação como prop
            />

          </div>
        </div>
      )}
    </div>
  );
};

export default TransactionList;
