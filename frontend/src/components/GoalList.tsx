import React, { useState, useEffect } from 'react';
import { differenceInDays, format } from 'date-fns';
import { FinancialGoalsService } from '../services/Financial Goals/FinancialGoalsService';


interface Goal {
    id: number;
    description: string;
    method: string;
    date: string;
    amount: number;
    daysLeft: number;
    deadline: string;
    saved: number;
    creationDate: string;
}

const GoalsList: React.FC = () => {
    const [showModal, setShowModal] = useState<boolean>(false);
    const [description, setDescription] = useState<string>('');
    const [deadline, setDeadline] = useState<string>('');
    const [amount, setAmount] = useState<number>(0);
    const [selectedGoal, setSelectedGoal] = useState<Goal | null>(null);
    const [goals, setGoals] = useState<Goal[]>([]);
    const [selectedType, setSelectedType] = useState<number>(1);
    const typeOptions: { [key: number]: string } = {
        1: 'Compras',
        2: 'Viagens',
        3: 'Reformas',
        4: 'Presentes',
        5: 'Eventos'
    };

    useEffect(() => {
        fetchData();
    }, []);

    const fetchData = async () => {
        try {
            const response = await FinancialGoalsService.getFinancialGoals();
            if (response && response.data) {
                const formattedGoals = response.data.map((goal: Goal) => ({
                    ...goal,
                    creationDate: format(new Date(goal.creationDate), 'dd/MM/yyyy'),
                    deadline: format(new Date(goal.deadline), 'dd/MM/yyyy'),
                    daysLeft: calculateDaysLeft(new Date(goal.creationDate), new Date(goal.deadline))
                }));
                setGoals(formattedGoals);
                console.log('Financial goals:', formattedGoals);
            }
        } catch (error) {
            console.error('Error fetching financial goals:', error);
        }
    };

    const calculateDaysLeft = (creationDate: Date, deadline: Date) => {
        const diff = differenceInDays(deadline, creationDate);
        return diff;
    };

    const handleEdit = (goal: Goal) => {
        setSelectedGoal(goal);
        setDescription(goal.description);
        setDeadline(goal.deadline);
        setAmount(goal.amount);
        setShowModal(true);
    };

    const handleDelete = async (goal: Goal) => {
        const confirmed = window.confirm('Are you sure you want to delete this goal?');
        if (confirmed) {
            try {
                await FinancialGoalsService.deleteFinancialGoals(goal.id);
                setGoals(goals.filter(item => item.id !== goal.id));
            } catch (error) {
                console.error('Error deleting financial goal:', error);
            }
        }
    };

    const handleAddGoal = async () => {
        if (!description || !deadline || !amount) {
            alert("Please fill out all fields");
            return;
        }

        const today = new Date();
        const deadlineDate = new Date(deadline);
        const daysLeft = differenceInDays(deadlineDate, today);

        const newGoal = {
            description,
            amount,
            daysLeft,
            deadline: deadlineDate.toISOString().split('T')[0],
            creationDate: today.toISOString().split('T')[0],
            id: 0,
            method: '',
            date: '',
            saved: 0
        };

        console.log("New goal body:", newGoal);

        try {
            const response = await FinancialGoalsService.postFinancialGoals({
                ...newGoal,
                type: selectedType,
                name: typeOptions[selectedType],
            });
            if (response && response.data) {
                await fetchData();
                setShowModal(false);
                console.log("If add");
                setShowModal(false);
                setDescription('');
                setDeadline('');
                setAmount(0);
            }
        } catch (error) {
            console.error('Error adding financial goal:', error);
        }
    };

    const handleSaveEdit = async () => {
        if (!selectedGoal) return;

        const updatedGoal: Goal = {
            ...selectedGoal,
            description,
            deadline,
            amount
        };

        try {
            await FinancialGoalsService.putFinancialGoals(selectedGoal.id, {
                ...updatedGoal,
                type: selectedType,
                name: typeOptions[selectedType]
            });
            const updatedGoals = goals.map(item =>
                item.id === updatedGoal.id ? updatedGoal : item
            );
            setGoals(updatedGoals);
            setShowModal(false);
            setDescription('');
            setDeadline('');
            setAmount(0);
        } catch (error) {
            console.error('Error updating financial goal:', error);
        }
    };

    return (
        <div className="container mx-auto p-8 bg-gray-50">
            <h2 className="text-2xl font-semibold mb-4">Your Goals</h2>
            <p className="mb-4">Check your financial goals</p>
            <button onClick={() => setShowModal(true)} className="bg-green-500 text-white px-4 py-2 rounded-md mb-4">
                Add New Goal
            </button>
            <div className="relative overflow-x-auto shadow-md sm:rounded-lg">
                <table className="w-full text-sm text-left text-gray-500 dark:text-gray-400">
                    <colgroup>
                        <col style={{ width: '25%' }} />
                        <col style={{ width: '10%' }} />
                        <col style={{ width: '10%' }} />
                        <col style={{ width: '5%' }} />
                        <col style={{ width: '15%' }} />
                        <col style={{ width: '10%' }} />
                        <col style={{ width: '10%' }} />
                        <col style={{ width: '15%' }} />
                    </colgroup>
                    <thead className="text-xs bg-gray-200 dark:bg-gray-700 dark:text-gray-400">
                        <tr>
                            <th scope="col" className="px-6 py-3">
                                Goal
                            </th>
                            <th scope="col" className="px-6 py-3">
                                Starting Date
                            </th>
                            <th scope="col" className="px-6 py-3">
                                Deadline
                            </th>
                            <th scope="col" className="px-6 py-3">
                                Days Left
                            </th>
                            <th scope="col" className="px-6 py-3">
                                Amount
                            </th>
                            <th scope="col" className="px-6 py-3">
                                Saved
                            </th>
                            <th scope="col" className="px-6 py-3">
                                Left
                            </th>
                            <th scope="col" className="px-6 py-3">
                                Options
                            </th>
                        </tr>
                    </thead>
                    <tbody>
                        {goals.map(goal => (
                            <tr key={goal.id} className="bg-white border-b dark:bg-gray-800">
                                <td className="px-6 py-4 font-medium whitespace-nowrap">{goal.description}</td>
                                <td className="px-6 py-4">{goal.creationDate}</td>
                                <td className="px-6 py-4">{goal.deadline}</td>
                                <td className="px-6 py-4">{goal.daysLeft}</td>
                                <td className="px-6 py-4">{goal.amount ? `$${goal.amount.toFixed(2)}` : '$ 0'}</td>
                                <td className="px-6 py-4">{goal.saved ? `$${goal.saved.toFixed(2)}` : '$ 0'}</td>
                                <td className="px-6 py-4">{goal.amount ? `$${(goal.amount - goal.saved).toFixed(2)}` : '$ 0'}</td>
                                <td className="px-6 py-4 text-right">
                                    <button onClick={() => handleEdit(goal)} className="mr-2 bg-yellow-500 text-white px-2 py-1 rounded-md">Edit</button>
                                    <button onClick={() => handleDelete(goal)} className="bg-red-500 text-white px-2 py-1 rounded-md">Delete</button>
                                </td>
                            </tr>
                        ))}
                    </tbody>
                </table>
            </div>

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

                        <select
                            value={selectedType}
                            onChange={(e) => setSelectedType(parseInt(e.target.value))}
                            className="mb-4 w-full border border-gray-300 rounded px-3 py-2"
                        >
                            {Object.keys(typeOptions).map(key => (
                                <option key={key} value={parseInt(key)}>
                                    {typeOptions[parseInt(key)]}
                                </option>
                            ))}
                        </select>

                        {selectedGoal ? (
                            <button onClick={handleSaveEdit} className="bg-green-500 text-white px-4 py-2 rounded-md">Save</button>
                        ) : (
                            <button onClick={handleAddGoal} className="bg-green-500 text-white px-4 py-2 rounded-md">Create</button>
                        )}
                        <button onClick={() => setShowModal(false)} className="bg-gray-300 text-gray-800 px-4 py-2 rounded-md ml-2">Cancel</button>
                    </div>
                </div>
            )}
        </div>
    );
};

export default GoalsList;
