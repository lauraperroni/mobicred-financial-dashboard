import React, { useState } from 'react';
import EditBankAccountModal from './EditBankAccountModal'; // Importe o novo modal de edição

interface BankCardDetailsProps {
    isOpen: boolean;
    onCloseModal: () => void;
    id: number;
    userId: number;
    bankNumber: number;
    bankName: string;
    balance: number;
    nextBillingDate: string;
    billingBalance: number;
    accountType: string;
    onSaveChanges: (formData: any) => void;
}

const BankCardDetails: React.FC<BankCardDetailsProps> = ({ isOpen, onCloseModal, id, userId, bankNumber, bankName, balance, nextBillingDate, billingBalance, accountType, onSaveChanges }) => {
    const [isEdit, setIsEdit] = useState(false);
    const [formData, setFormData] = useState({
        bankName,
        balance: balance.toString(),
        nextBillingDate,
        billingBalance: billingBalance.toString(),
        accountType,
        id,
        userId,
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
        <>
            {isEdit ? (
                <EditBankAccountModal
                    isOpen={isOpen}
                    onCloseModal={onCloseModal}
                    bankNumber={bankNumber}
                    bankName={bankName}
                    balance={balance}
                    nextBillingDate={nextBillingDate}
                    billingBalance={billingBalance}
                    accountType={accountType}
                    onSaveChanges={onSaveChanges}
                />
            ) : (
                <div className="fixed top-0 left-0 flex justify-center items-center w-full h-full bg-black bg-opacity-50">
                    <div className="bg-white shadow-lg rounded-lg overflow-hidden max-w-lg w-full m-4">
                        <div className="bg-green-500 text-white px-4 py-2">
                            <h2 className="text-xl font-semibold">{isEdit ? 'Edit Bank Details' : 'Bank Details'}</h2>
                        </div>
                        <div className="p-4">
                            <p className="mb-2"><span className="font-semibold">Bank Number:</span> {bankNumber}</p>
                            <p className="mb-2"><span className="font-semibold">Account Type:</span> {accountType}</p>
                            <p className="mb-2"><span className="font-semibold">Bank Name:</span> {bankName}</p>
                            <p className="mb-2"><span className="font-semibold">Balance:</span> {balance}</p>
                            <p className="mb-2"><span className="font-semibold">Due Date:</span> {nextBillingDate}</p>
                            <p className="mb-2"><span className="font-semibold">Amount:</span> {billingBalance}</p>
                            <div className="flex space-x-2 mt-4">
                                <button onClick={onCloseModal} className="bg-gray-200 px-4 py-2 rounded-md">Close</button>
                                <button onClick={() => setIsEdit(true)} className="bg-green-500 text-white px-4 py-2 rounded-md">Edit</button>
                            </div>
                        </div>
                    </div>
                </div>
            )}
        </>
    );
};

export default BankCardDetails;
