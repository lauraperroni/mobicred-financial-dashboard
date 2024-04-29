import React, { useState } from 'react';
import { differenceInDays } from 'date-fns';

interface GoalTransaction {
    id: string;
    description: string;
    method: string;
    date: string;
    amount: number;
    daysLeft: number;
    deadline: string;
    saved: number;
}

const defaultGoals: GoalTransaction[] = [
    {
        id: '1',
        description: 'Buy a new laptop',
        method: 'Credit Card',
        date: '2024-05-15',
        amount: 1000.00,
        daysLeft: 22,
        deadline: '2024-05-15',
        saved: 500.00
    },
    {
        id: '2',
        description: 'Travel to Europe',
        method: 'Savings Account',
        date: '2024-07-10',
        amount: 3000.00,
        daysLeft: 78,
        deadline: '2024-07-10',
        saved: 1500.00
    }
];

const GoalsList: React.FC = () => {
    const [showModal, setShowModal] = useState<boolean>(false);
    const [description, setDescription] = useState<string>('');
    const [deadline, setDeadline] = useState<string>('');
    const [amount, setAmount] = useState<number>(0);
    const [selectedTransaction, setSelectedTransaction] = useState<GoalTransaction | null>(null);
    const [transactions, setTransactions] = useState<GoalTransaction[]>(defaultGoals);

    const handleEdit = (transaction: GoalTransaction) => {
        setSelectedTransaction(transaction);
        setDescription(transaction.description);
        setDeadline(transaction.deadline);
        setAmount(transaction.amount);
        setShowModal(true);
    };

    const handleDelete = (transaction: GoalTransaction) => {
        const confirmed = window.confirm('Are you sure you want to delete this transaction?');
        if (confirmed) {
            setTransactions(transactions.filter(item => item.id !== transaction.id));
            setShowModal(false);
        }
    };

    const handleAddGoal = () => {
        if (!description || !deadline || !amount) {
            alert("Please fill out all fields");
            return;
        }

        const today = new Date();
        const daysLeft = differenceInDays(new Date(deadline), today);

        const newGoal: GoalTransaction = {
            id: Math.random().toString(),
            description,
            method: '',
            date: '',
            amount,
            daysLeft,
            deadline,
            saved: 0
        };

        setTransactions([...transactions, newGoal]);
        setShowModal(false);
        setDescription('');
        setDeadline('');
        setAmount(0);
    };

    const handleSaveEdit = () => {
        if (!selectedTransaction) return;

        const updatedTransaction: GoalTransaction = {
            ...selectedTransaction,
            description,
            deadline,
            amount
        };

        const updatedTransactions = transactions.map(item =>
            item.id === updatedTransaction.id ? updatedTransaction : item
        );

        setTransactions(updatedTransactions);
        setShowModal(false);
        setDescription('');
        setDeadline('');
        setAmount(0);
    };

    return (
        <div className="container mx-auto p-8 bg-gray-50">
            <h2 className="text-2xl font-semibold mb-4">Your Goals</h2>
            <p className="mb-4">Check your financial goals</p>
            <div className="relative overflow-x-auto shadow-md sm:rounded-lg">
                <table className="w-full text-sm text-left text-gray-500 dark:text-gray-400">
                    <colgroup>
                        <col style={{ width: '30%' }} />
                        <col style={{ width: '5%' }} />
                        <col style={{ width: '15%' }} />
                        <col style={{ width: '20%' }} />
                        <col style={{ width: '15%' }} />
                        <col style={{ width: '15%' }} />
                    </colgroup>
                    <thead className="text-xs bg-gray-200 dark:bg-gray-700 dark:text-gray-400">
                        <tr>
                            <th scope="col" className="px-6 py-3">
                                Goal
                            </th>
                            <th scope="col" className="px-6 py-3">
                                Days Left
                            </th>
                            <th scope="col" className="px-6 py-3">
                                Deadline
                            </th>
                            <th scope="col" className="px-6 py-3">
                                Amount
                            </th>
                            <th scope="col" className="px-6 py-3">
                                Saved
                            </th>
                            <th scope="col" className="px-6 py-3">
                                Options
                            </th>
                        </tr>
                    </thead>
                    <tbody>
                        {transactions.map(transaction => (
                            <tr key={transaction.id} className="bg-white border-b dark:bg-gray-800">
                                <td className="px-6 py-4 font-medium whitespace-nowrap">{transaction.description}</td>
                                <td className="px-6 py-4">{transaction.daysLeft}</td>
                                <td className="px-6 py-4">{transaction.deadline}</td>
                                <td className="px-6 py-4">${transaction.amount.toFixed(2)}</td>
                                <td className="px-6 py-4">${transaction.saved.toFixed(2)}</td>
                                <td className="px-6 py-4 text-right">
                                    <button onClick={() => handleEdit(transaction)} className="mr-2 bg-yellow-500 text-white px-2 py-1 rounded-md">Edit</button>
                                    <button onClick={() => handleDelete(transaction)} className="bg-red-500 text-white px-2 py-1 rounded-md">Delete</button>
                                </td>
                            </tr>
                        ))}
                    </tbody>
                </table>
            </div>
            <button onClick={() => setShowModal(true)} className="mt-4 bg-green-600 text-white px-4 py-2 rounded-md">
                Add New Goal
            </button>
            {showModal && (
                <div className="fixed top-0 left-0 z-50 flex items-center justify-center w-full h-full bg-gray-900 bg-opacity-50">
                    <div className="bg-white p-8 rounded-xl">
                        <h2 className="text-lg font-semibold mb-4">Add New Goal</h2>
                        <input
                            type="text"
                            placeholder="Description"
                            value={description}
                            onChange={(e) => setDescription(e.target.value)}
                            className="mb-2 w-full border border-gray-300 rounded px-3 py-2"
                        />
                        <input
                            type="date"
                            placeholder="Deadline"
                            value={deadline}
                            onChange={(e) => setDeadline(e.target.value)}
                            className="mb-2 w-full border border-gray-300 rounded px-3 py-2"
                        />
                        <input
                            type="number"
                            placeholder="Amount"
                            value={amount}
                            onChange={(e) => setAmount(parseFloat(e.target.value))}
                            className="mb-4 w-full border border-gray-300 rounded px-3 py-2"
                        />

                        <button onClick={() => setShowModal(false)} className="bg-gray-300 text-gray-800 px-4 py-2 rounded-md mr-2">Cancel</button>
                        <button onClick={handleAddGoal} className="bg-blue-500 text-white px-4 py-2 rounded-md">Create</button>
                    </div>
                </div>
            )}

        </div>
    );
};

export default GoalsList;
