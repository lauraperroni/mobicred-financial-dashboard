import React, { useState, useEffect } from 'react';
import AddTransactionModal from '../cards/AddTransactionModal';
import { TransactionsService } from '../../services/Transactions/TransactionsService'; // Importe o serviço de transações

// Enum para representar o tipo de transação
enum TransactionType {
  Expense = 2,
  Credit = 1,
}

// Remova o campo id do tipo Transaction
interface Transaction {
  id: number;
  description: string;
  method: string;
  date: string;
  amount: number;
  type: 1 | 2;
  categoryId: number;
  bankAccountId: number;
}

// Props para o componente TransactionList
interface TransactionListProps {
  period: string;
  onAddTransaction: (newTransaction: Transaction) => void; // Adicionado prop para adicionar transação
}

// Componente TransactionList
const TransactionList: React.FC<TransactionListProps> = ({ period, onAddTransaction }) => {
  const [showModal, setShowModal] = useState<boolean>(false);
  const [selectedTransaction, setSelectedTransaction] = useState<Transaction | null>(null);
  const [transactions, setTransactions] = useState<Transaction[]>([]);

  // Função para buscar as transações do back-end
  const fetchTransactions = async () => {
    try {
      const response = await TransactionsService.getTransactions();
      if (response && response.status === 200) {
        console.log('Dados recebidos das transações:', response.data);
        setTransactions(response.data);
      } else {
        console.error('Error fetching transactions:', response);
      }
    } catch (error) {
      console.error('Error fetching transactions:', error);
    }
  };

  // UseEffect para buscar as transações quando o componente for montado
  useEffect(() => {
    fetchTransactions();
  }, []);

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
  const handleDelete = async (transaction: Transaction) => {
    const confirmed = window.confirm('Are you sure you want to delete this transaction?');
    if (confirmed) {
      try {
        const response = await TransactionsService.deleteTransactions(transaction.id);
        console.log("Trying to delete");
        if (response && response.status === 200) {
          // Atualiza a lista de transações após a exclusão bem-sucedida
          await fetchTransactions();
          setShowModal(false);
          console.log("If Delete");
        } else {
          console.error('Else Error deleting transaction:', response);
        }
      } catch (error) {
        console.error('Catch Error deleting transaction:', error);
      }
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

  const filteredTransactions = transactions.filter(filterTransactionsByPeriod);

  return (
    <div className="container mx-auto p-8 bg-gray-50">
      <h2 className="text-2xl font-semibold mb-4">Last Transactions</h2>
      <p className="mb-4">Check your last transactions</p>
      {/* Adicionando botão para adicionar transação */}
      <button onClick={() => setShowModal(true)} className="bg-green-500 text-white px-4 py-2 rounded-md mb-4">Add Transaction</button>
      <div className="relative overflow-x-auto shadow-md sm:rounded-lg z-0"> {/* Define uma classe e um z-index menor para a tabela de transações */}
        {filteredTransactions.length === 0 ? (
          <p className="text-center text-black p-4">You have no transactions registered.</p>
        ) : (
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
              {filteredTransactions.map(transaction => (
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
        )}
      </div>
      {/* Renderizando o modal apenas se showModal for verdadeiro */}
      {showModal && (
        <div className="fixed top-0 left-0 z-50 flex items-center justify-center w-full h-full bg-gray-900 bg-opacity-50">
          <div className="bg-white p-8 rounded-xl">
            <h2 className="text-lg font-semibold mb-4">Add Transaction</h2>
            <AddTransactionModal
              isOpen={showModal}
              onClose={() => setShowModal(false)}
            />
          </div>
        </div>
      )}
    </div>
  );
};

export default TransactionList;
