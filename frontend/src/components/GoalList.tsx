import React, { useState, useEffect } from 'react';
import { differenceInDays, format } from 'date-fns';
import { FinancialGoalsService } from '../services/Financial Goals/FinancialGoalsService';
import EditGoalModal from './cards/EditGoalModal'; // Importe o novo modal
import goalsImage from '../assets/goals.png'; // Importe a imagem goals.png

interface Goal {
    name: string;
    id: number;
    description: string;
    method: string;
    type: number;
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
    const [goals, setGoals] = useState<Goal[]>([]);
    const [selectedType, setSelectedType] = useState<number>(1);

    const [selectedGoal, setSelectedGoal] = useState<Goal | null>(null);
    const [showEditModal, setShowEditModal] = useState<boolean>(false);
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
        setSelectedType(goal.type);
        setShowEditModal(true); // Mostrar o modal de edição ao invés do modal de adição
    };

    const handleDelete = async (goal: Goal) => {
        const confirmed = window.confirm('Are you sure you want to delete this goal?');
        if (confirmed) {
            try {
                await FinancialGoalsService.deleteFinancialGoal(goal.id);
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
        fetchData();

        try {
            const response = await FinancialGoalsService.createFinancialGoal({
                ...newGoal,
                type: selectedType,
                name: typeOptions[selectedType],
            });
            if (response && response.data) {
                await fetchData();
                setShowModal(false);
                setDescription('');
                setDeadline('');
                setAmount(0);
            }
        } catch (error) {
            console.error('Error adding financial goal:', error);
        }
    };

    const handleOpenAddGoalModal = () => {
        // Resetar os estados para garantir que os campos do modal estejam vazios
        setDescription('');
        setDeadline('');
        setAmount(0);
        setSelectedType(1);
        setShowModal(true);
    };

    const handleSaveEdit = async () => {
        if (!selectedGoal) return;

        const updatedGoal: Goal = {
            ...selectedGoal,
            description,
            deadline,
            amount,
            type: selectedType,
            name: typeOptions[selectedType]
        };

        try {
            await FinancialGoalsService.updateFinancialGoal(selectedGoal.id, updatedGoal);
            const updatedGoals = goals.map(item =>
                item.id === updatedGoal.id ? updatedGoal : item
            );
            setGoals(updatedGoals);
            setShowEditModal(false); // Fechar o modal de edição após salvar as alterações
            setDescription('');
            setDeadline('');
            setAmount(0);
        } catch (error) {
            console.error('Error updating financial goal:', error);
        }
    };

    return (
        <div className="container mx-auto p-8">
            <div className="flex items-center justify-between mb-4">
                <div className="flex-1">
                    <h2 className="text-2xl font-bold mb-4">Your Goals</h2>
                    <p className="mb-4">Check your financial goals</p>
                    <button onClick={handleOpenAddGoalModal} className="bg-green-500 text-white px-4 py-2 rounded-md mt-2">
                        Add New Goal
                    </button>
                </div>
                <div className="flex-none">
                    <img src={goalsImage} alt="Goals" className="w-32 h-32" />
                </div>
            </div>

            <div className="relative overflow-x-auto shadow-md sm:rounded-lg">
                <table className="w-full text-sm text-left text-gray-500 dark:text-gray-400">
                    <colgroup>
                        <col style={{ width: '25%' }} />
                        <col style={{ width: '10%' }} />
                        <col style={{ width: '15%' }} />
                        <col style={{ width: '10%' }} />
                        <col style={{ width: '10%' }} />
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
                                Type
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
                            <tr key={goal.id} className={`border-b ${goal.saved === goal.amount ? 'bg-green-100' : 'bg-white'} dark:bg-gray-800`}>
                                <td className="px-6 py-4 font-medium whitespace-nowrap">{goal.description}</td>
                                <td className="px-6 py-4 font-medium whitespace-nowrap">{goal.name}</td>
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
                <div className="fixed top-0 left-0 flex items-center justify-center w-full h-full bg-gray-900 bg-opacity-50 z-50">
                    <div className="bg-gray-50 shadow-lg rounded-lg overflow-hidden max-w-xl w-96 m-4 z-50">
                        <div className="bg-green-500 text-white px-4 py-2 p-8">
                            <h2 className="text-xl font-semibold">Add New Goal</h2>
                        </div>
                        <div className="p-4">
                            <form onSubmit={(e) => { e.preventDefault(); handleAddGoal(); }} className="space-y-4">
                                <div className="flex flex-col">
                                    <label htmlFor="description" className="text-gray-800">Description:</label>
                                    <input type="text" id="description" name="description" value={description} onChange={(e) => setDescription(e.target.value)} required className="border border-gray-300 rounded-md px-3 py-2" />
                                </div>
                                <div className="flex flex-col">
                                    <label htmlFor="deadline" className="text-gray-800">Deadline:</label>
                                    <input type="date" id="deadline" name="deadline" value={deadline} onChange={(e) => setDeadline(e.target.value)} required className="border border-gray-300 rounded-md px-3 py-2" />
                                </div>
                                <div className="flex flex-col">
                                    <label htmlFor="amount" className="text-gray-800">Amount:</label>
                                    <input type="number" id="amount" name="amount" value={amount} onChange={(e) => setAmount(parseFloat(e.target.value))} step="0.01" required className="border border-gray-300 rounded-md px-3 py-2" />
                                </div>

                                <div className="flex flex-col">
                                    <label htmlFor="selectedType" className="text-gray-800">Type:</label>
                                    <select
                                        id="selectedType"
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
                                </div>

                                <div className="flex justify-end">
                                    <button type="submit" className="bg-green-500 text-white px-4 py-2 rounded-md">Create</button>
                                    <button type="button" onClick={() => setShowModal(false)} className="bg-gray-300 text-gray-800 px-4 py-2 rounded-md ml-2">Cancel</button>
                                </div>

                            </form>
                        </div>
                    </div>
                </div>
            )}

            {/* Modal de Edição */}
            <EditGoalModal
                showModal={showEditModal}
                setShowModal={setShowEditModal}
                selectedGoal={selectedGoal}
                setSelectedGoal={setSelectedGoal}
                typeOptions={typeOptions}
                selectedType={selectedType}
                setSelectedType={setSelectedType}
                description={description}
                setDescription={setDescription}
                deadline={deadline}
                setDeadline={setDeadline}
                amount={amount}
                setAmount={setAmount}
                handleSaveEdit={handleSaveEdit}
            />

        </div>
    );
};

export default GoalsList;