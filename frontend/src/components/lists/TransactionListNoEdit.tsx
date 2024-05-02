import React, { useState } from 'react';

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
}

// Componente TransactionList
const TransactionList: React.FC<TransactionListProps> = ({ period }) => {
  const [showModal, setShowModal] = useState<boolean>(false);
  const [selectedTransaction, setSelectedTransaction] = useState<Transaction | null>(null);
  const [transactions, setTransactions] = useState<Transaction[]>(defaultTransactions);


  // Função para lidar com a conclusão da edição de uma transação
  const handleDone = (updatedTransaction: Transaction) => {
    const updatedTransactions = transactions.map((item) =>
      item.id === updatedTransaction.id ? updatedTransaction : item
    );
    setTransactions(updatedTransactions);
    setShowModal(false);
  };

  // Função para lidar com a exclusão de uma transação

  // Função para adicionar uma nova transação

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
      <div className="relative overflow-x-auto shadow-md sm:rounded-lg z-0"> {/* Define uma classe e um z-index menor para a tabela de transações */}
        <table className="w-full text-sm text-left text-gray-500 dark:text-gray-400">
          <colgroup>
            <col style={{ width: '30%' }} />
            <col style={{ width: '10%' }} />
            <col style={{ width: '10%' }} />
            <col style={{ width: '15%' }} />
            <col style={{ width: '15%' }} />
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
              </tr>
            ))}
          </tbody>
        </table>
      </div>
      {/* Renderizando o modal apenas se showModal for verdadeiro */}
      {showModal && selectedTransaction && (
        <div className="fixed top-0 left-0 z-50 flex items-center justify-center w-full h-full bg-gray-900 bg-opacity-50">
          <div className="bg-white p-8 rounded-xl">
            <h2 className="text-lg font-semibold mb-4">Edit Transaction</h2>
            <input
              type="text"
              value={selectedTransaction.description}
              onChange={(e) => setSelectedTransaction({ ...selectedTransaction, description: e.target.value })}
              className="mb-2 w-full border border-gray-300 rounded px-3 py-2"
            />
            <input
              type="text"
              value={selectedTransaction.method}
              onChange={(e) => setSelectedTransaction({ ...selectedTransaction, method: e.target.value })}
              className="mb-2 w-full border border-gray-300 rounded px-3 py-2"
            />
            <input
              type="date"
              value={selectedTransaction.date}
              onChange={(e) => setSelectedTransaction({ ...selectedTransaction, date: e.target.value })}
              className="mb-2 w-full border border-gray-300 rounded px-3 py-2"
            />
            <input
              type="number"
              value={selectedTransaction.amount}
              onChange={(e) => setSelectedTransaction({ ...selectedTransaction, amount: parseFloat(e.target.value) })}
              className="mb-2 w-full border border-gray-300 rounded px-3 py-2"
            />
            <label className="block mb-2">
              Type:
              <select
                value={selectedTransaction.type}
                onChange={(e) => setSelectedTransaction({ ...selectedTransaction, type: parseInt(e.target.value) as TransactionType })}
                className="w-full border border-gray-300 rounded px-3 py-2"
              >
                <option value={TransactionType.Expense}>Expense/Transfer</option>
                <option value={TransactionType.Credit}>Credit</option>
              </select>
            </label>
            <button onClick={() => handleDone(selectedTransaction)} className="bg-blue-500 text-white px-4 py-2 rounded-md mr-2">Done</button>
            <button onClick={() => setShowModal(false)} className="bg-gray-300 text-gray-800 px-4 py-2 rounded-md">Cancel</button>
          </div>
        </div>
      )}
    </div>
  );
};

export default TransactionList;
