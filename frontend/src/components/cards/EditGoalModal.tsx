import React, { useEffect } from 'react';

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

interface EditGoalModalProps {
    showModal: boolean;
    setShowModal: (show: boolean) => void;
    selectedGoal: Goal | null;
    setSelectedGoal: (goal: Goal | null) => void;
    typeOptions: { [key: number]: string };
    selectedType: number;
    setSelectedType: (type: number) => void;
    description: string;
    setDescription: (description: string) => void;
    deadline: string;
    setDeadline: (deadline: string) => void;
    amount: number;
    setAmount: (amount: number) => void;
    handleSaveEdit: () => void;
}

const EditGoalModal: React.FC<EditGoalModalProps> = ({
    showModal,
    setShowModal,
    selectedGoal,
    typeOptions,
    selectedType,
    setSelectedType,
    description,
    setDescription,
    deadline,
    setDeadline,
    amount,
    setAmount,
    handleSaveEdit,
}) => {
    if (!showModal) return null;

    useEffect(() => {
        if (selectedGoal) {
            setDescription(selectedGoal.description);
            setDeadline(formatDateForInput(selectedGoal.deadline)); // Formatar a data para aaaa-mm-dd para o input
            setAmount(selectedGoal.amount);
            setSelectedType(selectedGoal.type);
        }
    }, [selectedGoal]);

    // Formatar a data para aaaa-mm-dd para o input type="date"
    const formatDateForInput = (dateString: string) => {
        const dateParts = dateString.split('/');
        return `${dateParts[2]}-${dateParts[1]}-${dateParts[0]}`;
    };

    return (
        <div className="fixed top-0 left-0 flex items-center justify-center w-full h-full bg-gray-900 bg-opacity-50 z-50">
            <div className="bg-gray-50 shadow-lg rounded-lg overflow-hidden max-w-xl w-96 m-4 z-50">
                <div className="bg-green-500 text-white px-4 py-2 p-8">
                    <h2 className="text-xl font-semibold">Edit Goal</h2>
                </div>
                <div className="p-4">
                    <form onSubmit={(e) => { e.preventDefault(); handleSaveEdit(); }} className="space-y-4">
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
                            <button type="submit" className="bg-green-500 text-white px-4 py-2 rounded-md">Save</button>
                            <button type="button" onClick={() => setShowModal(false)} className="bg-gray-300 text-gray-800 px-4 py-2 rounded-md ml-2">Cancel</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    );
};

export default EditGoalModal;
