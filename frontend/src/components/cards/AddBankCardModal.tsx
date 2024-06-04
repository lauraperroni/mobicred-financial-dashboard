import React from 'react';

interface AddBankCardModalProps {
    isOpen: boolean;
    onClose: () => void;
    onAddCard: (formData: any) => void; // Atualizado para aceitar formData
    formData: {
        bankName: string;
        balance: string;
        nextBillingDate: string;
        billingBalance: string;
        accountType: string;
    };
    handleInputChange: (e: React.ChangeEvent<HTMLInputElement>) => void;
}

const AddBankCardModal: React.FC<AddBankCardModalProps> = ({ isOpen, onClose, onAddCard, formData, handleInputChange }) => {

    const handleSubmit = (e: React.FormEvent) => {
        e.preventDefault();
        onAddCard(formData); // Chama onAddCard com o formsData
        console.log('Dados a enviar para a API:', formData);
        onClose();
    };

    return (
        <>
            {isOpen && (
                <div className="fixed top-0 left-0 flex items-center justify-center w-full h-full bg-gray-900 bg-opacity-50 z-50">
                    <div className="bg-gray-50 shadow-lg rounded-lg overflow-hidden max-w-xl w-96 m-4 z-50">
                        <div className="bg-green-500 text-white px-4 py-2 p-8">
                            <h2 className="text-xl font-semibold">Add New Card</h2>
                        </div>
                        <div className="p-4">
                            <form onSubmit={handleSubmit} className="space-y-4">
                                <div className="flex flex-col">
                                    <label htmlFor="bankName" className="text-gray-800">Bank Name:</label>
                                    <input type="text" id="bankName" name="bankName" value={formData.bankName} onChange={handleInputChange} required className="border border-gray-300 rounded-md px-3 py-2" />
                                </div>
                                <div className="flex flex-col">
                                    <label htmlFor="balance" className="text-gray-800">Current Balance:</label>
                                    <input type="number" id="balance" name="balance" value={formData.balance} onChange={handleInputChange} step="0.01" required className="border border-gray-300 rounded-md px-3 py-2" />
                                </div>
                                <div className="flex flex-col">
                                    <label htmlFor="nextBillingDate" className="text-gray-800">Next Billing Date:</label>
                                    <input type="text" id="nextBillingDate" name="nextBillingDate" value={formData.nextBillingDate} onChange={handleInputChange} required className="border border-gray-300 rounded-md px-3 py-2" />
                                </div>
                                <div className="flex flex-col">
                                    <label htmlFor="billingBalance" className="text-gray-800">Billing Balance:</label>
                                    <input type="number" id="billingBalance" name="billingBalance" value={formData.billingBalance} onChange={handleInputChange} step="0.01" required className="border border-gray-300 rounded-md px-3 py-2" />
                                </div>
                                <div className="flex flex-col">
                                    <label htmlFor="accountType" className="text-gray-800">Account Type:</label>
                                    <input type="text" id="accountType" name="accountType" value={formData.accountType} onChange={handleInputChange} required className="border border-gray-300 rounded-md px-3 py-2" />
                                </div>
                                <div className="flex justify-end">
                                    <button type="submit" className="bg-green-500 text-white px-4 py-2 rounded-md">Add Card</button>
                                    <button type="button" onClick={onClose} className="bg-gray-300 text-gray-800 px-4 py-2 rounded-md ml-2">Cancel</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            )}
        </>
    );
};

export default AddBankCardModal;
