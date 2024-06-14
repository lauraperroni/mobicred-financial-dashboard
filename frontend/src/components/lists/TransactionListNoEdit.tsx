import React, { useState, useEffect } from 'react';
import { TransactionsService } from '../../services/Transactions/TransactionsService';

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

interface TransactionListNoEditProps {
  period: string;
}

const TransactionListNoEdit: React.FC<TransactionListNoEditProps> = ({ period }) => {
  const [transactions, setTransactions] = useState<Transaction[]>([]);

  useEffect(() => {
    const fetchTransactions = async () => {
      try {
        const response = await TransactionsService.getTransactions();
        if (response && response.status === 200) {
          console.log('Dados recebidos da API:', response.data);
          setTransactions(response.data);
        } else {
          console.error('Error fetching transactions:', response);
        }
      } catch (error) {
        console.error('Error fetching transactions:', error);
      }
    };

    fetchTransactions();
  }, []);

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

  const getLastFiveTransactions = () => {
    const filteredTransactions = transactions.filter(filterTransactionsByPeriod);
    const sortedTransactions = filteredTransactions.sort((a, b) => new Date(b.date).getTime() - new Date(a.date).getTime());
    return sortedTransactions.slice(0, 5);
  };

  return (
    <div className="container mx-auto p-8">
      <h2 className="text-2xl font-semibold">Last Transactions</h2>
      <p className="mb-4">Check your last transactions</p>
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
            </tr>
          </thead>
          <tbody>
            {getLastFiveTransactions().map(transaction => (
              <tr key={transaction.id} className={transaction.type === TransactionType.Expense ? "bg-red-100 border-b dark:bg-gray-800" : "bg-green-100 border-b dark:bg-gray-800"}>
                <td className="px-6 py-4 font-medium whitespace-nowrap">{transaction.description}</td>
                <td className="px-6 py-4">{transaction.method}</td>
                <td className="px-6 py-4">{new Date(transaction.date).toLocaleDateString('en-GB')}</td>
                <td className="px-6 py-4">${transaction.amount.toFixed(2)}</td>
                <td className="px-6 py-4">{transaction.categoryName}</td>
                <td className="px-6 py-4">{transaction.bankName}</td>
                <td className="px-6 py-4">{transaction.type === TransactionType.Expense ? "Expense" : "Income"}</td>
              </tr>
            )).reverse() /* Invertendo a ordem das transações para exibir as mais recentes primeiro */}
          </tbody>
        </table>
      </div>
    </div>
  );
};
export default TransactionListNoEdit;
