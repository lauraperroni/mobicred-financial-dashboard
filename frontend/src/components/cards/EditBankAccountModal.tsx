import React, { useState } from 'react';

interface EditBankAccountModalProps {
    isOpen: boolean;
    onCloseModal: () => void;
    bankNumber: number;
    bankName: string;
    balance: number;
    nextBillingDate: string;
    billingBalance: number;
    accountType: string;
    onSaveChanges: (formData: any) => void;
}

const EditBankAccountModal: React.FC<EditBankAccountModalProps> = ({ isOpen, onCloseModal, bankNumber, bankName, balance, nextBillingDate, billingBalance, accountType, onSaveChanges }) => {
    const [formData, setFormData] = useState({
        bankName,
        balance: balance.toString(),
        nextBillingDate,
        billingBalance: billingBalance.toString(),
        accountType,
        bankNumber
    });

    const handleInputChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        const { name, value } = e.target;
        setFormData(prevState => ({
            ...prevState,
            [name]: value
        }));
    };

    const handleSaveChanges = async () => {
        try {
            await onSaveChanges(formData);
            onCloseModal();
        } catch (error) {
            console.error('Erro ao atualizar conta bancária:', error);
        }
    };

    if (!isOpen) return null;

    return (
        <div className="fixed top-0 left-0 flex justify-center items-center w-full h-full bg-black bg-opacity-50">
            <div className="bg-white shadow-lg rounded-lg overflow-hidden max-w-lg w-full m-4">
                <div className="bg-green-500 text-white px-4 py-2">
                    <h2 className="text-xl font-semibold">Edit Bank Account Detalhesss</h2>
                </div>
                <div className="p-4 flex flex-wrap">
                    <div className="w-full md:w-1/2 pr-2">
                        <label className="block mb-2">Bank Name</label>
                        <input type="text" name="bankName" value={formData.bankName} onChange={handleInputChange} className="w-full px-3 py-2 mb-2 border rounded-md" />

                        <label className="block mb-2">Bank Number</label>
                        <input type="text" name="bankNumber" value={formData.bankNumber} onChange={handleInputChange} className="w-full px-3 py-2 mb-2 border rounded-md" />

                        <label className="block mb-2">Balance</label>
                        <input type="number" name="balance" value={formData.balance} onChange={handleInputChange} className="w-full px-3 py-2 mb-2 border rounded-md" />
                    </div>
                    <div className="w-full md:w-1/2 pl-2">
                        <label className="block mb-2">Due Date</label>
                        <input type="text" name="nextBillingDate" value={formData.nextBillingDate} onChange={handleInputChange} className="w-full px-3 py-2 mb-2 border rounded-md" />

                        <label className="block mb-2">Amount</label>
                        <input type="number" name="billingBalance" value={formData.billingBalance} onChange={handleInputChange} className="w-full px-3 py-2 mb-2 border rounded-md" />

                        <label className="block mb-2">Account Type</label>
                        <input type="text" name="accountType" value={formData.accountType} onChange={handleInputChange} className="w-full px-3 py-2 mb-2 border rounded-md" />
                    </div>
                    <div className="w-full mt-4">
                        <button onClick={onCloseModal} className="bg-gray-200 px-4 py-2 rounded-md">Close</button>
                        <button onClick={handleSaveChanges} className="bg-green-500 text-white px-4 py-2 rounded-md">Save</button>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default EditBankAccountModal;
